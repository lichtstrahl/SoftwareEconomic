package root.iv.cocomoapp.ui.fragment.cocomo.visual.mans;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.LinkedList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import root.iv.cocomoapp.R;
import root.iv.cocomoapp.cocomo.ProjectParam;
import root.iv.cocomoapp.ui.fragment.cocomo.visual.BaseVisualFragment;

public class VisualMansFragment extends BaseVisualFragment {
    @BindView(R.id.barDiagram)
    protected BarChart barChart;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_visual_mans, container,false);
        ButterKnife.bind(this, view);

        Bundle bundle = getArguments();
        mans = bundle.getDouble(ARG_MANS);
        time = bundle.getDouble(ARG_TIME);

        fillContent();
        return view;
    }

    public static VisualMansFragment getInstance(double m, double t) {
        VisualMansFragment fragment = new VisualMansFragment();
        Bundle bundle = new Bundle();
        bundle.putDouble(ARG_MANS, m);
        bundle.putDouble(ARG_TIME, t);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void fillContent() {
        List<ProjectParam> paramMans = new LinkedList<>();
        paramMans.add(ProjectParam.getInstance("Планирование и определение требований (8%)", 0.08*mans));
        paramMans.add(ProjectParam.getInstance("Проектирование продукта (18%)", 0.18 * mans));
        paramMans.add(ProjectParam.getInstance("Детальное проектирование (25%)", 0.25 * mans));
        paramMans.add(ProjectParam.getInstance("Кодирование и тестирование отдельных модуей (26%)", 0.26 * mans));
        paramMans.add(ProjectParam.getInstance("Интеграция и тестирование (31%)", 0.31 * mans));
        List<ProjectParam> paramTime = new LinkedList<>();
        paramTime.add(ProjectParam.getInstance("Планирование и определение требований (36%)", 0.36*time));
        paramTime.add(ProjectParam.getInstance("Проектирование продукта (36%)", 0.36 * time));
        paramTime.add(ProjectParam.getInstance("Детальное проектирование (18%)", 0.18 * time));
        paramTime.add(ProjectParam.getInstance("Кодирование и тестирование отдельных модуей (18%)", 0.18 * time));
        paramTime.add(ProjectParam.getInstance("Интеграция и тестирование (28%)", 0.28 * time));




        List<BarEntry> entrys = new LinkedList<>();
        for (int i = 0; i < paramMans.size(); i++) {
            double m = paramMans.get(i).getValue();
            double t = paramTime.get(i).getValue();
            entrys.add(new BarEntry(i, Math.round(m/t), "Подсказка " + i));
        }

        BarDataSet dataSet = new BarDataSet(entrys, "Декомпозиция работ по стадиям ЖЦ");

        BarData data = new BarData(dataSet);
        barChart.setData(data);
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barChart.animateY(DURATION_ANIM);
        Description desc = new Description();
        desc.setText("Привлечение сотрудников");
        barChart.setDescription(desc);
    }

    @Override
    public String getName() {
        return "MANS";
    }
}
