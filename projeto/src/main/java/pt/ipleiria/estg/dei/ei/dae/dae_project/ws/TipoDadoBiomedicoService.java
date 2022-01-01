package pt.ipleiria.estg.dei.ei.dae.dae_project.ws;

import pt.ipleiria.estg.dei.ei.dae.dae_project.dtos.TipoDadoBiomedicoDTO;
import pt.ipleiria.estg.dei.ei.dae.dae_project.ejbs.TipoDadoBiomedicoBean;
import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.TipoDadoBiomedico;
import pt.ipleiria.estg.dei.ei.dae.dae_project.exceptions.MyEntityNotFoundException;

import javax.ejb.DuplicateKeyException;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("dadosbiomedicos")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class TipoDadoBiomedicoService {

    @EJB
    private TipoDadoBiomedicoBean biomedicoBean;

    @GET
    @Path("/")
    public List<TipoDadoBiomedicoDTO> getAllTipoDadoBiomedicos() {
        return toDTOs(biomedicoBean.getAllDadosBiomedicos());
    }

    @GET
    @Path("/{id}")
    public Response getTipoDadoBiomedico(@PathParam("id") int id) {
        TipoDadoBiomedico dadoBiomedico = biomedicoBean.findTipoDadoBiomedico(id);
        if (dadoBiomedico != null) {
            return Response.ok(toDTO(dadoBiomedico)).build();
        }
        return Response.status(Response.Status.NOT_FOUND)
                .entity("ERROR_FINDING_SUBJECT")
                .build();
    }

    @POST
    @Path("/")
    public Response createNewDadoBiomedico (TipoDadoBiomedicoDTO biomedicoDTO){
        System.out.println(biomedicoDTO.getValoresQualitativos());
        biomedicoBean.create(
                biomedicoDTO.getNome(),
                biomedicoDTO.getDescricao(),
                biomedicoDTO.getValorMin(),
                biomedicoDTO.getValorMax(),
                biomedicoDTO.getUnidades(),
                biomedicoDTO.getValoresQualitativos());

        TipoDadoBiomedico newDadoBiomedico = biomedicoBean.findTipoDadoBiomedico(biomedicoDTO.getNome());

        if(newDadoBiomedico == null)
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

        return Response.status(Response.Status.CREATED)
                .entity(toDTO(newDadoBiomedico))
                .build();
    }

    @PUT
    @Path("{id}")
    public Response updateTipoDadoBiomedico (@PathParam("id") int id, TipoDadoBiomedicoDTO dadoBiomedicoDTO) throws MyEntityNotFoundException, DuplicateKeyException {

        biomedicoBean.update(id, dadoBiomedicoDTO.getNome(), dadoBiomedicoDTO.getDescricao(),  dadoBiomedicoDTO.getValorMin(), dadoBiomedicoDTO.getValorMax(),dadoBiomedicoDTO.getUnidades(),dadoBiomedicoDTO.getValoresQualitativos());
        TipoDadoBiomedico dadoBiomedico = biomedicoBean.findTipoDadoBiomedico(id);
        return Response.status(Response.Status.ACCEPTED)
                .entity(toDTO(dadoBiomedico))
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTipoDadoBiomedico (@PathParam("id") int id) throws MyEntityNotFoundException {
        TipoDadoBiomedico dadoBiomedico = biomedicoBean.delete(id);

        if(dadoBiomedico == null)
            return Response.status(Response.Status.NOT_FOUND).build();

        return Response.status(Response.Status.OK)
                .entity(toDTO(dadoBiomedico))
                .build();
    }

    private TipoDadoBiomedicoDTO toDTO(TipoDadoBiomedico dadoBiomedico) {

        return new TipoDadoBiomedicoDTO(
                dadoBiomedico.getId(),
                dadoBiomedico.getNome(),
                dadoBiomedico.getDescricao(),
                dadoBiomedico.getValorMin(),
                dadoBiomedico.getValorMax(),
                dadoBiomedico.getUnidades(),
                List.copyOf(dadoBiomedico.getValoresQualitativos().values())
        );
    }

    private List<TipoDadoBiomedicoDTO> toDTOs(List<TipoDadoBiomedico> dadoBiomedico) {
        return dadoBiomedico.stream().map(this::toDTO).collect(Collectors.toList());
    }
}
