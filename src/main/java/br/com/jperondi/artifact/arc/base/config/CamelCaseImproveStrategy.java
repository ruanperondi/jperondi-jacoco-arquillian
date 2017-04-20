package br.com.jperondi.artifact.arc.base.config;

import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;

import br.com.jperondi.artifact.arc.util.text.NamingFormat;

/**
 * Classe responsável por utilizar a strategia de convers�o de Camel Case para
 * underscore
 *
 * @author Juan Perondi
 */
public class CamelCaseImproveStrategy extends PhysicalNamingStrategyStandardImpl {

	private static final long serialVersionUID = -3684947602685100085L;

	@Override
	public Identifier toPhysicalColumnName(Identifier name, JdbcEnvironment context) {
		return new Identifier(NamingFormat.toUnderscore(name.getText()), name.isQuoted());
	}

	@Override
	public Identifier toPhysicalTableName(Identifier name, JdbcEnvironment context) {
		return new Identifier(NamingFormat.toUnderscore(name.getText()), name.isQuoted());
	}

}