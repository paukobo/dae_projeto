package pt.ipleiria.estg.dei.ei.dae.dae_project.ejbs;

import pt.ipleiria.estg.dei.ei.dae.dae_project.dtos.DadoBiomedicoDTO;
import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.DadoBiomedico;
import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.Doente;
import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.TipoDadoBiomedico;
import pt.ipleiria.estg.dei.ei.dae.dae_project.exceptions.MyEntityNotFoundException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Stateless
public class DadoBiomedicoBean {

    @PersistenceContext
    private EntityManager em;

    public void create(int tipoId, double valorQuantitativo, String valorQualitativo, Date date, String doenteId) throws MyEntityNotFoundException {
        Doente doente = em.find(Doente.class, doenteId);
        if(doente == null){
            throw new MyEntityNotFoundException("Doente Not Found");
        }

        TipoDadoBiomedico tipo = em.find(TipoDadoBiomedico.class, tipoId);
        if(tipo == null){
            throw new MyEntityNotFoundException("Tipo Dado Not Found");
        }

        DadoBiomedico d = new DadoBiomedico(tipo.getNome(), doente, tipo, tipo.getUnidades(), valorQuantitativo,valorQualitativo, date);
        em.persist(d);
    }

    public List<DadoBiomedico> getAllDadosBiomedicos(String doenteId) {
        Doente doente = em.find(Doente.class, doenteId);
        return (List<DadoBiomedico>) em.createNamedQuery("getAllDadoBiomedicoDoente").setParameter("doente", doente).getResultList();
    }

}
