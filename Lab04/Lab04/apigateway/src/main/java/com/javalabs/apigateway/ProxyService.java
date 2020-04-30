package com.javalabs.apigateway;

import com.javalabs.apigateway.Car.Car;
import com.javalabs.apigateway.Car.CarsClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProxyService {
    private static final String BACKEND_A = "client";

    @Autowired
    private CarsClient carsClient;

    @CircuitBreaker(name = BACKEND_A, fallbackMethod = "fallback")
    public @ResponseBody
    GetResponse<List<Car>> getCarById(@RequestParam(value = "id", required = false) Integer id) throws IOException {

        GetResponse<List<Car>> result = carsClient.getCarById(id);
        return result;
    }

    @PostMapping("/delete")
    public @ResponseBody
    GetResponse<String> deleteCarById(@RequestParam(value = "id", required = true) Integer id) {
        return carsClient.deleteCarById(id);
    }

    @Retry(name = BACKEND_A)
    public @ResponseBody
    GetResponse<String> addCar(@RequestBody Car car) {
        return carsClient.addCar(car);
    }

    @Retry(name = BACKEND_A)
    public @ResponseBody
    GetResponse<String> updateCar(@RequestBody Car car) {
        return carsClient.updateCar(car);
    }

    public GetResponse<List<Car>> fallback(Throwable e) {
        return new GetResponse<List<Car>>(null, "Fallback");
    }
}