package com.jotacode.pizza.persistence.repository;

import com.jotacode.pizza.persistence.entity.PizzaEntity;
import com.jotacode.pizza.service.dto.UpdatePizzaPriceDto;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PizzaRepository extends ListCrudRepository<PizzaEntity, Integer> {

    List<PizzaEntity> findAllByAvailableTrueOrderByPrice();

    //El first o (top) by hace que solo devuelva un objeto y no una lista y lo limita a 1 en el query
    Optional<PizzaEntity> findFirstByAvailableTrueAndNameIgnoreCase(String name);

    List<PizzaEntity> findAllByAvailableTrueAndDescriptionContainingIgnoreCase(String description);

    List<PizzaEntity> findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(String ingredient);

    int countByVeganTrue();

    //Se puede decir cuantos registros se quieren devolver con el top y se puede ordenar por el campo que se quiera
    Optional<List<PizzaEntity>> findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(Double price);

    @Query(value = "UPDATE pizza SET price = :#{#newPizzaPrice.newPrice} WHERE id_pizza =:#{#newPizzaPrice.pizzaId}", nativeQuery = true)
    @Modifying //Para hacer operaciones de insert, update y delete
    void updatePrice(@Param("newPizzaPrice")UpdatePizzaPriceDto newPizzaPrice);


}
