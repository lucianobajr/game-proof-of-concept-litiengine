package com.poc.game.application.services;

import com.poc.game.domain.dtos.CreateItemDTO;
import com.poc.game.domain.repositories.ItemRepositoryImpl;
import com.poc.game.utils.NameNormalizer;

public class CreateItemService {
    private final ItemRepositoryImpl repository;

    public CreateItemService(){
        this.repository = new ItemRepositoryImpl();
    }

    public void execute(String name, Integer value) throws Exception {
        // regra de negócio para o campo valor
        verifyIfValueIsValid(value);

        // regra de negócio para o campo name
        verifyIfNameIsValid(name);

        // nome normalizado
        String normalizedName = NameNormalizer.normalizeName(name);

        CreateItemDTO createItemDTO = new CreateItemDTO();
        createItemDTO.setName(normalizedName);
        createItemDTO.setValue(value);

        // cria  o item
        repository.create(createItemDTO);
    }

    private void verifyIfValueIsValid(int value) throws Exception{
        if(value <= 0){
            throw  new Exception("The value must be greater than 0");
        }
    }

    private void verifyIfNameIsValid(String name) throws Exception {
        if (name == null || name.trim().isEmpty()) {
            throw new Exception("The name must not be empty");
        }
    }
}
