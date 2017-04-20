package br.com.jperondi.artifact.arc.base.service;

import java.util.List;

import br.com.jperondi.artifact.arc.base.dto.GenericDTO;
import br.com.jperondi.artifact.arc.base.filter.GenericFilter;

/**
 * Classe responsável por armazenar informa��es de response ao usu�rio
 *
 * @author Juan Perondi
 */
public class FilterResponse {

  private final GenericFilter filter;

  private final List<? extends GenericDTO<?, ?>> results;

  /**
   * Construtor da Classe
   * 
   * @param filter
   * @param results
   */
  public FilterResponse(GenericFilter filter, List<? extends GenericDTO<?, ?>> results) {
    super();
    this.filter = filter;
    this.results = results;
  }

  /**
   * M�todo responsável por retornar o campo filter
   *
   * @return retorna o campo filter
   */
  public GenericFilter getFilter() {
    return filter;
  }

  /**
   * M�todo responsável por retornar o campo results
   *
   * @return retorna o campo results
   */
  public List<? extends GenericDTO<?, ?>> getResults() {
    return results;
  }

}
