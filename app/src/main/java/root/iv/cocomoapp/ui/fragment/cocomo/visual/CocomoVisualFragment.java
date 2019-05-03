package root.iv.cocomoapp.ui.fragment.cocomo.visual;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;
import root.iv.cocomoapp.R;

public class CocomoVisualFragment extends Fragment {
    private static final String ARG_MANS = "args:mans";
    private static final String ARG_TIME = "args:time";

    @BindView(R.id.viewPager)
    protected ViewPager viewPager;
    @BindView(R.id.tab_layout)
    protected TabLayout tabLayout;
    private CollectionVisualFragments collectionFragments;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_visual_cocomo, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        collectionFragments = new CollectionVisualFragments(getChildFragmentManager(), args.getDouble(ARG_MANS), args.getDouble(ARG_TIME));
        viewPager.setAdapter(collectionFragments);
        tabLayout.setupWithViewPager(viewPager);
    }

    public static CocomoVisualFragment getInstance(double mans, double time) {
        CocomoVisualFragment fragment = new CocomoVisualFragment();
        Bundle bundle = new Bundle();
        bundle.putDouble(ARG_MANS, mans);
        bundle.putDouble(ARG_TIME, time);
        fragment.setArguments(bundle);

        return fragment;
    }


}
