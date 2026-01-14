let mapName;
let mapId;
let selectedHeight;
let buildingBlocks = {};
let currentBuildingBlock = null;
let selectedBlockColor = Cesium.Color.GRAY.withAlpha(0.5);


/**
 * Laadt alle BlockTypes van de server en converteert ze naar frontend formaat.
 * Frontend heeft BlockType data nodig om bouwblok knoppen te maken.
 * @returns {Promise<boolean>} true als succesvol, false bij fout
 */
async function loadBuildingBlocks() {
    try {
        // Haal BlockTypes op van server
        const response = await fetch('http://localhost:8080/api/blocktypes');

        // Controleer of request succesvol was (HTTP 200-299)
        if (!response.ok) throw new Error(`HTTP ${response.status}`);

        // JSON response naar JavaScript array
        const blockTypes = await response.json();

        // Reset buildingBlocks object
        buildingBlocks = {};

        // Stap 2: Converteer elk BlockType naar frontend formaat
        blockTypes.forEach(block => {
            buildingBlocks[block.blockCode] = {
                name: block.name,
                unit: `per ${block.unit}`,
                costPerUnit: parseFloat(block.costPerUnit),
                yieldPercentage: parseFloat(block.yieldPercentage),
                residentsPerUnit: parseFloat(block.residentsPerUnit),
                sustainabilityScore: block.livabilityPoints,
                color: Cesium.Color.fromCssColorString(block.colorHex),
                colorHex: block.colorHex,
                isVolumetric: block.isVolumetric,
                icon: block.iconSvg
            };
        });

        console.log('Building blocks succesvol geladen uit database:', buildingBlocks);

        // Maak UI knoppen met de nieuwe data
        rebuildBuildingPanel();

        return true;

    } catch (error) {
        console.error('Fout bij laden building blocks:', error);

        // Toon foutmelding aan gebruiker
        showLoadingMessage();

        return false; // Failure
    }
}

/** Toont een bericht in de UI wanneer BlockTypes niet geladen kunnen worden
 *
 */
function showLoadingMessage() {
    const grid = document.querySelector('#building-ui-container .row.g-2');
    if (grid) {
        grid.innerHTML = '<div class="col-12 text-center text-muted py-4">Laden van bouwblokken...<br><small>Controleer of de database online is</small></div>';
    }
}


/** Plant een retry poging na 5 seconden als laden mislukt
 * Recursief en voorkomt dat handmatig herladen moet worden voor als de database niet bereikbaar is
 *
 * evt. max aantal retries toevoegen bij uitbreiding
 */
function scheduleRetry() {
    console.log('Retry over 5 seconden...');
    setTimeout(async () => {
        const success = await loadBuildingBlocks();
        if (!success) scheduleRetry();
    }, 5000);
}

/**
 * Genereert de bouwblok-selectieknoppen in de UI.
 *
 * Leeg de huidige grid > loop door buildingBlocks > maar voor elke blok een knop met icoon, naam, code, kleur > voeg onclick handlers toe
 *
 * Data driven design; UI past zich aan op basis van de informatie die uit de database gehaald wordt
 */
function rebuildBuildingPanel() {
    const grid = document.querySelector('#building-ui-container .row.g-2');
    if (!grid) return;

    // Clear bestaande knoppen
    grid.innerHTML = '';

    // Controleer of er data is
    if (Object.keys(buildingBlocks).length === 0) {
        showLoadingMessage();
        return;
    }

    // Genereer een knop voor elk bouwblok type
    Object.keys(buildingBlocks).forEach(key => {
        const block = buildingBlocks[key];

        // Maak column element via bootstrap grids
        const col = document.createElement('div');
        col.className = 'col-6'; // 2 knoppen per rij

        // Maak knop element
        const button = document.createElement('button');
        button.className = 'building-btn btn w-100 d-flex flex-column align-items-center gap-2 p-3 rounded-3';
        button.id = `block-${key}`; // ID
        button.onclick = () => selectBuildingBlock(key); // Click handler

        // Vul knop met HTML (icoon, code badge, naam, kleur indicator)
        button.innerHTML = `
            <div class="btn-icon">${block.icon}</div>
            <span class="btn-code badge rounded-pill px-2">${key}</span>
            <div class="btn-label text-center lh-sm">${block.name}</div>
            <div class="color-indicator" style="background-color: ${block.colorHex};"></div>
        `;

        col.appendChild(button);
        grid.appendChild(col);
    });
}

