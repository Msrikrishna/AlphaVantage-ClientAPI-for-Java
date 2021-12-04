package com.sri.stockmarket.alphavantageapiintegration.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Access;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.ToString;

@Entity
@Data
@Table(name = "stock_meta_data")
public class StockMetaData {

    @Id
    @Column(name = "id" )
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(optional = false)
    @JoinColumn(name = "daily_data_id") 
    @JsonIgnore
    @ToString.Exclude
    private DailyData dailyData;

    @Column(name = "information")
    @JsonProperty(value = "1. Information")
    private String information;

    @Column(name = "symbol")
    @JsonProperty(value = "2. Symbol")
    private String symbol;

    @Column(name = "lastrefreshed")
    @JsonProperty(value = "3. Last Refreshed")
    private Date lastRefreshed;

    @Column(name = "outputsize")
    @JsonProperty(value = "4. Output Size")
    private String outputSize;

    @Column(name = "timezone")
    @JsonProperty(value = "5. Time Zone")
    private String timeZone;  
}
