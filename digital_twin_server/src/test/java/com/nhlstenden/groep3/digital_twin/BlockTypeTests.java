/*
package com.nhlstenden.groep3.digital_twin;

import com.nhlstenden.groep3.digital_twin.Model.BlockType;
import com.nhlstenden.groep3.digital_twin.Services.BlockTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BlockTypeTests {

    @Autowired
    private BlockTypeService blockTypeService;

    @Test
    void testBlockTypeService() {
        //Test getAllBlockTypes()
        List<BlockType> bouwblokken = blockTypeService.getAllBlockTypes();

        assertNotNull(bouwblokken, "getAllBlockTypes() mag geen null teruggeven");
        assertFalse(bouwblokken.isEmpty(), "Database moet bouwblokken bevatten");
        assertTrue(bouwblokken.size() >= 8,
                "Frontend verwacht minimaal 8 bouwblokken (A-H), maar kreeg: " + bouwblokken.size());

        System.out.println("getAllBlockTypes() werkt en geeft " + bouwblokken.size() + " bouwblokken terug");

        //Valideer data die frontend nodig heeft
        String cesiumKleurPattern = "^#[0-9A-Fa-f]{6}$"; // Definieert het regex-patroon

        for (BlockType blok : bouwblokken) {
            String blokInfo = "Bouwblok " + blok.getBlockCode();

            // Check velden voor createBuildingPanel()
            assertNotNull(blok.getName(),
                    blokInfo + ": Naam mag niet null zijn (nodig voor button label)");
            assertFalse(blok.getName().trim().isEmpty(),
                    blokInfo + ": Naam mag niet leeg zijn");

            assertNotNull(blok.getBlockCode(),
                    blokInfo + ": BlockCode mag niet null zijn (nodig als identifier)");
            assertFalse(blok.getBlockCode().trim().isEmpty(),
                    blokInfo + ": BlockCode mag niet leeg zijn");

            // Check velden voor drawShape()
            assertNotNull(blok.getColorHex(),
                    blokInfo + ": ColorHex mag niet null zijn (crasht Cesium.Color.fromCssColorString())");
            assertTrue(blok.getColorHex().matches(cesiumKleurPattern),
                    blokInfo + ": ColorHex '" + blok.getColorHex() + "' moet formaat #RRGGBB hebben voor Cesium");

            assertNotNull(blok.getIsVolumetric(),
                    blokInfo + ": IsVolumetric mag niet null zijn (bepaalt 2D/3D rendering)");

            // Check velden voor button icons
            assertNotNull(blok.getIconSvg(),
                    blokInfo + ": IconSvg mag niet null zijn");
            assertFalse(blok.getIconSvg().trim().isEmpty(),
                    blokInfo + ": IconSvg mag niet leeg zijn");
            assertTrue(blok.getIconSvg().toLowerCase().contains("<svg"),
                    blokInfo + ": IconSvg moet een geldige SVG zijn (moet <svg bevatten)");

            // Check financiële data voor updateStatsPanel()
            assertNotNull(blok.getCostPerUnit(),
                    blokInfo + ": CostPerUnit mag niet null zijn (geeft NaN in berekeningen)");
            assertTrue(blok.getCostPerUnit().compareTo(BigDecimal.ZERO) > 0,
                    blokInfo + ": CostPerUnit moet positief zijn, maar is: €" + blok.getCostPerUnit());

            // Check yield percentage
            assertNotNull(blok.getYieldPercentage(),
                    blokInfo + ": YieldPercentage mag niet null zijn");
            assertTrue(blok.getYieldPercentage().compareTo(BigDecimal.ZERO) >= 0,
                    blokInfo + ": YieldPercentage moet minimaal 0% zijn");
            assertTrue(blok.getYieldPercentage().compareTo(new BigDecimal("100")) <= 0,
                    blokInfo + ": YieldPercentage mag niet meer dan 100% zijn, maar is: " + blok.getYieldPercentage() + "%");

            // Check bewoners data voor statistieken
            assertNotNull(blok.getResidentsPerUnit(),
                    blokInfo + ": ResidentsPerUnit mag niet null zijn");
            assertTrue(blok.getResidentsPerUnit().compareTo(BigDecimal.ZERO) >= 0,
                    blokInfo + ": ResidentsPerUnit moet 0 of positief zijn");

            // Check leefbaarheid voor stats panel
            assertNotNull(blok.getLivabilityPoints(),
                    blokInfo + ": LivabilityPoints mag niet null zijn");
            assertTrue(blok.getLivabilityPoints() >= 0 && blok.getLivabilityPoints() <= 10,
                    blokInfo + ": LivabilityPoints moet tussen 0-10 zijn, maar is: " + blok.getLivabilityPoints());
        }

        System.out.println("Alle bouwblokken hebben geldige data voor frontend rendering");

        //Test getBlockTypeByCode()
        BlockType bouwblokA = blockTypeService.getBlockTypeByCode("A");
        assertNotNull(bouwblokA, "getBlockTypeByCode('A') moet bouwblok A teruggeven");
        assertEquals("A", bouwblokA.getBlockCode(), "BlockCode moet overeenkomen");

        System.out.println("getBlockTypeByCode() werkt correct");

        //Test error handling
        BlockType nietBestaand = blockTypeService.getBlockTypeByCode("XYZ");
        assertNull(nietBestaand, "Niet-bestaand bouwblok moet null teruggeven (niet crashen)");

        System.out.println("Service crasht niet bij ongeldige input");

        //Check dat alle verwachte bouwblokken aanwezig zijn
        String[] verwachteCodes = {"A", "B", "C", "D", "E", "F", "G", "H"};
        for (String code : verwachteCodes) {
            BlockType blok = blockTypeService.getBlockTypeByCode(code);
            assertNotNull(blok, "Bouwblok " + code + " moet bestaan (frontend verwacht deze)");
        }

        System.out.println(" Alle 8 verwachte bouwblokken (A-H) zijn aanwezig");
    }
}*/

