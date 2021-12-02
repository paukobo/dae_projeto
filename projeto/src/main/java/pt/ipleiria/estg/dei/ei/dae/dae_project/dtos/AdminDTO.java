package pt.ipleiria.estg.dei.ei.dae.dae_project.dtos;

import java.io.Serializable;

public class AdminDTO implements Serializable {
    private String name;
    private String email;

    public AdminDTO(String name, String email) {
        this.name = name;
        this.email = email;
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

}
