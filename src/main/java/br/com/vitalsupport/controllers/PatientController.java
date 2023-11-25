package br.com.vitalsupport.controllers;

import br.com.vitalsupport.services.PatientService;
import br.com.vitalsupport.dtos.PatientRequestDto;
import br.com.vitalsupport.models.Patient;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;

import java.util.List;

@Path("/patient")
public class PatientController {
    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid PatientRequestDto patientRequestDto) {
        try {
            patientService.create(new Patient(patientRequestDto));
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
            public Response getAllPatients(@QueryParam("page") int page, @QueryParam("pageSize") int pageSize) {
        try {
            List<Patient> patients = patientService.getAllPatients(page, pageSize);
            return Response.ok(patients).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findOneById(@PathParam("id") long id) {
        try {
            Patient patient = patientService.getById(id);

            if (patient == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            return Response.ok(patient).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT()
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePatient(@PathParam("id") long id, @Valid PatientRequestDto patientRequestDto) {
        try {
            patientService.update(id, new Patient(patientRequestDto));
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePatient(@PathParam("id") long id) {
        try {
            Patient patient = patientService.getById(id);

            if (patient == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            patientService.delete(patient);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}

