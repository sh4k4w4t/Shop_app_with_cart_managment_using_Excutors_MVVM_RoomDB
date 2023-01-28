package sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.R;
import sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.utils.model.ShoeCart;
import sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.utils.model.ShoeItem;
import sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.viewmodel.CartViewModel;

public class DetailsActivity extends AppCompatActivity {

    private ImageView shoeImageView;
    private TextView shoeNameTV, shoeBrandNameTV, shoePriceTV;
    private AppCompatButton addToCartBtn;
    private ShoeItem shoeItem;
    private CartViewModel cartViewModel;
    private List<ShoeCart> shoeCartList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_details);

        shoeItem= getIntent().getParcelableExtra("showItem");

        intializeVariables();

        cartViewModel.getAllCartItems().observe(this, new Observer<List<ShoeCart>>() {
            @Override
            public void onChanged(List<ShoeCart> shoeCarts) {
                shoeCartList.addAll(shoeCarts);
            }
        });


        if (shoeItem!=null){
            setDataToWidgets();
        }

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addToCartBtnClicked();
            }
        });
    }

    private void addToCartBtnClicked() {
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
        startActivity(new Intent(this,CartActivity.class));
    }

    @SuppressLint("SetTextI18n")
    private void setDataToWidgets() {
        shoeNameTV.setText(shoeItem.getShoeName()+"");
        shoeBrandNameTV.setText(shoeItem.getShoeBrand()+"");
        shoePriceTV.setText(String.valueOf(shoeItem.getShoePrice()));
        Glide.with(this)
                .load(shoeItem.getShoeImage())
                .centerCrop()
                .into(shoeImageView);

    }

    private void intializeVariables() {
        shoeCartList= new ArrayList<>();
        shoeImageView = findViewById(R.id.detailActivityShoeIV);
        shoeNameTV = findViewById(R.id.detailActivityShoeNameTv);
        shoeBrandNameTV = findViewById(R.id.detailActivityShoeBrandNameTv);
        shoePriceTV = findViewById(R.id.detailActivityShoePriceTv);
        addToCartBtn = findViewById(R.id.detailActivityAddToCartBtn);

        cartViewModel= new ViewModelProvider(this).get(CartViewModel.class);
    }
}