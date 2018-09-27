package com.epam.htsa.restconvertor;

import com.epam.htsa.entity.Ticket;
import com.google.common.collect.Lists;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;

public class Ticket2PdfMessageConverter extends AbstractHttpMessageConverter<Object> {

	public Ticket2PdfMessageConverter() {
		super(new MediaType("application","pdf"));
	}

	@Override
	protected boolean supports(Class<?> clazz) {
		return clazz.isInstance(Ticket.class);
	}

	@Override
	protected Object readInternal(Class<? extends Object> clazz, HttpInputMessage inputMessage)
			throws IOException, HttpMessageNotReadableException {

		return super.read(clazz, inputMessage);
	}

	@Override
	protected void writeInternal(Object t, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {

		outputMessage.getHeaders().setAccept(Lists.newArrayList(new MediaType("application", "pdf")));
		writeInternal(t, outputMessage);
	}

	@Override
	protected boolean canRead(MediaType mediaType) {
		return false;
	}

	@Override
	public boolean canWrite(Class<?> clazz, MediaType mediaType) {
		return super.canWrite(clazz, mediaType);
	}

}
