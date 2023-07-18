package com.hydra.dev.core.mapper;


import com.hydra.dev.domain.dto.PriceDTO;
import com.hydra.dev.domain.entity.PriceEntity;
import lombok.NonNull;
import org.springframework.stereotype.Component;

@Component
public class PriceMapper {

    public PriceDTO mapPriceDTOWIthPriceEntity(@NonNull final PriceEntity priceEntity) {
        return PriceDTO.builder()
                .productId(priceEntity.getProduct().getId())
                .chainIdentifier(priceEntity.getPriority())
                .startDate(priceEntity.getStartDate())
                .endDate(priceEntity.getEndDate())
                .price(priceEntity.getPrice())
                .build();
    }
}
