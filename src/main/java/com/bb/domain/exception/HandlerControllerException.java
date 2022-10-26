package com.bb.domain.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import com.bb.domain.exception.dto.ApiExceptionDto;

@Provider
public class HandlerControllerException implements ExceptionMapper<RuntimeException>{
    @Override
    public Response toResponse(RuntimeException e) {
        return Response.status(Response.Status.BAD_REQUEST).
               entity(new ApiExceptionDto(e.getMessage(), 400))
          .build();
    }
}
