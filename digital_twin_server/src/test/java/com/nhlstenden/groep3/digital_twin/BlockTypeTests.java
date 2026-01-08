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
