package seawolf.com.euetilico;

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

            return priceString.concat("0");
        }
        return priceString;
    }

    public static void setPriceTextViewSize(Double price, TextView textView){
        if(price >= 10){
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 56);
        }
        if(price >= 100){
            textView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 32);
        }
    }



}
