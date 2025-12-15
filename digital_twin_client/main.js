// const buildingBlocks = {
//     A: {
//         name: "Vrijstaand huis",
//         unit: "per m³",
//         costPerUnit: 500,
//         yieldPercentage: 12,
//         residentsPerUnit: 0.005,
//         sustainabilityScore: 4,
//         color: Cesium.Color.SANDYBROWN,
//         colorHex: "#F4A460",
//         isVolumetric: true,
//         icon: `<svg viewBox="0 0 24 24" fill="currentColor"><path d="M10 20v-6h4v6h5v-8h3L12 3 2 12h3v8z"/></svg>`
//     },
//     B: {
//         name: "Rijtjeswoning",
//         unit: "per m³",
//         costPerUnit: 400,
//         yieldPercentage: 8,
//         residentsPerUnit: 0.01,
//         sustainabilityScore: 6,
//         color: Cesium.Color.CORAL,
//         colorHex: "#FF7F50",
//         isVolumetric: true,
//         icon: `<svg viewBox="0 0 24 24" fill="currentColor"><path d="M12 3L2 12h3v8h6v-6h2v6h6v-8h3L12 3zm0 2.84L18 11v8h-2v-6h-8v6H6v-8l6-5.16z"/></svg>`
//     },
//     C: {
//         name: "Appartement",
//         unit: "per m³",
//         costPerUnit: 300,
//         yieldPercentage: 12,
//         residentsPerUnit: 0.006,
//         sustainabilityScore: 5,
//         color: Cesium.Color.LIGHTBLUE,
//         colorHex: "#ADD8E6",
//         isVolumetric: true,
//         icon: `<svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 -960 960 960" width="24" fill="currentColor"><path d="M120-120v-560h160v-160h400v320h160v400H520v-160h-80v160H120Zm80-80h80v-80h-80v80Zm0-160h80v-80h-80v80Zm0-160h80v-80h-80v80Zm160 160h80v-80h-80v80Zm0-160h80v-80h-80v80Zm0-160h80v-80h-80v80Zm160 320h80v-80h-80v80Zm0-160h80v-80h-80v80Zm0-160h80v-80h-80v80Zm160 480h80v-80h-80v80Zm0-160h80v-80h-80v80Z"/></svg>`
//     },
//     D: {
//         name: "Bedrijfsgebouw",
//         unit: "per m³",
//         costPerUnit: 200,
//         yieldPercentage: 15,
//         residentsPerUnit: 0.018,
//         sustainabilityScore: 2,
//         color: Cesium.Color.DARKGRAY,
//         colorHex: "#A9A9A9",
//         isVolumetric: true,
//         icon: `<svg viewBox="0 0 24 24" fill="currentColor"><path d="M12 7V3H2v18h20V7H12zM6 19H4v-2h2v2zm0-4H4v-2h2v2zm0-4H4V9h2v2zm0-4H4V5h2v2zm4 12H8v-2h2v2zm0-4H8v-2h2v2zm0-4H8V9h2v2zm0-4H8V5h2v2zm10 12h-8v-2h2v-2h-2v-2h2v-2h-2V9h8v10zm-2-8h-2v2h2v-2zm0 4h-2v2h2v-2z"/></svg>`
//     },
//     E: {
//         name: "Park/groen",
//         unit: "per m²",
//         costPerUnit: 150,
//         yieldPercentage: 0,
//         residentsPerUnit: 0,
//         sustainabilityScore: 10,
//         color: Cesium.Color.LIMEGREEN,
//         colorHex: "#32CD32",
//         isVolumetric: false,
//         icon: `<svg xmlns="http://www.w3.org/2000/svg" height="24" width="24" viewBox="0 -960 960 960" fill="currentColor"><path d="M180-520q-26 0-43-17t-17-43q0-26 17-43t43-17q26 0 43 17t17 43q0 26-17 43t-43 17ZM120-80v-200h-40v-160q0-17 11.5-28.5T120-480h120q17 0 28.5 11.5T280-440v160h-40v120h320v-200h-70q-71 0-120.5-49.5T320-530q0-53 28.5-94.5T422-686q11-65 60.5-109.5T600-840q68 0 117.5 44.5T778-686q45 20 73.5 61.5T880-530q0 71-49.5 120.5T710-360h-70v200h200v80H120Zm370-360h220q38 0 64-26t26-64q0-27-14.5-49T746-612l-42-18-6-44q-6-37-33.5-61.5T600-760q-37 0-64.5 24.5T502-674l-6 44-42 18q-25 11-39.5 33T400-530q0 38 26 64t64 26Zm110-160Z"/></svg>`
//     },
//     F: {
//         name: "Wegen",
//         unit: "per m²",
//         costPerUnit: 100,
//         yieldPercentage: 5,
//         residentsPerUnit: 0,
//         sustainabilityScore: 8,
//         color: Cesium.Color.DIMGRAY,
//         colorHex: "#696969",
//         isVolumetric: false,
//         icon: `<svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 -960 960 960" width="24" fill="currentColor"><path d="M720-40v-120H600v-80h120v-120h80v120h120v80H800v120h-80Zm0-400v-360h80v360h-80ZM160-160v-640h80v640h-80Zm280-480v-160h80v160h-80Zm0 240v-160h80v160h-80Zm0 240v-160h80v160h-80Z"/></svg>`
//     },
//     G: {
//         name: "Parkeerplaatsen",
//         unit: "per m²",
//         costPerUnit: 100,
//         yieldPercentage: 10,
//         residentsPerUnit: 0,
//         sustainabilityScore: 6,
//         color: Cesium.Color.LIGHTGRAY,
//         colorHex: "#D3D3D3",
//         isVolumetric: false,
//         icon: `<svg viewBox="0 0 24 24" fill="currentColor"><path d="M13 3H6v18h4v-6h3c3.31 0 6-2.69 6-6s-2.69-6-6-6zm.2 8H10V7h3.2c1.1 0 2 .9 2 2s-.9 2-2 2z"/></svg>`
//     },
//    H : {
//         name: "Parkeerplaatsen overdekt",
//         unit: "per m²",
//         costPerUnit: 1500,
//         yieldPercentage: 15,
//         residentsPerUnit: 0,
//         sustainabilityScore: 10,
//         color: Cesium.Color.SLATEGRAY,
//         colorHex: "#708090",
//         isVolumetric: false,
//         icon: `<svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 -960 960 960" width="24" fill="currentColor"><path d="M160-80q-33 0-56.5-23.5T80-160v-640q0-33 23.5-56.5T160-880h640q33 0 56.5 23.5T880-800v640q0 33-23.5 56.5T800-80H160Zm0-80h640v-640H160v640Zm200-240q-17 0-28.5-11.5T320-440q0-17 11.5-28.5T360-480q17 0 28.5 11.5T400-440q0 17-11.5 28.5T360-400Zm240 0q-17 0-28.5-11.5T560-440q0-17 11.5-28.5T600-480q17 0 28.5 11.5T640-440q0 17-11.5 28.5T600-400ZM200-516v264q0 14 9 23t23 9h16q14 0 23-9t9-23v-48h400v48q0 14 9 23t23 9h16q14 0 23-9t9-23v-264l-66-192q-5-14-16.5-23t-25.5-9H308q-14 0-25.5 9T266-708l-66 192Zm106-64 28-80h292l28 80H306ZM160-800v640-640Zm120 420v-120h400v120H280Z"/></svg>`
//     }
// };

