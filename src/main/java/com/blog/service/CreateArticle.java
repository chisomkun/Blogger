package com.blog.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

@Service
@Slf4j
public class CreateArticle {

    @Value("${blogs_folder}")
    public String folderPath;


    public String createArticleInFile(String title, String description) throws IOException {
        title = title.trim();
        description = description.trim();
        try {
            Path filePath = Path.of(folderPath + title + ".txt");

            if (Files.notExists(filePath)) {
                Files.createFile(filePath);
            } else {
                return "Title already used!)";
            }
            // Write content to file
            Files.writeString(filePath, description, StandardOpenOption.APPEND);

        } catch (IOException e) {
            log.info("Error Creating File");
        }
        return "Article created!";
    }
}
