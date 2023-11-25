package br.com.vitalsupport.controllers;

import br.com.vitalsupport.services.ClinicService;
import br.com.vitalsupport.dtos.ClinicRequestDto;
import br.com.vitalsupport.models.Clinic;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;

import java.util.List;

@Path("/clinic")
public class ClinicController {
    private final ClinicService clinicService;

    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid ClinicRequestDto clinicRequestDto) {
        try {
            clinicService.create(new Clinic(clinicRequestDto));
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllClinics(@QueryParam("page") int page, @QueryParam("pageSize") int pageSize) {
        try {
            List<Clinic> clinics = clinicService.getAllClinics(page, pageSize);
            return Response.ok(clinics).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findOneById(@PathParam("id") long id) {
        try {
            Clinic clinic = clinicService.getById(id);

            if (clinic == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            return Response.ok(clinic).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT()
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateClinic(@PathParam("id") long id, @Valid ClinicRequestDto clinicRequestDto) {
        try {
            clinicService.update(id, new Clinic(clinicRequestDto));
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteClinic(@PathParam("id") long id) {
        try {
            Clinic clinic = clinicService.getById(id);

            if (clinic == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            clinicService.delete(clinic);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
