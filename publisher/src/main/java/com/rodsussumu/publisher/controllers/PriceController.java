package com.rodsussumu.publisher.controllers;

import com.rodsussumu.publisher.constants.RabbitMQConstants;
import com.rodsussumu.publisher.dtos.PriceDTO;
import com.rodsussumu.publisher.services.RabbitMQService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("price")
public class PriceController {

    @Autowired
    private RabbitMQService rabbitMQService;

    @PutMapping
    private ResponseEntity updatePrice(@RequestBody PriceDTO priceDTO) {
        this.rabbitMQService.sendMessage(RabbitMQConstants.QUEUE_PRICE, priceDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
}
