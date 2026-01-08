package com.nhlstenden.groep3.digital_twin.Controller;

import com.nhlstenden.groep3.digital_twin.Model.Block;
import com.nhlstenden.groep3.digital_twin.Model.BlockType;
import com.nhlstenden.groep3.digital_twin.Repository.BlockRepository;
import com.nhlstenden.groep3.digital_twin.Repository.BlockTypeRepository;
import com.nhlstenden.groep3.digital_twin.Repository.MapRepository;
import com.nhlstenden.groep3.digital_twin.Services.PolygonService;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LinearRing;
import org.locationtech.jts.geom.Polygon;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tools.jackson.databind.JsonNode;


@RestController
@RequestMapping("/block")
public class BlockController {

    private final BlockTypeRepository blockTypeRepository;
    private final MapRepository mapRepository;
    private final BlockRepository blockRepository;
    private final PolygonService polygonService;

    public BlockController(BlockTypeRepository blockTypeRepository, MapRepository mapRepository, BlockRepository blockRepository, PolygonService polygonService) {
        this.blockTypeRepository = blockTypeRepository;
        this.mapRepository = mapRepository;
        this.blockRepository = blockRepository;
        this.polygonService = polygonService;
    }


    @PostMapping("/send")
    public ResponseEntity<String> processBlock(@RequestBody JsonNode json){
        Block block = new Block();
        System.out.println(json.get("coordinates"));
        block.setBlockType(blockTypeRepository.findByBlockCode(json.get("blockCode").asString()).orElseThrow());
        block.setMap(mapRepository.findById(json.get("mapId").asLong()).orElseThrow());
        System.out.println(polygonService.toPolygon(json.get("coordinates")));
        Polygon blockPoly = polygonService.toPolygon(json.get("coordinates"));
        block.setGeometry(blockPoly);
        block.setHeight(json.get("height").asInt());
        block.setArea((float) calculateArea(blockPoly));
        block.setVolume(block.getHeight() !=0 ? block.getArea() * block.getHeight() : block.getArea());

        if (block.getBlockType().getUnit() == BlockType.Unit.m2){
            block.setCalculated_cost(block.getArea() * block.getBlockType().getCostPerUnit());
        } else if (block.getBlockType().getUnit() == BlockType.Unit.m3) {
            block.setCalculated_cost(block.getArea() * block.getBlockType().getCostPerUnit());
            block.setCalculated_residents(block.getArea() * block.getBlockType().getResidentsPerUnit());
        }

        block.setCalculated_yield(block.getCalculated_cost() * (block.getBlockType().getYieldPercentage() / 100));

        blockRepository.save(block);

        //Maybe return block id for later editing?
        return ResponseEntity.ok("{\"test\" : \"oke\"}");
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
