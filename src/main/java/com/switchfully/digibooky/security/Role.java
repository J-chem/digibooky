package com.switchfully.digibooky.security;

import java.util.List;

public enum Role {
    ADMIN(List.of( Features.REGISTER_NEW_LIBRARIAN)),
    LIBRARIAN(List.of(Features.REGISTER_NEW_BOOK, Features.CONSULT_LENDINGS)),
    MEMBER(List.of(Features.LEND_A_BOOK, Features.REGISTER_NEW_MEMBER));

    private List<Features> listOfFeatures;

    Role(List<Features> listFeatures) {
        listOfFeatures = listFeatures;
    }

    public List<Features> getListOfFeatures() {
        return listOfFeatures;
    }
}
