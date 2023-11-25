package br.com.vitalsupport.controllers;

import br.com.vitalsupport.services.AddressService;
import br.com.vitalsupport.dtos.AddressRequestDto;
import br.com.vitalsupport.models.Address;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;

import java.util.List;

@Path("/address")
public class AddressController {
    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid AddressRequestDto addressRequestDto) {
        try {
            addressService.create(new Address(addressRequestDto));
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAddresses(@QueryParam("page") int page, @QueryParam("pageSize") int pageSize) {
        try {
            List<Address> addresses = addressService.getAllAddresses(page, pageSize);
            return Response.ok(addresses).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findOneById(@PathParam("id") long id) {
        try {
            Address address = addressService.getById(id);

            if (address == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            return Response.ok(address).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT()
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAddress(@PathParam("id") long id, @Valid AddressRequestDto addressRequestDto) {
        try {
            addressService.update(id, new Address(addressRequestDto));
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAddress(@PathParam("id") long id) {
        try {
            Address address = addressService.getById(id);

            if (address == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            addressService.delete(address);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
