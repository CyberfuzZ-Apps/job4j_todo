package ru.job4j.hibernate.cars;

import javax.persistence.*;

/**
 * Класс Engine
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Entity
@Table(name = "engine")
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String model;

    public Engine() {
    }

    public static Engine of(String model) {
        Engine engine = new Engine();
        engine.model = model;
        return engine;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Engine{"
                + "id=" + id
                + ", model='" + model + '\''
                + '}';
    }
}
