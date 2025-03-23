package com.blog.service;

import com.blog.model.Article;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.attribute.FileTime;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class QueryArticles {

    @Value("${blogs_folder}")
    public String folderPath;

    public List<Article> getArticles() throws IOException {
        File folder = new File(folderPath);
        List<Article> articleNames = new ArrayList<>();

        if(folder.exists() && folder.isDirectory()){
            File[] files = folder.listFiles();
            if(files != null){
                Arrays.sort(files, (f1, f2) -> Long.compare(f2.lastModified(), f1.lastModified()));
                for(File file : files){
                    int nameIdx = file.getName().lastIndexOf('.');
                    String fileNm = file.getName().substring(0,nameIdx);
                    String publishDate = getDate(file);

                    articleNames.add(new Article(fileNm,"",publishDate));
                }
            }
        }
        return articleNames;
    }

    public Article getArticle(int index) throws IOException {
        File folder = new File(folderPath);
        File [] files = folder.listFiles();

        File article = null;
        if(folder.listFiles() != null){
            Arrays.sort(files, (f1, f2) -> Long.compare(f2.lastModified(), f1.lastModified()));
            article = files[index-1];
        }
        int nameIdx = article.getName().lastIndexOf('.');
        return new Article(article.getName().substring(0,nameIdx),
                new String(Files.readAllBytes(article.toPath())),
                        getDate(article));
    }

    private String getDate(File article) throws IOException {
        FileTime time = Files.getLastModifiedTime(article.toPath());

        SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd, yyyy");
        return sdf.format(new Date(time.toMillis()));
    }
}
