package sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.utils.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "shoe_table")
public class ShoeCart {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String shoeName, shoeBrand;
    private int shoeImage;
    private double shoePrice;
    private int quantity;
    private double totalItemPrice;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getTotalItemPrice() {
        return totalItemPrice;
    }

    public void setTotalItemPrice(double totalItemPrice) {
        this.totalItemPrice = totalItemPrice;
    }

    public String getShoeName() {
        return shoeName;
    }

    public void setShoeName(String shoeName) {
        this.shoeName = shoeName;
    }

    public String getShoeBrand() {
        return shoeBrand;
    }

    public void setShoeBrand(String shoeBrand) {
        this.shoeBrand = shoeBrand;
    }

    public int getShoeImage() {
        return shoeImage;
    }

    public void setShoeImage(int shoeImage) {
        this.shoeImage = shoeImage;
    }

    public double getShoePrice() {
        return shoePrice;
    }

    public void setShoePrice(double shoePrice) {
        this.shoePrice = shoePrice;
    }
}
