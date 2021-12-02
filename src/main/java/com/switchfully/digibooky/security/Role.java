package com.switchfully.digibooky.security;

import java.util.List;

import static com.switchfully.digibooky.security.Features.*;

public enum Role {
    ADMIN(List.of(
            REGISTER_NEW_LIBRARIAN,
            GET_ALL_MEMBERS)),
    LIBRARIAN(List.of(
            REGISTER_NEW_BOOK,
            CONSULT_LENDINGS,
            UPDATE_A_BOOK,
            DELETE_A_BOOK,
            RESTORE_A_BOOK)),
    MEMBER(List.of(
            LEND_A_BOOK,
            REGISTER_NEW_MEMBER));

    private final List<Features> listOfFeatures;

    Role(List<Features> listFeatures) {
        listOfFeatures = listFeatures;
    }

    public List<Features> getListOfFeatures() {
        return listOfFeatures;
    }
}
