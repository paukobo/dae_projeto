package pt.ipleiria.estg.dei.ei.dae.dae_project.dtos;

import java.io.Serializable;

public class DoenteDTO implements Serializable {
    private String name;
    private String email;
    private String password;
    private String contact;
    private String address;
    private String profissionalEmail;

    public DoenteDTO() {
    }

    public DoenteDTO(String name, String email, String password, String contact, String address, String profissionalEmail) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.address = address;
        this.profissionalEmail = profissionalEmail;
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

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getProfissionalEmail() {
        return profissionalEmail;
    }

    public void setProfissionalEmail(String profissionalEmail) {
        this.profissionalEmail = profissionalEmail;
    }
}
