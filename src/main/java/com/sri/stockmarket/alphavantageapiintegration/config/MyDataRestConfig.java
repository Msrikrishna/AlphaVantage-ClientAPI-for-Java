package com.sri.stockmarket.alphavantageapiintegration.config;

import com.sri.stockmarket.alphavantageapiintegration.entity.StockMetaData;
import com.sri.stockmarket.alphavantageapiintegration.entity.TimeSeriesData;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
       
        HttpMethod[] unsupported = {HttpMethod.DELETE};

        config.getExposureConfiguration()
        .forDomainType(StockMetaData.class)
        .withItemExposure((metadata, httpMethods) -> httpMethods.disable(unsupported))
        .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(unsupported));  
        
        config.getExposureConfiguration()
        .forDomainType(TimeSeriesData.class)
        .withItemExposure((metadata, httpMethods) -> httpMethods.disable(unsupported))
        .withCollectionExposure((metadata, httpMethods) -> httpMethods.disable(unsupported));  
}

}
