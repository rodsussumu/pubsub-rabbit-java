package com.rodsussumu.publisher.controllers;


import com.rodsussumu.publisher.services.RabbitMQService;
import constants.RabbitMQConstants;
import dtos.StockDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("stock")
public class StockController {
    @Autowired
    private RabbitMQService rabbitMQService;
    @PutMapping
    private ResponseEntity updateStock(@RequestBody StockDTO stockDTO) {
        this.rabbitMQService.sendMessage(RabbitMQConstants.QUEUE_STOCK, stockDTO);
        return new ResponseEntity(HttpStatus.OK);
    }
}
