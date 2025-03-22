package com.blog.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Service
public class DeleteService {

    @Value("${blogs_folder}")
    public String folderPath;

    public void deleteArticle(int id) throws IOException {
        File folder = new File(folderPath);
        File article = null;
        if(folder.exists() && folder.isDirectory()){
            if(folder.listFiles() != null){
                article = folder.listFiles()[id-1];
                Files.delete(article.toPath());
            }else{
                throw new IOException(); //error page
            }
        }
    }
}
