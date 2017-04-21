package com.emma.finance.batch;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.ItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DefaultFieldSetFactory;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.emma.finance.domain.GiroTransaction;

@Configuration
@EnableBatchProcessing
public class ImportBatchConfiguration {
	
	private static final String PROPERTY_CSV_SOURCE_FILE_PATH = "csv.to.database.job.source.file.path";
	
	private static final String QUERY_INSERT_TA = "INSERT INTO "
												+ "		fin_transaction (account_type, account_fk, value_date, booking_date, amount, payment_details, "
												+ "			transaction_type, recipient_initiator, account_number, bank_code, mandate, creditor_id, customer) "
												+ "VALUES ('GIRO', 1, :valueDate, :bookingDate, :amount, :paymentDetails, :transactionType, "
												+ "			:recipientInitiator, :accountNumber, :bankCode, :mandate, :creditorId, :customer)";


	
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Autowired
    public DataSource dataSource;

    // tag::readerwriterprocessor[]
    @Bean
    public FlatFileItemReader<GiroTransaction> csvFileItemReader(Environment environment) throws UnexpectedInputException, ParseException, Exception {
    	//create FlatFileItemReader
        FlatFileItemReader<GiroTransaction> itemReader = new FlatFileItemReader<GiroTransaction>();
        itemReader.setResource(new ClassPathResource(environment.getRequiredProperty(PROPERTY_CSV_SOURCE_FILE_PATH)));
        itemReader.setLinesToSkip(7);
        
        //LineMapper
        LineMapper<GiroTransaction> lineMapper = this.createTransactionLineMapper();
        itemReader.setLineMapper(lineMapper);
        
        return itemReader;
    }
    
    private LineMapper<GiroTransaction> createTransactionLineMapper() {
    	DefaultLineMapper<GiroTransaction> lineMapper = new DefaultLineMapper<GiroTransaction>();
    	
    	LineTokenizer lineTokenizer = createTransactionLineTokenizer();
    	lineMapper.setLineTokenizer(lineTokenizer);
    	
    	lineMapper.setFieldSetMapper(new GiroFieldSetMapper());
    	
    	return lineMapper;
    	
    }
    
    private LineTokenizer createTransactionLineTokenizer() {
    	DelimitedLineTokenizer transactionLineTokenizer = new DelimitedLineTokenizer();
    	
    	DefaultFieldSetFactory fsf = new DefaultFieldSetFactory();		
		fsf.setDateFormat(new SimpleDateFormat("dd.MM.yyyy"));
		fsf.setNumberFormat(NumberFormat.getInstance(Locale.GERMANY));
		transactionLineTokenizer.setFieldSetFactory(fsf);
		
    	transactionLineTokenizer.setDelimiter(";");
    	transactionLineTokenizer.setQuoteCharacter(DelimitedLineTokenizer.DEFAULT_QUOTE_CHARACTER);
    	transactionLineTokenizer.setStrict(false);
    	transactionLineTokenizer.setNames(new String[] { "Buchungstag", "Wertstellung", "Buchungstext", 
        		"Auftraggeber", "Verwendungszweck", "Kontonummer", "BLZ", "Betrag", 
        		"Glaeubiger-ID", "Mandatsreferenz", "Kundenreferenz" });
    	return transactionLineTokenizer;
    }

    @Bean
    public ItemProcessor<GiroTransaction, GiroTransaction> processor() {
        return new GiroItemProcessor();
    }

    @Bean
    public ItemWriter<GiroTransaction> csvFileDatabaseItemWriter(DataSource dataSource, NamedParameterJdbcTemplate jdbcTemplate) {
    	
        JdbcBatchItemWriter<GiroTransaction> databaseItemWriter = new JdbcBatchItemWriter<>();
        databaseItemWriter.setDataSource(dataSource);
        databaseItemWriter.setJdbcTemplate(jdbcTemplate);

        databaseItemWriter.setSql(QUERY_INSERT_TA);

        ItemSqlParameterSourceProvider<GiroTransaction> sqlParameterSourceProvider = studentSqlParameterSourceProvider();
        databaseItemWriter.setItemSqlParameterSourceProvider(sqlParameterSourceProvider);

        return databaseItemWriter;
    }
    
    private ItemSqlParameterSourceProvider<GiroTransaction> studentSqlParameterSourceProvider() {
        return new BeanPropertyItemSqlParameterSourceProvider<>();
    }
    
    // end::readerwriterprocessor[]

    // tag::jobstep[]
    @Bean
    Step csvFileToDatabaseStep(ItemReader<GiroTransaction> csvFileItemReader, ItemProcessor<GiroTransaction, GiroTransaction> csvFileItemProcessor, ItemWriter<GiroTransaction> csvFileDatabaseItemWriter, StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("csvFileToDatabaseStep")
                .<GiroTransaction, GiroTransaction>chunk(1)
                .reader(csvFileItemReader)
                .processor(csvFileItemProcessor)
                .writer(csvFileDatabaseItemWriter)
                .build();
    }

    @Bean
    Job csvFileToDatabaseJob(JobCompletionNotificationListener listener, JobBuilderFactory jobBuilderFactory, @Qualifier("csvFileToDatabaseStep") Step csvTansactionStep) {
        return jobBuilderFactory.get("csvFileToDatabaseJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(csvTansactionStep)
                .end()
                .build();
    }
    // end::jobstep[]
}