package com.nhlstenden.groep3.digital_twin;

import com.nhlstenden.groep3.digital_twin.Controller.BlockTypeController;
import com.nhlstenden.groep3.digital_twin.Model.BlockType;
import com.nhlstenden.groep3.digital_twin.Services.BlockTypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Unit / integratietest voor BlockTypeController
 * <p>
 * Deze test controleert of de REST API endpoints van BlockTypeController correct werken.
 * Dit is essentieel omdat de frontend deze endpoints gebruikt om knoppen en UI-elementen te genereren.
 */
@WebMvcTest(BlockTypeController.class) // Start een lichte Spring Boot voor alleen deze controller
class BlockTypeControllerTest {

    @Autowired
    private MockMvc mockMvc; // Simuleert HTTP requests zonder echte server

    @MockitoBean
    private BlockTypeService blockTypeService; // Mockt de service

    private BlockType blockTypeA;
    private BlockType blockTypeB;
    private BlockType blockTypeH;

    @BeforeEach
    void setup() {

        blockTypeA = new BlockType();
        blockTypeA.setId(1);
        blockTypeA.setBlockCode("A");
        blockTypeA.setName("Woongebouw");
        blockTypeA.setDescription("Standaard woongebouw");
        blockTypeA.setUnit(BlockType.Unit.m3);
        blockTypeA.setCostPerUnit(500.0);
        blockTypeA.setYieldPercentage(5.0);
        blockTypeA.setResidentsPerUnit(0.05);
        blockTypeA.setLivabilityPoints(7);
        blockTypeA.setColorHex("#FF5733");
        blockTypeA.setIsVolumetric(true);
        blockTypeA.setGreen(false);
        blockTypeA.setResidentType(BlockType.ResidentType.residential);
        blockTypeA.setIconSvg("<svg xmlns=\"http://www.w3.org/2000/svg\"><path d=\"M10 20v-6h4v6h5v-8h3L12 3 2 12h3v8z\"/></svg>");


        blockTypeB = new BlockType();
        blockTypeB.setId(2);
        blockTypeB.setBlockCode("B");
        blockTypeB.setName("Kantoorgebouw");
        blockTypeB.setDescription("Modern kantoorgebouw");
        blockTypeB.setUnit(BlockType.Unit.m3);
        blockTypeB.setCostPerUnit(700.0);
        blockTypeB.setYieldPercentage(8.0);
        blockTypeB.setResidentsPerUnit(0.02);
        blockTypeB.setLivabilityPoints(5);
        blockTypeB.setColorHex("#3357FF");
        blockTypeB.setIsVolumetric(true);
        blockTypeB.setGreen(false);
        blockTypeB.setResidentType(BlockType.ResidentType.workplace);
        blockTypeB.setIconSvg("<svg xmlns=\"http://www.w3.org/2000/svg\"><path d=\"M4 10v12h16V10H4z\"/></svg>");


        blockTypeH = new BlockType();
        blockTypeH.setId(8);
        blockTypeH.setBlockCode("H");
        blockTypeH.setName("Park");
        blockTypeH.setDescription("Openbaar groen");
        blockTypeH.setUnit(BlockType.Unit.m2);
        blockTypeH.setCostPerUnit(100.0);
        blockTypeH.setYieldPercentage(0.0);
        blockTypeH.setResidentsPerUnit(0.0);
        blockTypeH.setLivabilityPoints(10);
        blockTypeH.setColorHex("#33FF57");
        blockTypeH.setIsVolumetric(false);
        blockTypeH.setGreen(true);
        blockTypeH.setResidentType(null);
        blockTypeH.setIconSvg("<svg xmlns=\"http://www.w3.org/2000/svg\"><path d=\"M12 2L2 22h20L12 2z\"/></svg>");
    }

