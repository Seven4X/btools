package io.seven4x.discuss.parse;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DiscussParser {

    /**
     * @ 匹配
     */
    private final Pattern atUserRen = Pattern.compile("@[\\u4e00-\\u9fa5A-Z0-9a-z_-]{2,30}");
    /**
     * 话题正则
     */
    private final Pattern topicRen = Pattern.compile("#[^@<>#\"&'\\r\\n\\t]{1,49}#");

    /**
     * 表情符号
     */
    private final Pattern emojiRen = Pattern.compile("\\[[\\u4e00-\\u9fa5A-Za-z]{1,8}\\]");

    /**
     * http
     */
    private final Pattern urlRen = Pattern
            .compile("(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]");

    public ParseResult parseAll(final String context) {
        ParseResult result = new ParseResult();
        parseUser(context, result);
        parseTopic(context, result);
        parseEmoji(context, result);
        parseUrl(context, result);
        return result;
    }

    /**
     * 并发的进行解析
     * 
     * @param context
     * @param executor 在方法内直接创建线程有点浪费资源，从外部传一个线程池 ，一般一个应用的线程池配置都是全局的可控的
     * @return
     */
    public ParseResult parseParallel(final String context, Executor executor) {
        ParseResult result = new ParseResult();
        CountDownLatch cdl = new CountDownLatch(4);
        executor.execute(() -> {
            parseUser(context, result);
            cdl.countDown();
        });
        executor.execute(() -> {
            parseTopic(context, result);
            cdl.countDown();
        });
        executor.execute(() -> {
            parseEmoji(context, result);
            cdl.countDown();
        });
        executor.execute(() -> {
            parseUrl(context, result);
            cdl.countDown();
        });
        return result;
    }

    public ParseResult parseUser(String context) {
        ParseResult result = new ParseResult();
        parseUser(context, result);
        return result;
    }

    public ParseResult parseEmoji(String context) {
        ParseResult result = new ParseResult();
        parseEmoji(context, result);
        return result;
    }

    public ParseResult parseTopic(String context) {
        ParseResult result = new ParseResult();
        parseTopic(context, result);
        return result;
    }

    public ParseResult parseUrl(String context) {
        ParseResult result = new ParseResult();
        parseUrl(context, result);
        return result;
    }

    private void parseUrl(String context, ParseResult result) {
        Matcher matcher = urlRen.matcher(context);
        while (matcher.find()) {
            final String group = matcher.group();
            result.addUrl(group);
        }
    }

    private void parseEmoji(String context, ParseResult result) {
        Matcher matcher = emojiRen.matcher(context);
        while (matcher.find()) {
            final String group = matcher.group();
            result.addEmoji(group);
        }

    }

    private void parseTopic(String context, ParseResult result) {
        Matcher matcher = topicRen.matcher(context);
        while (matcher.find()) {
            String group = matcher.group();
            result.addTopic(group.substring(1, group.length()-1));
        }

    }

    private void parseUser(String context, ParseResult result) {
        Matcher matcher = atUserRen.matcher(context);
        while (matcher.find()) {
            String group = matcher.group();
            result.addUser(group.substring(1,group.length()));
        }
    }
}
