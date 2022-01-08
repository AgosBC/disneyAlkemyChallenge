package ar.com.alkemy.disney.models.response;

import java.util.*;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import ar.com.alkemy.disney.models.request.ErrorItemInfo;

public class GenericResponse {

    public boolean isOk;
    public int id;
    public String message;

    @JsonInclude(Include.NON_NULL)
    public List<ErrorItemInfo> errors = new ArrayList<>();
}
