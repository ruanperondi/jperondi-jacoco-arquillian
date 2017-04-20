package br.com.jperondi.artifact.arc.util.query;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import br.com.jperondi.artifact.arc.base.filter.GenericFilter;
import br.com.jperondi.artifact.arc.base.validate.BusinessException;
import br.com.jperondi.artifact.arc.util.query.RepresentColumn;
import br.com.jperondi.artifact.arc.util.text.ReflectionUtils;

/**
 * Classe responsável por gerar JPQL de uma coluna anotada com @RepresentColumn
 *
 * @author Juan Perondi
 */
public class RepresentColumnJPQL extends RepresentColumnGenerator {

  /**
   * Constroi uma clausula JPQL
   * 
   * @param clazz Classe
   * @param filter Filter
   * @return Clausula gerada
   * @throws BusinessException
   */
  public static <T extends GenericFilter> String jpqlClauseBuild(Class<T> clazz, T filter) throws BusinessException {
    try {
      Field[] fieldsAnnotated = ReflectionUtils.getAnnotatedDeclaredFields(clazz, RepresentColumn.class, true);

      List<String> clauses = new ArrayList<>();

      for (int i = 0; i < fieldsAnnotated.length; i++) {
        Field fieldAnnotated = fieldsAnnotated[i];
        fieldAnnotated.setAccessible(true);

        if (!hasValue(fieldAnnotated, filter)) {
          continue;
        }

        clauses.add(generateClause(fieldAnnotated, filter));
      }

      return StringUtils.join(clauses, " AND ");
    } catch (IllegalArgumentException | IllegalAccessException e) {
      throw new BusinessException("erro ao tentar ler as informa��es da entidade de filtro" + clazz);
    }
  }

  /**
   * Metodo responsável por gerar uma clausula JPQL de Filter
   * 
   * @param fieldAnnotated Campo anotado
   * @param filter Filter que queremos trabalhar
   * @return Valor gerado
   * @throws IllegalArgumentException Exce��o ao tentar acessar o argumento
   * @throws IllegalAccessException Exce��o ao tentar acessar o argumento
   */
  private static <T extends GenericFilter> String generateClause(Field fieldAnnotated, T filter) throws IllegalArgumentException, IllegalAccessException {
    Object value = fieldAnnotated.get(filter);

    if (value == null) {
      return "";
    }

    RepresentColumn annotation = fieldAnnotated.getAnnotation(RepresentColumn.class);
    if (!StringUtils.isEmpty(annotation.value())) {
      return annotation.value() + "=:" + fieldAnnotated.getName();
    }

    if (fieldAnnotated.getGenericType() != String.class) {
      return fieldAnnotated.getName() + "=:" + fieldAnnotated.getName();
    }

    if (StringUtils.isEmpty(value.toString())) {
      return "";
    }

    return "upper(" + fieldAnnotated.getName() + ") like :" + fieldAnnotated.getName();
  }

}
