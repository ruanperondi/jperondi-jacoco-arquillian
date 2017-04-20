package br.com.jperondi.artifact.arc.base.business;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.inject.Any;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;

import br.com.jperondi.artifact.arc.base.filter.GenericFilter;
import br.com.jperondi.artifact.arc.base.persistence.GenericPersistenceClass;
import br.com.jperondi.artifact.arc.base.repository.GenericRepository;
import br.com.jperondi.artifact.arc.base.validate.BusinessException;
import br.com.jperondi.artifact.arc.base.validate.GenericEntityValidation;

public abstract class GenericBusinessImpl<PK extends Serializable, T extends GenericPersistenceClass<PK>, F extends GenericFilter> implements GenericBusiness<PK, T, F> {

  @Inject
  @Any
  private GenericRepository<PK, T, F> repository;

  @Inject
  @Any
  private GenericEntityValidation<PK, T> validation;

  @Override
  public List<T> filter(final F filter) throws BusinessException {
    return repository.filter(filter);
  }

  @Override
  public Long count(@NotNull final F filter) throws BusinessException {
    return repository.count(filter);
  }

  @Override
  public T get(@NotNull final PK id) throws BusinessException {
    final T t = repository.get(id);

    if (t != null) {
      return t;
    }

    throw new BusinessException("ID " + id + " não encontrado!");
  }

  @Override
  public T save(@NotNull final T t) throws BusinessException {
    validation.validatePersist(t);

    return repository.save(t);
  }

  @Override
  public T update(@NotNull final T t) throws BusinessException {
    validation.validatePersist(t);

    return repository.edit(t);
  }
}
