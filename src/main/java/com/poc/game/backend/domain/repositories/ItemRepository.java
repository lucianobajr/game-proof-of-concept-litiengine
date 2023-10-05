package com.poc.game.domain.repositories;

import com.poc.game.domain.dtos.CreateItemDTO;
import com.poc.game.domain.entities.Item;

import java.util.List;

public interface ItemRepository {
    List<Item> findAll();
    void create(CreateItemDTO createItemDTO);
    Item findById(int itemId);
    void delete(int itemId);
}
