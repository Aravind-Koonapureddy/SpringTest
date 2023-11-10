package com.example.demo.service;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class DataService {

    public String combineData(int customerId) {

        // Call the endpoints to get Pack 1 and Pack 2 data
        String pack1Data = callAPI("https://6466e9a7ba7110b663ab51f2.mockapi.io/api/v1/pack1");
        String pack2Data = callAPI("https://6466e9a7ba7110b663ab51f2.mockapi.io/api/v1/pack2");

        // Combine the data and format the response
        String combinedData = "{ \"id\": \"1\", \"customer id\": " + customerId +
                ", \"pack1\": " + pack1Data + ", \"pack2\": " + pack2Data + " }";

        return combinedData;
    }

    private String callAPI(String url)  {
        StringBuilder result = new StringBuilder();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet request = new HttpGet(url);

        try (CloseableHttpResponse response = httpClient.execute(request)) {
            BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line;
            while ((line = reader.readLine()) != null) {
                result.append(line);
            }
        } catch (IOException e) {
            // Log the error
            e.printStackTrace();
        }
        return result.toString();
    }
}