package br.com.jperondi.artifact.arc.base.validate;

import java.io.Serializable;

import javax.validation.Valid;

import br.com.jperondi.artifact.arc.base.persistence.GenericPersistenceClass;


public interface GenericEntityValidation<PK extends Serializable, T extends GenericPersistenceClass<PK>> {

  /**
   * Metodo responsável por validar constraints da entidade
   * 
   * @param object Valida as Uniques Constraints das Entidades
   */
  void validatePersist(@Valid T object) throws BusinessException;

}
