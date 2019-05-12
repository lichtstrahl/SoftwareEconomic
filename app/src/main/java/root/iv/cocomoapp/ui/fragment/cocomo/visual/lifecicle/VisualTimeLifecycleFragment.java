package root.iv.cocomoapp.ui.fragment.cocomo.visual.lifecicle;

import android.os.Bundle;

import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.LinkedList;
import java.util.List;

import root.iv.cocomoapp.cocomo.ProjectParam;
import root.iv.cocomoapp.ui.fragment.cocomo.visual.BaseVisualFragment;

public class VisualTimeLifecycleFragment extends BaseVisualFragment {
    public static VisualTimeLifecycleFragment getInstance(double m, double t) {
        VisualTimeLifecycleFragment fragment = new VisualTimeLifecycleFragment();
        Bundle bundle = new Bundle();
        bundle.putDouble(ARG_MANS, m);
        bundle.putDouble(ARG_TIME, t);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void fillContent() {
        List<ProjectParam> params = new LinkedList<>();

        params.add(ProjectParam.getInstance("Планирование и определение требований (36%)", 0.36*time));
        params.add(ProjectParam.getInstance("Проектирование продукта (36%)", 0.36 * time));
        params.add(ProjectParam.getInstance("Детальное проектирование (18%)", 0.18 * time));
        params.add(ProjectParam.getInstance("Кодирование и тестирование отдельных модуей (18%)", 0.18 * time));
        params.add(ProjectParam.getInstance("Интеграция и тестирование (28%)", 0.28 * time));

        adapter.append(params);

        List<PieEntry> entrys = new LinkedList<>();
        for (ProjectParam f : params) {
            entrys.add(new PieEntry((float)f.getValue(), f.getName()));
        }

        PieDataSet dataSet = new PieDataSet(entrys, "Декомпозиция времени по стадиям ЖЦ");


        PieData data = new PieData(dataSet);
        diagram.setData(data);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        diagram.animateXY(DURATION_ANIM, DURATION_ANIM);
        Description desc = new Description();
        desc.setText("Распределение времени по ЖЦ");
        diagram.setDescription(desc);
    }

    @Override
    public String getName() {
        return "TIME LIFECYCLE";
    }
}
