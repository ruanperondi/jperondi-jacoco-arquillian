package br.com.jperondi.artifact.arc.features.classe_processual;

import javax.ws.rs.QueryParam;

import br.com.jperondi.artifact.arc.base.filter.GenericFilter;

/**
 * Classe respons�vel por definir o que ser� utilizado como filtro para a Classe Processual
 *
 * @author Juan Perondi
 */
public class ClasseProcessualFilter extends GenericFilter {

  @QueryParam("nome")
  private String nome;

  /**
   * Retorna o campo nome
   *
   * @return Retorna o campo nome
   */
  public final String getNome() {
    return nome;
  }

  /**
   * Seta o parametro nome para o campo nome
   *
   * @param nome Parametro para setar no valor nome
   */
  public final void setNome(final String nome) {
    this.nome = nome;
  }

}
