package com.sebrogala.WordPressGenerator.Helper;

public class NameConverter {

    String[] parts;

    public NameConverter(String name) {
        if(name.contains(" ")) {
            parts = name.split(" ");
        } else {
            parts = new String[1];
            parts[0] = name;
        }
    }

    public String capitalizedUnderscores() {
        return join(parts, "_").toUpperCase();
    }

    public String camelCase() {
        return lcfirst(capitalizedCamelCase());
    }

    public String slug() {
        return join(parts, "-").toLowerCase();
    }

    public String capitalizedCamelCase() {
        if(parts.length < 2) {
            return lcfirst(parts[0]);
        }

        String newWord = "";
        for(String word: parts) {
            newWord = newWord + ucfirst(word);
        }

        return newWord;
    }

    public String ucfirst(String name) {
        return name.substring(0,1).toUpperCase() + name.substring(1);
    }
    public String lcfirst(String name) { return name.substring(0,1).toLowerCase() + name.substring(1); }


    public static String join(String[] strings, String glue) {
        if(strings.length < 2) {
            return strings[0];
        }

        String newString = "";

        for (String str: strings) {
            newString += str + glue;
        }

        return newString.substring(0, newString.length() - glue.length());
    }
}
