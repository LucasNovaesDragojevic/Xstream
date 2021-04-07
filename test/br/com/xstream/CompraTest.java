package br.com.xstream;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.thoughtworks.xstream.XStream;

public class CompraTest {
	
	@Test
	public void deveGerarXMLDeCompraComProdutos() {
		String xmlEsperado = "<compra>\n"
							+ "  <id>15</id>\n"
							+ "  <produtos>\n"
							+ "    <produto codigo=\"1587\">\n"
							+ "      <nome>Geladeira</nome>\n"
							+ "      <preco>2000.0</preco>\n"
							+ "      <descrição>Geladeira com freezer invertido</descrição>\n"
							+ "    </produto>\n"
							+ "    <produto codigo=\"1588\">\n"
							+ "      <nome>Batedeira</nome>\n"
							+ "      <preco>200.0</preco>\n"
							+ "      <descrição>Batedeira de aço</descrição>\n"
							+ "    </produto>\n"
							+ "  </produtos>\n"
							+ "</compra>";
		
		Compra compra = compraComGeladeiraEBatedeira();
		
		XStream xstream = xstreamParaCompraEProduto();
		
		String xmlGerado = xstream.toXML(compra);

		assertEquals(xmlEsperado, xmlGerado);
	}
	
	@Test
	public void deveGerarUmaCompraAPartirDeUmXML() {
		String xmlOrigem = "<compra>\n"
							+ "  <id>15</id>\n"
							+ "  <produtos>\n"
							+ "    <produto codigo=\"1587\">\n"
							+ "      <nome>Geladeira</nome>\n"
							+ "      <preco>2000.0</preco>\n"
							+ "      <descrição>Geladeira com freezer invertido</descrição>\n"
							+ "    </produto>\n"
							+ "    <produto codigo=\"1588\">\n"
							+ "      <nome>Batedeira</nome>\n"
							+ "      <preco>200.0</preco>\n"
							+ "      <descrição>Batedeira de aço</descrição>\n"
							+ "    </produto>\n"
							+ "  </produtos>\n"
							+ "</compra>";
		
		Compra compraEsperada = compraComGeladeiraEBatedeira();
	
		XStream xstream = xstreamParaCompraEProduto();
		
		Compra compraDeserializada = (Compra) xstream.fromXML(xmlOrigem);
		
		assertEquals(compraEsperada, compraDeserializada);
	}
	
	@Test
	public void deveSerializarDuasGeladeirasIguais() {
		String xmlEsperado = "<compra>\n"
							+ "  <id>15</id>\n"
							+ "  <produtos>\n"
							+ "    <produto codigo=\"1587\">\n"
							+ "      <nome>Geladeira</nome>\n"
							+ "      <preco>2000.0</preco>\n"
							+ "      <descrição>Geladeira com freezer invertido</descrição>\n"
							+ "    </produto>\n"
							+ "    <produto codigo=\"1587\">\n"
							+ "      <nome>Geladeira</nome>\n"
							+ "      <preco>2000.0</preco>\n"
							+ "      <descrição>Geladeira com freezer invertido</descrição>\n"
							+ "    </produto>\n"
							+ "  </produtos>\n"
							+ "</compra>";
		
		Compra compra = compraComGeladeirasIguais();
		
		XStream xstream = xstreamParaCompraEProduto();
		xstream.setMode(XStream.NO_REFERENCES);
		
		String xmlGerado = xstream.toXML(compra);
		
		assertEquals(xmlEsperado, xmlGerado);
	}
	
	@Test
	public void deveSerializarColecoesImplicitas() {
		String xmlEsperado = "<compra>\n"
							+ "  <id>15</id>\n"
							+ "  <produto codigo=\"1587\">\n"
							+ "    <nome>Geladeira</nome>\n"
							+ "    <preco>2000.0</preco>\n"
							+ "    <descrição>Geladeira com freezer invertido</descrição>\n"
							+ "  </produto>\n"
							+ "  <produto codigo=\"1588\">\n"
							+ "    <nome>Batedeira</nome>\n"
							+ "    <preco>200.0</preco>\n"
							+ "    <descrição>Batedeira de aço</descrição>\n"
							+ "  </produto>\n"
							+ "</compra>";

		Compra compra = compraComGeladeiraEBatedeira();
		
		XStream xstream = xstreamParaCompraEProduto();
		xstream.addImplicitCollection(Compra.class, "produtos");
		
		String xmlGerado = xstream.toXML(compra);
		
		assertEquals(xmlEsperado, xmlGerado);
	}
	
	@Test
	public void deveSerializarLivroEMusica() {
		String xmlEsperado = "<compra>\n"
							+ "  <id>15</id>\n"
							+ "  <produtos class=\"linked-list\">\n"
							+ "    <livro codigo=\"1589\">\n"
							+ "      <nome>O pássaro raro</nome>\n"
							+ "      <preco>100.0</preco>\n"
							+ "      <descrição>Dez histórias sobre a existência</descrição>\n"
							+ "    </livro>\n"
							+ "    <livro codigo=\"1570\">\n"
							+ "      <nome>Meu passeio</nome>\n"
							+ "      <preco>100.0</preco>\n"
							+ "      <descrição>Música livre</descrição>\n"
							+ "    </livro>\n"
							+ "  </produtos>\n"
							+ "</compra>";
		
		Compra compra = compraComLivroEMusica();
		XStream xstream = xstreamParaCompraEProduto();
		String xmlGerado = xstream.toXML(compra);
		assertEquals(xmlEsperado, xmlGerado);
	}
	
	private Compra compraComLivroEMusica() {
		Produto livro = new Livro("O pássaro raro", 100.00, "Dez histórias sobre a existência", 1589);
		Produto musica = new Livro("Meu passeio", 100.00, "Música livre", 1570);
		
		List<Produto> produtos = new LinkedList<>();
		produtos.add(livro);
		produtos.add(musica);
		
		return new Compra(15, produtos);
	}
	
	private Compra compraComGeladeirasIguais() {
		Produto geladeira = geladeira();
	
		List<Produto> produtos = new ArrayList<>();
		produtos.add(geladeira);
		produtos.add(geladeira);
		
		Compra compra = new Compra(15, produtos);
		return compra;
	}
	
	private XStream xstreamParaCompraEProduto() {
		XStream xstream = new XStream();
		xstream.alias("compra", Compra.class);
		xstream.alias("produto", Produto.class);
		xstream.alias("livro", Livro.class);
		xstream.alias("musica", Musica.class);
		xstream.aliasField("descrição", Produto.class, "descricao");
		xstream.useAttributeFor(Produto.class, "codigo");
		return xstream;
	}

	private Compra compraComGeladeiraEBatedeira() {
		Produto geladeira = geladeira();
		Produto batedeira = batedeira();
		
		List<Produto> produtos = new ArrayList<>();
		produtos.add(geladeira);
		produtos.add(batedeira);
		
		Compra compra = new Compra(15, produtos);
		return compra;
	}

	private Produto batedeira() {
		return new Produto("Batedeira", 200.00, "Batedeira de aço", 1588);
	}

	private Produto geladeira() {
		return new Produto("Geladeira", 2000.00, "Geladeira com freezer invertido", 1587);
	}
}
