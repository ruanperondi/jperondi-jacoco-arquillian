package br.com.jperondi.artifact.arc.util.query;

import java.lang.reflect.Field;

import org.apache.commons.lang.StringUtils;

import br.com.jperondi.artifact.arc.base.filter.GenericFilter;

/**
 * Classe responsável por ser o pai dos generators
 *
 * @author Juan Perondi
 */
public class RepresentColumnGenerator {

  /**
   * Metodo responsável por verificar se existe um valor no objeto par ao field passado por
   * parametro
   * 
   * @param fieldAnnotated Field anotado
   * @param filter Filter
   * @return true se existir valor
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   */
  public static <T extends GenericFilter> boolean hasValue(Field fieldAnnotated, T filter) throws IllegalArgumentException, IllegalAccessException {
    Object value = fieldAnnotated.get(filter);

    if (value == null) {
      return false;
    }

    if (fieldAnnotated.getDeclaringClass() != String.class) {
      return true;
    }

    if (!StringUtils.isEmpty(value.toString())) {
      return false;
    }

    return true;
  }

}
