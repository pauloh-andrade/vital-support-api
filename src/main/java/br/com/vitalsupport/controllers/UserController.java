package br.com.vitalsupport.controllers;

import br.com.vitalsupport.dtos.LoginRequestDto;
import br.com.vitalsupport.dtos.LoginResponseDto;
import br.com.vitalsupport.services.UserService;
import br.com.vitalsupport.dtos.UserRequestDto;
import br.com.vitalsupport.models.User;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;

import java.util.List;

@Path("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @POST
    @Path("/auth")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@Valid LoginRequestDto requestDto) throws Exception {
        boolean result = userService.login(requestDto.login(), requestDto.password());

        LoginResponseDto responseDto = new LoginResponseDto(result);

        return Response.status(Response.Status.OK).entity(responseDto).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid UserRequestDto userRequestDto) {
        try {
            userService.create(new User(userRequestDto));
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllUsers(@QueryParam("page") int page, @QueryParam("pageSize") int pageSize) {
        try {
            List<User> users = userService.getAllUsers(page, pageSize);
            return Response.ok(users).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findOneById(@PathParam("id") long id)  {
        try {
            User user = userService.getById(id);

            if (user == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            return Response.ok(user).build();
        } catch (Exception e) {
            System.out.println(e);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT()
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") long id, @Valid UserRequestDto userRequestDto) {
        try {
            userService.update(id, new User(userRequestDto));
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteUser(@PathParam("id") long id) {
        try {
            User user = userService.getById(id);

            if (user == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            userService.delete(user);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
