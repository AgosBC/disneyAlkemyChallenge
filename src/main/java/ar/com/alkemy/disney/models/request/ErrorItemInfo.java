package ar.com.alkemy.disney.models.request;

public class ErrorItemInfo {

    public ErrorItemInfo(String field, String message) {
        this.field = field;
        this.message = message;
    }

    public String field;
    public String message;
    
}
