package sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.repository.CartRepo;
import sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.utils.model.ShoeCart;

public class CartViewModel extends AndroidViewModel {
    private CartRepo cartRepo;

    public CartViewModel(@NonNull Application application) {
        super(application);
        cartRepo = new CartRepo(application);
    }

    public LiveData<List<ShoeCart>> getAllCartItems(){
        return cartRepo.getAllCartItemLiveData();
    }

    public void insertCartItem(ShoeCart shoeCart) {
        cartRepo.insertCartItem(shoeCart);
    }

    public void updateQuantity(int id, int quantity) {
        cartRepo.updateQuantity(id, quantity);
    }

    public void updatePrice(int id, double price) {
        cartRepo.updatePrice(id, price);
    }

    public void deleteCartItem(ShoeCart shoeCart) {
        cartRepo.deleteCartItem(shoeCart);
    }

    public void deleteAllCartItems() {
        cartRepo.deleteAllCartItems();
    }
}
