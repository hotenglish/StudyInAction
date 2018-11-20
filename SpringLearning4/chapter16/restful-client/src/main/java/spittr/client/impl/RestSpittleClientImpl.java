package spittr.client.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import spittr.Spittle;
import spittr.client.RestSpittleClient;
import spittr.client.exception.SpitterException;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@Component
public class RestSpittleClientImpl implements RestSpittleClient {

    private final static String url = "http://localhost:8080/spittles";

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Spittle fetchSpittle(String id) {
        return restTemplate.getForObject(url + "/{id}", Spittle.class, id);
    }

    @Override
    public Spittle fetchSpittleM(String id) {
        Map<String, String> uriVariables = new HashMap<String, String>();
        uriVariables.put("id", id);
        return restTemplate.getForObject(url+"/{id}", Spittle.class, uriVariables);
    }

    @Override
    public void putSpittle(Spittle spittle) throws SpitterException {
        String newUrl = url + "/" + spittle.getId();
        restTemplate.put(URI.create(newUrl), spittle);
    }

    @Override
    public void putSpittleV(Spittle spittle) throws SpitterException {
        String newUrl = url + "/{id}";
        restTemplate.put(newUrl, spittle, spittle.getId());
    }

    @Override
    public void putSpittleM(Spittle spittle) throws SpitterException {
        String newUrl = url + "/{id}";
        Map<String, String> params = new HashMap<String, String>();
        params.put("id", String.valueOf(spittle.getId()));
        restTemplate.put(newUrl, spittle, spittle.getId());
    }

    @Override
    public void deleteSpittle(Long id) throws SpitterException {
        String newUrl = url + "/";
        restTemplate.delete(URI.create(newUrl + id));
    }

    @Override
    public void deleteSpittleV(Long id) throws SpitterException {
        String newUrl = url + "/{id}";
        restTemplate.delete(newUrl, id);
    }

    @Override
    public Spittle postSpittle(Spittle spittle) throws SpitterException {
        ResponseEntity<Spittle> responseEntity =
                restTemplate.postForEntity(url, spittle, Spittle.class);
        return responseEntity.getBody();
    }

    @Override
    public ResponseEntity<Spittle> postSpittleThenGetSpittleAndLocation(Spittle spittle) throws SpitterException {
        ResponseEntity<Spittle> responseEntity = restTemplate.postForEntity(url, spittle, Spittle.class);
        System.out.println(responseEntity.getBody());
        URI url = responseEntity.getHeaders().getLocation();
        System.out.println(url.toString());
        return responseEntity;
    }

    @Override
    public String postSpittleLocation(Spittle spittle) {
        return restTemplate.postForLocation(url, spittle).toString();
    }

}
