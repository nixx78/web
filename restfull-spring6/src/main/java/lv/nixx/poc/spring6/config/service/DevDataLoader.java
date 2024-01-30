package lv.nixx.poc.spring6.config.service;

import lv.nixx.poc.common.db.SQLScriptUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import javax.sql.DataSource;

@Service
public class DevDataLoader {


    private final String alphaInitFile;
    private final String betaInitFile;
    private final DataSource alphaDataSource;
    private final DataSource betaDataSource;

    public DevDataLoader(
            @Value("${db.alpha.data-file}") String alphaInitFile,
            @Value("${db.beta.data-file}") String betaInitFile,
            DataSource alphaDataSource,
            DataSource betaDataSource
    ) {
        this.alphaInitFile = alphaInitFile;
        this.betaInitFile = betaInitFile;
        this.alphaDataSource = alphaDataSource;
        this.betaDataSource = betaDataSource;
    }

    public void loadTestData() {
        SQLScriptUtil.execute(alphaDataSource, alphaInitFile);
        SQLScriptUtil.execute(betaDataSource, betaInitFile);
    }

}
