package ng.dominic.bb.util.diceImpl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

@Configuration
public class DiceConfig {

    @Bean
    @Primary
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public D d(@Value("6") int faces) {
        return new D(faces);
    }

    @Bean
    public D d6() {
        return new D(6);
    }

    @Bean
    public D d16() {
        return new D(16);
    }

    @Bean
    public D d100() {
        return new D(100);
    }

    @Bean
    public D d25() {
        return new D(25);
    }
}
