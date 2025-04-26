/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.myproject.coviddata.controller;
import com.myproject.coviddata.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @author melquisedec
 */
@RestController
public class ReportController {
        
    @Autowired
    private ReportService reportService;

    @GetMapping("/reports/load")
    public String loadReports(@RequestParam String iso, @RequestParam String date) {
        reportService.fetchAndSaveReports(iso, date);
        return "✅ Reportes cargados correctamente.";
    }
}
