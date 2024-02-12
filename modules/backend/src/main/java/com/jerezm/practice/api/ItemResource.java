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

    @PostMapping
    public ResponseEntity<ItemDTO> createItem(@RequestBody ItemDTO itemDTO) {
        ItemDTO itemDTOCreated = itemService.createItem(itemDTO);
        return new ResponseEntity<>(itemDTOCreated, HttpStatus.CREATED);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<ItemDTO> updateContentItem(@PathVariable("itemId") Long itemId, @RequestBody ItemDTO itemDTO) {
        ItemDTO itemDTOUpdated = itemService.updateContentItem(itemId, itemDTO.getContent());

        HttpStatus httpStatus = (itemDTOUpdated != null) ? HttpStatus.OK : HttpStatus.NOT_FOUND;
        return new ResponseEntity<>(itemDTOUpdated, httpStatus);
    }

    @PutMapping("/{itemId}/check")
    public ResponseEntity<ItemDTO> checkItem(@PathVariable("itemId") Long itemId) {
        ItemDTO itemDTOUpdated = itemService.checkItem(itemId);

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
