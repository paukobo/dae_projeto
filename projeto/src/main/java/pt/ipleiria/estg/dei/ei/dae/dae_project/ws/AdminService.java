package pt.ipleiria.estg.dei.ei.dae.dae_project.ws;

import pt.ipleiria.estg.dei.ei.dae.dae_project.dtos.AdminDTO;
import pt.ipleiria.estg.dei.ei.dae.dae_project.dtos.DoenteDTO;
import pt.ipleiria.estg.dei.ei.dae.dae_project.dtos.ProfissionalSaudeDTO;
import pt.ipleiria.estg.dei.ei.dae.dae_project.ejbs.AdminBean;
import pt.ipleiria.estg.dei.ei.dae.dae_project.ejbs.DoenteBean;
import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.Admin;
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

@Path("admins")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class AdminService {
    @EJB
    private AdminBean adminBean;

    @Context
    private SecurityContext securityContext;

    @GET
    @Path("/")
    @RolesAllowed({"Admin"})
    public List<AdminDTO> getAllAdminsWS() {
        return toDTOs(adminBean.getAllAdmins());
    }

    @POST
    @Path("/")
    @RolesAllowed({"Admin"})
    public Response createNewAdmin (AdminDTO adminDTO) throws MyEntityExistsException, MyEntityNotFoundException, MyConstraintViolationException {
        String id = adminBean.create(
                adminDTO.getName(),
                adminDTO.getEmail(),
                adminDTO.getPassword()
        );
        Admin newAdmin = adminBean.findAdmin(id);
        if(newAdmin == null)
            throw new MyEntityNotFoundException("Administrador " + adminDTO.getName() + " not found");
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{email}")
    @RolesAllowed({"Admin"})
    public Response getAdminiDetails(@PathParam("email") String id) {
        Admin admin = adminBean.findAdmin(id);
        if (admin != null) {
            return Response.ok(toDTO(admin)).build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity("ERROR_FINDING_ADMIN")
                .build();
    }

    @DELETE
    @Path("/{email}")
    @RolesAllowed({"Admin"})
    public Response removeAdmin (@PathParam("email") String id) throws MyEntityNotFoundException{
        adminBean.remove(id);
        Admin admin = adminBean.findAdmin(id);
        if(admin != null)
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        return Response.status(Response.Status.OK).build();
    }


    @PUT
    @Path("/{email}")
    @RolesAllowed({"Admin"})
    public Response updateAdmin (@PathParam("email") String id, AdminDTO adminDTO) throws MyEntityNotFoundException{
        boolean updated = adminBean.update(id, adminDTO.getName(), adminDTO.getEmail(), adminDTO.getPassword());
        if(!updated)
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        return Response.status(Response.Status.OK).build();
    }


    private AdminDTO toDTO(Admin admin){
        return new AdminDTO(
                admin.getName(),
                admin.getEmail(),
                admin.getPassword()
        );
    }

    private List<AdminDTO> toDTOs(List<Admin> admins){
        return admins.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
