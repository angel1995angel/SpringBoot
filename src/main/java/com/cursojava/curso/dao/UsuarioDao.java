package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public interface UsuarioDao {



    List<Usuario> getUsuarios();

    void registrar(Usuario usuario);

    void eliminar(Long idd);

    Usuario obtenerUsuarioPorCredenciales(Usuario usuario);
}
