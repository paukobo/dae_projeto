package pt.ipleiria.estg.dei.ei.dae.dae_project.entities;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

@Entity
@Table(name="Plano", uniqueConstraints = @UniqueConstraint(columnNames = {"IDPLANO"}))
@NamedQueries({
        @NamedQuery(
                name = "getAllPlanos",
                query = "SELECT p FROM Plano p ORDER BY p.idPlano"
        )
})
public class Plano {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int idPlano;

    private String descricao;

    private int duracao;

    @ManyToMany(mappedBy = "planos")
    private List<Prescricao> prescricoes;

    public Plano() {
        this.prescricoes = new LinkedList<>();
    }

    public Plano(String descricao, int duracao) {
        this.descricao = descricao;
        this.duracao = duracao;
        this.prescricoes = new LinkedList<>();
    }

    public int getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(int idPlano) {
        this.idPlano = idPlano;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public List<Prescricao> getPrescricoes() {
        return prescricoes;
    }

    public void setPrescricoes(List<Prescricao> prescricoes) {
        this.prescricoes = prescricoes;
    }

    public void addPrescricao(Prescricao prescricao){
        if(prescricoes.contains(prescricao)){
            return;
        }
        prescricoes.add(prescricao);
    }

    public void removePrescricao(Prescricao prescricao){
        if(!prescricoes.contains(prescricao)){
            return;
        }
        prescricoes.remove(prescricao);
    }

}
