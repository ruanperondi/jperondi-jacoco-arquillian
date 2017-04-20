package br.com.jperondi.artifact.arc.base.business;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import br.com.jperondi.artifact.arc.base.filter.GenericFilter;
import br.com.jperondi.artifact.arc.base.persistence.GenericPersistenceClass;
import br.com.jperondi.artifact.arc.base.validate.BusinessException;

/**
 * Classe responsável por ter uum serviçoo de um tipo especifico
 *
 * @author Juan Perondi
 *
 * @param <PK> Tipo da Chave Primaria
 * @param <T> Tipo referente ao banco de Dados
 * @param <F> Filtro
 */
public interface GenericBusiness<PK extends Serializable, T extends GenericPersistenceClass<PK>, F extends GenericFilter> {

  /**
   * Metodo responsável por filtrar
   * 
   * @param filter Filter que queremos realizar
   * @return lista dos objetos encontrados
   * @throws BusinessException
   */
  List<T> filter(@NotNull F filter) throws BusinessException;

  /**
   * Retorna uma instancia com o ID passado por par�metro
   * 
   * @param id Id que queremos trabalhar
   * @return T com os valores encontrados
   */
  T get(@NotNull PK id) throws BusinessException;

  /**
   * Salva um objeto
   * 
   * @param t T para salvarmos
   * @return Retorna o T salvo
   */
  T save(@NotNull T t) throws BusinessException;

  /**
   * Realiza o Update em um T
   * 
   * @param t T que queremos trabalhar
   * @return T atualizado
   */
  T update(@NotNull T t) throws BusinessException;

  /**
   * Metodo responsável por fazer a contagem de inform�oes que podem ser retornadas no filter
   * 
   * @param filter Filter que queremos mexer
   * @return Quantidade Encontrados
   * @throws BusinessException
   */
  Long count(@NotNull F filter) throws BusinessException;

}
