package seawolf.com.euetilico.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import seawolf.com.euetilico.R;
import seawolf.com.euetilico.models.Constants;
import seawolf.com.euetilico.models.ConsumableInfo;
import seawolf.com.euetilico.models.Product;
import seawolf.com.euetilico.ui.BotequimActivity;
import seawolf.com.euetilico.utils.FormatStringAndText;

/**
 * Created by Miguel on 05/10/2015.
 */
public class ConsumableAdapter extends RecyclerView.Adapter<ConsumableAdapter.ConsumableViewHolder> {
    private Context mContext;
    private List<ConsumableInfo> mConsumableList;
    private HashMap<String, String> mProductsHashMap = Constants.PRODUCT_HASHMAP_CONSTANTS;
    private double mTotalPrice = 0;
    private boolean isAutoCompleteClicked = false;
    int mPersonsConsumed = 1, mPersonsConsumedBuffer = 1, mConsumableUnits = 1, mConsumableUnitsBuffer = 1;
    double mProductPriceBeforeChange = 0, mProductPriceAfterChange = 0;
    private Product mProduct;
    private String mPriceBuffer;
    int posicaoGlobal = 0;
    private Double mPercent = 1.0;

    public ConsumableAdapter(Context context, List<ConsumableInfo> contactList) {
        mContext = context;
        mConsumableList = contactList;
    }

    public String getTotalPriceString() {
        return FormatStringAndText.getStringPrice(mTotalPrice);
    }

    public double getTotalPrice() {
        return mTotalPrice;
    }


    @Override
    public ConsumableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.consumable_item, parent, false);
        ConsumableViewHolder viewHolder = new ConsumableViewHolder(view);
        clearProductPrices();
        return viewHolder;

    }

    private void clearProductPrices() {
        mProductPriceBeforeChange = 0;
        mProductPriceAfterChange = 0;
    }

    @Override
    public void onBindViewHolder(ConsumableViewHolder holder, int position) {
        holder.bindData(mConsumableList.get(position), holder, mContext);
    }

    @Override
    public int getItemCount() {
        return mConsumableList.size();
    }

    public class ConsumableViewHolder extends RecyclerView.ViewHolder {
        public AutoCompleteTextView mAutoCompleteTextView;
        public Spinner mDivideConsumableSpinner;
        public Spinner mUnitsConsumableSpinner;
        public EditText mConsumablePriceTextView;

        public ConsumableViewHolder(View itemView) {
            super(itemView);

            mAutoCompleteTextView = (AutoCompleteTextView) itemView.findViewById(R.id.ConsumableTextView);
            mConsumablePriceTextView = (EditText) itemView.findViewById(R.id.priceTextView);
            mDivideConsumableSpinner = (Spinner) itemView.findViewById(R.id.DivideConsumableSpinner);
            mUnitsConsumableSpinner = (Spinner) itemView.findViewById(R.id.unitsSpinner);
        }


        public void bindData(ConsumableInfo consumableInfo, ConsumableViewHolder holder, final Context context) {

            final int posicao = Integer.parseInt(consumableInfo.getName());
            posicaoGlobal = posicao;


            //OBSERVAÇÃO IMPORANTE:
            // ao retirar o comentario a linha a seguir, os nomes ao inicializar os cards  serão 1, 2, 3, 4...
            // esse caso é importante, pois nele não há o bug ao deletar os cards!
            //adapter para carregar produtos no AutoComplete do mAutoCompleteTextView
            ArrayAdapter adapter = new ArrayAdapter(mContext, android.R.layout.select_dialog_item,
                    Constants.CONSUMABLE_CONSTANTS);
            holder.mAutoCompleteTextView.setAdapter(adapter);


            holder.mAutoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {

                    isAutoCompleteClicked = true;

                    mPersonsConsumed = mDivideConsumableSpinner.getSelectedItemPosition();
                    if (mDivideConsumableSpinner.getSelectedItemPosition() == 0) {
                        mPersonsConsumed = 1;
                    }
                    mConsumableUnits = mUnitsConsumableSpinner.getSelectedItemPosition();
                    if (mUnitsConsumableSpinner.getSelectedItemPosition() == 0) {
                        mConsumableUnits = 1;
                    }
                    if (mAutoCompleteTextView.getText().toString().length() > 16) {
                        mAutoCompleteTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 16);
                    } else {
                        mAutoCompleteTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
                    }
                    mPriceBuffer = mConsumablePriceTextView.getText().toString();

                    if (mProductsHashMap.containsKey(parent.getItemAtPosition(position).toString())) {
                        if (mPriceBuffer.length() != 0) {
                            mTotalPrice = mTotalPrice - mConsumableUnits * (Double.parseDouble(mPriceBuffer)) / mPersonsConsumed;
                        }
                        mConsumablePriceTextView.setText(mProductsHashMap.get(parent.getItemAtPosition(position).toString()));
                        updateTotalPrice(posicao);

                    }
                }
            });

            holder.mConsumablePriceTextView.getOnFocusChangeListener();