let buildingBlocks = {};

async function loadBuildingBlocks() {
    try {
        const response = await fetch('http://localhost:8080/api/blocktypes');
        const blockTypes = await response.json();

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

        console.log('Building blocks loaded from database.', buildingBlocks);
        return true;
    } catch (error) {
        console.error('Error loading building blocks:', error);
        return false;
    }
}



let currentBuildingBlock = null;
let placedBuildings = [];

// UI SETUP
function createBuildingUI() {
    const container = createUIContainer();
    document.body.appendChild(container);
}

function createUIContainer() {
    const container = document.createElement('div');
    container.id = 'building-ui-container';
    container.className = 'd-flex flex-column gap-3';

    // Bouwblokken Panel
    container.appendChild(createBuildingPanel());

    // Info Panel
    container.appendChild(createInfoPanel());

    // Stats Panel
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

    Object.keys(buildingBlocks).forEach(key => {
        const block = buildingBlocks[key];
        const col = document.createElement('div');
        col.className = 'col-6';

        const button = document.createElement('button');
        button.className = 'building-btn btn w-100 d-flex flex-column align-items-center gap-2 p-3 rounded-3';
        button.id = `block-${key}`;
        button.onclick = () => selectBuildingBlock(key);

        button.innerHTML = `
            <div class="btn-icon">${block.icon}</div>
            <span class="btn-code badge rounded-pill px-2">${key}</span>
            <div class="btn-label text-center lh-sm">${block.name}</div>
            <div class="color-indicator" style="background-color: ${block.colorHex};"></div>
        `;

        col.appendChild(button);
        grid.appendChild(col);
    });

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
            icon: `<svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 -960 960 960" width="24"><path d="M600-120q-118 0-210-67T260-360H120v-80h122q-3-24-2.5-44.5T242-520H120v-80h140q38-106 130-173t210-67q69 0 130.5 24.5T840-748l-57 56q-37-32-83.5-50T600-760q-85 0-152 44.5T347-600h253v80H323q-4 27-3 47.5t3 32.5h277v80H347q34 71 101 115.5T600-200q53 0 99.5-18t83.5-50l57 56q-48 43-109.5 67.5T600-120Z"/></svg>`
        },
        {
            id: 'total-yield',
            label: 'Opbrengst',
            icon: `<svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 -960 960 960" width="24"><path d="M336-120q-91 0-153.5-62.5T120-336q0-38 13-74t37-65l142-171-97-194h530l-97 194 142 171q24 29 37 65t13 74q0 91-63 153.5T624-120H336Zm144-200q-33 0-56.5-23.5T400-400q0-33 23.5-56.5T480-480q33 0 56.5 23.5T560-400q0 33-23.5 56.5T480-320Zm-95-360h190l40-80H345l40 80Zm-49 480h288q57 0 96.5-39.5T760-336q0-24-8.5-46.5T728-423L581-600H380L232-424q-15 18-23.5 41t-8.5 47q0 57 39.5 96.5T336-200Z"/></svg>`
        },
        {
            id: 'total-residents',
            label: 'Bewoners',
            icon: `<svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 -960 960 960" width="24"><path d="M360-80v-529q-91-24-145.5-100.5T160-880h80q0 83 53.5 141.5T430-680h100q30 0 56 11t47 32l181 181-56 56-158-158v478h-80v-240h-80v240h-80Zm120-640q-33 0-56.5-23.5T400-800q0-33 23.5-56.5T480-880q33 0 56.5 23.5T560-800q0 33-23.5 56.5T480-720Z"/></svg>`
        },
        {
            id: 'total-livability',
            label: 'Leefbaarheid score',
            icon: `<svg xmlns="http://www.w3.org/2000/svg" height="24" viewBox="0 -960 960 960" width="24"><path d="M280-280h80v-200h-80v200Zm320 0h80v-400h-80v400Zm-160 0h80v-120h-80v120Zm0-200h80v-80h-80v80ZM200-120q-33 0-56.5-23.5T120-200v-560q0-33 23.5-56.5T200-840h560q33 0 56.5 23.5T840-760v560q0 33-23.5 56.5T760-120H200Zm0-80h560v-560H200v560Zm0-560v560-560Z"/></svg>`
        }
    ];

    stats.forEach(stat => {
        const statItem = document.createElement('div');
        statItem.className = 'stat-item d-flex justify-content-between align-items-center p-3 rounded-3';

        statItem.innerHTML = `
            <div class="d-flex align-items-center gap-2">
                <span class="stat-icon">${stat.icon}</span>
                <span class="stat-label text-secondary fw-medium small">${stat.label}</span>
            </div>
            <div class="stat-value h5 mb-0 fw-bold" id="${stat.id}">&euro;0</div>
        `;

        statsGrid.appendChild(statItem);
    });

    cardBody.appendChild(header);
    cardBody.appendChild(statsGrid);
    panel.appendChild(cardBody);

    return panel;
}

