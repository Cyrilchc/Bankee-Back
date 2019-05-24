package com.iut.metz.bankee.back.services.process;

import static com.iut.metz.bankee.back.services.util.ServiceConstante.*;

import org.apache.log4j.Logger;

import com.iut.metz.bankee.back.services.*;

public class GetDataProcess {

    private Logger log = Logger.getLogger(getDataService.class);

    public Object doIt(String num, ServiceSupplier supplier) {
        log.info("connection");
        try {
            if (num == null || num.equals("")) {
                log.info("pas de numéro de compte");
                return NUMERO_NON_VALIDE;
            }
            Object object = supplier.get(num);
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
    }
}
