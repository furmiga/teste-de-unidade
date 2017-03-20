package br.com.caelum.leilao.dominio;

import static org.junit.Assert.*;

import org.junit.Test;

public class LeilaoTest {

	@Test
	public void naoDeveAceitarDoisLancesDoMesmoUsuario() {
		Usuario bill = new Usuario("Bill");

		Leilao leilao = new Leilao("computador foda");
		leilao.propoe(new Lance(bill, 1000));
		leilao.propoe(new Lance(bill, 2000));

		assertEquals(1, leilao.getLances().size());
		assertEquals(1000, leilao.getLances().get(0).getValor(), 0.00001);
	}

	@Test
	public void naoDeveAceitarMaisDoQue5LancesDeUmMesmoUsuario() {
		Usuario bill = new Usuario("Bill");
		Usuario will = new Usuario("will");

		Leilao leilao = new Leilao("computador foda");
		leilao.propoe(new Lance(bill, 1000));
		leilao.propoe(new Lance(will, 2000));

		leilao.propoe(new Lance(bill, 5000));
		leilao.propoe(new Lance(will, 7000));

		leilao.propoe(new Lance(bill, 9000));
		leilao.propoe(new Lance(will, 9500));

		leilao.propoe(new Lance(bill, 10000));
		leilao.propoe(new Lance(will, 11000));

		leilao.propoe(new Lance(bill, 12000));
		leilao.propoe(new Lance(will, 20000));

		leilao.propoe(new Lance(bill, 1000));

		assertEquals(10, leilao.getLances().size());
		assertEquals(20000, leilao.getLances().get(leilao.getLances().size() - 1).getValor(), 0.00001);
	}

	@Test
	public void deveDobrarOUltimoLanceDado() {
		Leilao leilao = new Leilao("Macbook Pro 15");
		Usuario steveJobs = new Usuario("Steve Jobs");
		Usuario billGates = new Usuario("Bill Gates");

		leilao.propoe(new Lance(steveJobs, 2000));
		leilao.propoe(new Lance(billGates, 3000));
		leilao.dobraLance(steveJobs);

		assertEquals(4000, leilao.getLances().get(2).getValor(), 0.00001);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deveLancarExcecaoComLanceZero(){
		Leilao leilao = new Leilao("Macbook Pro 15");
		Usuario steveJobs = new Usuario("Steve Jobs");

		leilao.propoe(new Lance(steveJobs, 0));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void deveLancarExcecaoComLanceMenorQueZero(){
		Leilao leilao = new Leilao("Macbook Pro 15");
		Usuario steveJobs = new Usuario("Steve Jobs");

		leilao.propoe(new Lance(steveJobs, -1));
	}

	@Test
	public void naoDeveDobrarCasoNaoHajaLanceAnterior() {
		Leilao leilao = new Leilao("Macbook Pro 15");
		Usuario steveJobs = new Usuario("Steve Jobs");

		leilao.dobraLance(steveJobs);

		assertEquals(0, leilao.getLances().size());
	}
}
