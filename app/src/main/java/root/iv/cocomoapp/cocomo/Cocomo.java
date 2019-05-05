package root.iv.cocomoapp.cocomo;

import root.iv.cocomoapp.cocomo.config.Configuration;
import root.iv.cocomoapp.cocomo.config.Group;

public class Cocomo {
    private Configuration configuration;
    public Cocomo(Configuration configuration) {
        this.configuration = configuration;
    }

    // TODO Сделать ввод KLOC
    public CocomoResult calculate() {
        final int KLOC = configuration.getKloc();

        // Универсальная длина. В строках указанного языка
        double kLoc = toFixed2(KLOC/configuration.getValue(Group.LANG));
        final double EAF = eaf();

        double manMonth = 0.0;
        double timeMonth = 0.0;

        switch (configuration.getModel()) {
            case NORMAL:
                manMonth = 3.2 * EAF * Math.pow(kLoc, 1.05);
                timeMonth = 2.5 * Math.pow(manMonth, 0.38);
                break;

            case MEDIUM:
                manMonth = 3 * EAF * Math.pow(kLoc, 1.12);
                timeMonth = 2.5 * Math.pow(manMonth, 0.35);
                break;

            case INNER:
                manMonth = 2.8 * EAF * Math.pow(kLoc, 1.2);
                timeMonth = 2.5 * Math.pow(manMonth, 0.32);
                break;
        }

        return new CocomoResult(manMonth, timeMonth);
    }

    public double eaf() {
        double result = 1;
        result *= configuration.getValue(Group.RELY);
        result *= configuration.getValue(Group.DATA);
        result *= configuration.getValue(Group.CPLX);

        result *= configuration.getValue(Group.TIME);
        result *= configuration.getValue(Group.STOR);
        result *= configuration.getValue(Group.VIRT);
        result *= configuration.getValue(Group.TURN);

        result *= configuration.getValue(Group.ACAP);
        result *= configuration.getValue(Group.AEXP);
        result *= configuration.getValue(Group.PCAP);
        result *= configuration.getValue(Group.VEXP);
        result *= configuration.getValue(Group.LEXP);

        result *= configuration.getValue(Group.MODP);
        result *= configuration.getValue(Group.TOOL);
        result *= configuration.getValue(Group.SCED);

        return toFixed2(result);
    }

    private double toFixed2(double x) {
        return Math.round(x*100)/100.0;
    }
}
