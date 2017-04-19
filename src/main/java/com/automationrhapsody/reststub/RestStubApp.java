package com.automationrhapsody.reststub;

import com.automationrhapsody.reststub.filters.AuthenticateFilter;
import com.automationrhapsody.reststub.resources.BookService;
import com.automationrhapsody.reststub.resources.BooksSecureService;
import com.automationrhapsody.reststub.resources.PersonService;
import com.automationrhapsody.reststub.resources.ProductsServlet;

import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class RestStubApp extends Application<RestStubConfig> {

    public static void main(String[] args) throws Exception {
        new RestStubApp().run(args);
    }

    @Override
    public void run(RestStubConfig config, Environment env) {
        final PersonService personService = new PersonService();
        env.jersey().register(personService);

        final BookService bookService = new BookService();
        env.jersey().register(bookService);

        env.jersey().register(BooksSecureService.class);
        env.jersey().register(AuthenticateFilter.class);

        env.servlets().addServlet("ProductsServlet", ProductsServlet.class).addMapping("/products");

        final RestStubCheck healthCheck = new RestStubCheck(config.getVersion());
        env.healthChecks().register("template", healthCheck);
        env.jersey().register(healthCheck);
    }

    @Override
     public void initialize(Bootstrap<RestStubConfig> bootstrap) {
        bootstrap.setConfigurationSourceProvider(new SubstitutingSourceProvider(
            bootstrap.getConfigurationSourceProvider(), new EnvironmentVariableSubstitutor(false)));
    }
}