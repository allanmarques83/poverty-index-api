package com.bb.domain.integration.worldbank;

import java.io.ByteArrayInputStream;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.ext.ResponseExceptionMapper;

import io.quarkus.arc.Priority;

@Priority(1)
public class WorldBankException implements ResponseExceptionMapper<RuntimeException> {
    @Override
    public RuntimeException toThrowable(Response response) {
        return new WebApplicationException(
            String.format(
                "RestClient error! statusCode(%d), body(%s)", response.getStatus(), getBody(response)
            )
        );
    }

    private String getBody(Response response) {
        ByteArrayInputStream is = (ByteArrayInputStream) response.getEntity();
        byte[] bytes = new byte[is.available()];
        is.read(bytes,0,is.available());
        return new String(bytes);
      }
}
