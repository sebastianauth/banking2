package com.emma.finance.batch;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.emma.finance.domain.GiroTransaction;

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
        itemReader.setResource(new ClassPathResource("import/1019838471.csv"));
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
        lineMapper.setFieldSetMapper(new GiroFieldSetMapper());
        
        itemReader.setLineMapper(lineMapper);
//        itemReader.open(new ExecutionContext());
//        GiroTransaction transaction = itemReader.read();
        
        return itemReader;
    }

    @Bean
    public GiroItemProcessor processor() {
        return new GiroItemProcessor();
    }

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
    public Job importUserJob(JobCompletionNotificationListener listener) throws UnexpectedInputException, ParseException, Exception {
        return jobBuilderFactory.get("importTransactionJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() throws UnexpectedInputException, ParseException, Exception {
        return stepBuilderFactory.get("step1")
                .<GiroTransaction, GiroTransaction> chunk(10)
                .reader(this.reader())
                .processor(this.processor())
                .writer(writer())
                .build();
    }
    // end::jobstep[]
}