package ladsoft.roulette.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ladsoft.roulette.R;
import ladsoft.roulette.fragment.Tab1;
import ladsoft.roulette.fragment.Tab2;

/**
 * Created by suematsu on 3/10/15.
 */
public class SlidingTabsFragmentAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private String[] mTabTitles;

    public SlidingTabsFragmentAdapter(FragmentManager fm, Context context){
        super(fm);
        this.mContext = context;

        // Sets tab names.
        Resources resources = mContext.getResources();
        mTabTitles = new String[]{
                resources.getString(R.string.tab1_name),
                resources.getString(R.string.tab2_name)
        };
    }


    @Override
    public Fragment getItem(int position) {
        Fragment tabFragment = null;

        switch(position){
            case 0:
                tabFragment = new Tab1();
            break;

            case 1:
                tabFragment = new Tab2();
            break;

        }

        return tabFragment;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTabTitles[position];
    }
}
