package com.mitrais.dbassocdemo.entity;

import javax.persistence.*;

@Entity
public class Post extends BaseAuditModel {
    private String title;
    private String description;
    @Lob
    private String content;

    @OneToOne(mappedBy = "post",
                cascade = CascadeType.ALL,
                fetch = FetchType.LAZY,
                orphanRemoval = true)
    private PostDetails details;

    public Post() {}

    public Post(String title) {
        this.title = title;
    }

    // NOT GENERATED
    public void setDetails(PostDetails details) {
        if(details == null){
            if(this.details != null){
                this.details.setPost(null);
            }
        } else {
            details.setPost(this);
        }
        this.details = details;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public PostDetails getDetails() {
        return details;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Post post = (Post) o;

        if (title != null ? !title.equals(post.title) : post.title != null) return false;
        if (description != null ? !description.equals(post.description) : post.description != null)
            return false;
        if (content != null ? !content.equals(post.content) : post.content != null) return false;
        return details != null ? details.equals(post.details) : post.details == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (details != null ? details.hashCode() : 0);
        return result;
    }
}
