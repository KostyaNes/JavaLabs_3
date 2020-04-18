package com.javalabs.apigateway.Car;

import com.javalabs.apigateway.ConfigClientAppConfiguration;
import com.javalabs.apigateway.GetResponse;
import com.javalabs.apigateway.ProxyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class CarsController {

   /* @Autowired
    CarsClient carsClient;*/
    @Autowired
    ProxyService carsClient;

    @Autowired
    ConfigClientAppConfiguration configClientAppConfiguration;

    @GetMapping(path = "/config")
    public @ResponseBody
    Map<String, String> getConfig() {
        HashMap<String, String> configmap = new HashMap<String, String>();
        configmap.put("gateway prop1", configClientAppConfiguration.getProp1());
        configmap.put("gateway prop2", configClientAppConfiguration.getProp2());
        return configmap;
    }

    @GetMapping("/get")
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
        return carsClient.deleteCarById(id);
    }

    @PostMapping(value = "/add")
    public @ResponseBody
    GetResponse<String> addCar(@RequestBody Car car) {
        return carsClient.addCar(car);
    }

    @PostMapping("/update")
    public @ResponseBody
    GetResponse<String> updateCar(@RequestBody Car car) {
        return carsClient.updateCar(car);
    }
}
