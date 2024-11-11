package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class FluxAndMonoGeneratorService {


    public static void main(String[] args) {
        FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();
        fluxAndMonoGeneratorService.namesFlux().subscribe(name -> {
            System.out.println("Name is : " + name);
        });
        fluxAndMonoGeneratorService.nameMono()
                .subscribe(name -> {
                    System.out.println("Mono Name is : " + name);
                });
    }

    public Flux<String> namesFlux() {
        return Flux.fromIterable(List.of("alex", "ben", "chloe")).log(); // db or a remote service call
    }

    public Mono<String> nameMono() {
        return Mono.just("alex").log();
    }

    public Flux<String> namesFluxMap() {
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .map(String::toUpperCase) // Map se usa para transformar cada uno de los elementos del flujo y es sincrono
                //.map(s -> s.toUpperCase()) // Map usando una lambda
                .log();
    }

    public Flux<String> namesFluxInmutability() {
        var namesFlux = Flux.fromIterable(List.of("alex", "ben", "chloe"));
        namesFlux.map(String::toUpperCase);
        return namesFlux;
    }

    public Flux<String> namesFluxFilter(Integer stringLength) {
        // filter the string whose lenght is greater than 3
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .map(String::toUpperCase)
                .filter(s -> s.length() > stringLength) // El operador filtro se usa para filtrar elementos del flujo
                .map(s -> s.length() + "-" + s)
                .log();
    }

    public Mono<String> nameMonoFilter(Integer stringLength) {
        return Mono.just("alex")
                .map(String::toUpperCase)
                .filter(s -> s.length()>stringLength)
                .log();
    }

    public Flux<String> namesFluxFlatMap(Integer stringLength) {
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .map(String::toUpperCase) // FlatMap se usa para transformar un elemento fuente en un flujo de uno a n elementos, es decir, de Mono a Flux y es asincrono
                .filter(s->s.length()>stringLength)
                .flatMap(this::splitString)
                 //.flatMap(s->splitString(s)) usando lambda
                .log();
    }

    public Flux<String> splitString(String name){
        return Flux.fromArray(name.split(""));
    }

    public Flux<String> namesFluxFlatMapAsync(Integer stringLength) {
        return Flux.fromIterable(List.of("alex", "ben", "chloe"))
                .map(String::toUpperCase) // FlatMap se usa para transformar un elemento fuente en un flujo de uno a n elementos, es decir, de Mono a Flux
                .filter(s->s.length()>stringLength)
                .flatMap(this::splitStringDelay)
                //.flatMap(s->splitString(s)) usando lambda
                .log();
    }

    public Flux<String> splitStringDelay(String name){
        return Flux.fromArray(name.split(""))
                .delayElements(Duration.ofMillis(new Random().nextInt(1000)));
    }

}
