package lv.nixx.poc.react.flux;

import org.junit.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

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

    @Test
    public void onErrorExample() {
        Flux<String> lengthCalculator = Flux.just("1", null, "123", "1234")
                .map(t -> "Length:" + t.length())
//                .onErrorReturn(NullPointerException.class, "Incorrect value")
                .onErrorResume(NullPointerException.class, t -> Mono.just("DefaultValue"));

        lengthCalculator.subscribe(value -> System.out.println("Next: " + value),
                error -> System.err.println("Error: " + error));

        StepVerifier.create(lengthCalculator)
                .expectNextCount(2)
                .verifyComplete();
    }

    @Test
    public void stepVerifier() {
        Flux<String> lengthCalculator = Flux.just("1", null, "123", "1234")
                .map(t -> "Length:" + t.length());

        StepVerifier.create(lengthCalculator)
                .expectNextCount(1)
                .expectError(NullPointerException.class)
                .verify();
    }


}
