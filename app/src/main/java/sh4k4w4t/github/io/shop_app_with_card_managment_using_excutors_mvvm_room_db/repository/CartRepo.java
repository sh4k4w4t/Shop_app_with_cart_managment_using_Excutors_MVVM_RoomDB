package sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.dao.CartDao;
import sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.database.CartDatabase;
import sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.utils.model.ShoeCart;

public class CartRepo {
    private CartDao cartDao;
    private LiveData<List<ShoeCart>> allCartItemLiveData;
    private Executor executor= Executors.newSingleThreadExecutor();

    public LiveData<List<ShoeCart>> getAllCartItemLiveData() {
        return allCartItemLiveData;
    }

    public CartRepo(Application application) {
        cartDao = CartDatabase.getInstance(application).cartDao();
        allCartItemLiveData= cartDao.getAllCartItems();
    }

    public void insertCartItem(ShoeCart shoeCart){
        executor.execute(() -> cartDao.insertCartItem(shoeCart));
    }

    public void deleteCartItem(ShoeCart shoeCart){
        executor.execute(() -> cartDao.deleteCartItem(shoeCart));
    }

    public void updateQuantity(int id, int quantity){
        executor.execute(() -> cartDao.updateQuantity(id,quantity));
    }

    public void updatePrice(int id, double price){
        executor.execute(() -> cartDao.updatePrice(id,price));
    }

    public void deleteAllCartItems(){
        executor.execute(() -> cartDao.deleteAllItems());
    }
}
