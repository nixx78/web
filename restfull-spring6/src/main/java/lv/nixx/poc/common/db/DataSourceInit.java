package lv.nixx.poc.common.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DataSourceInit {

    private static final Logger log = LoggerFactory.getLogger(DataSourceInit.class);

    public static void initDB(DataSource dataSource, String schemaFile) throws SQLException {
        if (schemaFile != null) {
            log.info("Execute SQL script from file [{}] data source", schemaFile);
            ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource(schemaFile));
        }
    }
}
