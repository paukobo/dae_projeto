package pt.ipleiria.estg.dei.ei.dae.dae_project.dtos;

import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.Doente;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class ProfissionalSaudeDTO implements Serializable {
    private String name;
    private String email;
    private String password;
    private List<DoenteDTO> doentesDTOList;

    public ProfissionalSaudeDTO() {
        this.doentesDTOList = new LinkedList<>();
    }

    public ProfissionalSaudeDTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public ProfissionalSaudeDTO(String name, String email, String password, List<DoenteDTO> doentesDTOList) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.doentesDTOList = new LinkedList<>(doentesDTOList);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<DoenteDTO> getDoentesDTOList() {
        return doentesDTOList;
    }

    public void setDoentesDTOList(List<DoenteDTO> doentesDTOList) {
        this.doentesDTOList = doentesDTOList;
    }
}
