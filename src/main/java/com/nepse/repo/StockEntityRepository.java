package com.nepse.repo;

import com.nepse.model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockEntityRepository extends JpaRepository<Stock, Long> {
}
