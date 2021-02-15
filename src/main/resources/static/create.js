'use strict';

const projectList = document.querySelector("#projectList"); 
const title = document.querySelector("#title"); 
const alert = document.querySelector("#onsuccess");
const modal = document.querySelector("#firstModal");

const createProject = () => {
    const userValue = title.value; 
    const projectId = null;

    let data = {

        id: projectId,
        projectName: userValue, 
    }

    fetch("http://localhost:8080/project/create",{
        method: "POST", 
        body: JSON.stringify(data),
        headers: {
            "Content-Type":"application/json",
            "Access-Control-Allow-Origin": "*"
        }
    })
    .then(response => response.json())
    .then(info => {
        console.log(info);
        alert.setAttribute("class", "alert alert-success"); 
        alert.innerHTML = "Project has been successfully created!"; 
        setTimeout( () => {
           alert.removeAttribute("class"); 
           alert.innerHTML = ""; 
        },2000);
        printProject(info.projectName);
    })
    .catch(err => console.error(`Code RED! ${err}`));
}
