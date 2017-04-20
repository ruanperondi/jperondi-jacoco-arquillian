package br.com.jperondi.artifact.arc.features.classe_processual;

import br.com.jperondi.artifact.arc.base.service.GenericService;
import br.com.jperondi.artifact.arc.features.classe_processual.dto.ClasseProcessualDTO;
import br.com.jperondi.artifact.arc.features.classe_processual.entity.ClasseProcessual;

/**
 * Classe responsável por determinar endpoints extras da Classe processual
 *
 * @author Juan Perondi
 *
 */
public interface ClasseProcessualService extends GenericService<Integer, ClasseProcessual, ClasseProcessualDTO, ClasseProcessualFilter> {

}
