package com.sebrogala.WordPressGenerator.Helper;

public class PregReplaceInFile {

    public static boolean foundInFile(String filePath, String substr) {
        String content = FileHandler.getContents(filePath);

        return content.contains(substr);
    }

    public static void run(String filePath, String regex, String replacement) {

        String content = FileHandler.getContents(filePath);
        String response = content.replaceAll(regex, replacement);
        FileHandler.putContents(filePath, response);
    }
}
