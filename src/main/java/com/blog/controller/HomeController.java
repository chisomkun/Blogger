package com.blog.controller;

import com.blog.service.QueryArticles;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class HomeController {

    private final QueryArticles articles;

    @GetMapping("/home")
    public String getArticles(Model model) throws IOException {
        var listArticles = articles.getArticles();
        model.addAttribute("articles", listArticles);
        return "home";
    }
}
