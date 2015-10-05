package seawolf.com.euetilico;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class BotequimActivity extends AppCompatActivity {
    private static final String TAG = "BotequimActivity";
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ConsumableAdapter mConsumableAdapter;
    private List mListItems;
    private Button mAddCardButton;
    String[] consumableCardapio;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        consumableCardapio = Constants.CONSUMABLE_CONSTANTS;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botequim);
        mAddCardButton = (Button) findViewById(R.id.addCardButton);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
         //  mListItems = createList(11);

            mListItems = new ArrayList();{
            ConsumableInfo mConsumableInfo = new ConsumableInfo(0, true);
            mListItems.add(mConsumableInfo);
            }


        mConsumableAdapter = new ConsumableAdapter(this, mListItems);
        mRecyclerView.setAdapter(mConsumableAdapter);

//        swipeTouchListenerMethod(mRecyclerView, mListItems, mConsumableAdapter);

        SwipeableRecyclerViewTouchListener swipeTouchListener =
                new SwipeableRecyclerViewTouchListener(mRecyclerView,
                        new SwipeableRecyclerViewTouchListener.SwipeListener() {
                            @Override
                            public boolean canSwipe(int position) {
                                return true;
                            }

                            @Override
                            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    mListItems.remove(position);
                                    mConsumableAdapter.notifyItemRemoved(position);
                                    Toast.makeText(BotequimActivity.this,"posicao=" + position + "." +
                                            " tamanho de mListItems=" + mListItems.size() + "." +
                                            " tamanho do adapter=" + mConsumableAdapter.getItemCount(), Toast.LENGTH_LONG).show();
                                    Log.v(TAG, "posicao=" + position + "." +
                                            " tamanho de mListItems=" + mListItems.size() + "." +
                                            " tamanho do adapter=" + mConsumableAdapter.getItemCount());

                                }
                                mConsumableAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    mListItems.remove(position);
                                    mConsumableAdapter.notifyItemRemoved(position);
                                    Toast.makeText(BotequimActivity.this,"posicao=" + position + "." +
                                            " tamanho de mListItems=" + mListItems.size() + "." +
                                            " tamanho do adapter=" + mConsumableAdapter.getItemCount(), Toast.LENGTH_LONG).show();
                                    Log.v(TAG, "posicao=" + position + "." +
                                            " tamanho de mListItems=" + mListItems.size() + "." +
                                            " tamanho do adapter=" + mConsumableAdapter.getItemCount());

                                }
                                mConsumableAdapter.notifyDataSetChanged();
                            }
                        });

        mRecyclerView.addOnItemTouchListener(swipeTouchListener);





        mAddCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConsumableInfo mConsumableInfo = new ConsumableInfo(0, true);
                mListItems.add( mListItems.size() - 1 , mConsumableInfo);
                mConsumableAdapter.notifyItemInserted(mListItems.size() - 1);
                Toast.makeText(BotequimActivity.this,"mListItems.size() - 1 = " + (mListItems.size() - 1) + "." +
                        " tamanho de mListItems=" + mListItems.size() + "." +
                        " tamanho do adapter=" + mConsumableAdapter.getItemCount(), Toast.LENGTH_LONG).show();
                Log.v(TAG,"mListItems.size() - 1 = " + (mListItems.size() - 1) + "." +
                        " tamanho de mListItems=" + mListItems.size() + "." +
                        " tamanho do adapter=" + mConsumableAdapter.getItemCount() );
            }
        });
    }

    /* se algo der errado com esse método, pode ter haver com os parametros final
     * nesse caso, indica-se retirar o código do método e substituí-lo direto no método OnCreate() */
    void swipeTouchListenerMethod(RecyclerView recyclerView, final List listItems,
                                          final ConsumableAdapter consumableAdapter ) {
        SwipeableRecyclerViewTouchListener swipeTouchListener =
                new SwipeableRecyclerViewTouchListener(recyclerView,
                        new SwipeableRecyclerViewTouchListener.SwipeListener() {
                            @Override
                            public boolean canSwipe(int position) {
                                return true;
                            }

                            @Override
                            public void onDismissedBySwipeLeft(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    listItems.remove(position);
                                    consumableAdapter.notifyItemRemoved(position);
                                }
                                consumableAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    listItems.remove(position);
                                    consumableAdapter.notifyItemRemoved(position);
                                }
                                consumableAdapter.notifyDataSetChanged();
                            }
                        });

        mRecyclerView.addOnItemTouchListener(swipeTouchListener);

    }


/*
    private List createList(int size) {

        List result = new ArrayList();
        for (int i=1; i <= size; i++) {
            ConsumableInfo mConsumableInfo = new ConsumableInfo();
            mConsumableInfo.setBirthPositionCard(i);
            if (i == size) {
                mConsumableInfo.setIsLastItem(true);
            }
            result.add(mConsumableInfo);
        }

        return result;
    } */

  /*  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    } */
}
