package root.iv.cocomoapp.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.chip.ChipGroup;

import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import root.iv.cocomoapp.R;
import root.iv.cocomoapp.cocomo.config.Configuration;
import root.iv.cocomoapp.cocomo.config.ProgrammLanguage;

public class CocomoFragment extends Fragment {
    @BindView(R.id.groupLanguage)
    protected ChipGroup groupLanguage;
    private Configuration configuration;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cocomo, container, false);
        ButterKnife.bind(this, view);

        groupLanguage.setOnCheckedChangeListener((group, number) -> {
            int index = number - 1;
            configuration.setLanguage(ProgrammLanguage.values()[number]);
        });

        configuration = new Configuration();

        return view;
    }

    @OnClick(R.id.test)
    protected void clickTest() {
        Toast.makeText(this.getContext(), configuration.getLanguage().toString(), Toast.LENGTH_SHORT).show();
    }

    public static CocomoFragment getInstance() {
        CocomoFragment fragment = new CocomoFragment();
        Bundle bundle = new Bundle();
        fragment.setArguments(bundle);
        return fragment;
    }
}
