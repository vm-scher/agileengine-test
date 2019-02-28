package com.agileengine.test.htmlcrawler;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.junit.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class HtmlLookupUtilTest {

    private static final Path SAMPLE_DIR_PATH = Paths.get("src", "test", "resources");
    private static final String ORIGINAL_SAMPLE_0_HTML = "sample-0-origin.html";
    private static final String ORIGINAL_OK_BUTTON_ID = "make-everything-ok-button";

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
        Document originalDoc = HtmlLookupUtil.parseHtml(SAMPLE_DIR_PATH.resolve(HtmlLookupUtilTest.ORIGINAL_SAMPLE_0_HTML));
        Document changedDoc = HtmlLookupUtil.parseHtml(SAMPLE_DIR_PATH.resolve(changedFileName));
        Element originalElement = originalDoc.getElementById(ORIGINAL_OK_BUTTON_ID);
        Element foundElement = HtmlLookupUtil.findFuzzyElementByAttributes(changedDoc, originalElement.attributes().asList());

        assertThat(foundElement.cssSelector(), is(expectedCssSelector));
    }

}
