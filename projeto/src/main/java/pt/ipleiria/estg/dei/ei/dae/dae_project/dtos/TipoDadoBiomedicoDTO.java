package pt.ipleiria.estg.dei.ei.dae.dae_project.dtos;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class TipoDadoBiomedicoDTO implements Serializable {

    private String nome;

    private String descricao;

    private double valorMin;

    private double valorMax;

    private String unidades;

    private List<String> valoresQualitativos;


    public TipoDadoBiomedicoDTO() {
        valoresQualitativos = new LinkedList<>();
    }

    public TipoDadoBiomedicoDTO(String nome, String descricao, double valorMin, double valorMax, String unidades) {
        this.nome = nome;
        this.descricao = descricao;
        this.valorMin = valorMin;
        this.valorMax = valorMax;
        this.unidades = unidades;
        valoresQualitativos = new LinkedList<>();
    }

    public TipoDadoBiomedicoDTO(String nome, String descricao, double valorMin, double valorMax, String unidades, List<String> valoresQualitativos) {
        this.nome = nome;
        this.descricao = descricao;
        this.valorMin = valorMin;
        this.valorMax = valorMax;
        this.unidades = unidades;
        this.valoresQualitativos = valoresQualitativos;
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

    public List<String> getValoresQualitativos() {
        return valoresQualitativos;
    }

    public void setValoresQualitativos(List<String> valoresQualitativos) {
        this.valoresQualitativos = valoresQualitativos;
    }
}
