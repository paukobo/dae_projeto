package pt.ipleiria.estg.dei.ei.dae.dae_project.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.logging.Logger;

@Provider
public class CatchAllExceptionMapper implements ExceptionMapper<CatchAllException> {
    private static final Logger logger = Logger.getLogger("exceptions.CatchAllExceptionMapper");

    @Override
    public Response toResponse(CatchAllException e) {
        String errorMsg = e.getMessage();
        logger.warning("ERROR: " + errorMsg);
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorMsg).build();
    }
}