// UI SETUP
function createBuildingUI() {
    const container = createBuildingUIContainer("left");
    document.body.appendChild(container);
    showLoadingMessage();
}

function createAIUI() {
    const container2 = createUIAIContainer("right");
    document.body.appendChild(container2);
    showLoadingMessage();
}

function createBuildingUIContainer(Position) {
    const container = document.createElement('div');
    container.id = 'building-ui-container';
    container.className = 'd-flex flex-column gap-3 ' + Position;

    container.appendChild(createBuildingPanel());
    container.appendChild(createInfoPanel());
    container.appendChild(createStatsPanel());

    return container;
}

function createAIUIContainer(Position) {
    const container = document.createElement('div');
    container.id = 'building-ui-container';
    container.className = 'd-flex flex-column gap-3 ' + Position;

    container.appendChild(createUIAIContainer());
    container.appendChild(createInfoPanel());
    container.appendChild(createStatsPanel());

    return container;
}

function createBuildingPanel() {
    const panel = document.createElement('div');
    panel.className = 'ui-panel card shadow-sm';

    const cardBody = document.createElement('div');
    cardBody.className = 'card-body p-3';

    const header = document.createElement('h3');
    header.className = 'panel-header h5 mb-3 fw-bold';
    header.textContent = 'Bouwblokken';

    const grid = document.createElement('div');
    grid.className = 'row g-2';

    cardBody.appendChild(header);
    cardBody.appendChild(grid);
    panel.appendChild(cardBody);

    return panel;
}

function createInfoPanel() {
    const panel = document.createElement('div');
    panel.id = 'info-panel';
    panel.className = 'ui-panel card shadow-sm info-panel';
    panel.style.display = 'none';

    return panel;
}

