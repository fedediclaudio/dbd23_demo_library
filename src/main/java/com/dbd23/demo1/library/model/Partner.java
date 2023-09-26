package com.dbd23.demo1.library.model;

import java.time.LocalDate;
import java.util.List;

public class Partner {

    private Long id;
    private String fullname;
    private String dni;
    private String phone;
    private String address;
    private String mail;
    private LocalDate dateOfBirth;
    private LocalDate registrationDate;
    private boolean isActive;
    private int numberOfLoans;
    private int score;
    private List<Loan> loanList;
}
