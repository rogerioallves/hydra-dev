package com.hydra.dev.infrastructure.repository;

import com.hydra.dev.domain.entity.PriceEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

    @Query("   SELECT p " +
            "     FROM PriceEntity p " +
            "    WHERE p.brand.id = :brandId " +
            "      AND p.product.id = :productId " +
            "      AND :period BETWEEN p.startDate AND p.endDate " +
            " ORDER BY p.priority DESC ")
    Page<PriceEntity> findPriceByPeriodAndProductIdAndBrandId(
            LocalDateTime period, Long productId, Long brandId, Pageable pageable);
}
