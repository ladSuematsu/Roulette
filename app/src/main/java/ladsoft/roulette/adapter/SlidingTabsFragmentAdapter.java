package ladsoft.roulette.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ladsoft.roulette.fragment.Tab1;
import ladsoft.roulette.fragment.Tab2;

/**
 * Created by suematsu on 3/10/15.
 */
public class SlidingTabsFragmentAdapter extends FragmentPagerAdapter {

    private Context mContext;
    private final String[] tabTitles = new String[]{"Tab #1", "Tab #2"};

    public SlidingTabsFragmentAdapter(FragmentManager fm, Context context){
        super(fm);
        this.mContext = context;
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
        return tabTitles[position];
    }
}