function createStatsPanel() {
    const panel = document.createElement('div');
    panel.className = 'ui-panel card shadow-sm';

    const cardBody = document.createElement('div');
    cardBody.className = 'card-body p-3';

    const header = document.createElement('h3');
    header.className = 'panel-header h5 mb-3 fw-bold';
    header.textContent = 'Statistieken';

    const statsGrid = document.createElement('div');
    statsGrid.className = 'd-flex flex-column gap-2';

    const stats = [
        {
            id: 'total-cost',
            label: 'Kosten',
            goal: '100000',
            icon: `<svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 -960 960 960" width="24"><path d="M600-120q-118 0-210-67T260-360H120v-80h122q-3-24-2.5-44.5T242-520H120v-80h140q38-106 130-173t210-67q69 0 130.5 24.5T840-748l-57 56q-37-32-83.5-50T600-760q-85 0-152 44.5T347-600h253v80H323q-4 27-3 47.5t3 32.5h277v80H347q34 71 101 115.5T600-200q53 0 99.5-18t83.5-50l57 56q-48 43-109.5 67.5T600-120Z"/></svg>`
        },
        {
            id: 'total-yield',
            label: 'Opbrengst',
            goal: '100000',
            icon: `<svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 -960 960 960" width="24"><path d="M336-120q-91 0-153.5-62.5T120-336q0-38 13-74t37-65l142-171-97-194h530l-97 194 142 171q24 29 37 65t13 74q0 91-63 153.5T624-120H336Zm144-200q-33 0-56.5-23.5T400-400q0-33 23.5-56.5T480-480q33 0 56.5 23.5T560-400q0 33-23.5 56.5T480-320Zm-95-360h190l40-80H345l40 80Zm-49 480h288q57 0 96.5-39.5T760-336q0-24-8.5-46.5T728-423L581-600H380L232-424q-15 18-23.5 41t-8.5 47q0 57 39.5 96.5T336-200Z"/></svg>`
        },
        {
            id: 'total-residents',
            label: 'Bewoners',
            goal: '3000',
            icon: `<svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 -960 960 960" width="24"><path d="M360-80v-529q-91-24-145.5-100.5T160-880h80q0 83 53.5 141.5T430-680h100q30 0 56 11t47 32l181 181-56 56-158-158v478h-80v-240h-80v240h-80Zm120-640q-33 0-56.5-23.5T400-800q0-33 23.5-56.5T480-880q33 0 56.5 23.5T560-800q0 33-23.5 56.5T480-720Z"/></svg>`
        },
        {
            id: 'total-livability',
            label: 'Leefbaarheid score',
            goal: '100',
            icon: `<svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 -960 960 960" width="24"><path d="M280-280h80v-200h-80v200Zm320 0h80v-400h-80v400Zm-160 0h80v-120h-80v120Zm0-200h80v-80h-80v80ZM200-120q-33 0-56.5-23.5T120-200v-560q0-33 23.5-56.5T200-840h560q33 0 56.5 23.5T840-760v560q0 33-23.5 56.5T760-120H200Zm0-80h560v-560H200v560Zm0-560v560-560Z"/></svg>`
        },
        {
            id: 'total-workplaces',
            label: 'Werkplekken',
            goal: '500'
            //icon: `<svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 -960 960 960" width="24"><path d="M280-280h80v-200h-80v200Zm320 0h80v-400h-80v400Zm-160 0h80v-120h-80v120Zm0-200h80v-80h-80v80ZM200-120q-33 0-56.5-23.5T120-200v-560q0-33 23.5-56.5T200-840h560q33 0 56.5 23.5T840-760v560q0 33-23.5 56.5T760-120H200Zm0-80h560v-560H200v560Zm0-560v560-560Z"/></svg>`
        },
        {
            id: 'green-percentage',
            label: 'Groen percentage',
            goal: '20'
            //icon: `<svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 -960 960 960" width="24"><path d="M280-280h80v-200h-80v200Zm320 0h80v-400h-80v400Zm-160 0h80v-120h-80v120Zm0-200h80v-80h-80v80ZM200-120q-33 0-56.5-23.5T120-200v-560q0-33 23.5-56.5T200-840h560q33 0 56.5 23.5T840-760v560q0 33-23.5 56.5T760-120H200Zm0-80h560v-560H200v560Zm0-560v560-560Z"/></svg>`
        },
        {
            id: 'workplace-percentage',
            label: 'Percentage bedrijfsgebouwen',
            goal: '20'
            //icon: `<svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 -960 960 960" width="24"><path d="M280-280h80v-200h-80v200Zm320 0h80v-400h-80v400Zm-160 0h80v-120h-80v120Zm0-200h80v-80h-80v80ZM200-120q-33 0-56.5-23.5T120-200v-560q0-33 23.5-56.5T200-840h560q33 0 56.5 23.5T840-760v560q0 33-23.5 56.5T760-120H200Zm0-80h560v-560H200v560Zm0-560v560-560Z"/></svg>`
        },
        {
            id: 'total-parkingspots',
            label: 'Parkeerplekken',
            goal: '4500'
            //icon: `<svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 -960 960 960" width="24"><path d="M280-280h80v-200h-80v200Zm320 0h80v-400h-80v400Zm-160 0h80v-120h-80v120Zm0-200h80v-80h-80v80ZM200-120q-33 0-56.5-23.5T120-200v-560q0-33 23.5-56.5T200-840h560q33 0 56.5 23.5T840-760v560q0 33-23.5 56.5T760-120H200Zm0-80h560v-560H200v560Zm0-560v560-560Z"/></svg>`
        },
    ];

    stats.forEach(stat => {
        const statItem = document.createElement('div');
        statItem.className = 'stat-item d-flex justify-content-between align-items-center p-3 rounded-3';

        statItem.innerHTML = `
            <div class="d-flex flex-column">
                <div class="d-flex align-items-center gap-2">
                    <span class="stat-icon">${stat.icon}</span>
                    <span class="stat-label text-secondary fw-medium small">${stat.label}</span>
                </div>
                <div class="d-flex align-items-center gap-2">
                    <div class="stat-value h5 mb-0 fw-bold mt-2" id="${stat.id}">0</div>
                    <span class="h5 mb-0 fw-bold mt-2">/</span>
                    <div class="h5 mb-0 fw-bold mt-2" id="${stat.id}-goal">${stat.goal}</div>
                </div>
            </div>
        `;

        statsGrid.appendChild(statItem);
    });

    cardBody.appendChild(header);
    cardBody.appendChild(statsGrid);
    panel.appendChild(cardBody);

    return panel;
}

/**
 * Wordt opgeroepen wanneer gebruiker op bouwblok knop klikt
 *
 * Reset alle knoppen > Markeer geselecteerde knop als actief > Update currentBuildingBlock variabele > Toon info panel > Update selectedBlockColor
 * @param blockType
 */
