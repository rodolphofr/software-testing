package br.com.leilao.start;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import br.com.leilao.dominio.LeilaoTest;
import br.com.leilao.dominio.LeiloesTest;
import br.com.leilao.dominio.UsuarioTest;

@RunWith(Suite.class)
@SuiteClasses({
	UsuarioTest.class,
	LeilaoTest.class,
	LeiloesTest.class
})
public class TestSuite { }
