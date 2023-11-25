package br.com.vitalsupport.exeptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;


@Provider
public class GenericExceptionMapper implements ExceptionMapper<Exception> {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Response toResponse(Exception e) {
        e.printStackTrace();

        ObjectNode json = mapper.createObjectNode();
        json.put("errorMessage", e.getMessage());

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(json.toPrettyString())
                .build();
    }
}