package pt.ipleiria.estg.dei.ei.dae.dae_project.ejbs;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Startup
@Singleton
public class ConfigBean {

    @EJB
    private DoenteBean doenteBean;

    @EJB
    private AdminBean adminBean;

    @EJB
    private ProfissionalSaudeBean profissionalSaudeBean;

    @EJB
    private TipoDadoBiomedicoBean dadoBiomedicoBean;

    @EJB
    private PrescricaoBean prescricaoBean;

    private static final Logger logger = Logger.getLogger("ejbs.ConfigBean");


    @PostConstruct
    public void populateDB() {
        try {
            profissionalSaudeBean.create( "Ana", "ana@mail.pt", "123456");
            profissionalSaudeBean.create("Ana2", "ana2@mail.pt", "123456");
            doenteBean.create("João", "joao@mail.pt", "1234", "912345678", "Rua da Cova nº140", "ana@mail.pt");
            doenteBean.create("João2", "joao2@mail.pt", "12342", "9123456782", "Rua da Cova2 nº1402", "ana@mail.pt");
            doenteBean.create("João3", "joao3@mail.pt", "1234", "9123456783", "Rua da Cova3 nº1403", "ana2@mail.pt");
            adminBean.create("Pedro", "pedro@mail.pt", "12345");
            dadoBiomedicoBean.create("Temperatura", "Temperatura do corpo do paciente", 50, 70, "ºC", new LinkedList<String>(List.of("Saudavel", "Febre")));

            prescricaoBean.create("this is a test description", "11-11-2021", "12-12-2021", 20);

            prescricaoBean.associatePrescricaoWithDoente(2, "joao2@mail.pt");
            prescricaoBean.disassociatePrescricaoWithDoente(2, "joao2@mail.pt");
            prescricaoBean.associatePrescricaoWithDoente(2, "joao3@mail.pt");


        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}