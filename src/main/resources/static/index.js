function init() {
    getChoices()
        .then(turnToJson)
        .then(json => createDraggableButtons(json));
}

async function getChoices() {
    try {
        return await fetch("http://localhost:8080/choices");
    } catch (e) {
        console.log(`Something went wrong at the fetch: ${e}`)
    }
}

async function turnToJson(response) {
    try {
        return await response.json();
    } catch (e) {
        console.log(`Turning response into json did not work: ${e}`);
    }
}

async function createDraggableButtons(json) {
    try {
        console.log(json);
        let counter = 0; // need to think about a nice way to get a unique id. perhaps have it generated from back-end
        json.forEach(diceName => {
            createBtn(diceName, counter);
        });
    } catch (e) {
        console.log(e);
    }
}

function createBtn(diceName, counter) {
    const btn = document.createElement("BUTTON");
    btn.innerText = diceName;
    btn.id = `${diceName}_${counter++}`;
    btn.name = diceName;
    btn.setAttribute("class", "btn-die");
    btn.setAttribute("draggable", "true");
    btn.addEventListener("dragstart", dragEvent => drag(dragEvent));
    document.getElementById("dicePool").appendChild(btn);
}

function diceInHand() {
    const diceCollection = [];
    hand.childNodes.forEach(node => {
        if ("BUTTON" === node.nodeName) {
            diceCollection.push(node.name);
        }
    })
    return diceCollection;
}

async function roll(diceNames) {
    let response = await fetch("http://localhost:8080/roll",
        {
            method: "POST",
            headers: {"Content-Type": "application/json"},
            body: JSON.stringify({dice: diceNames})
        });
    let json = await turnToJson(response);
    console.log(json);
    json.values.forEach(v => {
        addDiv(v.value0, v.value1);
    })
}

// unused for the moment
async function rollSpecificDice() {
    try {
        let url = defineUrl();
        let response = await fetch(url);
        let json = await response.json();
        let result = json.values[0].value0;
        let value = json.values[0].value1;
        addDiv(result, value);
    } catch (error) {
        console.log(`Something went wrong in the roll() function: ${error}`);
    }
}

// unused for the moment
function defineUrl() {
    let choice = document.getElementById("diceSelectId").value;
    return `http://localhost:8080/roll/${choice}`;
}

const addDiv = (result, value) => {
    let rolled = document.createElement("DIV");
    rolled.innerText = value === "NUMERIC"
        ? `Rolled a ${result}`
        : `Result is ${value}`;
    document.getElementById("diceResult").appendChild(rolled);
}

function allowDrop(e) {
    e.preventDefault();
}

function drag(e) {
    e.dataTransfer.setData("text", e.target.id);
}

function drop(e) {
    e.preventDefault();
    let elemId = e.dataTransfer.getData("text");
    e.target.appendChild(document.getElementById(elemId));
}

function toggleGlow() {
    // this is the only place that lights up, for now
    const hand = document.getElementById("diceInHand");
    console.log(`toggle glow ${hand.childElementCount}`);
    if (hand.childElementCount > 0) {
        hand.setAttribute("class", "ul-glow");
    } else {
        hand.setAttribute("class", "");
    }
}