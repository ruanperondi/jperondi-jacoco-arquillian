package br.com.jperondi.artifact.arc.features.classe_processual.def;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;

import br.com.jperondi.artifact.arc.base.repository.EntityManagerRepository;
import br.com.jperondi.artifact.arc.features.classe_processual.ClasseProcessualFilter;
import br.com.jperondi.artifact.arc.features.classe_processual.ClasseProcessualRepository;
import br.com.jperondi.artifact.arc.features.classe_processual.entity.ClasseProcessual;

/**
 * Classe responsável por realizar o que a classe EntityManager Repository exige
 *
 * @author Juan Perondi
 */
@Default
@Dependent
class DefaultClasseRepositoryEntityManager extends EntityManagerRepository<Integer, ClasseProcessual, ClasseProcessualFilter> implements ClasseProcessualRepository {

  private static final List<String> COLUNAS_DEFAULT = Arrays.asList("id", "nome");

  /**
   * Construtor da Classe
   */
  DefaultClasseRepositoryEntityManager() {
    super(ClasseProcessual.class, ClasseProcessualFilter.class);
  }

  /**
   * Retorna o JPQL default para utilizar na query
   * 
   * @return
   */
  @Override
  protected List<String> getDefaultJPQL() {
    return DefaultClasseRepositoryEntityManager.COLUNAS_DEFAULT;
  }
}
