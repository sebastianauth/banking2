package com.emma.finance.batch;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.AbstractLineTokenizer;
import org.springframework.batch.item.file.transform.DefaultFieldSetFactory;
import org.springframework.batch.item.file.transform.LineTokenizer;

public class LocalizedLineMapper <T> extends DefaultLineMapper<T> {
	
	private AbstractLineTokenizer tokenizer;
	
	@Override
	public void setLineTokenizer(LineTokenizer tokenizer) {
		super.setLineTokenizer(tokenizer);
		if(tokenizer instanceof AbstractLineTokenizer){
			this.tokenizer = (AbstractLineTokenizer)tokenizer;
		}else{
			throw new IllegalArgumentException("lineMapper must be a class or subclass of "
					+LocalizedLineMapper.class.getName());
		}
	}
	
	@Override
	public void afterPropertiesSet() {
		super.afterPropertiesSet();
		DefaultFieldSetFactory fsf = new DefaultFieldSetFactory();		

		fsf.setDateFormat(new SimpleDateFormat("dd.MM.yyyy"));
		
		fsf.setNumberFormat(NumberFormat.getInstance(Locale.GERMANY));

		tokenizer.setFieldSetFactory(fsf);
	}	
}