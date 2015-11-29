package seawolf.com.euetilico.ui;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SwitchCompat;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.ArrayList;
import java.util.List;

import seawolf.com.euetilico.models.Comanda;
import seawolf.com.euetilico.adapters.ConsumableAdapter;
import seawolf.com.euetilico.R;
import seawolf.com.euetilico.models.ConsumableInfo;
import seawolf.com.euetilico.models.Product;
import seawolf.com.euetilico.models.SwipeableRecyclerViewTouchListener;
import seawolf.com.euetilico.utils.AppMethods;
import seawolf.com.euetilico.utils.FormatStringAndText;

public class BotequimActivity extends AppCompatActivity{
    private static final String TAG = "BotequimActivity";
    private RecyclerView mRecyclerView;
    private RecyclerView.LayoutManager mLayoutManager;
    private ConsumableAdapter mConsumableAdapter;
    private List mListItems;
    private Button mAddCardButton;
    public static TextView mTotalPriceTextView;
    private int nameCounter = 0;
    public static Comanda mComanda;
    public static ArrayList<Product> mProductList;
    boolean mBooleanToggle = false;
    private SwitchCompat switchService;



    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_botequim);


        switchService = (SwitchCompat) findViewById(R.id.myswitch);
        mAddCardButton = (Button) findViewById(R.id.addCardButton);
        mTotalPriceTextView = (TextView) findViewById(R.id.resultPriceText);
        mRecyclerView = (RecyclerView)findViewById(R.id.recycler_view);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());


            //inicializa lista de ConsumableInfo e primeiro item
            mListItems = new ArrayList();{
            ConsumableInfo mConsumableInfo = new ConsumableInfo(0, true);
            mConsumableInfo.setName("" + nameCounter); nameCounter = nameCounter +1;
            mListItems.add(mConsumableInfo);
            }

        //inicializa comanda
        mComanda = new Comanda();
        mProductList = new ArrayList<Product>();
        {
            Product mProduct = new Product();
            mProductList.add(mProduct);
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
                                    mComanda.setProductList(mProductList);
                                    mComanda.notifyProductRemoved(position);
                                    mConsumableAdapter.notifyItemRemoved(position);
                                    mTotalPriceTextView.setText(FormatStringAndText.getStringPrice(mComanda.getTotalPrice()));
                                    FormatStringAndText.setPriceTextViewSize(mComanda.getTotalPrice(), mTotalPriceTextView);
                                }
                                //OBSERVAÇÃO IMPORTANTE:
                                //ao comentar a linha a seguir, o texto dos Cards não sumirão ao deletar algum card
                                //no caso de deslizar o Card para a esquerda
                 //               mConsumableAdapter.notifyDataSetChanged();
                            }

                            @Override
                            public void onDismissedBySwipeRight(RecyclerView recyclerView, int[] reverseSortedPositions) {
                                for (int position : reverseSortedPositions) {
                                    mListItems.remove(position);
                                    mComanda.setProductList(mProductList);
                                    mComanda.notifyProductRemoved(position);
                                    mConsumableAdapter.notifyItemRemoved(position);
                                    mTotalPriceTextView.setText(FormatStringAndText.getStringPrice(mComanda.getTotalPrice()));
                                    FormatStringAndText.setPriceTextViewSize(mComanda.getTotalPrice(), mTotalPriceTextView);
                                }
                                //OBSERVAÇÃO IMPORTANTE:
                                //ao comentar a linha a seguir, o texto dos Cards não sumirão ao deletar algum card
                                //no caso de deslizar o Card para a direita
        //                        mConsumableAdapter.notifyDataSetChanged();
                            }
                        });
    //    mRecyclerView.addOnItemTouchListener(swipeTouchListener);

        //método para adicionar cards ao clicar no botão
        mAddCardButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                ConsumableInfo consumableInfo = new ConsumableInfo(0, true);
                if (mListItems.size() == 0){
                    consumableInfo.setName("0");
                }else {
                    consumableInfo.setName("" + mListItems.size());

                }
 //               consumableInfo.setName("" + nameCounter);
   //             nameCounter = nameCounter + 1;
                mListItems.add(mListItems.size(), consumableInfo);
                mConsumableAdapter.notifyItemInserted(mListItems.size() - 1);



                Product mProduct = new Product();
                mProductList.add(mProductList.size(), mProduct);

                mTotalPriceTextView.setText(mConsumableAdapter.getTotalPriceString());
                FormatStringAndText.setPriceTextViewSize(mConsumableAdapter.getTotalPrice(), mTotalPriceTextView);
                AppMethods.closeKeyboard(getCurrentFocus(), getSystemService(Context.INPUT_METHOD_SERVICE));
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        MenuItem item = menu.findItem(R.id.myswitch);
        item.setActionView(R.layout.switch_layout);

        // Inflate the menu; this adds items to the action bar if it is present.

        MenuItem menuItem = menu.findItem(R.id.myswitch);
        View mActionView = MenuItemCompat.getActionView(menuItem);
        final android.support.v7.widget.SwitchCompat switcher = (android.support.v7.widget.SwitchCompat) mActionView.findViewById(R.id.switchForActionBar);
        switcher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                mConsumableAdapter.set10Percent(switcher.isChecked());
            }
        });
        return super.onCreateOptionsMenu(menu);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        switch (id) {
            case R.id.myswitch:
            {
            }
            case R.id.action_extras:
            {
                Toast.makeText(this, "em Breve!", Toast.LENGTH_LONG).show();
            }
        }

        return super.onOptionsItemSelected(item);
    }


    public void notifyProductRemoved(int position){
        if (mProductList!=null) {
            mProductList.remove(position);
        }
    }


}
