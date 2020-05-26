package com.nepse.service.impl;

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
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class DataServiceImpl implements DataService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataServiceImpl.class);

    @Autowired
    StockEntityRepository stockEntityRepository;

    @Override
    public List<Stock> extractStockDetailsFromNepse() {
        LOGGER.info("extractStockDetailsFromNepse()" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        Document document = null;
        List<Stock> stockList = new ArrayList<Stock>();
        try {
            document = Jsoup.connect("http://www.nepalstock.com/todaysprice?_limit=500").timeout(5000).get();
            Elements tableData = document.select("tr");

            for (Element element : tableData) {
                if (element.childrenSize() == 10) {
                    Elements tdData = element.children();
                    tdData.size();
                    if (!String.valueOf(tdData).contains("<td>S.N.</td>")) {
                        List<String> values = new ArrayList<String>();
                        for (Element element1 : tdData.select("td")) {
                            values.add(element1.text());
                        }
                        String sn = values.get(0);
                        String companyName = values.get(1);
                        String numberOfTransaction = values.get(2);
                        String maxPrice = values.get(3);
                        String minPrice = values.get(4);
                        String closingPrice = values.get(5);
                        String tradedShares = values.get(6);
                        String totalAmount = values.get(7);
                        String previousClosingPrice = values.get(8);
                        String differenceAmount = values.get(9);
                        Stock stock = Stock.builder().sn(Integer.valueOf(sn)).companyName(companyName).numberOfTransaction(Integer.valueOf(numberOfTransaction)).maxPrice(Double.valueOf(maxPrice)).minPrice(Double.valueOf(minPrice)).closingPrice(Double.valueOf(closingPrice)).tradedShares(Double.valueOf(tradedShares)).totalAmount(Double.valueOf(totalAmount)).previousClosingPrice(Double.valueOf(previousClosingPrice)).differenceAmount(Double.valueOf(differenceAmount)).build();
                        stockList.add(stock);
                    }
                }
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        return stockList;
    }

    @Override
    public void insertStockList(List<Stock> stockList) {
        LOGGER.info("insertStockList()" + LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        stockEntityRepository.saveAll(stockList);
        stockEntityRepository.flush();
    }

    @Override
    public void insertStock(Stock stock) {
        stockEntityRepository.saveAndFlush(stock);
    }
}
