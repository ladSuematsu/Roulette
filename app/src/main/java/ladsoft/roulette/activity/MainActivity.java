package ladsoft.roulette.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import ladsoft.roulette.R;
import ladsoft.roulette.adapter.SlidingTabsFragmentAdapter;
import ladsoft.roulette.fragment.ResultDialog;
import view.SlidingTabLayout;

//TODO: Make app work with PlaceHistory entity class
public class MainActivity extends ActionBarActivity implements ResultDialog.OnFragmentInteractionListener {
    @Override
    public void onFragmentInteraction(Bundle bundle) {
//        mArrayHistory.add((PlaceHistory) bundle.getSerializable(ResultDialog.ARG_PARAM_PLACEHISTORY));
//        mTxtViewResult.setText(bundle.getString(ResultDialog.ARG_PARAM_PLACE));
//        mListAdapter.notifyDataSetChanged();
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        if(savedInstanceState == null) {
            ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
            viewPager.setAdapter(new SlidingTabsFragmentAdapter(getSupportFragmentManager()
                                        , MainActivity.this));

            SlidingTabLayout slidingTabLayout = (SlidingTabLayout) findViewById(R.id.sliding_tabs);
            slidingTabLayout.setDistributeEvenly(true);
            slidingTabLayout.setViewPager(viewPager);
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
