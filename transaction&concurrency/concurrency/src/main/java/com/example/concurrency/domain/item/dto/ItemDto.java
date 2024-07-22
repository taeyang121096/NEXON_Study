package com.example.concurrency.domain.item.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemDto {

    private Long itemId;

    private String name;

    private Long price;

    private Long count;

    @Builder.Default
    private LocalDateTime registerDate = LocalDateTime.now();

}
