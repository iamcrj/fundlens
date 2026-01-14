package com.iamcrj.fundlens.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class NseIpoService {

    @Value("${nse.api.base-url}")
    private String baseUrl;

    private final RestTemplate restTemplate;

    private static final long CACHE_TTL_MS = 15 * 60 * 60 * 1000; // 15 hours

    private ResponseEntity<String> cachedResponse;
    private long lastFetchTime = 0;

    public NseIpoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public synchronized ResponseEntity<String> fetchCurrentIpos() {

        long now = System.currentTimeMillis();

        // ✅ Return cached response if within 15 hours
        if (cachedResponse != null && (now - lastFetchTime) < CACHE_TTL_MS) {
            return cachedResponse;
        }

        // ❌ Cache expired → Call NSE
        String url = baseUrl + "/api/ipo-current-issue";

        HttpHeaders headers = new HttpHeaders();
        headers.set("User-Agent", "Mozilla/5.0");
        headers.set("Accept", "application/json");
        headers.set("Referer", "https://www.nseindia.com/");
        headers.set("Accept-Language", "en-US,en;q=0.9");

        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                entity,
                String.class
        );

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<String> finalResponse =
                new ResponseEntity<>(response.getBody(), responseHeaders, HttpStatus.OK);

        // ✅ Update cache
        cachedResponse = finalResponse;
        lastFetchTime = now;

        return finalResponse;
    }
}
