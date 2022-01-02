package pt.ipleiria.estg.dei.ei.dae.dae_project.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="Prescricao", uniqueConstraints = @UniqueConstraint(columnNames = {"ID"}))
@NamedQueries({
        @NamedQuery(
                name = "getAllPrescricoes",
                query = "SELECT p FROM Prescricao p ORDER BY p.id"
        )
})
public class Prescricao implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    private String descricao;

    private String dataInicio;

    private String dataFim;

    private int duracao;

    @ManyToMany
    @JoinTable(name = "PRESCRICOES_DOENTES",
            joinColumns = @JoinColumn(name = "PRESCRIPTION_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "DOENTE_EMAIL", referencedColumnName = "EMAIL"))
    private List<Doente> doentes;

    @ManyToMany
    @JoinTable(name = "PLANOS_PRESCRICOES",
            joinColumns = @JoinColumn(name = "PRESCRIPTION_ID", referencedColumnName = "ID"),
            inverseJoinColumns = @JoinColumn(name = "PLANO_ID", referencedColumnName = "IDPLANO"))
    private List<Plano> planos;

    public Prescricao() {
        this.doentes = new LinkedList<>();
        this.planos = new LinkedList<>();
    }

    public Prescricao(String descricao, String dataInicio, String dataFim, int duracao) {
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.duracao = duracao;
        this.doentes = new LinkedList<>();
       this.planos = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFim() {
        return dataFim;
    }

    public void setDataFim(String dataFim) {
        this.dataFim = dataFim;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public List<Doente> getDoentes() {
        return doentes;
    }

    public void setDoentes(List<Doente> doentes) {
        this.doentes = doentes;
    }

    public List<Plano> getPlanos() {
        return planos;
    }

    public void setPlanos(List<Plano> planos) {
        this.planos = planos;
    }

    public void addDoente(Doente doente){
        if(doentes.contains(doente)){
            return;
        }
        doentes.add(doente);
    }

    public void removeDoente(Doente doente){
        if(!doentes.contains(doente)){
            return;
        }
        doentes.remove(doente);
    }

    public void addPlano(Plano plano){
        if(planos.contains(plano)){
            return;
        }
        planos.add(plano);
    }

    public void removePlano(Plano plano){
        if(!planos.contains(plano)){
            return;
        }
        planos.remove(plano);
    }
}
