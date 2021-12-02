package pt.ipleiria.estg.dei.ei.dae.dae_project.ws;

import pt.ipleiria.estg.dei.ei.dae.dae_project.dtos.DoenteDTO;
import pt.ipleiria.estg.dei.ei.dae.dae_project.dtos.ProfissionalSaudeDTO;
import pt.ipleiria.estg.dei.ei.dae.dae_project.ejbs.DoenteBean;
import pt.ipleiria.estg.dei.ei.dae.dae_project.ejbs.ProfissionalSaudeBean;
import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.Doente;
import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.ProfissionalSaude;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.SecurityContext;
import java.util.List;
import java.util.stream.Collectors;

@Path("profissionais")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class ProfissionalSaudeService {
    @EJB
    private ProfissionalSaudeBean profissionalSaudeBean;

    @Context
    private SecurityContext securityContext;

    @GET
    @Path("/")
    public List<ProfissionalSaudeDTO> getAllProfissionaisSaudeWS() {
        return toDTOs(profissionalSaudeBean.getAllProfissionaisSaude());
    }


    private ProfissionalSaudeDTO toDTO(ProfissionalSaude profissionalSaude){
        return new ProfissionalSaudeDTO(
                profissionalSaude.getId(),
                profissionalSaude.getName(),
                profissionalSaude.getEmail(),
                profissionalSaude.getPassword()
        );
    }

    private List<ProfissionalSaudeDTO> toDTOs(List<ProfissionalSaude> profissionaisSaude){
        return profissionaisSaude.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
