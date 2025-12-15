package com.nhlstenden.groep3.digital_twin.Controller;

import com.nhlstenden.groep3.digital_twin.Model.Block;
import com.nhlstenden.groep3.digital_twin.Model.BlockType;
import com.nhlstenden.groep3.digital_twin.Model.Map;
import com.nhlstenden.groep3.digital_twin.Model.Message;
import com.nhlstenden.groep3.digital_twin.Services.BlockTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/blocktypes")
@CrossOrigin(origins = "*")
public class BlockTypeController {

    @Autowired
    private BlockTypeService blockTypeService;

    @GetMapping
    public ResponseEntity<List<BlockType>> getAllBlockTypes() {
        List<BlockType> blockTypes = blockTypeService.getAllBlockTypes();
        return ResponseEntity.ok(blockTypes);
    }

    @GetMapping("{blockCode}")
    public BlockType getBlockType(@PathVariable String blockCode) {
        BlockType blockType = blockTypeService.getBlockTypeByCode(blockCode);
        if (blockType == null) {
            System.out.println("BlockType not found: " + blockCode);
        }
        return blockType;
    }
}