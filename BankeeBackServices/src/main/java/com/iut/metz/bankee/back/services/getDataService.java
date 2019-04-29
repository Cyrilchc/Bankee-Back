package com.iut.metz.bankee.back.services;

import static com.iut.metz.bankee.back.services.util.ServiceConstante.*;
import static com.iut.metz.bankee.back.services.util.ServiceUtils.*;

import javax.ws.rs.core.*;

import com.iut.metz.bankee.back.metier.manager.CompteManager;
import com.iut.metz.bankee.back.services.process.GetDataProcess;

public class getDataService {

    public Response getData(String num, ServiceSupplier supplier) {
        Object object = new GetDataProcess().doIt(num, supplier);
        if (object instanceof String) {
            return badRequest(NUMERO_NON_VALIDE);
        }
        if (object instanceof Exception) {
            return getError((Exception) object);
        }
        return getResponse(object);
    }
}
