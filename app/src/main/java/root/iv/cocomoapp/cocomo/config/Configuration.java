package root.iv.cocomoapp.cocomo.config;

import androidx.annotation.Nullable;
import lombok.Data;

@Data
public class Configuration {
    // Множители ЯП
    private static final double[] CONFIG_LANG = {1.0,   2.5,    3.0,    3.0,    3.5,    6,  6,  6.5,    8.5,    11, 16};
    // Аттрибуты ПО
    private static final double[] CONFIG_RELY = {0.75,   0.86,   1.0,    1.15,   1.4};
    private static final double[] CONFIG_DATA = {        0.94,   1.0,    1.08,   1.16};
    private static final double[] CONFIG_CPLX = {0.7,    0.85,   1.0,    1.15,   1.3};
    // Аттрибуты компьютера
    private static final double[] CONFIG_TIME = {                1.0,    1.11,   1.50};
    private static final double[] CONFIG_STOR = {                1.0,    1.06,   1.21};
    private static final double[] CONFIG_VIRT = {        0.87,   1.0,    1.15,   1.30};
    private static final double[] CONFIG_TURN = {        0.87,   1.0,    1.07,   1.15};
    // Аттрибуты персонала
    private static final double[] CONFIG_ACAP = {1.46,   1.19,   1.0,    0.86,   0.71};
    private static final double[] CONFIG_AEXP = {1.29,   1.15,   1.0,    0.91,   0.82};
    private static final double[] CONFIG_PCAP = {1.42,   1.17,   1.0,    0.86,   0.7};
    private static final double[] CONFIG_VEXP = {1.21,   1.1,    1.0,    0.9};
    private static final double[] CONFIG_LEXP = {1.14,   1.07,   1.0,    0.95};
    // Аттрибуты проекта
    private static final double[] CONFIG_MODP = {1.24,   1.1,    1.0,    0.91,   0.82};
    private static final double[] CONFIG_TOOL = {1.24,   1.1,    1.0,    0.91,   0.82};
    private static final double[] CONFIG_SCED = {1.23,   1.08,   1.0,    1.04,   1.1};


    private Group[] groupsConfig = new Group[]{
            new Group(Group.LANG, CONFIG_LANG).setCurrentValue(0),
            new Group(Group.RELY, CONFIG_RELY).setCurrentValue(2),
            new Group(Group.DATA, CONFIG_DATA).setCurrentValue(1),
            new Group(Group.CPLX, CONFIG_CPLX).setCurrentValue(2),
            new Group(Group.TIME, CONFIG_TIME).setCurrentValue(0),
            new Group(Group.STOR, CONFIG_STOR).setCurrentValue(0),
            new Group(Group.VIRT, CONFIG_VIRT).setCurrentValue(1),
            new Group(Group.TURN, CONFIG_TURN).setCurrentValue(1),
            new Group(Group.ACAP, CONFIG_ACAP).setCurrentValue(2),
            new Group(Group.AEXP, CONFIG_AEXP).setCurrentValue(2),
            new Group(Group.PCAP, CONFIG_PCAP).setCurrentValue(2),
            new Group(Group.VEXP, CONFIG_VEXP).setCurrentValue(2),
            new Group(Group.LEXP, CONFIG_LEXP).setCurrentValue(2),
            new Group(Group.MODP, CONFIG_MODP).setCurrentValue(2),
            new Group(Group.TOOL, CONFIG_TOOL).setCurrentValue(2),
            new Group(Group.SCED, CONFIG_SCED).setCurrentValue(2)
    };

    @Nullable private CocomoModel model = CocomoModel.NORMAL;

    /**
     *
     * @param name
     * @param number Всегда увеличен на 1
     */
    public void setConfig(String name, int number) {
        int sum = 0;

        for (int i = 0; i < groupsConfig.length; i++) {
            if (groupsConfig[i].getName().equals(name)) {
                int currentNumber = number - sum - 1;
                groupsConfig[i].setCurrentIndex(currentNumber);
                break;
            } else {
                sum += groupsConfig[i].size();
            }
        }
    }

    public void setModel(int number) {
        int sum = 0;

        for (Group g : groupsConfig)
            sum += g.size();

        int currentNumber = number - sum - 1;
        model = CocomoModel.values()[currentNumber];
    }

    public double getValue(String nameGroup) {
        for (int i = 0; i < groupsConfig.length; i++) {
            if (groupsConfig[i].getName().equals(nameGroup)) {
                return groupsConfig[i].getValue();
            }
        }
        throw new IllegalStateException("Не удалось найти группу с указанным именем");
    }

    public int getIndex(String nameGroup) {
        for (int i = 0; i < groupsConfig.length; i++) {
            if (groupsConfig[i].getName().equals(nameGroup)) {
                return groupsConfig[i].getCurrentIndex();
            }
        }
        throw new IllegalStateException("Не удалось найти группу с указанным именем");
    }
}
