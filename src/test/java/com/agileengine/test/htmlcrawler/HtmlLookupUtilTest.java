package com.agileengine.test.htmlcrawler;

import com.agileengine.test.htmlcrawler.exception.ElementNotFoundException;
import lombok.SneakyThrows;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Iterator;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HtmlLookupUtilTest {

    private static final Path SAMPLE_DIR_PATH = Paths.get("src", "test", "resources");
    private static final String ORIGINAL_SAMPLE_0_HTML = "sample-0-origin.html";
    private static final String ORIGINAL_OK_BUTTON_CSS_SELECTOR = String.format("#%s", "make-everything-ok-button");

    @Test
    public void testHtmlCrawler_1_evilGemini() {
        testHtmlCrawler(
                "sample-1-evil-gemini.html",
                "#page-wrapper > div.row:nth-child(3) > div.col-lg-8 > div.panel.panel-default > div.panel-body > a.btn.btn-success"
        );
    }

    @Test
    public void testHtmlCrawler_2_containerAndClone() {
        testHtmlCrawler(
                "sample-2-container-and-clone.html",
                "#page-wrapper > div.row:nth-child(3) > div.col-lg-8 > div.panel.panel-default > div.panel-body > div.some-container > a.btn.test-link-ok"
        );
    }

    @Test
    public void testHtmlCrawler_3_theEscape() {
        testHtmlCrawler(
                "sample-3-the-escape.html",
                "#page-wrapper > div.row:nth-child(3) > div.col-lg-8 > div.panel.panel-default > div.panel-footer > a.btn.btn-success"
        );
    }

    @Test
    public void testHtmlCrawler_4_theMash() {
        testHtmlCrawler(
                "sample-4-the-mash.html",
                "#page-wrapper > div.row:nth-child(3) > div.col-lg-8 > div.panel.panel-default > div.panel-footer > a.btn.btn-success"
        );
    }

    private void testHtmlCrawler(String changedFileName, String expectedCssSelector) {
        Document originalDoc = parseHtml(SAMPLE_DIR_PATH.resolve(HtmlLookupUtilTest.ORIGINAL_SAMPLE_0_HTML));
        Document changedDoc = parseHtml(SAMPLE_DIR_PATH.resolve(changedFileName));
        Element originalElement = CollectionUtil.getSingleElement(originalDoc.select(ORIGINAL_OK_BUTTON_CSS_SELECTOR));
        Element foundElement = HtmlLookupUtil.findFuzzyElementByAttributes(changedDoc, originalElement.attributes().asList());

        assertThat(foundElement.cssSelector(), is(expectedCssSelector));
    }

    @SneakyThrows
    private static Document parseHtml(Path originalHtml) {
        return Jsoup.parse(new File(originalHtml.toUri()), StandardCharsets.UTF_8.name());
    }

}
