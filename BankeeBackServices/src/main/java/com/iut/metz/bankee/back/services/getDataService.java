package com.iut.metz.bankee.back.services;

import static com.iut.metz.bankee.back.services.util.ServiceConstante.*;
import static com.iut.metz.bankee.back.services.util.ServiceUtils.*;

import javax.ws.rs.core.*;

import com.iut.metz.bankee.back.services.process.GetDataProcess;

public class getDataService {


  public Response getData(HttpHeaders headers, String headerAVerifier, ServiceSupplier supplier) {
    Object res = new GetDataProcess().doIt(headers, headerAVerifier, supplier);
    if (res == null) {
      return getNotAuthorized();
    }
    if (res instanceof String) {
      if(res.equals(NON_AUTORISE)) {
        return getNotAuthorized();
      } else {
        return badRequest(res);
      }
    }
    if (res instanceof Exception) {
      return getError((Exception)res);
    }
    return getResponse(res);
  }
}
