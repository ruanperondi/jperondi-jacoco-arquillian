package br.com.jperondi.artifact.arc.features.classe_processual.dto;

import br.com.jperondi.artifact.arc.base.dto.GenericDTO;
import br.com.jperondi.artifact.arc.features.classe_processual.entity.ClasseProcessual;

/**
 * Classe respons�vel por represntar um DTO da entidade de Classe Processual para trabalhar com o
 * Front e o Back
 *
 * @author Juan Perondi
 */
public final class ClasseProcessualDTO extends GenericDTO<Integer, ClasseProcessual> {

  private final String nome;

  /**
   * Construtor da classe
   * 
   * @param id
   * @param nome
   */
  public ClasseProcessualDTO(Integer id, String nome) {
    super(id);
    this.nome = nome;
  }

  /**
   * Consturtor defualt da classe
   */
  public ClasseProcessualDTO() {
    super(null);
    nome = null;
  }

  /**
   * Consturtor defualt da classe
   */
  public ClasseProcessualDTO(String nome) {
    super(null);
    this.nome = nome;
  }


  /**
   * Retorna o campo nome
   *
   * @return Retorna o campo nome
   */
  public final String getNome() {
    return nome;
  }

}
