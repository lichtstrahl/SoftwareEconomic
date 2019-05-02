package root.iv.cocomoapp.ui.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import butterknife.BindView;
import butterknife.ButterKnife;
import root.iv.cocomoapp.R;
import root.iv.cocomoapp.ui.fragment.Cocomo2Fragment;
import root.iv.cocomoapp.ui.fragment.CocomoFragment;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.drawer)
    protected DrawerLayout drawerLayout;
    @BindView(R.id.navigation)
    protected NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        navigationView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.menuCocomo:
                    setupCocomoFragment();
                    drawerLayout.closeDrawer(GravityCompat.START, true);
                    break;
                case R.id.menuCocomo2:
                    setupCocomo2Fragment();
                    drawerLayout.closeDrawer(GravityCompat.START, true);
                    break;
            }

            return true;
        });

        setupCocomoFragment();
    }

    private void setupCocomoFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainFrame, CocomoFragment.getInstance())
                .commit();
    }

    private void setupCocomo2Fragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainFrame, Cocomo2Fragment.getInstance())
                .commit();
    }
}
