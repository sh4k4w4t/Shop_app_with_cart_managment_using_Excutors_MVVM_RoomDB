package sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.utils.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.R;
import sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.databinding.EachCartItemBinding;
import sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.utils.model.ShoeCart;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.holder> {
    private List<ShoeCart> shoeCartList;
    private CartClickedListeners cartClickedListeners;

    public CartAdapter(CartClickedListeners cartClickedListeners) {
        this.cartClickedListeners = cartClickedListeners;
    }

    public void setShoeCartList(List<ShoeCart> shoeCartList) {
        this.shoeCartList = shoeCartList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CartAdapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_cart_item, parent, false);
        return new holder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CartAdapter.holder holder, int position) {
        ShoeCart shoeCart= shoeCartList.get(position);
        holder.binding.eachCartItemIV.setImageResource(shoeCart.getShoeImage());
        holder.binding.eachCartItemName.setText(shoeCart.getShoeName());
        holder.binding.eachCartItemBrandNameTv.setText(shoeCart.getShoeBrand());
        holder.binding.eachCartItemQuantityTV.setText(shoeCart.getQuantity() + "");
        holder.binding.eachCartItemPriceTv.setText(shoeCart.getTotalItemPrice() + "");

        holder.binding.eachCartItemAddQuantityBtn.setOnClickListener(v -> cartClickedListeners.onPlusClicked(shoeCart));
        holder.binding.eachCartItemMinusQuantityBtn.setOnClickListener(v -> cartClickedListeners.onMinusClicked(shoeCart));
        holder.binding.eachCartItemDeleteBtn.setOnClickListener(v -> cartClickedListeners.onDeleteClicked(shoeCart));
    }

    @Override
    public int getItemCount() {
        if (shoeCartList == null) {
            return 0;
        }
        return shoeCartList.size();
    }

    public class holder extends RecyclerView.ViewHolder {
        EachCartItemBinding binding;

        public holder(@NonNull View itemView) {
            super(itemView);
            binding = EachCartItemBinding.bind(itemView);
        }
    }

    public interface CartClickedListeners{
        void onDeleteClicked(ShoeCart shoeCart);
        void onPlusClicked(ShoeCart shoeCart);
        void onMinusClicked(ShoeCart shoeCart);
    }
}
