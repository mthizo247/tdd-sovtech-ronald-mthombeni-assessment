package com.sovtech.tddsovtechassessment.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class AppUtils {
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
