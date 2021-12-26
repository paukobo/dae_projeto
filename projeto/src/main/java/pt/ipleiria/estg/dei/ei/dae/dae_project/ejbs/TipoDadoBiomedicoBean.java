package pt.ipleiria.estg.dei.ei.dae.dae_project.ejbs;

import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.TipoDadoBiomedico;
import pt.ipleiria.estg.dei.ei.dae.dae_project.exceptions.MyEntityNotFoundException;

import javax.ejb.DuplicateKeyException;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Stateless
public class TipoDadoBiomedicoBean {

    @PersistenceContext
    private EntityManager em;

    public void create(String nome, String descricao, double valorMin, double valorMax, String unidades, List<String> valorQualitativo){
        TipoDadoBiomedico d = new TipoDadoBiomedico(nome, descricao, valorMin, valorMax, unidades);

        if(valorQualitativo != null){
            for (String vq: valorQualitativo) {
                d.addValorQualitativo(vq);
            }
        }

        em.persist(d);
    }

    public void update(int id, String newNome, String descricao, double valorMin, double valorMax, String unidades, List<String> valorQualitativo) throws MyEntityNotFoundException, DuplicateKeyException {
        TipoDadoBiomedico dadoBiomedico = findTipoDadoBiomedico(id);

        if(dadoBiomedico == null) {
            throw new MyEntityNotFoundException();
        }

        if(newNome != dadoBiomedico.getNome() && findTipoDadoBiomedico(newNome) != null){
            throw new DuplicateKeyException();
        }
        em.lock(dadoBiomedico, LockModeType.PESSIMISTIC_WRITE);

        dadoBiomedico.setNome(newNome);
        dadoBiomedico.setDescricao(descricao);
        dadoBiomedico.setValorMin(valorMin);
        dadoBiomedico.setValorMax(valorMax);
        dadoBiomedico.setUnidades(unidades);

        dadoBiomedico.setValoresQualitativos(new HashMap<>());

        if(valorQualitativo != null){
            for (String vq: valorQualitativo) {
                dadoBiomedico.addValorQualitativo(vq);
            }
        }

        em.merge(dadoBiomedico);
    }

    public TipoDadoBiomedico delete(int id) throws MyEntityNotFoundException {
        TipoDadoBiomedico dadoBiomedico = findTipoDadoBiomedico(id);

        if(dadoBiomedico == null) {
            throw new MyEntityNotFoundException();
        }
        em.remove(dadoBiomedico);
        return dadoBiomedico;
    }

    public TipoDadoBiomedico findTipoDadoBiomedico(String name){
        try {
             TipoDadoBiomedico dado = (TipoDadoBiomedico) em.createNamedQuery("getTipoDadoBiomedicoByNome").setParameter("nome", name).getSingleResult();
            System.out.println(dado);
            return dado;
        }catch(Exception e){
            return null;
        }
    }

    public TipoDadoBiomedico findTipoDadoBiomedico(int id){
        return (TipoDadoBiomedico) em.find(TipoDadoBiomedico.class, id);
    }

    public List<TipoDadoBiomedico> getAllDadosBiomedicos(){
        return (List<TipoDadoBiomedico>) em.createNamedQuery("getAllTipoDadoBiomedicos").getResultList();
    }
}
