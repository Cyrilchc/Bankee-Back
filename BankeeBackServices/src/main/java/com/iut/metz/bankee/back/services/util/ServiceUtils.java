package com.iut.metz.bankee.back.services.util;

import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

import java.nio.charset.StandardCharsets;
import java.util.*;

import javax.ws.rs.core.*;

public class ServiceUtils {
  private ServiceUtils(){}

  public static Response getResponse(Object object) {
    return Response.ok(object).header("Access-Control-Allow-Origin", "*").build();
  }

  public static Response getNotAuthorized(){
    return Response.status(UNAUTHORIZED).build();
  }

  public static Response getError(Exception e) {
    e.printStackTrace();
    return Response.serverError().build();
  }

  public static Response badRequest(Object o) {
    return Response.status(Response.Status.BAD_REQUEST).entity(o).build();
  }

  public static boolean isAllows(HttpHeaders headers) {
    List<String> authHeaders = headers.getRequestHeader(HttpHeaders.AUTHORIZATION);
    if (authHeaders != null) {
      for (String authorization : authHeaders) {
        try {
          String base64Credentials = authorization.substring("Basic".length()).trim();
          byte[] credDecoded = Base64.getDecoder().decode(base64Credentials);
          String credentials = new String(credDecoded, StandardCharsets.UTF_8);
          final String[] values = credentials.split(":", 2);
          if(values[0].equals("admin") && values[1].equals("admin")) {
            return true;
          }
        } catch (Exception ignore){
          //do nothing
        }
      }
    }
    return false;
  }
}
