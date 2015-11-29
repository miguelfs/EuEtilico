package seawolf.com.euetilico.models;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import seawolf.com.euetilico.models.Product;
import seawolf.com.euetilico.ui.BotequimActivity;
import seawolf.com.euetilico.utils.FormatStringAndText;

/**
 * Created by Miguel on 24/11/2015.
 */
public class Comanda {

    private boolean is10Percent = false;
    private Double Percent = 1.0;
    private Double mTotalPrice = 0.0;
    private ArrayList<Product> mProductList = new ArrayList<Product>() {
        @Override
        public void add(int location, Product object) {

        }

        @Override
        public boolean add(Product object) {
            return false;
        }

        @Override
        public boolean addAll(int location, Collection<? extends Product> collection) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends Product> collection) {
            return false;
        }

        @Override
        public void clear() {

        }

        @Override
        public boolean contains(Object object) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> collection) {
            return false;
        }

        @Override
        public Product get(int location) {
            return null;
        }

        @Override
        public int indexOf(Object object) {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @NonNull
        @Override
        public Iterator<Product> iterator() {
            return null;
        }

        @Override
        public int lastIndexOf(Object object) {
            return 0;
        }

        @Override
        public ListIterator<Product> listIterator() {
            return null;
        }

        @NonNull
        @Override
        public ListIterator<Product> listIterator(int location) {
            return null;
        }

        @Override
        public Product remove(int location) {
            return null;
        }

        @Override
        public boolean remove(Object object) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> collection) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> collection) {
            return false;
        }

        @Override
        public Product set(int location, Product object) {
            return null;
        }

        @Override
        public int size() {
            return 0;
        }

        @NonNull
        @Override
        public List<Product> subList(int start, int end) {
            return null;
        }

        @NonNull
        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @NonNull
        @Override
        public <T> T[] toArray(T[] array) {
            return null;
        }
    };

    public boolean is10Percent() {
        return is10Percent;
    }

    public void setIs10Percent(boolean is10Percent) {
        this.is10Percent = is10Percent;
        if (is10Percent){
            Percent = 1.1;
        } else {
            Percent = 1.0;
        }
    }

    public List<Product> getProductList() {
        return mProductList;
    }

    public void setProductList(ArrayList<Product> productList) {
        mProductList = productList;
    }

    public Double getTotalPrice(){
        mTotalPrice = 0.0;
        for (Product product : mProductList) {
            mTotalPrice = mTotalPrice + Percent*(product.getUnits()*(product.getDoublePrice())/product.getDividedBy());
        }
        return mTotalPrice;
    }

    public Double getTotalPrice(ArrayList<Product> productList){
        for (Product product : productList) {
            mTotalPrice = mTotalPrice + Percent*(product.getUnits()*(product.getDoublePrice())/product.getDividedBy());
        }
        return mTotalPrice;
    }
    public void notifyProductRemoved(int position){
        if (mProductList!=null) {
            mProductList.remove(position);
            mTotalPrice = getTotalPrice();
        }
    }

}
