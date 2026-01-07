package com.nhlstenden.groep3.digital_twin.Mapper;

import com.nhlstenden.groep3.digital_twin.DTO.BlockDTO;
import com.nhlstenden.groep3.digital_twin.DTO.BlockLoadDTO;
import com.nhlstenden.groep3.digital_twin.DTO.MapDTO;
import com.nhlstenden.groep3.digital_twin.Model.Block;
import com.nhlstenden.groep3.digital_twin.Model.Map;
import com.nhlstenden.groep3.digital_twin.Repository.BlockRepository;
import org.locationtech.jts.io.geojson.GeoJsonWriter;
import org.springframework.stereotype.Component;
import tools.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapMapper{

    private final BlockRepository blockRepository;
    private final BlockMapper blockMapper;

    GeoJsonWriter writer = new GeoJsonWriter();
    ObjectMapper mapper = new ObjectMapper();


    public MapMapper(BlockRepository blockRepository, BlockMapper blockMapper) {
        this.blockRepository = blockRepository;
        this.blockMapper = blockMapper;
    }

    public MapDTO toDTO(Map map) {
        MapDTO dto = new MapDTO();
        dto.setId(map.getId());
        dto.setName(map.getName());
        List<BlockLoadDTO> blocks = new ArrayList<>();
        for (Block block : map.getBlocks()){
            BlockLoadDTO blockDTO = new BlockLoadDTO();
            blockDTO.setBlockType(block.getBlockType());
            blockDTO.setMapId(block.getMap().getId());
            blockDTO.setGeometry(mapper.readTree(writer.write(block.getGeometry())));
            blockDTO.setHeight(block.getHeight());
            blocks.add(blockDTO);
        }
        dto.setBlocks(blocks);
        dto.setLivability(map.getLivability());
        dto.setCost(map.getCost());
        dto.setResidents(map.getResidents());
        dto.setWorkplaces(map.getWorkplaces());
        dto.setParkingSpots(map.getParkingSpots());
        dto.setYield(map.getYield());
        dto.setGreenPercentage(map.getGreenPercentage());
        dto.setWorkplacePercentage(map.getWorkplacePercentage());
        return dto;
    }

}
