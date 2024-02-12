package com.jerezm.practice.service;

import com.jerezm.practice.dto.ItemDTO;
import com.jerezm.practice.model.Item;
import com.jerezm.practice.utils.DTOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import com.jerezm.practice.repository.ItemRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

    @Transactional
    public ItemDTO updateContentItem(Long itemId, String itemContent) {
        Optional<Item> itemToUpdate = itemRepository.findById(itemId);
        ItemDTO itemDTO;

        if (itemToUpdate.isPresent()) {
            Item item = itemToUpdate.get();

            if (!item.getContent().equals(itemContent)) {
                item.setContent(itemContent);
            }

            item = itemRepository.save(item);
            itemDTO = DTOUtils.toDTO(item);
        } else {
            itemDTO = null;
        }

        return itemDTO;
    }

    @Transactional
    public ItemDTO checkItem(Long itemId) {
        Optional<Item> itemToUpdate = itemRepository.findById(itemId);
        ItemDTO itemDTO;

        if (itemToUpdate.isPresent()) {
            Item item = itemToUpdate.get();
            item.setDone(!item.isDone());

            item = itemRepository.save(item);
            itemDTO = DTOUtils.toDTO(item);
        } else {
            itemDTO = null;
        }

        return itemDTO;
    }

    @Transactional
    public ItemDTO deleteItemById(Long itemId) {
        Optional<Item> item = itemRepository.findById(itemId);
        ItemDTO itemDeletedDTO = null;

        if (item.isPresent()) {
            Item itemToDelete = item.get();
            itemRepository.deleteById(itemId);
            itemDeletedDTO = DTOUtils.toDTO(itemToDelete);
        }

        return itemDeletedDTO;
    }
}
