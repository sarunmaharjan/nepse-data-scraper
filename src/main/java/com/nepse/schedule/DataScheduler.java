package com.nepse.schedule;

import com.nepse.model.Stock;
import com.nepse.service.DataService;
import com.nepse.service.impl.DataServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("data-scheduling")
public class DataScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataScheduler.class);

    @Autowired
    DataService dataService;

    @Scheduled(cron = "30 * * * * ?")
    public void extractData() {
        System.out.println(
                "Fixed rate task - " + System.currentTimeMillis() / 1000);
        List<Stock> stockList = dataService.extractStockDetailsFromNepse();
        dataService.insertStockList(stockList);
    }
}
