// Call the dataTables jQuery plugin
$(document).ready(function() {
 cargarUsuario();
  $('#usuarios').DataTable();
});

async function cargarUsuario() {

  const request= await fetch('api/usuariogg', {
    method: 'GET',
    headers: getHeaders()
  });
  const usuarios = await request.json();
let listadoHtml='';
  for (let usu of usuarios) {
    let botonEliminar = '<a href=\"#\" onclick="eliminarUsuario('+usu.id+')" class=\"btn btn-danger btn-circle btn-sm\"><i class=\"fas fa-trash\"></i></a>';

    let usuarioHtml = '<tr><td>'+usu.id+'</td><td>'+usu.nombre+' '+usu.apellido+'</td><td>'+usu.email+'</td><td>'+usu.telefono+'</td><td>'+botonEliminar+'</td></tr>'
    listadoHtml+=usuarioHtml
    console.log(usuarios);
  }

  document.querySelector('#usuarios tbody').outerHTML= listadoHtml
}

function getHeaders() {
  return {
    'Accept': 'application/json',
    'Content-Type': 'application/json',
    'Authorization': localStorage.token
  };
}

async function eliminarUsuario(id){
if (!confirm("Deseas eliminar este usuario ?")){
  return;
}
  const request= await fetch('api/usuarioe/'+id, {
    method: 'DELETE',
    headers: getHeaders()
  });

location.reload();
}