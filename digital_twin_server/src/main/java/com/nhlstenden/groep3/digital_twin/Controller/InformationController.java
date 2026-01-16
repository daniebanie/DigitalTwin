package com.nhlstenden.groep3.digital_twin.Controller;


import com.nhlstenden.groep3.digital_twin.Model.Block;
import com.nhlstenden.groep3.digital_twin.Model.BlockType;
import com.nhlstenden.groep3.digital_twin.Model.Information;
import com.nhlstenden.groep3.digital_twin.Services.InformationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/info")
public class InformationController {
    private final InformationService informationService;

    public InformationController(InformationService informationService) {
        this.informationService = informationService;
    }

    @GetMapping("/get")
    public ResponseEntity<Information> getInformation(){
        System.out.println(informationService.getCurrentInformation().getCurrentCost());
        return ResponseEntity.ok(informationService.getCurrentInformation());
    }
}
