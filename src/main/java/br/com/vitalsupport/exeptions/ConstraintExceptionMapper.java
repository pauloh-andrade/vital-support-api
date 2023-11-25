package br.com.vitalsupport.exeptions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

import java.util.List;

@Provider
public class ConstraintExceptionMapper implements ExceptionMapper<ConstraintViolationException> {
    private static final ObjectMapper mapper = new ObjectMapper();

    @Override
    public Response toResponse(ConstraintViolationException e) {
        e.printStackTrace();

        List<String> errorMessages = e.getConstraintViolations()
                .stream()
                .map(this::getErrorMessage)
                .toList();

        ArrayNode errorArrayNode = mapper.valueToTree(errorMessages);

        ObjectNode json = mapper.createObjectNode();
        json.set("errors", errorArrayNode);

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(json.toPrettyString())
                .build();
    }

    private String getErrorMessage(ConstraintViolation<?> violation) {
        return String.format("%s %s. InvalidValue: %s",
                violation.getPropertyPath(),
                violation.getMessage(),
                violation.getInvalidValue());
    }
}



