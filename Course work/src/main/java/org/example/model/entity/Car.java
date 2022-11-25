package org.example.model.entity;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@RequiredArgsConstructor
@Getter
@Setter
@Table(name = "cars")
@Entity
public class Car implements Serializable {
    public static final String ID = "id";
    public static final String COLOR = "color";
    public static final String COST = "cost";
    public static final String BRAND = "brand";
    public static final String NAME = "name";
    @NonNull @Id private Integer id;
    @NonNull private String color;
    @NonNull private Integer cost;
    @NonNull private String brand;
    @NonNull private String name;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Car car = (Car) o;
        return getColor().equals(car.getColor())
                && getCost().equals(car.getCost())
                && getBrand().equals(car.getBrand())
                && getName().equals(car.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColor(), getCost(), getBrand(), getName());
    }

    @Override
    public String toString() {
        return getColor() + ' ' + getCost() + ' ' + getBrand() + ' ' + getName();
    }
}
