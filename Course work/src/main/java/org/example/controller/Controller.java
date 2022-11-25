package org.example.controller;

import lombok.val;
import org.example.model.entity.Car;
import org.example.model.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.Map;

@org.springframework.stereotype.Controller
public class Controller {
    private static final String JSON = "application/json";
    @Autowired private CarService carService;

    @GetMapping(value = "/get_car", produces = JSON) // localhost:8080/car
    public Map<String, String> getCar(@RequestParam String id) {
        return carService.getCarById(id);
//        return String.format("{\"id\":%d, \"color\":\"%s\", \"cost\":%d, \"brand\":\"%s\", \"name\":\"%s\"}",
//                car.getId(), car.getColor(), car.getCost(), car.getBrand(), car.getName());
    }

    @GetMapping(value = "/get_cars", produces = JSON)
    public List<Map<String, String>> getAllCars() {
        return carService.gatAllCars();
    }

    @PostMapping("/save_car") // insert and update
    public ResponseEntity<Void> saveCar(HttpServletRequest request, @RequestParam Car car) {
        return carService.updateCar(car)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PostMapping("/delete_car")
    public ResponseEntity<Void> deleteCar(HttpServletRequest request, @RequestParam String id) {
        return carService.deleteCar(id)
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
