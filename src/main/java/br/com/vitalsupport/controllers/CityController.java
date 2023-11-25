package br.com.vitalsupport.controllers;

import br.com.vitalsupport.services.CityService;
import br.com.vitalsupport.dtos.CityRequestDto;
import br.com.vitalsupport.models.City;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/city")
public class CityController {
    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid CityRequestDto cityRequestDto) {
        try {
            cityService.create(new City(cityRequestDto));
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCities(@QueryParam("page") int page, @QueryParam("pageSize") int pageSize) {
        try {
            List<City> cities = cityService.getAllCities(page, pageSize);
            return Response.ok(cities).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findOneById(@PathParam("id") Long id) {
        try {
            City city = cityService.getById(id);

            if (city == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            return Response.ok(city).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT()
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateCity(@PathParam("id") long id, @Valid CityRequestDto cityRequestDto) {
        try {
            cityService.update(id, new City(cityRequestDto));
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCity(@PathParam("id") long id) {
        try {
            City city = cityService.getById(id);

            if (city == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            cityService.delete(city);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
