package com.ijse.posproject.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.ijse.posproject.entity.Item;

@Service
public interface ItemService {

    Item createItem(Item item);
    List<Item> getAllItems();
    Item updateItem(Long itemCode,Item item);
    void deleteItem(Long itemCode);
    Item getItemById(Long itemcode);
}
