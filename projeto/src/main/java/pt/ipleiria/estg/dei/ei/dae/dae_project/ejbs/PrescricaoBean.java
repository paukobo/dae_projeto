package pt.ipleiria.estg.dei.ei.dae.dae_project.ejbs;

import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.Doente;
import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.Prescricao;
import pt.ipleiria.estg.dei.ei.dae.dae_project.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.dae_project.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.dae_project.exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class PrescricaoBean {

    @PersistenceContext
    private EntityManager em;

    public int create(String descricao, String dataInicio, String dataFim, int duracao) throws MyEntityNotFoundException, MyEntityExistsException, MyConstraintViolationException {

        try {

            Prescricao p = new Prescricao(descricao, dataInicio, dataFim, duracao);
            em.persist(p);
            return p.getId();

        } catch (ConstraintViolationException e) {
            throw new MyConstraintViolationException(e);
        }
    }

    public boolean associatePrescricaoWithDoente(int prescricaoId, String email) throws MyEntityNotFoundException{
        Doente doente = em.find(Doente.class, email);
        if(doente == null) {
            throw new MyEntityNotFoundException("Doente with email: " + email + " not found");
        }

        Prescricao prescricao = em.find(Prescricao.class, prescricaoId);
        if(prescricao != null){
            if(!prescricao.getDoentes().contains(doente)){
                prescricao.addDoente(doente);
                doente.addPrescricao(prescricao);
            }
        }
        return true;
    }

    public boolean disassociatePrescricaoWithDoente(int prescricaoId, String email) throws MyEntityNotFoundException{
        Doente doente = em.find(Doente.class, email);
        if(doente == null) {
            throw new MyEntityNotFoundException("Doente with email: " + email + " not found");
        }

        Prescricao prescricao = em.find(Prescricao.class, prescricaoId);
        if(prescricao == null){
            throw new MyEntityNotFoundException("Prescricao with id: " + prescricaoId + " not found");
        }
        prescricao.removeDoente(doente);
        doente.removePrescricao(prescricao);
        return true;
    }

    public void remove(int prescricaoId) throws MyEntityNotFoundException{
        Prescricao prescricaoFound = em.find(Prescricao.class, prescricaoId);
        if (prescricaoFound == null){
            throw new MyEntityNotFoundException("Prescricao with id: " + prescricaoId + " not found");
        }
        for (Doente doente : prescricaoFound.getDoentes()) {
            doente.removePrescricao(prescricaoFound);
        }
        em.remove(prescricaoFound);
    }


    public boolean update(int prescricaoId, String descricao, String dataInicio, String dataFim, int duracao) throws MyEntityNotFoundException{

        Prescricao prescricaoFound = em.find(Prescricao.class, prescricaoId);
        if (prescricaoFound == null){
            throw new MyEntityNotFoundException("Prescricao with id: " + prescricaoId + " not found");
        }

        prescricaoFound.setDescricao(descricao);
        prescricaoFound.setDataInicio(dataInicio);
        prescricaoFound.setDataFim(dataFim);
        prescricaoFound.setDuracao(duracao);

        return true;
    }

    public Prescricao findPrescricao(int id) {
        return em.find(Prescricao.class, id);
    }

    public List<Prescricao> getAllPrescricoes(){
        return (List<Prescricao>) em.createNamedQuery("getAllPrescricoes").getResultList();
    }
}