// BOUWBLOK SELECTIE
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


function createAIUI() {
    const container = createUIAIContainer();
    document.body.appendChild(container);
}

function createUIAIContainer() {
    const container = document.createElement('div');
    container.id = 'ai-ui-container';
    container.className = 'd-flex flex-column gap-3';

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

        fetch('http://localhost:8080/upload', {
            method: 'POST',
            body: formData
        }).then(res => res.json())
            .then(data => console.log(data))
            .catch(error => console.log(error));
    })
}

function createResultsArea() {
    const panel = document.createElement('div');
    panel.id = 'results-area-panel';

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

async function setup() {
    //TODO: Remove if websocket isn't needed
    //connect()

    const loaded = await loadBuildingBlocks();

/*    if (!loaded) {
        alert('Kon bouwblokken niet laden van de server!');
        return;
    }*/

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
    });


    // viewer.imageryLayers.removeAll();
    createAIUI();
    viewer.imageryLayers.addImageryProvider(osm);

    //Improves tile quality
    viewer.scene.globe.maximumScreenSpaceError = 1;

    // console.log(viewer.scene.globe.maximumScreenSpaceError);

    const condo1 = createBox(200, 300, 50, 40, 70, 0, "building_tex.jpg");
    measure = createBox(0, 0, 3, 3, 30, 0, Cesium.Color.RED);

    var carX = 230;
    var carY = 78;

    const car = createBox(carX, carY, 5, 2, 1.5, 0, Cesium.Color.BLUE);

    function moveCar() {
        carX++;
        carY += 0.35;
        moveEntity(car, carX, carY);
        setTimeout(() => {
            moveCar();
        }, 150);
    }

    moveCar();

    createPolygonFromXYs([
        [250, 72], //linksonder-onder
        [230, 85], //linksonder-boven
        [510, 185], //midden-links-boven
        [520, 175] //midden-links-onder
    ], Cesium.Color.WHITE);

    const redPolygon = viewer.entities.add({
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

    createModel("Cesium_Man.glb", latlonFromXY(220, 70), 0);

    createModel("strange_building.glb", latlonFromXY(240, 70), 0);

    setupInputActions();

    createBuildingUI();
    console.log('UI should be created now');

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
        shape = viewer.entities.add({
            polygon: {
                hierarchy: positionData,
                material: selectedBlockColor,  // <-- gebruik nieuwe kleur
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
        drawShape(activeShapePoints);
        viewer.entities.remove(floatingPoint);
        viewer.entities.remove(activeShape);
        floatingPoint = undefined;
        activeShape = undefined;
        activeShapePoints = [];
    }
    handler.setInputAction(function (event) {
        terminateShape();

/*
        var xhr = new XMLHttpRequest();

        xhr.setRequestHeader("Accept", "application/json");
        xhr.setRequestHeader("Content-Type", "application/json");
        xhr.open("POST", "http://localhost:8080/map/create");
        xhttp.send({"title": "test", "content": "test"});
*/


        //TODO: this should update server and database
        //sendMessage("test", "test");
    }, Cesium.ScreenSpaceEventType.RIGHT_CLICK);
}

// x = verplaatsing in meters noord (+) / zuid (-)
// y = verplaatsing in meters oost (+) / west (-)
// top_right_lat = referentie-latitude (graden)
// top_left_lon = referentie-longitude (graden)

const top_right_lat = 5.77465380114684;
const top_left_lon = 53.194528716741345;

function latlonFromXY(xMeters, yMeters) {
    // gemiddelde meters per graad latitude ~111320
    const metersPerDegLat = 111320.0;

    // bereken nieuwe latitude (in graden)
    const newLat = top_right_lat + (xMeters / metersPerDegLat);

    // meters per graad longitude = ~111320 * cos(latitude_in_radians)
    const latRad = newLat * Math.PI / 180.0;
    const metersPerDegLon = 111320.0 * Math.cos(latRad);

    // voorkom deling door 0 vlak bij polen
    const newLon = top_left_lon + (yMeters / (metersPerDegLon || 1e-9));

    return { lat: newLat, lon: newLon };
}

var _box = 1;

//Color kan ook een pad zijn naar een afbeelding
//Let wel op dat afbeeldingen niet via UV-mapping gaan, en dat de afbeelding
//dus op elk vlak herhaald zal worden. Dit ziet er niet super uit.
//De oplossing is om een eigen model te maken met textures. Dit kan vrij
//simpel via Blender. Zie de volgende tutorial: https://www.youtube.com/watch?v=mURA2g1rOSc
function createBox(x, y, width, depth, height, rotation, color) {
    const cords = latlonFromXY(x, y);
    return createBoxLatLon(cords, width, depth, height, rotation, color);
}

function createBoxLatLon(cords, width, depth, height, rotation, color) {
    return viewer.entities.add({
        name: "Box_" + _box++,
        position: Cesium.Cartesian3.fromDegrees(cords.lat, cords.lon, height / 2.0),
        box: {
            dimensions: new Cesium.Cartesian3(width, depth, height),
            material: color
        }
    });
}

function createBoxXYZ(position, width, depth, height, rotation, color) {
    return viewer.entities.add({
        name: "Box_" + _box++,
        position: position,
        box: {
            dimensions: new Cesium.Cartesian3(width, depth, height),
            material: color,
            heightReference: Cesium.HeightReference.CLAMP_TO_GROUND //IMPORTANT
        }
    });
}

function moveEntity(entity, x, y) {
    const cords = latlonFromXY(x, y);
    entity.position = Cesium.Cartesian3.fromDegrees(cords.lat, cords.lon, entity.box.dimensions._value.z);
}

var _polygon = 1;

function createPolygonFromXYs(xyArray, color) {
    var degreeArray = [];
    xyArray.forEach(element => {
        const cords = latlonFromXY(element[0], element[1]);
        degreeArray.push(cords.lat);
        degreeArray.push(cords.lon);
    });

    const redPolygon = viewer.entities.add({
        name: "Polygon_" + _polygon++,
        polygon: {
            hierarchy: Cesium.Cartesian3.fromDegreesArray(degreeArray),
            material: color,
        },
    });
}

//Werkt alleen met glTF modellen!
//Als je OBJ-modellen wilt laden, moet je ze eerst naar glTF converten. Dit kan met Blender,
//maar ook via de volgende tool van Cesium: https://github.com/CesiumGS/obj2gltf
//!Let op bij gebruik van Blender! 3D-modellen die als .blend bestand worden opgeslagen kunnen
//embedded Python-code bevatten. Pas op dat dit niet tijdens het openen automatisch uitgevoerd
//wordt, want dit is een bekende attack vector voor exploits, etc.
function createModel(url, position, height) {

    const full_position = Cesium.Cartesian3.fromDegrees(
        position.lat,
        position.lon,
        height
    );

    const heading = Cesium.Math.toRadians(135);
    const pitch = 0;
    const roll = 0;
    const hpr = new Cesium.HeadingPitchRoll(heading, pitch, roll);
    const orientation = Cesium.Transforms.headingPitchRollQuaternion(
        full_position,
        hpr,
    );

    const entity = viewer.entities.add({
        name: url,
        position: full_position,
        orientation: orientation,
        model: {
            uri: url,
            minimumPixelSize: 128,
            maximumScale: 1,
        },
    });
    viewer.trackedEntity = entity;
}

// Function to convert Cartesian coordinates to latitude and longitude
function cartesianToLatLon(cartesianPosition) {
    const cartographic = Cesium.Cartographic.fromCartesian(cartesianPosition);
    // Convert radians to degrees
    const lon = cartographic.longitude;
    const lat = cartographic.latitude;

    return { lat, lon };
}

// Define grid size
const gridSize = 1.1; // Adjust this to your desired grid size

// Function to snap coordinates to the grid
function snapToGrid(position) {
    const snappedX = Math.round(position.x / gridSize) * gridSize;
    const snappedZ = Math.round(position.z / gridSize) * gridSize;

    return new Cesium.Cartesian3(snappedX, position.y, snappedZ);
}

function handleMouseClick(event) {
    const mousePosition = new Cesium.Cartesian2(event.clientX, event.clientY);
    //const ray = viewer.camera.getPickRay(mousePosition);
    const hitPosition = viewer.scene.pickPosition(mousePosition);

    // Check if the ray intersects the globe
    if (hitPosition) {
        var snappedPosition = snapToGrid(hitPosition);

        createBoxXYZ(snappedPosition, 1, 1, 1, 0, Cesium.Color.RED);
    }
}

function create3DObject(basePolygon, height) {
    if (basePolygon.polygon.extrudedHeight == undefined) {
        basePolygon.polygon.extrudedHeight = height;
    }

    //Deze extrudedHeight komt heel goed van pas
    basePolygon.polygon.extrudedHeight *= 1.5;
}


function post (url, data) {
    try {
        const response = fetch(url, {
            method: "POST",
            headers: {'Content-Type': 'application/json'},
            body: JSON.stringify(data)
        });
        if (!response.ok){
            throw new Error('Response status: ${response.status}');
        }
    }catch(error){
        console.error(error.message)
    }
}

async function get (url){
    try {
        const response = await fetch(url);
        if (!response.ok){
            throw new Error('Response status: ${response.status}');
        }
        const content = await response.json();
        console.log(content.toString());
    } catch(error){
        console.error(error.message)
    }

}


function createMap(){
    let name = prompt("Please enter a name for your map");
    post("http://localhost:8080/map/create", {title: name, content: "Test"})
}

function saveMap(){
    post("http://localhost:8080/map/save", {title: "save", content: "map"})
}

function loadMap(){
    get("http://localhost:8080/map/load")
        .then(data => console.log(data))
}

//Websocket setup

/*
const stompClient = new StompJs.Client({
    brokerURL: 'ws://localhost:8080/websocket'
})

stompClient.onConnect = (frame) => {
    console.log('Connected: ' + frame)
    stompClient.subscribe('/topic/messages', (message) => {
        console.log(JSON.stringify(JSON.parse(message.body), null, 2))
    })
}

stompClient.webSocketError = (error) => {
    console.error("websocket error", error)
};

stompClient.stompError = (frame) => {
    console.error('Broker error: ' + frame.headers['message'])
    console.error('details: ' + frame.body)
};

function connect(){
    stompClient.activate()
}

function disconnect(){
    stompClient.deactivate()
    console.log('Disconnected')
}

function sendMessage(title, content){
    console.log('broadcasting message')
    stompClient.publish({
        destination: '/messages/broadcast',
        body: JSON.stringify({
            'title': title,
            'content': content
        })
    })
}*/
