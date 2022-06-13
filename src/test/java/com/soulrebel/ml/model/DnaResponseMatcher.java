package com.soulrebel.ml.model;

import org.mockito.ArgumentMatcher;

import java.util.Arrays;

public class DnaResponseMatcher implements ArgumentMatcher<DnaResponse> {

    private final DnaResponse expected;

    public DnaResponseMatcher(DnaResponse expected) {
        this.expected = expected;
    }

    @Override
    public boolean matches(DnaResponse argument) {

        return Arrays.deepEquals(expected.getDna(), argument.getDna());
    }
}
