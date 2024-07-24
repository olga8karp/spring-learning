package org.news.newsapiproject.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.news.newsapiproject.entity.Article;
import org.news.newsapiproject.entity.Source;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NewsParser {
    public List<Article> parseNewsArticles(String jsonResponse) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonResponse);
        JsonNode articlesNode = root.path("articles");
        List<Article> articles = new ArrayList<>();
        for (JsonNode articleNode : articlesNode) {
            Article article = new Article();
            article.setAuthor(articleNode.path("author").asText());
            article.setTitle(articleNode.path("title").asText());
            article.setDescription(articleNode.path("description").asText());
            article.setUrl(articleNode.path("url").asText());
            article.setUrlToImage(articleNode.path("urlToImage").asText());
            article.setContent(articleNode.path("content").asText());
            String sourceName = articlesNode.path("source").path("name").asText();
            String sourceId = articlesNode.path("source").path("id").asText();
            article.setSource(new Source().sourceId(sourceId).sourceName(sourceName));
            articles.add(article);
        }

        return articles;
    }
}
