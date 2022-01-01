package pt.ipleiria.estg.dei.ei.dae.dae_project.entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="DadoBiomedico")
@NamedQueries({
        @NamedQuery(
                name = "getAllDadoBiomedicoDoente", query = "SELECT s FROM DadoBiomedico s WHERE s.doente = :doente ORDER BY s.date DESC"
        )
})
public class DadoBiomedico {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String nome;
    @ManyToOne @JoinColumn(name="DOENTE_ID")
    private Doente doente;
    @ManyToOne @JoinColumn(name="TIPO_ID")
    private TipoDadoBiomedico tipoDadoBiomedico;
    private String unidades;
    private double valorQuantitativo;
    private String valoreQualitativo;
    private Date date;

    public DadoBiomedico() {
    }

    public DadoBiomedico(String nome, Doente doente, TipoDadoBiomedico tipoDadoBiomedico, String unidades, double valorQuantitativo, String valoreQualitativo, Date date) {
        this.id = id;
        this.nome = nome;
        this.doente = doente;
        this.tipoDadoBiomedico = tipoDadoBiomedico;
        this.unidades = unidades;
        this.valorQuantitativo = valorQuantitativo;
        this.valoreQualitativo = valoreQualitativo;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Doente getDoente() {
        return doente;
    }

    public void setDoenteId(Doente doente) {
        this.doente = doente;
    }

    public String getUnidades() {
        return unidades;
    }

    public void setUnidades(String unidades) {
        this.unidades = unidades;
    }

    public TipoDadoBiomedico getTipoId() {
        return tipoDadoBiomedico;
    }

    public void setTipoId(TipoDadoBiomedico tipoDadoBiomedico) {
        this.tipoDadoBiomedico = tipoDadoBiomedico;
    }

    public double getValorQuantitativo() {
        return valorQuantitativo;
    }

    public void setValorQuantitativo(double valorQuantitativo) {
        this.valorQuantitativo = valorQuantitativo;
    }

    public String getValoreQualitativo() {
        return valoreQualitativo;
    }

    public void setValoreQualitativo(String valoreQualitativo) {
        this.valoreQualitativo = valoreQualitativo;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
