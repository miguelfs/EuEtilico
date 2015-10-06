package seawolf.com.euetilico;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;
import java.util.List;

public class BotequimActivity extends AppCompatActivity{
    private static final String TAG = "BotequimActivity";
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ConsumableAdapter mConsumableAdapter;
    private List mListItems;
    private Button mAddCardButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botequim);
        mAddCardButton = (Button) findViewById(R.id.addCardButton);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

            //inicializa lista de ConsumableInfo e primeiro item
            mListItems = new ArrayList();{
            ConsumableInfo mConsumableInfo = new ConsumableInfo(0, true);
            mConsumableInfo.setName("1");
            mListItems.add(mConsumableInfo);
            }


        mConsumableAdapter = new ConsumableAdapter(this, mListItems);
        mRecyclerView.setAdapter(mConsumableAdapter);


        //metodo para adicionar funcionalidade de remover Cards ao deslizar o dedo
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
                                }
                                //OBSERVAÇÃO IMPORTANTE:
                                //ao comentar a linha a seguir, o texto dos Cards não sumirão ao deletar algum card
                                //no caso de deslizar o Card para a esquerda
                                mConsumableAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    mListItems.remove(position);
                                    mConsumableAdapter.notifyItemRemoved(position);
                                }
                                //OBSERVAÇÃO IMPORTANTE:
                                //ao comentar a linha a seguir, o texto dos Cards não sumirão ao deletar algum card
                                //no caso de deslizar o Card para a direita
                                mConsumableAdapter.notifyDataSetChanged();
                            }
                        });
        mRecyclerView.addOnItemTouchListener(swipeTouchListener);

        //método para adicionar cards ao clicar no botão
        mAddCardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConsumableInfo consumableInfo = new ConsumableInfo(0, true);
                consumableInfo.setName("" + (mListItems.size() + 1));
                mListItems.add(mListItems.size(), consumableInfo);
                mConsumableAdapter.notifyItemInserted(mListItems.size() - 1);
            }
        });
    }

}
