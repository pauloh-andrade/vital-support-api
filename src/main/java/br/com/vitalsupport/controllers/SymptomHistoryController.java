package br.com.vitalsupport.controllers;

import br.com.vitalsupport.services.SymptomHistoryService;
import br.com.vitalsupport.dtos.SymptomHistoryRequestDto;
import br.com.vitalsupport.models.SymptomHistory;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;

import java.util.List;

@Path("/symptom-history")
public class SymptomHistoryController {
    private final SymptomHistoryService symptomHistoryService;

    public SymptomHistoryController(SymptomHistoryService symptomHistoryService) {
        this.symptomHistoryService = symptomHistoryService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid SymptomHistoryRequestDto symptomHistoryRequestDto) {
        try {
            symptomHistoryService.create(new SymptomHistory(symptomHistoryRequestDto));
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllHistories(@QueryParam("page") int page, @QueryParam("pageSize") int pageSize) {
        try {
            List<SymptomHistory> histories = symptomHistoryService.getAllSymptomHistories(page, pageSize);
            return Response.ok(histories).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findOneById(@PathParam("id") long id) {
        try {
            SymptomHistory history = symptomHistoryService.getById(id);

            if (history == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            return Response.ok(history).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT()
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateHistory(@PathParam("id") long id, @Valid SymptomHistoryRequestDto symptomHistoryRequestDto) {
        try {
            symptomHistoryService.update(id, new SymptomHistory(symptomHistoryRequestDto));
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteHistory(@PathParam("id") long id) {
        try {
            SymptomHistory history = symptomHistoryService.getById(id);

            if (history == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            symptomHistoryService.delete(history);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
