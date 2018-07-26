package org.delphy.tokenherocms.service;

import lombok.extern.slf4j.Slf4j;
import org.delphy.tokenherocms.pojo.OracleVo;
import org.delphy.tokenherocms.pojo.OracleResultVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author mutouji
 */
@Slf4j
@Service
public class OracleService {
    @Value("${oracle.url}")
    private String oracleUrl;
    private RestTemplate restTemplate;

    public OracleService(@Autowired RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    OracleVo<OracleResultVo> getResult(Long oracleId) {
        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.add("Content-Type","application/json;charset=UTF-8");
        HttpEntity requestEntity = new HttpEntity(requestHeaders);
        return restTemplate.exchange(oracleUrl + "oracles/" + oracleId + "/result",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<OracleVo<OracleResultVo>>() {}).getBody();
    }
}
