package com.vncodelab.controller;

import com.vncodelab.entity.Lab;
import com.vncodelab.respository.LabRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MainController {

    @Autowired
    private LabRespository labRespository;

    @GetMapping("/save")
    public String save() {
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
        return "save";
    }

    @GetMapping("/lab/{docID}")
    public String lab(Model model, @PathVariable(name = "docID") String docID) {

        Lab lab = labRespository.findByDocID(docID);
        model.addAttribute("lab", lab);
        return "lab";
    }

    @GetMapping("/hello")
    public String hello(Model model) {

        Lab lab = labRespository.findByDocID("1EEGARIc9dEj9mpnmKoYP8n4EA9KNH9qR0W2c6CYEWT0");
     //   Lab lab = new Lab("1EEGARIc9dEj9mpnmKoYP8n4EA9KNH9qR0W2c6CYEWT0", "Tiêu đề");
        model.addAttribute("lab", lab.getName());
        return "hello";
    }


}
