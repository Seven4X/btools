package io.seven4x.tinyurldemo.controller;

import io.seven4x.service.TinyUrlService;
import io.seven4x.service.UrlInfo;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

@Controller
public class DemoController {
    @Autowired
    TinyUrlService tinyUrlService;

    @SneakyThrows
    @GetMapping("/api/add")
    public void addLongUrl(String longUrl, HttpServletResponse response) {
        PrintWriter writer = response.getWriter();
        if (StringUtils.startsWithIgnoreCase(longUrl, "http")
                || StringUtils.startsWithIgnoreCase(longUrl, "https")) {
            String s = tinyUrlService.toShortUrl(longUrl);
            writer.write(s);
            ;
        } else {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            writer.write("参数错误：非法的url");
        }
        writer.flush();
    }



    @SneakyThrows
    @GetMapping("/{shortUrl}")
    public void accessShortUrl(@PathVariable("shortUrl") String shortUrl,
                               HttpServletResponse response) {
        UrlInfo info = tinyUrlService.getLongUrl(shortUrl);
        PrintWriter writer = response.getWriter();
        if (info == null || StringUtils.isEmpty(info.getLongUrl())) {
            response.setStatus(HttpStatus.NOT_FOUND.value());
            writer.write("您访问的地址不存在");
            writer.flush();
            return;
        }
        response.sendRedirect(info.getLongUrl());


    }
}
