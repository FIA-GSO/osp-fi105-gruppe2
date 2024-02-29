
//Variables
const name = null;
const status= null;
const ausbilder = null;
const date = null;

// Test Variables
const test_name = " Test Schüler";
const test_status= "ok";
const test_status_waiting= "waiting";
const test_status_wrong= "wrong";
const test_ausbilder = "Test Ausbilder";
const test_date = "99.99.9999";



//add tablerow
function  addTablerow(name, ausbilder, date, status) {
    let table = document.getElementById("table");
    let row = table.insertRow(table.rows.length);

    row.insertCell(0).innerHTML = name.toString();
    row.insertCell(1).innerHTML = ausbilder.toString();
    row.insertCell(2).innerHTML = date.toString();
    row.insertCell(3).setAttribute("id", "row_image" + checkForRowIncrement());
    let image = document.getElementById("row_image" + checkForRowIncrement());
    image.appendChild(statusImage(status));
}
// Creating Status Image
function statusImage(status) {
    switch (status) {
        case "ok":
            let pic = document.createElement("img");
            pic.src = "files/correct.png";
            return pic;
        case "waiting":
            let pic1 = document.createElement("img");
            pic1.src = "files/waiting.png";
            return pic1;
        case "wrong":
            let pic2 = document.createElement("img");
            pic2.src = "files/wrong.png";
            return pic2;
        default:
            return "-";
    }
}

//Check for Row Number
function checkForRowIncrement() {
    let table = document.getElementById("table");
    return table.rows.length
}

