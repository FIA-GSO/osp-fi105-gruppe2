//add tablerow
function  addTablerowForAzubiView(name, ausbilder) {
    let table = document.getElementById("table");
    let row = table.insertRow(table.rows.length);

    row.insertCell(0).innerHTML = "<a  id='name' href='#'>" + name + "</a>";
    row.insertCell(1).innerHTML = ausbilder.toString();
}