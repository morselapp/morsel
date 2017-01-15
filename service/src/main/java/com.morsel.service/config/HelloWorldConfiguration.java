package com.morsel.service.config;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.db.DataSourceFactory;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by kunalsingh.k on 07/01/17.
 */
public class HelloWorldConfiguration extends io.dropwizard.Configuration{

    @NotEmpty
    private String template ="OK";

    @NotEmpty
    private String defaultName ="OK";

    @JsonProperty
    public void setTemplate(String template){
        this.template = template;
    }

    @JsonProperty
    public void setDefaultName(String defaultName){
        this.defaultName = defaultName;
    }

    @JsonProperty
    public String getTemplate(){
        return template;
    }

    @JsonProperty
    public String getDefaultName(){
        return defaultName;
    }

    public DataSourceFactory getDataSourceFactory() {
        return dataSourceFactory;
    }

    private DataSourceFactory dataSourceFactory = new DataSourceFactory();

}
