package com.sri.stockmarket.alphavantageapiintegration.service;

import com.sri.stockmarket.alphavantageapiintegration.entity.StockMetaData;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;



@RepositoryRestResource(collectionResourceRel = "stockMetaData", path = "meta-data") //Adds automatic REST endpoint handling for this class
public interface StockMetaDataDBservice extends JpaRepository<StockMetaData, Long> {
    
}
