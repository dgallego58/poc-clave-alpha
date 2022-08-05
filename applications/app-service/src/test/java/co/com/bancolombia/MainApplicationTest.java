package co.com.bancolombia;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.function.Supplier;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class MainApplicationTest {


    @Autowired
    ConfigurableApplicationContext applicationContext;

    @Test
    void workerStarter() {
        applicationContext.start();
        boolean isCtxRunning = applicationContext.isRunning();
        assertThat(isCtxRunning).isTrue();
        applicationContext.registerShutdownHook();
    }
}
