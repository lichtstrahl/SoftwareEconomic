package root.iv.cocomoapp.ui.fragment.cocomo.visual;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import root.iv.cocomoapp.ui.fragment.cocomo.visual.finance.VisualFinanceFragmentHolder;
import root.iv.cocomoapp.ui.fragment.cocomo.visual.lifecicle.VisualWorkLifecycleFragmentHolder;

public class CollectionVisualFragments extends FragmentPagerAdapter {
    private static final FragmentBuilder[] FRAGMENTS = new FragmentBuilder[] {
            new VisualFinanceFragmentHolder(),
            new VisualWorkLifecycleFragmentHolder()
    };


    private double m;
    private double t;

    public CollectionVisualFragments(FragmentManager fm, double man, double time) {
        super(fm);
        this.m = man;
        this.t = time;
    }

    @Override
    public Fragment getItem(int position) {
        return FRAGMENTS[position].build(m, t);
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
