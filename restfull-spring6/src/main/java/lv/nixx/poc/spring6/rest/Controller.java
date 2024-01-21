package lv.nixx.poc.spring6.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.SQLException;

//TODO https://springdoc.org/#features
@RestController
public class Controller {

    @Autowired

    DataSource alphaDataSource;

    @Autowired
    DataSource betaDataSource;

    @GetMapping("/service")
    public String getHello() throws SQLException {

        alphaDataSource.getConnection();
        betaDataSource.getConnection();

        return "Hello: " + System.currentTimeMillis();
    }



}
