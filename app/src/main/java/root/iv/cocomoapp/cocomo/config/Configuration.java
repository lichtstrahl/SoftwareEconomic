package root.iv.cocomoapp.cocomo.config;

import androidx.annotation.Nullable;
import lombok.Data;

@Data
public class Configuration {
    @Nullable
    private ProgrammLanguage language;

    public Configuration() {}


}
