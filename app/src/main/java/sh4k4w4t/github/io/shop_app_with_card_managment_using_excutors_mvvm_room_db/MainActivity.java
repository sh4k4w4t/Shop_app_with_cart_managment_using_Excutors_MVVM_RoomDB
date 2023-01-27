package sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.PrimaryKey;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

import sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.utils.adapter.ShoeItemAdapter;
import sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.utils.model.ShoeItem;
import sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.views.DetailsActivity;

public class MainActivity extends AppCompatActivity implements ShoeItemAdapter.ShoeClickedListeners {
    private RecyclerView recyclerView;
    private List<ShoeItem> shoeItemList;
    private ShoeItemAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        initializeVariables();
        setupList();
        adapter.setShoeItemList(MainActivity.this,shoeItemList);
        recyclerView.setAdapter(adapter);
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
}