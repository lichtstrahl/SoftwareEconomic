package root.iv.cocomoapp.ui.fragment.cocomo.visual;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import root.iv.cocomoapp.R;
import root.iv.cocomoapp.ui.adapter.ProjectParamAdapter;

public abstract class BaseVisualFragment extends Fragment {
    protected static final String ARG_MANS = "args:mans";
    protected static final String ARG_TIME = "args:time";
    protected static final int DURATION_ANIM = 1500;
    protected double mans;
    protected double time;
    @Nullable
    @BindView(R.id.listParams)
    protected RecyclerView listParams;
    @Nullable
    @BindView(R.id.diagram)
    protected PieChart diagram;
    protected ProjectParamAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_visual_work_lifecycle, container, false);
        ButterKnife.bind(this, view);

        Bundle args = getArguments();
        mans = args.getDouble(ARG_MANS);
        time = args.getDouble(ARG_TIME);

        adapter = new ProjectParamAdapter(getLayoutInflater());
        listParams.setAdapter(adapter);
        listParams.setLayoutManager(new LinearLayoutManager(this.getContext(), RecyclerView.HORIZONTAL, false));
        fillContent();
        return view;
    }

    abstract public void fillContent();
    abstract public String getName();
}
