package br.com.vitalsupport.controllers;

import br.com.vitalsupport.services.HistoricalReportService;
import br.com.vitalsupport.dtos.HistoricalReportRequestDto;
import br.com.vitalsupport.models.HistoricalReport;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;

import java.util.List;

@Path("/historical-report")
public class HistoricalReportController {
    private final HistoricalReportService historicalReportService;

    public HistoricalReportController(HistoricalReportService historicalReportService) {
        this.historicalReportService = historicalReportService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(@Valid HistoricalReportRequestDto historicalReportRequestDto) {
        try {
            historicalReportService.create(new HistoricalReport(historicalReportRequestDto));
            return Response.status(Response.Status.CREATED).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllReports(@QueryParam("page") int page, @QueryParam("pageSize") int pageSize) {
        try {
            List<HistoricalReport> reports = historicalReportService.getAllHistoricalReports(page, pageSize);
            return Response.ok(reports).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findOneById(@PathParam("id") long id) {
        try {
            HistoricalReport report = historicalReportService.getById(id);

            if (report == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            return Response.ok(report).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PUT()
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateReport(@PathParam("id") long id, @Valid HistoricalReportRequestDto historicalReportRequestDto) {
        try {
            historicalReportService.update(id, new HistoricalReport(historicalReportRequestDto));
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteReport(@PathParam("id") long id) {
        try {
            HistoricalReport report = historicalReportService.getById(id);

            if (report == null) {
                return Response.status(Response.Status.NOT_FOUND).build();
            }

            historicalReportService.delete(report);
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
        }
    }
}
