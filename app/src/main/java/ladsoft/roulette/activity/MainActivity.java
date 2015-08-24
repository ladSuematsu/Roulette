package ladsoft.roulette.activity;

import android.content.ContentValues;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
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

public class MainActivity
    extends ActionBarActivity
    implements ResultDialog.OnFragmentInteractionListener{

    /**
     * Interface method implementation of the ResultDialog OnFragmentInteractionListener interface.
     * @param bundle = contains selected entry.
     */
    @Override
    public void onFragmentInteraction(Bundle bundle) {
        // Get tab views.
        View mainTabView = mViewPager.getChildAt(0);
        View historyTabView = mViewPager.getChildAt(1);

        // Gets history CursorAdapter.
        ListView historyListView = (ListView) historyTabView.findViewById(R.id.fragment_history_listview);
        PlaceHistoryCursorAdapter historyListAdapter = (PlaceHistoryCursorAdapter) historyListView.getAdapter();

        // Get selected entry.
        PlaceHistory placeHistory = (PlaceHistory) bundle.getSerializable(ResultDialog.ARG_PARAM_PLACEHISTORY);

        // Insert new entry in DB.
        DatabaseManager databaseManager = new DatabaseManager(MainActivity.this);
        ContentValues values = new ContentValues();
        values.put(PlaceHistoryTable.PLACE_NAME, placeHistory.getPlace());
        values.put(PlaceHistoryTable.SORT_DATE, placeHistory.getDate().getTime());
        databaseManager.insert(RouletteContentProvider.PLACE_HISTORY_CONTENT_URI, values);

        // Refresh ListAdapter - only when not using CursorAdapter
        //historyListAdapter.notifyDataSetChanged();

        // Refresh circular button content.
        TextView resultText = (TextView) mainTabView.findViewById(R.id.tab1_txtview_result);
        resultText.setText(placeHistory.getPlace());
    }

//    private SlidingTabLayout mSlidingTabLayout;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

            // Hide action bar.
            getSupportActionBar().hide();

            // Set tabbed navigation container view.
            mViewPager = (ViewPager) findViewById(R.id.viewpager);
            mTabLayout = (TabLayout) findViewById(R.id.tablayout);

            mViewPager.setAdapter(new SlidingTabsFragmentAdapter(getSupportFragmentManager()
                                        , MainActivity.this));

            mTabLayout.setTabsFromPagerAdapter(mViewPager.getAdapter());

            mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
            mTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                @Override
                public void onTabSelected(TabLayout.Tab tab) {
                    mViewPager.setCurrentItem(tab.getPosition());
                }

                @Override
                public void onTabUnselected(TabLayout.Tab tab) {}

                @Override
                public void onTabReselected(TabLayout.Tab tab) {}
            });

            // Map ViewPager to SlidingTabLayout view.
            //mSlidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
            //mSlidingTabLayout.setDistributeEvenly(true);
        //mSlidingTabLayout.setViewPager(mViewPager);

    }
}
