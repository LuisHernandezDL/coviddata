/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.myproject.coviddata.service;
import com.myproject.coviddata.model.Province;
import com.myproject.coviddata.repository.ProvinceRepository;
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
public class ProvinceService {
    
    private static final String API_URL = "https://covid-19-statistics.p.rapidapi.com/provinces";
    
    @Autowired
    private RestTemplate restTemplate;

     @Autowired
    private ProvinceRepository provinceRepository;

     
      public void fetchAndSaveProvinces(String iso) {
          try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-RapidAPI-Key", "94e09ccf53msh3159b300b92fdecp13735djsne70a717f9990");
            headers.set("X-RapidAPI-Host", "covid-19-statistics.p.rapidapi.com");

            HttpEntity<String> entity = new HttpEntity<>(headers);

            String finalUrl = API_URL + "?iso=" + iso;

            ResponseEntity<String> response = restTemplate.exchange(
                    finalUrl,
                    HttpMethod.GET,
                    entity,
                    String.class
            );

            JSONObject json = new JSONObject(response.getBody());
            JSONArray dataArray = json.getJSONArray("data");

            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject provinceJson = dataArray.getJSONObject(i);

                Province province = new Province();
                province.setName(provinceJson.getString("province"));
                province.setIso(iso);

                provinceRepository.save(province);
            }

            System.out.println("✅ Provincias guardadas correctamente");

        } catch (Exception e) {
            System.err.println("❌ Error al consumir API de provincias: " + e.getMessage());
        }
    }
   
     
    public List<Province> getAllProvinces() {
        return provinceRepository.findAll();
    }

    public void saveProvince(Province province) {
        provinceRepository.save(province);
    }
}