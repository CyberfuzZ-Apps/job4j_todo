package ru.job4j.hibernate.cars;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Класс Car
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Entity
@Table(name = "car")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "engine_id", foreignKey = @ForeignKey(name = "ENGINE_ID_FK"))
    private Engine engine;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "history_owner", joinColumns = {
            @JoinColumn(name = "driver_id", nullable = false,
            updatable = false)},
    inverseJoinColumns = {
            @JoinColumn(name = "car_id", nullable = false,
            updatable = false)})
    private Set<Driver> drivers = new HashSet<>();

    public Car() {
    }

    public static Car of(String name, Engine engine) {
        Car car = new Car();
        car.name = name;
        car.engine = engine;
        return car;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Set<Driver> getDrivers() {
        return drivers;
    }

    public void setDrivers(Set<Driver> drivers) {
        this.drivers = drivers;
    }

    public void addDriver(Driver driver) {
        this.drivers.add(driver);
    }

    @Override
    public String toString() {
        return "Car{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", engine=" + engine
                + ", drivers=" + drivers
                + '}';
    }
}
