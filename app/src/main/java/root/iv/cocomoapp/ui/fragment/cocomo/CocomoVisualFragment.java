package root.iv.cocomoapp.ui.fragment.cocomo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;
import root.iv.cocomoapp.R;
import root.iv.cocomoapp.cocomo.Finance;
import root.iv.cocomoapp.ui.adapter.FinanceAdapter;

public class CocomoVisualFragment extends Fragment {
    private static final String ARG_MANS = "args:mans";
    private static final String ARG_TIME = "args:time";
    @BindView(R.id.pieFinanceDiagram)
    PieChart pieFinanceDiagram;
    @BindView(R.id.listFinance)
    protected RecyclerView listFinance;
    private FinanceAdapter financeAdapter;
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

        financeAdapter = new FinanceAdapter(getLayoutInflater());
        listFinance.setAdapter(financeAdapter);
        listFinance.setLayoutManager(new LinearLayoutManager(this.getContext(), LinearLayoutManager.HORIZONTAL, false));

        fillFinance();
        Toast.makeText(this.getContext(), "create", Toast.LENGTH_SHORT).show();

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

    public void fillFinance() {
        List<Finance> fin = new LinkedList<>();

        fin.add(Finance.getInstance("Анализ требований (4%)", 0.04 * mans));
        fin.add(Finance.getInstance("Проектирование продукта (12%)", 0.12 * mans));
        fin.add(Finance.getInstance("Программирование (44%)", 0.44*mans));
        fin.add(Finance.getInstance("Тестирование (6%)", 0.06*mans));
        fin.add(Finance.getInstance("Верификация и аттестация (14%)", 0.14*mans));
        fin.add(Finance.getInstance("Канцелярий проекта (7%)", 0.07*mans));
        fin.add(Finance.getInstance("Управление конфигурацией и обсечение качества (7%)", 0.07*mans));
        fin.add(Finance.getInstance("Создание руководств (6%)", 0.06*mans));

        // Загружаем данные в адаптер
        financeAdapter.append(fin);


        List<PieEntry> entrys = new LinkedList<>();
        for (Finance f : fin) {
            entrys.add(new PieEntry((float)f.getValue(), f.getName()));
        }

        PieDataSet dataSet = new PieDataSet(entrys, "Декомпозиция работ по созданию ПО");


        PieData data = new PieData(dataSet);

        pieFinanceDiagram.setData(data);
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        pieFinanceDiagram.animateXY(5000, 5000);
        Description desc = new Description();
        desc.setText("РАспределение ресурсов на разработку проекта");
        pieFinanceDiagram.setDescription(desc);
    }
}
