package sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;

import sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.R;
import sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.utils.adapter.ShoeItemAdapter;
import sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.utils.model.ShoeCart;
import sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.utils.model.ShoeItem;
import sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.viewmodel.CartViewModel;

public class MainActivity extends AppCompatActivity implements ShoeItemAdapter.ShoeClickedListeners {
    CoordinatorLayout coordinatorLayout;
    private RecyclerView recyclerView;
    private ImageView cartImageButton;
    private List<ShoeItem> shoeItemList;
    private ShoeItemAdapter adapter;

    private CartViewModel cartViewModel;
    private List<ShoeCart> shoeCartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        initializeVariables();
        setupList();
        cartViewModel.getAllCartItems().observe(this, new Observer<List<ShoeCart>>() {
            @Override
            public void onChanged(List<ShoeCart> shoeCarts) {
                shoeCartList.addAll(shoeCarts);
            }
        });

        adapter.setShoeItemList(MainActivity.this,shoeItemList);
        recyclerView.setAdapter(adapter);

        cartImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,CartActivity.class));
            }
        });
    }

    private void setupList() {
        shoeItemList.add(new ShoeItem("Nike Revolation", "Nike", R.drawable.nike_revolution_road, 1500));
        shoeItemList.add(new ShoeItem("Nike Flex run 2022", "Nike", R.drawable.flex_run_road_running, 2000));
        shoeItemList.add(new ShoeItem("Nike Zoom Braper", "Nike", R.drawable.adidas_eq_run, 1452));
        shoeItemList.add(new ShoeItem("Adidas Revolation", "Adidas", R.drawable.nike_revolution_road, 1300));
        shoeItemList.add(new ShoeItem("Adidas Ozilia", "Adidas", R.drawable.adidas_ozelia_shoes_grey, 800));
        shoeItemList.add(new ShoeItem("Adidas Revolation", "Adidas", R.drawable.adidas_ultraboost, 1315));
        shoeItemList.add(new ShoeItem("Adidas Questar", "Adidas", R.drawable.nike_revolution_road, 2560));
        shoeItemList.add(new ShoeItem("Adidas Ultraboost", "Adidas", R.drawable.adidas_questar_shoes, 5000));
    }

    private void initializeVariables() {
        coordinatorLayout= findViewById(R.id.coordinatorLayout);
        cartImageButton= findViewById(R.id.cartIv);

        cartViewModel= new ViewModelProvider(this).get(CartViewModel.class);
        shoeCartList= new ArrayList<>();

        shoeItemList = new ArrayList<>();
        recyclerView = findViewById(R.id.mainRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        adapter = new ShoeItemAdapter(this);
    }

    @Override
    public void onCardClicked(ShoeItem shoeItem) {
        Intent intent= new Intent(MainActivity.this, DetailsActivity.class);
        intent.putExtra("showItem",shoeItem);
        startActivity(intent);
    }

    @Override
    public void onAddToCartBtnClicked(ShoeItem shoeItem) {
        ShoeCart shoeCart= new ShoeCart();
        shoeCart.setShoeName(shoeItem.getShoeName());
        shoeCart.setShoeBrand(shoeItem.getShoeBrand());
        shoeCart.setShoePrice(shoeItem.getShoePrice());
        shoeCart.setShoeImage(shoeItem.getShoeImage());

        final int[] quantity= {1};
        final int[] id=new int[1];
        if (!shoeCartList.isEmpty()){
            for (int i=0;i<shoeCartList.size();i++){
                if (shoeCart.getShoeName().equals(shoeCartList.get(i).getShoeName())){
                    quantity[0]=shoeCartList.get(i).getQuantity();
                    quantity[0]++;
                    id[0]=shoeCartList.get(i).getId();
                }
            }
        }
        if (quantity[0]==1){
            shoeCart.setQuantity(quantity[0]);
            shoeCart.setTotalItemPrice(quantity[0]*shoeCart.getShoePrice());
            cartViewModel.insertCartItem(shoeCart);
        }else {
            cartViewModel.updateQuantity(id[0],quantity[0]);
            cartViewModel.updatePrice(id[0],quantity[0]*shoeCart.getShoePrice());

        }

        makeSnackBarInHere("Item added to cart.");
    }

    private void makeSnackBarInHere(String message) {
        Snackbar.make(coordinatorLayout,message,Snackbar.LENGTH_SHORT)
                .setAction("Go to Cart", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(MainActivity.this, CartActivity.class));
                    }
                }).show();
    }
}