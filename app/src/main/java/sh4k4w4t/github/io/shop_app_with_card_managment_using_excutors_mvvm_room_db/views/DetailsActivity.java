package sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.views;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.R;
import sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.utils.model.ShoeItem;

public class DetailsActivity extends AppCompatActivity {

    private ImageView shoeImageView;
    private TextView shoeNameTV, shoeBrandNameTV, shoePriceTV;
    private AppCompatButton addToCartBtn;
    private ShoeItem shoeItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_details);

        shoeItem= getIntent().getParcelableExtra("showItem");
        intializeVariables();

        if (shoeItem!=null){
            setDataToWidgets();
        }

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
        shoeImageView = findViewById(R.id.detailActivityShoeIV);
        shoeNameTV = findViewById(R.id.detailActivityShoeNameTv);
        shoeBrandNameTV = findViewById(R.id.detailActivityShoeBrandNameTv);
        shoePriceTV = findViewById(R.id.detailActivityShoePriceTv);
        addToCartBtn = findViewById(R.id.detailActivityAddToCartBtn);
    }
}