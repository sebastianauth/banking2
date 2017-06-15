package com.emma.finance.batch;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("batchtest")
//@SpringApplicationConfiguration(classes = { ImportBatchConfiguration.class ,TestBatchConfiguration.class})
public class ImportBatchIT {

}
