package br.com.jperondi.artifact.arc.base.validate;

import java.io.Serializable;
import java.lang.reflect.Field;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import br.com.jperondi.artifact.arc.base.config.Configuration;
import br.com.jperondi.artifact.arc.base.persistence.GenericPersistenceClass;
import br.com.jperondi.artifact.arc.util.text.ReflectionUtils;

public abstract class GenericJPAEntityValidation<C extends Number, PK extends Serializable, T extends GenericPersistenceClass<PK>> extends GenericEntityValidationImpl<PK, T> {

  @PersistenceContext(unitName = Configuration.UNIT_NAME)
  protected EntityManager manager;

  private final Class<T> clazz;

  private final Class<C> numberCountClass;

  public GenericJPAEntityValidation(final Class<T> clazz, final Class<C> numberCountClass) {
    super();
    this.clazz = clazz;
    this.numberCountClass = numberCountClass;
  }

  @Override
  protected void validateUniqueConstraints(final T object) throws BusinessException {
    validarUnicidadeCampos(object);
  }

  /**
   * Valida a unicidade dos campos em uma entidade
   * 
   * @param object Valida unicidade dos campos em entidade
   * @throws BusinessException
   */
  private void validarUnicidadeCampos(final T object) throws BusinessException {
    final Field[] fieldsAnotadosColumn = ReflectionUtils.getAnnotatedDeclaredFields(clazz, Column.class, true);

    for (final Field field : fieldsAnotadosColumn) {
      final Column coluna = field.getAnnotation(Column.class);

      if (!coluna.unique()) {
        return;
      }

      validarUnicidadeCampos(object, coluna, field);
    }
  }

  /**
   * Valida a unicidade do campo
   * 
   * @param object Objeto que queremos validar
   * @param coluna Coluna que queremos validar
   * @param field Field que queremos validar
   * @throws BusinessException
   */
  private void validarUnicidadeCampos(final T object, final Column coluna, final Field field) throws BusinessException {
    try {
      field.setAccessible(true);
      if (field.get(object) == null) {
        return;
      }

      final String columnName = field.getName();

      final Field fieldId = getIdField();
      fieldId.setAccessible(true);

      final StringBuilder builder = new StringBuilder("SELECT count(1) FROM " + clazz.getSimpleName());
      builder.append(" WHERE ");

      if (field.getType().equals(String.class)) {
        builder.append("upper(" + columnName + ")=? ");
      } else {
        builder.append(columnName + "=? ");
      }

      if (fieldId.get(object) != null) {
        builder.append(" AND " + fieldId.getName() + "<>?");
      }

      int index = 1;
      final TypedQuery<C> queryCount = manager.createQuery(builder.toString(), numberCountClass);

      if (field.getType().equals(String.class)) {
        queryCount.setParameter(index++, StringUtils.upperCase(field.get(object).toString()));
      } else {
        queryCount.setParameter(index++, field.get(object));
      }

      if (fieldId.get(object) != null) {
        queryCount.setParameter(index++, fieldId.get(object));
      }

      final C quantity = queryCount.getSingleResult();
      if (quantity.longValue() > 0) {
        throw new BusinessException(StringUtils.join(new String[] {clazz.getSimpleName(), field.getName(), "duplicado"}, "."));
      }
    } catch (IllegalArgumentException | IllegalAccessException e) {
      e.printStackTrace();
    }
  }

  /**
   * Metodo responsável por retornar o Field de ID
   * 
   * @return Field de ID
   */
  private Field getIdField() {
    Field[] idField = ReflectionUtils.getAnnotatedDeclaredFields(clazz, Id.class, true);

    if (!ArrayUtils.isEmpty(idField)) {
      return idField[0];
    }

    idField = ReflectionUtils.getAnnotatedDeclaredFields(clazz, Embeddable.class, true);

    if (!ArrayUtils.isEmpty(idField)) {
      return idField[0];
    }

    throw new IllegalArgumentException("Nenhum Field encontrado com as anota��es Id e Embendable");
  }
}
