function addRowWithValue(day, activitie, hours) {
    //Add Row
    document.getElementById( 'tableContent ' + day.toLowerCase() +' top')
                .insertAdjacentHTML("beforeend",
                    `<tr class="tableContent `+ day.toLowerCase() +`">
                        <td>
                            <textarea class="trainingActivities" type="text" value="` + activitie +`" readonly></textarea>
                        </td>
                        <td>
                            <textarea class="trainingActivitiesHours" type="text" value="` + hours +`" readonly></textarea>
                        </td>
                     </tr>`);

    //Add Row Span
    let tableWeekDay = document.getElementById('tableWeekDay' + day);
    let trainingTotalHours = document.getElementById('trainingTotalHours' + day)
    let department = document.getElementById('department' + day)

    let newtableWeekDayRowSpan = (tableWeekDay.rowSpan === undefined) ? 2 : tableWeekDay.rowSpan + 1;
    let newTrainingTotalHoursRowSpan = (trainingTotalHours.rowSpan === undefined) ? 2 : trainingTotalHours.rowSpan + 1;
    let newDepartmentRowSpan = (department.rowSpan === undefined) ? 2 : department.rowSpan + 1;

    tableWeekDay.rowSpan = newtableWeekDayRowSpan;
    trainingTotalHours.rowSpan = newTrainingTotalHoursRowSpan;
    department.rowSpan = newDepartmentRowSpan;
}

function acceptCertificate(accepted){
    let message = document.getElementById("comment").value;
    let id = 2;
    let apiCall = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body:{
            "acknowledged": accepted,
            "message": message
        }
    }

    let response = fetch(host + '/proof/' + id +'/acknowledgement', apiCall)
        .then(res => res.json());
}

function GoToOverview() {

}
