package pt.ipleiria.estg.dei.ei.dae.dae_project.ejbs;

import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.Admin;
import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.Doente;
import pt.ipleiria.estg.dei.ei.dae.dae_project.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.dae_project.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.dae_project.exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Stateless
public class AdminBean {
    @PersistenceContext
    private EntityManager em;

    public void create(int id, String name, String email, String password) throws MyEntityNotFoundException, MyEntityExistsException, MyConstraintViolationException {
        Admin a = em.find(Admin.class, id);
        if (a != null){
            throw new MyEntityExistsException("Admin nº: " + id + ", " + name + " já existe!");
        }
        try {
            a = new Admin(id, name, email, password);
            em.persist(a);
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }


    public List<Admin> getAllAdmins() {
        return (List<Admin>) em.createNamedQuery("getAllAdmins").getResultList();
    }
}
