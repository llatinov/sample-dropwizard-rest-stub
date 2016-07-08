package com.automationrhapsody.reststub.filters;

import com.automationrhapsody.reststub.persistence.AuthDB;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

@Authenticator
public class AuthenticateFilter implements ContainerRequestFilter {

    private static final String PARAM_API_KEY = "apiKey";
    private static final String PARAM_TOKEN = "token";
    private static final long SECONDS_IN_MILLISECOND = 1000L;
    private static final int TTL_SECONDS = 60;

    @Override
    public void filter(ContainerRequestContext context) throws IOException {
        final String apiKey = extractParam(context, PARAM_API_KEY);
        if (StringUtils.isEmpty(apiKey)) {
            context.abortWith(responseMissingParameter(PARAM_API_KEY));
        }

        final String token = extractParam(context, PARAM_TOKEN);
        if (StringUtils.isEmpty(token)) {
            context.abortWith(responseMissingParameter(PARAM_TOKEN));
        }

        if (!authenticate(apiKey, token)) {
            context.abortWith(responseUnauthorized());
        }
    }

    private String extractParam(ContainerRequestContext context, String param) {
        final UriInfo uriInfo = context.getUriInfo();
        final List user = uriInfo.getQueryParameters().get(param);
        return CollectionUtils.isEmpty(user) ? null : String.valueOf(user.get(0));
    }

    private Response responseMissingParameter(String name) {
        return Response.status(Response.Status.BAD_REQUEST)
            .type(MediaType.TEXT_PLAIN_TYPE)
            .entity("Parameter '" + name + "' is required.")
            .build();
    }

    private Response responseUnauthorized() {
        return Response.status(Response.Status.UNAUTHORIZED)
            .type(MediaType.TEXT_PLAIN_TYPE)
            .entity("Unauthorized")
            .build();
    }

    private boolean authenticate(String apiKey, String token) {
        final String secretKey = AuthDB.getSecretKey(apiKey);

        // No need to calculate digest in case of wrong apiKey
        if (StringUtils.isEmpty(secretKey)) {
            return false;
        }

        final long nowSec = System.currentTimeMillis() / SECONDS_IN_MILLISECOND;
        long startTime = nowSec - TTL_SECONDS;
        long endTime = nowSec + TTL_SECONDS;
        for (; startTime < endTime; startTime++) {
            final String toHash = apiKey + secretKey + startTime;
            final String sha1 = DigestUtils.sha256Hex(toHash);
            if (sha1.equals(token)) {
                return true;
            }
        }

        return false;
    }
}
