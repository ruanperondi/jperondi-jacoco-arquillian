package br.com.jperondi.artifact.arc.base.repository;

import java.io.Serializable;
import java.util.List;

import javax.validation.Valid;

import br.com.jperondi.artifact.arc.base.filter.GenericFilter;
import br.com.jperondi.artifact.arc.base.persistence.GenericPersistenceClass;
import br.com.jperondi.artifact.arc.base.validate.BusinessException;

/**
 * Classe responsável por definir o contrato basico de todos as classes que tiverem acesso ao um
 * repositorio
 *
 * @author Juan Perondi
 */
public interface GenericRepository<PK extends Serializable, T extends GenericPersistenceClass<PK>, F extends GenericFilter> {

  /**
   * Metodo responsável por realizar uma pesquisa
   * 
   * @param filter Filtro que iremos utilizar
   * @return Lista dos objetos encontrados
   * @throws BusinessException
   */
  public List<T> filter(F filter) throws BusinessException;

  /**
   * Metodo responsável por realizar uma pesquisa e retornar a quatidade de registros encontrados
   * 
   * @param filter Filtro que iremos utilizar
   * @return Quantidade de registros encontrados
   * @throws BusinessException
   */
  public Long count(F filter) throws BusinessException;

  /**
   * Recupera uma informa��o do Banco de Dados
   * 
   * @param id Id que estamos procurando
   * @return entidade encontrada
   */
  public T get(PK id);

  /**
   * Salva uma informa��o do Banco de Dados
   * 
   * @param T entidade que queremos persistir
   * @return entidade encontrada
   */
  public T save(@Valid T entity);

  /**
   * Edita uma informa��o no Banco de Dados
   * 
   * @param T entidade que queremos persistir
   * @return entidade encontrada
   */
  public T edit(T entity);

}
