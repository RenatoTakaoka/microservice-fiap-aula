package com.github.RenatoTakaoka.ms_pagamentos.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class CustomErrorDTO {

    private String timestamp;
    private Integer status;
    private String error;
    private String path;

}
