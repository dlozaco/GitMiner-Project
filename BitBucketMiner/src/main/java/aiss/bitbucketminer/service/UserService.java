package aiss.bitbucketminer.service;

import aiss.bitbucketminer.model.common.User;
import aiss.bitbucketminer.model.parsedModels.ParsedUser;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    public ParsedUser parseUser(User user) {
        if (user == null) {
            return null;}

        String id = user.getUuid();
        String username = user.getNickname();
        if (username == null) {
            username = "No username";}
        String name = user.getDisplayName().replaceAll("[^a-zA-Z0-9]", "");
        String avatar_url = user.getLinks().getAvatar().getHref();
        String webUrl = user.getLinks().getHtml().getHref();
        return new ParsedUser(id, username, name, avatar_url, webUrl);
    }
}
