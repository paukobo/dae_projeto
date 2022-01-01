package pt.ipleiria.estg.dei.ei.dae.dae_project.ejbs;

import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.Doente;
import pt.ipleiria.estg.dei.ei.dae.dae_project.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.dae_project.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.dae_project.exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class DoenteBean {

    @PersistenceContext
    private EntityManager em;

    public Doente findDoente(String email){
        return em.find(Doente.class, email);
    }

    public String create(String name, String email, String password, String contact, String address) throws MyEntityNotFoundException, MyEntityExistsException, MyConstraintViolationException {

        Doente d = new Doente(name, email, password, contact, address);
        em.persist(d);

        return d.getEmail();
    }

    public void remove(String email) throws MyEntityNotFoundException{
        Doente doente = findDoente(email);
        if (doente == null) {
            throw new MyEntityNotFoundException("Doente: " + email + " not found");
        }
        em.remove(doente);
    }


    public boolean update(String name, String email, String password, String contact, String address) throws MyEntityNotFoundException{
        Doente doente = findDoente(email);
        if(doente == null){
            throw new MyEntityNotFoundException("Doente: " + email + " not found");
        }

        em.lock(doente, LockModeType.OPTIMISTIC);

        doente.setName(name);
        doente.setEmail(email);
        doente.setPassword(password);
        doente.setContact(contact);
        doente.setAddress(address);

        return true;
    }



    public List<Doente> getAllDoentes() {
        return (List<Doente>) em.createNamedQuery("getAllDoentes").getResultList();
    }
}
