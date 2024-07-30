package com.jotacode.pizza.web.controller;

import com.jotacode.pizza.persistence.entity.PizzaEntity;
import com.jotacode.pizza.service.PizzaService;
import com.jotacode.pizza.service.dto.UpdatePizzaPriceDto;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
public class    PizzaController {

    private final PizzaService pizzaService;

    @Autowired
    public PizzaController(PizzaService pizzaService) {
        this.pizzaService = pizzaService;
    }

    @GetMapping
    public ResponseEntity<List<PizzaEntity>> getAll() {
        return ResponseEntity.ok(pizzaService.getAll());
    }

    //Get with paging
    @GetMapping("/pages")
    public ResponseEntity<Page<PizzaEntity>> getAllWithPages(@RequestParam(defaultValue = "0") int page,
                                                             @RequestParam(defaultValue = "8") int elements) {
        return ResponseEntity.ok(pizzaService.getAllWithPages(page, elements));
    }

    @GetMapping("/{idPizza}")
    public ResponseEntity<PizzaEntity> getById(@PathVariable Integer idPizza) {
        return ResponseEntity.ok(pizzaService.getById(idPizza));
    }

    //Query method
    @GetMapping("/available")

    public ResponseEntity<List<PizzaEntity>> getAvailable() {
        return ResponseEntity.ok(pizzaService.getAvailable());
    }

    //Query method
    @GetMapping("/available/{name}")
    public ResponseEntity<PizzaEntity> getByName(@PathVariable String name) {
        return ResponseEntity.ok(pizzaService.getByName(name));
    }

    //Query method
    @GetMapping("/available/description/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getByDescription(@PathVariable String ingredient) {
        return ResponseEntity.ok(pizzaService.getByDescription(ingredient));
    }

    //Query method
    @GetMapping("/available/description/not/{ingredient}")
    public ResponseEntity<List<PizzaEntity>> getByDescriptionNot(@PathVariable String ingredient) {
        return ResponseEntity.ok(pizzaService.getByDescriptionNot(ingredient));
    }

    //Query method
    @GetMapping("/available/cheapest/{price}")
    public ResponseEntity<List<PizzaEntity>> getCheapest(@PathVariable Double price) {
        return ResponseEntity.ok(pizzaService.getCheapest(price));
    }

    @PostMapping
    public ResponseEntity<PizzaEntity> save(@RequestBody PizzaEntity pizza) {
        if (pizza.getIdPizza() == null || !pizzaService.existsById(pizza.getIdPizza())) {
            return ResponseEntity.ok(pizzaService.save(pizza));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<PizzaEntity> update(@RequestBody PizzaEntity pizza) {
        if (pizza.getIdPizza() != null && pizzaService.existsById(pizza.getIdPizza())){
            return ResponseEntity.ok(pizzaService.save(pizza));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{idPizza}")
    public ResponseEntity<Void> delete(@PathVariable Integer idPizza) {
        if (pizzaService.existsById(idPizza)) {
            pizzaService.delete(idPizza);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/updatePrice/")
    public ResponseEntity<Void> updatePrice(@RequestBody UpdatePizzaPriceDto updatePizzaPriceDto) {
       if (pizzaService.existsById(updatePizzaPriceDto.getPizzaId())){
           pizzaService.updatePrice(updatePizzaPriceDto);
           return ResponseEntity.ok().build();
       }else {
           return ResponseEntity.badRequest().build();
       }
    }

}
