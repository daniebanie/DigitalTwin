package com.nhlstenden.groep3.digital_twin.Controller;

//import com.nhlstenden.groep3.digital_twin.Dto.MapDTO;
import com.nhlstenden.groep3.digital_twin.Model.Block;
import com.nhlstenden.groep3.digital_twin.Model.Map;
import com.nhlstenden.groep3.digital_twin.Model.Message;
import com.nhlstenden.groep3.digital_twin.Repository.MapRepository;
import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Enumeration;
import java.util.List;

@RestController
@RequestMapping("/map")
public class MapController {

    private Map currentMap = new Map();
    private final MapRepository mapRepository;

    public MapController(MapRepository mapRepository) {
        this.mapRepository = mapRepository;
    }


    @PostMapping("/create")
    public void  createMap(@RequestBody String name) {
        System.out.println("Creating Map");
        System.out.println(name);
        currentMap = new Map();
        currentMap.setName(name);
    }

    @PostMapping("/save")
    public void saveMap(@RequestBody Message message) {
        System.out.println("Saving Map");
        System.out.println(currentMap.getName());
        mapRepository.save(currentMap);
    }

    @GetMapping("/load")
    public Message loadMap(@RequestBody Message message) {

        return new Message("test", "test");
    }
}
