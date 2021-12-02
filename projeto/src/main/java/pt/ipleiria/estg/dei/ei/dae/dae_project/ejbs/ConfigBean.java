package pt.ipleiria.estg.dei.ei.dae.dae_project.ejbs;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
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

    private static final Logger logger = Logger.getLogger("ejbs.ConfigBean");


    @PostConstruct
    public void populateDB() {
        try {
            doenteBean.create(1, "João", "joao@mail.pt", "1234");
            adminBean.create(100, "Pedro", "pedro@mail.pt", "12345");
            profissionalSaudeBean.create(200, "Ana", "ana@mail.pt", "123456");
            profissionalSaudeBean.create(201, "Ana2", "ana2@mail.pt", "123456");

        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage());
        }
    }
}