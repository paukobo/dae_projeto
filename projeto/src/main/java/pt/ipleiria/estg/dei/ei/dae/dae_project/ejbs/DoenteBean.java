package pt.ipleiria.estg.dei.ei.dae.dae_project.ejbs;

import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.Doente;
import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.ProfissionalSaude;
import pt.ipleiria.estg.dei.ei.dae.dae_project.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.dae_project.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.dae_project.exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.util.LinkedList;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Stateless
public class DoenteBean {

    @PersistenceContext
    private EntityManager em;

    public Doente findDoente(String email){
        return em.find(Doente.class, email);
    }

    public String create(String name, String email, String password, String contact, String address, String medico_email) throws MyEntityNotFoundException, MyEntityExistsException, MyConstraintViolationException {

        Doente d = em.find(Doente.class, email);
        if (d != null) {
            throw new MyEntityExistsException("Doente with email: " + email + " already exists");
        }

        ProfissionalSaude p = em.find(ProfissionalSaude.class, medico_email);
        if (p == null) {
            throw new MyEntityNotFoundException("Médico with email: " + medico_email + " not found");
        }

        try {
            d = new Doente(name, email, password, contact, address, p);
            p.addDoente(d);
            em.persist(d);
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }


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

    public boolean associateDoenteToProfissional(String email, String email_medico) throws MyEntityNotFoundException{
        Doente doente = findDoente(email);
        if(doente == null) {
            throw new MyEntityNotFoundException("Doente with email: " + email + "not found");
        }
        ProfissionalSaude profissionalSaude = em.find(ProfissionalSaude.class, email_medico);
        if(profissionalSaude != null){
            if(!profissionalSaude.getDoentes().contains(doente)){
                profissionalSaude.addDoente(doente);
            }
        }
        return true;
    }

    public boolean disassociateDoenteToProfissional(String email, String email_medico) throws MyEntityNotFoundException{
        Doente doente = findDoente(email);
        if(doente == null) {
            throw new MyEntityNotFoundException("Doente with email: " + email + "not found");
        }
        ProfissionalSaude profissionalSaude = em.find(ProfissionalSaude.class, email_medico);
        if(profissionalSaude != null){
            profissionalSaude.removeDoente(doente);
        }
        return true;
    }

    public List<Doente> getAllDoentes() {
        return (List<Doente>) em.createNamedQuery("getAllDoentes").getResultList();
    }
}
