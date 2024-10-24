package com.ijse.posproject.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ijse.posproject.entity.Item;
import com.ijse.posproject.repository.ItemRepository;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemRepository itemRepository;

    @Override
    public Item createItem(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public List<Item> getAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item updateItem(Long itemCode, Item item) {
        Item existingItem = itemRepository.findById(itemCode).orElse(null);
        if(existingItem==null){
            return null;
        }else{
            existingItem.setItemName(item.getItemName());
            existingItem.setDescription(item.getDescription());
            existingItem.setPrice(item.getPrice());
            existingItem.setCategory(item.getCategory());
            return itemRepository.save(existingItem);
        } 
    }

    @Override
    public void deleteItem(Long itemCode) {
        itemRepository.deleteById(itemCode);
    }

    @Override
    public Item getItemById(Long itemcode) {
        return itemRepository.findById(itemcode).orElse(null);
    }


}
