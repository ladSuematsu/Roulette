package ladsoft.roulette.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ladsoft.roulette.R;
import ladsoft.roulette.entity.PlaceHistory;
import ladsoft.roulette.presenter.ITab1Presenter;
import ladsoft.roulette.presenter.Tab1Presenter;
import ladsoft.roulette.view.ITab1View;

/**
 * Fragment for main tab.
 */
public class Tab1 extends Fragment implements ITab1View{
    private TextView mTxtViewResult;
    private Resources mResources;

    private ITab1Presenter mPresenter;

    public Tab1() {
        // Required empty public constructor.
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initializes variables.
        mResources = getResources();
        mPresenter = new Tab1Presenter(this, mResources);
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
                mPresenter.onItemClick();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    /**
     * Shows result dialog.
     * @param bundle = bundle.
     */
    @Override
    public void showResultDialog(Bundle bundle) {
        FragmentManager fragmentManager = getChildFragmentManager();
        ResultDialog resultDialog = new ResultDialog();
        resultDialog.setArguments(bundle);
        resultDialog.show(fragmentManager, "show");
    }

}
