package pt.ipleiria.estg.dei.ei.dae.dae_project.ws;

import pt.ipleiria.estg.dei.ei.dae.dae_project.dtos.DoenteDTO;
import pt.ipleiria.estg.dei.ei.dae.dae_project.dtos.ProfissionalSaudeDTO;
import pt.ipleiria.estg.dei.ei.dae.dae_project.ejbs.ProfissionalSaudeBean;
import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.Doente;
import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.ProfissionalSaude;
import pt.ipleiria.estg.dei.ei.dae.dae_project.exceptions.MyConstraintViolationException;
import pt.ipleiria.estg.dei.ei.dae.dae_project.exceptions.MyEntityExistsException;
import pt.ipleiria.estg.dei.ei.dae.dae_project.exceptions.MyEntityNotFoundException;

import javax.annotation.security.RolesAllowed;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import java.util.List;
import java.util.stream.Collectors;

@Path("profissionaisSaude")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class ProfissionalSaudeService {
    @EJB
    private ProfissionalSaudeBean profissionalSaudeBean;

    @Context
    private SecurityContext securityContext;

    @GET
    @Path("/")
    @RolesAllowed({"Admin"})
    public List<ProfissionalSaudeDTO> getAllProfissionaisSaudeWS() {
        return toDTOs(profissionalSaudeBean.getAllProfissionaisSaude());
    }

    @GET
    @Path("{email}/doentes")
    @RolesAllowed({"Admin","ProfissionalSaude"})
    public Response getDoentesProfissionaisSaude(@PathParam("email") String email) {
        ProfissionalSaude profissionalSaude = profissionalSaudeBean.findProfissional(email);
        if (profissionalSaude != null) {
            var dtos = doentesToDTOs(profissionalSaude.getDoentes());
            return Response.ok(dtos).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("ERROR_FINDING_STUDENT").build();
    }

    @POST
    @Path("/")
    @RolesAllowed({"Admin"})
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
    @RolesAllowed({"Admin","ProfissionalSaude"})
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
    @Path("/{email}")@RolesAllowed({"Admin"})
    public Response removeProfissionalSaude (@PathParam("email") String id) throws MyEntityNotFoundException{
        profissionalSaudeBean.remove(id);
        ProfissionalSaude profissionalSaude = profissionalSaudeBean.findProfissional(id);
        if(profissionalSaude != null)
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        return Response.status(Response.Status.OK).build();
    }


    @PUT
    @Path("/{email}")
    @RolesAllowed({"Admin"})
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
                profissionalSaude.getPassword(),
                doentesToDTOs(profissionalSaude.getDoentes())
        );
    }

    private List<ProfissionalSaudeDTO> toDTOs(List<ProfissionalSaude> profissionaisSaude){
        return profissionaisSaude.stream().map(this::toDTO).collect(Collectors.toList());
    }

    private DoenteDTO doenteToDTO(Doente doente){
        return new DoenteDTO(
                doente.getName(),
                doente.getEmail(),
                doente.getPassword(),
                doente.getContact(),
                doente.getAddress(),
                doente.getProfissionalSaude().getEmail()
        );
    }

    private List<DoenteDTO> doentesToDTOs(List<Doente> doentes){
        return doentes.stream().map(this::doenteToDTO).collect(Collectors.toList());
    }
}
