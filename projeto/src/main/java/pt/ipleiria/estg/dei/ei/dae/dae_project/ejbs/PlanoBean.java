package pt.ipleiria.estg.dei.ei.dae.dae_project.ejbs;

import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.Doente;
import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.Plano;
import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.Prescricao;
import pt.ipleiria.estg.dei.ei.dae.dae_project.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.dae_project.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.dae_project.exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.util.List;

@Stateless
public class PlanoBean {

    @PersistenceContext
    private EntityManager em;

    public int create(String descricao, int duracao) throws MyEntityNotFoundException, MyEntityExistsException, MyConstraintViolationException {

        try {
            Plano p = new Plano(descricao, duracao);
            em.persist(p);
            return p.getIdPlano();

        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public boolean associatePlanoWithPrescricao(int planoId, int prescricaoId) throws MyEntityNotFoundException{
        Plano plano = em.find(Plano.class, planoId);
        if(plano == null) {
            throw new MyEntityNotFoundException("Plano with id: " + planoId + " not found");
        }

        Prescricao prescricao = em.find(Prescricao.class, prescricaoId);
        if(prescricao != null){
            if(!prescricao.getPlanos().contains(plano)){
                prescricao.addPlano(plano);
                plano.addPrescricao(prescricao);
            }
        }
        return true;
    }

    public boolean disassociatePlanoWithPrescricao(int planoId, int prescricaoId) throws MyEntityNotFoundException{
        Plano plano = em.find(Plano.class, planoId);
        if(plano == null) {
            throw new MyEntityNotFoundException("Plano with id: " + planoId + " not found");
        }

        Prescricao prescricao = em.find(Prescricao.class, prescricaoId);
        if(prescricao == null){
            throw new MyEntityNotFoundException("Prescricao with id: " + prescricaoId + " not found");
        }

        prescricao.removePlano(plano);
        plano.removePrescricao(prescricao);
        return true;
    }

    public void remove(int planoId) throws MyEntityNotFoundException{
        Plano planoFound = em.find(Plano.class, planoId);
        if (planoFound == null){
            throw new MyEntityNotFoundException("Plano with id: " + planoId + " not found");
        }

        for (Prescricao prescricao : planoFound.getPrescricoes()) {
            prescricao.removePlano(planoFound);
        }
        em.remove(planoFound);
    }


    public boolean update(int planoId, String descricao, int duracao) throws MyEntityNotFoundException{

        Plano planoFound = em.find(Plano.class, planoId);
        if (planoFound == null){
            throw new MyEntityNotFoundException("Plano with id: " + planoId + " not found");
        }

        planoFound.setDescricao(descricao);
        planoFound.setDuracao(duracao);

        return true;
    }

    public Plano findPlano(int id) {
        return em.find(Plano.class, id);
    }

    public List<Plano> getAllPlanos(){
        return (List<Plano>) em.createNamedQuery("getAllPlanos").getResultList();
    }

}
