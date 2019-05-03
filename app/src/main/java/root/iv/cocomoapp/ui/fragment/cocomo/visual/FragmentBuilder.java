package root.iv.cocomoapp.ui.fragment.cocomo.visual;

import androidx.fragment.app.Fragment;

public interface FragmentBuilder {
    Fragment build(double m, double t);
    String getName();
}
