package pt.ipleiria.estg.dei.ei.dae.dae_project.ejbs;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

@Startup
@Singleton
public class ConfigBean {

    @PostConstruct
    public void populateDB() {
        System.out.println("Hello Java EE!");
    }
}