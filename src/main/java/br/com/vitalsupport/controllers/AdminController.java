package br.com.vitalsupport.controllers;

import br.com.vitalsupport.services.AdminService;
import br.com.vitalsupport.dtos.AdminRequestDto;
import br.com.vitalsupport.models.Admin;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;

import java.util.List;

@Path("/admin")
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid AdminRequestDto adminRequestDto) {
        try {
            adminService.create(new Admin(adminRequestDto));
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAdmins(@QueryParam("page") int page, @QueryParam("pageSize") int pageSize) {
        try {
            List<Admin> admins = adminService.getAllAdmins(page, pageSize);
            return Response.ok(admins).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findOneById(@PathParam("id") long id) {
        try {
            Admin admin = adminService.getById(id);

            if (admin == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            return Response.ok(admin).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT()
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAdmin(@PathParam("id") long id, @Valid AdminRequestDto adminRequestDto) {
        try {
            adminService.update(id, new Admin(adminRequestDto));
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAdmin(@PathParam("id") long id) {
        try {
            Admin admin = adminService.getById(id);

            if (admin == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            adminService.delete(admin);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
