package ladsoft.roulette.presenter;

import android.content.res.Resources;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

import ladsoft.roulette.R;
import ladsoft.roulette.entity.PlaceHistory;
import ladsoft.roulette.fragment.ResultDialog;
import ladsoft.roulette.view.ITab1View;

/**
 * Tab1 MVP presenter interface implementation
 */
public class Tab1Presenter implements ITab1Presenter {
    private List<String> mArrayPlace;
    private List<PlaceHistory> mArrayHistory;
    private String[] mArrayWeekday;
    private Resources mResources;
    private PlaceHistory mPlaceHistory;
    private String mRandomResult;

    private ITab1View mTab1View;


    /**
     * Constructor
     * @param tab1View MVP view.
     * @param resources Resources instance.
     */
    public Tab1Presenter(ITab1View tab1View, Resources resources){
        mResources = resources;
        mArrayPlace = new ArrayList<String>(Arrays.asList(mResources.getStringArray(R.array.places)));
        mArrayWeekday = mResources.getStringArray(R.array.weekdays);
        mTab1View = tab1View;
    }


    /**
     * List item click handling.
     */
    @Override
    public void onItemClick() {
        mPlaceHistory = new PlaceHistory();

        mRandomResult = mArrayPlace.get(randomize() - 1);

        mPlaceHistory.setPlace(mRandomResult);
        mPlaceHistory.setDate(new Date());

        Bundle bundle = new Bundle();
        bundle.putSerializable(ResultDialog.ARG_PARAM_PLACEHISTORY, mPlaceHistory);
        bundle.putStringArray(ResultDialog.ARG_PARAM_WEEKDAYS, mArrayWeekday);
        bundle.putString(ResultDialog.ARG_PARAM_PLACE, mRandomResult);

        mTab1View.showResultDialog(bundle);
    }

    /**
     * Returns random number in the range of the
     * place list.
     * @return
     */
    private int randomize()
    {
        int min = 1;
        int max = mArrayPlace.size();

        Random r = new Random();
        int result = r.nextInt(max - min + 1) + min;

        return result;
    }
}
