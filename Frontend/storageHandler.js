const rolename = "role"
function setRole(role) {
   localStorage.setItem(rolename, role);
}

function getRole() {
  return localStorage.getItem(rolename);
}

const cetificatename = "cetificate"
function setRequestedCetificate(cetificate) {
   localStorage.setItem(cetificatename, cetificate);
}

function setRequestedCetificate() {
  return localStorage.getItem(cetificatename);
}

function removeRequestedCetificate() {
  localStorage.removeItem(cetificatename);
}

const studentname = "student"
function setRequestedCetificate(student) {
   localStorage.setItem(studentname, student);
}

function setRequestedCetificate() {
  return localStorage.getItem(studentname);
}

function removeRequestedCetificate() {
  localStorage.removeItem(studentname);
}
