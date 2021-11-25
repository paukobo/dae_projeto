package pt.ipleiria.estg.dei.ei.dae.dae_project.ejbs;

import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.Admin;
import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.ProfissionalSaude;
import pt.ipleiria.estg.dei.ei.dae.dae_project.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.dae_project.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.dae_project.exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Stateless
public class ProfissionalSaudeBean {
    @PersistenceContext
    private EntityManager em;

    public void create(int id, String name, String email, String password) throws MyEntityNotFoundException, MyEntityExistsException, MyConstraintViolationException {
        ProfissionalSaude p = em.find(ProfissionalSaude.class, id);
        if (p != null){
            throw new MyEntityExistsException("Profissional de Saúde nº: " + id + ", " + name + " já existe!");
        }
        try {
            p = new ProfissionalSaude(id, name, email, password);
            em.persist(p);
        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }


    public List<ProfissionalSaude> getAllProfissionaisSaude() {
        return (List<ProfissionalSaude>) em.createNamedQuery("getAllProfissionaisSaude").getResultList();
    }
}
