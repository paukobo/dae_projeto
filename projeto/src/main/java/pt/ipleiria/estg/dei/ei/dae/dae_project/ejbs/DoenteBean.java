package pt.ipleiria.estg.dei.ei.dae.dae_project.ejbs;

import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.Doente;
import pt.ipleiria.estg.dei.ei.dae.dae_project.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.dae_project.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.dae_project.exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class DoenteBean {

    @PersistenceContext
    private EntityManager em;

    public Doente findDoente(String id){
        return em.find(Doente.class, id);
    }

    public String create(String name, String email, String password, String contact, String address) throws MyEntityNotFoundException, MyEntityExistsException, MyConstraintViolationException {

        Doente d = new Doente(name, email, password, contact, address);
        em.persist(d);

        return d.getId();
    }

    public void remove(String id) throws MyEntityNotFoundException{
        Doente doente = findDoente(id);
        if (doente == null) {
            throw new MyEntityNotFoundException("Doente nº: " + id + " not found");
        }
        em.remove(doente);
    }


    public boolean update(String id, String name, String email, String password, String contact, String address) throws MyEntityNotFoundException{
        Doente doente = findDoente(id);
        if(doente == null){
            throw new MyEntityNotFoundException("Doente nº: " + id + " not found");
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
