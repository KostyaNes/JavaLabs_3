package com.javalabs.eclient.controller;

import com.javalabs.eclient.entity.Car;
import com.javalabs.eclient.repository.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
public class CarsController {

    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    CarsRepository carsRepository;

    @GetMapping("")
    public List<Car> getCarById(ServletResponse res, @RequestParam(value = "id", required = false) Integer id) throws IOException {

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
                res.getOutputStream().println("Error! Car with id: '" + id + "' does not exist!");
                return null;
            }
        }

        res.getOutputStream().println(discoveryClient.toString());

        return resultList;
    }

    @PostMapping("/delete")
    public String deleteCarById(@RequestParam(value = "id", required = true) Integer id) {

        Optional<Car> car = carsRepository.findById(id);
        if (car.isPresent()) {
            carsRepository.delete(car.get());
            return "Successfully removed car with id: '" + id + "'!";
        }

        return "Error! Car with id: '" + id + "' does not exist!";
    }

    @PostMapping("/add")
    public String addCar(@RequestParam(value = "model", required = true) String model,
                         @RequestParam(value = "manufacturer", required = true) String manufacturer,
                         @RequestParam(value = "manufactureyear", required = true) Integer manufactureYear,
                         @RequestParam(value = "gearbox", required = true) String gearbox,
                         @RequestParam(value = "fuel", required = true) String fuel,
                         @RequestParam(value = "horsepower", required = true) Integer horsepower,
                         @RequestParam(value = "color", required = true) String color,
                         @RequestParam(value = "price", required = true) Float price) {

        Car newCar = new Car(model, manufacturer, manufactureYear, gearbox, fuel, horsepower, color, price);
        carsRepository.save(newCar);
        return "Successfully added new car!";
    }

    @PostMapping("/update")
    public String updateCar(@RequestParam(value = "id", required = true) Integer id,
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

            return "Successfully updated car with id: '" + id + "'!";
        }
        else {
            return "Error! Car with id: '" + id + "' does not exist!";
        }
    }
}