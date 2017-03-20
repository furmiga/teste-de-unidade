package br.com.caelum.leilao.dominio;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class AvaliadorTest {

	private Avaliador leiloeiro;
	private Usuario joao;
	private Usuario jose;
	private Usuario maria;

	@BeforeClass
	public static void testandoBeforeClass() {
	  System.out.println("before class");
	}
	
	@AfterClass
	public static void testandoAfterClass() {
	  System.out.println("after class");
	}
	
	@Before
	public void setUp() {
		this.leiloeiro = new Avaliador();
		this.joao = new Usuario("João");
		this.jose = new Usuario("José");
		this.maria = new Usuario("Maria");
	}

	@Test
	public void deveEntenderLancesEmOrdemCrescente() {
		// cenario: 3 lances em ordem crescente
		Leilao leilao = new Leilao("Playstation 3 Novo");
		leilao.propoe(new Lance(maria, 250.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(jose, 400.0));
		// executando a acao
		leiloeiro.avalia(leilao);
		// comparando a saida com o esperado
		double maiorEsperado = 400;
		double menorEsperado = 250;
		assertEquals(maiorEsperado, leiloeiro.getMaiorLance(), 0.0001);
		assertEquals(menorEsperado, leiloeiro.getMenorLance(), 0.0001);
	}

	@Test
	public void deveEntenderUnicoLance() {
		Leilao leilao = new Leilao("Playstation 4");
		leilao.propoe(new Lance(joao, 200));

		leiloeiro.avalia(leilao);

		assertEquals(200, leiloeiro.getMaiorLance(), 0.0001);
		assertEquals(200, leiloeiro.getMenorLance(), 0.0001);
	}

	@Test
	public void deveEntenderLeilaoComOrdemAleatoria() {

		Leilao leilao = new Leilao("Playstation 3 Novo");
		leilao.propoe(new Lance(maria, 200.0));
		leilao.propoe(new Lance(joao, 450.0));
		leilao.propoe(new Lance(jose, 120.0));
		leilao.propoe(new Lance(jose, 500.0));
		leilao.propoe(new Lance(jose, 320.0));

		// executando a acao
		leiloeiro.avalia(leilao);

		assertEquals(120, leiloeiro.getMenorLance(), 0.0001);
		assertEquals(450, leiloeiro.getMaiorLance(), 0.0001);
	}

	@Test
	public void deveEntenderLancesComOrdemDecrescente() {

		Leilao leilao = new Leilao("Playstation 4 Novo");
		leilao.propoe(new Lance(maria, 400.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(jose, 200.0));

		// executando a acao
		leiloeiro.avalia(leilao);

		assertEquals(200, leiloeiro.getMenorLance(), 0.0001);
		assertEquals(400, leiloeiro.getMaiorLance(), 0.0001);
	}

	@Test
	public void deveEntenderosTresMaioresLances() {

		Leilao leilao = new CriadorDeLeilao().para("Tv LG 40").lance(joao, 100.0).constroi();

		leilao.propoe(new Lance(joao, 100.0));
		leilao.propoe(new Lance(maria, 200.0));
		leilao.propoe(new Lance(joao, 300.0));
		leilao.propoe(new Lance(maria, 400.0));

		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();

		assertEquals(3, maiores.size());
		assertEquals(400, maiores.get(0).getValor(), 0.00001);
		assertEquals(300, maiores.get(1).getValor(), 0.00001);
		assertEquals(200, maiores.get(2).getValor(), 0.00001);
	}

	@Test
	public void deveDevolverTodosLancesCasoNaoHajaNoMinimo3() {
		Leilao leilao = new CriadorDeLeilao().para("Tv LG 40").
				lance(joao, 100.0).
				lance(maria, 200.0).
				constroi();

		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();

		assertEquals(2, maiores.size());
		assertEquals(200, maiores.get(0).getValor(), 0.00001);
		assertEquals(100, maiores.get(1).getValor(), 0.00001);
	}

	@Test(expected=RuntimeException.class)
	public void deveDevolverListaVaziaCasoNaoHajaLances() {
		Leilao leilao = new Leilao("Playstation 3 Novo");

		leiloeiro.avalia(leilao);

		List<Lance> maiores = leiloeiro.getTresMaiores();

		assertEquals(0, maiores.size());
	}
	
	@Test(expected=RuntimeException.class)
	public void naoDeveAvaliarLeiloesSemNenhumLanceDado() {
	    Leilao leilao = new CriadorDeLeilao()
	        .para("Playstation 3 Novo")
	        .constroi();

	    leiloeiro.avalia(leilao);
	}

	@After
	public void finaliza() {
		System.out.println("fim");
	}
}
