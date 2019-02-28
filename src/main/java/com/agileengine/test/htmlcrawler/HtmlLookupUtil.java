package com.agileengine.test.htmlcrawler;

import com.agileengine.test.htmlcrawler.exception.ElementNotFoundException;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.AbstractMap.SimpleEntry;
import java.util.List;
import java.util.Objects;

import static com.agileengine.test.htmlcrawler.CollectionUtil.innerJoin;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.joining;

public class HtmlLookupUtil {

    @SneakyThrows
    public static Document parseHtml(Path originalHtml) {
        return Jsoup.parse(new File(originalHtml.toUri()), StandardCharsets.UTF_8.name());
    }

    // The method is way to simple to put it in a stateful class.
    // Looks more like an util which belongs to a static method
    public static Element findFuzzyElementByAttributes(Document document, List<Attribute> keyAttributes) {
        Objects.requireNonNull(document, "document");
        if (keyAttributes == null || keyAttributes.isEmpty()) {
            throw new IllegalArgumentException("keyAttributes should be specified");
        }
        return document.getAllElements()
                .stream()
                .map(e -> new SimpleEntry<>(e, innerJoin(e.attributes().asList(), keyAttributes)))
                .max(comparingInt(entry -> entry.getValue().size()))
                .map(SimpleEntry::getKey)
                .orElseThrow(() -> new ElementNotFoundException(joinAttributes(keyAttributes)));
    }

    private static String joinAttributes(List<Attribute> keyAttributes) {
        return keyAttributes.stream()
                .map(Attribute::toString)
                .collect(joining());
    }
}
