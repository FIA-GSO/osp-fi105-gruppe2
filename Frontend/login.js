async function handleSubmit(event) {
    event.preventDefault();
    let json = getJsonFromForm(event.target);

    let apiCall = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: {
            'password': json.password
        }
    }

    let result = await fetch(host + '/session/' + json.name, apiCall)
    let response = await result.body;

    if(response) {
        document.getElementById('errorLabel').insertAdjacentHTML("afterbegin", response.error);
        return;
    }

    //Session speichern
    setSession(response);

    // User speichern
    setUsername(json.name);

    // Rolle speichern
    let user = fetch(host + '/user/' + json.name)
    .then(res => res.json());
    setRole(user.role);
    
    //Zur Seite gehen
    switch(user.role) {
        case "Azubi":
            window.location.href = 'nachweis_uebersicht.html';
            break;
            default:
                window.location.href = 'azubi_uebersicht.html';
                break;
    } 
}

function getJsonFromForm(form) {
    let formData = new FormData(form);
    let object = Object.fromEntries(formData);
    return object
}

function handlePasswortVergessen(){
    let text;
    let email = prompt("Bitte gib deine Email zum Zurücksetzten des Passworts an:");

    if (email == null || email == "") {
        return;
    }

    if(!email.toLowerCase()
    .match(
      /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|.(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
    )){
        text = "Die Email hat ein falsches Format falsch";
    } else {
      text = "We will send you a new Password to "+ email;
    }

    alert(text);
}

window.onload = function() {
    let form = document.getElementById('loginForm');
    form.addEventListener('submit',handleSubmit);

    let passwortVergessen = document.getElementById('passwortVergessenLink');
    passwortVergessen.addEventListener('click', handlePasswortVergessen);
}
