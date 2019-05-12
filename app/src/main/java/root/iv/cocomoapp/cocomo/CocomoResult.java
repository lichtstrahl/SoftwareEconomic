package root.iv.cocomoapp.cocomo;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
public class CocomoResult {
    private double man;
    private double time;

    public CocomoResult(double man, double time) {
        this.man = man;
        this.time = time;
    }
}
