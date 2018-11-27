package spittr.domain;

import java.io.Serializable;
import java.util.Date;

public class Spittle implements Serializable {

    private static final long serialVersionUID = 7647914826341553968L;

    private final Long id;
    private final Spitter spitter;
    private final String message;
    private final Date postedTime;

    public Spittle(Long id, Spitter spitter, String message, Date postedTime) {
        this.id = id;
        this.spitter = spitter;
        this.message = message;
        this.postedTime = postedTime;
    }

    public Long getId() {
        return id;
    }

    public Spitter getSpitter() {
        return spitter;
    }

    public String getMessage() {
        return message;
    }

    public Date getPostedTime() {
        return postedTime;
    }

    @Override
    public String toString() {
        return "Spittle{" +
                "id=" + id +
                ", spitter=" + spitter +
                ", message='" + message + '\'' +
                ", postedTime=" + postedTime +
                '}';
    }
}
