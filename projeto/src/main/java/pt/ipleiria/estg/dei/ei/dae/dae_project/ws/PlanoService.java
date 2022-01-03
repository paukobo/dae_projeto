package pt.ipleiria.estg.dei.ei.dae.dae_project.ws;

import pt.ipleiria.estg.dei.ei.dae.dae_project.dtos.DoenteDTO;
import pt.ipleiria.estg.dei.ei.dae.dae_project.dtos.PlanoDTO;
import pt.ipleiria.estg.dei.ei.dae.dae_project.dtos.PrescricaoDTO;
import pt.ipleiria.estg.dei.ei.dae.dae_project.ejbs.PlanoBean;
import pt.ipleiria.estg.dei.ei.dae.dae_project.ejbs.PrescricaoBean;
import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.Doente;
import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.Plano;
import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.Prescricao;
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

@Path("planos")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class PlanoService {

    @EJB
    private PlanoBean planoBean;

    @Context
    private SecurityContext securityContext;

    @GET
    @Path("/")
    public List<PlanoDTO> getAllPlanosWS() {
        return planostoDTOs(planoBean.getAllPlanos());
    }

    @GET
    @Path("/{id}/prescricoes")
    public Response getPlanosPrescricoes(@PathParam("id") int id) {
        Plano plano = planoBean.findPlano(id);
        if (plano != null) {
            var dtos = prescricoesToDTOs(plano.getPrescricoes());
            return Response.ok(dtos).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("ERROR_FINDING_PLANO").build();
    }

    @POST
    @Path("/")
    public Response createNewPlano(Plano planoDTO) throws MyEntityExistsException, MyEntityNotFoundException, MyConstraintViolationException {
        int id = planoBean.create(
                planoDTO.getDescricao(),
                planoDTO.getDuracao()
        );
        Plano newPlano = planoBean.findPlano(id);
        if(newPlano  == null)
            throw new MyEntityNotFoundException("Plano " + id + " not found");
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    public Response getPlanoDetails(@PathParam("id") int id) {
        Plano plano = planoBean.findPlano(id);
        if (plano != null) {
            return Response.ok(planotoDTO(plano)).build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity("ERROR_FINDING_PLANO")
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response removePlano (@PathParam("id") int id) throws MyEntityNotFoundException{
        planoBean.remove(id);
        Plano plano = planoBean.findPlano(id);
        if(plano != null)
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Path("/{id}")
    public Response updatePlano (@PathParam("id") int id, PlanoDTO planoDTO) throws MyEntityNotFoundException{
        boolean updated = planoBean.update(id, planoDTO.getDescricao(), planoDTO.getDuracao());
        if(!updated)
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Path("/{id}/prescricao/associate/{id_prescricao}")
    public Response associatePlanoWithPrescricao (@PathParam("id") int id, @PathParam("id_prescricao") int id_prescricao) throws MyEntityNotFoundException{
        boolean updated = planoBean.associatePlanoWithPrescricao(id, id_prescricao);
        if(!updated)
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Path("/{id}/prescricao/disassociate/{id_prescricao}")
    public Response disassociatePlanoWithPrescricao (@PathParam("id") int id, @PathParam("id_prescricao") int id_prescricao) throws MyEntityNotFoundException{
        boolean updated = planoBean.disassociatePlanoWithPrescricao(id, id_prescricao);
        if(!updated)
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        return Response.status(Response.Status.OK).build();
    }

    private PlanoDTO planotoDTO(Plano plano){
        return new PlanoDTO(
                plano.getIdPlano(),
                plano.getDescricao(),
                plano.getDuracao(),
                prescricoesToDTOs(plano.getPrescricoes())
        );
    }

    private List<PlanoDTO> planostoDTOs(List<Plano> planos){
        return planos.stream().map(this::planotoDTO).collect(Collectors.toList());
    }

    private PrescricaoDTO prescricaoToDTO(Prescricao prescricao){
        return new PrescricaoDTO(
                prescricao.getId(),
                prescricao.getDescricao(),
                prescricao.getDataInicio(),
                prescricao.getDataFim(),
                prescricao.getDuracao(),
                doentesToDTOs(prescricao.getDoentes())
        );
    }

    private List<PrescricaoDTO> prescricoesToDTOs(List<Prescricao> prescricoes){
        return prescricoes.stream().map(this::prescricaoToDTO).collect(Collectors.toList());
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
