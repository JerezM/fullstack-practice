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

    @Transactional(readOnly = true)
    public ItemDTO getItemById(Long id) {
        Optional<Item> item = itemRepository.findById(id);

        return item.map(DTOUtils::toDTO).orElse(null);
    }

    @Transactional
    public ItemDTO createItem(ItemDTO itemDTO) {
        Item itemCreated = DTOUtils.fromDTO(itemDTO);
        itemCreated = itemRepository.save(itemCreated);

        return DTOUtils.toDTO(itemCreated);
    }

    @Transactional
    public ItemDTO updateItem(Long itemId, ItemDTO itemDTO) {
        Optional<Item> itemToUpdate = itemRepository.findById(itemId);

        if (itemToUpdate.isPresent()) {
            Item item = itemToUpdate.get();

            if (!item.getContent().equals(itemDTO.getContent())) {
                item.setContent(itemDTO.getContent());
            }

            if (item.isDone() != itemDTO.isDone()) {
                item.setDone(itemDTO.isDone());
            }

            item = itemRepository.save(item);
            itemDTO = DTOUtils.toDTO(item);
        } else {
            itemDTO = null;
        }


        return itemDTO;
    }
}
