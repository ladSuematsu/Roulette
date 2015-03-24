package ladsoft.roulette.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import ladsoft.roulette.R;
import ladsoft.roulette.adapter.PlaceHistoryListAdapter;
import ladsoft.roulette.adapter.SlidingTabsFragmentAdapter;
import ladsoft.roulette.entity.PlaceHistory;
import ladsoft.roulette.fragment.ResultDialog;
import view.SlidingTabLayout;

//TODO: Make app work with PlaceHistory entity class
public class MainActivity
    extends ActionBarActivity
    implements ResultDialog.OnFragmentInteractionListener{
    @Override
    public void onFragmentInteraction(Bundle bundle) {
       // mArrayHistory.add((PlaceHistory) bundle.getSerializable(ResultDialog.ARG_PARAM_PLACEHISTORY));
//        mTxtViewResult.setText(bundle.getString(ResultDialog.ARG_PARAM_PLACE));
//        mListAdapter.notifyDataSetChanged();
        View mainTabView = mViewPager.getChildAt(0);
        View historyTabView = mViewPager.getChildAt(1);
        ListView historyListView = (ListView) historyTabView.findViewById(R.id.fragment_history_listview);
        PlaceHistoryListAdapter historyListAdapter = (PlaceHistoryListAdapter) historyListView.getAdapter();

        PlaceHistory placeHistory = (PlaceHistory) bundle.getSerializable(ResultDialog.ARG_PARAM_PLACEHISTORY);

        List<PlaceHistory> listPlaceHistory = historyListAdapter.getAllItems();
        listPlaceHistory.add(placeHistory);

        TextView resultText = (TextView) mainTabView.findViewById(R.id.tab1_txtview_result);
        resultText.setText(placeHistory.getPlace());

        historyListAdapter.notifyDataSetChanged();
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
