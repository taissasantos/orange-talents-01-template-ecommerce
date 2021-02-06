package br.com.zup.mercadolivre.model;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;

@Entity
public class Usuario implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	@Email
	private String email;
	
	@NotBlank
	@Length(min = 6)
	private String senha;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Perfil> perfis = new ArrayList<>();
	
	public LocalDateTime dataCadastro = LocalDateTime.ofInstant(Instant.now(), ZoneId.systemDefault());

	public Usuario(@NotBlank @Email String email, @NotNull @Valid SenhaLimpa senhaLimpa) {
		Assert.notNull(senhaLimpa, "o objeto do tipo senha limpa nao pode ser nulo");
		this.email = email;
		this.senha = senhaLimpa.hash();
	}
	
	@Deprecated
	public Usuario() {}

	@Override
	public String toString() {
		return "Usuario [email=" + email + ", senha=" + senha + ", dataCadastro=" + dataCadastro + "]";
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



	public Object getId() {
		return id;
	}



	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.perfis;
	}



	@Override
	public String getPassword() {
		return this.senha;
	}



	@Override
	public String getUsername() {
		return this.email;
	}



	@Override
	public boolean isAccountNonExpired() {
		return true;
	}



	@Override
	public boolean isAccountNonLocked() {
		return true;
	}



	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}



	@Override
	public boolean isEnabled() {
		return true;
	}
	
	
	
}
