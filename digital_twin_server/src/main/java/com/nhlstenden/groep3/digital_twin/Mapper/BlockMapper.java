package com.nhlstenden.groep3.digital_twin.Mapper;

import com.nhlstenden.groep3.digital_twin.DTO.BlockDTO;
import com.nhlstenden.groep3.digital_twin.Model.Block;
import com.nhlstenden.groep3.digital_twin.Repository.MapRepository;
import com.nhlstenden.groep3.digital_twin.Services.PolygonService;
import org.springframework.boot.json.JsonWriter;
import org.springframework.stereotype.Component;
import org.locationtech.jts.io.geojson.GeoJsonWriter;
import tools.jackson.databind.ObjectMapper;


import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public class BlockMapper implements Mapper<BlockDTO, Block> {

    private final MapRepository mapRepository;

    public BlockMapper(MapRepository mapRepository, PolygonService polygonService) {
        this.mapRepository = mapRepository;
    }

    @Override
    public BlockDTO toDTO(Block block){
        BlockDTO dto = new BlockDTO();
        dto.setId(block.getId());
        dto.setBlockType(block.getBlockType());
        dto.setMapId(block.getMap().getId());
        dto.setGeometry(block.getGeometry());
        dto.setHeight(block.getHeight());
        dto.setArea(block.getArea());
        dto.setVolume(block.getVolume());
        dto.setCalculated_cost(block.getCalculated_cost());
        dto.setCalculated_yield(block.getCalculated_yield());
        dto.setCalculated_residents(block.getCalculated_residents());
        return dto;
    }

    @Override
    public Block toEntity(BlockDTO blockDTO){
        return new Block(
                blockDTO.getBlockType(),
                mapRepository.findById(blockDTO.getMapId()).orElseThrow(),
                blockDTO.getGeometry(),
                blockDTO.getHeight(),
                blockDTO.getArea(),
                blockDTO.getVolume(),
                blockDTO.getCalculated_cost(),
                blockDTO.getCalculated_yield(),
                blockDTO.getCalculated_residents()
        );
    }


    public List<BlockDTO> toDTO(List<Block> blocks) {
        return blocks.stream()
                .map(this::toDTO)
                .toList();
    }

    public List<Block> toEntity(List<BlockDTO> blocks) {
        return blocks.stream()
                .map(this::toEntity)
                .toList();
    }


}
