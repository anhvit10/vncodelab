//
package com.vncodelab.entity;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * This class is .
 *
 * @Description: .
 * @author: NVAnh
 * @create_date: Feb 19, 2021
 * @version: 1.0
 * @modifer: NVAnh
 * @modifer_date: Feb 19, 2021
 */
public class Home implements Serializable {

    private static final long serialVersionUID = 1L;
    private String logoUrl;
    private String title;
    private String footers;
    private String description;
    private MultipartFile image;

    public Home() {

    }

    public Home(String logoUrl, String title, String footers, String description) {
        this.logoUrl = logoUrl;
        this.title = title;
        this.footers = footers;
        this.description = description;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFooters() {
        return footers;
    }

    public void setFooters(String footers) {
        this.footers = footers;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
