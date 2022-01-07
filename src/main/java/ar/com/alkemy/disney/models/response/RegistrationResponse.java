package ar.com.alkemy.disney.models.response;

import java.util.ArrayList;
import java.util.List;

import ar.com.alkemy.disney.models.request.ErrorItemInfo;

public class RegistrationResponse {

    public boolean isOk = false;
    public String message = "";
    public Integer userId;
    public List<ErrorItemInfo> errors = new ArrayList<>();
    
}
