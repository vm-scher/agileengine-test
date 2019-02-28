package com.agileengine.test;

import com.agileengine.test.htmlcrawler.HtmlLookupUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.nio.file.Paths;

import static java.util.Objects.requireNonNull;

public class Application {
    private static final String ORIGINAL_OK_BUTTON_ID = "make-everything-ok-button";

    public static void main(String[] args) {
        if (args == null || args.length < 2) {
            System.out.println("You should specify 2 or 3 args: input_origin_file_path input_other_sample_file_path [target_element_id]");
            return;
        }
        String originalFile = requireNonNull(args[0], "input_origin_file_path required at position 0");
        String changedFile = requireNonNull(args[1], "input_other_sample_file_path required at position 1");
        String elementId = args.length == 3 ? args[2] : ORIGINAL_OK_BUTTON_ID;

        Document originalDoc = HtmlLookupUtil.parseHtml(Paths.get(originalFile));
        Document changedDoc = HtmlLookupUtil.parseHtml(Paths.get(changedFile));
        Element originalElement = originalDoc.getElementById(elementId);
        Element foundElement = HtmlLookupUtil.findFuzzyElementByAttributes(changedDoc, originalElement.attributes().asList());

        System.out.println();
        System.out.println("Found element: " + foundElement.cssSelector());
    }
}
