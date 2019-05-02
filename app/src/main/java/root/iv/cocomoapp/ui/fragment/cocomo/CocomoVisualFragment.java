package root.iv.cocomoapp.ui.fragment.cocomo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

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
        financeAdapter.append(Finance.getInstance("Анализ требований (4%)", 0.04 * mans));
        financeAdapter.append(Finance.getInstance("Проектирование продукта (12%)", 0.12 * mans));
        financeAdapter.append(Finance.getInstance("Программирование (44%)", 0.44*mans));
        financeAdapter.append(Finance.getInstance("Тестирование (6%)", 0.06*mans));
        financeAdapter.append(Finance.getInstance("Верификация и аттестация (14%)", 0.14*mans));
        financeAdapter.append(Finance.getInstance("Канцелярий проекта (7%)", 0.07*mans));
        financeAdapter.append(Finance.getInstance("Управление конфигурацией и обсечение качества (7%)", 0.07*mans));
        financeAdapter.append(Finance.getInstance("Создание руководств (6%)", 0.06*mans));

    }
}
