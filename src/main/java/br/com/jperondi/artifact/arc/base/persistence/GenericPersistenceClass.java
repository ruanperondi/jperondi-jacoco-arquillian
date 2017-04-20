package br.com.jperondi.artifact.arc.base.persistence;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.envers.Audited;

/**
 * Classe abstrata responsável pela definição basica das classes do sistema
 * 
 * @author Juan Perondi
 */
@MappedSuperclass
public abstract class GenericPersistenceClass<PK extends Serializable> {

  @Temporal(TemporalType.TIMESTAMP)
  @Column(nullable = false, updatable = false)
  @Audited
  private Date dataInclusao;

  @Temporal(TemporalType.TIMESTAMP)
  @Audited
  private Date dataAlteracao;

  @PrePersist
  private void prePersist() {
    this.dataInclusao = new Date();
  }

  @PreUpdate
  private void preUpdate() {
    this.dataAlteracao = new Date();
  }


  /**
   * M�todo responsável por retornar o campo
   *
   * @return retorna o campo Id
   */
  public abstract PK getId();

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#hashCode()
   */
  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
    return result;
  }

  /*
   * (non-Javadoc)
   * 
   * @see java.lang.Object#equals(java.lang.Object)
   */
  @SuppressWarnings("rawtypes")
  @Override
  public boolean equals(final Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (!(obj instanceof GenericPersistenceClass)) {
      return false;
    }
    final GenericPersistenceClass other = (GenericPersistenceClass) obj;
    if (getId() == null) {
      if (other.getId() != null) {
        return false;
      }
    } else if (!getId().equals(other.getId())) {
      return false;
    }
    return true;
  }
}
