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

    private final MapMapper mapMapper;
    private final MapRepository mapRepository;


    public MapController(MapRepository mapRepository, MapMapper mapMapper) {
        this.mapRepository = mapRepository;
        this.mapMapper = mapMapper;
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
        return ResponseEntity.ok(mapMapper.toDTO(mapRepository.findByName(name).orElseThrow()));
    }

}

