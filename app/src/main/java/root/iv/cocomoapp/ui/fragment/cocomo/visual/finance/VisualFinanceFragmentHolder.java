package root.iv.cocomoapp.ui.fragment.cocomo.visual.finance;

import root.iv.cocomoapp.ui.fragment.cocomo.visual.FragmentBuilder;

public class VisualFinanceFragmentHolder implements FragmentBuilder {
    public VisualFinanceFragmentHolder() {
    }

    @Override
    public VisualFinanceFragment build(double m, double t) {
        return VisualFinanceFragment.getInstance(m, t);
    }

    @Override
    public String getName() {
        return "ProjectParam";
    }
}
