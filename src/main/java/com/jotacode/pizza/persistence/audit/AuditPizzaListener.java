package com.jotacode.pizza.persistence.audit;

import com.jotacode.pizza.persistence.entity.PizzaEntity;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PostUpdate;
import jakarta.persistence.PreRemove;
import org.apache.logging.log4j.util.internal.SerializationUtil;
import org.springframework.util.SerializationUtils;

public class AuditPizzaListener {

    private PizzaEntity currentPizza;

    //METODO QUE SE EJECUTA DESPUES DE QUE SE CARGA LA ENTIDAD
    @PostLoad
    public void postLoad(PizzaEntity pizzaEntity) {
        System.out.println("POST LOAD");
        //COn esto no se sobrecarga la memoria
        this.currentPizza = SerializationUtils.clone(pizzaEntity);
    }

    //METODO QUE SE EJECUTA UNA VEZ QUE SE MODIFICA O SE CREA UNA ENTIDAD
    @PostPersist
    @PostUpdate
    public void onPostPersist(PizzaEntity pizzaEntity) {
        System.out.println("POST PERSIST/UPDATE");
        System.out.println("Old value: " + this.currentPizza.toString());
        System.out.println("New Value: " + pizzaEntity.toString());
    }

    //METODO QUE SE EJECUTA ANTES DE QUE SE ELIMINE UNA ENTIDAD
    @PreRemove
    public void onPreDelete(PizzaEntity pizzaEntity) {
        System.out.println("Pizza deleted: " + pizzaEntity.toString());
    }
}
