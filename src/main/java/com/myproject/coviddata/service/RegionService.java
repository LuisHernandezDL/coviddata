/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.myproject.coviddata.service;
import com.myproject.coviddata.model.Region;
import com.myproject.coviddata.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.*;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author ROCK
 */
@Service
public class RegionService {
    
   private static final String API_URL = "https://covid-19-statistics.p.rapidapi.com/regions";

   
    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private RegionRepository regionRepository;

     public void fetchAndSaveRegions() {
        try {
            // Headers con la API Key
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-RapidAPI-Key", "94e09ccf53msh3159b300b92fdecp13735djsne70a717f9990");
            headers.set("X-RapidAPI-Host", "covid-19-statistics.p.rapidapi.com");

            HttpEntity<String> entity = new HttpEntity<>(headers);

            // Petición GET
            ResponseEntity<String> response = restTemplate.exchange(
                    API_URL,
                    HttpMethod.GET,
                    entity,
                    String.class
            );

            JSONObject json = new JSONObject(response.getBody());
            JSONArray dataArray = json.getJSONArray("data");

            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject regionJson = dataArray.getJSONObject(i);

                Region region = new Region();
                region.setName(regionJson.getString("name"));
                region.setIso(regionJson.getString("iso"));
                region.setProvince(regionJson.optString("province", null));

                regionRepository.save(region);
            }

            System.out.println("✅ Regiones guardadas correctamente");

        } catch (Exception e) {
            System.err.println("❌ Error al consumir la API: " + e.getMessage());
        }
    }
    
    
    
    public List<Region> getAllRegions() {
        return regionRepository.findAll();
    }

    public void saveRegion(Region region) {
        regionRepository.save(region);
    }
}