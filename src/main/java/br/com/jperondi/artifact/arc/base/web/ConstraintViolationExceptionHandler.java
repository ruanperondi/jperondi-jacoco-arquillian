package br.com.jperondi.artifact.arc.base.web;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import br.com.jperondi.artifact.arc.util.text.JSONUtils;

@Provider
public class ConstraintViolationExceptionHandler implements ExceptionMapper<ConstraintViolationException> {

  @Override
  public javax.ws.rs.core.Response toResponse(ConstraintViolationException exception) {
    List<String> collect = exception.getConstraintViolations().stream().map(sc -> sc.getMessage()).collect(Collectors.toList());
    
    return Response.status(Status.BAD_REQUEST).entity(JSONUtils.toJSON(collect)).build();
  }

}
