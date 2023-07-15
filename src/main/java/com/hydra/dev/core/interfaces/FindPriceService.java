package com.hydra.dev.core.interfaces;

import com.hydra.dev.domain.dto.PriceDTO;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public interface FindPriceService {

    PriceDTO findPrice(LocalDateTime applicationDate,
                       Long productId,
                       Long brandId);
}
