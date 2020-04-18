package com.javalabs.eclient.controller;

import com.javalabs.eclient.GetResponse;
import com.javalabs.eclient.entity.Car;
import com.javalabs.eclient.repository.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.ServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

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

    /*
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }*/

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
    GetResponse<String> addCar(@Valid @RequestBody Car car) throws ParseException {
        carsRepository.save(car);
        return new GetResponse<String>("Successfully added new car!", instanceId);
    }

    @PostMapping("/update")
    public @ResponseBody
    GetResponse<String> updateCar(@Valid @RequestBody Car newCar) {

        Optional<Car> car = carsRepository.findById(newCar.getId());
        if (car.isPresent()) {
            Car updatedCar = car.get();

            updatedCar.setPrice(newCar.getPrice());
            updatedCar.setColor(newCar.getColor());
            updatedCar.setHorsepower(newCar.getHorsepower());
            updatedCar.setFuel(newCar.getFuel());
            updatedCar.setGearbox(newCar.getGearbox());
            updatedCar.setManufacturer(newCar.getManufacturer());
            updatedCar.setManufactureYear(newCar.getManufactureYear());
            updatedCar.setModel(newCar.getModel());

            carsRepository.save(updatedCar);

            return new GetResponse<String>("Successfully updated car with id: '" + newCar.getId() + "'!", instanceId);
        }
        else {
            return new GetResponse<String>("Error! Car with id: '" + newCar.getId() + "' does not exist!", instanceId);
        }
    }
}