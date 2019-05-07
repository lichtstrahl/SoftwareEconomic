package root.iv.cocomoapp.ui.fragment.cocomo.visual.finance;

import android.os.Bundle;

import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import root.iv.cocomoapp.app.App;
import root.iv.cocomoapp.cocomo.ProjectParam;
import root.iv.cocomoapp.ui.fragment.cocomo.visual.BaseVisualFragment;

public class VisualFinanceFragment extends BaseVisualFragment {
    private static final double COST_MAN = 30000;   // Ставка одного сотрудника

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

        double sum = 0;
        for (int i = 0; i < paramMans.size(); i++) {
            double m = paramMans.get(i).getValue();
            double t = paramTime.get(i).getValue();
            // ЧМ = Ч*М
            // Ч = ЧM / М
            double mans = Math.round(m/t); // Количество человек
            sum += mans*COST_MAN;
        }
        App.logI(String.format(Locale.ENGLISH, "Суммарные расходы: %5.2f", sum));

        List<ProjectParam> fin = new LinkedList<>();

        fin.add(ProjectParam.getInstance("Анализ требований (4%)", 0.04 * sum));
        fin.add(ProjectParam.getInstance("Проектирование продукта (12%)", 0.12 * sum));
        fin.add(ProjectParam.getInstance("Программирование (44%)", 0.44*sum));
        fin.add(ProjectParam.getInstance("Тестирование (6%)", 0.06*sum));
        fin.add(ProjectParam.getInstance("Верификация и аттестация (14%)", 0.14*sum));
        fin.add(ProjectParam.getInstance("Канцелярий проекта (7%)", 0.07*sum));
        fin.add(ProjectParam.getInstance("Управление конфигурацией и обсечение качества (7%)", 0.07*sum));
        fin.add(ProjectParam.getInstance("Создание руководств (6%)", 0.06*sum));

        // Загружаем данные в адаптер
        adapter.append(fin);


        List<PieEntry> entrys = new LinkedList<>();
        for (ProjectParam f : fin) {
            entrys.add(new PieEntry((float)f.getValue(), f.getName()));
        }

        PieDataSet dataSet = new PieDataSet(entrys, "Декомпозиция работ по созданию ПО. Бюджет.");


        PieData data = new PieData(dataSet);

        diagram.setData(data);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        diagram.animateXY(DURATION_ANIM, DURATION_ANIM);
        Description desc = new Description();
        desc.setText("Декомпозиция работ по созданию ПО. Бюджет.");
        diagram.setDescription(desc);
    }

    @Override
    public String getName() {
        return "FINANCE";
    }
}
