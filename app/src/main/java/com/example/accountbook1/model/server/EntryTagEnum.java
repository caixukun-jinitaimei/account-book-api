package com.example.accountbook1.model.server;

/**
 * 条目标签枚举
 *
 * @author FranklinThree
 * @date 2023/04/09
 * @className EntryTagEnum
 * @see
 * @since 1.0.0
 */
public class EntryTagEnum {
    private Long id;
    private String name;
    private Long ownerId;
    private String description;

    private Boolean isIncome;

    @Override
    public String toString() {
        return "EntryTagEnum{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ownerId=" + ownerId +
                ", description='" + description + '\'' +
                ", isIncome=" + isIncome +
                '}';
    }

    public EntryTagEnum() {
    }

    public EntryTagEnum(String name, Long ownerId, String description, Boolean isIncome) {
        this.id = null;
        this.name = name;
        this.ownerId = ownerId;
        this.description = description;
        this.isIncome = isIncome;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public Boolean getIncome() {
        return isIncome;
    }

    public void setIncome(Boolean income) {
        isIncome = income;
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
