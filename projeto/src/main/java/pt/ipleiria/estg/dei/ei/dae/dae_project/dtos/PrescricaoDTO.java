package pt.ipleiria.estg.dei.ei.dae.dae_project.dtos;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class PrescricaoDTO implements Serializable {
    private int id;
    private String descricao;
    private String dataInicio;
    private String dataFim;
    private int duracao;
    private List<DoenteDTO> doentesDTOList;

    public PrescricaoDTO() {
        this.doentesDTOList = new LinkedList<>();
    }

    public PrescricaoDTO(int id, String descricao, String dataInicio, String dataFim, int duracao) {
        this.id = id;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.duracao = duracao;
    }

    public PrescricaoDTO(int id, String descricao, String dataInicio, String dataFim, int duracao, List<DoenteDTO> doentesDTOList) {
        this.id = id;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.duracao = duracao;
        this.doentesDTOList = new LinkedList<>(doentesDTOList);
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

    public List<DoenteDTO> getDoentesDTOList() {
        return doentesDTOList;
    }

    public void setDoentesDTOList(List<DoenteDTO> doentesDTOList) {
        this.doentesDTOList = doentesDTOList;
    }
}
