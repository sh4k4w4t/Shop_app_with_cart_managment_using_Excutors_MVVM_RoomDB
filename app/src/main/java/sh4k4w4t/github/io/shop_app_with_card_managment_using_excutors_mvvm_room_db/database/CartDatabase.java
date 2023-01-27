package sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.dao.CartDao;
import sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.utils.model.ShoeCart;

@Database(entities = {ShoeCart.class}, version = 1, exportSchema = false)
public abstract class CartDatabase extends RoomDatabase {
    public abstract CartDao cartDao();

    public static CartDatabase instance;

    public static synchronized CartDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            CartDatabase.class, "ShoeCartDatabase")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}
