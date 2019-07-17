package ru.ajana.debugtest;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.JsonbHttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

/**
 * Абстрактный интеграционных тестов.
 *
 * @author Andrey Kharintsev on 09.06.2019
 */
@RunWith(SpringRunner.class)
@TestPropertySource(locations = {"classpath:application-test.properties",
    "classpath:datasource-test.properties"})
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@SqlConfig(encoding = "UTF-8")
@ContextConfiguration(classes = {Application.class})
public abstract class AbstractIntegrationTest extends AbstractTest {

  /**
   * Логгер.
   */
  protected final Logger LOG = LoggerFactory.getLogger(getClass());

  /**
   * Таймаут на чтение данных REST-клиента.
   */
  private static final int READ_TIMEOUT = 5000;

  /**
   * REST- клиент для тестирования.
   */
  @Autowired
  public TestRestTemplate restClient;

  /**
   * Номер свободного порта локального сервера, который генериться RANDOM.
   */
  @LocalServerPort
  private int serverPort;


  public int getServerPort() {
    return serverPort;
  }

  /**
   * Возвращает URI к API сервиса.
   */
  protected String getBaseURI() {
    return "http://localhost:" + getServerPort() + "/debugtest/api/";
  }

  /**
   * Возвращает Http-клиент.
   *
   * @return Http-клиент
   */
  protected TestRestTemplate getClient() {
    return restClient;
  }


  /**
   * Возвращает заголовки отправляемые по умолчанию.
   *
   * @return список HTTP-заголовков
   */
  protected HttpHeaders getDefaultHttpHeaders() {
    HttpHeaders headers = new HttpHeaders();
    headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
    headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
    return headers;
  }

  /**
   * Создаёт запрос с заголовками по умолчанию.
   *
   * @return сущность HTTP-запроса
   */
  protected HttpEntity buildRequest() {
    return new HttpEntity<>(getDefaultHttpHeaders());
  }

  /**
   * Создаёт запрос с параметрами.
   *
   * @param params параметры, тело запроса
   * @return сущность HTTP-запроса
   */
  protected HttpEntity buildRequestWithParameters(Object params) {
    return new HttpEntity<>(params, getDefaultHttpHeaders());
  }

  /**
   * Выполняет HTTP-запрос методом GET.
   *
   * @param path относительный путь от базового URI
   * @param responseType возвращаемый класс данных
   * @param urlVariables передаваемые параметры
   * @param <T> тип возвращаемых данных
   * @return результат запроса
   */
  protected <T> ResponseEntity<T> get(String path,
      Class<T> responseType,
      Object... urlVariables) {
    return getClient()
        .exchange(getBaseURI() + path, HttpMethod.GET, buildRequest(), responseType,
            urlVariables);
  }

  /**
   * Выполняет Http-запрос методом GET.
   *
   * @param path относительный путь по отношению к базовому URI
   * @param httpEntity отправляемая сущность
   * @param responseType возвращаемый класс данных
   * @param <T> тип возвращаемых данных
   * @return результат запроса
   */
  protected <T> ResponseEntity<T> get(String path,
      HttpEntity httpEntity,
      Class<T> responseType) {
    return getClient().exchange(getBaseURI() + path, HttpMethod.GET, httpEntity, responseType);
  }

  /**
   * Выполняет HTTP-запрос методом GET.
   *
   * @param path относительный путь от базового URI
   * @param responseType возвращаемый класс данных
   * @return результат запроса
   */
  protected <T> ResponseEntity<T> get(String path,
      ParameterizedTypeReference<T> responseType,
      Object... urlVariables) {
    return getClient()
        .exchange(getBaseURI() + path, HttpMethod.GET, buildRequest(), responseType,
            urlVariables);
  }

  /**
   * Выполняет Http-запрос методом POST.
   *
   * @param path относительный путь по отношению к базовому URI
   * @param httpEntity отправляемая сущность
   * @param responseType возвращаемый класс данных
   * @param <T> тип возвращаемых данных
   * @return результат запроса
   */
  protected <T> ResponseEntity<T> post(String path,
      HttpEntity httpEntity,
      Class<T> responseType) {
    return getClient().exchange(getBaseURI() + path, HttpMethod.POST, httpEntity, responseType);
  }

