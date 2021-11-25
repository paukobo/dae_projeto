package pt.ipleiria.estg.dei.ei.dae.dae_project.entities;


import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import java.io.Serializable;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllAdmins",
                query = "SELECT a FROM Admin a ORDER BY a.name"
        )
})
public class Admin extends User implements Serializable {
    public Admin() {
    }

    public Admin(int id, String name, String email, String password) {
        super(id, name, email, password);
    }
}
