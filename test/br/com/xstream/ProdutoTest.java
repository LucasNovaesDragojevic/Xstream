package br.com.xstream;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import com.thoughtworks.xstream.XStream;

public class ProdutoTest {
	
	@Test
	public void deveGerarXMLAdequadamente() {
		String xmlEsperado = "<produto codigo=\"1587\">\n"
							+ "  <nome>Geladeira</nome>\n"
							+ "  <preco>R$ 2.000,00</preco>\n"
							+ "  <descrição>Geladeira com freezer invertido</descrição>\n"
							+ "</produto>";
		
		Produto geladeira = new Produto("Geladeira", 2000.00, "Geladeira com freezer invertido", 1587);
		
		XStream xstream = new XStream();
		xstream.alias("produto", Produto.class);
		xstream.aliasField("descrição", Produto.class, "descricao");
		xstream.registerLocalConverter(Produto.class, "preco", new PrecoSimplesConverter());
		xstream.useAttributeFor(Produto.class, "codigo");
		String xmlGerado = xstream.toXML(geladeira);
		
		assertEquals(xmlEsperado, xmlGerado);
	}
}
