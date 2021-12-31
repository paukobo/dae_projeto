package pt.ipleiria.estg.dei.ei.dae.dae_project.entities;


import io.smallrye.common.constraint.NotNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

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
    @JoinColumn(name = "MEDICO_ID")
    @NotNull
    private ProfissionalSaude profissionalSaude;

    //doente tem vários dados biomédicos --> vários dados biomédicos têm vários doentes

    private String contact;

    private String address;

    @ManyToMany(mappedBy = "doentes")
    private List<Prescricao> prescricoes;

    public Doente() {
        this.prescricoes = new LinkedList<>();
    }

    public Doente(String name, String email, String password, String contact, String address) {
        super(name, email, password);
        this.contact = contact;
        this.address = address;
        this.prescricoes = new LinkedList<>();
    }

    public Doente(String name, String email, String password, String contact, String address, ProfissionalSaude profissionalSaude) {
        super(name, email, password);
        this.profissionalSaude = profissionalSaude;
        this.contact = contact;
        this.address = address;
        this.prescricoes = new LinkedList<>();
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

    public ProfissionalSaude getProfissionalSaude() {
        return profissionalSaude;
    }

    public void setProfissionalSaude(ProfissionalSaude profissionalSaude) {
        this.profissionalSaude = profissionalSaude;
    }

    public List<Prescricao> getPrescricoes() {
        return prescricoes;
    }

    public void setPrescricoes(List<Prescricao> prescricoes) {
        this.prescricoes = prescricoes;
    }

    public void addPrescricao(Prescricao prescricao){
        if (prescricoes.contains(prescricao)){
            return;
        }
        this.prescricoes.add(prescricao);
    }

    public void removePrescricao(Prescricao prescricao){
        if (!prescricoes.contains(prescricao)){
            return;
        }
        this.prescricoes.remove(prescricao);
    }
}