package com.vncodelab;

import com.vncodelab.entity.Lab;
import com.vncodelab.respository.LabRespository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class VnCodelabApplication extends SpringBootServletInitializer {
    private static final Logger log = LoggerFactory.getLogger(VnCodelabApplication.class);


    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(VnCodelabApplication.class, args);

        LabRespository labRespository = context.getBean(LabRespository.class);

        Lab lab = new Lab("1EEGARIc9dEj9mpnmKoYP8n4EA9KNH9qR0W2c6CYEWT0", "Tiêu đề");
        String html = "<google-codelab-step label=\"Heading 1\" duration=\"3\">\n" +
                "        <p> Duration:3:00 which uses dark grey 1 text.</p>\n" +
                "        <h2 is-upgraded>Heading 2</h2>\n" +
                "        <p class=\"image-container\"><img th:src=\"@{/images/test.png}\" style=\"width: 96.31px\" src=\"img/6287aa28c16749ca.png\"></p>\n" +
                "        <aside class=\"warning\"><p>Table single-cell with Light Orange 3 background</p>\n" +
                "        </aside>\n" +
                "        <aside class=\"special\"><p>Table single-cell with Light Green 3 background</p>\n" +
                "        </aside>\n" +
                "        <pre>Table single-cell with Consolas font</pre>\n" +
                "        <pre><code>public class Helloworld(){\n" +
                "        //Use courier new font for code, xml ...\n" +
                "        }</code></pre>\n" +
                "        <p><a href=\"https://downloadfestival.co.uk/\" target=\"_blank\">\n" +
                "            <paper-button class=\"colored\" raised>Download</paper-button>\n" +
                "        </a> (Text with Dark green 1 background + hyperlink)\n" +
                "        </p>\n" +
                "        <google-codelab-survey survey-id=\"title-1\">\n" +
                "            <h4>Heading 4 in single-cell table with a light blue 3 background.</h4>\n" +
                "            <paper-radio-group>\n" +
                "                <paper-radio-button>List item 1</paper-radio-button>\n" +
                "                <paper-radio-button>List item 2</paper-radio-button>\n" +
                "                <paper-radio-button>List item 3</paper-radio-button>\n" +
                "            </paper-radio-group>\n" +
                "        </google-codelab-survey>\n" +
                "    </google-codelab-step>\n" +
                "\n" +
                "    <google-codelab-step label=\"Cài đặt Claat\" duration=\"1\">\n" +
                "        <p>Cài đặt go tại website</p>\n" +
                "        <p class=\"image-container\"><img style=\"width: 601.70px\" src=\"img/6205e03d42f5789d.png\"></p>\n" +
                "        <p>Sau khi cài đặt xong go, tải claat bằng cách gõ lệnh sau:</p>\n" +
                "        <pre>go get github.com/googlecodelabs/tools/claat</pre>\n" +
                "        <p>Sau khi lệnh trên chạy xong claat được tải về</p>\n" +
                "        <p class=\"image-container\"><img style=\"width: 601.70px\" src=\"img/a0b2d24c521a1d56.png\"></p>\n" +
                "        <p>Chạy claat bằng lệnh sau (chú ý thay đổi đường dẫn):</p>\n" +
                "        <pre>/Users/xuanlam/go/bin/claat 1EEGARIc9dEj9mpnmKoYP8n4EA9KNH9qR0W2c6CYEWT0</pre>\n" +
                "    </google-codelab-step>\n" +
                "\n" +
                "    <google-codelab-step label=\"Tạo ra trang landing page\" duration=\"2\">\n" +
                "    </google-codelab-step>";

        lab.setHtml(html);
        labRespository.save(lab);

    }


//    @Bean
//    public CommandLineRunner demo(LabRespository repository) {
//        return (args) -> {
//            // save a few customers
//            repository.save(new Lab("123", "Bauer"));
//            repository.save(new Lab("456", "O'Brian"));
//
//
//            // fetch all customers
//            log.info("Customers found with findAll():");
//            log.info("-------------------------------");
//            for (Lab customer : repository.findAll()) {
//                log.info(customer.toString());
//            }
//            log.info("");
//
//            // fetch an individual customer by ID
//            Lab lab = repository.findById(1L);
//            log.info("Customer found with findById(1L):");
//            log.info("--------------------------------");
//            log.info(lab.toString());
//            log.info("");
//
//            // fetch customers by last name
//            log.info("Customer found with findByLastName('Bauer'):");
//            log.info("--------------------------------------------");
//            repository.findByName("Bauer").forEach(bauer -> {
//                log.info(bauer.toString());
//            });
//            // for (Customer bauer : repository.findByLastName("Bauer")) {
//            //  log.info(bauer.toString());
//            // }
//            log.info("");
//        };
//    }
}
