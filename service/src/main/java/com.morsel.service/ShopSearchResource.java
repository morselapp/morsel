package com.morsel.service;
import com.codahale.metrics.annotation.Timed;
import com.morsel.service.service.ESResource;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.SearchHit;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

@Path("/shop")
@Produces(MediaType.APPLICATION_JSON)

public class ShopSearchResource {
    private String template;
    private String defaultName;

    public ShopSearchResource(String template, String defaultName){
        this.defaultName = defaultName;
        this.template = template;
    }

    @GET
    @Timed
    public String sayHello(){
        return "hello "+defaultName;
    }

    @GET
    @Path("/search/{data}")
    public List shopSearch(@PathParam("data") String data){
        ESResource esResource = new ESResource();
        Client client = esResource.getClient();
        List hits= esResource.searchDocument(client, "shop_directory","shop", "area",data);
        esResource.nodeClose();
        return hits;
    }

    @GET
    @Path("/input/{data}")
    public String shopInjest(@PathParam("data") String data){
       /* ESResource esResource = new ESResource();
        Client client = esResource.getClient();
        esResource.inputDocument(client);
        esResource.nodeClose(); */

        return "hi "+data;
    }
}
