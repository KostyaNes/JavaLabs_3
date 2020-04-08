package com.javalabs.eclient.controller;

import com.javalabs.eclient.GetResponse;
import com.javalabs.eclient.entity.Car;
import com.javalabs.eclient.repository.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RefreshScope
@RequestMapping("/cars")
public class CarsController {

    @Value("${eureka.instance.instance-id}")
    private String instanceId;

    @Value("${client.property1}")
    private String prop1;

    @Value("${client.property2}")
    private String prop2;

    @Autowired
    CarsRepository carsRepository;

    @GetMapping(path="/config")
    public @ResponseBody
    HashMap<String, String> getConfig() {
        HashMap<String, String> configmap = new HashMap<String, String>();
        configmap.put("client prop1", prop1);
        configmap.put("client prop2", prop2);
        return configmap;
    }

    @GetMapping("")
    public @ResponseBody
    GetResponse<List<Car>> getCarById(@RequestParam(value = "id", required = false) Integer id) throws IOException {

        List<Car> resultList = new ArrayList<>();
        if (id == null) {
            Iterable<Car> result = carsRepository.findAll();
            result.forEach(resultList::add);
        }
        else {
            Optional<Car> Car = carsRepository.findById(id);
            if (Car.isPresent()) {
                resultList.add(Car.get());
            }
            else {
                return null;
            }
        }

        return new GetResponse<List<Car>>(resultList, instanceId);
    }

    @PostMapping("/delete")
    public @ResponseBody
    GetResponse<String> deleteCarById(@RequestParam(value = "id", required = true) Integer id) {

        Optional<Car> car = carsRepository.findById(id);
        if (car.isPresent()) {
            carsRepository.delete(car.get());
            return new GetResponse<String>("Successfully removed car with id: '" + id + "'!", instanceId);
        }

        return new GetResponse<String>("Error! Car with id: '" + id + "' does not exist!", instanceId);
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

        Car newCar = new Car(model, manufacturer, manufactureYear, gearbox, fuel, horsepower, color, price);
        carsRepository.save(newCar);
        return new GetResponse<String>("Successfully added new car!", instanceId);
    }

    @PostMapping("/update")
    public @ResponseBody
    GetResponse<String> updateCar(@RequestParam(value = "id", required = true) Integer id,
                            @RequestParam(value = "model", required = false) String model,
                            @RequestParam(value = "manufacturer", required = false) String manufacturer,
                            @RequestParam(value = "manufactureyear", required = false) Integer manufactureYear,
                            @RequestParam(value = "gearbox", required = false) String gearbox,
                            @RequestParam(value = "fuel", required = false) String fuel,
                            @RequestParam(value = "horsepower", required = false) Integer horsepower,
                            @RequestParam(value = "color", required = false) String color,
                            @RequestParam(value = "price", required = false) Float price) {

        Optional<Car> car = carsRepository.findById(id);
        if (car.isPresent()) {
            Car updatedCar = car.get();

            if (model != null) {
                updatedCar.setModel(model);
            }

            if (manufacturer != null) {
                updatedCar.setManufacturer(manufacturer);
            }

            if (manufactureYear != null) {
                updatedCar.setManufactureYear(manufactureYear);
            }

            if (gearbox != null) {
                updatedCar.setGearbox(gearbox);
            }

            if (fuel != null) {
                updatedCar.setFuel(fuel);
            }

            if (horsepower != null) {
                updatedCar.setHorsepower(horsepower);
            }

            if (color != null) {
                updatedCar.setColor(color);
            }

            if (price != null) {
                updatedCar.setPrice(price);
            }

            carsRepository.save(updatedCar);

            return new GetResponse<String>("Successfully updated car with id: '" + id + "'!", instanceId);
        }
        else {
            return new GetResponse<String>("Error! Car with id: '" + id + "' does not exist!", instanceId);
        }
    }
}