package br.com.jperondi.artifact.arc.base.repository;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;
import javax.validation.Valid;

import org.apache.commons.lang.StringUtils;
import org.hibernate.Session;

import br.com.jperondi.artifact.arc.base.config.Configuration;
import br.com.jperondi.artifact.arc.base.filter.GenericFilter;
import br.com.jperondi.artifact.arc.base.persistence.GenericPersistenceClass;
import br.com.jperondi.artifact.arc.base.validate.BusinessException;
import br.com.jperondi.artifact.arc.util.jpa.JPAUtils;
import br.com.jperondi.artifact.arc.util.query.RepresentColumnJPQL;
import br.com.jperondi.artifact.arc.util.query.RepresentColumnSetter;

/**
 * Classe responsável por utilizar o contrato do reposit�rio pelo EntityManager
 *
 * @author Juan Perondi
 */
public abstract class EntityManagerRepository<PK extends Serializable, T extends GenericPersistenceClass<PK>, F extends GenericFilter> implements GenericRepository<PK, T, F> {

  @PersistenceContext(unitName = Configuration.UNIT_NAME)
  protected EntityManager entityManager;

  private Class<T> clazz;

  private Class<F> filterClass;

  private String columns;

  private boolean isTipada;

  /**
   * Construtor da Classe
   * 
   * @param clazz Classe para utilizarmos como base
   */
  public EntityManagerRepository(Class<T> clazz, Class<F> filterClass) {
    super();
    this.clazz = clazz;
    this.filterClass = filterClass;
  }

  /**
   * Metodo responsável por realizar uma pesquisa e retornar a quatidade de registros encontrados
   * 
   * @param filter Filtro que iremos utilizar
   * @return Quantidade de registros encontrados
   * @throws BusinessException
   */
  public Long count(F filter) throws BusinessException {
    TypedQuery<Long> typedQuery = entityManager.createQuery(generateJPQLCount(filter), Long.class);

    setParameters(typedQuery, filter);

    return typedQuery.getSingleResult();
  }

  @SuppressWarnings("unchecked")
  @Override
  @Transactional(value = TxType.REQUIRED)
  public List<T> filter(F filter) throws BusinessException {
    Query typedQuery;

    if (this.isTipada) {
      typedQuery = entityManager.createQuery(generateJPQLFilter(filter), clazz);
    } else {
      typedQuery = entityManager.createQuery(generateJPQLFilter(filter));
    }

    setParameters(typedQuery, filter);

    typedQuery.setMaxResults(filter.getLimit());
    typedQuery.setFirstResult(filter.getOffSet());

    if (isTipada) {
      return typedQuery.getResultList();
    }

    return JPAUtils.convertToClass(clazz, typedQuery.getResultList(), this.columns);
  }

  @Override
  @Transactional(value = TxType.REQUIRED)
  public T get(PK id) {
    return entityManager.find(clazz, id);
  }

  @Override
  @Transactional(value = TxType.REQUIRED)
  public T save(@Valid T entity) {
    entityManager.persist(entity);

    return entity;
  }

  @Override
  @Transactional(value = TxType.REQUIRED)
  public T edit(T entity) {
    Session session = entityManager.unwrap(Session.class);
    session.update(entity);

    return entity;
  }

  /**
   * Metodo responsável por gerar um JPQL para utilizarmos no FIltro
   * 
   * @param filter Filtro que queremos utilizar
   * @return String com a JPQL gerada
   * @throws BusinessException
   */
  public String generateJPQLFilter(F filter) throws BusinessException {
    return buildJPQL(filter, getDefaultJPQL(), false);
  }

  /**
   * Constroi o JPQL da Categoria. <br>
   * Se os campo fields n�o for passado, ent�o ser� considerado o campo whenEmpty
   * 
   * @param fields Campos
   * @param list Campo utilizado quando construirmos o JPQL
   * @throws BusinessException
   */
  protected String buildJPQL(F filter, List<String> list, boolean countMode) throws BusinessException {
    StringBuilder builder = new StringBuilder();
    if (!isTipada || countMode) {
      builder.append(" SELECT ");
      this.columns = getFields(filter, list, countMode);
      builder.append(columns);
    }

    builder.append(" FROM " + this.clazz.getSimpleName() + " ");

    String clause = RepresentColumnJPQL.jpqlClauseBuild(filterClass, filter);

    if (!StringUtils.isEmpty(clause)) {
      builder.append(" WHERE ");
      builder.append(clause);
    }

    return builder.toString();
  }

  /**
   * Retorna o JPQL default para consulta
   * 
   * @return JPQL padr�o para a consulta
   */
  protected abstract List<String> getDefaultJPQL();

  /**
   * Metodo responsável por gerar um JPQL para utilizarmos no FIltro
   * 
   * @param filter Filtro que queremos utilizar
   * @return String com a JPQL gerada
   * @throws BusinessException
   */
  protected String generateJPQLCount(F filter) throws BusinessException {
    return this.buildJPQL(filter, null, true);
  }

  /**
   * Metodo responsável por setar os parametros na Query
   * 
   * @param typedQuery Query Tipada
   * @param filter Filtro para preenchermos a Query
   * @throws BusinessException
   */
  protected void setParameters(Query typedQuery, F filter) throws BusinessException {
    RepresentColumnSetter.setClauses(filterClass, typedQuery, filter);
  }

  /**
   * Metodo que retorna os campos. <br>
   * se for countMode ir� retornar count(1), se os fields da Categoria forem preenchidos, ent�ao
   * este ser� passado por par�metro, sen�o ser�o aqueles definodos na variavel whenEmpty
   * 
   * @param filter Filter para utilizarmos
   * @param list Quando for vazio
   * @param countMode Modo de Contagem
   * @return Campos em forma de String
   */
  protected String getFields(F filter, List<String> list, boolean countMode) {
    if (countMode) {
      return "count(1)";
    }

    return StringUtils.join(list.toArray(), ",");
  }

  /**
   * Seta se a Query é tipada ou não
   * 
   * @param true se tipada
   */
  public void setTypedQuery(boolean isTipada) {
    this.isTipada = isTipada;
  }

}
