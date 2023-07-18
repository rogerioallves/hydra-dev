package com.hydra.dev.domain.dto;

import com.hydra.dev.domain.entity.PriceEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class PriceDTO {

    private Long productId;
    private Integer chainIdentifier;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double price;
}
