const usernamename = "username"
function setUsername(name) {
   localStorage.setItem(usernamename, name);
}

function getUsername() {
  return localStorage.getItem(usernamename);
}


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

function getRequestedCetificate() {
  return localStorage.getItem(cetificatename);
}

function removeRequestedCetificate() {
  localStorage.removeItem(cetificatename);
}

const studentname = "student"
function setRequestedStudent(student) {
   localStorage.setItem(studentname, student);
}

function getRequestedStudent() {
  return localStorage.getItem(studentname);
}

function removeRequestedStudent() {
  localStorage.removeItem(studentname);
}

const sessionname = "session"
function setSession(session) {
   localStorage.setItem(sessionname, session);
}

function getSession() {
  return localStorage.getItem(sessionname);
}

