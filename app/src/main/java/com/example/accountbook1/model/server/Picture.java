package com.example.accountbook1.model.server;

import java.util.Arrays;

/**
 * 图片
 *
 * @author FranklinThree
 * @date 2023/04/08
 * @className Picture
 * @see
 * @since 1.0.0
 */
public class Picture implements Cloneable{
    private Long id;
    private String name;
    private Byte[] data;
    private Long groupId;
    private Long createTime;

    private PictureType type;


    @Override
    public Picture clone() throws CloneNotSupportedException {

        Picture picture = (Picture) super.clone();
        picture.id = this.id;
        picture.data = picture.getData().clone();
        picture.groupId = this.groupId;
        picture.createTime = this.createTime;
        picture.type = this.type;

        return picture;
    }

    public Picture() {
    }

    public Picture(String name, Byte[] data, Long groupId, Long createTime, PictureType type) {
        this.id = null;
        this.name = name;
        this.data = data;
        this.groupId = groupId;
        this.createTime = createTime;
        this.type = type;
    }


    public Picture(String name, Byte[] data, Long groupId) {
        this.id = null;
        this.name = name;
        this.data = data;
        this.groupId = groupId;
        this.createTime = null;
        this.type = null;
    }


    @Override
    public String toString() {
        return "Picture{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", data=" + Arrays.toString(data) +
                ", groupId=" + groupId +
                ", createTime=" + createTime +
                ", type=" + type +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte[] getData() {
        return data;
    }

    public void setData(Byte[] data) {
        this.data = data;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public PictureType getType() {
        return type;
    }

    public void setType(PictureType type) {
        this.type = type;
    }
}
