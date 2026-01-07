package com.nhlstenden.groep3.digital_twin.Mapper;

import com.nhlstenden.groep3.digital_twin.DTO.MapDTO;
import com.nhlstenden.groep3.digital_twin.Model.Block;
import com.nhlstenden.groep3.digital_twin.Model.Map;
import com.nhlstenden.groep3.digital_twin.Repository.BlockRepository;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MapMapper implements Mapper<MapDTO, Map> {

    private final BlockRepository blockRepository;
    private final BlockMapper blockMapper;

    public MapMapper(BlockRepository blockRepository, BlockMapper blockMapper) {
        this.blockRepository = blockRepository;
        this.blockMapper = blockMapper;
    }

    @Override
    public MapDTO toDTO(Map map) {
        MapDTO dto = new MapDTO();
        dto.setId(map.getId());
        dto.setName(map.getName());
        dto.setBlocks(blockMapper.toDTO(map.getBlocks()));
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

    @Override
    public Map toEntity(MapDTO mapDTO) {
        return new Map(
                mapDTO.getName(),
                //blocks,
                blockMapper.toEntity(mapDTO.getBlocks()),
                mapDTO.getLivability(),
                mapDTO.getCost(),
                mapDTO.getResidents(),
                mapDTO.getWorkplaces(),
                mapDTO.getParkingSpots(),
                mapDTO.getYield(),
                mapDTO.getGreenPercentage(),
                mapDTO.getWorkplacePercentage()
        );
    }
}
