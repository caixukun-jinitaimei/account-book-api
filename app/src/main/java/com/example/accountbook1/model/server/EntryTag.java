package com.example.accountbook1.model.server;

/**
 * 条目标记
 *
 * @author FranklinThree
 * @date 2023/04/12
 * @className EntryTag
 * @see
 * @since 1.0.0
 */
public class EntryTag {
    private Long id;

    private EntryTagEnum entryTagEnum;
    private Long groupId;

    @Override
    public String toString() {
        return "EntryTag{" +
                "id=" + id +
                ", entryTagEnum=" + entryTagEnum +
                ", groupId=" + groupId +
                '}';
    }
    public EntryTag() {
    }

    public EntryTag(EntryTagEnum entryTagEnum, Long groupId) {
        this.id = null;
        this.entryTagEnum = entryTagEnum;
        this.groupId = groupId;
    }

    public Long getId() {
        return id;
    }

    @Deprecated
    public void setId(Long id) {
        this.id = id;
    }

    public EntryTagEnum getEntryTagEnum() {
        return entryTagEnum;
    }

    public void setEntryTagEnum(EntryTagEnum entryTagEnum) {
        this.entryTagEnum = entryTagEnum;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }
}
