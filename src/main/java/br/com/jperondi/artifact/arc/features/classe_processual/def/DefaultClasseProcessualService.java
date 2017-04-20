package br.com.jperondi.artifact.arc.features.classe_processual.def;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Default;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import br.com.jperondi.artifact.arc.base.service.GenericServiceImpl;
import br.com.jperondi.artifact.arc.base.validate.BusinessException;
import br.com.jperondi.artifact.arc.features.classe_processual.ClasseProcessualFilter;
import br.com.jperondi.artifact.arc.features.classe_processual.ClasseProcessualService;
import br.com.jperondi.artifact.arc.features.classe_processual.dto.ClasseProcessualDTO;
import br.com.jperondi.artifact.arc.features.classe_processual.entity.ClasseProcessual;

/**
 * Classe responsável por determinar o endpoint de acesso a Classe Processual
 *
 * @author Juan Perondi
 *
 */
@Path("/classes")
@Produces({MediaType.APPLICATION_JSON})
@Consumes({MediaType.APPLICATION_JSON})
@RequestScoped
@Default
public class DefaultClasseProcessualService extends GenericServiceImpl<Integer, ClasseProcessual, ClasseProcessualDTO, ClasseProcessualFilter> implements ClasseProcessualService {

  private static final long serialVersionUID = 6831206113494397369L;

  @Override
  @GET
  @Path("/{id}")
  public ClasseProcessualDTO get(@PathParam("id") final Integer id) throws BusinessException {
    return super.get(id);
  }

}
