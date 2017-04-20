package br.com.jperondi.artifact.arc.util.query;

import java.lang.reflect.Field;

import javax.persistence.Query;

import org.apache.commons.lang.StringUtils;

import br.com.jperondi.artifact.arc.base.filter.GenericFilter;
import br.com.jperondi.artifact.arc.base.validate.BusinessException;
import br.com.jperondi.artifact.arc.util.text.ReflectionUtils;

/**
 * Classe responsável por setar o valor na query
 *
 * @author Juan Perondi
 */
public class RepresentColumnSetter extends RepresentColumnJPQL {

  /**
   * Metodo responsável por setar valor na clausula
   * 
   * @param clazz Classe que queremos trabalhar
   * @param typedQuery Query para setarmos
   * @param filter Filter
   * @throws BusinessException
   */
  public static <T extends GenericFilter> void setClauses(Class<T> clazz, Query typedQuery, T filter) throws BusinessException {
    try {
      Field[] fieldsAnnotated = ReflectionUtils.getAnnotatedDeclaredFields(clazz, RepresentColumn.class, true);

      for (int i = 0; i < fieldsAnnotated.length; i++) {
        Field fieldAnnotated = fieldsAnnotated[i];
        fieldAnnotated.setAccessible(true);

        if (!hasValue(fieldAnnotated, filter)) {
          continue;
        }

        setClause(fieldAnnotated, filter, typedQuery);
      }
    } catch (IllegalArgumentException | IllegalAccessException e) {
      throw new BusinessException("erro ao tentar ler as informações da entidade de filtro" + clazz);
    }
  }

  /**
   * Metodo responsável por setar as informações na query
   * 
   * @param fieldAnnotated Campo que queremos etar
   * @param filter Filter para setar
   * @param query Query que será setada
   * @throws IllegalArgumentException Exceção faltando valor
   * @throws IllegalAccessException exceção ao tentar acessar o valor
   */
  private static <T extends GenericFilter> void setClause(Field fieldAnnotated, T filter, Query query) throws IllegalArgumentException, IllegalAccessException {
    Object value = fieldAnnotated.get(filter);

    if (fieldAnnotated.getGenericType() != String.class) {
      query.setParameter(fieldAnnotated.getName(), value);
    } else {
      query.setParameter(fieldAnnotated.getName(), StringUtils.upperCase("%" + value + "%"));
    }
  }
}
