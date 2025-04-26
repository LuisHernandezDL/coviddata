/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.myproject.coviddata.config;

import com.myproject.coviddata.service.RegionService;
import com.myproject.coviddata.service.ProvinceService;
import com.myproject.coviddata.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
/**
 *
 * @author melquisedec
 */
@Component
public class AutoFetchTask {

    @Autowired
    private RegionService regionService;

    @Autowired
    private ProvinceService provinceService;

    @Autowired
    private ReportService reportService;

    // Ejecuta la tarea 15 segundos después del arranque
    @Scheduled(initialDelay = 15000, fixedDelay = Long.MAX_VALUE)
    public void autoRunCovidDataLoad() {
        System.out.println("🕒 Ejecutando hilo automático...");

        regionService.fetchAndSaveRegions();
        provinceService.fetchAndSaveProvinces("GTM");
        reportService.fetchAndSaveReports("GTM", "2022-04-16");

        System.out.println("✅ Datos cargados automáticamente por el hilo.");
    }
        }