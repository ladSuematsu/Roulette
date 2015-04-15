package ladsoft.roulette.fragment;

import android.app.Activity;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.Random;

import ladsoft.roulette.R;
import ladsoft.roulette.entity.PlaceHistory;

/**
 * Fragment for main tab.
 */
public class Tab1 extends Fragment {

    private Activity mActivity;
    private TextView mTxtViewResult;
    private Resources mResources;
    private String mRandomResult;
    private ArrayList<String> mArrayPlace;
    private ArrayList<PlaceHistory> mArrayHistory;
    private String[] mArrayWeekday;
    private PlaceHistory mPlaceHistory;

    public Tab1() {
        // Required empty public constructor.
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initializes variables.
        mResources = getResources();
        mArrayHistory = new ArrayList<PlaceHistory>();
        mArrayPlace = new ArrayList<String>(Arrays.asList(mResources.getStringArray(R.array.places)));
        mArrayWeekday = mResources.getStringArray(R.array.weekdays);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment.
        View view = inflater.inflate(R.layout.tab_fragment_1, container, false);

        mTxtViewResult = (TextView) view.findViewById(R.id.tab1_txtview_result);

        mTxtViewResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPlaceHistory = new PlaceHistory();

                mRandomResult = mArrayPlace.get(randomize() - 1);

                mPlaceHistory.setPlace(mRandomResult);
                mPlaceHistory.setDate(new Date());

                Bundle bundle = new Bundle();
                bundle.putSerializable(ResultDialog.ARG_PARAM_PLACEHISTORY , mPlaceHistory);
                bundle.putStringArray(ResultDialog.ARG_PARAM_WEEKDAYS, mArrayWeekday);
                bundle.putString(ResultDialog.ARG_PARAM_PLACE, mRandomResult);

                showResultDialog(bundle);
            }
        });

        return view;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        mActivity = activity;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * Shows result dialog.
     * @param bundle = bundle.
     */
    private void showResultDialog(Bundle bundle) {
        FragmentManager fragmentManager = getChildFragmentManager();
        ResultDialog resultDialog = new ResultDialog();
        resultDialog.setArguments(bundle);
        resultDialog.show(fragmentManager, "show");
    }

    /**
     * Returns random number in the range of the
     * place list.
     * @return
     */
    private int randomize()
    {
        int min = 1;
        int max = mArrayPlace.size();;

        Random r = new Random();
        int result = r.nextInt(max - min + 1) + min;

        return result;
    }

}
