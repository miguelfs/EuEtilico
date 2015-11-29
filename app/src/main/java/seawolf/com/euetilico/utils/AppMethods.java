package seawolf.com.euetilico.utils;

import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

/**
 * Created by Miguel on 26/11/2015.
 */
public class AppMethods {

    public static void closeKeyboard(View view, Object systemService) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) systemService;
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }
/*
    private static double updateTotalPrice(int posicao,String priceName, Double priceValue,
                                    int units, int dividedBy, BotequimActivity activity, TextView textView) {

        if (!FormatStringAndText.isNumeric(textView.getText().toString())) {
            Product mProduct = new Product(priceName,
                    0.0,
                    units,
                    dividedBy);
            activity.mProductList.set(posicao, mProduct);
        } else {
            Product mProduct = new Product(priceName,
                    priceValue,
                    units,
                    dividedBy);
            activity.mProductList.set(posicao, mProduct);
        }
        return getTotalPrice(BotequimActivity.mProductList, mPercent);
    }*/

}
