function addContentRow(day) {
    //Add Row
    document.getElementById(day + 'AddRow')
                .insertAdjacentHTML("beforebegin",
                    `<tr class="tableContent">
                        <td>
                            <textarea class="trainingActivities" type="text" placeholder="Hier ist deine Textbox"></textarea>
                        </td>
                        <td>
                            <textarea class="trainingActivitiesHours" type="text" placeholder="Einzelstunden"></textarea>
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


window.onerror = function(message, source, lineno, colno, error) {
    console.error("Error:", message, "at", source, "line:", lineno, "column:", colno, error);
    return true; // Prevent default error handling
};