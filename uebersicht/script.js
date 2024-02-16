
//Variables
const name = null;
const status= null;
const ausbilder = null;
const date = null;

// Test Variables
const test_name = " Test Schüler";
const  test_status= "OK";
const test_ausbilder = "Test Ausbilder";
const test_date = "99.99.9999";



//add tablerow
function  addTablerow() {
    let table = document.getElementById("table");
    let row = table.insertRow(table.rows.length);
    row.insertCell(0).innerHTML = test_name;
    row.insertCell(1).innerHTML = test_ausbilder;
    row.insertCell(2).innerHTML = test_date;
    row.insertCell(3).innerHTML = statusImage("OK");
}

function statusImage(status) {
    switch (status) {
        case "OK":
            let pic = document.createElement("img");
            pic.src = "files/correct.png";
            return pic;
        case "WAITING":
            return "files/waiting.png";
        case "WRONG":
            return "files/wrong.png";
        default:
            return "OK";
    }
}
