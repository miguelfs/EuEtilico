package seawolf.com.euetilico.models;

import seawolf.com.euetilico.utils.FormatStringAndText;

/**
 * Created by Miguel on 24/11/2015.
 */
public class Product {
    private String mName;
    private Double mDoublePrice;
    private String mDoubleString;
    private int mUnits;
    private int mDividedBy;

    public Product() {
        mName = "";
        mDoublePrice = 0.00;
        mDoubleString = "0.00";
        mUnits = 1;
        mDividedBy = 1;
    }

    public Product(String name, Double price, int units, int dividedBy){


        mName = name;
        mDoublePrice = price;
        mDoubleString = FormatStringAndText.getStringPrice(price);
        mUnits = units;
        mDividedBy = dividedBy;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public Double getDoublePrice() {
        return mDoublePrice;
    }

    public void setDoublePrice(Double doublePrice) {
        mDoublePrice = doublePrice;
    }

    public String getDoubleString() {
        return mDoubleString;
    }

    public void setDoubleString(String doubleString) {
        mDoubleString = doubleString;
    }

    public int getUnits() {
        return mUnits;
    }

    public void setUnits(int units) {
        mUnits = units;
    }

    public int getDividedBy() {
        return mDividedBy;
    }

    public void setDividedBy(int dividedBy) {
        mDividedBy = dividedBy;
    }
}
