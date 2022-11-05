package org.hbrs.semesterprojekt.entities;

import lombok.Data;
import org.springframework.data.annotation.Id;

@Data
public class Salesman {
    @Id
    private final String id;
    private String firstname;
    private String lastname;
}
