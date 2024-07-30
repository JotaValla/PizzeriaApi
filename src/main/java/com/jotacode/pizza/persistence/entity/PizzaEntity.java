package com.jotacode.pizza.persistence.entity;

import com.jotacode.pizza.persistence.audit.AuditPizzaListener;
import com.jotacode.pizza.persistence.audit.AuditableEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Objects;

@Entity
@EntityListeners({AuditingEntityListener.class, AuditPizzaListener.class})
@Table(name = "pizza")
@Getter
@Setter
@NoArgsConstructor
public class PizzaEntity extends AuditableEntity implements java.io.Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pizza", nullable = false)
    private Integer idPizza;

    @Column(nullable = false, length = 45, unique = true)
    private String name;

    @Column(nullable = false, length = 150)
    private String description;

    @Column(nullable = false, columnDefinition = "DECIMAL(5,2)")
    private Double price;

    @Column(columnDefinition = "TINYINT")
    private Boolean vegetarian;

    @Column(columnDefinition = "TINYINT")
    private Boolean vegan;

    @Column(columnDefinition = "TINYINT", nullable = false)
    private Boolean available;

    @Override
    public String toString() {
        return "PizzaEntity{" +
               "idPizza=" + idPizza +
               ", name='" + name + '\'' +
               ", description='" + description + '\'' +
               ", price=" + price +
               ", vegetarian=" + vegetarian +
               ", vegan=" + vegan +
               ", available=" + available +
               '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PizzaEntity that = (PizzaEntity) o;
        return Objects.equals(idPizza, that.idPizza) && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(price, that.price) && Objects.equals(vegetarian, that.vegetarian) && Objects.equals(vegan, that.vegan) && Objects.equals(available, that.available);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPizza, name, description, price, vegetarian, vegan, available);
    }
}
