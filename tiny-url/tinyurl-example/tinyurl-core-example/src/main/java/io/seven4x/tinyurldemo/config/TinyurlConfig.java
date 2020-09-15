package io.seven4x.tinyurldemo.config;

import io.seven4x.repository.InMemoryUrlStoreRepository;
import io.seven4x.service.TinyUrlConfig;
import io.seven4x.service.TinyUrlService;
import io.seven4x.service.UrlStore;
import io.seven4x.service.UrlTransformStrategy;
import io.seven4x.service.impl.DefaultUrlTransformStrategy;
import io.seven4x.service.impl.TinUrlServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TinyurlConfig {

    /**
     * todo 改成 builder
     *
     * @return
     */
    @Bean
    public TinyUrlService tinyUrlService(UrlTransformStrategy urlTransformStrategy,
                                         UrlStore urlStore,
                                         TinyUrlConfig tinyUrlConfig) {
        TinUrlServiceImpl service = new TinUrlServiceImpl();
        service.setConfig(tinyUrlConfig);
        service.setUrlStore(urlStore);
        service.setUrlTransformStrategy(urlTransformStrategy);
        return service;
    }

    @Bean
    public UrlTransformStrategy urlTransformStrategy() {
        return new DefaultUrlTransformStrategy();
    }

    @Bean
    public UrlStore urlStore() {
        return new InMemoryUrlStoreRepository();
    }

    @Bean
    public TinyUrlConfig tinyUrlConfig() {
        return new TinyUrlConfig();
    }
}
