package br.com.escritorioDeVaquejada.vqr.vo;

import java.util.Date;
import java.util.Objects;

public class ResponseExceptionVo{
    private Date timestamp;
    private String details;
    private String message;

    public ResponseExceptionVo(Date timestamp, String details, String message) {
        this.timestamp = timestamp;
        this.details = details;
        this.message = message;
    }

    public ResponseExceptionVo() {
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ResponseExceptionVo that = (ResponseExceptionVo) object;
        return Objects.equals(timestamp, that.timestamp) && Objects.equals(details, that.details) && Objects.equals(message, that.message);
    }

    @Override
    public int hashCode() {
        return Objects.hash(timestamp, details, message);
    }
}

