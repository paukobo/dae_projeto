package pt.ipleiria.estg.dei.ei.dae.dae_project.ejbs;

import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.Admin;
import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.Doente;
import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.ProfissionalSaude;
import pt.ipleiria.estg.dei.ei.dae.dae_project.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.dae_project.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.dae_project.exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Stateless
public class AdminBean {
    @PersistenceContext
    private EntityManager em;

    public Admin findAdmin(long id) {
        return em.find(Admin.class, id);
    }

    public long create(String name, String email, String password) throws MyEntityNotFoundException, MyEntityExistsException, MyConstraintViolationException {

        Admin a = new Admin(name, email, password);
        em.persist(a);
        return a.getId();
    }

    public void remove(long id) throws MyEntityNotFoundException{
        Admin admin = findAdmin(id);
        if (admin == null) {
            throw new MyEntityNotFoundException("Administrador nº: " + id + " not found");
        }
        em.remove(admin);
    }


    public boolean update(long id, String name, String email,String password) throws MyEntityNotFoundException{
        Admin admin = findAdmin(id);
        if(admin == null){
            throw new MyEntityNotFoundException("Administrador nº: " + id + " not found");
        }

        em.lock(admin, LockModeType.OPTIMISTIC);

        admin.setName(name);
        admin.setEmail(email);
        admin.setPassword(password);

        return true;
    }


    public List<Admin> getAllAdmins() {
        return (List<Admin>) em.createNamedQuery("getAllAdmins").getResultList();
    }
}
