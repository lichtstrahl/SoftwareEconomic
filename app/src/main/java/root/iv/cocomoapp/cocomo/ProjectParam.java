package root.iv.cocomoapp.cocomo;

import lombok.Data;

/**
 * Статья бюджета
 */
@Data
public class ProjectParam {
    private double value;
    private String name;

    public static ProjectParam getInstance(String name, double value) {
        ProjectParam f = new ProjectParam();
        f.value = value;
        f.name = name;
        return f;
    }
}
