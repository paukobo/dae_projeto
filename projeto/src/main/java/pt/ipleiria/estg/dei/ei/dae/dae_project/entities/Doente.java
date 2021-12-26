package pt.ipleiria.estg.dei.ei.dae.dae_project.entities;


import io.smallrye.common.constraint.NotNull;

import javax.persistence.*;
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
    @ManyToOne
    @NotNull
    private ProfissionalSaude medico;

    //doente tem vários dados biomédicos --> vários dados biomédicos têm vários doentes

    private String contact;

    private String address;

    public Doente() {
    }

    public Doente(String name, String email, String password, String contact, String address) {
        super(name, email, password);
        this.contact = contact;
        this.address = address;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}