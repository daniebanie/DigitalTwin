package com.nhlstenden.groep3.digital_twin.Services;
import com.nhlstenden.groep3.digital_twin.Model.BlockType;
import com.nhlstenden.groep3.digital_twin.Repository.BlockTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class BlockTypeService {

    @Autowired
    private BlockTypeRepository blockTypeRepository;


    public List<BlockType> getAllBlockTypes() {
        return blockTypeRepository.findAll();
    }

    public BlockType getBlockTypeByCode(String blockCode) {
        return blockTypeRepository.findByBlockCode(blockCode)
                .orElse(null);
    }
}