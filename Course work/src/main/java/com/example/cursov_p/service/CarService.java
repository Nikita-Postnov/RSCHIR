package com.example.cursov_p.service;

import com.example.cursov_p.entity.Car;
import com.example.cursov_p.repo.CarRepo;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.List;

@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Service
public class CarService extends AbsService<Car, CarRepo> {

    public CarService(@NonNull CarRepo repo) { super(repo); }

    @NonNull
    public List<Car> getAllByBrand(String brand) { return repo.getAllByBrand(brand); }

    @Nullable
    public Car getByName(String name) { return repo.getByName(name); }
}
