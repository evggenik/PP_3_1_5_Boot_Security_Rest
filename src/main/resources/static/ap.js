
async function getUsers() {
    const response = await fetch("/api/users");
    const users = await response.json();
    users.forEach(user => setUsers(user));

}

async function getRoles() {
    const response = await fetch("/api/roles");
    const roles = await response.json();
    return roles;

}

function setUsers(user) {
    const tableBody = document.querySelector("tbody");
    tableBody.insertAdjacentHTML('beforeend',
        `<tr id="user${user.id}">
        <td>${user.id}</td>
        <td>${user.userName}</td>
        <td>${user.firstName}</td>
        <td>${user.lastName}</td>
        <td>${user.email}</td>
        <td>${setRoles(user.roles)}</td>
        
        <td><button type="button" class="btn btn-info" data-bs-toggle="modal" 
                data-bs-target="#editModal" data-bs-whatever=${JSON.stringify(user)}>Edit</button></td>
             
        <td><button id="btn${user.id}"  type="button" class="btn btn-danger" data-bs-toggle="modal" 
                data-bs-target="#deleteModal" data-bs-whatever=${JSON.stringify(user)}>Delete</button></td>
        
        </tr>`);
}

function setRoles(roles) {
    return roles.map(role => `${role.name}`);}

// 
// Добавление пользователя
// 


async function addUser(user) {
    const response = await fetch("/api/users", {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    });
    const data = await response.json();
    console.log(data);
     
}

function showNewUserRoles() {
    let select = document.getElementById('rolesAdd');
     select.length = 0;

        getRoles().then(roles => 
            {roles.forEach(role => {
             option = document.createElement('option');
             option.setAttribute('value', role.id);

            option.appendChild(document.createTextNode(role.name));
            select.appendChild(option);})
        });
}

function addFormUserEvent(event) {

    

var selected_options = document.getElementById('rolesAdd').selectedOptions;
var rolesArr = [];
for(var i=0; i<selected_options.length; i++) {
    let role ={};
    role.id=selected_options[i].value;
    role.name=selected_options[i].text;
    rolesArr.push(role);
}

// console.log(rolesArr);

    const {userName, firstName, lastName, email, password, roles} = document.forms.addUserForm;
            const user = {
                userName: userName.value,
                firstName: firstName.value,
                lastName: lastName.value,
                email: email.value,
                password: password.value,
                roles: rolesArr
            }
    // console.log(user);
    addUser(user);      
}
addUserForm.addEventListener("submit", addFormUserEvent);

// 
// Редактирование пользователя
// 

async function updateUser(user) {
    const response = await fetch("/api/users", {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(user)
    });
    const data = await response.json();
    console.log(data);
     
}


var edModal = document.getElementById('editModal');
edModal.addEventListener('shown.bs.modal', event => {
    event.preventDefault();
    const button = event.relatedTarget
    const recipient = button.getAttribute('data-bs-whatever')
    jsUser = JSON.parse(recipient);
        // console.log(jsUser.roles);
        document.getElementById('editUserId').setAttribute('value', jsUser.id);
        document.getElementById('editUsername').setAttribute('value', jsUser.userName);
        document.getElementById('editFname').setAttribute('value', jsUser.firstName);
        document.getElementById('editLname').setAttribute('value', jsUser.lastName);
        document.getElementById('editEmail').setAttribute('value', jsUser.email);
        document.getElementById('editPassword').setAttribute('value', jsUser.password);
        let select = document.getElementById('selectRoles');
        
        
        select.length = 0;

        getRoles().then(roles => 
            {roles.forEach(role => {
             option = document.createElement('option');
             option.setAttribute('value', role.id);

            option.appendChild(document.createTextNode(role.name));
            select.appendChild(option);})
        });

        
        // editUser.addEventListener("submit", showUser(user));

        // saveBtn = document.getElementById("saveChanges");
        // saveBtn.addEventListener("submit", function(event) {
        //     if (event.target.nodeName == "BUTTON") showUser(user); });
});


function retrieveFormUserEvent(event) {

var selected_options = document.getElementById('selectRoles').selectedOptions;
var rolesArr = [];
for(var i=0; i<selected_options.length; i++) {
    let role ={};
    role.id=selected_options[i].value;
    role.name=selected_options[i].text;
    rolesArr.push(role);
}

// console.log(rolesArr);

    const {id, userName, firstName, lastName, email, password, roles} = document.forms.editUser;
            const user = {
                id: id.value,
                userName: userName.value,
                firstName: firstName.value,
                lastName: lastName.value,
                email: email.value,
                password: password.value,
                roles: rolesArr
            }
    // console.log(user);
    updateUser(user);      
}
editUser.addEventListener("submit", retrieveFormUserEvent);

// 
// Удаление пользователя
// 

async function deleteUser(id) {
    const response = await fetch(`/api/users/${id}`, {
        method: 'DELETE',
        headers: {
            'Content-Type': 'application/json',
        }
    })
    const data = await response.json();
    // console.log(data);
    if (data == `User with ID=${user.id} was deleted`) {
        document.getElementById(`user${user.id}`).remove();
    }
     
}

var delModal = document.getElementById('deleteModal');
delModal.addEventListener('shown.bs.modal', event => {
    const button = event.relatedTarget
    const recipient = button.getAttribute('data-bs-whatever')
    jsUser = JSON.parse(recipient);
        // console.log(jsUser.roles);
        document.getElementById('deleteUserId').setAttribute('value', jsUser.id);
        document.getElementById('deleteUsername').setAttribute('value', jsUser.userName);
        document.getElementById('deleteFname').setAttribute('value', jsUser.firstName);
        document.getElementById('deleteLname').setAttribute('value', jsUser.lastName);
        document.getElementById('deleteEmail').setAttribute('value', jsUser.email);
        document.getElementById('deletePassword').setAttribute('value', jsUser.password);
        let select = document.getElementById('deleteRoles');
        select.length = 0;
        jsUser.roles.forEach(role => {
            option = document.createElement('option');
           // option.setAttribute('name', role.name);
            option.appendChild(document.createTextNode(role.name));
            select.appendChild(option);
        });

        delBtn = document.getElementById("btnDelete");
        delBtn.addEventListener("click", function(event) {
            if (event.target.nodeName == "BUTTON") deleteUser(jsUser.id); });
})


getUsers();


