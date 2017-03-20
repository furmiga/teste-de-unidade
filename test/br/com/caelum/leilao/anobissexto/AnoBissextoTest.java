package br.com.caelum.leilao.anobissexto;

import static org.junit.Assert.*;

import org.junit.Test;

public class AnoBissextoTest {
	@Test
	public void deveVerificarAnoBissexto(){
		AnoBissexto anoBissexto = new AnoBissexto();
		
		assertTrue(anoBissexto.ehBissexto(1808));
		assertTrue(anoBissexto.ehBissexto(1824));
		assertTrue(anoBissexto.ehBissexto(1832));
		assertTrue(anoBissexto.ehBissexto(1836));
		assertTrue(anoBissexto.ehBissexto(1892));
		assertTrue(anoBissexto.ehBissexto(1844));
		assertTrue(anoBissexto.ehBissexto(1852));
		assertTrue(anoBissexto.ehBissexto(1908));
		assertTrue(anoBissexto.ehBissexto(1912));
		assertTrue(anoBissexto.ehBissexto(1952));
		assertTrue(anoBissexto.ehBissexto(1944));
		assertTrue(anoBissexto.ehBissexto(1960));
		assertTrue(anoBissexto.ehBissexto(1968));
		assertTrue(anoBissexto.ehBissexto(1976));
		assertTrue(anoBissexto.ehBissexto(1980));
		assertTrue(anoBissexto.ehBissexto(1984));
		
		
	}
	
	@Test
	public void deveVerificarAnoNaoBissexto(){
		AnoBissexto anoBissexto = new AnoBissexto();
		
		assertFalse(anoBissexto.ehBissexto(1809));
		assertFalse(anoBissexto.ehBissexto(1825));
		assertFalse(anoBissexto.ehBissexto(1833));
		assertFalse(anoBissexto.ehBissexto(1837));
		assertFalse(anoBissexto.ehBissexto(1893));
		assertFalse(anoBissexto.ehBissexto(1845));
		assertFalse(anoBissexto.ehBissexto(1853));
		assertFalse(anoBissexto.ehBissexto(1909));
		assertFalse(anoBissexto.ehBissexto(1913));
		assertFalse(anoBissexto.ehBissexto(1953));
		assertFalse(anoBissexto.ehBissexto(1945));
		assertFalse(anoBissexto.ehBissexto(1961));
		assertFalse(anoBissexto.ehBissexto(1962));
		assertFalse(anoBissexto.ehBissexto(1977));
		assertFalse(anoBissexto.ehBissexto(1981));
		assertFalse(anoBissexto.ehBissexto(1985));
	}
	
}
