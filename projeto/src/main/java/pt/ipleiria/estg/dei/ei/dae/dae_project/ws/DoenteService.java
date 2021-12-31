package pt.ipleiria.estg.dei.ei.dae.dae_project.ws;

import pt.ipleiria.estg.dei.ei.dae.dae_project.dtos.DoenteDTO;
import pt.ipleiria.estg.dei.ei.dae.dae_project.ejbs.DoenteBean;
import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.Admin;
import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.Doente;
import pt.ipleiria.estg.dei.ei.dae.dae_project.exceptions.CatchAllException;
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

@Path("doentes")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class DoenteService {
    @EJB
    private DoenteBean doenteBean;

    @Context
    private SecurityContext securityContext;

    @GET
    @Path("/")
    public List<DoenteDTO> getAllDoentesWS() {
        return toDTOs(doenteBean.getAllDoentes());
    }

    @POST
    @Path("/")
    public Response createNewDoente (DoenteDTO doenteDTO) throws MyEntityExistsException, MyEntityNotFoundException, MyConstraintViolationException {
        String id = doenteBean.create(
                doenteDTO.getName(),
                doenteDTO.getEmail(),
                doenteDTO.getPassword(),
                doenteDTO.getContact(),
                doenteDTO.getAddress(),
                doenteDTO.getProfissionalEmail()
        );
        Doente newDoente = doenteBean.findDoente(id);
        if(newDoente == null)
            throw new MyEntityNotFoundException("Doente " + doenteDTO.getName() + " not found");
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{email}")
    public Response getDoenteDetails(@PathParam("email") String id) {
        Doente doente = doenteBean.findDoente(id);
        if (doente != null) {
            return Response.ok(toDTO(doente)).build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity("ERROR_FINDING_DOENTE")
                .build();
    }

    @DELETE
    @Path("/{email}")
    public Response removeDoente (@PathParam("email") String id) throws MyEntityNotFoundException{
        doenteBean.remove(id);
        Doente doente = doenteBean.findDoente(id);
        if(doente != null)
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        return Response.status(Response.Status.OK).build();
    }


    @PUT
    @Path("/{email}")
    public Response updateDoente (@PathParam("email") String id, DoenteDTO doenteDTO) throws MyEntityNotFoundException{
        boolean updated = doenteBean.update(doenteDTO.getName(), doenteDTO.getEmail(), doenteDTO.getPassword(), doenteDTO.getContact(), doenteDTO.getAddress());
        if(!updated)
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Path("{email}/profissionalSaude/associate/{email_medico}")
    public Response associateDoenteToProfissional (@PathParam("email") String email, @PathParam("email_medico") String email_medico) throws MyEntityNotFoundException{
        boolean updated = doenteBean.associateDoenteToProfissional(email, email_medico);
        if(!updated)
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Path("{email}/profissionalSaude/disassociate/{email_medico}")
    public Response disassociateDoenteToProfissional (@PathParam("email") String email, @PathParam("email_medico") String email_medico) throws MyEntityNotFoundException{
        boolean updated = doenteBean.disassociateDoenteToProfissional(email, email_medico);
        if(!updated)
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        return Response.status(Response.Status.OK).build();
    }

    private DoenteDTO toDTO(Doente doente){
        return new DoenteDTO(
                doente.getName(),
                doente.getEmail(),
                doente.getPassword(),
                doente.getContact(),
                doente.getAddress(),
                doente.getProfissionalSaude().getEmail()
        );
    }

    private List<DoenteDTO> toDTOs(List<Doente> doentes){
        return doentes.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
