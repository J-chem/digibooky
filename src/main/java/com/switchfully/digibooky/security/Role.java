package com.switchfully.digibooky.security;

import java.util.List;

public enum Role {
    ADMIN(List.of( Features.REGISTER_NEW_LIBRARIAN)),
    LIBRARIAN(List.of(Features.REGISTER_NEW_BOOK, Features.CONSULT_LENDINGS, Features.DELETE_A_BOOK, Features.RESTORE_A_BOOK)),
    MEMBER(List.of(Features.LEND_A_BOOK, Features.REGISTER_NEW_MEMBER));

    private List<Features> listOfFeatures;

    Role(List<Features> listFeatures) {
        listOfFeatures = listFeatures;
    }

    public List<Features> getListOfFeatures() {
        return listOfFeatures;
    }
}
