package com.mitrais.dbassocdemo.entity;

import javax.persistence.*;

@Entity
public class PostDetails extends BaseAuditModel {

    private String metainfo;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    private PostDetails(){}

    public PostDetails(String metainfo) {
        this.metainfo = metainfo;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public String getMetainfo() {
        return metainfo;
    }

    public void setMetainfo(String metainfo) {
        this.metainfo = metainfo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PostDetails details = (PostDetails) o;

        if (metainfo != null ? !metainfo.equals(details.metainfo) : details.metainfo != null)
            return false;
        return post != null ? post.equals(details.post) : details.post == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (metainfo != null ? metainfo.hashCode() : 0);
        result = 31 * result + (post != null ? post.hashCode() : 0);
        return result;
    }
}
