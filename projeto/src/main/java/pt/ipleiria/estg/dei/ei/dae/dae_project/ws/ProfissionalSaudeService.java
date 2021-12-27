package pt.ipleiria.estg.dei.ei.dae.dae_project.ws;

import pt.ipleiria.estg.dei.ei.dae.dae_project.dtos.DoenteDTO;
import pt.ipleiria.estg.dei.ei.dae.dae_project.dtos.ProfissionalSaudeDTO;
import pt.ipleiria.estg.dei.ei.dae.dae_project.ejbs.DoenteBean;
import pt.ipleiria.estg.dei.ei.dae.dae_project.ejbs.ProfissionalSaudeBean;
import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.Admin;
import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.Doente;
import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.ProfissionalSaude;
import pt.ipleiria.estg.dei.ei.dae.dae_project.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.dae_project.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.dae_project.exceptions.MyEntityNotFoundException;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
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

    @POST
    @Path("/")
    public Response createNewProfissionalSaude (ProfissionalSaudeDTO profissionalSaudeDTO) throws MyEntityExistsException, MyEntityNotFoundException, MyConstraintViolationException {
        String id = profissionalSaudeBean.create(
                profissionalSaudeDTO.getName(),
                profissionalSaudeDTO.getEmail(),
                profissionalSaudeDTO.getPassword()
        );
        ProfissionalSaude newProfissionalSaude = profissionalSaudeBean.findProfissional(id);
        if(newProfissionalSaude == null)
            throw new MyEntityNotFoundException("Profissional Saúde: " + profissionalSaudeDTO.getName() + " not found");
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{email}")
    public Response getProfissionalSaudeDetails(@PathParam("email") String id) {
        ProfissionalSaude profissionalSaude = profissionalSaudeBean.findProfissional(id);
        if (profissionalSaude != null) {
            return Response.ok(toDTO(profissionalSaude)).build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity("ERROR_FINDING_PROFISSIONAL_SAUDE")
                .build();
    }

    @DELETE
    @Path("/{email}")
    public Response removeProfissionalSaude (@PathParam("email") String id) throws MyEntityNotFoundException{
        profissionalSaudeBean.remove(id);
        ProfissionalSaude profissionalSaude = profissionalSaudeBean.findProfissional(id);
        if(profissionalSaude != null)
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        return Response.status(Response.Status.OK).build();
    }


    @PUT
    @Path("/{email}")
    public Response updateProfissionalSaude (@PathParam("email") String id, ProfissionalSaudeDTO profissionalSaudeDTO) throws MyEntityNotFoundException{
        boolean updated = profissionalSaudeBean.update(id, profissionalSaudeDTO.getName(), profissionalSaudeDTO.getEmail(), profissionalSaudeDTO.getPassword());
        if(!updated)
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        return Response.status(Response.Status.OK).build();
    }


    private ProfissionalSaudeDTO toDTO(ProfissionalSaude profissionalSaude){
        return new ProfissionalSaudeDTO(
                profissionalSaude.getName(),
                profissionalSaude.getEmail(),
                profissionalSaude.getPassword()
        );
    }

    private List<ProfissionalSaudeDTO> toDTOs(List<ProfissionalSaude> profissionaisSaude){
        return profissionaisSaude.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
