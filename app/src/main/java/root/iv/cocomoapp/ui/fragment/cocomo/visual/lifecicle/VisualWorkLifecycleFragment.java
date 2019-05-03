package root.iv.cocomoapp.ui.fragment.cocomo.visual.lifecicle;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.LinkedList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import root.iv.cocomoapp.R;
import root.iv.cocomoapp.cocomo.ProjectParam;
import root.iv.cocomoapp.ui.adapter.ProjectParamAdapter;
import root.iv.cocomoapp.ui.fragment.cocomo.visual.BaseVisualFragment;

public class VisualWorkLifecycleFragment extends BaseVisualFragment {
    @BindView(R.id.listParams)
    protected RecyclerView listParams;
    @BindView(R.id.diagram)
    protected PieChart diagram;
    private ProjectParamAdapter adapter;

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

    public static VisualWorkLifecycleFragment getInstance(double m, double t) {
        VisualWorkLifecycleFragment fragment = new VisualWorkLifecycleFragment();
        Bundle bundle = new Bundle();
        bundle.putDouble(ARG_MANS, m);
        bundle.putDouble(ARG_TIME, t);
        fragment.setArguments(bundle);

        return fragment;
    }

    public void fillContent() {
        List<ProjectParam> params = new LinkedList<>();

        params.add(ProjectParam.getInstance("Планирование и определение требований (8%)", 0.08*mans));
        params.add(ProjectParam.getInstance("Проектирование продукта (18%)", 0.18 * mans));
        params.add(ProjectParam.getInstance("Детальное проектирование (25%)", 0.25 * mans));
        params.add(ProjectParam.getInstance("Кодирование и тестирование отдельных модуей (26%)", 0.26 * mans));
        params.add(ProjectParam.getInstance("Интеграция и тестирование (31%)", 0.31 * mans));

        adapter.append(params);

        List<PieEntry> entrys = new LinkedList<>();
        for (ProjectParam f : params) {
            entrys.add(new PieEntry((float)f.getValue(), f.getName()));
        }

        PieDataSet dataSet = new PieDataSet(entrys, "Декомпозиция работ по стадиям ЖЦ");


        PieData data = new PieData(dataSet);
        diagram.setData(data);
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        diagram.animateXY(5000, 5000);
        Description desc = new Description();
        desc.setText("Распределение ресурсов на разработку проекта");
        diagram.setDescription(desc);
    }
}
