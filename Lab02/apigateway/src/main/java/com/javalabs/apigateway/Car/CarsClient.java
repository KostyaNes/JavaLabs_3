package com.javalabs.apigateway.Car;

import com.javalabs.apigateway.GetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@FeignClient(value = "client")
@RequestMapping("/cars")
public interface CarsClient {

    @GetMapping("")
    public @ResponseBody
    GetResponse<List<Car>> getCarById(@RequestParam(value = "id", required = false) Integer id) throws IOException;

    @PostMapping("/delete")
    public @ResponseBody
    GetResponse<String> deleteCarById(@RequestParam(value = "id", required = true) Integer id);

    @PostMapping("/add")
    public @ResponseBody
    GetResponse<String> addCar(@RequestParam(value = "model", required = true) String model,
                               @RequestParam(value = "manufacturer", required = true) String manufacturer,
                               @RequestParam(value = "manufactureyear", required = true) Integer manufactureYear,
                               @RequestParam(value = "gearbox", required = true) String gearbox,
                               @RequestParam(value = "fuel", required = true) String fuel,
                               @RequestParam(value = "horsepower", required = true) Integer horsepower,
                               @RequestParam(value = "color", required = true) String color,
                               @RequestParam(value = "price", required = true) Float price);

    @PostMapping("/update")
    public GetResponse<String> updateCar(@RequestParam(value = "id", required = true) Integer id,
                                  @RequestParam(value = "model", required = false) String model,
                                  @RequestParam(value = "manufacturer", required = false) String manufacturer,
                                  @RequestParam(value = "manufactureyear", required = false) Integer manufactureYear,
                                  @RequestParam(value = "gearbox", required = false) String gearbox,
                                  @RequestParam(value = "fuel", required = false) String fuel,
                                  @RequestParam(value = "horsepower", required = false) Integer horsepower,
                                  @RequestParam(value = "color", required = false) String color,
                                  @RequestParam(value = "price", required = false) Float price);
}
