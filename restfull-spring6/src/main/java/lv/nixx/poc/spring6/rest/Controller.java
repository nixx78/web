package lv.nixx.poc.spring6.rest;

import lv.nixx.poc.spring6.orm.alpha.AlphaEntity;
import lv.nixx.poc.spring6.orm.beta.BetaEntity;
import lv.nixx.poc.spring6.repository.alpha.AlphaRepository;
import lv.nixx.poc.spring6.repository.beta.BetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;

//TODO https://springdoc.org/#features
@RestController
public class Controller {

    private final AlphaRepository alphaRepository;
    private final BetaRepository betaRepository;

    @Autowired
    public Controller(AlphaRepository alphaRepository, BetaRepository betaRepository) {
        this.alphaRepository = alphaRepository;
        this.betaRepository = betaRepository;
    }

    @GetMapping("/service")
    public String getHello() {
        return "Hello: " + System.currentTimeMillis();
    }

    @GetMapping("/allAlpha")
    public List<AlphaEntity> getAllAlpha() {
        return alphaRepository.findAll();
    }

    @GetMapping("/allBeta")
    public List<BetaEntity> getAllBeta() {
        return betaRepository.findAll();
    }



}