  /**
   * Http-запрос отправляемы методом POST.
   *
   * @param path path относительный путь по отношению к базовому URI
   * @param requestEntity Http-сущность запроса
   * @param responseType возвращаемый класс данных
   * @param urlVariables передаваемые параметры
   * @return результат запроса
   */
  protected <T> ResponseEntity<T> post(String path, Object requestEntity,
      Class<T> responseType,
      Object... urlVariables) {
    return send(HttpMethod.POST, path, requestEntity, responseType, urlVariables);
  }

  /**
   * Http-запрос отправляемы методом POST.
   *
   * @param path path относительный путь по отношению к базовому URI
   * @param requestEntity Http-сущность запроса
   * @param responseType возвращаемый класс данных
   * @param urlVariables передаваемые параметры
   * @return результат запроса
   */
  protected <T> ResponseEntity<T> post(String path, Object requestEntity,
      ParameterizedTypeReference<T> responseType,
      Object... urlVariables) {
    return send(HttpMethod.POST, path, requestEntity, responseType, urlVariables);
  }

  /**
   * Http-запрос отправляемы методом PUT.
   *
   * @param path path относительный путь по отношению к базовому URI
   * @param requestEntity Http-сущность запроса
   * @param responseType возвращаемый класс данных
   * @param urlVariables передаваемые параметры
   * @return результат запроса
   */
  protected <T> ResponseEntity<T> put(String path, Object requestEntity,
      Class<T> responseType,
      Object... urlVariables) {
    return send(HttpMethod.PUT, path, requestEntity, responseType, urlVariables);
  }

  /**
   * Http-запрос отправляемы методом PUT.
   *
   * @param path path относительный путь по отношению к базовому URI
   * @param requestEntity Http-сущность запроса
   * @param responseType возвращаемый класс данных
   * @param urlVariables передаваемые параметры
   * @return результат запроса
   */
  protected <T> ResponseEntity<T> put(String path, Object requestEntity,
      ParameterizedTypeReference<T> responseType,
      Object... urlVariables) {
    return send(HttpMethod.PUT, path, requestEntity, responseType, urlVariables);
  }

  /**
   * Http-запрос отправляемы методом DELETE.
   *
   * @param path path относительный путь по отношению к базовому URI
   * @param requestEntity Http-сущность запроса
   * @param responseType возвращаемый класс данных
   * @param urlVariables передаваемые параметры
   * @return результат запроса
   */
  protected <T> ResponseEntity<T> delete(String path, Object requestEntity,
      ParameterizedTypeReference<T> responseType,
      Object... urlVariables) {
    return send(HttpMethod.DELETE, path, requestEntity, responseType, urlVariables);
  }

  /**
   * Общий метод отправляющий Http-запрос.
   *
   * @param method Http-метод
   * @param path относительный путь
   * @param requestEntity Http-сущность запроса
   * @param responseType возвращаемый класс данных
   * @param urlVariables передаваемые параметры
   * @param <T> возвращаемый тип данных
   * @return результат запроса
   */
  protected <T> ResponseEntity<T> send(HttpMethod method, String path, Object requestEntity,
      Class<T> responseType,
      Object... urlVariables) {
    return getClient()
        .exchange(getBaseURI() + path, method, buildRequestWithParameters(requestEntity),
            responseType, urlVariables);
  }

  /**
   * Общий метод отправляющий Http-запрос.
   *
   * @param method Http-метод
   * @param path относительный путь
   * @param requestEntity Http-сущность запроса
   * @param responseType возвращаемый класс данных
   * @param urlVariables передаваемые параметры
   * @param <T> возвращаемый тип данных
   * @return результат запроса
   */
  protected <T> ResponseEntity<T> send(HttpMethod method, String path, Object requestEntity,
      ParameterizedTypeReference<T> responseType,
      Object... urlVariables) {
    return getClient()
        .exchange(getBaseURI() + path, method, buildRequestWithParameters(requestEntity),
            responseType, urlVariables);
  }

  /**
   * Конфигурация REST-клиента
   */
  @Bean
  protected RestTemplate restTemplate() {
    final HttpClientBuilder clientBuilder = HttpClientBuilder.create();
    final HttpClient httpClient = clientBuilder.build();
    final HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory(
        httpClient);
    requestFactory.setReadTimeout(READ_TIMEOUT);
    RestTemplate restTemplate = new RestTemplate(requestFactory);
    List<HttpMessageConverter<?>> converters = new ArrayList<>();
    converters.add(new JsonbHttpMessageConverter());
    restTemplate.setMessageConverters(converters);
    return restTemplate;
  }
}
