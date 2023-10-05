package com.poc.game.backend.utils;

import java.text.Normalizer;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class NameNormalizer {
    public static String normalizeName(String name) {
        // Remove acentos e caracteres especiais
        String normalized = Normalizer.normalize(name, Normalizer.Form.NFD)
                .replaceAll("[^\\p{ASCII}]", "");

        // Remove espaços extras e converte para minúsculas
        normalized = normalized.trim().toLowerCase();

        // Capitaliza a primeira letra de cada palavra
        List<String> words = Arrays.asList(normalized.split("\\s+"));
        normalized = words.stream()
                .map(word -> word.substring(0, 1).toUpperCase() + word.substring(1))
                .collect(Collectors.joining(" "));

        return normalized;
    }
}
