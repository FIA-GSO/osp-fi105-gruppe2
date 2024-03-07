function addContentRow(day) {
    //Add Row
    document.getElementById(day + 'AddRow')
                .insertAdjacentHTML("beforebegin",
                    `<tr class="tableContent `+ day.toLowerCase() +`">
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

function GetCertificateAsJson(proofRequested) {
    function GetEntries (day) {
      
        var content = document.getElementsByClassName("tableContent " + day.toLowerCase()); 
        let entries = new Array();;
        for(let i = 0; i < content.length; i++){
            let workDone = content[i].children.length > 3 ? content[i].children[1].children[0].value : content[i].children[0].children[0].value;
            let hours = content[i].children.length > 3 ? content[i].children[2].children[0].value :  content[i].children[1].children[0].value;
                    entries.push(
                        {
                            workDone: workDone,
                            hours: hours
                        }
                    );
                }
                return entries;
    }

    function GetDepartment (day) {
        var department = document.getElementById("department" + day).children[0].value;
        return department;
    }

      var contentJson = {
        monday: {
            entries: GetEntries('Monday'),
            department: GetDepartment('Monday')
        },
        tuesday: {
            entries: GetEntries('Tuesday'),
            department: GetDepartment('Tuesday')
        },
        wednesday: {
            entries: GetEntries('Wednesday'),
            department: GetDepartment('Wednesday')
        },
        thursday: {
            entries: GetEntries('Thursday'),
            department: GetDepartment('Thursday')
        },
        friday: {
            entries: GetEntries('Friday'),
            department: GetDepartment('Friday')
        },
        saturday: {
            entries: GetEntries('Saturday'),
            department: GetDepartment('Saturday')
          },
        requested: proofRequested
      }

      return contentJson;
}
