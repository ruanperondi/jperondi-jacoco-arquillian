package br.com.jperondi.artifact.arc.features.classe_processual.dto;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;

import br.com.jperondi.artifact.arc.base.dto.DTOConverter;
import br.com.jperondi.artifact.arc.features.classe_processual.entity.ClasseProcessual;

/**
 * Classe respons�vel por converter um DTO para uma Classe Processual
 *
 * @author Juan Perondi
 */
@Default
@Dependent
class ClasseProcessualDTOConverter implements DTOConverter<Integer, ClasseProcessual, ClasseProcessualDTO> {

  @Override
  public ClasseProcessualDTO convert(final ClasseProcessual entity) {
    if (entity == null) {
      return new ClasseProcessualDTO();
    }
    return new ClasseProcessualDTO(entity.getId(), entity.getNome());

  }

  @Override
  public ClasseProcessual convert(final ClasseProcessualDTO dto) {
    if (dto == null) {
      return new ClasseProcessual();
    }

    final ClasseProcessual entity = new ClasseProcessual(dto.getId(), dto.getNome());

    return entity;
  }

}
