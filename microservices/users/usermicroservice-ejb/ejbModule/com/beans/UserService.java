package com.beans;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;

import model.Usuario;

/**
 * Session Bean implementation class ServiciosUsuario
 */
@Stateless
@LocalBean
public class UserService implements UserServiceRemote, UserServiceLocal {
	@PersistenceContext(unitName="entities",type=PersistenceContextType.TRANSACTION)
	private EntityManager entityManager;

    /**
     * Default constructor. 
     */
    public UserService() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public List<Usuario> findUsuario(String userName, String password) {
    String consulta = "SELECT u FROM usuario u WHERE u.username=:userName AND u.password=:password";
    TypedQuery<Usuario> query = entityManager.createQuery(consulta, Usuario.class);
    query.setParameter("userName", userName);
    query.setParameter("password", password);
    query.setMaxResults(1);
    List<Usuario> resultList = query.getResultList();
    return resultList;
    }
    
    @Override
    public String addUsuario(String userName, String password) {
    Usuario newUsuario = new Usuario();
    newUsuario.setIdusuario(4);
    //newUsuario.setNombres("hola");
    //newUsuario.setApellidos("loco");
    newUsuario.setUsername(userName);
    newUsuario.setPassword(password);
    Usuario user = entityManager.find(Usuario.class, newUsuario.getIdusuario());
    if (user == null) {
    entityManager.persist(newUsuario);
    return "insertado";
    } else
    return "existe";
    }
    
    @Override
    public List<Usuario> getAllUsuarios() {
    // TODO Auto-generated method stub
    return entityManager.createQuery("SELECT p FROM Usuario p", Usuario.class).getResultList();
    }

}
