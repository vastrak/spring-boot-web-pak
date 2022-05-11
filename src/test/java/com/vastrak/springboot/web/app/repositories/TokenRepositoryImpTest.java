package com.vastrak.springboot.web.app.repositories;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import com.vastrak.springboot.web.app.model.Token;

@DataJpaTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TokenRepositoryImpTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	TokenRepository tokenRepository;

	@Test
	public void test100_crearNuevoToken() {
		
		Token nuevo = new Token("BDE", "NUEVO567");
		Token agregado = tokenRepository.save(nuevo);
		assertNotNull(agregado);
		assertTrue(nuevo.equals(agregado));
		assertThat(agregado).hasFieldOrPropertyWithValue("tokenId", "BDE");
		assertThat(agregado).hasFieldOrPropertyWithValue("tokenValor", "NUEVO567");
		
	}
	
	@Test
	public void test200_buscarTokenPorTokenId() {
		
		Token token1 = new Token("BDE", "LMJH567");
		entityManager.persist(token1);
		Token token2 = new Token("AVF", "TYQE568");
		entityManager.persist(token2);
		Token token3 = new Token("TMK", "POIU569");
		entityManager.persist(token3);
		
		Token testigo = tokenRepository.findByTokenId("AVF");
		assertNotNull(testigo);
		assertNotNull(testigo.getTokenId());
		assertThat(testigo).hasFieldOrPropertyWithValue("tokenId", "AVF");
		assertThat(testigo).hasFieldOrPropertyWithValue("tokenValor", "TYQE568");
	}		
	
	@Test
	public void test300_recuperarTodosLosTokens() {
		
		Token token1 = new Token("BDE", "LMJH567");
		entityManager.persist(token1);
		Token token2 = new Token("AVF", "TYQE568");
		entityManager.persist(token2);
		Token token3 = new Token("TMK", "POIU569");
		entityManager.persist(token3);
		
		List<Token> listaTokens = tokenRepository.findAll();
		assertThat(listaTokens).hasSize(3).contains(token1, token2, token3);
		
	}
	
	@Test
	public void test400_actualizarToken() {
		
		Token token1 = new Token("BDE", "LMJH567");
		entityManager.persist(token1);
		Token token2 = new Token("AVF", "TYQE568");
		entityManager.persist(token2);
		Token token3 = new Token("TMK", "POIU569");
		entityManager.persist(token3);
		
		Token actualizado = tokenRepository.findByTokenId("AVF");
		Long id = actualizado.getId();
		actualizado.setTokenValor("CAMBIADO");
		tokenRepository.save(actualizado);
		
		Token recuperado = tokenRepository.getById(id);
		
		assertThat(recuperado.getId()).isEqualTo(id);
		assertThat(recuperado.getTokenId()).isEqualTo("AVF");
		assertThat(recuperado.getTokenValor()).isEqualTo("CAMBIADO");
		
	}
	
	@Test
	public void test500_eliminarToken() {
		
		Token token1 = new Token("BDE", "LMJH567");
		entityManager.persist(token1);
		Token token2 = new Token("AVF", "TYQE568");
		entityManager.persist(token2);
		Token token3 = new Token("TMK", "POIU569");
		entityManager.persist(token3);
		
		tokenRepository.delete(token2);
		
		Token recuperado = tokenRepository.findByTokenId(token2.getTokenId());
		List<Token> listaTokens = tokenRepository.findAll();
		
		assertNull(recuperado);
		assertThat(listaTokens).hasSize(2).contains(token1, token3);
		
	}	
	
	@Test
	public void test600_excepcionPorTokenIdDuplicado() {
		
		tokenRepository.save(new Token("BDE", "KLUJ3456"));
	    Exception exception = assertThrows(org.springframework.dao.DataIntegrityViolationException.class, () -> {
			tokenRepository.save(new Token("BDE", "NTPD6565"));
			tokenRepository.count();
	    });

	    assertNotNull(exception);
	}
	
}
