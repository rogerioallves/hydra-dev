package com.hydra.dev.usecase;

import com.hydra.dev.core.mapper.PriceMapper;
import com.hydra.dev.infrastructure.repository.PriceRepository;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import static org.mockito.Mockito.mock;

@TestConfiguration
public class FindPriceServiceTestConfiguration {

    @Bean
    public PriceRepository priceRepository() {
        return mock(PriceRepository.class);
    }

    @Bean
    public PriceMapper priceMapper() {
        return mock(PriceMapper.class);
    }
}
