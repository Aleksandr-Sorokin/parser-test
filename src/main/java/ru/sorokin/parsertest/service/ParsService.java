package ru.sorokin.parsertest.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Date;
@Service
public class ParsService {

    public String parseFeatures() {
        String url = "https://map.novo-sibirsk.ru/elitegis/rest/services/maps/ritual/MapServer/1/query";
        //String url = "https://map.novo-sibirsk.ru/elitegis/rest/services/maps/ritual/MapServer/1/layers";
        //String url = "https://map.novo-sibirsk.ru/elitegis/rest/services/maps/";

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet request = new HttpGet(url);
        JsonArray features = null;
        try {
            HttpResponse response = httpClient.execute(request);
            HttpEntity entity = response.getEntity();

            if (entity != null) {
                String jsonString = EntityUtils.toString(entity);

                Gson gson = new Gson();
                JsonObject jsonObject = gson.fromJson(jsonString, JsonObject.class);
                System.out.println(jsonObject);
                String geometryType = jsonObject.get("geometryType").getAsString();
                features = jsonObject.getAsJsonArray("features");
                System.out.println(features.size());
                System.out.println(features.get(999));
                System.out.println(new Date(410205600000l));
            }
            return features.get(999).toString();
        } catch (ClientProtocolException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
