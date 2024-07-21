package com.example.concurrency.domain.user.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long userId;

    @NotBlank
    private String id;

    @NotBlank
    private String pw;

    @Builder.Default
    private LocalDateTime registerDate = LocalDateTime.now();
}
