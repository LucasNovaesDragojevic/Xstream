package br.com.xstream;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.SingleValueConverter;

public class PrecoSimplesConverter implements SingleValueConverter {

	@Override
	public boolean canConvert(Class type) {
		return type.isAssignableFrom(Double.class);
	}

	@Override
	public Object fromString(String valor) {
		NumberFormat formatter = getFormatter();
		try {
			return formatter.parse(valor);
		} catch (ParseException e) {
			throw new ConversionException("Não consegui converter " + valor, e);
		}
	}

	@Override
	public String toString(Object valor) {
		return getFormatter().format(valor);
	}

	private NumberFormat getFormatter() {
		Locale brasil = new Locale("pt", "br");
		NumberFormat formatter = NumberFormat.getCurrencyInstance(brasil);
		return formatter;
	}
}
