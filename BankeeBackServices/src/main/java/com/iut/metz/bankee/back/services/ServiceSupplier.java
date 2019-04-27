package com.iut.metz.bankee.back.services;

@FunctionalInterface
public interface ServiceSupplier {
  Object get(String num);
}
