package br.com.zup.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.model.Categoria;
import br.com.zup.mercadolivre.request.NovaCategoriaRequest;

@RestController
public class CategoriaController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping(value = "/novaCategoria")
	@Transactional
	public String cadastraNovoUsuario(@RequestBody @Valid NovaCategoriaRequest request){
		Categoria categoria = request.toModel(manager);
		manager.persist(categoria);
		return categoria.toString();
		
	}

}
