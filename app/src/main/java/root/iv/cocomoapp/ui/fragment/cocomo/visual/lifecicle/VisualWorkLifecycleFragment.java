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

public class VisualWorkLifecycleFragment extends BaseVisualFragment {

    public static VisualWorkLifecycleFragment getInstance(double m, double t) {
        VisualWorkLifecycleFragment fragment = new VisualWorkLifecycleFragment();
        Bundle bundle = new Bundle();
        bundle.putDouble(ARG_MANS, m);
        bundle.putDouble(ARG_TIME, t);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
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
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        diagram.animateXY(DURATION_ANIM, DURATION_ANIM);
        Description desc = new Description();
        desc.setText("Распределение работ по ЖЦ");
        diagram.setDescription(desc);
    }

    @Override
    public String getName() {
        return "WORK LIFECYCLE";
    }
}