function selectBuildingBlock(blockType) {
    // Reset alle knoppen
    document.querySelectorAll('.building-btn').forEach(btn => {
        btn.classList.remove('selected');
    });

    // Selecteer nieuwe knop
    const selectedButton = document.getElementById(`block-${blockType}`);
    selectedButton.classList.add('selected');
    currentBuildingBlock = blockType;

    // Update info panel
    updateInfoPanel(blockType);

    console.log(`Bouwblok ${blockType} geselecteerd: ${buildingBlocks[blockType].name}`);
}

function updateInfoPanel(blockType) {
    const block = buildingBlocks[blockType];
    const infoPanel = document.getElementById('info-panel');
    infoPanel.style.display = 'block';

    const cardBody = document.createElement('div');
    cardBody.className = 'card-body p-3';

    cardBody.innerHTML = `
        <div class="info-title h6 mb-3 d-flex align-items-center gap-2">
            <span style="font-size: 24px;">${block.icon.replace('currentColor', 'white')}</span>
            <span>${block.name}</span>
        </div>
        <div class="row g-2 small">
            <div class="col-6">
                <div class="info-item p-2 rounded-2">
                    <div class="info-label opacity-75 mb-1">Kostprijs:</div>
                    <div class="info-value fw-semibold">&euro;${block.costPerUnit}</div>
                </div>
            </div>
            <div class="col-6">
                <div class="info-item p-2 rounded-2">
                    <div class="info-label opacity-75 mb-1">Opbrengst:</div>
                    <div class="info-value fw-semibold">${block.yieldPercentage}%</div>
                </div>
            </div>
            <div class="col-6">
                <div class="info-item p-2 rounded-2">
                    <div class="info-label opacity-75 mb-1">Bewoners:</div>
                    <div class="info-value fw-semibold">${block.residentsPerUnit}</div>
                </div>
            </div>
            <div class="col-6">
                <div class="info-item p-2 rounded-2">
                    <div class="info-label opacity-75 mb-1">Leefbaarheid score:</div>
                    <div class="info-value fw-semibold">${block.sustainabilityScore}/10</div>
                </div>
            </div>
            <div class="col-12">
                <div class="d-flex align-items-center gap-2 mt-2">
                    <span class="small opacity-75">Kleur:</span>
                    <div class="flex-grow-1" style="height: 8px; background-color: ${block.colorHex}; border-radius: 4px;"></div>
                </div>
            </div>
        </div>
    `;

    infoPanel.innerHTML = '';
    infoPanel.appendChild(cardBody);
    selectedBlockColor = Cesium.Color.fromCssColorString(block.colorHex).withAlpha(0.6);

}


function createAIUI2() {
    const container = createUIAIContainer();
    document.body.appendChild(container);
}

function createUIAIContainer(Position) {
    const container = document.createElement('div');
    container.id = 'ai-ui-container';
    container.className = 'building-ui-container d-flex flex-column gap-3 ' + Position;

    // Hide/Show AI Panel Button
    container.appendChild(createAIButton());
    // AI Panel
    container.appendChild(createAIPanel());

    return container;
}

function createAIPanel() {
    const panel = document.createElement('div');
    panel.id = 'ai-panel';
    panel.className = 'ui-panel card shadow-sm ai-panel';

    const cardBody = document.createElement('div');
    cardBody.className = 'card-body p-3';

    const header = document.createElement('h3');
    header.className = 'panel-header h5 mb-3 fw-bold';
    header.textContent = 'AI panel';

    cardBody.appendChild(header);
    panel.appendChild(cardBody);

    panel.appendChild(createInputArea());
    panel.appendChild(createResultsArea());

    return panel;
}

function createInputArea() {
    const panel = document.createElement('div');
    panel.id = 'input-area-panel';
    panel.appendChild(createAIInput());

    return panel;
}

function createAIButton() {
    const button = document.createElement('button');
    button.id = 'ai-toggle-btn';
    button.className = 'btn btn-primary';
    button.textContent = 'AI Panel';

    button.onclick = () => {
        const panel = document.getElementById('ai-panel');
        panel.style.display = (panel.style.display === 'none' || panel.style.display === '')
            ? 'block'
            : 'none';
    };

    return button;
}

function createAIInput() {
    const form = document.createElement('form');
    form.method = 'post';
    form.enctype = 'multipart/form-data';

    const input = document.createElement('input');
    input.id = 'ai-input';
    input.className = 'input input-primary';
    input.type = 'file';
    input.accept = 'image/*';
    input.name = 'image';


    const button = document.createElement('button');
    button.id = 'ai-submit-btn';
    button.className = 'btn btn-primary';
    button.type = 'submit'
    button.textContent = 'Submit';

    form.appendChild(input);
    form.appendChild(button);

    attachImageUploadHandler(form);

    return form;
}

