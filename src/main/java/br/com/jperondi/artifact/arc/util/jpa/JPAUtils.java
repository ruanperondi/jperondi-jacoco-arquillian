package br.com.jperondi.artifact.arc.util.jpa;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import br.com.jperondi.artifact.arc.util.text.ReflectionUtils;


public class JPAUtils {

  public static <T> List<T> convertToClass(Class<T> clazz, List<Object[]> objectList, String columns) {
    try {
      String[] columnsSelect = StringUtils.split(columns, ",");
      Map<String, Field> mapaAtributos = convertToMapFields(columnsSelect, clazz);

      List<T> listaRetorno = new ArrayList<>();

      for (Object[] objects : objectList) {
        int contador = 0;

        T t = clazz.newInstance();
        listaRetorno.add(t);

        for (String column : columnsSelect) {
          Field field = mapaAtributos.get(column);

          field.set(t, objects[contador++]);
        }
      }

      return listaRetorno;
    } catch (IllegalArgumentException | IllegalAccessException | InstantiationException e) {
      e.printStackTrace();

      return new ArrayList<>();
    }
  }

  private static <T> Map<String, Field> convertToMapFields(String[] columnsSelect, Class<T> clazz) {
    Map<String, Field> mapaAtributos = new LinkedHashMap<>();

    List<Field> fieldsClassMapped = ReflectionUtils.getAllFields(new LinkedList<Field>(), clazz);
    for (String string : columnsSelect) {
      List<Object> collect = fieldsClassMapped.stream().filter(field -> StringUtils.equalsIgnoreCase(field.getName(), string)).collect(Collectors.toList());

      if (CollectionUtils.isEmpty(collect)) {
        continue;
      }

      Field field = (Field) collect.get(0);
      field.setAccessible(true);
      mapaAtributos.put(string, field);
    }

    return mapaAtributos;
  }

}
