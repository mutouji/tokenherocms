package org.delphy.tokenherocms.controller;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.delphy.tokenherocms.common.Constant;
import org.delphy.tokenherocms.common.EnumError;
import org.delphy.tokenherocms.exception.DefaultException;
import org.delphy.tokenherocms.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

/**
 * @author mutouji
 */
@Slf4j
@Api(value="restTemplateTest", tags={"RestTemplate测试"}, description = "RestTemplate测试接口")
@RequestMapping("/test")
@RestController
public class RestTemplateTest {
    private RestTemplate restTemplate;

    public RestTemplateTest(@Autowired RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @ApiOperation(value="通过url，获取json结果")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "p", value = "访问路径", paramType = "path", dataType = "string")
    })
    @GetMapping("/url/{p}")
    public String getJsonFromUrl(@PathVariable String p) {
        try {
            String query = "/";
            if (!Constant.UNDEFINED.equals(p)) {
                query = URLDecoder.decode(p, "UTF-8");
            }
            JSONObject json = restTemplate.getForEntity("http://192.168.1.113:3300" + query, JSONObject.class).getBody();
            return json.toJSONString();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new DefaultException(EnumError.ENUMERROR_END);
        }
    }

    @ApiOperation("getForEntity, 通过url获取,返回String")
    @GetMapping("/gethello")
    public String getHello() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.1.113:3300/hello", String.class);
        String body = responseEntity.getBody();
        HttpStatus statusCode = responseEntity.getStatusCode();
        int statusCodeValue = responseEntity.getStatusCodeValue();
        HttpHeaders headers = responseEntity.getHeaders();
        StringBuffer result = new StringBuffer();
        result.append("responseEntity.getBody()：").append(body).append("<hr>")
                .append("responseEntity.getStatusCode()：").append(statusCode).append("<hr>")
                .append("responseEntity.getStatusCodeValue()：").append(statusCodeValue).append("<hr>")
                .append("responseEntity.getHeaders()：").append(headers).append("<hr>");
        return result.toString();
    }

    /**
     * https://blog.csdn.net/u012702547/article/details/77917939
    * 可以用一个数字做占位符，最后是一个可变长度的参数，来一一替换前面的占位符
     */
    @ApiOperation("getForEntity, 通过url获取,返回String")
    @GetMapping("/sayhello")
    public String sayHello() {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.1.113:3300/sayhello?name={1}", String.class, "张三");
        return responseEntity.getBody();
    }

    /**
     *
     * 也可以前面使用name={name}这种形式，最后一个参数是一个map，map的key即为前边占位符的名字，map的value为参数值
     */
    @ApiOperation("getForEntity, 通过url获取")
    @GetMapping("/sayhello2")
    public String sayHello2() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "李四");
        ResponseEntity<String> responseEntity = restTemplate.getForEntity("http://192.168.1.113:3300/sayhello?name={name}", String.class, map);
        return responseEntity.getBody();
    }
    /**
     * 第一个调用地址也可以是一个URI而不是字符串，这个时候我们构建一个URI即可，参数神马的都包含在URI中了
     * 通过Spring中提供的UriComponents来构建Uri即可。
     */
    @ApiOperation("getForEntity, 通过uri获取")
    @GetMapping("/sayhello3")
    public String sayHello3() {
        UriComponents uriComponents = UriComponentsBuilder.fromUriString("http://192.168.1.113:3300/sayhello?name={name}").build().expand("王五").encode();
        URI uri = uriComponents.toUri();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);
        return responseEntity.getBody();
    }
    /**
     * 当然，服务提供者不仅可以返回String，也可以返回一个自定义类型的对象，比如我的服务提供者中有如下方法：
     */
    @ApiOperation("返回对象")
    @GetMapping(value = "/getbook1")
    public Book book() {
        return new Book("三国演义", 90, "罗贯中", "花城出版社");
    }

    @ApiOperation("getForEntity")
    @GetMapping("/book1")
    public Book book1() {
        ResponseEntity<Book> responseEntity = restTemplate.getForEntity("http://192.168.1.113:3300/getbook1", Book.class);
        return responseEntity.getBody();
    }

    ///
    /**
     * 第二种 getForObject
     * getForObject函数实际上是对getForEntity函数的进一步封装，如果你只关注返回的消息体的内容，对其他信息都不关注，此时可以使用getForObject
     */
    @ApiOperation("getForObject")
    @GetMapping("/book2")
    public Book book2() {
        Book book = restTemplate.getForObject("http://192.168.1.113:3300/getbook1", Book.class);
        return book;
    }

    /////////////////////////////////////////////////////////
    /**
     * post 请求
     * 第一种 postForEntity
     */
    @ApiOperation("postForEntity")
    @PostMapping("/book3")
    public Book book3() {
        Book book = new Book();
        book.setName("红楼梦");
        ResponseEntity<Book> responseEntity = restTemplate.postForEntity("http://192.168.1.113:3300/getbook2", book, Book.class);
        return responseEntity.getBody();
    }
    /*
     * 第二种：postForObject
     * 如果你只关注，返回的消息体，可以直接使用postForObject。用法和getForObject一致
     */

    /*
     * 第三种：postForLocation
     * postForLocation也是提交新资源，提交成功之后，返回新资源的URI，postForLocation的参数和前面两种的参数基本一致，
     * 只不过该方法的返回值为Uri，这个只需要服务提供者返回一个Uri即可，该Uri表示新资源的位置。
     */

    /**
     * PUT请求
     * put方法的参数和前面介绍的postForEntity方法的参数基本一致，只是put方法没有返回值而已
     */
    @ApiOperation("put添加")
    @PostMapping("/put")
    public void put() {
        Book book = new Book();
        book.setName("红楼梦");
        restTemplate.put("http://192.168.1.113:3300/getbook3/{1}", book, 99);
    }
    /**
     * DELETE请求
     */
    @ApiOperation("删除某项")
    @PostMapping("/delete")
    public void delete() {
        restTemplate.delete("http://192.168.1.113:3300/getbook4/{1}", 100);
    }

    //////////////////////////////////////////////////////////////////////
    /**
     * 设置header
     */
    @ApiOperation("获取header")
    @PostMapping("/header")
    public String header(HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = (String) headerNames.nextElement();
            String value = request.getHeader(key);
            headers.add(key, value);
        }
        String restStrResult = restTemplate.postForObject("http://192.168.1.113:3300/header",
                new HttpEntity<String>(headers), String.class, request.getParameterMap());
        return restStrResult;
    }
}
