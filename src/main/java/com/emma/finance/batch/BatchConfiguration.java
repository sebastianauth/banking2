package com.emma.finance.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.emma.finance.domain.GiroTransaction;
import com.emma.finance.domain.Transaction;

import antlr.TokenStreamRewriteEngine;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    // tag::readerwriterprocessor[]
    @Bean
    public FlatFileItemReader<GiroTransaction> reader() throws UnexpectedInputException, ParseException, Exception {
    	//create FlatFileItemReader
        FlatFileItemReader<GiroTransaction> itemReader = new FlatFileItemReader<GiroTransaction>();
        itemReader.setResource(new ClassPathResource("4748________1319.csv"));
        int linesToSkip = 7;
        itemReader.setLinesToSkip(linesToSkip);
        
        //LineMapper
        DefaultLineMapper<GiroTransaction> lineMapper = new DefaultLineMapper<GiroTransaction>();
        
        //LineTokenizer
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames(new String[] { "Buchungstag", "Wertstellung", "Buchungstext", 
        		"Auftraggeber", "Verwendungszweck", "Kontonummer", "BLZ", "Betrag", 
        		"Glaeubiger-ID", "Mandatsreferenz", "Kundenreferenz" });
        tokenizer.setDelimiter(";");
        tokenizer.setQuoteCharacter(DelimitedLineTokenizer.DEFAULT_QUOTE_CHARACTER);
        
        
        lineMapper.setLineTokenizer(tokenizer);
//        lineMapper.setFieldSetMapper(new GiroFieldSetMapper());
        itemReader.setLineMapper(lineMapper);
        itemReader.open(new ExecutionContext());
        GiroTransaction transaction = itemReader.read();
        
        return itemReader;
    }

//    @Bean
//    public PersonItemProcessor processor() {
//        return new PersonItemProcessor();
//    }

//    @Bean
//    public JdbcBatchItemWriter<TransactionStaging> writer() {
//        JdbcBatchItemWriter<TransactionStaging> writer = new JdbcBatchItemWriter<TransactionStaging>();
//        writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Person>());
//        writer.setSql("INSERT INTO people (first_name, last_name) VALUES (:firstName, :lastName)");
//        writer.setDataSource(dataSource);
//        return writer;
//    }
    
    @Bean
    public JpaItemWriter<GiroTransaction> writer(){
    	
    	
    	
		return null;
    	
    }
    
    
    
    // end::readerwriterprocessor[]

    // tag::jobstep[]
    @Bean
    public Job importUserJob(JobCompletionNotificationListener listener) {
        return jobBuilderFactory.get("importTransactionJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Transaction, Transaction> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .build();
    }
    // end::jobstep[]
}