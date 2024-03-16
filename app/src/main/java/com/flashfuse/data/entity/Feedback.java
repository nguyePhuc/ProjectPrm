package com.flashfuse.data.entity;

public class Feedback {
    private int id;
    private String content;
    private String user_id;
    private String created_at;
    private String update_at;

    public Feedback() {
    }

    public Feedback(int id, String content, String user_id, String created_at, String update_at) {
        this.id = id;
        this.content = content;
        this.user_id = user_id;
        this.created_at = created_at;
        this.update_at = update_at;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String getUpdate_at() {
        return update_at;
    }

    public void setUpdate_at(String update_at) {
        this.update_at = update_at;
    }
}
