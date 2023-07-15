package com.hydra.dev.application.controller;


import com.hydra.dev.core.interfaces.FindPriceService;
import com.hydra.dev.domain.dto.PriceDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/price")
@RequiredArgsConstructor
public class PriceController {

    private final FindPriceService findPriceService;

    @GetMapping("/find")
    public PriceDTO findPriceToApply(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") final LocalDateTime applicationDate,
            @RequestParam final Long productId,
            @RequestParam final Long brandId) {

        return findPriceService.findPrice(applicationDate, productId, brandId);
    }
}
