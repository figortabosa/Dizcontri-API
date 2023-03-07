package com.exemple.dizcontre.api.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.exemple.dizcontre.api.ApplicationContextLoad;
import com.exemple.dizcontre.api.model.Usuario;
import com.exemple.dizcontre.api.repository.UsuarioRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
@Component
public class JWTTokenAutenticacaoService {

	private static final long EXPIRATION_TIME= 172800000;
	
	
	private static final String SECRET = "SenhaExtremamenteSecreta";
	
	
	private static final String TOKEN_PREFIX = "Bearer";
	
	private static final String HEADER_STRING = "authorization";
	
	/* Gerando token de autenticação e adicionando ao cabeçalho e response Http */
	public void addAuthentication(HttpServletResponse response, String username) throws IOException {
		
		String JWT = Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET).compact();
		
		/*Junta token com o prefixo*/
		String token = TOKEN_PREFIX + " " + JWT;  /*Bearer gdfd44e45e5e5434343*/
		
		/*Adiciona no cabeçalho http*/
		response.addHeader(HEADER_STRING, token);  /*authotization: Bearer gdfd44e45e5e5434343*/
		
		ApplicationContextLoad.getApplicationContext()
		.getBean(UsuarioRepository.class).atualizaTokenUser(JWT, username);;
		
		liberacaoCors(response);
		
		response.getWriter().write("{\"authorization\": \""+token+"\"}");
	}
	
	public Authentication getAuthentication(HttpServletRequest request, HttpServletResponse response) {
		
		/*Pega o token enviado no cabeçalho*/
		String token = request.getHeader(HEADER_STRING);
		
		  try {
			if (token != null) {
				
				String tokenLimpo = token.replace(TOKEN_PREFIX, "").trim();
				
				/* Faz a validação do token do usuario na requisição*/
				String user = Jwts.parser().setSigningKey(SECRET)
						      .parseClaimsJws(tokenLimpo)
						      .getBody().getSubject();
				
				if(user != null) {
					Usuario usuario = ApplicationContextLoad.getApplicationContext()
							.getBean(UsuarioRepository.class).findUserByLong(user);
					
					/*Retorna o usuario logado*/
					if(usuario != null) {
						if (tokenLimpo.equalsIgnoreCase(usuario.getToken())) {
						return new UsernamePasswordAuthenticationToken(
								usuario.getLogin(),
								usuario.getSenha(),
								usuario.getAuthorities());
						}
					}	
				}
			}
		  }catch (io.jsonwebtoken.ExpiredJwtException e) {
			try {
				response.getOutputStream().println("Seu TOKEN expirou, faça o login novamente ou informe um novo TOKEN.");
			} catch (IOException e1) {}
		}
		
			liberacaoCors(response);
		
			return null; /* Não autorizado*/ 	
	}

	private void liberacaoCors(HttpServletResponse response) {
		
		if (response.getHeader("Access-Control-Allow-Origin") == null) {
			response.addHeader("Access-Control-Allow-Origin", "*");
		}
		
		if (response.getHeader("Access-Control-Allow-Headers") == null) {
			response.addHeader("Access-Control-Allow-Headers", "*");
		}
		
		if (response.getHeader("Access-Control-Request-Headers") == null) {
			response.addHeader("Access-Control-Request-Headers", "*");
		}
		
		if (response.getHeader("Access-Control-Allow-Methods") == null) {
			response.addHeader("Access-Control-Allow-Methods", "*");
		}
	}
}
