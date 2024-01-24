package com.glushkov.consolecrud.util;

import com.glushkov.consolecrud.model.BaseItem;
import com.glushkov.consolecrud.model.Post;
import com.glushkov.consolecrud.model.Writer;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtil {

    private final static String path = "src/main/resources/";

    public static void add(String fileName, String jsonString) {
        try {
            Files.write(Paths.get(path + fileName), jsonString.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static String read(String fileName) {
        try {
            return Files.readString(Paths.get(path + fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T extends BaseItem> String getFileName(T item) {
        if (item instanceof Post) {
            return "Post.json";
        } else if (item instanceof Writer) {
            return "Writer.json";
        } else {
            return "Label.json";
        }
    }
}