function attachImageUploadHandler(formEl) {
    formEl.addEventListener('submit', event => {
        event.preventDefault();

        const formData = new FormData(formEl);

        // Optional: show loading state
        setAIResult("Analyzing image...");

        fetch('http://localhost:8080/upload', {
            method: 'POST',
            body: formData
        })
            .then(res => res.json())
            .then(data => {
                console.log("Server response:", data);

                const aiText = data?.aiResponse?.response
                    || "No AI response received";

                setAIResult(aiText);
            })
            .catch(error => {
                console.error(error);
                setAIResult("AI analysis failed.");
            });
    });
}

function setAIResult(text) {
    const resultText = document.getElementById('resultai');

    if (!resultText) {
        console.error("resultai element not found");
        return;
    }

    resultText.textContent = text;
}


function createResultsArea() {
    const panel = document.createElement('div');
    panel.id = 'results-area-panel';

    const text = document.createElement('plaintext');
    text.id = 'resultai';
    text.className = 'mb-3 fw-bold';
    text.textContent = 'Resultaten komen hier';

    panel.appendChild(text);

    panel.appendChild(createResultsPanel())
    return panel;
}

function createResultsPanel() {
    const panel = document.createElement('div');
    panel.id = 'test';

    const header = document.createElement('h3');
    header.className = 'panel-header h5 mb-3 fw-bold';
    header.textContent = 'Resultaten';

    const grid = document.createElement('div');
    grid.className = 'row g-2';

    Object.keys(resultBlocks).forEach(key => {
        const result = resultBlocks[key];

        const col = document.createElement('div');
        col.id = 'result-block';
        col.className = 'col-6 text-center';

        const name = document.createElement('div');
        name.textContent = result.name + " / " + result.uploadedOn;

        const img = document.createElement('img');
        img.src = result.route; // <-- FIX PATH
        img.alt = 'result';
        img.style.width = '100%';

        const resultText = document.createElement('div');
        resultText.textContent = result.result;


        col.appendChild(name);
        col.appendChild(img);
        col.appendChild(resultText);
        grid.appendChild(col);
    });


    panel.appendChild(header);
    panel.appendChild(grid);

    return panel;
}


const resultBlocks = {
    1: {
        name: "imageno1",
        route: `Cesium-1.135/Screenshots/Schermafbeelding2025-11-27221716.png`,
        uploadedOn: "10-12-2025",
        result: "Dit is een mooi minecraft huis"

    },
    2: {
        name: "imageno1",
        route: `Cesium-1.135/Screenshots/Schermafbeelding2025-11-27221716.png`,
        uploadedOn: "10-12-2025",
        result: "Dit is een mooi minecraft huis"
    }
};

window.onload = setup;

var measure;
var viewer;

function setup() {


    loadBuildingBlocks().then(success => {
        if (!success) {
            scheduleRetry();
        }
    });


    const west = 5.798212900532118;
    const south = 53.19304584690279;
    const east = 5.798212900532118;
    const north = 53.19304584690279;

    var rectangle = Cesium.Rectangle.fromDegrees(west, south, east, north);

    Cesium.Camera.DEFAULT_VIEW_FACTOR = 0.0005;
    Cesium.Camera.DEFAULT_VIEW_RECTANGLE = rectangle;

    //Verwijderd Cesium Ion credit
    //Als je hun systemen niet gebruikt kun je dit verwijderen
    //viewer.creditDisplay.removeStaticCredit(Cesium.CreditDisplay._cesiumCredit);

    const osm = new Cesium.OpenStreetMapImageryProvider({
        url: 'https://tile.openstreetmap.org/'
    });

    viewer = new Cesium.Viewer("cesiumContainer", {
        baseLayerPicker: false,
        imageryProvider: false,
        infoBox: false,
        selectionIndicator: false,
        shadows: true,
        shouldAnimate: true,

        geocoder: false,
        sceneModePicker: false,
        navigationHelpButton: false,
        homeButton: false,

        animation: false,
        timeline: false,
    });


    // viewer.imageryLayers.removeAll();
    createAIUI();
    viewer.imageryLayers.addImageryProvider(osm);

    //Improves tile quality
    viewer.scene.globe.maximumScreenSpaceError = 1;

    viewer.imageryLayers.removeAll();
    viewer.imageryLayers.addImageryProvider(osm);

    addSpoordokPoly();
    setupInputActions();

    createBuildingUI();
    createBuildingUI2();
    console.log('UI should be created now');

}

