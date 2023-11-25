package br.com.vitalsupport;

import br.com.vitalsupport.controllers.*;
import br.com.vitalsupport.models.*;
import br.com.vitalsupport.repositories.*;
import br.com.vitalsupport.services.*;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import br.com.vitalsupport.exeptions.ConstraintExceptionMapper;
import br.com.vitalsupport.exeptions.GenericExceptionMapper;
import org.hibernate.boot.registry.StandardServiceRegistry;
import br.com.vitalsupport.exeptions.SQLExceptionMapper;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.server.ResourceConfig;
import org.hibernate.boot.MetadataSources;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.net.URI;

public class Main {
    public static final String BASE_URI = "http://localhost:8080/";

    private static SessionFactory buildSessionFactory() {
        final StandardServiceRegistry registry =
                new StandardServiceRegistryBuilder()
                        .build();

        try {
            return new MetadataSources(registry)
                    .addAnnotatedClass(User.class)
                    .addAnnotatedClass(Address.class)
                    .addAnnotatedClass(Admin.class)
                    .addAnnotatedClass(City.class)
                    .addAnnotatedClass(Clinic.class)
                    .addAnnotatedClass(HistoricalReport.class)
                    .addAnnotatedClass(Patient.class)
                    .addAnnotatedClass(State.class)
                    .addAnnotatedClass(Symptom.class)
                    .addAnnotatedClass(SymptomHistory.class)
                    .buildMetadata()
                    .buildSessionFactory();
        } catch (Exception e) {
            StandardServiceRegistryBuilder.destroy(registry);
            e.printStackTrace();
            throw new RuntimeException("Erro ao configurar a SessionFactory");
        }
    }
    public static HttpServer startServer() {
        SessionFactory sessionFactory = buildSessionFactory();

        final ResourceConfig rc = new ResourceConfig()
                .packages("br.com.vitalsupport")
                .register(new CorsFilter())
                .register(ConstraintExceptionMapper.class)
                .register(SQLExceptionMapper.class)
                .register(GenericExceptionMapper.class)
                .register(new UserController(new UserService(new UserRepository(sessionFactory))))
                .register(new AddressController(new AddressService(new AddressRepository(sessionFactory))))
                .register(new AdminController(new AdminService(new AdminRepository(sessionFactory))))
                .register(new ClinicController(new ClinicService(new ClinicRepository(sessionFactory))))
                .register(new HistoricalReportController(new HistoricalReportService(new HistoricalReportRepository(sessionFactory))))
                .register(new PatientController(new PatientService(new PatientRepository(sessionFactory))))
                .register(new StateController(new StateService(new StateRepository(sessionFactory))))
                .register(new SymptomController(new SymptomService(new SymptomRepository(sessionFactory))))
                .register(new SymptomHistoryController(new SymptomHistoryService(new SymptomHistoryRepository(sessionFactory))))
                .register(new CityController(new CityService(new CityRepository(sessionFactory))));

        return GrizzlyHttpServerFactory.createHttpServer(URI.create(BASE_URI), rc);
    }

    public static void main(String[] args) throws IOException {
        final HttpServer server = startServer();

        System.out.printf("Jersey app started with endpoints available at "
                + "%s%nHit Ctrl-C to stop it...%n", BASE_URI);
        System.in.read();
        server.stop();

        buildSessionFactory().close();
    }
}

