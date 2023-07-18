package com.hydra.dev.core;

import com.github.javafaker.Faker;
import com.hydra.dev.core.mapper.PriceMapper;
import com.hydra.dev.domain.entity.BrandEntity;
import com.hydra.dev.domain.entity.PriceEntity;
import com.hydra.dev.domain.entity.ProductEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@Import(PriceMapper.class)
public class PriceMapperTest {

    @Autowired
    private PriceMapper priceMapper;

    private PriceEntity priceEntity;

    @BeforeEach
    public void initialize() {
        buildPriceEntity();
    }

    private void buildPriceEntity() {
        final var faker = new Faker();
        priceEntity = new PriceEntity();
        priceEntity.setId(faker.number().numberBetween(0L, 100L));
        priceEntity.setPriceList(faker.number().numberBetween(0L, 100L));
        priceEntity.setPriority(faker.number().numberBetween(0, 100));
        priceEntity.setPrice(faker.number().randomDouble(2, 0, 1000));
        priceEntity.setCurrency(faker.currency().code());

        final var startDate = convertDateToLocalDateTime(faker.date().past(90, TimeUnit.DAYS));
        priceEntity.setStartDate(startDate);

        final var endDate = convertDateToLocalDateTime(faker.date().future(120, TimeUnit.DAYS));
        priceEntity.setEndDate(endDate);

        final var brand = new BrandEntity();
        brand.setId(faker.number().numberBetween(0L, 100L));
        brand.setName(faker.name().name());
        priceEntity.setBrand(brand);

        final var product = new ProductEntity();
        product.setId(faker.number().numberBetween(0L, 100L));
        product.setName(faker.name().name());
        priceEntity.setProduct(product);

    }

    private LocalDateTime convertDateToLocalDateTime(final Date date) {
        return date.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDateTime();
    }

    @Test
    public void whenPriceEntityIsValidThenConvertToPriceDtoWithSuccess() {
        final var priceDTO = priceMapper.mapPriceDTOWIthPriceEntity(priceEntity);
        assertNotNull(priceDTO);
        assertEquals(priceEntity.getProduct().getId(), priceDTO.getProductId());
        assertEquals(priceEntity.getPriority(), priceDTO.getChainIdentifier());
        assertEquals(priceEntity.getStartDate(), priceDTO.getStartDate());
        assertEquals(priceEntity.getEndDate(), priceDTO.getEndDate());
        assertEquals(priceEntity.getPrice(), priceDTO.getPrice());
    }

    @Test
    public void whenPriceEntityIsNullThenThrowError() {
        final var thrown = Assertions.assertThrows(NullPointerException.class, () -> {
            priceMapper.mapPriceDTOWIthPriceEntity(null);
        });

        assertEquals("priceEntity is marked non-null but is null", thrown.getMessage());
    }
}
