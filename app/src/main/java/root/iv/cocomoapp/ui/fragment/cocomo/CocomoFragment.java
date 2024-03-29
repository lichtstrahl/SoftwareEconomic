package root.iv.cocomoapp.ui.fragment.cocomo;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import root.iv.cocomoapp.R;
import root.iv.cocomoapp.app.App;
import root.iv.cocomoapp.cocomo.Cocomo;
import root.iv.cocomoapp.cocomo.CocomoResult;
import root.iv.cocomoapp.cocomo.config.Configuration;
import root.iv.cocomoapp.cocomo.config.Group;
import root.iv.cocomoapp.ui.activity.ActivityAPI;

public class CocomoFragment extends Fragment {
    @BindView(R.id.groupLanguage)
    protected ChipGroup groupLang;
    @BindView(R.id.groupRaly)
    protected ChipGroup groupRely;
    @BindView(R.id.groupData)
    protected ChipGroup groupData;
    @BindView(R.id.groupCplx)
    protected ChipGroup groupCplx;
    @BindView(R.id.groupTime)
    protected ChipGroup groupTime;
    @BindView(R.id.groupStor)
    protected ChipGroup groupStor;
    @BindView(R.id.groupVirt)
    protected ChipGroup groupVirt;
    @BindView(R.id.groupTurn)
    protected ChipGroup groupTurn;
    @BindView(R.id.groupAcap)
    protected ChipGroup groupAcap;
    @BindView(R.id.groupAexp)
    protected ChipGroup groupAexp;
    @BindView(R.id.groupPcap)
    protected ChipGroup groupPcap;
    @BindView(R.id.groupVexp)
    protected ChipGroup groupVexp;
    @BindView(R.id.groupLexp)
    protected ChipGroup groupLexp;
    @BindView(R.id.groupModp)
    protected ChipGroup groupModp;
    @BindView(R.id.groupTool)
    protected ChipGroup groupTool;
    @BindView(R.id.groupSced)
    protected ChipGroup groupSced;
    @BindView(R.id.groupModel)
    protected ChipGroup groupModel;
    @BindView(R.id.inputKLOC)
    protected EditText inputKLOC;
    private Configuration configuration;
    private ActivityAPI activity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cocomo, container, false);
        ButterKnife.bind(this, view);
        groupLang.setOnCheckedChangeListener((g,n) -> {
            checkChip(g,n);
            configuration.setConfig(Group.LANG, n);
        });
        groupRely.setOnCheckedChangeListener((g,n) -> {
            checkChip(g,n);
            configuration.setConfig(Group.RELY, n);
        });
        groupData.setOnCheckedChangeListener((g,n) -> {
            checkChip(g,n);
            configuration.setConfig(Group.DATA, n);
        });
        groupCplx.setOnCheckedChangeListener((g,n) -> {
            checkChip(g,n);
            configuration.setConfig(Group.CPLX, n);
        });
        groupTime.setOnCheckedChangeListener((g,n) -> {
            checkChip(g,n);
            configuration.setConfig(Group.TIME, n);
        });
        groupStor.setOnCheckedChangeListener((g,n) -> {
            checkChip(g,n);
            configuration.setConfig(Group.STOR, n);
        });
        groupVirt.setOnCheckedChangeListener((g,n) -> {
            checkChip(g,n);
            configuration.setConfig(Group.VIRT, n);
        });
        groupTurn.setOnCheckedChangeListener((g,n) -> {
            checkChip(g,n);
            configuration.setConfig(Group.TURN, n);
        });
        groupAcap.setOnCheckedChangeListener((g,n) -> {
            checkChip(g,n);
            configuration.setConfig(Group.ACAP, n);
        });
        groupAexp.setOnCheckedChangeListener((g,n) -> {
            checkChip(g,n);
            configuration.setConfig(Group.AEXP, n);
        });
        groupPcap.setOnCheckedChangeListener((g,n) -> {
            checkChip(g,n);
            configuration.setConfig(Group.PCAP, n);
        });
        groupVexp.setOnCheckedChangeListener((g,n) -> {
            checkChip(g,n);
            configuration.setConfig(Group.VEXP, n);
        });
        groupLexp.setOnCheckedChangeListener((g,n) -> {
            checkChip(g,n);
            configuration.setConfig(Group.LEXP, n);
        });
        groupModp.setOnCheckedChangeListener((g,n) -> {
            checkChip(g,n);
            configuration.setConfig(Group.MODP, n);
        });
        groupTool.setOnCheckedChangeListener((g,n) -> {
            checkChip(g,n);
            configuration.setConfig(Group.TOOL, n);
        });
        groupSced.setOnCheckedChangeListener((g,n) -> {
            checkChip(g,n);
            configuration.setConfig(Group.SCED, n);
        });

        groupModel.setOnCheckedChangeListener((g,n) -> {
            checkChip(g,n);
            configuration.setModel(n);
        });

        configuration = new Configuration();

        return view;
    }

    private void checkChip(ChipGroup g, int n) {
        for (int i = 0; i < g.getChildCount(); i++) {
            Chip c = (Chip) g.getChildAt(i);

            if (c.getId() != n) {
                c.setChecked(false);
                c.setClickable(true);
            }

            if (c.getId() == n) {
                c.setChecked(true);
                c.setClickable(false);
            }
        }
    }

    @OnClick(R.id.buttonCalculate)
    public void clickCalculate() {
        int kloc = Integer.parseInt(inputKLOC.getText().toString());
        configuration.setKloc(kloc);
        Cocomo cocomo = new Cocomo(configuration);
        CocomoResult result = cocomo.calculate();
        activity.viewResult(result.getMan(), result.getTime());
        App.logI(String.format(Locale.ENGLISH, "Трудозатраты и время: %5.2f %5.2f", result.getMan(), result.getTime()));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        activity = (ActivityAPI)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        activity = null;
    }

    public static CocomoFragment getInstance() {
        CocomoFragment fragment = new CocomoFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }
}
