package com.example.accountbook1.model.server;

import java.util.List;

public class Book {
    private Long id;
    private Long ownerId;
    private List<BookEntry> bookEntries;
    private String description;
    private Long createTime;
    private Long updateTime;
    private String title;

    private Picture picture;


    public Book() {
    }

    public Book(Long ownerId, List<BookEntry> bookEntries, String description, String title, Picture picture) {
        this.id = null;
        this.ownerId = ownerId;
        this.bookEntries = bookEntries;
        this.description = description;
        this.createTime = null;
        this.updateTime = null;
        this.title = title;
        this.picture = picture;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public List<BookEntry> getBookEntries() {
        return bookEntries;
    }

    public void setBookEntries(List<BookEntry> bookEntries) {
        this.bookEntries = bookEntries;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Long updateTime) {
        this.updateTime = updateTime;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", ownerId=" + ownerId +
                ", bookEntries=" + bookEntries +
                ", description='" + description + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                ", title='" + title + '\'' +
                ", picture=" + picture +
                '}';
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
