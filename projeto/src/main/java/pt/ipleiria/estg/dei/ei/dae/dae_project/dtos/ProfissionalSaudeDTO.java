package pt.ipleiria.estg.dei.ei.dae.dae_project.dtos;

import java.io.Serializable;

public class ProfissionalSaudeDTO implements Serializable {
    private String name;
    private String email;
    private String password;

    public ProfissionalSaudeDTO() {
    }

    public ProfissionalSaudeDTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
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
}
