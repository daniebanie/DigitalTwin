package com.nhlstenden.groep3.digital_twin.Controller;

//import com.nhlstenden.groep3.digital_twin.Dto.MapDTO;
import com.nhlstenden.groep3.digital_twin.DTO.MapDTO;
import com.nhlstenden.groep3.digital_twin.Mapper.MapMapper;
import com.nhlstenden.groep3.digital_twin.Model.Block;
import com.nhlstenden.groep3.digital_twin.Model.BlockType;
import com.nhlstenden.groep3.digital_twin.Model.Map;
import com.nhlstenden.groep3.digital_twin.Model.Message;
import com.nhlstenden.groep3.digital_twin.Repository.BlockRepository;
import com.nhlstenden.groep3.digital_twin.Repository.BlockTypeRepository;
import com.nhlstenden.groep3.digital_twin.Repository.MapRepository;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Polygon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Enumeration;
import java.util.List;

@RestController
@RequestMapping("/map")
public class MapController {

    private final BlockRepository blockRepository;
    private Map currentMap = new Map();
    private final MapMapper mapMapper;
    private final MapRepository mapRepository;

    //For testing
    private final BlockTypeRepository blockTypeRepository;

    public MapController(MapRepository mapRepository, MapMapper mapMapper, BlockTypeRepository blockTypeRepository, BlockRepository blockRepository) {
        this.mapRepository = mapRepository;
        this.blockTypeRepository = blockTypeRepository;
        this.mapMapper = mapMapper;
        this.blockRepository = blockRepository;
    }


    @PostMapping("/create")
    public ResponseEntity<String> createMap(@RequestBody String name) {
        System.out.println("Creating Map");
        Map map = new Map();
        map.setName(name);
        mapRepository.save(map);
        System.out.println(map.getName());
        System.out.println(map.getId());
        return ResponseEntity.ok("{\"id\" : \"" + map.getId().toString() + "\"}");
    }

    //TODO: can probably be removed
    @PostMapping("/save")
    public void saveMap(@RequestBody Long mapId) {
        System.out.println("Saving Map");
        Map map = mapRepository.findById(mapId).orElseThrow();
        System.out.println(map.getBlocks());
    }

    @PostMapping("/load")
    public ResponseEntity<MapDTO> loadMap(@RequestBody String name){
        //For testing


        for (int i = 0; i < 5; i++) {
            Block block = new Block();
            block.setId((long) i);
            block.setBlockType(blockTypeRepository.findByBlockCode("A").orElse(null));
            block.setMap(currentMap);
/*
        GeometryFactory factory = new GeometryFactory();

        Coordinate[] coords = new Coordinate[] {
            new Coordinate(0, 0),
            new Coordinate(10, 0),
            new Coordinate(10, 10),
            new Coordinate(0, 10),
            new Coordinate(0, 0)
        };

        Polygon polygon = factory.createPolygon(coords);

        block.setGeometry(polygon);*/

            block.setArea(20);
            block.setVolume(30);
            block.setCalculated_cost(40);
            block.setCalculated_yield(50);
            block.setCalculated_residents(60);

            block.setHeight(i+10);
            addBlockToMap(block);
        }
        for(Block blocks: currentMap.getBlocks()){
            System.out.println(blocks.getId());
            System.out.println(blocks.getHeight());
        }
        return ResponseEntity.ok(mapMapper.toDTO(currentMap));

/*        System.out.println(name);
        Map map = mapRepository.findByName(name).orElse(null);
        System.out.println(map.getGreenPercentage());
        return ResponseEntity.ok(mapRepository.findByName(name).orElse(null));*/
    }

    public void addBlockToMap(Block block){
        currentMap.addBlock(block);
    }

    public Long getCurrentMapId() {
        return currentMap.getId();
    }
}

