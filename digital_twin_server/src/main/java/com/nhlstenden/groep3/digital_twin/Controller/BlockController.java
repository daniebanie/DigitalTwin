package com.nhlstenden.groep3.digital_twin.Controller;

import com.nhlstenden.groep3.digital_twin.Model.Block;
import com.nhlstenden.groep3.digital_twin.Model.BlockType;
import com.nhlstenden.groep3.digital_twin.Model.Information;
import com.nhlstenden.groep3.digital_twin.Repository.BlockRepository;
import com.nhlstenden.groep3.digital_twin.Repository.BlockTypeRepository;
import com.nhlstenden.groep3.digital_twin.Repository.MapRepository;
import com.nhlstenden.groep3.digital_twin.Services.InformationService;
import com.nhlstenden.groep3.digital_twin.Services.PolygonService;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.Polygon;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.JsonNode;

import java.awt.*;


@RestController
@RequestMapping("/block")
public class BlockController {

    private final BlockTypeRepository blockTypeRepository;
    private final MapRepository mapRepository;
    private final BlockRepository blockRepository;
    private final PolygonService polygonService;
    private final InformationService informationService;

    public BlockController(BlockTypeRepository blockTypeRepository, MapRepository mapRepository, BlockRepository blockRepository, PolygonService polygonService, InformationService informationService) {
        this.blockTypeRepository = blockTypeRepository;
        this.mapRepository = mapRepository;
        this.blockRepository = blockRepository;
        this.polygonService = polygonService;
        this.informationService = informationService;
    }

    @PostMapping("/send")
    public ResponseEntity<Information> processBlock(@RequestBody JsonNode json){
        Block block = new Block();
        System.out.println(json.get("coordinates"));
        block.setBlockType(blockTypeRepository.findByBlockCode(json.get("blockCode").asString()).orElseThrow());
        block.setMap(mapRepository.findById(json.get("mapId").asLong()).orElseThrow());
        System.out.println(polygonService.toPolygon(json.get("coordinates")));
        Polygon blockPoly = polygonService.toPolygon(json.get("coordinates"));
        block.setGeometry(blockPoly);
        block.setHeight(json.get("height").asInt());
        block.setArea((float) calculateArea(blockPoly));
        System.out.println(block.getArea());
        System.out.println(block.getBlockType().getUnit());

        block.setVolume(block.getHeight() !=0 ? block.getArea() * block.getHeight() : block.getArea());

        if (block.getBlockType().getUnit() == BlockType.Unit.m2){
            block.setCalculated_cost(block.getArea() * block.getBlockType().getCostPerUnit());
            block.setCalculated_residents((int) (block.getArea() * block.getBlockType().getResidentsPerUnit()));
        } else if (block.getBlockType().getUnit() == BlockType.Unit.m3) {
            block.setCalculated_cost(block.getArea() * block.getBlockType().getCostPerUnit());
            block.setCalculated_residents((int) (block.getArea() * block.getBlockType().getResidentsPerUnit()));
            System.out.println(block.getCalculated_residents());
        }

        block.setCalculated_yield(block.getCalculated_cost() * (block.getBlockType().getYieldPercentage() / 100));

        informationService.updateValuesFromBlock(block);

        System.out.println(block.getCalculated_cost());
        System.out.println(informationService.getCurrentInformation().getCurrentCost());

        blockRepository.save(block);

        return ResponseEntity.ok(informationService.getCurrentInformation());
    }


    public double calculateArea(Polygon polygon){
        Coordinate[] coords = polygon.getCoordinates();
        double area = 0.0;
        float r = 6378137; //Earth's radius in meters

        for (int i = 0; i < coords.length -1; i++) {
            double lon1 = Math.toRadians(coords[i].x);
            double lat1 = Math.toRadians(coords[i].y);
            double lon2 = Math.toRadians(coords[i+1].x);
            double lat2 = Math.toRadians(coords[i+1].y);

            area += (lon2 - lon1) * (2 + Math.sin(lat1) + Math.sin(lat2));
        }

        return Math.abs(area * r * r / 2.0);
    }
}
