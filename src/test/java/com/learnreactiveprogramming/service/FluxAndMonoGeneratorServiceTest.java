package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

public class FluxAndMonoGeneratorServiceTest {
    FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

    @Test
    void namesFlux() {

        var namesFlux = fluxAndMonoGeneratorService.namesFlux();

        StepVerifier.create(namesFlux)
                //.expectNext("alex", "ben", "chloe")// Para comprobar todos los elementos del flujo
                .expectNextCount(3) // Para comprobar la cantidad de elementos del flujo
                .verifyComplete();
    }

    @Test
    void nameMono(){
        //given

        //when
        var nameMono = fluxAndMonoGeneratorService.nameMono();

        //then
        StepVerifier.create(nameMono)
                .expectNext("alex")
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    void namesFluxMap() {
        var nameFluxMap = fluxAndMonoGeneratorService.namesFluxMap();

        StepVerifier.create(nameFluxMap)
                .expectNext("ALEX")
                .expectNext("BEN")
                .expectNext("CHLOE")
                .verifyComplete();
    }

    @Test
    void namesFluxInmutability() {
        var nameFluxMap = fluxAndMonoGeneratorService.namesFluxInmutability();

        StepVerifier.create(nameFluxMap)
                .expectNext("alex")
                .expectNext("ben")
                .expectNextCount(1)
                .verifyComplete();

    }

    @Test
    void namesFluxFilter() {
        Integer stringLenght = 3;
        var namesFluxFilter = fluxAndMonoGeneratorService.namesFluxFilter(stringLenght);
        StepVerifier.create(namesFluxFilter)
                .expectNext("4-ALEX", "5-CHLOE")
                .verifyComplete();
    }

    @Test
    void nameMonoFilter() {
        Integer stringLenght = 3;
        var nameMonoFilter = fluxAndMonoGeneratorService.nameMonoFilter(stringLenght);
        StepVerifier.create(nameMonoFilter)
                .expectNext("ALEX")
                .verifyComplete();
    }

    @Test
    void namesFluxFlatMap() {
        Integer stringLenght = 3;
        var namesFluxFlatMap = fluxAndMonoGeneratorService.namesFluxFlatMap(stringLenght);
        StepVerifier.create(namesFluxFlatMap)
                .expectNext("A","L","E","X","C","H","L","O","E")
                .verifyComplete();
    }

    @Test
    void namesFluxFlatMapAsync() {
        Integer stringLenght = 3;
        var namesFluxFlatMap = fluxAndMonoGeneratorService.namesFluxFlatMapAsync(stringLenght);
        StepVerifier.create(namesFluxFlatMap)
                //.expectNext("A","L","E","X","C","H","L","O","E")
                .expectNextCount(9)
                .verifyComplete();
    }
}
