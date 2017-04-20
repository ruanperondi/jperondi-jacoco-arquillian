package br.com.sigma.test.text;

import org.junit.Assert;
import org.junit.Test;

import br.com.jperondi.artifact.arc.base.validate.BusinessException;
import br.com.jperondi.artifact.arc.util.text.NamingFormat;

public class NamingFormatTestCase {

  @Test
  public void naoDevePossuirRegistros() throws BusinessException {
    Assert.assertEquals("to_underscore", NamingFormat.toUnderscore("toUnderscore"));
  }
}