function addSpoordokPoly() {
    return viewer.entities.add({
        name: "Spoordok",
        polygon: {
            hierarchy: Cesium.Cartesian3.fromDegreesArray([
                5.787759928698073, 53.197831145908,
                5.789123554275904, 53.19763995957844,
                5.788934967759822, 53.19602353198474,
                5.776937964005922, 53.194528716741345,
                5.774587885853288, 53.196901277127026,
                5.774703939093954, 53.1976225789762,
                5.786410809746187, 53.19704032421097,
            ]),
            material: Cesium.Color.LIGHTGRAY,
        },
    });
}

function createPoint(worldPosition) {
    const point = viewer.entities.add({
        position: worldPosition,
        point: {
            color: Cesium.Color.BLUE,
            pixelSize: 5,
            heightReference: Cesium.HeightReference.CLAMP_TO_GROUND,
        },
    });
    return point;
}

let drawingMode = "polygon"; //Deze kun je aanpassen als je een GUI-element hiervoor maakt.

/**
 * Tekent een vorm (lijn of polygon) in de 3D viewer.
 *
 * Gebruikt selectedBlockColor voor de kleur ingesteld door selectBuildingBlock
 *
 * @param {Cesium.CallbackProperty} positionData - Dynamische posities voor de vorm
 * @returns {Cesium.Entity} De getekende vorm
 */
function drawShape(positionData) {
    let shape;

    if (drawingMode === "line") {
        shape = viewer.entities.add({
            polyline: {
                positions: positionData,
                clampToGround: true,
                width: 3,
            },
        });
    } else if (drawingMode === "polygon") {
        // Teken een polygon met de kleur van het geselecteerde bouwblok
        shape = viewer.entities.add({
            polygon: {
                hierarchy: positionData,
                extrudedHeight: selectedHeight,
                material: selectedBlockColor,  // Gebruikt kleur van geselecteerd BlockType
            },
        });
    }

    return shape;
}


function setupInputActions() {
    viewer.cesiumWidget.screenSpaceEventHandler.removeInputAction(
        Cesium.ScreenSpaceEventType.LEFT_DOUBLE_CLICK,
    );

    //Deze variabelen worden binnen deze functie gedeclareerd. Dit is vreemd, want je zou zeggen dat
    //wanneer de functie voorbij is, de variabelen uit het geheugen worden verwijderd. Dit is in dit
    //geval niet zo, omdat de variabelen worden gebruikt in de inline functies hieronder. Daardoor
    //blijven ze bestaan. Op zich is dit handig, omdat de variabelen nu niet vanuit de globale
    //scope bereikbaar zijn. Wel moet je letten op leesbaarheid. Soms is het handiger om de variabelen
    //wel globaal te zetten.
    let activeShapePoints = [];
    let activeShape;
    let floatingPoint;
    const handler = new Cesium.ScreenSpaceEventHandler(viewer.canvas);

    handler.setInputAction(function (event) {
        // We use `viewer.scene.globe.pick here instead of `viewer.camera.pickEllipsoid` so that
        // we get the correct point when mousing over terrain.
        const ray = viewer.camera.getPickRay(event.position);
        const earthPosition = viewer.scene.globe.pick(ray, viewer.scene);
        // `earthPosition` will be undefined if our mouse is not over the globe.
        if (Cesium.defined(earthPosition)) {
            if (activeShapePoints.length === 0) {
                floatingPoint = createPoint(earthPosition);
                activeShapePoints.push(earthPosition);
                const dynamicPositions = new Cesium.CallbackProperty(function () {
                    if (drawingMode === "polygon") {
                        return new Cesium.PolygonHierarchy(activeShapePoints);
                    }
                    return activeShapePoints;
                }, false);
                activeShape = drawShape(dynamicPositions);
            }
            activeShapePoints.push(earthPosition);
            createPoint(earthPosition);
        }
    }, Cesium.ScreenSpaceEventType.LEFT_CLICK);

    // Set an action for when the left mouse button is clicked while holding the CTRL key
    handler.setInputAction(function (event) {
        var pickedObject = viewer.scene.pick(event.position); // For hover effect
        if (Cesium.defined(pickedObject)) {
            const entity = viewer.entities.getById(pickedObject.id.id);
            // entity.polygon.material.color = Cesium.Color.YELLOW;
            create3DObject(entity, 20);
            console.log(entity);
            // Optionally, highlight the polygon or show any other UI feedback
        }
    }, Cesium.ScreenSpaceEventType.LEFT_CLICK, Cesium.KeyboardEventModifier.CTRL);

    handler.setInputAction(function (event) {
        if (Cesium.defined(floatingPoint)) {
            const ray = viewer.camera.getPickRay(event.endPosition);
            const newPosition = viewer.scene.globe.pick(ray, viewer.scene);
            if (Cesium.defined(newPosition)) {
                floatingPoint.position.setValue(newPosition);
                activeShapePoints.pop();
                activeShapePoints.push(newPosition);
            }
        }
    }, Cesium.ScreenSpaceEventType.MOUSE_MOVE);

    // Redraw the shape so it's not dynamic and remove the dynamic shape.
    function terminateShape() {
        activeShapePoints.pop();

        console.log(activeShapePoints)
        SendPolygon(activeShapePoints, currentBuildingBlock, drawShape(activeShapePoints));

        drawShape(activeShapePoints);
        viewer.entities.remove(floatingPoint);
        viewer.entities.remove(activeShape);
        floatingPoint = undefined;
        activeShape = undefined;
        activeShapePoints = [];
    }

    handler.setInputAction(function (event) {
        terminateShape();
    }, Cesium.ScreenSpaceEventType.RIGHT_CLICK);
}

