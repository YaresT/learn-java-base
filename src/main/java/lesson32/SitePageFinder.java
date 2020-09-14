package lesson32;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.CookieSpecs;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SitePageFinder {
    private static final Pattern aHrefPattern = Pattern.compile("<a[^>]*?href=['\"](.+?)['\"]");

    private final URL baseUrl;

    private final HttpClient client = HttpClients.custom()
            .setDefaultRequestConfig(RequestConfig.custom()
                .setCookieSpec(CookieSpecs.STANDARD).build())
            .build();

    private final Set<URL> completePages = new HashSet<>();

    private final Queue<URL> pageQueue = new LinkedList<>();

    public SitePageFinder(URL baseUrl) {
        this.baseUrl = baseUrl;
    }

    public static void main(final String[] args) throws MalformedURLException {
        for (URL url : new SitePageFinder(new URL("https://www.baeldung.com/")).getAllPages()) {
            System.out.println(url.toString());
        }
    }

    protected Set<URL> getAllPages() {
        if (completePages.isEmpty()) {
            loadPages();
        }

        return completePages;
    }

    protected void loadPages() {
        pageQueue.offer(baseUrl);
        int count = 0;

        while (!pageQueue.isEmpty()) {
            System.out.printf(
                    "%d - %d %s%n",
                    count + 1,
                    pageQueue.size(),
                    pageQueue.peek()
            );

            processPage(pageQueue.poll());

            count++;
        }
    }

    protected void processPage(final URL pageURL) {
        if (pageURL == null || completePages.contains(pageURL)) {
            return;
        }

        if (pageURL.getHost().equals(baseUrl.getHost())) {
            completePages.add(pageURL);

            pageQueue.addAll(getPageUrls(pageURL));
        }
    }

    protected Set<URL> getPageUrls(final URL pageURL) {
        Set<URL> hrefSet = new HashSet<>();
        String pageHTML = getPageHTML(pageURL);

        if (pageHTML != null && !pageHTML.isEmpty()) {
            Matcher hrefMatcher = aHrefPattern.matcher(pageHTML);

            while (hrefMatcher.find()) {
                String href = hrefMatcher.group(1);

                if (href == null || href.isEmpty() || href.charAt(0) == '#') {
                    continue;
                }

                href = href.replaceFirst("\\?.*$", "");

                if (href.charAt(0) == '/') {
                    if (href.length() == 1) {
                        continue;
                    }

                    if (href.charAt(1) == '/') {
                        href = "https:" + href;
                    } else {
                        href = String.format(
                                "%s://%s%s",
                                pageURL.getProtocol(),
                                pageURL.getHost(),
                                href
                        );
                    }
                }


                try {
                    URL newURL = new URL(href);

                    if (newURL.getHost().equals(baseUrl.getHost())) {
                        if (!completePages.contains(newURL) && !pageQueue.contains(newURL)) {
                            pageQueue.add(newURL);
                        }
                    }
                } catch (MalformedURLException ignored) {
                }
            }
        }

        return hrefSet;
    }

    protected String getPageHTML(final URL pageURL) {
        HttpGet httpget;
        try {
            httpget = new HttpGet(pageURL.toURI());
        } catch (URISyntaxException e) {
            return null;
        }

        StringBuilder builder;
        try {
            HttpResponse httpresponse = client.execute(httpget);

            try (Scanner sc = new Scanner(httpresponse.getEntity().getContent())) {
                builder = new StringBuilder();
                while (sc.hasNext()) {
                    builder.append(sc.next());
                }

                return builder.toString();
            }
        } catch (IOException ignored) {
        }

        return null;
    }
}
