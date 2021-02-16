'use strict';

const projectList = document.querySelector("#projectList");
const deleteButton = document.querySelector("#deleteButton")
const projectId = document.querySelector("#projectId")

const printProject = (title) => {
    let project = document.createElement("li");
    project.id = "projectId"
    let text = document.createTextNode(`${title}`);
    project.appendChild(text);
    projectList.appendChild(project);

    let deleteLink = document.createElement("a");

    deleteLink.href = "#";
    deleteLink.className = "btn btn-sm btn-danger m-1 delete";
    deleteLink.id = "deleteButton"
    deleteLink.appendChild(
        document.createTextNode("Delete")
    );
    projectList.appendChild(deleteLink);

    for (let i = 0; i < projectList.length; i++) {
        deleteButton[i].addEventListener('click', deleteProject, false);
    }
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