// Function to convert Cartesian coordinates to latitude and longitude
function cartesianToLatLon(cartesianPosition) {
    const cartographic = Cesium.Cartographic.fromCartesian(cartesianPosition);
    // Convert radians to degrees
    const lon = cartographic.longitude;
    const lat = cartographic.latitude;

    return {lat, lon};
}

function create3DObject(basePolygon, height) {
    if (basePolygon.polygon.extrudedHeight == undefined) {
        basePolygon.polygon.extrudedHeight = height;
    }

    //Deze extrudedHeight komt heel goed van pas
    basePolygon.polygon.extrudedHeight *= 1.5;
}

async function SendPolygon(points, type, entity) {
    const coordinates = points.map(point => {
        const coords = cartesianToLatLon(point);
        return {
            latitude: Cesium.Math.toDegrees(coords.lat),
            longitude: Cesium.Math.toDegrees(coords.lon)
        };
    });

    const data = {
        mapId: mapId,
        blockCode: type,
        coordinates: coordinates,
        height: entity.polygon.extrudedHeight?._value || 0
    };

    try {
        const response = await fetch("http://localhost:8080/block/send", {
            method: 'POST',
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(data)
        });
        const content = await response.json();
        console.log(response)

        updateInformationPanel(content);
        if (!response.ok) {
            throw new Error('Response status:' + response.status);
        }
    } catch (error) {
        console.log(error.message)
    }
    getInfo();

}

async function post(url, data) {
    try {
        const response = await fetch(url, {
            method: "POST",
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(data)
        });
        if (!response.ok) {
            throw new Error('Response status:' + response.status);
        }
    } catch (error) {
        console.error(error.message)
    }

}

async function get(url) {
    try {
        const response = await fetch(url);
        if (!response.ok) {
            throw new Error('Response status:' + response.status);
        }
        const content = await response.json();
        console.log(content);
        return content;
    } catch (error) {
        console.error(error.message)
    }

}


async function createMap() {
    mapName = prompt("Please enter a name for your map");

    try {
        const response = await fetch("http://localhost:8080/map/create", {
            method: "POST",
            headers: {'Content-Type': 'text/plain'},
            body: mapName
        });
        viewer.entities.removeAll();
        addSpoordokPoly()
        viewer.dataSources.removeAll();
        const content = await response.json();
        console.log(content)
        mapId = content.id;
        resetInformationPanel();
        resetGoals();
        if (!response.ok) {
            throw new Error('Response status:' + response.status);
        }
    } catch (error) {
        console.error(error.message)
    }
}


/*//TODO: this can probably be removed
function saveMap(){
    post("http://localhost:8080/map/save", mapId)
}*/

