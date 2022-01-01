package pt.ipleiria.estg.dei.ei.dae.dae_project.dtos;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class DoenteDTO implements Serializable {
    private String name;
    private String email;
    private String password;
    private String contact;
    private String address;
    private String profissionalEmail;
    private List<PrescricaoDTO> prescricaoDTOList;

    public DoenteDTO() {
        this.prescricaoDTOList = new LinkedList<>();
    }

    public DoenteDTO(String name, String email, String password, String contact, String address, String profissionalEmail) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.address = address;
        this.profissionalEmail = profissionalEmail;
        this.prescricaoDTOList = new LinkedList<>();
    }

    public DoenteDTO(String name, String email, String password, String contact, String address, String profissionalEmail, List<PrescricaoDTO> prescricaoDTOList) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.contact = contact;
        this.address = address;
        this.profissionalEmail = profissionalEmail;
        this.prescricaoDTOList = new LinkedList<>(prescricaoDTOList);
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

    public List<PrescricaoDTO> getPrescricaoDTOList() {
        return prescricaoDTOList;
    }

    public void setPrescricaoDTOList(List<PrescricaoDTO> prescricaoDTOList) {
        this.prescricaoDTOList = prescricaoDTOList;
    }
}
