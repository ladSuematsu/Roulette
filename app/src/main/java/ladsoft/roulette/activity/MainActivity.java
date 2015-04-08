package ladsoft.roulette.activity;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import ladsoft.roulette.R;
import ladsoft.roulette.adapter.PlaceHistoryCursorAdapter;
import ladsoft.roulette.adapter.SlidingTabsFragmentAdapter;
import ladsoft.roulette.contentprovider.RouletteContentProvider;
import ladsoft.roulette.database.table.PlaceHistoryTable;
import ladsoft.roulette.entity.PlaceHistory;
import ladsoft.roulette.fragment.ResultDialog;
import ladsoft.roulette.manager.DatabaseManager;
import view.SlidingTabLayout;

//TODO: Make app work with PlaceHistory entity class
public class MainActivity
    extends ActionBarActivity
    implements ResultDialog.OnFragmentInteractionListener{
    @Override
    public void onFragmentInteraction(Bundle bundle) {
        View mainTabView = mViewPager.getChildAt(0);
        View historyTabView = mViewPager.getChildAt(1);
        ListView historyListView = (ListView) historyTabView.findViewById(R.id.fragment_history_listview);
        PlaceHistoryCursorAdapter historyListAdapter = (PlaceHistoryCursorAdapter) historyListView.getAdapter();

        PlaceHistory placeHistory = (PlaceHistory) bundle.getSerializable(ResultDialog.ARG_PARAM_PLACEHISTORY);

        // Inserts new entry in DB
        DatabaseManager databaseManager = new DatabaseManager(MainActivity.this);
        ContentValues values = new ContentValues();
        values.put(PlaceHistoryTable.PLACE_NAME, placeHistory.getPlace());
        values.put(PlaceHistoryTable.SORT_DATE, placeHistory.getDate().getTime());
        databaseManager.insert(RouletteContentProvider.PLACE_HISTORY_CONTENT_URI, values);

        // Refreshes ListAdapter
        historyListAdapter.notifyDataSetChanged();

        TextView resultText = (TextView) mainTabView.findViewById(R.id.tab1_txtview_result);
        resultText.setText(placeHistory.getPlace());
    }

    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;

        @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        if(savedInstanceState == null) {
            mViewPager = (ViewPager) findViewById(R.id.viewpager);
            mViewPager.setAdapter(new SlidingTabsFragmentAdapter(getSupportFragmentManager()
                                        , MainActivity.this));


            mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
            mSlidingTabLayout.setDistributeEvenly(true);
            mSlidingTabLayout.setViewPager(mViewPager);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
    }
}
