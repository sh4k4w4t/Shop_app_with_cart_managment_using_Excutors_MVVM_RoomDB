package sh4k4w4t.github.io.shop_app_with_card_managment_using_excutors_mvvm_room_db.utils.model;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class ShoeItem implements Parcelable {

    private String shoeName, shoeBrand;
    private int shoeImage;
    private double shoePrice;

    public ShoeItem(String shoeName, String shoeBrand, int shoeImage, double shoePrice) {
        this.shoeName = shoeName;
        this.shoeBrand = shoeBrand;
        this.shoeImage = shoeImage;
        this.shoePrice = shoePrice;
    }

    protected ShoeItem(Parcel in) {
        shoeName = in.readString();
        shoeBrand = in.readString();
        shoeImage = in.readInt();
        shoePrice = in.readDouble();
    }

    public static final Creator<ShoeItem> CREATOR = new Creator<ShoeItem>() {
        @Override
        public ShoeItem createFromParcel(Parcel in) {
            return new ShoeItem(in);
        }

        @Override
        public ShoeItem[] newArray(int size) {
            return new ShoeItem[size];
        }
    };

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

    public int setShoeImage() {
        this.shoeImage = shoeImage;
        return 0;
    }

    public double getShoePrice() {
        return shoePrice;
    }

    public void setShoePrice(double shoePrice) {
        this.shoePrice = shoePrice;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(shoeName);
        dest.writeString(shoeBrand);
        dest.writeInt(shoeImage);
        dest.writeDouble(shoePrice);
    }
}
