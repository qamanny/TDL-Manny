'use strict';


const printProject = (title) => {
    let project = document.createElement("li");
    let text = document.createTextNode(`${title}`);
    project.appendChild(text);
    projectList.appendChild(project);
}


const readAll = () => {
    fetch("http://localhost:8080/project/readAll")
        .then((response) => {
            if (response.status !== 200) {
                throw new Error("I don't have a status of 200");
            } else {
                console.log(response);
                console.log(`response is OK (200)`);
                response.json().then((infofromserver) => {
                    console.log(infofromserver);
                    console.log(infofromserver.data);
                    
                })
            }
        }).catch((err) => {
            console.error(err);
        })
}


readAll(); 