package pt.ipleiria.estg.dei.ei.dae.dae_project.entities;


import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllDoentes",
                query = "SELECT d FROM Doente d ORDER BY d.name"
        )
})
public class Doente extends User implements Serializable {
    //doente tem 1 médico --> 1 médico tem vários doentes

    //doente tem vários dados biomédicos --> vários dados biomédicos têm vários doentes


    public Doente() {
    }

    public Doente(String name, String email, String password) {
        super(name, email, password);
    }

}