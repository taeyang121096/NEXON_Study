package com.example.concurrency.domain.item.service;

import com.example.concurrency.domain.item.dto.ItemDto;
import com.example.concurrency.domain.item.entity.Item;
import com.example.concurrency.domain.item.repo.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository itemRepository;

    public Item save(ItemDto itemDto) {
        return itemRepository.save(Item.builder()
                        .name(itemDto.getName())
                        .price(itemDto.getPrice())
                        .count(itemDto.getCount())
                        .registerDate(itemDto.getRegisterDate())
                .build());
    }

    public Item findByName(String name) {
        return itemRepository.findByName(name);
    }

    public Item findByNameWithXLock(String name) {
        return itemRepository.findByNameWithXLock(name);
    }


    public void deleteAllItems(){
        itemRepository.deleteAllItems();
    }
}
