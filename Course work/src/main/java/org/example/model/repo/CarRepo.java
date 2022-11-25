package org.example.model.repo;

import org.example.model.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepo extends JpaRepository<Car, Integer> {

//    @Query(value = "select * from cars where id = ?1", nativeQuery = true)
//    Car getCarById(Integer id);
}
