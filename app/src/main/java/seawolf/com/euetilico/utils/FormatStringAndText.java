package seawolf.com.euetilico.utils;

import android.util.TypedValue;
import android.widget.TextView;

import java.text.DecimalFormat;

/**
 * Created by Miguel on 22/11/2015.
 */
public class FormatStringAndText {

    public static String getStringPrice(Double price){
        String priceString = Double.toString(price);
        if (price % 1 == 0){
            return Integer.toString(price.intValue()).concat(".00");
        }
        if ((price*10) % 1 == 0){

        //    return priceString.concat("0");
            return String.format("%.2f", price);
        }
        return String.format("%.2f", price);
    }

    public static void setPriceTextViewSize(Double price, TextView textView){
        if(price >= 10){
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 56);
        }
        if(price >= 100){
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 32);
        }
    }

    public static boolean isNumeric(String str)
    {
        try
        {
            double d = Double.parseDouble(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }




}
