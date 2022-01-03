package pt.ipleiria.estg.dei.ei.dae.dae_project.ws;

import pt.ipleiria.estg.dei.ei.dae.dae_project.dtos.DoenteDTO;
import pt.ipleiria.estg.dei.ei.dae.dae_project.dtos.PlanoDTO;
import pt.ipleiria.estg.dei.ei.dae.dae_project.dtos.PrescricaoDTO;
import pt.ipleiria.estg.dei.ei.dae.dae_project.dtos.PrescricaoDTO;
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

@Path("prescricoes")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class PrescricaoService {

    @EJB
    private PrescricaoBean prescricaoBean;

    @Context
    private SecurityContext securityContext;

    @GET
    @Path("/")
    public List<PrescricaoDTO> getAllPrescricoesWS() {
        return toDTOs(prescricaoBean.getAllPrescricoes());
    }

    @GET
    @Path("{id}/doentes")
    public Response getPrescricoesDoente(@PathParam("id") int id) {
        Prescricao prescricao = prescricaoBean.findPrescricao(id);
        if (prescricao != null) {
            var dtos = doentesToDTOs(prescricao.getDoentes());
            return Response.ok(dtos).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("ERROR_FINDING_PRESCRICAO").build();
    }

    @GET
    @Path("{id}/planos")
    public Response getPrescricoesPlano(@PathParam("id") int id) {
        Prescricao prescricao = prescricaoBean.findPrescricao(id);
        if (prescricao != null) {
            var dtos = planosToDTOs(prescricao.getPlanos());
            return Response.ok(dtos).build();
        }
        return Response.status(Response.Status.NOT_FOUND).entity("ERROR_FINDING_PRESCRICAO").build();
    }

    @POST
    @Path("/")
    public Response createNewPrescricao(PrescricaoDTO prescricaoDTO) throws MyEntityExistsException, MyEntityNotFoundException, MyConstraintViolationException {
        int id = prescricaoBean.create(
                prescricaoDTO.getDescricao(),
                prescricaoDTO.getDataInicio(),
                prescricaoDTO.getDataFim(),
                prescricaoDTO.getDuracao()
        );
        Prescricao newPrescricao = prescricaoBean.findPrescricao(id);
        if(newPrescricao == null)
            throw new MyEntityNotFoundException("Prescricao " + id + " not found");
        return Response.status(Response.Status.CREATED).build();
    }

    @GET
    @Path("/{id}")
    public Response getPrescricaoDetails(@PathParam("id") int id) {
        Prescricao prescricao = prescricaoBean.findPrescricao(id);
        if (prescricao != null) {
            return Response.ok(toDTO(prescricao)).build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity("ERROR_FINDING_PRESCRICAO")
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response removePrescricao (@PathParam("id") int id) throws MyEntityNotFoundException{
        prescricaoBean.remove(id);
        Prescricao prescricao = prescricaoBean.findPrescricao(id);
        if(prescricao != null)
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        return Response.status(Response.Status.OK).build();
    }


    @PUT
    @Path("/{id}")
    public Response updatePrescricao (@PathParam("id") int id, PrescricaoDTO prescricaoDTO) throws MyEntityNotFoundException{
        boolean updated = prescricaoBean.update(id, prescricaoDTO.getDescricao(), prescricaoDTO.getDataInicio(), prescricaoDTO.getDataFim(), prescricaoDTO.getDuracao());
        if(!updated)
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Path("{id}/doente/associate/{email}")
    public Response associatePrescricaoWithDoente (@PathParam("id") int id, @PathParam("email") String email) throws MyEntityNotFoundException{
        boolean updated = prescricaoBean.associatePrescricaoWithDoente(id, email);
        if(!updated)
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        return Response.status(Response.Status.OK).build();
    }

    @PUT
    @Path("{id}/doente/disassociate/{email}")
    public Response disassociatePrescricaoWithDoente (@PathParam("id") int id, @PathParam("email") String email) throws MyEntityNotFoundException{
        boolean updated = prescricaoBean.disassociatePrescricaoWithDoente(id, email);
        if(!updated)
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        return Response.status(Response.Status.OK).build();
    }

    private PrescricaoDTO toDTO(Prescricao prescricao){
        return new PrescricaoDTO(
                prescricao.getId(),
                prescricao.getDescricao(),
                prescricao.getDataInicio(),
                prescricao.getDataFim(),
                prescricao.getDuracao(),
                doentesToDTOs(prescricao.getDoentes()),
                planosToDTOs(prescricao.getPlanos())
        );
    }

    private List<PrescricaoDTO> toDTOs(List<Prescricao> prescricoes){
        return prescricoes.stream().map(this::toDTO).collect(Collectors.toList());
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

    private PlanoDTO planoToDTO(Plano plano){
        return new PlanoDTO(
                plano.getIdPlano(),
                plano.getDescricao(),
                plano.getDuracao()
        );
    }

    private List<PlanoDTO> planosToDTOs(List<Plano> planos){
        return planos.stream().map(this::planoToDTO).collect(Collectors.toList());
    }
}
