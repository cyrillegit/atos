package com.example.atos.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = User.TABLE_NAME)
public class User implements Serializable {
    public static final String TABLE_NAME = "user";
    public static final String ID_COLUMN = "id";
    public static final String USERNAME_COLUMN = "username";
    public static final String BIRTHDATE_COLUMN = "birthdate";
    public static final String COUNTRY_COLUMN = "country";
    public static final String PHONE_NUMBER_COLUMN = "phoneNumber";
    public static final String GENDER_COLUMN = "gender";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ID_COLUMN)
    private Integer id;

    @Column(name = USERNAME_COLUMN, nullable = false)
    private String username;

    @Column(name = BIRTHDATE_COLUMN, nullable = false)
    private LocalDate birthdate;

    @Column(name = COUNTRY_COLUMN, nullable = false)
    private String country;

    @Column(name = PHONE_NUMBER_COLUMN)
    private String phoneNumber;

    @Column(name=GENDER_COLUMN)
    private String gender;
}
