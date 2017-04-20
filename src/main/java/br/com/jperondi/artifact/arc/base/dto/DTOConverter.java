package br.com.jperondi.artifact.arc.base.dto;

import java.io.Serializable;

import br.com.jperondi.artifact.arc.base.persistence.GenericPersistenceClass;

/**
 * Classe responsável por realizar a convers�o de um DTO para seu tipo persistente
 *
 * @author Juan Perondi
 *
 * @param <PK> Chave primaria
 * @param <E> Entidade de Origem
 * @param <DTO> DTO de Destino
 */
public interface DTOConverter<PK extends Serializable, E extends GenericPersistenceClass<PK>, DTO extends GenericDTO<PK, E>> {

  /**
   * Converte de uma entity para o DTO
   * 
   * @param entity Entidade que queremos converter
   * @return DTO convertido
   */
  public DTO convert(E entity);

  /**
   * Converte para a entidade com base em um DTO recebido
   * 
   * @param DTO Entidade que queremos trabalhar
   * @return Entidade convertida
   */
  public E convert(DTO entity);

}
