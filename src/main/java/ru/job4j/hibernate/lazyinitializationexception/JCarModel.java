package ru.job4j.hibernate.lazyinitializationexception;

import javax.persistence.*;
import java.util.Objects;

/**
 * Класс JCarModel
 *
 * @author Evgeniy Zaytsev
 * @version 1.0
 */
@Entity
@Table(name = "j_car_model")
public class JCarModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "j_car_brand_id")
    private JCarBrand jCarBrand;

    public static JCarModel of(String name, JCarBrand brand) {
        JCarModel jCarModel = new JCarModel();
        jCarModel.name = name;
        jCarModel.jCarBrand = brand;
        return jCarModel;
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

    public JCarBrand getjCarBrand() {
        return jCarBrand;
    }

    public void setjCarBrand(JCarBrand jCarBrand) {
        this.jCarBrand = jCarBrand;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JCarModel jCarModel = (JCarModel) o;
        return id == jCarModel.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "JCarModel{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", jCarBrand=" + jCarBrand
                + '}';
    }
}
