/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.myproject.coviddata.service;
import com.myproject.coviddata.model.Report;
import com.myproject.coviddata.repository.ReportRepository;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;

/**
 *
 * @author ROCK
 */
@Service
public class ReportService {
    private static final String API_URL = "https://covid-19-statistics.p.rapidapi.com/reports";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ReportRepository reportRepository;

    public void fetchAndSaveReports(String iso, String date) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.set("X-RapidAPI-Key", "94e09ccf53msh3159b300b92fdecp13735djsne70a717f9990");
            headers.set("X-RapidAPI-Host", "covid-19-statistics.p.rapidapi.com");

            HttpEntity<String> entity = new HttpEntity<>(headers);
            String fullUrl = API_URL + "?iso=" + iso + "&date=" + date;

            ResponseEntity<String> response = restTemplate.exchange(
                    fullUrl,
                    HttpMethod.GET,
                    entity,
                    String.class
            );

            JSONObject json = new JSONObject(response.getBody());
            JSONArray dataArray = json.getJSONArray("data");

            for (int i = 0; i < dataArray.length(); i++) {
                JSONObject reportJson = dataArray.getJSONObject(i);

                Report report = new Report();
                report.setIso(reportJson.getJSONObject("region").optString("iso", null));
                report.setProvince(reportJson.optString("province", null));
                report.setDate(LocalDate.parse(reportJson.getString("date")));
                report.setConfirmed(reportJson.optInt("confirmed", 0));
                report.setDeaths(reportJson.optInt("deaths", 0));
                report.setRecovered(reportJson.optInt("recovered", 0));

                reportRepository.save(report);
            }

            System.out.println("✅ Reportes guardados correctamente");

        } catch (Exception e) {
            System.err.println("❌ Error al consumir API de reportes: " + e.getMessage());
            e.printStackTrace();
        }
    }

}