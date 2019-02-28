# Agile Engine test

1. To build run the command:

    `mvn clean install`

1. To execute run one of the following:

    * Input 1:
    
        `java -jar target/agileengine-test-1.0.jar src/test/resources/sample-0-origin.html src/test/resources/sample-1-evil-gemini.html`
    
        * expected output:
        
            `Found element: #page-wrapper > div.row:nth-child(3) > div.col-lg-8 > div.panel.panel-default > div.panel-body > a.btn.btn-success`
    
    * Input 2:
    
        `java -jar target/agileengine-test-1.0.jar src/test/resources/sample-0-origin.html src/test/resources/sample-2-container-and-clone.html`
    
        * expected output:
        
            ` #page-wrapper > div.row:nth-child(3) > div.col-lg-8 > div.panel.panel-default > div.panel-body > div.some-container > a.btn.test-link-ok`
            
    * Input 3:
    
        `java -jar target/agileengine-test-1.0.jar src/test/resources/sample-0-origin.html src/test/resources/sample-3-the-escape.html`
    
        * expected output:
        
            `Found element: #page-wrapper > div.row:nth-child(3) > div.col-lg-8 > div.panel.panel-default > div.panel-footer > a.btn.btn-success`
            
    * Input4: 
    
        `java -jar target/agileengine-test-1.0.jar src/test/resources/sample-0-origin.html src/test/resources/sample-3-the-mash.html`
    
        * expected output:
        
            `Found element: #page-wrapper > div.row:nth-child(3) > div.col-lg-8 > div.panel.panel-default > div.panel-footer > a.btn.btn-success`
            