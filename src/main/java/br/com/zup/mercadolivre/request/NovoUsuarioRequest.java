package br.com.zup.mercadolivre.request;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

import br.com.zup.mercadolivre.validator.UniqueValue;
import br.com.zup.mercadolivre.model.SenhaLimpa;
import br.com.zup.mercadolivre.model.Usuario;

public class NovoUsuarioRequest {
	
	@NotBlank
	@Email
	@UniqueValue(domainClass = Usuario.class, fieldName = "email", message = "Email não pode ser duplicado")
	private String email;
	@NotBlank
	@Length(min = 6)
	private String senha;
	
	public LocalDateTime dataCadastro = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());

	public NovoUsuarioRequest() {}
	
	public NovoUsuarioRequest(@NotBlank @Email String email, @NotBlank @Length(min = 6) String senha,
			LocalDateTime dataCadastro) {
		super();
		this.email = email;
		this.senha = senha;
		this.dataCadastro = dataCadastro;
	}
	
	public Usuario toUsuario() {
		return new Usuario(email, new SenhaLimpa(senha));
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public LocalDateTime getDataCadastro() {
		return dataCadastro;
	}



}
