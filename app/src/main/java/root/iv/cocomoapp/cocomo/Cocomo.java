package root.iv.cocomoapp.cocomo;

import root.iv.cocomoapp.cocomo.config.Configuration;
import root.iv.cocomoapp.cocomo.config.Group;

public class Cocomo {
    private Configuration configuration;
    public Cocomo(Configuration configuration) {
        this.configuration = configuration;
    }

    // TODO Сделать ввод KLOC
    public void calculate() {
        int KLOC = 25;


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

        return Math.round(result*100)/100.0;
    }
}
