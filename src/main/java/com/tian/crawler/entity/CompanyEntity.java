package com.tian.crawler.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "t_company")
public class CompanyEntity {
    /**
     * id
     */
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    /**
     * 图片地址
     */
    @Column(name = "img_url")
    private String imgUrl;
    /**
     * 访问链接地址
     */
    @Column(name = "Url")
    private String Url;
    /**
     * 标题
     */
    @Column(name = "title")
    private String title;
    /**
     * 内容
     */
    @Column(name = "text")
    private String text;

    public CompanyEntity() {
    }

    public CompanyEntity(String imgUrl, String url, String title, String text) {
        this.imgUrl = imgUrl;
        Url = url;
        this.title = title;
        this.text = text;
    }
}
