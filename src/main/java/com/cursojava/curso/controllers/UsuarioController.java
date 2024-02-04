package com.cursojava.curso.controllers;

import com.cursojava.curso.dao.UsuarioDao;
import com.cursojava.curso.dao.UsuarioDaoImp;
import com.cursojava.curso.models.Usuario;
import com.cursojava.curso.utils.JWTUtil;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class UsuarioController {

    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private JWTUtil jwtUtil;

    @RequestMapping(value = "api/usuariog/{id}")
    public Usuario getUsuario(@PathVariable long id){
        Usuario usuario = new Usuario();
        usuario.setId(id);
        usuario.setNombre("Angel");
        usuario.setApellido("Herrera");
        usuario.setEmail("prueba@gmail.com");
        usuario.setTelefono("04141055027");
        usuario.setPassword("22045794");
        return usuario;
    }
    @RequestMapping(value = "api/usuariogg")
    public List<Usuario> getUsuarios(@RequestHeader(value="Authorization") String token){
        if (!validarToken(token)) { return null; }
        return usuarioDao.getUsuarios();
    }

    private boolean validarToken(String token) {
        String usuarioId = jwtUtil.getKey(token);
        return usuarioId != null;
    }

    @RequestMapping(value = "api/usuarioggg" , method = RequestMethod.POST)
    public void registrarUsuarios(@RequestBody Usuario usuario){
        Argon2 argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String has =  argon.hash(1,1024,1,usuario.getPassword());
        usuario.setPassword(has);
        usuarioDao.registrar(usuario);
    }

    @RequestMapping(value = "api/usuarioe")
    public   Usuario editar(){
        Usuario usuario = new Usuario();
        usuario.setNombre("Angel");
        usuario.setApellido("Herrera");
        usuario.setEmail("prueba@gmail.com");
        usuario.setTelefono("04141055027");
        usuario.setPassword("22045794");
        return usuario;
    }
    @RequestMapping(value = "api/usuarioe/{id}", method = RequestMethod.DELETE)
    public void eliminar(@RequestHeader(value="Authorization") String token, @PathVariable Long id){
        if (!validarToken(token)) { return; }
        usuarioDao.eliminar(id);
    }
    @RequestMapping(value = "api/usuariob/{id}", method = RequestMethod.GET)
    void buscar(@PathVariable Long id){

    }
}
