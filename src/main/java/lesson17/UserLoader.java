package lesson17;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

class UserLoader {
    private static final String DATA_HOST_URL = "https://jsonplaceholder.typicode.com/";

    private UserLoader() {
    }

    public static User loadById(final int userId) throws IOException {
        JSONObject userObject = new JSONObject(
                getContentFromURL(new URL(ACTIONS.GET.getUrl(userId)))
        );

        return new User(
                userObject.getInt("id"),
                userObject.getString("name"),
                userObject.getString("username"),
                userObject.getString("email")
        );
    }

    private static String getContentFromURL(final URL serviceURL) throws IOException {
        StringBuilder content = new StringBuilder();

        try (BufferedReader in = new BufferedReader(
                new InputStreamReader(serviceURL.openStream())
        )) {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine).append("\n");
            }
        }

        return content.toString();
    }

    private enum ACTIONS {
        GET("users/%d/"),
        GET_COMMENT("users/%d/comments/");

        private String url;

        ACTIONS(final String url) {
            this.url = url;
        }

        public String getUrl(final int userId) {
            return DATA_HOST_URL + String.format(url, userId);
        }
    }
}
