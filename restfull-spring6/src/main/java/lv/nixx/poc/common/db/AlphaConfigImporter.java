package lv.nixx.poc.common.db;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.lang.NonNull;

public class AlphaConfigImporter implements ImportSelector {
    @Override
    public String[] selectImports(@NonNull AnnotationMetadata metadata) {
        return new String[]{
                "lv.nixx.poc.common.db.AlphaDBConfig"
        };
    }

}
