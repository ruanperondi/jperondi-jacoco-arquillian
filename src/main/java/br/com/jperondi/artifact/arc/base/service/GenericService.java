package br.com.jperondi.artifact.arc.base.service;

import java.io.Serializable;

import br.com.jperondi.artifact.arc.base.dto.GenericDTO;
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
 * @param <DTO> DTO para fazer a transferencia das informações so serviçoi para a tela
 * @param <F> Filtro
 */
public interface GenericService<PK extends Serializable, T extends GenericPersistenceClass<PK>, DTO extends GenericDTO<PK, T>, F extends GenericFilter> extends Serializable {

  /**
   * Metodo responsável por filtrar
   * 
   * @param filter Filter que queremos realizar
   * @return lista dos objetos encontrados
   */
  FilterResponse filter(F filter) throws BusinessException;

  /**
   * Retorna uma instancia com o ID passado por par�metro
   * 
   * @param id Id que queremos trabalhar
   * @return DTO com os valores encontrados
   */
  DTO get(PK id) throws BusinessException;

  /**
   * Salva um objeto
   * 
   * @param dto DTO para salvarmos
   * @return Retorna o DTO salvo
   */
  DTO save(DTO dto) throws BusinessException;

  /**
   * Realiza o Update em um DTO
   * 
   * @param dto DTO que queremos trabalhar
   * @return DTO atualizado
   */
  DTO update(DTO dto) throws BusinessException;
}
