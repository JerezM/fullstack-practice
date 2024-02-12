package com.jerezm.practice.service;

import com.jerezm.practice.dto.ItemDTO;
import com.jerezm.practice.model.Item;
import com.jerezm.practice.utils.DTOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.jerezm.practice.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemService {

    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository){
        this.itemRepository = itemRepository;
    }

    @Transactional(readOnly = true)
    public List<ItemDTO> getItems(){
        List<Item> items = itemRepository.findAll();
        List<ItemDTO> itemsDTO = new ArrayList<>();

        items.forEach(item -> itemsDTO.add(DTOUtils.toDTO(item)));

        return itemsDTO;
    }

    @Transactional
    public ItemDTO createItem(ItemDTO itemDTO) {
        Item itemCreated = DTOUtils.fromDTO(itemDTO);
        itemCreated = itemRepository.save(itemCreated);

        return DTOUtils.toDTO(itemCreated);
    }
}
