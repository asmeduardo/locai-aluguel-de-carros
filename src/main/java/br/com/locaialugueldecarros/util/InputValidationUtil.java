package br.com.locaialugueldecarros.util;

import java.util.Objects;

public class InputValidationUtil {

    public static boolean validateInputs(String[] inputs) {
        for (String input : inputs) {
            if (Objects.equals(input, "")) return false;
        }
        return true;
    }
}