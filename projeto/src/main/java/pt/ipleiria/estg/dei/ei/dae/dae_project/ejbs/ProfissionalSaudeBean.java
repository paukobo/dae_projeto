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
public class ProfissionalSaudeBean {
    @PersistenceContext
    private EntityManager em;

    public ProfissionalSaude findProfissional(String id) {
        return em.find(ProfissionalSaude.class, id);
    }

    public String create(String name, String email, String password) throws MyEntityNotFoundException, MyEntityExistsException, MyConstraintViolationException {

       ProfissionalSaude p = new ProfissionalSaude(name, email, password);
        em.persist(p);
        return p.getId();
    }

    public void remove(String id) throws MyEntityNotFoundException{
        ProfissionalSaude profissionalSaude = findProfissional(id);
        if (profissionalSaude == null) {
            throw new MyEntityNotFoundException("Profissional Saúde nº: " + id + " not found");
        }
        em.remove(profissionalSaude);
    }


    public boolean update(String id, String name, String email, String password) throws MyEntityNotFoundException{
        ProfissionalSaude profissionalSaude = findProfissional(id);
        if(profissionalSaude == null){
            throw new MyEntityNotFoundException("Profissional de Saúde nº: " + id + " not found");
        }

        em.lock(profissionalSaude, LockModeType.OPTIMISTIC);

        profissionalSaude.setName(name);
        profissionalSaude.setEmail(email);
        profissionalSaude.setPassword(password);

        return true;
    }


    public List<ProfissionalSaude> getAllProfissionaisSaude() {
        return (List<ProfissionalSaude>) em.createNamedQuery("getAllProfissionaisSaude").getResultList();
    }
}
