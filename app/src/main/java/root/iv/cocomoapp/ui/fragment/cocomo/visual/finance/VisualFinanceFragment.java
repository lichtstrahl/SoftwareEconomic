package root.iv.cocomoapp.ui.fragment.cocomo.visual.finance;

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

public class VisualFinanceFragment extends BaseVisualFragment {
    @BindView(R.id.listFinance)
    protected RecyclerView listFinance;
    @BindView(R.id.diagram)
    protected PieChart pieFinanceDiagram;
    private ProjectParamAdapter financeAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_visual_finance, container, false);
        ButterKnife.bind(this, view);

        Bundle args = getArguments();
        mans = args.getDouble(ARG_MANS);
        time = args.getDouble(ARG_TIME);

        financeAdapter = new ProjectParamAdapter(getLayoutInflater());
        listFinance.setAdapter(financeAdapter);
        listFinance.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));

        fillFinance();
        return view;
    }

    public static VisualFinanceFragment getInstance(double man, double time) {
        VisualFinanceFragment fragment = new VisualFinanceFragment();
        Bundle bundle = new Bundle();
        bundle.putDouble(ARG_MANS, man);
        bundle.putDouble(ARG_TIME, time);
        fragment.setArguments(bundle);

        return fragment;
    }

    public void fillFinance() {
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
        financeAdapter.append(fin);


        List<PieEntry> entrys = new LinkedList<>();
        for (ProjectParam f : fin) {
            entrys.add(new PieEntry((float)f.getValue(), f.getName()));
        }

        PieDataSet dataSet = new PieDataSet(entrys, "Декомпозиция работ по созданию ПО");


        PieData data = new PieData(dataSet);

        pieFinanceDiagram.setData(data);
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        pieFinanceDiagram.animateXY(5000, 5000);
        Description desc = new Description();
        desc.setText("Распределение ресурсов на разработку проекта");
        pieFinanceDiagram.setDescription(desc);
    }

}
