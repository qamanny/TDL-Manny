'use strict';


function deleteProject(projectId) {

    let data = {

        id: projectId,
        projectName: title.value,
    }

    fetch("http://localhost:8080/project/delete/" + projectId, {
        method: "DELETE",
    })
        .then(info => {
            console.log(info);
            alert.setAttribute("class", "alert alert-success");
            alert.innerHTML = "Project has been successfully deleted";
            setTimeout(() => {
                alert.removeAttribute("class");
                alert.innerHTML = "";
            }, 2000);
        })
        .catch(err => console.error(`Code RED! ${err}`));
}