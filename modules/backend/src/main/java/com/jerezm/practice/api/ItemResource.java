package com.jerezm.practice.api;

import com.jerezm.practice.dto.ItemDTO;
import com.jerezm.practice.model.Item;
import com.jerezm.practice.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("items")
public class ItemResource {
    private final ItemService itemService;

    @Autowired
    public ItemResource(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ResponseEntity<List<ItemDTO>> getItems() {
        List<ItemDTO> itemsDTO = itemService.getItems();

        return new ResponseEntity<>(itemsDTO, HttpStatus.OK);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<ItemDTO> getItemById(@PathVariable("itemId") Long itemId) {
        ItemDTO itemDTO = itemService.getItemById(itemId);

        HttpStatus httpStatus = (itemDTO != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(itemDTO, httpStatus);
    }

    @PostMapping
    public ResponseEntity<ItemDTO> createItem(@RequestBody ItemDTO itemDTO) {
        ItemDTO itemDTOCreated = itemService.createItem(itemDTO);
        return new ResponseEntity<>(itemDTOCreated, HttpStatus.CREATED);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<ItemDTO> updateItem(@PathVariable("itemId") Long itemId, @RequestBody ItemDTO itemDTO) {
        ItemDTO itemDTOUpdated = itemService.updateItem(itemId, itemDTO);

        HttpStatus httpStatus = (itemDTOUpdated != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(itemDTOUpdated, httpStatus);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<ItemDTO> deleteItemById(@PathVariable("itemId") Long itemId) {
        ItemDTO itemDTO = itemService.deleteItemById(itemId);

        HttpStatus httpStatus = (itemDTO != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(itemDTO, httpStatus);
    }
}
