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

    private static final Logger logger = Logger.getLogger("ejbs.ConfigBean");


    @PostConstruct
    public void populateDB() {
        try {
            doenteBean.create("João", "joao@mail.pt", "1234", "912345678", "Rua da Cova nº140");
            adminBean.create("Pedro", "pedro@mail.pt", "12345");
            profissionalSaudeBean.create( "Ana", "ana@mail.pt", "123456");
            profissionalSaudeBean.create("Ana2", "ana2@mail.pt", "123456");
            dadoBiomedicoBean.create("Temperatura", "Temperatura do corpo do paciente", 50, 70, "ºC", new LinkedList<String>(List.of("Saudavel", "Febre")));
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}