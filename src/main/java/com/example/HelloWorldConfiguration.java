package com.example;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by kunalsingh.k on 07/01/17.
 */
public class HelloWorldConfiguration extends io.dropwizard.Configuration{

    @NotEmpty
    private String template;

    @NotEmpty
    private String defaultName;

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

}
