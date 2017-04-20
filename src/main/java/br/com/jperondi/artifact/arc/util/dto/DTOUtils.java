package br.com.jperondi.artifact.arc.util.dto;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import br.com.jperondi.artifact.arc.base.dto.DTOConverter;
import br.com.jperondi.artifact.arc.base.dto.GenericDTO;
import br.com.jperondi.artifact.arc.base.persistence.GenericPersistenceClass;
import br.com.jperondi.artifact.arc.base.validate.BusinessException;
import br.com.jperondi.artifact.arc.util.text.ReflectionUtils;

/**
 * Classe responsável por
 *
 * @author Juan Perondi
 */
public final class DTOUtils {

  /**
   * Metodo responsável por converter uma lista de Entidade para DTO
   * 
   * @param converter Converter que queremos trabalhar
   * @param list Lista que ser� convertida
   * @return Lista convetida
   */
  public static <PK extends Serializable, T extends GenericPersistenceClass<PK>, E extends GenericDTO<PK, T>> List<E> convertToDTO(DTOConverter<PK, T, E> converter, List<T> list) {
    List<E> retorno = new ArrayList<>();

    for (T t : list) {
      retorno.add(converter.convert(t));
    }

    return retorno;
  }


  /**
   * Metodo responsável por converter uma lista de Entidade para DTO
   * 
   * @param converter Converter que queremos trabalhar
   * @param list Lista que ser� convertida
   * @return Lista convetida
   */
  public static <PK extends Serializable, T extends GenericPersistenceClass<PK>, E extends GenericDTO<PK, T>> Set<E> convertToDTO(DTOConverter<PK, T, E> converter, Set<T> list) {
    Set<E> retorno = new HashSet<>();

    for (T t : list) {
      retorno.add(converter.convert(t));
    }

    return retorno;
  }

  /**
   * Metodo responsável por converter uma lista de DTO para Entidade
   * 
   * @param converter Converter que queremos trabalhar
   * @param list Lista que ser� convertida
   * @return Lista convetida
   */
  public static <PK extends Serializable, T extends GenericPersistenceClass<PK>, E extends GenericDTO<PK, T>> List<T> convertToEntity(DTOConverter<PK, T, E> converter, List<E> list) {
    List<T> retorno = new ArrayList<>();

    for (E e : list) {
      retorno.add(converter.convert(e));
    }

    return retorno;
  }

  /**
   * Metodo responsável por converter uma lista de DTO para Entidade
   * 
   * @param converter Converter que queremos trabalhar
   * @param list Lista que ser� convertida
   * @return Lista convetida
   */
  public static <PK extends Serializable, T extends GenericPersistenceClass<PK>, E extends GenericDTO<PK, T>> Set<T> convertToEntity(DTOConverter<PK, T, E> converter, Set<E> list) {
    Set<T> retorno = new HashSet<>();

    for (E e : list) {
      retorno.add(converter.convert(e));
    }

    return retorno;
  }

  public static <DTO extends GenericDTO<?, ?>> void validateFiledsWithDTO(Class<DTO> dtoClass, String fieldsString) throws BusinessException {
    List<Field> fieldList = ReflectionUtils.getAllFields(new LinkedList<>(), dtoClass, true);
    String[] fields = StringUtils.split(fieldsString, ",");

    for (String string : fields) {
      List<Field> collect = fieldList.stream().filter(field -> StringUtils.equalsIgnoreCase(field.getName(), string)).collect(Collectors.toList());

      if (CollectionUtils.isEmpty(collect)) {
        throw new BusinessException("Campo não encontrado: " + string + ". Campos permitidos: " + StringUtils.join(fieldList.stream().map(Field::getName).collect(Collectors.toList()).toArray(), ","));
      }
    }

  }



}
