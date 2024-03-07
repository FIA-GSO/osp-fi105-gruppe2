//Creds
const data = {
    password: 'root'
};


function neuenAzubianlegen(){
    fetch("https://localhost:8080/user")
        .then(response=>{
            if (!response.ok){
                throw new Error("Network response was not ok");
            }
            console.log('RESPONSE:',response);
            return response.json();
        })

}

function neuenAzubianlegen_test(){
    fetch("https://localhost:8080/user")
        .then(response=>{
            if (!response.ok){
                throw new Error("Network response was not ok");
            }
            return response.json();
        })

}

function fetchSession() {
    const  options = {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data),
    };
    fetch('http://10.133.14.146:8080/session/root', options)
        .then(response=>{
            console.log('RESPONSE:',response);
        })
}


// Create User
/* {
  "email": "email@example.com",
  "password": "asd/8+_:#",
  "role": "Azubi",
  "vorname": "string",
  "nachname": "string"
}*/
function  createUser() {
    let create_data = {
        email: 'email@example.com',
        password: "TESTESTTEST",
        role: "Azubi",
        vorname: "string",
        nachname: "string"
        cookie: "JSESSIONID=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJzZXNzaW9uIiwibmJmIjoxNzA5ODA4NTY2LCJpc3MiOiJuYWNod2Vpc2VCYWNrZW5kIiwiZXhwIjoxNzA5ODk0OTY1LCJ1c2VySWQiOjEsImlhdCI6MTcwOTgwODU2NX0.4P2f-XLQPzYOlW1NdfNyV_9uae1WonNGJtPwvcv9H0k"
    };

    const  options = {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(create_data),
    };
    fetch('http://10.133.14.146:8080/', options)
        .then(response=>{
            console.log('RESPONSE:',response);
        })
}

/*---

// Make a POST request with JSON data
fetch('https://api.example.com/submit', {
  method: 'POST', // specify the request method
  headers: {
    'Content-Type': 'application/json', // specify content type
    // add any other headers as needed
  },
  body: JSON.stringify({ key: 'value' }) // convert data to JSON format
})
.then(response => {
  if (!response.ok) {
    throw new Error('Network response was not ok');
  }
  return response.json(); // Parse the JSON data
})
.then(data => {
  // Work with the JSON data
  console.log(data);
})
.catch(error => {
  // Handle errors
  console.error('There was a problem with the fetch operation:', error);
});
----*/
