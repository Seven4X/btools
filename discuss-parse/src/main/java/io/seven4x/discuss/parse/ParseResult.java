package io.seven4x.discuss.parse;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ParseResult {
    private List<String> users;
    private List<String> topics;
    private List<String> urls;
    private List<String> emojis;

    /**
     * 线程不安全哦！
     * 
     * @param group
     */
    public void addUser(String group) {
        if (users == null) {
            users = new ArrayList<>();
        }
        users.add(group);
    }

    public void addTopic(String topic) {
        if (topics == null) {
            topics = new ArrayList<>();
        }
        topics.add(topic);
    }

    public void addUrl(String url) {
        if (urls == null) {
            urls = new ArrayList<>();
        }
        urls.add(url);
    }

    public void addEmoji(String emoji) {
        if (emojis == null) {
            emojis = new ArrayList<>();
        }
        emojis.add(emoji);
    }
}
