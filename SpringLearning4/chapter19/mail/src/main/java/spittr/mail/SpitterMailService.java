package spittr.mail;

import spittr.domain.Spittle;

public interface SpitterMailService {

    void sendSimpleSpittleEmail(String to, Spittle spittle);

    void sendSpittleEmailWithAttachment(String to, Spittle spittle) throws Exception;

}