    /**
     * TEST 1: GET /api/blocktypes
     * <p>
     * Controleert of de endpoint alle blocktypes correct teruggeeft als JSON.
     * Deze data wordt door de frontend gebruikt om UI-knoppen dynamisch te genereren.
     */
    @Test
    void getAllBlockTypes() throws Exception {
        // Mock service om testdata terug te geven
        List<BlockType> mockBlockTypes = Arrays.asList(blockTypeA, blockTypeB, blockTypeH);
        when(blockTypeService.getAllBlockTypes()).thenReturn(mockBlockTypes);

        // Voer GET request uit en controleer het resultaat
        mockMvc.perform(get("/api/blocktypes")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // HTTP status 200
                .andExpect(content().contentType(MediaType.APPLICATION_JSON)) // Controleer JSON response


                .andExpect(jsonPath("$.length()").value(3))


                .andExpect(jsonPath("$[0].blockCode").value("A"))
                .andExpect(jsonPath("$[0].name").value("Woongebouw"))
                .andExpect(jsonPath("$[0].unit").value("m3"))
                .andExpect(jsonPath("$[0].costPerUnit").value(500.0))
                .andExpect(jsonPath("$[0].yieldPercentage").value(5.0))
                .andExpect(jsonPath("$[0].residentsPerUnit").value(0.05))
                .andExpect(jsonPath("$[0].livabilityPoints").value(7))
                .andExpect(jsonPath("$[0].colorHex").value("#FF5733"))
                .andExpect(jsonPath("$[0].isVolumetric").value(true))
                .andExpect(jsonPath("$[0].green").value(false))
                .andExpect(jsonPath("$[0].residentType").value("residential"))
                .andExpect(jsonPath("$[0].iconSvg").exists())


                .andExpect(jsonPath("$[1].blockCode").value("B"))
                .andExpect(jsonPath("$[1].name").value("Kantoorgebouw"))
                .andExpect(jsonPath("$[1].unit").value("m3"))
                .andExpect(jsonPath("$[1].costPerUnit").value(700.0))
                .andExpect(jsonPath("$[1].residentType").value("workplace"))
                .andExpect(jsonPath("$[1].isVolumetric").value(true))


                .andExpect(jsonPath("$[2].blockCode").value("H"))
                .andExpect(jsonPath("$[2].name").value("Park"))
                .andExpect(jsonPath("$[2].unit").value("m2"))
                .andExpect(jsonPath("$[2].costPerUnit").value(100.0))
                .andExpect(jsonPath("$[2].green").value(true))
                .andExpect(jsonPath("$[2].isVolumetric").value(false))
                .andExpect(jsonPath("$[2].livabilityPoints").value(10));
    }

    /**
     * TEST 2: GET /api/blocktypes/{blockCode} met geldige code
     * <p>
     * Controleert of één specifiek blocktype correct teruggegeven wordt.
     * Dit wordt gebruikt door de frontend wanneer een gebruiker een gebouw selecteert.
     */
    @Test
    void getBlockType_validCode_returnsBlockType() throws Exception {
        // Mock service voor blockCode "A"
        when(blockTypeService.getBlockTypeByCode("A")).thenReturn(blockTypeA);

        // Voer GET request uit en controleer JSON response
        mockMvc.perform(get("/api/blocktypes/A")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.blockCode").value("A"))
                .andExpect(jsonPath("$.name").value("Woongebouw"))
                .andExpect(jsonPath("$.costPerUnit").value(500.0));
    }

    /**
     * TEST 3: GET /api/blocktypes/{blockCode} met ongeldige code
     * <p>
     * Controleert dat de controller een niet-bestaand blocktype correct afhandelt.
     * Verwacht een lege response en logt "BlockType not found" in de console.
     */
    @Test
    void getBlockType_invalidCode_returnsNull() throws Exception {
        // Mock service voor ongeldige code
        when(blockTypeService.getBlockTypeByCode("INVALID")).thenReturn(null);

        // Voer GET request uit en controleer dat response leeg is
        mockMvc.perform(get("/api/blocktypes/INVALID")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()) // HTTP 200 nog steeds, geen fout
                .andExpect(content().string("")); // Lege response
    }
}
