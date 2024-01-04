package br.com.golden_awards_worst_movies.infrastructure.utils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Util {

    public static <T> List<T> convertSetToList(Set<T> set){
        return new ArrayList<>(set);
    }

    public static <T> Set<T> convertListToSet(List<T> list){
        return new HashSet<>(list);
    }

}
