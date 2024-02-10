package com.jerezm.practice.service;

import org.springframework.beans.factory.annotation.Autowired;
import com.jerezm.practice.repository.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService {

    private ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }


    public String getItems(){
        return "Hello World!";
    }
}
