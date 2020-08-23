package io.seven4x.discuss.parse;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ParseResult {
    List<String> users;
    List<String> topics;
    List<String> urls;
    List<String> emoji;

    public void addUser(String group) {
        if(users == null){
            users = new ArrayList<>();
        }
        users.add(group);
    }
}
