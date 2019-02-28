package com.agileengine.test;

import com.agileengine.test.htmlcrawler.CollectionUtil;
import com.agileengine.test.htmlcrawler.HtmlLookupUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.nio.file.Paths;

import static java.util.Objects.requireNonNull;

public class Application {
    private static final String ORIGINAL_OK_BUTTON_CSS_SELECTOR = String.format("#%s", "make-everything-ok-button");

    public static void main(String[] args) {
        if (args == null || args.length < 2) {
            System.out.println("You should specify two args: input_origin_file_path input_other_sample_file_path");
            return;
        }
        String originalFile = requireNonNull(args[0], "input_origin_file_path required at position 0");
        String changedFile = requireNonNull(args[1], "input_other_sample_file_path required at position 1");

        Document originalDoc = HtmlLookupUtil.parseHtml(Paths.get(originalFile));
        Document changedDoc = HtmlLookupUtil.parseHtml(Paths.get(changedFile));
        Element originalElement = CollectionUtil.getSingleElement(originalDoc.select(ORIGINAL_OK_BUTTON_CSS_SELECTOR));
        Element foundElement = HtmlLookupUtil.findFuzzyElementByAttributes(changedDoc, originalElement.attributes().asList());

        System.out.println("Found element: " + foundElement.cssSelector());
    }
}
