package pt.ipleiria.estg.dei.ei.dae.dae_project.ejbs;

import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.TipoDadoBiomedico;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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

    public TipoDadoBiomedico findTipoDadoBiomedico(String name){
        return (TipoDadoBiomedico) em.createNamedQuery("getTipoDadoBiomedicoByNome").setParameter("nome", name).getSingleResult();
    }

}
