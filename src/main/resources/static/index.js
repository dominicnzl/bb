function populateList() {
    fetch("http://localhost:8080/choices")
        .then(res => res.json())
        .then(data => {
            console.log(data);
            for (const val of data) {
                console.log(val);
                const option = document.createElement("OPTION");
                option.value = val;
                option.text = val;
                document.getElementById("diceSelectId").appendChild(option);
            }
        });
}


async function roll() {
    try {
        let url = defineUrl();
        let response = await fetch(url);
        let json = await response.json();
        let result = json.values[0].value0;
        let value = json.values[0].value1;
        addDiv(result, value);
    } catch (error) {
        console.log(error);
    }
}

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