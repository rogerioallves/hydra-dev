package com.hydra.dev.domain.dto;

import com.hydra.dev.domain.entity.PriceEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public final class PriceDTO {

    private Long productId;
    private Integer chainIdentifier;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Double price;

    public static PriceDTO createPriceDTOByPriceEntity(final PriceEntity priceEntity) {
        return builder()
                .productId(priceEntity.getProduct().getId())
                .chainIdentifier(priceEntity.getPriority())
                .startDate(priceEntity.getStartDate())
                .endDate(priceEntity.getEndDate())
                .price(priceEntity.getPrice())
                .build();
    }
}
