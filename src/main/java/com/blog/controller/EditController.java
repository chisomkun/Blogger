package com.blog.controller;

import com.blog.model.Article;
import com.blog.service.EditArticle;
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
public class EditController {

    private final QueryArticles queryArticles;
    private final EditArticle editArticle;

    @GetMapping("/edit/{id}")
    public String editPage(@PathVariable int id, Model model) throws IOException {
        var article = queryArticles.getArticle(id);

        model.addAttribute("article", article);
        model.addAttribute("articleId",id);

        return "edit";
    }

    @PostMapping("/edit/{id}")
    public String editArticle(@PathVariable int id, @ModelAttribute Article article) throws IOException {
        editArticle.editArticle(id,article.getTitle(),article.getContent());

        return "redirect:/admin";
    }


}
