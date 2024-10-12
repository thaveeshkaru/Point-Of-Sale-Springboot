package com.ijse.posproject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ijse.posproject.dto.ItemReqDto;
import com.ijse.posproject.entity.Category;
import com.ijse.posproject.entity.Item;
import com.ijse.posproject.service.CategoryService;
import com.ijse.posproject.service.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@CrossOrigin(origins = "*")
public class ItemController {

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ItemService itemService;

    @PostMapping("/items")
    public ResponseEntity<?> createItem (@RequestBody ItemReqDto itemReqDto) {
        
        try {
            Item newItem = new Item();
            newItem.setItemName(itemReqDto.getItemName());
            newItem.setPrice(itemReqDto.getPrice());
            newItem.setDescription(itemReqDto.getDescription());
            Category category = categoryService.getCategoryById(itemReqDto.getCategoryId());
            newItem.setCategory(category);
            Item createItem = itemService.createItem(newItem);
            return ResponseEntity.status(201).body(createItem);
            
            
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
        
    }

    @GetMapping("/items")
    public ResponseEntity<List<Item>> getAllItems() {
        List<Item> Items = itemService.getAllItems();
        return ResponseEntity.status(201).body(Items);
    }

    @PutMapping("items/{itemCode}")
    public ResponseEntity<?> updateItem(@PathVariable Long itemCode, @RequestBody ItemReqDto itemReqDto) {

        try {
            Item item = new Item();
            item.setItemName(itemReqDto.getItemName());
            item.setDescription(itemReqDto.getDescription());
            item.setPrice(itemReqDto.getPrice());
            Category category = categoryService.getCategoryById(itemReqDto.getCategoryId());
    
            if(category!=null){
                item.setCategory(category);
                Item updatedItem = itemService.updateItem(itemCode,item);
                return ResponseEntity.status(200).body(updatedItem);
            }else{
                return ResponseEntity.status(400).body(null);
            }
        } catch (Exception e) {
            return ResponseEntity.status(400).body(null);

        }

    }

    @DeleteMapping("items/{itemCode}")
    public void deleteItem(@PathVariable Long itemCode){
        itemService.deleteItem(itemCode);
    }
    
}