async function loadMap() {
    let name = prompt("Please enter the name of the map you want to load");
    try {
        const response = await fetch("http://localhost:8080/map/load", {
            method: "POST",
            headers: {'Content-Type': 'text/plain'},
            body: name
        });
        const content = await response.json();
        console.log(content);
        mapId = content.id;


        const featureCollection = {
            type: "FeatureCollection",
            features: content.blocks.map(block => ({
                type: "Feature",
                properties: {
                    id: block.id,
                    height: block.height,
                    blockCode: block.blockType.blockCode
                },
                geometry: block.geometry
            }))
        };
        viewer.entities.removeAll();
        addSpoordokPoly()
        viewer.dataSources.removeAll();


        Cesium.GeoJsonDataSource.load(featureCollection)
            .then(dataSource => {
                viewer.dataSources.add(dataSource);

                dataSource.entities.values.forEach(entity => {
                    entity.polygon.extrudedHeight = entity.properties.height.getValue();

                    console.log(entity.properties.blockCode)

                    entity.polygon.material = buildingBlocks[entity.properties.blockCode].color;
                    entity.polygon.outline = false;
                })
            });

        getInfo()
        updateGoals(content)

        if (!response.ok) {
            throw new Error('Response status:' + response.status);
        }
    } catch (error) {
        console.error(error.message)
    }

}

async function getInfo() {
    const response = await get("http://localhost:8080/info/get");
    updateInformationPanel(response);
}

function setHeight() {
    selectedHeight = document.getElementById("heightInput").value;
}


function updateInformationPanel(content) {
    document.getElementById("total-cost").textContent = Number(content.currentCost).toFixed(2);
    document.getElementById("total-yield").textContent = Number(content.currentYield).toFixed(2);
    document.getElementById("total-residents").textContent = content.currentResidents;
    document.getElementById("total-livability").textContent = content.currentLivability;
    document.getElementById("total-workplaces").textContent = content.currentWorkplaces;
    document.getElementById("green-percentage").textContent = Number(content.currentGreenPercentage).toFixed(2);
    document.getElementById("workplace-percentage").textContent = Number(content.currentWorkplacePercentage).toFixed(2);
    document.getElementById("total-parkingspots").textContent = Number(content.currentParkingSpots).toFixed(2);
}

function resetInformationPanel() {
    document.getElementById("total-cost").textContent = "0";
    document.getElementById("total-yield").textContent = "0";
    document.getElementById("total-residents").textContent = "0";
    document.getElementById("total-livability").textContent = "0";
    document.getElementById("total-workplaces").textContent = "0";
    document.getElementById("green-percentage").textContent = "0";
    document.getElementById("workplace-percentage").textContent = "0";
    document.getElementById("total-parkingspots").textContent = "0";
}

function updateGoals(content) {
    document.getElementById("total-cost-goal").textContent = content.cost;
    document.getElementById("total-yield-goal").textContent = content.yield;
    document.getElementById("total-residents-goal").textContent = content.residents;
    document.getElementById("total-livability-goal").textContent = content.livability;
    document.getElementById("total-workplaces-goal").textContent = content.workplaces;
    document.getElementById("green-percentage-goal").textContent = content.greenPercentage;
    document.getElementById("workplace-percentage-goal").textContent = content.workplacePercentage;
    document.getElementById("total-parkingspots-goal").textContent = content.parkingSpots;
}

function resetGoals() {
    document.getElementById("total-cost-goal").textContent = "100000";
    document.getElementById("total-yield-goal").textContent = "100000";
    document.getElementById("total-residents-goal").textContent = "3000";
    document.getElementById("total-livability-goal").textContent = "100";
    document.getElementById("total-workplaces-goal").textContent = "500";
    document.getElementById("green-percentage-goal").textContent = "20";
    document.getElementById("workplace-percentage-goal").textContent = "20";
    document.getElementById("total-parkingspots-goal").textContent = "4500";
}


function openModal() {
    document.getElementById("modal").style.display = "block";
}

function closeModal() {
    document.getElementById("modal").style.display = "none";
}

async function submitForm() {
    if (mapId != null) {
        const data = {
            mapId: mapId,
            cost: document.getElementById("costInput").value,
            yield: document.getElementById("yieldInput").value,
            residents: document.getElementById("residentsInput").value,
            livability: document.getElementById("livabilityInput").value,
            workplaces: document.getElementById("workplacesInput").value,
            greenPercentage: document.getElementById("greenPercentageInput").value,
            workplacePercentage: document.getElementById("workplacePercentageInput").value,
            parkingSpots: document.getElementById("parkingspotsInput").value
        }
        post("http://localhost:8080/map/setGoals", data);
        updateGoals(data);
        closeModal();
    }
}

