package org.springframework.samples.mvc.convert;

import java.text.ParseException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

import org.springframework.format.AnnotationFormatterFactory;
import org.springframework.format.Formatter;
import org.springframework.format.Parser;
import org.springframework.format.Printer;

public class MaskFormatAnnotationFormatterFactory implements AnnotationFormatterFactory<MaskFormat> {

	public Set<Class<?>> getFieldTypes() {
		Set<Class<?>> fieldTypes = new HashSet<Class<?>>(1, 1);
		fieldTypes.add(String.class);
		return fieldTypes;
	}

	public Parser<?> getParser(MaskFormat annotation, Class<?> fieldType) {
		return new MaskFormatter(annotation.value());
	}

	public Printer<?> getPrinter(MaskFormat annotation, Class<?> fieldType) {
		return new MaskFormatter(annotation.value());
	}
	
	/**
	 * A formatter that applies a mask to format and parse strings.
	 * This formatter uses the javax.swing.text.MaskFormatter class to apply the mask.
	 */
	/**
	 * A formatter that applies a mask to format and parse strings.
	 */
	private static class MaskFormatter implements Formatter<String> {

		private javax.swing.text.MaskFormatter delegate;

		/**
		 * Constructs a new MaskFormatter with the specified mask.
		 *
		 * @param mask the mask to be applied
		 * @throws IllegalStateException if the mask could not be parsed
		 */
		public MaskFormatter(String mask) {
			try {
				this.delegate = new javax.swing.text.MaskFormatter(mask);
				this.delegate.setValueContainsLiteralCharacters(false);
			} catch (ParseException e) {
				throw new IllegalStateException("Mask could not be parsed " + mask, e);
			}
		}

		/**
		 * Formats the specified object into a string representation.
		 *
		 * @param object the object to be formatted
		 * @param locale the locale to be used for formatting
		 * @return the formatted string
		 */
		public String print(String object, Locale locale) {
			try {
				return delegate.valueToString(object);
			} catch (ParseException e) {
				throw new IllegalArgumentException("Unable to print using mask " + delegate.getMask(), e);
			}
		}
s
		/**
		 * Parses the specified text into an object.
		 *
		 * @param text the text to be parsed
		 * @param locale the locale to be used for parsing
		 * @return the parsed object
		 * @throws ParseException if the text cannot be parsed
		 */
		public String parse(String text, Locale locale) throws ParseException {
			return (String) delegate.stringToValue(text);
		}

	}

}
