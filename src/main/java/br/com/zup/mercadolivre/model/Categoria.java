package br.com.zup.mercadolivre.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Column(unique = true , nullable = false)
	private String nome;
	
	@ManyToOne
	private Categoria categoriaMae;
	
	@Deprecated
	public Categoria() {}


	public Categoria(@NotBlank String nome) {
		this.nome = nome;
	}


	public void setCategoriaMae(Categoria categoriaMae) {
		this.categoriaMae = categoriaMae;
	}


	@Override
	public String toString() {
		return "Categoria [id=" + id + ", nome=" + nome + ", categoriaMae=" + categoriaMae + "]";
	}


	
	
	
	
	
}
