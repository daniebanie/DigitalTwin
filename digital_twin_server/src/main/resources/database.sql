CREATE DATABASE IF NOT EXISTS digital_twin_spoordok;
USE digital_twin_spoordok;
CREATE TABLE `block_types` (
                               `id` INT NOT NULL AUTO_INCREMENT,
                               `block_code` VARCHAR(10) NOT NULL UNIQUE,
                               `name` VARCHAR(100) NOT NULL,
                               `description` TEXT,
                               `unit` ENUM('m2', 'm3') NOT NULL,
                               `cost_per_unit` DECIMAL(15,4) NOT NULL,
                               `yield_percentage` DECIMAL(5,2) NOT NULL,
                               `residents_per_unit` DECIMAL(10,4) DEFAULT NULL,
                               `livability_points` INT NOT NULL,
                               `color_hex` VARCHAR(7) NOT NULL,
                               `is_volumetric` BOOLEAN NOT NULL DEFAULT TRUE,
                               `icon_svg` TEXT,
                               `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                               `updated_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
                               PRIMARY KEY (`id`),
                               CONSTRAINT `block_types_chk_livability` CHECK (`livability_points` BETWEEN 1 AND 10)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

INSERT INTO `block_types`
(`block_code`, `name`, `description`, `unit`, `cost_per_unit`, `yield_percentage`,
 `residents_per_unit`, `livability_points`, `color_hex`, `is_volumetric`, `icon_svg`)
VALUES
    -- A
    (
        'A',
        'Vrijstaand huis',
        'Vrijstaande woning met ruime tuin en privacy. Ideaal voor gezinnen die veel ruimte willen.',
        'm3',
        500.0000,
        12.00,
        0.0050,
        4,
        '#F4A460',
        TRUE,
        '<svg viewBox="0 0 24 24" fill="currentColor"><path d="M10 20v-6h4v6h5v-8h3L12 3 2 12h3v8z"/></svg>'
    ),

    -- B
    (
        'B',
        'Rijtjeswoning',
        'Geschakelde woning in een rij. Betaalbaar en geschikt voor starters en kleine gezinnen.',
        'm3',
        400.0000,
        8.00,
        0.0100,
        6,
        '#FF7F50',
        TRUE,
        '<svg viewBox="0 0 24 24" fill="currentColor"><path d="M12 3L2 12h3v8h6v-6h2v6h6v-8h3L12 3zm0 2.84L18 11v8h-2v-6h-8v6H6v-8l6-5.16z"/></svg>'
    ),

    -- C
    (
        'C',
        'Appartement',
        'Modern appartement in een hoogbouw. Efficiënt gebruik van ruimte in stedelijke omgeving.',
        'm3',
        300.0000,
        12.00,
        0.0060,
        5,
        '#ADD8E6',
        TRUE,
        '<svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 -960 960 960" width="24" fill="currentColor"><path d="M120-120v-560h160v-160h400v320h160v400H520v-160h-80v160H120Zm80-80h80v-80h-80v80Zm0-160h80v-80h-80v80Zm0-160h80v-80h-80v80Zm160 160h80v-80h-80v80Zm0-160h80v-80h-80v80Zm0-160h80v-80h-80v80Zm160 320h80v-80h-80v80Zm0-160h80v-80h-80v80Zm0-160h80v-80h-80v80Zm160 480h80v-80h-80v80Zm0-160h80v-80h-80v80Z"/></svg>'
    ),

    -- D
    (
        'D',
        'Bedrijfsgebouw',
        'Zakelijk pand voor kantoren, winkels of lichte industrie. Genereert werkgelegenheid.',
        'm3',
        200.0000,
        15.00,
        0.0180,
        2,
        '#A9A9A9',
        TRUE,
        '<svg viewBox="0 0 24 24" fill="currentColor"><path d="M12 7V3H2v18h20V7H12zM6 19H4v-2h2v2zm0-4H4v-2h2v2zm0-4H4V9h2v2zm0-4H4V5h2v2zm4 12H8v-2h2v2zm0-4H8v-2h2v2zm0-4H8V9h2v2zm0-4H8V5h2v2zm10 12h-8v-2h2v-2h-2v-2h2v-2h-2V9h8v10zm-2-8h-2v2h2v-2zm0 4h-2v2h2v-2z"/></svg>'
    ),

    -- E
    (
        'E',
        'Park/groen',
        'Groene openbare ruimte met bomen, grasvelden en recreatievoorzieningen. Verhoogt leefbaarheid.',
        'm2',
        150.0000,
        0.00,
        0.0000,
        10,
        '#32CD32',
        FALSE,
        '<svg xmlns="http://www.w3.org/2000/svg" height="24" width="24" viewBox="0 -960 960 960" fill="currentColor"><path d="M180-520q-26 0-43-17t-17-43q0-26 17-43t43-17q26 0 43 17t17 43q0 26-17 43t-43 17ZM120-80v-200h-40v-160q0-17 11.5-28.5T120-480h120q17 0 28.5 11.5T280-440v160h-40v120h320v-200h-70q-71 0-120.5-49.5T320-530q0-53 28.5-94.5T422-686q11-65 60.5-109.5T600-840q68 0 117.5 44.5T778-686q45 20 73.5 61.5T880-530q0 71-49.5 120.5T710-360h-70v200h200v80H120Zm370-360h220q38 0 64-26t26-64q0-27-14.5-49T746-612l-42-18-6-44q-6-37-33.5-61.5T600-760q-37 0-64.5 24.5T502-674l-6 44-42 18q-25 11-39.5 33T400-530q0 38 26 64t64 26Zm110-160Z"/></svg>'
    ),

    -- F
    (
        'F',
        'Wegen',
        'Verharde wegen en straten voor verkeer. Essentieel voor infrastructuur en bereikbaarheid.',
        'm2',
        100.0000,
        5.00,
        0.0000,
        8,
        '#696969',
        FALSE,
        '<svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 -960 960 960" width="24" fill="currentColor"><path d="M720-40v-120H600v-80h120v-120h80v120h120v80H800v120h-80Zm0-400v-360h80v360h-80ZM160-160v-640h80v640h-80Zm280-480v-160h80v160h-80Zm0 240v-160h80v160h-80Zm0 240v-160h80v160h-80Z"/></svg>'
    ),

    -- G
    (
        'G',
        'Parkeerplaatsen',
        'Open parkeerterrein voor voertuigen. Biedt parkeergelegenheid voor bewoners en bezoekers.',
        'm2',
        100.0000,
        10.00,
        0.0000,
        6,
        '#D3D3D3',
        FALSE,
        '<svg viewBox="0 0 24 24" fill="currentColor"><path d="M13 3H6v18h4v-6h3c3.31 0 6-2.69 6-6s-2.69-6-6-6zm.2 8H10V7h3.2c1.1 0 2 .9 2 2s-.9 2-2 2z"/></svg>'
    ),

    -- H
    (
        'H',
        'Parkeerplaatsen overdekt',
        'Overdekte parkeergarage met bescherming tegen weer en wind. Premium parkeeroplossing.',
        'm2',
        1500.0000,
        15.00,
        0.0000,
        10,
        '#708090',
        FALSE,
        '<svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 -960 960 960" width="24" fill="currentColor"><path d="M160-80q-33 0-56.5-23.5T80-160v-640q0-33 23.5-56.5T160-880h640q33 0 56.5 23.5T880-800v640q0 33-23.5 56.5T800-80H160Zm0-80h640v-640H160v640Zm200-240q-17 0-28.5-11.5T320-440q0-17 11.5-28.5T360-480q17 0 28.5 11.5T400-440q0 17-11.5 28.5T360-400Zm240 0q-17 0-28.5-11.5T560-440q0-17 11.5-28.5T600-480q17 0 28.5 11.5T640-440q0 17-11.5 28.5T600-400ZM200-516v264q0 14 9 23t23 9h16q14 0 23-9t9-23v-48h400v48q0 14 9 23t23 9h16q14 0 23-9t9-23v-264l-66-192q-5-14-16.5-23t-25.5-9H308q-14 0-25.5 9T266-708l-66 192Zm106-64 28-80h292l28 80H306ZM160-800v640-640Zm120 420v-120h400v120H280Z"/></svg>'
    );


CREATE TABLE `blocks` (
                          `id` INT NOT NULL AUTO_INCREMENT,
                          `type_id` INT NOT NULL,
                          `geometry` GEOMETRY NOT NULL, CHECK (ST_SRID(geometry) = 4326 ),
                          `height` DECIMAL(10,4) DEFAULT NULL,
                          `area_m2` DECIMAL(15,4) DEFAULT NULL,
                          `volume_m3` DECIMAL(15,4) DEFAULT NULL,
                          `calculated_cost` DECIMAL(15,4) DEFAULT NULL,
                          `calculated_yield` DECIMAL(15,4) DEFAULT NULL,
                          `calculated_residents` INT DEFAULT NULL,
                          `description` TEXT,
                          `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          PRIMARY KEY (`id`),
                          SPATIAL KEY `idx_geometry` (`geometry`),
                          KEY `idx_type_id` (`type_id`),
                          CONSTRAINT `blocks_ibfk_1` FOREIGN KEY (`type_id`) REFERENCES `block_types` (`id`) ON DELETE RESTRICT
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `goals` (
                         `id` INT NOT NULL AUTO_INCREMENT,
                         `name` VARCHAR(100) NOT NULL,
                         `value` DECIMAL(15,4) NOT NULL,
                         `description` TEXT,
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `quality_scores` (
                                  `id` INT NOT NULL AUTO_INCREMENT,
                                  `score` DECIMAL(5,4) NOT NULL,
                                  `agent_type` VARCHAR(100) DEFAULT NULL,
                                  `timestamp` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                  `notes` TEXT,
                                  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE `quality_score_block` (
                                       `quality_score_id` INT NOT NULL,
                                       `block_id` INT NOT NULL,
                                       PRIMARY KEY (`quality_score_id`, `block_id`),
                                       KEY `idx_block_id` (`block_id`),
                                       CONSTRAINT `fk_block` FOREIGN KEY (`block_id`) REFERENCES `blocks` (`id`) ON DELETE CASCADE,
                                       CONSTRAINT `fk_quality_score` FOREIGN KEY (`quality_score_id`) REFERENCES `quality_scores` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


SELECT
    block_code AS 'Code',
    name AS 'Naam',
    unit AS 'Eenheid',
    cost_per_unit AS 'Kosten',
    yield_percentage AS 'Opbrengst %',
    residents_per_unit AS 'Bewoners',
    livability_points AS 'Leefbaarheid',
    color_hex AS 'Kleur',
    is_volumetric AS '3D'
FROM block_types
ORDER BY block_code;

SELECT COUNT(*) AS 'Aantal bouwblokken' FROM block_types;

DROP TABLE IF EXISTS `ai_reviews`;
CREATE TABLE IF NOT EXISTS `ai_reviews` (
                                       `ai_review_id` INT NOT NULL AUTO_INCREMENT,
                                       `review_content` TEXT NOT NULL,
                                       `image_path` VARCHAR(255) NOT NULL,
                                       `created_at` TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                                       PRIMARY KEY (`ai_review_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;