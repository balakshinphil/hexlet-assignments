package exercise.controller;

import exercise.model.Article;
import exercise.repository.ArticleRepository;

import liquibase.pro.packaged.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;

import javax.persistence.AttributeOverride;


@RestController
@RequestMapping("/articles")
public class ArticlesController {
    private final ArticleRepository articleRepository;

    @Autowired
    public ArticlesController(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
    }

    @GetMapping(path = "")
    public Iterable<Article> getArticles() {
        return this.articleRepository.findAll();
    }

    @DeleteMapping(path = "/{id}")
    public void deleteArticle(@PathVariable long id) {
        this.articleRepository.deleteById(id);
    }

    @PostMapping
    public void createArticle(@RequestBody Article article) {
        articleRepository.save(article);
    }

    @PatchMapping("/{id}")
    public void patchArticle(@PathVariable long id, @RequestBody Article article) {
        article.setId(id);
        articleRepository.save(article);
    }

    @GetMapping("/{id}")
    public Article getArticle(@PathVariable long id) {
        return articleRepository.findById(id);
    }


}
