package com.sri.stockmarket.alphavantageapiintegration.service;

import com.sri.stockmarket.alphavantageapiintegration.entity.TimeSeriesData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "timeSeriesDaily", path = "time-series-data")
public interface TimeSeriesDailyDBservice extends JpaRepository<TimeSeriesData, Long> {
    
}
