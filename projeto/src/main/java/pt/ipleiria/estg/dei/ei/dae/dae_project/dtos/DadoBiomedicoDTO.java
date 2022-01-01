package pt.ipleiria.estg.dei.ei.dae.dae_project.dtos;

import java.io.Serializable;

public class DadoBiomedicoDTO implements Serializable {

    private String doenteId;
    private String nome;
    private int tipoId;
    private double valorQuantitativo;
    private String unidade;
    private String valorQualitativo;
    private String date;

    public DadoBiomedicoDTO() {
    }

    public DadoBiomedicoDTO(String doenteId, String nome, int tipoId, double valorQuantitativo, String unidade, String valorQualitativo, String date) {
        this.doenteId = doenteId;
        this.nome = nome;
        this.tipoId = tipoId;
        this.valorQuantitativo = valorQuantitativo;
        this.unidade = unidade;
        this.valorQualitativo = valorQualitativo;
        this.date = date;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDoenteId() {
        return doenteId;
    }

    public void setDoenteId(String doenteId) {
        this.doenteId = doenteId;
    }

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }

    public double getValorQuantitativo() {
        return valorQuantitativo;
    }

    public void setValorQuantitativo(double valorQuantitativo) {
        this.valorQuantitativo = valorQuantitativo;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        this.unidade = unidade;
    }

    public String getValorQualitativo() {
        return valorQualitativo;
    }

    public void setValorQualitativo(String valorQualitativo) {
        this.valorQualitativo = valorQualitativo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
