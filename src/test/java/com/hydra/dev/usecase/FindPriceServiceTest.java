package com.hydra.dev.usecase;

import com.github.javafaker.Faker;
import com.hydra.dev.core.interfaces.FindPriceService;
import com.hydra.dev.core.mapper.PriceMapper;
import com.hydra.dev.domain.dto.PriceDTO;
import com.hydra.dev.domain.entity.BrandEntity;
import com.hydra.dev.domain.entity.PriceEntity;
import com.hydra.dev.domain.entity.ProductEntity;
import com.hydra.dev.infrastructure.repository.PriceRepository;
import com.hydra.dev.usecase.impl.FindPriceServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.clearInvocations;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {
        FindPriceServiceTestConfiguration.class
})
public class FindPriceServiceTest {

    @Autowired
    private PriceRepository priceRepository;

    @Autowired
    private PriceMapper priceMapper;

    private FindPriceServiceImpl findPriceService;
    private Faker faker = new Faker();

    @BeforeEach
    public void initialize() {
        findPriceService = new FindPriceServiceImpl(priceRepository, priceMapper);
        clearInvocations(priceMapper);
    }

    @Test
    public void whenParametersIsCorrectThenPriceDTOIsNotNull() {
        final var applicationDate = convertDateToLocalDateTime(faker.date().past(30, TimeUnit.DAYS));;
        final var productId = faker.number().numberBetween(0L, 100L);
        final var brandId = faker.number().numberBetween(0L, 100L);
        final var pageRequest = PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "priority"));

        final var priceEntity = new PriceEntity();
        final var pagedResponse = new PageImpl<>(Arrays.asList(priceEntity));
        when(priceRepository.findPriceByPeriodAndProductIdAndBrandId(
                applicationDate, productId, brandId, pageRequest
        )).thenReturn(pagedResponse);

        when(priceMapper.mapPriceDTOWIthPriceEntity(priceEntity)).thenReturn(new PriceDTO());

        final var priceDTO = findPriceService.findPrice(applicationDate, productId, brandId);
        assertNotNull(priceDTO);
        verify(priceMapper).mapPriceDTOWIthPriceEntity(priceEntity);
    }

    @Test
    public void whenParametersIsCorrectThenPriceDTOIsNull() {
        final var applicationDate = convertDateToLocalDateTime(faker.date().past(30, TimeUnit.DAYS));;
        final var productId = faker.number().numberBetween(0L, 100L);
        final var brandId = faker.number().numberBetween(0L, 100L);
        final var pageRequest = PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "priority"));

        final var priceEntity = new PriceEntity();
        final var pagedResponse = new PageImpl<PriceEntity>(Collections.emptyList());
        when(priceRepository.findPriceByPeriodAndProductIdAndBrandId(
                applicationDate, productId, brandId, pageRequest
        )).thenReturn(pagedResponse);

        when(priceMapper.mapPriceDTOWIthPriceEntity(priceEntity)).thenReturn(new PriceDTO());

        final var priceDTO = findPriceService.findPrice(applicationDate, productId, brandId);
        assertNull(priceDTO);
        verify(priceMapper, times(0)).mapPriceDTOWIthPriceEntity(priceEntity);
    }

    private LocalDateTime convertDateToLocalDateTime(final Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }
}
