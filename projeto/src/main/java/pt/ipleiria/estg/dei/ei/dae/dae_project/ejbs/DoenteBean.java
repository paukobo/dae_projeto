package pt.ipleiria.estg.dei.ei.dae.dae_project.ejbs;

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
public class DoenteBean {

    @PersistenceContext
    private EntityManager em;

    public void create(int id, String name, String email, String password) throws MyEntityNotFoundException, MyEntityExistsException, MyConstraintViolationException {
        Doente d = em.find(Doente.class, id);
        if (d != null){
            throw new MyEntityExistsException("Doente nº:" + id + ", " + name + " já existe!");
        }
        try {
            d = new Doente(id, name, email, password);
            em.persist(d);
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }


    public List<Doente> getAllDoentes() {
        return (List<Doente>) em.createNamedQuery("getAllDoentes").getResultList();
    }
}
