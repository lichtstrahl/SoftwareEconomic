package root.iv.cocomoapp.cocomo.config;

import lombok.Data;

@Data
public class Group {
    // Список имен
    public static final String LANG = "lang";
    public static final String RELY = "rely";
    public static final String DATA = "data";
    public static final String CPLX = "cplx";
    public static final String TIME = "time";
    public static final String STOR = "stor";
    public static final String VIRT = "virt";
    public static final String TURN = "turn";
    public static final String ACAP = "acap";
    public static final String AEXP = "aexp";
    public static final String PCAP = "pcap";
    public static final String VEXP = "vexp";
    public static final String LEXP = "lexp";
    public static final String MODP = "modp";
    public static final String TOOL = "tool";
    public static final String SCED = "sced";




    private double[] original;
    private int currentIndex;
    private String name;

    public Group(String name, double[] original) {
        this.name = name;
        this.original = original;
        this.currentIndex = -1;
    }

    public int size() {
        return original.length;
    }

    public double getValue() {
        return original[currentIndex];
    }

    public void setCurrentValue(int index) {
        currentIndex = index;
    }
}
