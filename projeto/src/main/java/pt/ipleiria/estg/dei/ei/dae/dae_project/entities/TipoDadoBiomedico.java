package pt.ipleiria.estg.dei.ei.dae.dae_project.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.Map;


@Entity
@Table(name="TipoDadoBiomedico", uniqueConstraints = @UniqueConstraint(columnNames = {"NOME"}))
@NamedQueries({
        @NamedQuery(
                name = "getAllTipoDadoBiomedicos", query = "SELECT s FROM TipoDadoBiomedico s"
        ),
        @NamedQuery(
                name = "getTipoDadoBiomedicoByNome", query = "SELECT s FROM TipoDadoBiomedico s WHERE s.nome= :nome"
        )
})
public class TipoDadoBiomedico {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    @Column(name = "NOME")
    private String nome;

    private String descricao;

    private double valorMin;

    private double valorMax;

    private String unidades;

    @ElementCollection
    private Map<Integer, String> valoresQualitativos;

    public TipoDadoBiomedico() {
        valoresQualitativos = new HashMap<>();
    }

    public TipoDadoBiomedico(String nome, String descricao, double valorMin, double valorMax, String unidades) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.valorMin = valorMin;
        this.valorMax = valorMax;
        this.unidades = unidades;
        valoresQualitativos = new HashMap<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getValorMin() {
        return valorMin;
    }

    public void setValorMin(double valorMin) {
        this.valorMin = valorMin;
    }

    public double getValorMax() {
        return valorMax;
    }

    public void setValorMax(double valorMax) {
        this.valorMax = valorMax;
    }

    public String getUnidades() {
        return unidades;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
    }

    public Map<Integer, String> getValoresQualitativos() {
        return valoresQualitativos;
    }

    public void setValoresQualitativos(Map<Integer, String> valoresQualitativos) {
        this.valoresQualitativos = valoresQualitativos;
    }

    public void addValorQualitativo(@NotNull String valor){
        if(valoresQualitativos.containsValue(valor)){
            return;
        }

        valoresQualitativos.put(valoresQualitativos.size(), valor);
    }

    public void removeValorQualitativo(@NotNull String valor){
        if(valoresQualitativos.containsValue(valor)){
            return;
        }
        valoresQualitativos.forEach((k,v) -> {
            if(valor.compareTo(v) == 0){
                valoresQualitativos.remove(k);
                return;
            }
        });
    }
}
