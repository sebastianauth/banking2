package com.emma.finance.repository;

import org.springframework.data.repository.CrudRepository;

import com.emma.finance.domain.GiroTransaction;

public interface GiroTransactionRepository extends CrudRepository<GiroTransaction, Long> {

}
