package com.iut.metz.bankee.back.metier.objet.utils;

import com.iut.metz.bankee.back.metier.objet.exception.MetierException;

@FunctionalInterface
public interface BiConsumerEx<T, U> {
  void accept(T t, U u) throws MetierException;
}
