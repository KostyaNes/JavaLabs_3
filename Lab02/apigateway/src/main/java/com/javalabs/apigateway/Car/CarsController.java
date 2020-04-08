package com.javalabs.apigateway.Car;

import com.javalabs.apigateway.GetResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class CarsController {

    @Autowired
    CarsClient carsClient;

    @GetMapping("")
    public @ResponseBody
    GetResponse<List<Car>> getCarById(ServletResponse res, @RequestParam(value = "id", required = false) Integer id) throws IOException {

        GetResponse<List<Car>> result = carsClient.getCarById(id);

        if (result == null) {
            res.getOutputStream().println("Error! Car with id: '" + id + "' does not exist!");
            return null;
        }
        else {
            return result;
        }
    }

    @PostMapping("/delete")
    public @ResponseBody
    GetResponse<String> deleteCarById(@RequestParam(value = "id", required = true) Integer id) {
        return deleteCarById(id);
    }

    @PostMapping("/add")
    public @ResponseBody
    GetResponse<String> addCar(@RequestParam(value = "model", required = true) String model,
                               @RequestParam(value = "manufacturer", required = true) String manufacturer,
                               @RequestParam(value = "manufactureyear", required = true) Integer manufactureYear,
                               @RequestParam(value = "gearbox", required = true) String gearbox,
                               @RequestParam(value = "fuel", required = true) String fuel,
                               @RequestParam(value = "horsepower", required = true) Integer horsepower,
                               @RequestParam(value = "color", required = true) String color,
                               @RequestParam(value = "price", required = true) Float price) {
        return addCar(model, manufacturer, manufactureYear, gearbox, fuel, horsepower, color, price);
    }

    @PostMapping("/update")
    public GetResponse<String> updateCar(@RequestParam(value = "id", required = true) Integer id,
                                         @RequestParam(value = "model", required = false) String model,
                                         @RequestParam(value = "manufacturer", required = false) String manufacturer,
                                         @RequestParam(value = "manufactureyear", required = false) Integer manufactureYear,
                                         @RequestParam(value = "gearbox", required = false) String gearbox,
                                         @RequestParam(value = "fuel", required = false) String fuel,
                                         @RequestParam(value = "horsepower", required = false) Integer horsepower,
                                         @RequestParam(value = "color", required = false) String color,
                                         @RequestParam(value = "price", required = false) Float price) {
        return updateCar(id, model, manufacturer, manufactureYear, gearbox, fuel, horsepower, color, price);
    }
}
