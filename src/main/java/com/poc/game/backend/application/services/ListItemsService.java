package com.poc.game.backend.application.services;

import com.poc.game.backend.domain.entities.Item;
import com.poc.game.backend.domain.repositories.ItemRepositoryImpl;

import java.util.List;

public class ListItemsService {
    private final ItemRepositoryImpl repository;

    public ListItemsService(){
        this.repository = new ItemRepositoryImpl();
    }

    public List<Item> execute(){
        return repository.findAll();
    }
}
