window.onload = function() {
    let createUserform = document.getElementById('createUserForm');
    createUserform.addEventListener('submit', handleCreateUserSubmit);

    let createTrainingForm = document.getElementById('createTrainingForm');
    createTrainingForm.addEventListener('submit', handlecreateTrainingSubmit);
}

function handleCreateUserSubmit (event) {
    event.preventDefault();
    let json = getJsonFromForm(event.target);
}

function handlecreateTrainingSubmit (event) {
    event.preventDefault();
    let json = getJsonFromForm(event.target);
}

function getJsonFromForm(form) {
    let formData = new FormData(form);
    let object = Object.fromEntries(formData);
    return JSON.stringify(object);
}
