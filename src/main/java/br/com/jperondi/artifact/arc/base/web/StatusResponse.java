package br.com.jperondi.artifact.arc.base.web;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.ws.rs.NameBinding;

@NameBinding
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface StatusResponse {
  int OK = 200;
  int CREATED = 201;
  int NOT_MODIFIED = 304;
  int ACCEPTED = 202;
  int BAD_REQUEST = 400;

  int value() default 200;
}
