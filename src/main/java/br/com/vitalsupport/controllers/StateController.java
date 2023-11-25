package br.com.vitalsupport.controllers;

import br.com.vitalsupport.services.StateService;
import br.com.vitalsupport.dtos.StateRequestDto;
import br.com.vitalsupport.models.State;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;

import java.util.List;

@Path("/state")
public class StateController {
    private final StateService stateService;

    public StateController(StateService stateService) {
        this.stateService = stateService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid StateRequestDto stateRequestDto) {
        try {
            stateService.create(new State(stateRequestDto));
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStates(@QueryParam("page") int page, @QueryParam("pageSize") int pageSize) {
        try {
            List<State> states = stateService.getAllStates(page, pageSize);
            return Response.ok(states).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findOneById(@PathParam("id") int id) {
        try {
            State state = stateService.getById(id);

            if (state == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            return Response.ok(state).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT()
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateState(@PathParam("id") int id, @Valid StateRequestDto stateRequestDto) {
        try {
            stateService.update(id, new State(stateRequestDto));
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteState(@PathParam("id") int id) {
        try {
            State state = stateService.getById(id);

            if (state == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            stateService.delete(state);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
