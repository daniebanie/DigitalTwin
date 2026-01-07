package com.nhlstenden.groep3.digital_twin;

import com.nhlstenden.groep3.digital_twin.Controller.InformationController;
import com.nhlstenden.groep3.digital_twin.Model.Block;
import com.nhlstenden.groep3.digital_twin.Model.BlockType;
import com.nhlstenden.groep3.digital_twin.Repository.BlockTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;


@WebMvcTest(controllers = InformationController.class)
public class InformationControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private InformationController informationController;

    @MockitoBean
    private BlockTypeRepository blockTypeRepository;

    @BeforeEach
    public void setup() {
        BlockType blockType1 = new BlockType();
        blockType1.setId(1);
        blockType1.setBlockCode("A");
        blockType1.setLivabilityPoints(5);
        blockType1.setUnit(BlockType.Unit.m3);
        BlockType blockType2 = new BlockType();
        blockType2.setId(2);
        blockType2.setBlockCode("D");
        blockType2.setLivabilityPoints(3);
        blockType2.setUnit(BlockType.Unit.m3);
        BlockType blockType3 = new BlockType();
        blockType3.setId(3);
        blockType3.setBlockCode("H");
        blockType3.setLivabilityPoints(10);
        blockType3.setUnit(BlockType.Unit.m2);

        when(blockTypeRepository.findById(1)).thenReturn(Optional.of(blockType1));
        when(blockTypeRepository.findById(2)).thenReturn(Optional.of(blockType2));
        when(blockTypeRepository.findById(3)).thenReturn(Optional.of(blockType3));
    }

    @Test
    void AddBlockInfoTest() {

        Block block1 = new Block();
        block1.setBlockType(blockTypeRepository.findById(1).orElseThrow());
        block1.setCalculated_cost(100);
        block1.setCalculated_yield(150);
        block1.setCalculated_residents(4);
        block1.setVolume(100);
        block1.setArea(50);

        informationController.updateValuesFromBlock(block1);



        assertEquals(5, informationController.getCurrentLivability());
        assertEquals(100, informationController.getCurrentCost());
        assertEquals(150, informationController.getCurrentYield());
        assertEquals(4, informationController.getCurrentResidents());
        assertEquals(0, informationController.getCurrentWorkplaces());
        assertEquals(0, informationController.getCurrentParkingSpots());
        assertEquals(100, informationController.getCurrentTotalVolume());
        assertEquals(50, informationController.getCurrentTotalArea());
        assertEquals(0, informationController.getCurrentGreenPercentage());
        assertEquals(0, informationController.getCurrentWorkplacePercentage());


        Block block2 = new Block();
        block2.setBlockType(blockTypeRepository.findById(2).orElseThrow());
        block2.setCalculated_cost(80);
        block2.setCalculated_yield(120);
        block2.setCalculated_residents(50);
        block2.setVolume(150);
        block2.setArea(70);

        informationController.updateValuesFromBlock(block2);

        assertEquals(8, informationController.getCurrentLivability());
        assertEquals(180, informationController.getCurrentCost());
        assertEquals(270, informationController.getCurrentYield());
        assertEquals(4, informationController.getCurrentResidents());
        assertEquals(50, informationController.getCurrentWorkplaces());
        assertEquals(0, informationController.getCurrentParkingSpots());
        assertEquals(250, informationController.getCurrentTotalVolume());
        assertEquals(120, informationController.getCurrentTotalArea());
        assertEquals(0, informationController.getCurrentGreenPercentage());
        assertEquals(70, informationController.getCurrentWorkplacePercentage());

        Block block3 = new Block();
        block3.setBlockType(blockTypeRepository.findById(3).orElseThrow());
        block3.setCalculated_cost(40);
        block3.setCalculated_yield(80);
        block3.setCalculated_residents(200);
        block3.setVolume(0);
        block3.setArea(200);

        informationController.updateValuesFromBlock(block3);

        assertEquals(18, informationController.getCurrentLivability());
        assertEquals(220, informationController.getCurrentCost());
        assertEquals(350, informationController.getCurrentYield());
        assertEquals(4, informationController.getCurrentResidents());
        assertEquals(50, informationController.getCurrentWorkplaces());
        assertEquals(200, informationController.getCurrentParkingSpots());
        assertEquals(250, informationController.getCurrentTotalVolume());
        assertEquals(320, informationController.getCurrentTotalArea());
        assertEquals(200, informationController.getCurrentGreenPercentage());
        assertEquals(70, informationController.getCurrentWorkplacePercentage());
    }
}
