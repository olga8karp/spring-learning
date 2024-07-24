package org.news.newsapiproject.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.news.newsapiproject.entity.Article;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class NewsService {
    private final RestTemplate restTemplate;
    private final NewsParser parser;

    public NewsService(RestTemplate restTemplate, NewsParser parser) {
        this.restTemplate = restTemplate;
        this.parser = parser;
    }

    @Value("${newsapi.key}")
    private String apiKey;

    @Value("${requests.topHeadlines}")
    private String topHeadlinesRequest;

    public Page<Article> getTopHeadlines(String country, Pageable pageable) {
        String url = topHeadlinesRequest + "?country=" + country + "&apiKey=" + apiKey + "&page=" + pageable.getPageNumber() + "&pageSize=" + pageable.getPageSize();
        String jsonResponse = restTemplate.getForObject(url, String.class);
        try {
            List<Article> articles = parser.parseNewsArticles(jsonResponse);
            return new PageImpl<>(articles, pageable, articles.size());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}