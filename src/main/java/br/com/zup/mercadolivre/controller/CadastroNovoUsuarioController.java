package br.com.zup.mercadolivre.controller;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.zup.mercadolivre.model.Usuario;
import br.com.zup.mercadolivre.request.NovoUsuarioRequest;

@RestController
public class CadastroNovoUsuarioController {
	
	@PersistenceContext
	private EntityManager manager;
	
	@PostMapping(value = "/novoUsuario")
	@Transactional
	public String cadastraNovoUsuario(@RequestBody @Valid NovoUsuarioRequest request){
		Usuario novoUsuario = request.toUsuario();
		manager.persist(novoUsuario);
		return novoUsuario.toString();
	}
}
