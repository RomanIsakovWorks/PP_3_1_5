//Users list
const table = $('#users');

function showAllUsers() {
    table.empty()
    fetch("http://localhost:8080/admin/users")
        .then(response => response.json())
        .then(data => {
            data.forEach(user => {
                let users = `$(
                        <tr>
                            <td>${user.id}</td>
                            <td>${user.name}</td>
                            <td>${user.lastName}</td>  
                            <td>${user.email}</td>
                            <td>${user.roles.map(role => " " + role.role.substring(5))}</td>
                            <td>
                                <button type="button" class="btn btn-info"
                                data-bs-toogle="modal"
                                data-bs-target="#editModal"
                                onclick="editModalDataUser(${user.id})">Edit</button>
                            </td>
                            <td>
                                <button type="button" class="btn btn-danger" 
                                data-toggle="modal"
                                data-bs-target="#deleteModal"
                                onclick="deleteModalUser(${user.id})">Delete</button>
                            </td>
                        </tr>)`;
                table.append(users);
            })
        })
}
showAllUsers();

//New User
let newUser = document.forms["newUserJS"];
addUser();

function addUser() {
    newUser.addEventListener("submit", ev => {
        ev.preventDefault();
        let newUserRoles = [];
        for (let i = 0; i < newUser.roles.options.length; i++) {
            if (newUser.roles.options[i].selected) newUserRoles.push({
                id: newUser.roles.value,
                role: "ROLE_" + newUser.roles.options[i].text
            });
        }
        fetch("http://localhost:8080/admin/users", {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: newUser.id.value,
                name: newUser.name.value,
                lastName: newUser.lastName.value,
                email: newUser.email.value,
                password: newUser.password.value,
                roles: newUserRoles
            })
        }).then(() => {
            newUser.reset();
            showAllUsers();
            $('#list-tab').click();
        });
    });
}

//Get User
async function getUser(id) {
    let url = "http://localhost:8080/admin/users/" + id;
    let response = await fetch(url);
    return await response.json();
}

//Edit User
let editUser = document.forms["editUser"];
updateUser();

async function editModalDataUser(id) {
    const modal = new bootstrap.Modal(document.querySelector('#editModal'));
    await openAndFillInModal(editUser, modal, id);
}

function updateUser() {
    editUser.addEventListener("submit", ev => {
        ev.preventDefault();
        let editUserRoles = [];
        for (let i = 0; i < editUser.roles.options.length; i++) {
            if (editUser.roles.options[i].selected) editUserRoles.push({
                id: editUser.roles.value,
                name: "ROLE_" + editUser.roles.options[i].text
            });
        }
        fetch('http://localhost:8080/admin/users/' + editUser.id.value, {
            method: 'PATCH',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                id: editUser.id.value,
                name: editUser.name.value,
                lastName: editUser.lastName.value,
                email: editUser.email.value,
                password: editUser.password.value,
                roles: editUserRoles
            })
        }).then(() => {
            $('#editFormCloseButton').click();
            showAllUsers();
        });
    });
}

//Delete User
let formDelete = document.forms["formDeleteUser"];
deleteUser();

async function deleteModalUser(id) {
    const modal = new bootstrap.Modal(document.querySelector('#deleteModal'));
    await openAndFillInModal(formDelete, modal, id);
    switch (formDelete.roles.value) {
        case '1':
            formDelete.roles.value = 'USER';
            break;
        case '2':
            formDelete.roles.value = 'ADMIN';
            break;
    }
}

function deleteUser() {
    formDelete .addEventListener("submit", ev => {
        ev.preventDefault();
        fetch("http://localhost:8080/admin/users/" + formDelete.id.value, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json'
            }
        }).then(() => {
            $('#deleteFormCloseButton').click();
            showAllUsers();
        });
    });
}

//Open and fill Modal window
async function openAndFillInModal(form, modal, id){
    modal.show();
    let user = await getUser(id);
    form.id.value = user.id;
    form.name.value = user.name;
    form.lastName.value = user.lastName;
    form.email.value = user.email;
    form.roles.value = user.roles[0].id;
}