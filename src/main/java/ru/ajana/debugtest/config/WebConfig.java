package ru.ajana.debugtest.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.nio.charset.Charset;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Конфишурация веб-приложения.
 *
 * @author Andrey Kharintsev on 08.06.2019
 */
@EnableWebMvc
@Configuration
public class WebConfig implements WebMvcConfigurer {

  /**
   * Настройка конверторов.
   */
  @Override
  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
    ObjectMapper objectMapper = new ObjectMapper();
    StringHttpMessageConverter stringConverter = new StringHttpMessageConverter(
        Charset.forName("UTF-8"));
    MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(
        objectMapper);
    converters.add(stringConverter);
    converters.add(converter);
  }
}
