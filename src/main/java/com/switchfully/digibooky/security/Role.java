package com.switchfully.digibooky.security;

import java.util.List;

public enum Role {
    ADMIN(List.of()),
    LIBRARIAN(List.of(Features.REGISTER_NEW_BOOK)),
    MEMBER(List.of(Features.LEND_A_BOOK));

    private List<Features> listOfFeatures;

    Role(List<Features> listFeatures) {
        listOfFeatures = listFeatures;
    }

    public List<Features> getListOfFeatures() {
        return listOfFeatures;
    }
}
