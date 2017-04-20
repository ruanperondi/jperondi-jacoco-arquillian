package br.com.jperondi.artifact.arc.features.classe_processual.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.envers.Audited;
import org.hibernate.validator.constraints.NotBlank;

import br.com.jperondi.artifact.arc.base.persistence.GenericPersistenceClass;

/**
 * Classe responsável por ter informações a respeito da Classe Processual
 */
@Entity
@Table
@DynamicUpdate
@SelectBeforeUpdate
@Audited
public class ClasseProcessual extends GenericPersistenceClass<Integer> {

  @Id
  @TableGenerator(name = "classe_processual_id", table = "sequence_ids", pkColumnName = "name", valueColumnName = "value")
  @GeneratedValue(strategy = GenerationType.TABLE, generator = "classe_processual_id")
  private Integer id;

  @Column(unique = true, nullable = false, length = 70)
  @NotBlank(message = "{classe_processual.nome.obrigatorio}")
  private String nome;

  @Override
  public Integer getId() {
    return id;
  }

  /**
   * Construtor da classe
   */
  public ClasseProcessual() {
    this(null, null);
  }

  /**
   * Construtor da classe
   * 
   * @param id
   * @param nome
   */
  public ClasseProcessual(Integer id, String nome) {
    super();
    this.id = id;
    this.nome = nome;
  }

  /**
   * Retorna o nome
   * 
   * @return the nome
   */
  public String getNome() {
    return nome;
  }
}
