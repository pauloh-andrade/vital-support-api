package br.com.vitalsupport.controllers;

import br.com.vitalsupport.services.SymptomService;
import br.com.vitalsupport.dtos.SymptomRequestDto;
import br.com.vitalsupport.models.Symptom;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;

import java.util.List;

@Path("/symptom")
public class SymptomController {
    private final SymptomService symptomService;

    public SymptomController(SymptomService symptomService) {
        this.symptomService = symptomService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid SymptomRequestDto symptomRequestDto) {
        try {
            symptomService.create(new Symptom(symptomRequestDto));
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllSymptoms(@QueryParam("page") int page, @QueryParam("pageSize") int pageSize) {
        try {
            List<Symptom> symptoms = symptomService.getAllSymptoms(page, pageSize);
            return Response.ok(symptoms).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findOneById(@PathParam("id") long id) {
        try {
            Symptom symptom = symptomService.getById(id);

            if (symptom == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            return Response.ok(symptom).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT()
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSymptom(@PathParam("id") long id, @Valid SymptomRequestDto symptomRequestDto) {
        try {
            symptomService.update(id, new Symptom(symptomRequestDto));
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteSymptom(@PathParam("id") long id) {
        try {
            Symptom symptom = symptomService.getById(id);

            if (symptom == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            symptomService.delete(symptom);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
