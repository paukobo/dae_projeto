package pt.ipleiria.estg.dei.ei.dae.dae_project.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(
                name = "getAllProfissionaisSaude",
                query = "SELECT p FROM ProfissionalSaude p ORDER BY p.name"
        )
})
public class ProfissionalSaude extends User implements Serializable {

    @OneToMany(mappedBy = "profissionalSaude", cascade = CascadeType.REMOVE)
    private List<Doente> doentes;

    public ProfissionalSaude() {
        this.doentes = new LinkedList<>();
    }

    public ProfissionalSaude(String name, String email, String password) {
        super(name, email, password);
        this.doentes = new LinkedList<>();
    }

    public List<Doente> getDoentes() {
        return doentes;
    }

    public void setDoentes(List<Doente> doentes) {
        this.doentes = doentes;
    }

    public void addDoente(Doente doente){
        if (doentes.contains(doente)) return;
        this.doentes.add(doente);
    }

    public void removeDoente(Doente doente){
        if (!doentes.contains(doente)) return;
        this.doentes.remove(doente);
    }
}
