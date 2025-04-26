
package com.myproject.coviddata.controller;
import com.myproject.coviddata.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegionController {
            @Autowired
    private RegionService regionService;

    @GetMapping("/regions/load")
    public String loadRegions() {
        regionService.fetchAndSaveRegions();
        return "✅ Regiones guardadas en la base de datos.";
    }
}
