package com.jotacode.pizza.service;

import com.jotacode.pizza.persistence.entity.PizzaEntity;
import com.jotacode.pizza.persistence.repository.PizzaPagSortRepository;
import com.jotacode.pizza.persistence.repository.PizzaRepository;
import com.jotacode.pizza.service.dto.UpdatePizzaPriceDto;
import com.jotacode.pizza.service.exception.EmailApiException;
import org.hibernate.sql.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PizzaService {

    private final PizzaRepository pizzaRepository;

    private final PizzaPagSortRepository pizzaPagSortRepository;

    @Autowired
    public PizzaService(PizzaRepository pizzaRepository, PizzaPagSortRepository pizzaPagSortRepository) {
        this.pizzaRepository = pizzaRepository;
        this.pizzaPagSortRepository = pizzaPagSortRepository;
    }

    public List<PizzaEntity> getAll() {
        return pizzaRepository.findAll();
    }

    public Page<PizzaEntity> getAllWithPages(int page, int elements) {
        Pageable pageRequest = PageRequest.of(page, elements);
        return pizzaPagSortRepository.findAll(pageRequest);
    }

    //Query method
    public List<PizzaEntity> getAvailable() {
        // count all vegan pizzas
        System.out.println(this.pizzaRepository.countByVeganTrue());
        return pizzaRepository.findAllByAvailableTrueOrderByPrice();
    }

    //Query method
    public PizzaEntity getByName(String name) {
        return pizzaRepository.findFirstByAvailableTrueAndNameIgnoreCase(name).orElseThrow(() -> new RuntimeException("Pizza not found"));
    }

    //Query method
    public List<PizzaEntity> getByDescription(String ingredient) {
        return pizzaRepository.findAllByAvailableTrueAndDescriptionContainingIgnoreCase(ingredient);
    }

    //Query method
    public List<PizzaEntity> getByDescriptionNot(String ingredient) {
        return pizzaRepository.findAllByAvailableTrueAndDescriptionNotContainingIgnoreCase(ingredient);
    }

    //Query method
    public List<PizzaEntity> getCheapest(Double price){
        return pizzaRepository.findTop3ByAvailableTrueAndPriceLessThanEqualOrderByPriceAsc(price).orElseThrow(() -> new RuntimeException("No pizzas found"));
    }

    public PizzaEntity getById(Integer id) {
        return pizzaRepository.findById(id).orElse(null);
    }

    public PizzaEntity save(PizzaEntity pizza) {
        return pizzaRepository.save(pizza);
    }

    public boolean existsById(Integer id) {
        return pizzaRepository.existsById(id);
    }

    public void delete(Integer idPizza) {
        pizzaRepository.deleteById(idPizza);
    }

    //Nos asegura las transacciones en la base de datos con ACID
    @Transactional(noRollbackFor = EmailApiException.class) //Si se lanza la excepción no se hace rollback, es decir aun asi se actualizara el precio de la pizza
    public void updatePrice(UpdatePizzaPriceDto updatePizzaPriceDto) {
        pizzaRepository.updatePrice(updatePizzaPriceDto);
        this.sendEmail();
    }

    private void sendEmail(){
        //Send email
        throw new EmailApiException();
    }

}
