package com.automationrhapsody.reststub.resources;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Properties;
import java.util.Random;

public class ProductsServlet extends HttpServlet {

    private Template search;
    private Template searchResults;
    private Template details;

    public void init() throws ServletException {
        Properties props = new Properties();
        props.setProperty("resource.loader", "class");
        props.setProperty("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
        VelocityEngine engine = new VelocityEngine(props);
        engine.init();
        search = engine.getTemplate("velocity.search.html");
        searchResults = engine.getTemplate("velocity.search-results.html");
        details = engine.getTemplate("velocity.details.html");
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String result = getContent(request);
        // Output
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println(result);
    }

    private String getContent(HttpServletRequest request) {
        String action = request.getParameter("action");
        String q = request.getParameter("q");
        String id = request.getParameter("id");
        if (StringUtils.isEmpty(action)) {
            action = "search";
        }

        switch (action) {
            case "search-results":
                return getSearchResults(q);
            case "details":
                return getProductDetails(id);
            case "search":
            default:
                return getSearchPage();
        }
    }

    public String getSearchPage() {
        VelocityContext context = new VelocityContext();
        StringWriter writer = new StringWriter();
        search.merge(context, writer);
        return writer.toString();
    }

    public String getSearchResults(String searchString) {
        if (StringUtils.isEmpty(searchString)) {
            searchString = "";
        }
        Random random = new Random();
        int count = random.nextInt(49) + 1;
        VelocityContext context = new VelocityContext();
        context.put("searchString", searchString);
        context.put("results", count);
        StringWriter writer = new StringWriter();
        searchResults.merge(context, writer);
        return writer.toString();
    }

    public String getProductDetails(String productId) {
        if (StringUtils.isEmpty(productId)) {
            productId = "";
        }
        VelocityContext context = new VelocityContext();
        context.put("productId", productId);
        StringWriter writer = new StringWriter();
        details.merge(context, writer);
        return writer.toString();
    }

    public void destroy() {
        // Do nothing
    }
}