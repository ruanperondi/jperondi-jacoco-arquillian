package br.com.jperondi.artifact.arc.util.text;

/**
 * Classe responsável por ter formatações de naming
 *
 * @author Juan Perondi
 */
public final class NamingFormat {

  /**
   * Metodo responsável por converter algo de camel case para underscore
   * 
   * @param value Valor que queremos converter
   * @return o que era camel case para score
   */
  public static String toUnderscore(String value) {
    String regex = "([a-z])([A-Z]+)";
    String replacement = "$1_$2";

    return value.replaceAll(regex, replacement).toLowerCase();
  }

}
