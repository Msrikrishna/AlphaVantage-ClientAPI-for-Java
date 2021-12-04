package com.sri.stockmarket.alphavantageapiintegration.service;

import com.sri.stockmarket.alphavantageapiintegration.entity.DailyData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RestResource;


@RestResource
public interface DailyDataDBservice extends JpaRepository<DailyData, Long> {
    
}
