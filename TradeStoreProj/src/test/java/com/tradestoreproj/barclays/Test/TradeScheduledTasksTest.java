package com.tradestoreproj.barclays.Test;

import static org.awaitility.Awaitility.await;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;

import org.awaitility.Duration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.tradestoreproj.barclays.DemoApplication;
import com.tradestoreproj.barclays.service.TradeScheduledTasks;



@SpringJUnitConfig(DemoApplication.class)
@ExtendWith(SpringExtension.class)
@SpringBootTest
 class TradeScheduledTasksTest {

    @SpyBean
    private TradeScheduledTasks TradeScheduledTasks;

    @Test
    @DisplayName("Scheduler Method to Test Expiry of Flag")
     void whenWaitOneMinute_thenScheduledIsCalledAtLeastOneTimes() {
        await()
                .atMost(Duration.ONE_MINUTE)
                .untilAsserted(() -> verify(TradeScheduledTasks,atLeast(1)).updateExpiryFlagOfTrade());
    }

}