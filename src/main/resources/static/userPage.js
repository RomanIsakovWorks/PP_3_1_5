function getUserInfo() {
    fetch("http://localhost:8080/user/info")
        .then(response => response.json())
        .then(user => {
            $('#userEmail').append(user.email);
            let roles = user.roles.map(role => " " + role.role.substring(5));
            $('#userRoles').append(roles);
            let userInfo = `$(
                <tr>
                    <td>${user.id}</td>
                    <td>${user.name}</td>
                    <td>${user.lastName}</td> 
                    <td>${user.email}</td>
                    <td>${roles}</td>
                </tr>)`;
            $('#userInfo').append(userInfo);
        })
}

getUserInfo();