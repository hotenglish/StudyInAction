package spittr.client;

import org.springframework.http.ResponseEntity;
import spittr.client.exception.*;
import spittr.Spittle;

public interface RestSpittleClient {

    Spittle fetchSpittle(String id);

    Spittle fetchSpittleM(String id);

    void putSpittle(Spittle spittle) throws SpitterException;

    void putSpittleV(Spittle spittle) throws SpitterException;

    void putSpittleM(Spittle spittle) throws SpitterException;

    void deleteSpittle(Long id) throws SpitterException;

    void deleteSpittleV(Long id) throws SpitterException;

    Spittle postSpittle(Spittle spittle) throws SpitterException;

    ResponseEntity<Spittle> postSpittleThenGetSpittleAndLocation(Spittle spittle) throws SpitterException;

    String postSpittleLocation(Spittle spittle);

    Spittle getSpittleByExchange(Spittle spittle);

}
