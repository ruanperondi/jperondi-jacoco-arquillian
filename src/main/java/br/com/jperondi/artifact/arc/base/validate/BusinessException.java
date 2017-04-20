package br.com.jperondi.artifact.arc.base.validate;

/**
 * Classe responsável por considerar exce��es relacioadas a regra de neg�cio
 *
 * @author Juan Perondi
 */
public class BusinessException extends Exception {

  private static final long serialVersionUID = -963234046829903256L;

  public BusinessException(String message) {
    super(message);
  }

  public String formatError() {
    return this.getMessage();
  }

}
