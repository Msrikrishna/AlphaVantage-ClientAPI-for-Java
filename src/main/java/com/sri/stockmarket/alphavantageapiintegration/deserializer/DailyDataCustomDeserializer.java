package com.sri.stockmarket.alphavantageapiintegration.deserializer;

import java.io.IOException;

import java.util.ArrayList;
import java.util.Date;

import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sri.stockmarket.alphavantageapiintegration.apiclient.AlphaVantageAPI;
import com.sri.stockmarket.alphavantageapiintegration.entity.DailyData;
import com.sri.stockmarket.alphavantageapiintegration.entity.StockMetaData;
import com.sri.stockmarket.alphavantageapiintegration.entity.TimeSeriesData;
import com.sri.stockmarket.alphavantageapiintegration.service.DailyDataDBservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.core.type.TypeReference;

//Tells how to handle json to entity conversion
@Component
public class DailyDataCustomDeserializer extends JsonDeserializer<DailyData> {


    @Override
    public DailyData deserialize(JsonParser p, DeserializationContext ctxt)
            throws IOException, JsonProcessingException {

        DailyData data = new DailyData();
        //data.setId((long)11200);

        JsonNode node = p.readValueAsTree();
        TreeNode metaDataNode = node.get("Meta Data");
        TreeNode timeSeriesDataNode = node.get("Time Series (Daily)");

        ObjectMapper objectMapper = new ObjectMapper();
        StockMetaData metaData = objectMapper.treeToValue(metaDataNode, StockMetaData.class);

        List<TimeSeriesData> timeSeriesData = new ArrayList<TimeSeriesData>();
        Map<String, Object> result = objectMapper.convertValue(timeSeriesDataNode,
                new TypeReference<Map<String, Object>>() {
                });

        for (Map.Entry<String, Object> entry : result.entrySet()) {

            TimeSeriesData temp = new TimeSeriesData();
            temp.setDateOfRecord((java.sql.Date.valueOf(entry.getKey())));
            for (Map.Entry<String, Object> entryDeep : ((Map<String, Object>) entry.getValue()).entrySet()){
                
                if(entryDeep.getKey() == "1. open"){temp.setOpen(Double.valueOf((String) entryDeep.getValue()));}
                if(entryDeep.getKey() == "2. high"){temp.setHigh(Double.valueOf((String) entryDeep.getValue()));}
                if(entryDeep.getKey() == "3. low"){temp.setLow(Double.valueOf((String) entryDeep.getValue()));}
                if(entryDeep.getKey() == "4. close"){temp.setClose(Double.valueOf((String) entryDeep.getValue()));}
                if(entryDeep.getKey() == "5. volume"){temp.setVolume(Integer.valueOf((String) entryDeep.getValue()));}
            }
            temp.setDailyData(data); //Add original reference to dailydata for every time stamped data
            timeSeriesData.add(temp); 
        }
        metaData.setDailyData(data); //Set original reference to DailyData
        data.setMetaData(metaData);
        data.setTimedata(timeSeriesData);

        return data;
    }
    
}



