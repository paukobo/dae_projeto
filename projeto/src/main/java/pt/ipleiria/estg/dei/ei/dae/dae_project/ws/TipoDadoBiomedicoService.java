package pt.ipleiria.estg.dei.ei.dae.dae_project.ws;

import pt.ipleiria.estg.dei.ei.dae.dae_project.dtos.TipoDadoBiomedicoDTO;
import pt.ipleiria.estg.dei.ei.dae.dae_project.ejbs.TipoDadoBiomedicoBean;
import pt.ipleiria.estg.dei.ei.dae.dae_project.entities.TipoDadoBiomedico;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("dadosbiomedicos")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
public class TipoDadoBiomedicoService {

    @EJB
    private TipoDadoBiomedicoBean biomedicoBean;

    @GET
    @Path("/")
    public Response getTest() {
        return Response.status(Response.Status.OK).entity("Test").build();
    }

    @POST
    @Path("/")
    public Response createNewDadoBiomedico (TipoDadoBiomedicoDTO biomedicoDTO){
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

    private TipoDadoBiomedicoDTO toDTO(TipoDadoBiomedico dadoBiomedico) {

        return new TipoDadoBiomedicoDTO(
                dadoBiomedico.getNome(),
                dadoBiomedico.getDescricao(),
                dadoBiomedico.getValorMin(),
                dadoBiomedico.getValorMax(),
                dadoBiomedico.getUnidades(),
                List.copyOf(dadoBiomedico.getValoresQualitativos().values())
        );
    }
}