//            PriceTextWatcher mPriceTextWatcher = new PriceTextWatcher(posicao);
 //           holder.mConsumablePriceTextView.addTextChangedListener(mPriceTextWatcher);

            holder.mConsumablePriceTextView.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    if (s.toString().length() == 0) {
                        updateTotalPrice2(posicao, "0.00");
                    } else {
                        if (!isAutoCompleteClicked) {
                            if (FormatStringAndText.isNumeric(s.toString())) {
                                mProductPriceAfterChange = Double.parseDouble(s.toString());
                                mProductPriceBeforeChange = mProductPriceAfterChange;
                                mConsumableUnits = BotequimActivity.mProductList.get(posicao).getUnits();
                                mPersonsConsumed = BotequimActivity.mProductList.get(posicao).getDividedBy();
                                updateTotalPrice2(posicao, s.toString());
                            }
                        } else {
                            isAutoCompleteClicked = false;
                        }
                    }
                }

                @Override
                public void beforeTextChanged(CharSequence s, int start, int count,
                                              int after) {
                }

                @Override
                public void afterTextChanged(Editable s) {
                }
            });

            //com quantas pessoas dividiu
            holder.mDivideConsumableSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    mPersonsConsumed = position;
                    if (position == 0) {
                        mPersonsConsumed = 1;
                    }
                    mPersonsConsumedBuffer = mPersonsConsumed;
                    updateTotalPrice(posicao);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //unidades do produto
            holder.mUnitsConsumableSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int positionOfSpinner, long id) {
                    mConsumableUnits = positionOfSpinner;
                    if (positionOfSpinner == 0) {
                        mConsumableUnits = 1;
                    }
                    mConsumableUnitsBuffer = mConsumableUnits;
                    updateTotalPrice(posicao);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

        }

        private void updateTotalPrice(int posicao) {

            if (!FormatStringAndText.isNumeric(mConsumablePriceTextView.getText().toString())) {
                mProduct = new Product(mPriceBuffer,
                        0.0,
                        mConsumableUnits,
                        mPersonsConsumed);
            } else {
                mProduct = new Product(mPriceBuffer,
                        Double.parseDouble(mConsumablePriceTextView.getText().toString()),
                        mConsumableUnits,
                        mPersonsConsumed);
            }
            BotequimActivity.mProductList.set(posicao, mProduct);

            mTotalPrice = getTotalPrice(BotequimActivity.mProductList, mPercent);
            BotequimActivity.mTotalPriceTextView.setText(getTotalPriceString());
            FormatStringAndText.setPriceTextViewSize(mTotalPrice, BotequimActivity.mTotalPriceTextView);
        }



            }

    private void updateTotalPrice2(int posicao, String priceString) {

        if ((!FormatStringAndText.isNumeric(priceString)) || priceString.equals("") || priceString.equals(".")) {
            mProduct = new Product(mPriceBuffer,
                    0.0,
                    mConsumableUnits,
                    mPersonsConsumed);
        } else {
            mProduct = new Product(mPriceBuffer,
                    Double.parseDouble(priceString),
                    mConsumableUnits,
                    mPersonsConsumed);
        }

        BotequimActivity.mProductList.set(posicao, mProduct);

        mTotalPrice = getTotalPrice(BotequimActivity.mProductList, mPercent);
        BotequimActivity.mTotalPriceTextView.setText(getTotalPriceString());
        FormatStringAndText.setPriceTextViewSize(mTotalPrice, BotequimActivity.mTotalPriceTextView);
    }

    public void updateTotalPrice3() {

        mTotalPrice = getTotalPrice(BotequimActivity.mProductList, mPercent);
        BotequimActivity.mTotalPriceTextView.setText(getTotalPriceString());
        FormatStringAndText.setPriceTextViewSize(mTotalPrice, BotequimActivity.mTotalPriceTextView);
    }







    public Double getTotalPrice(ArrayList<Product> productList, Double percent) {
        mTotalPrice = 0;
        for (Product product : productList) {
            mTotalPrice = mTotalPrice + percent * (product.getUnits() * (product.getDoublePrice()) / product.getDividedBy());
        }
        return mTotalPrice;
    }

    public void set10Percent (boolean percentSetter){
        if (percentSetter){
            mPercent = 1.1;
        } else {
            mPercent = 1.0;
        }
        mTotalPrice = getTotalPrice(BotequimActivity.mProductList, mPercent);
        BotequimActivity.mTotalPriceTextView.setText(FormatStringAndText.getStringPrice(mTotalPrice));
        FormatStringAndText.setPriceTextViewSize(mTotalPrice, BotequimActivity.mTotalPriceTextView);
    }

    public void clearCard(){
        mPriceBuffer = "";
        mPersonsConsumed = 1;
        mConsumableUnits = 1;
        mProductPriceAfterChange = 0.0;
    }

}
