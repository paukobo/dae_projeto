package pt.ipleiria.estg.dei.ei.dae.dae_project.entities;

import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllProfissionaisSaude",
                query = "SELECT p FROM ProfissionalSaude p ORDER BY p.name"
        )
})
public class ProfissionalSaude extends User implements Serializable {
    public ProfissionalSaude() {
    }

    public ProfissionalSaude(String name, String email, String password) {
        super(name, email, password);
    }
}
