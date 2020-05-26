package com.nepse.controller;

import com.nepse.model.Stock;
import com.nepse.repo.StockEntityRepository;
import com.nepse.service.DataService;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class DataController {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataController.class);

    @Autowired
    DataService dataService;

    @GetMapping("/api/v1/data-controller/extractData")
    public ResponseEntity<String> extractData() {

        List<Stock> stockList = dataService.extractStockDetailsFromNepse();
        dataService.insertStockList(stockList);
        return ResponseEntity.ok("Hello");
    }

}


