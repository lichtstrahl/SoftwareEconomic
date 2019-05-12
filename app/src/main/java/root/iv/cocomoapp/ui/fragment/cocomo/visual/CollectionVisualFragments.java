package root.iv.cocomoapp.ui.fragment.cocomo.visual;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import root.iv.cocomoapp.ui.fragment.cocomo.visual.finance.VisualFinanceFragment;
import root.iv.cocomoapp.ui.fragment.cocomo.visual.lifecicle.VisualTimeLifecycleFragment;
import root.iv.cocomoapp.ui.fragment.cocomo.visual.lifecicle.VisualWorkLifecycleFragment;
import root.iv.cocomoapp.ui.fragment.cocomo.visual.mans.VisualMansFragment;

public class CollectionVisualFragments extends FragmentPagerAdapter {
    private BaseVisualFragment[] FRAGMENTS;


    public CollectionVisualFragments(FragmentManager fm, double man, double time) {
        super(fm);
        FRAGMENTS = new BaseVisualFragment[] {
                VisualFinanceFragment.getInstance(man, time),
                VisualWorkLifecycleFragment.getInstance(man, time),
                VisualTimeLifecycleFragment.getInstance(man, time),
                VisualMansFragment.getInstance(man, time)
        };
    }

    @Override
    public Fragment getItem(int position) {
        return FRAGMENTS[position];
    }

    @Override
    public int getCount() {
        return FRAGMENTS.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return FRAGMENTS[position].getName();
    }

}
