package com.example.spring.service;
import com.example.spring.config.FinnhubConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StockPriceService {
    @Autowired
    private FinnhubConfig finnhubConfig;

    public String getStockPrice(String symbol) throws Exception {
        String url = finnhubConfig.getBaseUrl() + "/quote?symbol=" + symbol + "&token=" + finnhubConfig.getApiKey();

        try (CloseableHttpClient client = HttpClients.createDefault()) {
            HttpGet request = new HttpGet(url);
            try (CloseableHttpResponse response = client.execute(request)) {
                String jsonResponse = EntityUtils.toString(response.getEntity());
                return jsonResponse;
            }
        }
    }
}
