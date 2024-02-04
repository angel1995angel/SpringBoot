package com.cursojava.curso.dao;

import com.cursojava.curso.models.Usuario;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class UsuarioDaoImp implements UsuarioDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<Usuario> getUsuarios() {

        String jpql = "FROM Usuario";
        TypedQuery<Usuario> query = entityManager.createQuery(jpql, Usuario.class);
        return query.getResultList();
    }

    @Override
    public void registrar(Usuario usuario) {
        entityManager.merge(usuario);
    }

    public void eliminar(Long id) {
        Usuario usuario = entityManager.find(Usuario.class, id);
        entityManager.remove(usuario);
    }
    @Override
    public Usuario obtenerUsuarioPorCredenciales(Usuario usuario) {
        String jpql = "FROM Usuario WHERE email = :email";
        TypedQuery<Usuario> query = entityManager.createQuery(jpql, Usuario.class)
                .setParameter("email", usuario.getEmail());
        List<Usuario> result = query.getResultList();

        String passwordHas = result.get(0).getPassword();

        Argon2 argon = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        if (argon.verify(passwordHas, usuario.getPassword())) {
            return result.get(0);
        }
        return null;
    }
}
