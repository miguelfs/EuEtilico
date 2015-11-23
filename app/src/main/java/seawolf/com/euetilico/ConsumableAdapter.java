package seawolf.com.euetilico;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Miguel on 05/10/2015.
 */
public class ConsumableAdapter extends RecyclerView.Adapter<ConsumableAdapter.ConsumableViewHolder> {
    private Context mContext;
    private List<ConsumableInfo> mConsumableList;
    private HashMap<String, String> mProductsHashMap = Constants.PRODUCT_HASHMAP_CONSTANTS;
    private double mTotalPrice = 0;

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
        return viewHolder;
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
        public EditText mConsumablePriceTextView;

        public ConsumableViewHolder(View itemView) {
            super(itemView);

            mAutoCompleteTextView = (AutoCompleteTextView) itemView.findViewById(R.id.ConsumableTextView);
            mConsumablePriceTextView = (EditText) itemView.findViewById(R.id.priceTextView);
            mDivideConsumableSpinner = (Spinner) itemView.findViewById(R.id.DivideConsumableSpinner);
        }

        public void bindData(ConsumableInfo consumableInfo, ConsumableViewHolder holder, Context context) {

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
                    if (mAutoCompleteTextView.getText().toString().length() > 11) {
                        mAutoCompleteTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 12);
                    } else {
                        mAutoCompleteTextView.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 18);
                    }
                    String mPriceBuffer = mConsumablePriceTextView.getText().toString();

                    if (mProductsHashMap.containsKey(parent.getItemAtPosition(position).toString())) {
                        if (mPriceBuffer.length() != 0) {
                            mTotalPrice = mTotalPrice - Double.parseDouble(mPriceBuffer);
                        }
                        mConsumablePriceTextView.setText(mProductsHashMap.get(parent.getItemAtPosition(position).toString()));
                        mTotalPrice = mTotalPrice + Double.parseDouble(mProductsHashMap.get(parent.getItemAtPosition(position).toString()));
                        Toast.makeText(mContext, "preço total = " + mTotalPrice, Toast.LENGTH_SHORT).show();
                        BotequimActivity.mTotalPriceTextView.setText(getTotalPriceString());
                        FormatStringAndText.setPriceTextViewSize(mTotalPrice, BotequimActivity.mTotalPriceTextView);
                    }
                }
            });


            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter
                    .createFromResource(mContext, R.array.divide_array, android.R.layout.simple_spinner_item);
            // Specify the layout to use when the list of choices appears
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
// Apply the adapter to the spinner
            holder.mDivideConsumableSpinner.setAdapter(spinnerAdapter);


        }

    }
}
