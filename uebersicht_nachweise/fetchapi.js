function neuenAzubianlegen(){
    fetch("https://localhost:8080/user")
        .then(response=>{
            if (!response.ok){
                throw new Error("Network response was not ok");
            }
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