package root.iv.cocomoapp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.ButterKnife;
import root.iv.cocomoapp.R;

public class CocomoVisualFragment extends Fragment {
    private static final String ARG_MANS = "args:mans";
    private static final String ARG_TIME = "args:time";
    private double mans;
    private double time;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_visual_cocomo, container, false);
        ButterKnife.bind(this, view);

        Bundle args = getArguments();
        mans = args.getDouble(ARG_MANS);
        time = args.getDouble(ARG_TIME);

        return view;
    }

    public static CocomoVisualFragment getInstance(double mans, double time) {
        CocomoVisualFragment fragment = new CocomoVisualFragment();
        Bundle bundle = new Bundle();
        bundle.putDouble(ARG_MANS, mans);
        bundle.putDouble(ARG_TIME, time);
        fragment.setArguments(bundle);

        return fragment;
    }

    public double[] getFinance() {
        double[] finance = new double[8];
        finance[0] = 0.04 * mans;
        finance[1] = 0.12 * mans;
        finance[2] = 0.44 * mans;
        finance[3] = 0.06 * mans;
        finance[4] = 0.14 * mans;
        finance[5] = 0.07 * mans;
        finance[6] = 0.07 * mans;
        finance[7] = 0.06 * mans;
        return finance;
    }
}
