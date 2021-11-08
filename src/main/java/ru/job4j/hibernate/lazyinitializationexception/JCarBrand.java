package ru.job4j.hibernate.lazyinitializationexception;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Класс JCarBrand
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Entity
@Table(name = "j_car_brand")
public class JCarBrand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(mappedBy = "jCarBrand")
    private List<JCarModel> modelList = new ArrayList<>();

    public static JCarBrand of(String name) {
        JCarBrand jCarBrand = new JCarBrand();
        jCarBrand.name = name;
        return jCarBrand;
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

    public List<JCarModel> getModelList() {
        return modelList;
    }

    public void setModelList(List<JCarModel> modelList) {
        this.modelList = modelList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JCarBrand jCarBrand = (JCarBrand) o;
        return id == jCarBrand.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "JCarBrand{"
                + "id=" + id
                + ", name='" + name + '\''
                + '}';
    }
}
