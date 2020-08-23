package io.seven4x.discuss.parse;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DiscussParser {

    /**
     * @ 匹配
     */
    private Pattern atUserRen = Pattern.compile("@[\\u4e00-\\u9fa5A-Z0-9a-z_-]{2,30}");
    /**
     * 话题正则
     */
    private Pattern topicRen = Pattern.compile("#[^@<>#\"&'\\r\\n\\t]{1,49}#");

    /**
     * 表情符号
     */
    private Pattern emojiRen = Pattern.compile("\\[[\\u4e00-\\u9fa5A-Za-z]{1,8}\\]");

    /**
     * http
     */
    private Pattern urlRen = Pattern.compile("(https?|ftp|file)://[-A-Za-z0-9+&@#/%?=~_|!:,.;]+[-A-Za-z0-9+&@#/%=~_|]");

    public ParseResult parse(String context){
        ParseResult result = new ParseResult();
        Matcher matcher = atUserRen.matcher(context);
        while (matcher.find()){
            String group = matcher.group();
            result.addUser(group);
        }


        return result;
    }
}
