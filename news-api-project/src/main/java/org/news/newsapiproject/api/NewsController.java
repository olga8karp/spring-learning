package org.news.newsapiproject.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.news.newsapiproject.entity.Article;
import org.news.newsapiproject.service.NewsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class NewsController {
    private final NewsService newsService;

    @GetMapping("/api/news")
    public ResponseEntity<Page<Article>> getTopHeadlines(@RequestParam("country") String country,
                                                         @RequestParam(defaultValue = "0") int page,
                                                         @RequestParam(defaultValue = "3") int size
                                                         )
    {
        log.info("Request received: " + country);
        Pageable paging = PageRequest.of(page, size);
        return ResponseEntity.ok(newsService.getTopHeadlines(country, paging));
    }
}
