package ar.com.alkemy.disney.models.response;

import java.util.*;

import ar.com.alkemy.disney.models.request.ErrorItemInfo;

public class GenericResponse {

    public boolean isOk;
    public int id;
    public String message;
    public List<ErrorItemInfo> errors = new ArrayList<>();
}
