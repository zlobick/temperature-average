package ru.weather.aggregate.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Configuration;
import ru.weather.aggregate.service.DataExtractorImpl;
import ru.weather.aggregate.service.WeatherDataServiceImpl;
import ru.weather.aggregate.service.fetch.DataFetcherFirst;
import ru.weather.aggregate.service.fetch.DataFetcherSecond;
import ru.weather.aggregate.service.fetch.DataFetcherThird;
import ru.weather.aggregate.service.parse.DataParserFirst;
import ru.weather.aggregate.service.parse.DataParserSecond;
import ru.weather.aggregate.service.parse.DataParserThird;

import java.util.Random;

@Configuration
public class MultipleExtractorsSupplier implements BeanDefinitionRegistryPostProcessor {
    private final Random random = new Random();

    private String getBeanName(Class<?> clazz) {
        return clazz.getSimpleName().substring(0, 1).toLowerCase() + clazz.getSimpleName().substring(1);
    }

    /**
     * регистрируем 100 бинов, которые будут осуществлять "скачивание" и сохранение данных погоды
     * @param registry the bean definition registry used by the application context
     * @throws BeansException
     */
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {
        for (int i = 0; i < 100; i++) {
            BeanDefinitionBuilder bdb = BeanDefinitionBuilder.genericBeanDefinition(DataExtractorImpl.class);
            bdb.addConstructorArgValue(Integer.valueOf(i));
            switch (random.nextInt(3)) {
                case 0 -> {
                    bdb.addConstructorArgReference(getBeanName(DataFetcherFirst.class));
                    bdb.addConstructorArgReference(getBeanName(DataParserFirst.class));
                }
                case 1 -> {
                    bdb.addConstructorArgReference(getBeanName(DataFetcherSecond.class));
                    bdb.addConstructorArgReference(getBeanName(DataParserSecond.class));
                }
                case 2 -> {
                    bdb.addConstructorArgReference(getBeanName(DataFetcherThird.class));
                    bdb.addConstructorArgReference(getBeanName(DataParserThird.class));
                }
            }
            bdb.addConstructorArgReference(getBeanName(WeatherDataServiceImpl.class));

            registry.registerBeanDefinition("weather-extractor-"+i, bdb.getBeanDefinition());
        }
    }
}
