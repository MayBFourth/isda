package com.mcslearning.isdav11.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Customer{
    private int id;
    private String username;
    private String password;
    private String email;
    private String phone;
}


