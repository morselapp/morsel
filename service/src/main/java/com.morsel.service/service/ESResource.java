package com.morsel.service.service;

import org.apache.storm.shade.org.eclipse.jetty.util.ajax.JSON;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.*;
import org.elasticsearch.client.Client;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.node.Node;
import org.elasticsearch.script.ScriptService;
import org.elasticsearch.search.SearchHit;
import org.json.JSONArray;

import javax.inject.Inject;
import java.util.*;

import static org.elasticsearch.node.NodeBuilder.nodeBuilder;
import static org.elasticsearch.script.ScriptType.INLINE;

/**
 * Created by kunalsingh.k on 01/04/17.
 */
public class ESResource {

    private static Node node;
    private static Client client;

    @Inject
    public ESResource(){

    }

    public Client getClient(){
        node = nodeBuilder().clusterName("elasticsearch").node();
        return node.client();
    }

    public void nodeClose(){
        node.close();
    }

    public GetResponse esDeepSearch(Client client){
        GetResponse getResponse = client.prepareGet("shop_directory", "shop", "1").execute().actionGet();
        Map<String, Object> source = getResponse.getSource();
        System.out.println("------------------------------");
        System.out.println("Index: " + getResponse.getIndex());
        System.out.println("Type: " + getResponse.getType());
        System.out.println("Id: " + getResponse.getId());
        System.out.println("Version: " + getResponse.getVersion());
        System.out.println(source);
        System.out.println("------------------------------");
        return getResponse;
    }

    public void inputDocument(Client client){


        client.prepareIndex("shop_directory", "shop", "2")
                .setSource(putJsonDocument(2,"P_20170729_234724.jpg","Ramdev Enterprises",
                        null, null,null,"mobile accesories", null,
                 "Basvannagar Main Road","Hoodi",null, null)).execute().actionGet();
    }

    public static void deleteDocument(Client client, String index, String type, String id){
        DeleteResponse response = client.prepareDelete(index, type, id).execute().actionGet();
        System.out.println("Information on the deleted document:");
        System.out.println("Index: " + response.getIndex());
        System.out.println("Type: " + response.getType());
        System.out.println("Id: " + response.getId());
        System.out.println("Version: " + response.getVersion());
    }

    public static void updateDocument(Client client, String index, String type,
                                      String id, String field, String newValue){
        Map<String, Object> updateObject = new HashMap<String, Object>();
        updateObject.put(field, newValue);
        client.prepareUpdate(index, type, id)
                .setScript("ctx._source." + field + "=" + field, ScriptService.ScriptType.FILE)
                .setScriptParams(updateObject).execute().actionGet();
    }



    public static List<Object> searchDocument(Client client, String index, String type,
                                      String field, String value){

        List<Object> resultResponse = new ArrayList<Object>();
        Map<String, String> map = new HashMap<String, String>();
        map.put(field, value);
        SearchResponse response = client.prepareSearch(index)
                .setTypes(type)
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(
                        QueryBuilders.queryStringQuery(value)
                                .field("shopType")
                                .field("description")
                                .field("shopName")
                                .field("landmark")
                                .field("address")
                                .field("area")
                                .field("location")
                )
                //.setPostFilter(QueryBuilders.rangeQuery("age").from(12).to(18))     // Filter
                .setFrom(0).setSize(60).setExplain(true)
                .get();
        SearchHit[] results = response.getHits().getHits();
        System.out.println("Current results: " + results.length);
        for (SearchHit hit : results) {
            System.out.println("------------------------------");
            Map<String,Object> result = hit.getSource();
            resultResponse.add(result);
            System.out.println(result);
        }
        return resultResponse;
    }

    public static Map<String, Object> putJsonDocument(int shopId, String imageName, String shopName, String mobile, String alternateMobile, String thirdContact,
            String shopType, String address, String landmark, String area, String description, String email){
            Map<String, Object> jsonDocument = new HashMap<String, Object>();
            jsonDocument.put("shopId", shopId);
            jsonDocument.put("imageName", imageName);
            jsonDocument.put("shopName", shopName);
            jsonDocument.put("mobile", mobile);
            jsonDocument.put("alternateMobile", alternateMobile);
            jsonDocument.put("thirdContact", thirdContact);
            jsonDocument.put("shopType", shopType);
            jsonDocument.put("address", address);
            jsonDocument.put("landmark", landmark);
            jsonDocument.put("alternateMobile", alternateMobile);
            jsonDocument.put("area", area);
            jsonDocument.put("description", description);
            jsonDocument.put("email", email);
            return jsonDocument;
    }


    public static void main(String[] args) {
        try {
            ESResource esResource = new ESResource();
            Client client = esResource.getClient();
            esResource.inputDocument(client);
            esResource.esDeepSearch(client);
            deleteDocument(client, "kodcucom", "article", "1");
            // updateDocument(client, "kodcucom", "article", "1", "tags", "big-data");
            searchDocument(client, "shop_directory", "shop", "shopType", "Parlor");
            esResource.nodeClose();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}