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
import java.util.Map;
import java.util.Optional;


@FeignClient(value = "client")
@RequestMapping("/cars")
public interface CarsClient {

    @RequestMapping(path = "/config", method = RequestMethod.GET)
    public @ResponseBody
    Map<String, String> getConfig();

    @GetMapping("")
    public @ResponseBody
    GetResponse<List<Car>> getCarById(@RequestParam(value = "id", required = false) Integer id) throws IOException;

    @PostMapping("/delete")
    public @ResponseBody
    GetResponse<String> deleteCarById(@RequestParam(value = "id", required = true) Integer id);

    @PostMapping("/add")
    public @ResponseBody
    GetResponse<String> addCar(@RequestBody Car car);

    @PostMapping("/update")
    public @ResponseBody
    GetResponse<String> updateCar(@RequestBody Car car);
}
