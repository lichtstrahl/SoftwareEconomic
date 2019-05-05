package root.iv.cocomoapp.ui.fragment.cocomo.visual.finance;

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

public class VisualFinanceFragment extends BaseVisualFragment {

    public static VisualFinanceFragment getInstance(double man, double time) {
        VisualFinanceFragment fragment = new VisualFinanceFragment();
        Bundle bundle = new Bundle();
        bundle.putDouble(ARG_MANS, man);
        bundle.putDouble(ARG_TIME, time);
        fragment.setArguments(bundle);

        return fragment;
    }

    @Override
    public void fillContent() {
        List<ProjectParam> fin = new LinkedList<>();

        fin.add(ProjectParam.getInstance("Анализ требований (4%)", 0.04 * mans));
        fin.add(ProjectParam.getInstance("Проектирование продукта (12%)", 0.12 * mans));
        fin.add(ProjectParam.getInstance("Программирование (44%)", 0.44*mans));
        fin.add(ProjectParam.getInstance("Тестирование (6%)", 0.06*mans));
        fin.add(ProjectParam.getInstance("Верификация и аттестация (14%)", 0.14*mans));
        fin.add(ProjectParam.getInstance("Канцелярий проекта (7%)", 0.07*mans));
        fin.add(ProjectParam.getInstance("Управление конфигурацией и обсечение качества (7%)", 0.07*mans));
        fin.add(ProjectParam.getInstance("Создание руководств (6%)", 0.06*mans));

        // Загружаем данные в адаптер
        adapter.append(fin);


        List<PieEntry> entrys = new LinkedList<>();
        for (ProjectParam f : fin) {
            entrys.add(new PieEntry((float)f.getValue(), f.getName()));
        }

        PieDataSet dataSet = new PieDataSet(entrys, "Декомпозиция работ по созданию ПО");


        PieData data = new PieData(dataSet);

        diagram.setData(data);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        diagram.animateXY(DURATION_ANIM, DURATION_ANIM);
        Description desc = new Description();
        desc.setText("Распределение ресурсов на разработку проекта");
        diagram.setDescription(desc);
    }

    @Override
    public String getName() {
        return "FINANCE";
    }
}
