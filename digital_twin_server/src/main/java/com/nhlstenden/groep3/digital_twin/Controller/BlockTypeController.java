package com.nhlstenden.groep3.digital_twin.Controller;

import com.nhlstenden.groep3.digital_twin.Model.BlockType;
import com.nhlstenden.groep3.digital_twin.Services.BlockTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * De REST API endpoint voor Blocktype
 *
 * Handelt de HTTP requests van de frontend, converteert Java naar JSON (en vice versa) en scheidt web lagen
 *
 * API Endpoints:
 * GET  /api/blocktypes      > Alle BlockTypes ophalen
 * GET  /api/blocktypes/{blockCode} > Specifiek BlockType ophalen
 */

@RestController
@RequestMapping("/api/blocktypes") // URL voor alle endpoints in deze controller
@CrossOrigin(origins = "*") // Staat requests toe van alle origins
public class BlockTypeController {

    /** Service wordt geïnjecteerd voor toegang
     * Controller gebruikt nooit direct de repository maar volgt lagenmodel
     */
    @Autowired
    private BlockTypeService blockTypeService;

    /**
     * Haalt alle BLockTypes op en stuurt ze als JSON naar de client
     *
     * http://localhost:8080/api/blocktypes wordt gefetched en gebruikt in loadBuildingBlocks() functie
     * Data wordt gebruikt om bouwblok selectieknoppen te genereren voor het tekenen
     *
     * @return ResponseEntity met List van BlockTypes en HTTP 200 status
     */
    @GetMapping
    public ResponseEntity<List<BlockType>> getAllBlockTypes() {
        List<BlockType> blockTypes = blockTypeService.getAllBlockTypes();
        return ResponseEntity.ok(blockTypes);
    }

    /**
     * Haalt één BlockType op op basis van blockCode
     *
     * http://localhost:8080/api/blocktypes/${blockCode} wordt gefetched en kan worden opgeroepen om één BlockType op te halen
     * @param blockCode De code van het gezochte bouwblok
     * @return BlockType als JSON of null
     */
    @GetMapping("{blockCode}")
    public BlockType getBlockType(@PathVariable String blockCode) {
        // @PathVariable bindt de {blockCode} uit de URL aan de parameter
        BlockType blockType = blockTypeService.getBlockTypeByCode(blockCode);
        // Log een melding als BlockType niet gevonden is
        if (blockType == null) {
            System.out.println("BlockType not found: " + blockCode);
        }
        return blockType;
    }
}