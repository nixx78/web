package lv.nixx.poc.spring6.config;

import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;
import java.sql.SQLException;

public class DataSourceInit {

    public static void initDB(DataSource dataSource, String schemaFile) throws SQLException {
        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource(schemaFile));
    }
}
