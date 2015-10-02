package seawolf.com.euetilico;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Miguel on 01/10/2015.
 */
public class ConsumableAdapter extends RecyclerView.Adapter<ConsumableAdapter.ConsumableViewHolder> {
    Context mContext;
    private List<ConsumableInfo> mConsumableList;

    public ConsumableAdapter(Context context, List<ConsumableInfo> contactList) {
        mContext = context;
        mConsumableList = contactList;
    }

    @Override
    public int getItemCount() {
        return mConsumableList.size();
    }


    @Override
    public void onBindViewHolder(ConsumableViewHolder consumableViewHolder, int position) {
        ConsumableInfo consumableInfo = mConsumableList.get(position);
        consumableViewHolder.mAutoCompleteTextView.setHint(R.string.autocomplete_hint);
        String[] constants = Constants.CONSUMABLE_CONSTANTS;
        ArrayAdapter adapter = new ArrayAdapter(mContext, android.R.layout.select_dialog_item, constants);
        consumableViewHolder.mAutoCompleteTextView.setThreshold(3);
        consumableViewHolder.mAutoCompleteTextView.setAdapter(adapter);


    }


    @Override
    public ConsumableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.consumable_item, parent, false);
        return new ConsumableViewHolder(itemView);
    }

    public static class ConsumableViewHolder extends RecyclerView.ViewHolder{
        protected AutoCompleteTextView mAutoCompleteTextView;

        public ConsumableViewHolder(View v) {
            super(v);
            mAutoCompleteTextView = (AutoCompleteTextView) v.findViewById(R.id.autoCompleteCardTextView);
        }


    }






}
