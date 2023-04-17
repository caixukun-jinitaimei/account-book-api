package com.example.accountbook1.model.server;

/**
 * 条目获取渠道
 *
 * @author FranklinThree
 * @date 2023/04/09
 * @className Channel
 * @see
 * @since 1.0.0
 */
public class Channel {
    private Long id;
    private String name;
    private Long ownerId;
    private String description;

    public Channel() {
    }

    public Channel(String name, Long ownerId, String description) {
        this.id = null;
        this.name = name;
        this.ownerId = ownerId;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Channel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ownerId=" + ownerId +
                ", description='" + description + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(long ownerId) {
        this.ownerId = ownerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
