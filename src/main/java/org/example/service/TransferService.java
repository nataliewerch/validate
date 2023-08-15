package org.example.service;

public interface TransferService {
    void transfer(long acc1Id, long acc2Id, double amount) throws IllegalAccessException;
}
