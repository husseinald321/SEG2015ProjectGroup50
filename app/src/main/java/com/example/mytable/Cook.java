package com.example.mytable;

import android.provider.Telephony;

public class Cook extends User{
    String voidChequeFileName, cookDescription;
    int mealsSold, cookRating;
    Address cookAddress;

    public String getVoidChequeFileName() {
        return voidChequeFileName;
    }

    public void setVoidChequeFileName(String voidChequeFileName) {
        this.voidChequeFileName = voidChequeFileName;
    }

    public String getCookDescription() {
        return cookDescription;
    }

    public void setCookDescription(String cookDescription) {
        this.cookDescription = cookDescription;
    }

    public int getMealsSold() {
        return mealsSold;
    }

    public void setMealsSold(int mealsSold) {
        this.mealsSold = mealsSold;
    }

    public int getCookRating() {
        return cookRating;
    }

    public void setCookRating(int cookRating) {
        this.cookRating = cookRating;
    }

    public Address getCookAddress() {
        return cookAddress;
    }

    public void setCookAddress(Address cookAddress) {
        this.cookAddress = cookAddress;
    }
}
