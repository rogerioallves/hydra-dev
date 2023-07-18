package com.hydra.dev.usecase.impl;

import com.hydra.dev.core.interfaces.FindPriceService;
import com.hydra.dev.core.mapper.PriceMapper;
import com.hydra.dev.domain.dto.PriceDTO;
import com.hydra.dev.infrastructure.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class FindPriceServiceImpl implements FindPriceService {

    private final PriceRepository priceRepository;
    private final PriceMapper priceMapper;

    @Override
    public PriceDTO findPrice(
            final LocalDateTime applicationDate,
            final Long productId,
            final Long brandId) {

        final var pageRequest = PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "priority"));
        final var priceEntity = priceRepository.findPriceByPeriodAndProductIdAndBrandId(
                applicationDate, productId, brandId, pageRequest);

        if (priceEntity.isEmpty()) {
            return null;
        }

        return priceMapper.mapPriceDTOWIthPriceEntity(priceEntity.getContent().get(0));
    }
}
