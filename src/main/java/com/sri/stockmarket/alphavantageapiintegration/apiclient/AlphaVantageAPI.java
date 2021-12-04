package com.sri.stockmarket.alphavantageapiintegration.apiclient;

import com.fasterxml.jackson.databind.JsonNode;
import com.sri.stockmarket.alphavantageapiintegration.entity.DailyData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.Map;

    @Component
    @Transactional
    @Repository
    public class AlphaVantageAPI {
        Logger logger = LoggerFactory.getLogger(com.sri.stockmarket.alphavantageapiintegration.apiclient.AlphaVantageAPI.class);
        @PersistenceContext
        private EntityManager entityManager;
        static final String apiKey = "";   //Give your API key here
        static final String uri_dailyData = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol={symbol_id}&outputsize={outputSize_id}&apikey=" + apiKey;


        public AlphaVantageAPI() {
        }

        public Object fetchDailyData(String symbol, String outputSize) {
            RestTemplate restTemplate = new RestTemplate();
            Map<String, String> params = new HashMap();
            params.put("symbol_id", symbol);
            params.put("outputSize_id", outputSize);
            Object result = restTemplate.getForObject("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol={symbol_id}&outputsize={outputSize_id}&apikey=" + apiKey, Object.class, params);
            this.logger.debug("Received data from Alpha Vantage" + result.toString());
            return result;
        }

        public JsonNode fetchJSONData(String symbol, String outputSize) {
            RestTemplate restTemplate = new RestTemplate();
            Map<String, String> params = new HashMap();
            params.put("symbol_id", symbol);
            params.put("outputSize_id", outputSize);
            ResponseEntity<JsonNode> result = restTemplate.getForEntity("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol={symbol_id}&outputsize={outputSize_id}&apikey=" + apiKey, JsonNode.class, params);
            this.logger.debug("Received data from Alpha Vantage" + result.toString());
            return (JsonNode)result.getBody();
        }

        public String persistDailyData(String symbol, String outputSize) {
            RestTemplate restTemplate = new RestTemplate();
            Map<String, String> params = new HashMap();
            params.put("symbol_id", symbol);
            params.put("outputSize_id", outputSize);
            DailyData result = (DailyData)restTemplate.getForObject("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol={symbol_id}&outputsize={outputSize_id}&apikey="+apiKey, DailyData.class, params);
            this.logger.debug("Received data from Alpha Vantage" + result.toString());
            this.entityManager.persist(result);
            return "Success";
        }
    }

