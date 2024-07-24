package org.news.newsapiproject.api;

import lombok.AllArgsConstructor;
import org.news.newsapiproject.entity.GetTopHeadlines200Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class NewsTestController implements NewsApi {
    @Override
    public ResponseEntity<GetTopHeadlines200Response> getTopHeadlines(String country) {
        return null;
    }
}
