package pt.ipleiria.estg.dei.ei.dae.dae_project.dtos;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class PlanoDTO implements Serializable {
    private int id;
    private String descricao;
    private int duracao;
    private List<PrescricaoDTO> prescricaoDTOList;

    public PlanoDTO() {
        this.prescricaoDTOList = new LinkedList<>();
    }

    public PlanoDTO(int id, String descricao, int duracao) {
        this.id = id;
        this.descricao = descricao;
        this.duracao = duracao;
        this.prescricaoDTOList = new LinkedList<>();
    }

    public PlanoDTO(int id, String descricao, int duracao, List<PrescricaoDTO> prescricaoDTOList) {
        this.id = id;
        this.descricao = descricao;
        this.duracao = duracao;
        this.prescricaoDTOList = new LinkedList<>(prescricaoDTOList);
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

    public int getDuracao() {
        return duracao;
    }

    public void setDuracao(int duracao) {
        this.duracao = duracao;
    }

    public List<PrescricaoDTO> getPrescricaoDTOList() {
        return prescricaoDTOList;
    }

    public void setPrescricaoDTOList(List<PrescricaoDTO> prescricaoDTOList) {
        this.prescricaoDTOList = prescricaoDTOList;
    }
}
