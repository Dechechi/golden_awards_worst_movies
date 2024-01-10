package br.com.golden_awards_worst_movies.domain.enums;

import br.com.golden_awards_worst_movies.domain.exception.InvalidWinnerOptionException;

public enum WinnerBoolean {

    TRUE("true", "yes", "1", "verdadeiro"),
    FALSE("false", "no", "0", "falso", "");

    private final String[] winner;

    WinnerBoolean(String... winner) {
        this.winner = winner;
    }

    public static boolean valueFrom(String text) throws InvalidWinnerOptionException {
        for (WinnerBoolean value : WinnerBoolean.values()){
            for (String winner : value.winner){
                if (winner.equalsIgnoreCase(text)){
                    return value == TRUE;
                }
            }
        }
        throw new InvalidWinnerOptionException(String.format("A opção %s não pode ser interpretada adequadamente", text));
    }
}
