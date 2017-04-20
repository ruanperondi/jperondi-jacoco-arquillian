package br.com.jperondi.artifact.arc.base.web;

import java.io.IOException;
import java.lang.annotation.Annotation;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class StatusResponseFilter implements ContainerResponseFilter {

  @Override
  public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
    if (containerResponseContext.getStatus() == 200) {
      for (Annotation annotation : containerResponseContext.getEntityAnnotations()) {
        if (annotation instanceof StatusResponse) {
          containerResponseContext.setStatus(((StatusResponse) annotation).value());
          break;
        }
      }
    }
  }
}
