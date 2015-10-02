package seawolf.com.euetilico;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Miguel on 01/10/2015.
 */
public class ConsumableAdapter extends RecyclerView.Adapter<ConsumableAdapter.ConsumableViewHolder> {
    private List<ConsumableInfo> mConsumableList;

    public ConsumableAdapter(List<ConsumableInfo> contactList) {
        mConsumableList = contactList;
    }

    @Override
    public int getItemCount() {
        return mConsumableList.size();
    }


    @Override
    public void onBindViewHolder(ConsumableViewHolder consumableViewHolder, int position) {
        ConsumableInfo consumableInfo = mConsumableList.get(position);
        consumableViewHolder.mNameTextView.setText(consumableInfo.mName);

    }


    @Override
    public ConsumableViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.consumable_item, parent, false);
        return new ConsumableViewHolder(itemView);
    }

    public static class ConsumableViewHolder extends RecyclerView.ViewHolder{
        protected TextView mNameTextView;

        public ConsumableViewHolder(View v) {
            super(v);
            mNameTextView = (TextView) v.findViewById(R.id.title);
        }


    }






}
