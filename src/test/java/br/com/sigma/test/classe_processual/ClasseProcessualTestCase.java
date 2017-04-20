package br.com.sigma.test.classe_processual;

import java.io.File;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.test.spi.ArquillianProxyException;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.jboss.shrinkwrap.resolver.api.maven.PomEquippedResolveStage;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import br.com.jperondi.artifact.arc.base.business.GenericBusiness;
import br.com.jperondi.artifact.arc.base.config.CamelCaseImproveStrategy;
import br.com.jperondi.artifact.arc.base.dto.DTOConverter;
import br.com.jperondi.artifact.arc.base.filter.GenericFilter;
import br.com.jperondi.artifact.arc.base.persistence.GenericPersistenceClass;
import br.com.jperondi.artifact.arc.base.repository.EntityManagerRepository;
import br.com.jperondi.artifact.arc.base.service.FilterResponse;
import br.com.jperondi.artifact.arc.base.validate.BusinessException;
import br.com.jperondi.artifact.arc.base.web.ApplicationActivator;
import br.com.jperondi.artifact.arc.features.classe_processual.ClasseProcessualBusiness;
import br.com.jperondi.artifact.arc.features.classe_processual.ClasseProcessualFilter;
import br.com.jperondi.artifact.arc.features.classe_processual.ClasseProcessualService;
import br.com.jperondi.artifact.arc.features.classe_processual.def.DefaultClasseProcessualBusiness;
import br.com.jperondi.artifact.arc.features.classe_processual.dto.ClasseProcessualDTO;
import br.com.jperondi.artifact.arc.features.classe_processual.entity.ClasseProcessual;
import br.com.jperondi.artifact.arc.util.dto.DTOUtils;
import br.com.jperondi.artifact.arc.util.jpa.JPAUtils;
import br.com.jperondi.artifact.arc.util.query.RepresentColumn;
import br.com.jperondi.artifact.arc.util.text.JSONUtils;
import br.com.jperondi.artifact.arc.util.text.NamingFormat;

@RunWith(Arquillian.class)
public class ClasseProcessualTestCase {

  @Deployment
  public static WebArchive getDeployment() {
    try {
      WebArchive myArchive = ShrinkWrap.create(WebArchive.class, "web.war");

      final PomEquippedResolveStage pom = Maven.resolver().loadPomFromFile("pom.xml");
      final PomEquippedResolveStage dependencies = pom.importCompileAndRuntimeDependencies();
      final File[] libs = dependencies.resolve().withTransitivity().asFile();

      myArchive.addAsResource(EmptyAsset.INSTANCE, "WEB-INF/beans.xml");
      myArchive.addAsResource("persistence-test.xml", "META-INF/persistence.xml");
      myArchive.addClass(GenericPersistenceClass.class);
      myArchive.addClass(NamingFormat.class);
      myArchive.addPackage(GenericBusiness.class.getPackage());
      myArchive.addPackage(CamelCaseImproveStrategy.class.getPackage());
      myArchive.addPackage(DTOConverter.class.getPackage());
      myArchive.addPackage(EntityManagerRepository.class.getPackage());
      myArchive.addPackage(FilterResponse.class.getPackage());
      myArchive.addPackage(BusinessException.class.getPackage());
      myArchive.addPackage(ApplicationActivator.class.getPackage());
      myArchive.addPackage(GenericFilter.class.getPackage());
      myArchive.addPackage(DTOUtils.class.getPackage());
      myArchive.addPackage(JPAUtils.class.getPackage());
      myArchive.addPackage(JSONUtils.class.getPackage());

      myArchive.addPackage(ClasseProcessualService.class.getPackage());
      myArchive.addPackage(DefaultClasseProcessualBusiness.class.getPackage());
      myArchive.addPackage(ClasseProcessualDTO.class.getPackage());
      myArchive.addPackage(ClasseProcessualBusiness.class.getPackage());
      myArchive.addPackage(ClasseProcessual.class.getPackage());
      myArchive.addPackage(RepresentColumn.class.getPackage());

      myArchive.addAsLibraries(libs);

      return myArchive;
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  @Inject
  private ClasseProcessualService service;

  @Test
  @InSequence(1)
  public void naoPodeTerNenhuRegistroCadastrado() throws BusinessException {
    Assert.assertEquals(0, service.filter(new ClasseProcessualFilter()).getFilter().getCount(), 0);
  }

  @Test(expected = ArquillianProxyException.class)
  @InSequence(2)
  public void naoPodePermitirInserirNulo() throws BusinessException {
    service.save(null);
  }

  @Test(expected = ArquillianProxyException.class)
  @InSequence(3)
  public void naoPodePermitirInserirItemsObrigatoriosNulos() throws BusinessException {
    service.save(new ClasseProcessualDTO());
  }

  @Test
  @InSequence(4)
  public void deveInserir() throws BusinessException {
    service.save(new ClasseProcessualDTO("Classe 1"));
    service.save(new ClasseProcessualDTO("Classe 2"));
    service.save(new ClasseProcessualDTO("Classe 3"));
    service.save(new ClasseProcessualDTO("Classe 4"));
    service.save(new ClasseProcessualDTO("Classe 5"));
    service.save(new ClasseProcessualDTO("Classe 6"));
  }

  @Test
  @InSequence(5)
  public void deveRetornarUmRegistroFiltrado() throws BusinessException {
    ClasseProcessualFilter filter = new ClasseProcessualFilter();
    filter.setNome("1");

    Assert.assertEquals(6, service.filter(filter).getFilter().getCount(), 0);
  }

  @Test(expected = BusinessException.class)
  @InSequence(6)
  public void naoDevePermitirInserirDuplicado() throws BusinessException {
    service.save(new ClasseProcessualDTO("Classe 6"));
  }

  @Test(expected = BusinessException.class)
  @InSequence(7)
  public void naoPodeAtualizarNomeDuplicado() throws BusinessException {
    ClasseProcessualDTO classeProcessualDTO = new ClasseProcessualDTO(1, "Classe 6");

    service.update(classeProcessualDTO);
  }

  @Test
  @InSequence(8)
  public void deveAtualizarInformacao() throws BusinessException {
    ClasseProcessualDTO classeProcessualDTO = new ClasseProcessualDTO(1, "Classe Teste");

    service.update(classeProcessualDTO);
  }


}
