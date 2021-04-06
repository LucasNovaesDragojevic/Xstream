package br.com.xstream;

import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.TreeMarshaller.CircularReferenceException;

public class CategoriaTest {
	
	@Test
	public void deveSerializarUmCiclo() {
		Categoria esporte = new Categoria(null, "esporte");
		Categoria futebol = new Categoria(esporte, "futebol");
		Categoria geral = new Categoria(futebol, "geral");
		esporte.setPai(geral);
		
		XStream xstream = new XStream();
		xstream.alias("categoria", Categoria.class);
		xstream.setMode(XStream.NO_REFERENCES);
		
		assertThrows(CircularReferenceException.class, () -> { 
			xstream.toXML(esporte); 
		});
	}
}
