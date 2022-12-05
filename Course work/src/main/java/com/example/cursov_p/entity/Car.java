package com.example.cursov_p.entity;

import lombok.*;
import org.jetbrains.annotations.Nullable;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "cars")
@Entity
public class Car implements IEntity {

    @Nullable
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Integer id;

    @NonNull
    private String color;

    @NonNull
    private String name;

    @NonNull
    private String brand;

    @NonNull
    private Integer cost;

    @Nullable
    private String image;

    @Override
    public Map<String, Object> toMap() {
        val map = new HashMap<String, Object>();
        map.put("id", getId());
        map.put("color", getColor());
        map.put("name", getName());
        map.put("brand", getBrand());
        map.put("cost", getCost());
        map.put("image", getImage());
        return map;
    }

    @Nullable
    public static IEntity fromMap(Map<String, Object> map) {
        if (map.size() != 6) return null;
        return new Car(
            (Integer) map.get("id"),
            (String) map.get("color"),
            (String) map.get("name"),
            (String) map.get("brand"),
            (Integer) map.get("cost"),
            (String) map.get("image")
        );
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Car)) return false;
        Car car = (Car) o;

        return Objects.equals(getId(), car.getId())
            && getColor().equals(car.getColor())
            && getName().equals(car.getName())
            && getBrand().equals(car.getBrand())
            && getCost().equals(car.getCost())
            && Objects.equals(getImage(), car.getImage());
    }

    @Override
    public int hashCode()
    { return Objects.hash(getId(), getColor(), getName(), getBrand(), getCost(), getImage()); }

    @Override
    public String toString() { return String.format(
        "Car(%d %s %s %s, %s, %s)",
        getId(), getColor(), getName(), getBrand(), getCost(), getImage()
    ); }
}
