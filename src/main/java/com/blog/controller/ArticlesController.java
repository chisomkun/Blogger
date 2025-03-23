package com.blog.controller;

import com.blog.model.Article;
import com.blog.service.CreateArticle;
import com.blog.service.QueryArticles;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class ArticlesController {

    private final CreateArticle articleService;
    private final QueryArticles queryArticles;

    @GetMapping("/new")
    public String newArticle(Model model){
        model.addAttribute("article", new Article());
        return "new";
    }

    @PostMapping("/new")
    public String submitNewArticle(@ModelAttribute Article article, Model model) throws IOException {
        articleService.createArticleInFile(article.getTitle(),article.getContent());
        return "redirect:/home";
    }

    @GetMapping("/article/{id}")
    public String getArticles(@PathVariable int id, Model model) throws IOException {
        var article = queryArticles.getArticle(id);

        model.addAttribute("title", article.getTitle());
        model.addAttribute("content", article.getContent());
        model.addAttribute("publishDate", article.getPublishDate());

        return "article";
    }

}
