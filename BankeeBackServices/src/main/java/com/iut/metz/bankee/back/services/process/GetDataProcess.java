package com.iut.metz.bankee.back.services.process;

import static com.iut.metz.bankee.back.services.util.ServiceConstante.*;
import static com.iut.metz.bankee.back.services.util.ServiceUtils.*;

import java.util.List;

import javax.ws.rs.core.HttpHeaders;

import org.apache.log4j.Logger;

import com.iut.metz.bankee.back.services.*;

public class GetDataProcess {

  private Logger log = Logger.getLogger(getDataService.class);
  public Object doIt(HttpHeaders headers, String headerAVerifier, ServiceSupplier supplier) {
    log.info("connection");
    if (isAllows(headers)) {
      log.info("personne autorisé");
      try {
        List<String> dataNum = headers.getRequestHeader(headerAVerifier);
        if (dataNum == null || dataNum.isEmpty()) {
          log.info("pas de numéro de compte");
          return NUMERO_NON_VALIDE;
        }
        Object object = supplier.get(dataNum.get(0));
        if (object == null) {
          log.info("mauvais numéro de compte");
          return NUMERO_NON_VALIDE;
        }
        log.debug(object.getClass());
        return object;
      } catch (Exception e) {
        log.info(e);
        return e;
      }
    } else {
      log.info("personne non autorisé");
      return NON_AUTORISE;
    }
  }
}
