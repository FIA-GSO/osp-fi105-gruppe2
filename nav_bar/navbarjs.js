window.onload = function() {
    let form = document.getElementById('loginForm');
    form.addEventListener('submit', handleSubmit);

    let passwortVergessen = document.getElementById('passwortVergessenLink');
    passwortVergessen.addEventListener('click', handlePasswortVergessen);
}


function GenerateNavBar(){

    let navbarHTML = ""
    let role = "Azubi"

    switch(role) {
            case "Azubi" :
                navbarHTML = "";
            break;
            case "Ausbilder" :
                navbarHTML = "";
            break;
            case "Prüfer/Lehrer" :
                navbarHTML = "";
            break;
            case "Administartor" :
                navbarHTML = "";
            break;
    }


    document.getElementById("navbarList")
    .insertAdjacentHTML("afterbegin", navbarHTML);
}
