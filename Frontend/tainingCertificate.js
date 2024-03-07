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

function GetCertificateAsJson() {

    // var contentJson = {
    //     monday: {
    //       department: string
    //     },
    //     tuesday: {
    //       department: string
    //     },
    //     wednesday: {
    //       department: string
    //     },
    //     thursday: {
    //       department: string
    //     },
    //     friday: {
    //       department: string
    //     },
    //     saturday: {
    //         department: string
    //       },
    //     requested: false
    //   }

    function FilterTableContent(element) {
        return element.className.includes('tableContent')
    }

    function FilterActivities (element) {
        return element.className.includes('activitieContent')
    }

    function SetDepartmentForDay (day) {
        var xy = day;
        switch(day){
            case "monday":
            break;
            case "monday":
            break;
            case "monday":
            break;
            case "monday":
            break;
            case "monday":
            break;
            case "monday":
            break;
            case "monday":
            break;
            case "monday":
            break;
            case "monday":
            break;
        }
        
    }

    function SetDepartmentFor () {
        
    }

    let tableBody = document.getElementById('trainingCertificateTable').children[0];
    console.log(tableBody.className);

    // Array für das Json
    // var entrie = {
    //     entries: [
    //     {
    //       workDone: string,
    //       hours: 0
    //     }
    //   ],
    // }


    // Alle Rows mit Einträgen die obersten mit Gesamtstunden und Abteilung haben in der Klasse Top stehen
    let trainingCertificateTableContents =  Array.from(tableBody.children).filter(FilterTableContent);

    for(let i = 0; i <  trainingCertificateTableContents.length; i++) {
        // let  entrie = {
        //     entries: [
        //     {
        //       workDone: string,
        //       hours: 0
        //     }
        //   ],
        // }


        if(trainingCertificateTableContents[i].className.includes("top"))
        {
            let department = trainingCertificateTableContents[i].children[4].children[0].value;
            SetDepartmentForDay(trainingCertificateTableContents[i].className.replace("tableContent ", "").replace(" top", ""));
        }
        let activities = Array.from(trainingCertificateTableContents[i].children).filter(FilterActivities);


        for(let i = 0; i < activities.length; i + 2){
            let activitie = activities[i].nodeValue;
			let xy = activitie.classname;
            let timeForActivitie = activities[i + 1].nodeValue;
			let yx = timeForActivitie.classname;
        }
    }

    for (let i = 0; i < trainingCertificateTableContents.length; i++) {
        console.log(trainingCertificateTableContents[i].className);
        console.log(trainingCertificateTableContents[i]);
      }
}