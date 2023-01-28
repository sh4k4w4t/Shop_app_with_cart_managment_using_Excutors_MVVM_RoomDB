package sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.utils.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.R;
import sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.databinding.EachShoeBinding;
import sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.utils.model.ShoeItem;

public class ShoeItemAdapter extends RecyclerView.Adapter<ShoeItemAdapter.holder> {
    private List<ShoeItem> shoeItemList;
    Context context;

    private ShoeClickedListeners shoeClickedListeners;

    public ShoeItemAdapter(ShoeClickedListeners shoeClickedListeners) {
        this.shoeClickedListeners = shoeClickedListeners;
    }

    public void setShoeItemList(Context context, List<ShoeItem> shoeItemList) {
        this.shoeItemList = shoeItemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ShoeItemAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_shoe, parent, false);
        return new holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ShoeItemAdapter.holder holder, int position) {
        ShoeItem shoeItem = shoeItemList.get(position);
        holder.binding.eachShoeName.setText(shoeItem.getShoeName());
        holder.binding.eachShoeBrandNameTv.setText(shoeItem.getShoeBrand());
        holder.binding.eachShoePriceTv.setText(String.valueOf(shoeItem.getShoePrice()));
        Glide.with(context)
                .load(shoeItem.getShoeImage())
                .centerCrop()
                .into(holder.binding.eachShoeIv);
        holder.binding.eachShoeCardView.setOnClickListener(v -> shoeClickedListeners.onCardClicked(shoeItem));
        holder.binding.eachShoeAddToCartBtn.setOnClickListener(v -> shoeClickedListeners.onAddToCartBtnClicked(shoeItem));

    }

    @Override
    public int getItemCount() {
        if (shoeItemList == null) {
            return 0;
        }
        return shoeItemList.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        EachShoeBinding binding;

        public holder(@NonNull View itemView) {
            super(itemView);
            binding = EachShoeBinding.bind(itemView);
        }
    }

    public interface ShoeClickedListeners {
        void onCardClicked(ShoeItem shoeItem);
        void onAddToCartBtnClicked(ShoeItem shoeItem);
    }
}
