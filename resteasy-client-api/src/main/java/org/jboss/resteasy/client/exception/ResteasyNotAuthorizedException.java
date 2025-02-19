package org.jboss.resteasy.client.exception;

import static org.jboss.resteasy.client.exception.WebApplicationExceptionWrapper.sanitize;

import java.util.Collections;
import java.util.List;

import jakarta.ws.rs.NotAuthorizedException;
import jakarta.ws.rs.core.Response;

/**
 * Wraps a {@link NotAuthorizedException} with a {@linkplain #sanitize(Response) sanitized} response
 *
 * @author <a href="mailto:jperkins@redhat.com">James R. Perkins</a>
 */
public class ResteasyNotAuthorizedException extends NotAuthorizedException implements WebApplicationExceptionWrapper<NotAuthorizedException> {

   private static final long serialVersionUID = 7034604450379314101L;
   private final NotAuthorizedException wrapped;
   private final Response sanitizedResponse;

    ResteasyNotAuthorizedException(final NotAuthorizedException wrapped) {
        super(wrapped.getMessage(), wrapped.getResponse(), wrapped.getCause());
        this.wrapped = wrapped;
        this.sanitizedResponse = sanitize(wrapped.getResponse());
    }

    @Override
    public List<Object> getChallenges() {
        return Collections.emptyList();
    }

    @Override
    public NotAuthorizedException unwrap() {
        return wrapped;
    }

    @Override
    public Response getSanitizedResponse() {
        return sanitizedResponse;
    }
}