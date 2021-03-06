function init() {
    getChoicesAsJson()
        .then(createDraggableButtons)
        .catch(handleErrors);
}

async function getChoicesAsJson() {
    let choices = await fetch("http://localhost:8080/choices");
    return await choices.json();
}

async function createDraggableButtons(json) {
    try {
        console.log(json);
        json.forEach(diceName => createBtn(diceName));
    } catch (e) {
        console.log(e);
    }
}

function createBtn(diceName) {
    const btn = document.createElement("BUTTON");
    btn.innerText = diceName;
    btn.id = `${diceName}`;
    btn.name = diceName;
    btn.setAttribute("class", "btn-die");
    btn.setAttribute("draggable", "true");
    btn.addEventListener("dragstart", b => drag(b));
    document.getElementById("dicePool").appendChild(btn);
}

function handleErrors(error) {
    console.log(`Something went wrong during init(): ${error}`);
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
    let json = await response.json();
    console.log(json);
    json.values.forEach(v => {
        let [result, diceType] = [v.value0, v.value1];
        console.log(`result is ${result} and type is ${diceType}`)
        addDiv(result, diceType);
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
    // this is the only place that lights up, for now. The only child elements are Dice, for the moment.
    const hand = document.getElementById("diceInHand");
    let shouldGlow = hand.childElementCount > 0
        ? "ul-glow"
        : "";
    hand.setAttribute("class", shouldGlow);
}
