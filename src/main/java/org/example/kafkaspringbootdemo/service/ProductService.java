package org.example.kafkaspringbootdemo.service;

import org.example.kafkaspringbootdemo.dto.CreateProductDto;

import java.util.concurrent.ExecutionException;

public interface ProductService {

    String createProduct(CreateProductDto createProductDto) throws ExecutionException, InterruptedException;

}
