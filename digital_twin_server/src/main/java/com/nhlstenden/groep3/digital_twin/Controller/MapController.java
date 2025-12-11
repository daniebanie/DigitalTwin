package com.nhlstenden.groep3.digital_twin.Controller;

import com.nhlstenden.groep3.digital_twin.Model.Block;
import com.nhlstenden.groep3.digital_twin.Model.Map;
import com.nhlstenden.groep3.digital_twin.Model.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/map")
public class MapController {
    Map currentMap;

    @Autowired
    public MapController(Map currentMap) {
        this.currentMap = currentMap;
    }

    public Map getCurrentMap() {
        return currentMap;
    }

    public void setCurrentMap(Map currentMap) {
        this.currentMap = currentMap;
    }

    @PostMapping("/create")
    public void  createMap(@RequestBody Message message) {
        System.out.println("Creating Map");
        currentMap.setName(message.getTitle());
        currentMap.setBlocks(null);
        //currentMap.setMapGoals(InformationController.getCurrentGoals());
        currentMap.setVerdict(null);
        System.out.println(currentMap.getName());
    }

    @PostMapping("/save")
    public void saveMap(@RequestBody Message message) {
        System.out.println("Saving Map");

    }

    @PostMapping("/load")
    public void loadMap(@RequestBody Message message) {
        System.out.println("Loading Map");

    }
}
