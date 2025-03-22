package com.blog.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.Objects;

@Service
public class EditArticle {

    @Value("${blogs_folder}")
    public String folderPath;

    public String editArticle(int id, String newName, String content) throws IOException {
        File folder = new File(folderPath);
        File oldArticle = null;

        File [] files = folder.listFiles();
        if(folder.listFiles() != null){
            Arrays.sort(files, (f1, f2) -> Long.compare(f2.lastModified(), f1.lastModified()));
            oldArticle = files[id-1];
        }
        int nameIdx = oldArticle.getName().lastIndexOf('.');
        String oldArtName = oldArticle.getName().substring(0,nameIdx);

        if(Objects.equals(newName, oldArtName)){
            Path file = Path.of(folderPath + oldArtName + ".txt");
            Files.writeString(file,content, StandardOpenOption.TRUNCATE_EXISTING);
        }else {
            File file = new File(folderPath+oldArtName+".txt");
            File fileNew = new File(folderPath+newName+".txt");
            file.renameTo(fileNew);
            Files.writeString(fileNew.toPath(),content, StandardOpenOption.TRUNCATE_EXISTING);
        }
        return null;
    }
}
