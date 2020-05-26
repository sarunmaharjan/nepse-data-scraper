package com.nepse.service;

import com.nepse.model.Stock;

import java.util.List;

public interface DataService {

    public List<Stock> extractStockDetailsFromNepse();

    public void insertStockList(List<Stock> stockList);

    public void insertStock(Stock stock);
}
