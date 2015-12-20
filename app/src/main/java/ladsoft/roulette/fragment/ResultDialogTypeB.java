package ladsoft.roulette.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import ladsoft.roulette.R;

public class ResultDialogTypeB extends SwipeableBottomDialog{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(STYLE_NO_TITLE, R.style.ResultDialog);
    }

    @Override
    public View getContentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = (View) inflater.inflate(R.layout.fragment_result_simple, container);

        TextView txtContent = (TextView) rootView.findViewById(R.id.fragment_result_text);
        txtContent.setText(R.string.lorem_ipsum);

        return rootView;
    }

}
