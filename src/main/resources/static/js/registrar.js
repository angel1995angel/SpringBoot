// Call the dataTables jQuery plugin
$(document).ready(function () {
});

async function registrarUsuario() {
    let datos = {};

    datos.nombre = document.getElementById('txtNombre').value;
    datos.apellido = document.getElementById('txtApellido').value;
    datos.email = document.getElementById('txtEmail').value;
    datos.password = document.getElementById('txtPassword').value;
    datos.telefono = '4345';
    console.log(datos);
    let repetirContrasena = document.getElementById('txtPasswordR').value;

    if (repetirContrasena != datos.password) {
        alert('La contrasena son diferente');
        return;
    }
    const request = await fetch('api/usuarioggg', {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(datos)
    });
    alert('La cuenta fue registrada');
    window.location.href = 'login.html'
}
