package com.javalabs.eclient.repository;

import com.javalabs.eclient.entity.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarsRepository extends CrudRepository<Car, Integer> {

}