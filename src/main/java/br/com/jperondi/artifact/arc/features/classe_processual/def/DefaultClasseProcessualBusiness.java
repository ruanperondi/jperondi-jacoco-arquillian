package br.com.jperondi.artifact.arc.features.classe_processual.def;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Default;

import br.com.jperondi.artifact.arc.base.business.GenericBusinessImpl;
import br.com.jperondi.artifact.arc.features.classe_processual.ClasseProcessualBusiness;
import br.com.jperondi.artifact.arc.features.classe_processual.ClasseProcessualFilter;
import br.com.jperondi.artifact.arc.features.classe_processual.entity.ClasseProcessual;

/**
 * Classe responsáx vel por definir o contrato de regra de neg�cio da Classe Processual
 *
 * @author Juan Perondi
 */
@Default
@Dependent
public class DefaultClasseProcessualBusiness extends GenericBusinessImpl<Integer, ClasseProcessual, ClasseProcessualFilter> implements ClasseProcessualBusiness {

}
