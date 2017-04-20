package br.com.jperondi.artifact.arc.features.classe_processual.def;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;

import br.com.jperondi.artifact.arc.base.validate.BusinessException;
import br.com.jperondi.artifact.arc.base.validate.GenericJPAEntityValidation;
import br.com.jperondi.artifact.arc.features.classe_processual.ClasseProcessualValidacao;
import br.com.jperondi.artifact.arc.features.classe_processual.entity.ClasseProcessual;

/**
 * Classe responsável pela validação padrão da entidade classe processual
 *
 * @author Juan Perondi
 */
@Default
@Dependent
public class DefaultClasseProcessualValidacao extends GenericJPAEntityValidation<Long, Integer, ClasseProcessual> implements ClasseProcessualValidacao {

  /**
   * Construtor da Classe
   */
  public DefaultClasseProcessualValidacao() {
    super(ClasseProcessual.class, Long.class);
  }

  @Override
  protected void validateDeactivate(final ClasseProcessual object) throws BusinessException {}

  @Override
  protected void validateActivate(final ClasseProcessual object) throws BusinessException {}

  @Override
  protected void validateBusinessInsert(final ClasseProcessual object) throws BusinessException {}

  @Override
  protected void validateBusinessUpdate(final ClasseProcessual object) throws BusinessException {}

}
