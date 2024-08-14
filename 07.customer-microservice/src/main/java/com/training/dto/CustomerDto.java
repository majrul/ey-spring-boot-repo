package com.training.dto;

import lombok.Data;

@Data
public class CustomerDto {

	private String op;
    private String id;
    private String propertyName;
    private String propertyValue;

    //private List<Map<String, String>> properties;
}
