package lv.nixx.poc.spring6.rest;

import lv.nixx.poc.spring6.repository.beta.BetaRepository;
import lv.nixx.poc.spring6.service.DevDataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Profile("dev")
public class DevDataLoadController {
    private final DevDataLoader devDataLoader;

    @Autowired
    public DevDataLoadController(BetaRepository betaRepository, DevDataLoader devDataLoader) {
        this.devDataLoader = devDataLoader;
    }

    @GetMapping("/dev/loadTestData")
    public void loadDataSource() {
        devDataLoader.loadTestData();
    }

}
