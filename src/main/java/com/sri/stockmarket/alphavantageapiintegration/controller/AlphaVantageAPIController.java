package com.sri.stockmarket.alphavantageapiintegration.controller;

import java.util.Collection;
import java.util.Map;

import javax.persistence.Entity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.sri.stockmarket.alphavantageapiintegration.apiclient.AlphaVantageAPI;
import com.sri.stockmarket.alphavantageapiintegration.deserializer.DailyDataCustomDeserializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlphaVantageAPIController {

    Logger log = LoggerFactory.getLogger(AlphaVantageAPIController.class);

    @Autowired
    AlphaVantageAPI api;

    @Autowired
    DailyDataCustomDeserializer jsonMap;

    @GetMapping(path = "/alpha-api/{symbol}")
    public Object showdailyTimeSeriesData(@PathVariable String symbol) {

        return api.fetchDailyData(symbol, "compact");
    }

    @GetMapping(path = "/alpha-api/persist/{symbol}/{outputSize}")
    public void persistDailyData(@PathVariable String symbol, @PathVariable String outputSize)
            {
        
        api.persistDailyData(symbol, outputSize);

    }

    @GetMapping(path = "/deserialize")
    public void deserializeDailyData() {
        
           api.persistDailyData("IBM", "concise");

    }


    
}
