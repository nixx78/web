package lv.nixx.poc.react.flux;

import org.junit.Test;
import reactor.core.publisher.Flux;

public class FluxSandbox {

    @Test
    public void simpleFluxExample() {
        Flux<String> fluxColors = Flux.just("red", "green", "blue");
        fluxColors
                .log()
                .map(t -> "Mapped:" + t)
                .subscribe(t -> System.out.println("Process (" + t + ")"));
    }

    @Test
    public void zipExample() {
        Flux<String> fluxFruits = Flux.just("apple", "pear", "plum", "");
        Flux<String> fluxColors = Flux.just("red", "green", "blue", "");
        Flux<Integer> fluxAmounts = Flux.just(10, 20, 30, 40);
        Flux.zip(fluxFruits, fluxColors, fluxAmounts)
                .map(t -> "!" + t)
                .subscribe(System.out::println);
    }


}
