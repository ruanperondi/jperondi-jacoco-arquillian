package br.com.jperondi.artifact.arc.features.classe_processual;

import br.com.jperondi.artifact.arc.base.repository.GenericRepository;
import br.com.jperondi.artifact.arc.features.classe_processual.entity.ClasseProcessual;

/**
 * Classe responsável por definir o contrato para utilização do Repositório da entidade Categoria
 *
 * @author Juan Perondi
 */
public interface ClasseProcessualRepository extends GenericRepository<Integer, ClasseProcessual, ClasseProcessualFilter> {
}
