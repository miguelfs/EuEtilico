package seawolf.com.euetilico;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import java.util.List;

/**
 * Created by Miguel on 05/10/2015.
 */
public class ConsumableAdapter extends RecyclerView.Adapter<ConsumableAdapter.ConsumableViewHolder>{
    private Context mContext;
    private List<ConsumableInfo> mConsumableList;

    public ConsumableAdapter(Context context, List<ConsumableInfo> contactList){
        mContext = context;
        mConsumableList = contactList;
    }



    @Override
    public ConsumableViewHolder onCreateViewHolder (ViewGroup parent, int viewType)  {
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

        public ConsumableViewHolder(View itemView) {
            super(itemView);

            mAutoCompleteTextView = (AutoCompleteTextView) itemView.findViewById(R.id.autoCompleteTextView);
        }

        public void bindData(ConsumableInfo consumableInfo, ConsumableViewHolder holder, Context context) {

            //OBSERVAÇÃO IMPORANTE:
            // ao comentar a linha a seguir, os nomes ao inicializar os cards não serão 1, 2, 3, 4...
            //nesse caso, aparece a Hint "Comida/Bebida"
            mAutoCompleteTextView.setText(consumableInfo.getName());
            String[] constants = Constants.CONSUMABLE_CONSTANTS;

            //adapter para carregar produtos no AutoComplete do mAutoCompleteTextView
               ArrayAdapter adapter = new ArrayAdapter(mContext, android.R.layout.select_dialog_item, constants);
                holder.mAutoCompleteTextView.setThreshold(3);
               holder.mAutoCompleteTextView.setAdapter(adapter);
        }

    }
}
