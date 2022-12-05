package com.example.cursov_p.controller;

import com.example.cursov_p.entity.Car;
import com.example.cursov_p.entity.IEntity;
import com.example.cursov_p.entity.User;
import com.example.cursov_p.service.AbsService;
import com.example.cursov_p.service.CarService;
import com.example.cursov_p.service.UserService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.jetbrains.annotations.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * browser: http://localhost:8080/static/CHEVROLET%20AVEO%20BLACK.jpg
 * curl 'localhost:8080/login' -X POST -F 'name=admin' -F 'password=admin' -c cookie.txt
 * curl 'localhost:8080/login' -X POST -F 'name=user' -F 'password=user' -c cookie.txt
 * curl 'localhost:8080/logout' -b cookie.txt
 * curl 'localhost:8080/user' -X POST -H "Content-Type: application/json" -d '{"id":null,"name":"a","role":0,"password":"b"}' -b cookie.txt
 * curl 'localhost:8080/car' -X POST -H "Content-Type: application/json" -d '{"id":null,"color":"grey","name":"car4","brand":"brand2","cost":0,"image":null}' -b cookie.txt
 * curl 'localhost:8080/register?name=_aa&password=_bb' -X POST
 * curl 'localhost:8080/user?id=1' -b cookie.txt
 * curl 'localhost:8080/car?id=1'
 * curl 'localhost:8080/all/user' -b cookie.txt
 * curl 'localhost:8080/all/car'
 * curl 'localhost:8080/brand?brand=brand1'
 * curl 'localhost:8080/searchCar?name=car1'
 * curl 'localhost:8080/user' -X PUT -H "Content-Type: application/json" -d '{"id":8,"name":"aa","role":0,"password":"bb"}' -b cookie.txt
 * curl 'localhost:8080/car' -X PUT -H "Content-Type: application/json" -d '{"id":5,"color":"a","name":"car4","brand":"brand2","age":2}' -b cookie.txt
 * curl 'localhost:8080/user?id=8' -X DELETE -b cookie.txt
 * curl 'localhost:8080/car?id=5' -X DELETE -b cookie.txt
 **/
@RequiredArgsConstructor
@org.springframework.stereotype.Controller
public class Controller {
    private static final String whichParam = "/{which}";
    private static final String idName = "id";
    private static final String hasAdminRole = "hasRole('role1')";
    @NonNull
    private final UserService userService;
    @NonNull
    private final CarService carService;

    @Nullable
    private Integer tryParseInt(String numeric) { try {
        return Integer.parseInt(numeric);
    } catch (NumberFormatException ignored) { return null; } }

    @SuppressWarnings("rawtypes")
    private AbsService usersOrCars(String entity) { return Objects.requireNonNull(
        entity.equals("user")
            ? userService
            : entity.equals("car") ? carService : null
    ); }

    @SuppressWarnings("unchecked")
    private ResponseEntity<Void> doCreateOrUpdate(String which, Map<String, Object> map) {
        val user = User.fromMap(map);
        val car = Car.fromMap(map);
        if (user == null && car == null)
            throw new IllegalArgumentException();

        return new ResponseEntity<>(
            usersOrCars(which).createOrUpdate(user != null ? user : car)
                ? HttpStatus.OK
                : HttpStatus.BAD_REQUEST
        );
    }

    @PreAuthorize(hasAdminRole)
    @PostMapping(value = whichParam, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> create(@PathVariable String which, @RequestBody Map<String, Object> map)
    { return doCreateOrUpdate(which, map); }

    @PostMapping(value = "/register")
    public ResponseEntity<Void> register(@RequestParam String name, @RequestParam String password)
    { return new ResponseEntity<>(
        userService.registerUser(new User(name, User.Role.USER, password))
            ? HttpStatus.OK
            : HttpStatus.BAD_REQUEST
    ); }

    @Nullable
    private Map<String, Object> checkIsAdmin(String which, HttpServletRequest request) {
        if (!which.equals("user") || request.isUserInRole(User.Role.ADMIN.mkRole()))
            return null;

        val map = new HashMap<String, Object>();
        map.put("error", "unauthorized");
        return map;
    }

    @ResponseBody
    @GetMapping(value = whichParam, produces = MediaType.APPLICATION_JSON_VALUE)
    @Nullable
    public Map<String, Object> getEntity(
        HttpServletRequest request,
        @PathVariable String which,
        @RequestParam(name = idName) String idString
    ) {
        val checked = checkIsAdmin(which, request);
        if (checked != null) return checked;

        val id = tryParseInt(idString);
        if (id == null) return null;

        val entity = usersOrCars(which).getById(id);
        return entity == null ? null : entity.toMap();
    }

    @ResponseBody
    @GetMapping(value = "/all" + whichParam, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String, Object>> getAll(HttpServletRequest request, @PathVariable String which) {
        val checked = checkIsAdmin(which, request);
        if (checked != null)
            return Collections.singletonList(checked);

        val list = new ArrayList<Map<String, Object>>();
        for (val entity : usersOrCars(which).getAll())
            list.add(((IEntity) entity).toMap());
        return list;
    }

    @ResponseBody
    @GetMapping(value = "/brand", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Map<String, Object>> getCarsByBrand(@RequestParam String brand) {
        val list = new ArrayList<Map<String, Object>>();
        for (val car : carService.getAllByBrand(brand))
            list.add(car.toMap());
        return list;
    }

    @ResponseBody
    @GetMapping(value = "/searchCar", produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> getCarByName(@RequestParam String name) {
        val car = carService.getByName(name);
        return car == null ? null : car.toMap();
    }

    @PreAuthorize(hasAdminRole)
    @PutMapping(
        value = whichParam,
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Void> update(
        @PathVariable String which,
        @RequestBody Map<String, Object> map
    )
    { return doCreateOrUpdate(which, map); }

    @PreAuthorize(hasAdminRole)
    @DeleteMapping(whichParam)
    public ResponseEntity<Void> delete(
        @PathVariable String which,
        @RequestParam(name = idName) String idString
    ) {
        val id = tryParseInt(idString);
        if (id == null) return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        usersOrCars(which).deleteById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
