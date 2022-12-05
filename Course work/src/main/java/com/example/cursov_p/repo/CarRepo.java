package com.example.cursov_p.repo;

import com.example.cursov_p.entity.Car;
import lombok.NonNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Scope(ConfigurableBeanFactory.SCOPE_SINGLETON)
@Repository
public interface CarRepo extends JpaRepository<Car, Integer> {

    @Query(value = "select * from cars where brand = ?1", nativeQuery = true)
    @NonNull
    List<Car> getAllByBrand(String brand);

    @Query(value = "select * from cars where name = ?1", nativeQuery = true)
    @Nullable
    Car getByName(String name);
}
