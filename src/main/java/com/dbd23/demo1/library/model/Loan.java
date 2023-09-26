package com.dbd23.demo1.library.model;

import java.time.LocalDate;
import java.util.List;

public class Loan {

    private Long id;
    private LocalDate date;
    private LocalDate finishDate;
    private boolean canBeRenewed;
    private LoanState state;
    private Partner partner;
    private List<Book> bookList;

}
