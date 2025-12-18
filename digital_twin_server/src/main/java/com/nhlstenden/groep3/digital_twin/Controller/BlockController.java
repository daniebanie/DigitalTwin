package com.nhlstenden.groep3.digital_twin.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/blocks")
@CrossOrigin(origins = "*")
public class BlockController {

    @PostMapping("/process")
    public ResponseEntity<Map<String, Object>> processBlock(@RequestBody Map<String, Object> blockData) {

        System.out.println("Block ontvangen");
        System.out.println("BlockCode: " + blockData.get("blockCode"));
        System.out.println("Hoogte: " + blockData.get("height"));

        List<Map<String, Object>> coordinates = (List<Map<String, Object>>) blockData.get("coordinates");
        System.out.println("Aantal punten: " + coordinates.size());
        System.out.println("Coördinaten:");
        coordinates.forEach(coord ->
                System.out.println("  - Lat: " + coord.get("latitude") + ", Lon: " + coord.get("longitude"))
        );

        Map<String, Object> response = new HashMap<>();
        response.put("status", "received");
        response.put("blockCode", blockData.get("blockCode"));
        response.put("pointCount", coordinates.size());

        return ResponseEntity.ok(response);
    }
}
