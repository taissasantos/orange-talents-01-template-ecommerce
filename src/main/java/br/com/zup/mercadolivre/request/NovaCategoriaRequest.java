package br.com.zup.mercadolivre.request;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;

import org.springframework.util.Assert;

import br.com.zup.mercadolivre.model.Categoria;
import br.com.zup.mercadolivre.validator.UniqueValue;

public class NovaCategoriaRequest {

	@NotBlank
	@UniqueValue(domainClass = Categoria.class, fieldName = "nome", message = "Nome da categoria é obrigatório")
	private String nome;
	
	@Positive
	private Long idCategoriaMae;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setIdCategoriaMae(Long idCategoriaMae) {
		this.idCategoriaMae = idCategoriaMae;
	}

	

	public Categoria toModel(EntityManager manager) {
		Categoria categoria = new Categoria(nome);
		if(idCategoriaMae != null) {
			Categoria categoriaMae = manager.find(Categoria.class, idCategoriaMae);
			Assert.notNull(categoriaMae, "O id da categoria mãe precisa ser válido");
			categoria.setCategoriaMae(categoriaMae);
		}
		
		return categoria;
	}
	
	
	
	
}
