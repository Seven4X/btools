package io.seven4x.discuss.parse;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class DiscussParserTest {

    @Test
    void parse() {

        DiscussParser parser = new DiscussParser();

        String context = "👿👿👿明天凌晨的苹果产品发布会，关于苹果iPhone12的发布时间，#iPhone12# 又上热搜了，而且还不知道真实性，一切都是猜测！（我猜测此次发布会不会发布iPhone12[doge][doge][doge]）苹果还是这么自信？目前越来越多的证据表明iPhone12虽然支持5G，但不是全系列支持，而且还不送充电器和耳机，还是刘海屏和大黑边，网友评论说这是在作死？美国佬制裁#华为# ，打压#小米# ，大家都在骂#苹果# ，说要抵制？会不会到最后演变成“真香”？[伤心][伤心][伤心]让我们拭目以待！#数码#你会买iPhone12吗？ 美国打压中国科技，你还会买iPhone12吗？";
        String context2 = "https://www.yuque.com/dashboard/books今日头条热搜#华为芯片断供首日#长痛不如短痛，经历磨难的华为，不但让自己警醒，还让全球看到了唯利是图资本家的丑恶嘴脸！华为一定会挺过这一关，2020年9月15日，一定会成为华为历史上最值得纪念的里程碑记录！华为，挺你，你一定行！@赵毅论房 ​";

        ParseResult result = parser.parseAll(context);
        ParseResult result2 = parser.parseAll(context2);
 

        assertThat(result.getUsers()).hasSize(2);

    }

    @Test
    void parseLinkTest() {

        DiscussParser parser = new DiscussParser();

         String context2 = "https://www.yuque.com/dashboard/books今日头条热搜#华为芯片断供首日#长痛不如短痛，经历磨难的华为，不但让自己警醒，还让全球看到了唯利是图资本家的丑恶嘴脸！华为一定会挺过这一关，2020年9月15日，一定会成为华为历史上最值得纪念的里程碑记录！华为，挺你，你一定行！@网论房 ​";

         ParseResult result2 = parser.parseAll(context2);
 

        assertThat(result2.getUsers()).hasSize(2);

    }
 
}