package com.iut.metz.bankee.back.metier.process;

import static com.iut.metz.bankee.back.metier.objet.exception.utils.ObjetExceptionUtils.OBJET_NULL;

import org.apache.log4j.Logger;

import com.iut.metz.bankee.back.metier.objet.exception.MetierException;

public abstract class ObjectProcess<T> {
  private Logger log = Logger.getLogger(ObjectProcess.class);

  public abstract boolean isValid(T t) throws MetierException;

  @SafeVarargs
  final void doValid(T t, ExceptionConsumer<T>... validateurs) throws MetierException {
    MetierException e = isObjectNotNull(t);
    if (e != null) {
      log.warn(OBJET_NULL);
      throw e;
    }
    for (ExceptionConsumer<T> validateur : validateurs) {
      e = validateur.accept(t);
      if (e != null) {
        log.info(e.getMessage());
        throw e;
      }
    }
  }

  private MetierException isObjectNotNull(T t) {
    if (t == null) {
      return new MetierException(OBJET_NULL);
    }
    return null;
  }

  @FunctionalInterface
  protected interface ExceptionConsumer<T> {
    MetierException accept(T t);
  }
}
