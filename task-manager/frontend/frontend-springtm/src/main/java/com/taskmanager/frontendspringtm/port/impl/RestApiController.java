    package com.taskmanager.frontendspringtm.port.impl;

    import com.google.gson.Gson;
    import com.taskmanager.frontend.usecases.port.RestService;
    import org.springframework.core.ParameterizedTypeReference;
    import org.springframework.http.*;
    import org.springframework.web.client.RestTemplate;

    import java.util.List;

    public class RestApiController<T> implements RestService<T> {

        private static final String BASE_ENDPOINT = "http://localhost:8081/api";

        private String getEndpoint(final String resource) {
            return BASE_ENDPOINT + resource;
        }

        @Override
        public List<T> get(String resource) {
            final String endpoint = getEndpoint(resource);
            final RestTemplate restTemplate = new RestTemplate();
            final HttpEntity<String> httpEntity = new HttpEntity<>("");

            ResponseEntity<List<T>> responseEntity = restTemplate.exchange(endpoint, HttpMethod.GET, httpEntity,
                    new ParameterizedTypeReference<List<T>>() {
                    });

            List<T> body = responseEntity.getBody();

            return body;
        }

        @Override
        public List<T> get(String resource, T entity) {
            try {
                final String endpoint = getEndpoint(resource);
                final RestTemplate restTemplate = new RestTemplate();
                final HttpEntity<T> httpEntity = new HttpEntity<>(entity);

                ResponseEntity<List<T>> responseEntity = restTemplate.exchange(endpoint,
                        HttpMethod.POST,
                        httpEntity,
                        new ParameterizedTypeReference<List<T>>() {
                        });

                if (responseEntity.getStatusCode() == HttpStatus.OK) {
                    final List<T> bodyResponse = responseEntity.getBody();
                    if(bodyResponse.size() < 1){
                        return null;
                    }
                    return bodyResponse;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        public int post(String resource, T entity) {
            try {
                final String endpoint = getEndpoint(resource);
                final RestTemplate restTemplate = new RestTemplate();
                final HttpEntity<T> httpEntity = new HttpEntity<>(entity);

                final ResponseEntity<String> responseEntity = restTemplate.exchange(endpoint,
                        HttpMethod.POST,
                        httpEntity,
                        String.class);

                if (responseEntity.getStatusCode() == HttpStatus.OK) {
                    final String bodyResponse = responseEntity.getBody();
                    return Integer.parseInt(bodyResponse);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return -1;
        }

        @Override
        public boolean put(String resource, T entity) {
            try {
                final String endpoint = getEndpoint(resource);
                final RestTemplate restTemplate = new RestTemplate();
                final HttpEntity<T> httpEntity = new HttpEntity<>(entity);

                final ResponseEntity<Boolean> responseEntity = restTemplate.exchange(endpoint,
                        HttpMethod.PUT,
                        httpEntity,
                        Boolean.class);

    //            return responseEntity.getStatusCode() == HttpStatus.OK;
                if (responseEntity.getStatusCode() == HttpStatus.OK) {
                    final boolean bodyResponse = responseEntity.getBody();
                    return bodyResponse;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        public boolean patch(String resource, T entity) {
            try {
                final String endpoint = getEndpoint(resource);
                final RestTemplate restTemplate = new RestTemplate();

                // Cria os cabeçalhos
                HttpHeaders headers = new HttpHeaders();
                headers.set("X-HTTP-Method-Override", "PATCH");  // Definindo o método PATCH através do cabeçalho
                HttpEntity<T> httpEntity = new HttpEntity<>(entity, headers); // Passa o cabeçalho junto com o corpo da requisição

                // Faz a requisição POST, mas o servidor vai tratar como PATCH
                final ResponseEntity<Boolean> responseEntity = restTemplate.exchange(endpoint,
                        HttpMethod.POST, // Usando POST no lugar de PATCH
                        httpEntity,
                        Boolean.class);

                if (responseEntity.getStatusCode() == HttpStatus.OK) {
                    final boolean bodyResponse = responseEntity.getBody();
                    return bodyResponse;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        public boolean delete(String resource) {
            try {
                final String endpoint = getEndpoint(resource);
                final RestTemplate restTemplate = new RestTemplate();
                final HttpEntity<String> httpEntity = new HttpEntity<>("");

                final ResponseEntity<String> responseEntity = restTemplate.exchange(endpoint,
                        HttpMethod.DELETE,
                        httpEntity,
                        String.class);

                return responseEntity.getStatusCode() == HttpStatus.OK;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        public T getById(String resource, Class<T> clazz) {
            try {
                final String endpoint = getEndpoint(resource);
                final RestTemplate restTemplate = new RestTemplate();
                final HttpEntity<String> httpEntity = new HttpEntity<>("");
                final ResponseEntity<String> responseEntity = restTemplate.exchange(endpoint,
                        HttpMethod.GET,
                        httpEntity,
                        String.class);
                if (responseEntity.getStatusCode() == HttpStatus.OK){
                    final Gson gson = new Gson();

                    final T responseObject = gson.fromJson(responseEntity.getBody(),
                            clazz);
                    return responseObject;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
