package root.iv.cocomoapp.ui.fragment.cocomo.visual.lifecicle;

import androidx.fragment.app.Fragment;
import root.iv.cocomoapp.ui.fragment.cocomo.visual.FragmentBuilder;

public class VisualWorkLifecycleFragmentHolder implements FragmentBuilder {
    @Override
    public Fragment build(double m, double t) {
        return VisualWorkLifecycleFragment.getInstance(m ,t);
    }

    @Override
    public String getName() {
        return "WORK LIFECYCLE";
    }
}
