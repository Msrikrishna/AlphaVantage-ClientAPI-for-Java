package com.sri.stockmarket.alphavantageapiintegration.entity;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.sri.stockmarket.alphavantageapiintegration.deserializer.DailyDataCustomDeserializer;

import lombok.Data;

@JsonDeserialize(using = DailyDataCustomDeserializer.class) //Using a custom deserializer as the input JSON is not well formatted
@Entity
@Data
@Table(name = "daily_data")
public class DailyData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "dailyData")
    @JsonProperty("Meta Data")
    private StockMetaData metaData;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "dailyData")
    @JsonProperty("Time Series (Daily)")
    private List<TimeSeriesData> timedata = new ArrayList<TimeSeriesData>();   
}
