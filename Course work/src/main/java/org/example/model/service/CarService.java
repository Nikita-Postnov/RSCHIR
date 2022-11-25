package org.example.model.service;

import lombok.val;
import org.example.model.entity.Car;
import org.example.model.repo.CarRepo;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.example.model.entity.Car.*;

@Service
public class CarService {
    @Autowired private CarRepo repo;

    private Map<String, String> carToMap(Car car) {
        val map = new HashMap<String, String>();
        map.put(ID, car.getId().toString());
        map.put(COLOR, car.getColor());
        map.put(COST, car.getCost().toString());
        map.put(BRAND, car.getBrand());
        map.put(NAME, car.getName());
        return map;
    }

    @Nullable
    public Map<String, String> getCarById(String id) {
        val $car = repo.findById(Integer.parseInt(id));
        if ($car.isEmpty()) return null;
        return carToMap($car.get());
    }

    public List<Map<String, String>> gatAllCars() {
        val list = new ArrayList<Map<String, String>>();
        for (val car : repo.findAll())
            list.add(carToMap(car));
        return list;
    }

    public boolean updateCar(Car car) {
        try { repo.save(car); }
        catch (Exception e) { return false; }
        return true;
    }

    public boolean deleteCar(String id) {
        val car = repo.findById(Integer.parseInt(id));
        if (car.isEmpty()) return false;
        repo.delete(car.get());
        return true;
    }

    private void addCars() {
        repo.save(new Car(
            1,
            "red",
            750000,
            "MITSUBISHI",
            "LANCER"
        ));
        repo.save(new Car(
            2,
            "black",
            430000,
            "CHEVROLET",
            "AVEO"
        ));
        repo.save(new Car(
            3,
            "red",
            870000,
            "FORD",
            "EXPLORER"
        ));
        repo.save(new Car(
            4,
            "black",
            1530000,
            "KIA",
            "OPTIMA"
        ));
        repo.save(new Car(
            5,
            "white",
            2550000,
            "MAZDA",
            "6(|||(GJ))"
        ));
        repo.save(new Car(
            6,
            "black",
            1530000,
            "NISSAN",
            "TEANA"
        ));
        repo.save(new Car(
            7,
            "green",
            1015000,
            "TOYOTA",
            "COROLLA (X|)"
        ));
        repo.save(new Car(
            8,
            "pink",
            340000,
            "MISSAN",
            "ALMERA CLASSIC |"
        ));
        repo.save(new Car(
            9,
            "red",
            1015000,
            "MERCEDES-BENZ",
            "G-КЛАСС, AMG, BRABUS"
        ));
        repo.save(new Car(
            10,
            "white",
            75000000,
            "FERRARI",
            "SF90"
        ));
        repo.save(new Car(
            11,
            "black",
            75000000,
            "FERRARI",
            "SF90"
        ));
        repo.save(new Car(
            12,
            "neon",
            320000,
            "TOYOTA",
            "MARK ||, V|| (X90)"
        ));
        repo.save(new Car(
            13,
            "white",
            1425000,
            "KIA",
            "OPTIMA |V"
        ));
        repo.save(new Car(
            14,
            "black",
            50000000,
            "LAMBORGHINI",
            "AVENTADOR (SVJ|)"
        ));
        repo.save(new Car(
            15,
            "red",
            1680000,
            "HONDA",
            "CIVIC X"
        ));
    }
}
