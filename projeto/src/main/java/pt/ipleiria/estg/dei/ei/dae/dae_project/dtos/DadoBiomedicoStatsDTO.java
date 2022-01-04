package pt.ipleiria.estg.dei.ei.dae.dae_project.dtos;

import java.io.Serializable;

public class DadoBiomedicoStatsDTO implements Serializable {

    private String dataInicio;
    private String dataFim;
    private int tipoId;

    public DadoBiomedicoStatsDTO() {
    }

    public DadoBiomedicoStatsDTO(String dataInicio, String dataFim, int tipoId) {
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.tipoId = tipoId;
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

    public int getTipoId() {
        return tipoId;
    }

    public void setTipoId(int tipoId) {
        this.tipoId = tipoId;
    }
}
