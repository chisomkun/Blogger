package com.blog.controller;

import com.blog.service.DeleteService;
import com.blog.service.QueryArticles;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class AdminController {

    private final QueryArticles queryArticles;
    private final DeleteService deleteService;

    @GetMapping("/admin")
    public String getAdmin(Model model) throws IOException {
        var listArticles = queryArticles.getArticles();
        model.addAttribute("articles", listArticles);
        return "admin";
    }

    @PostMapping("/delete/{id}")
    public String deleteArticle(@PathVariable int id) throws IOException {
        deleteService.deleteArticle(id);
        return "admin";
    }
}
