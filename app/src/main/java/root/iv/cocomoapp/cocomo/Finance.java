package root.iv.cocomoapp.cocomo;

import lombok.Data;

/**
 * Статья бюджета
 */
@Data
public class Finance {
    private double value;
    private String name;

    public static Finance getInstance(String name, double value) {
        Finance f = new Finance();
        f.value = value;
        f.name = name;
        return f;
    }
}
