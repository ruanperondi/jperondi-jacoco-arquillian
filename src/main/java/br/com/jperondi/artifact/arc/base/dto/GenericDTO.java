package br.com.jperondi.artifact.arc.base.dto;

import java.io.Serializable;

import br.com.jperondi.artifact.arc.base.persistence.GenericPersistenceClass;

/**
 * Classe responsável por definir um contrato de DTO entre o serviçoo e o Business
 *
 * @author Juan Perondi
 *
 * @param <PK> Tipo da PK
 * @param <T> Tipo da classe persistente
 */
public abstract class GenericDTO<PK extends Serializable, T extends GenericPersistenceClass<PK>> {

  private final PK id;

  /**
   * Construtor base
   * 
   * @param id
   */
  public GenericDTO(PK id) {
    super();
    this.id = id;
  }

  /**
   * M�todo responsável por retornar o campo id
   *
   * @return retorna o campo id
   */
  public PK getId() {
    return id;
  }
}
