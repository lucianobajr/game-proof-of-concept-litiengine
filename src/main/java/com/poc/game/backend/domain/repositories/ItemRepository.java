package com.poc.game.backend.domain.repositories;

import com.poc.game.backend.domain.dtos.CreateItemDTO;
import com.poc.game.backend.domain.entities.Item;

import java.util.List;

public interface ItemRepository {
    List<Item> findAll();
    void create(CreateItemDTO createItemDTO);
    Item findById(int itemId);
    void delete(int itemId);
}
