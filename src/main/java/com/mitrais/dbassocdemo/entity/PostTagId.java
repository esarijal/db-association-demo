package com.mitrais.dbassocdemo.entity;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class PostTagId implements Serializable {
    private Long postId;
    private Long tagId;

    private PostTagId(){}

    public PostTagId(Long postId, Long tagId) {
        this.postId = postId;
        this.tagId = tagId;
    }

    public Long getPostId() {
        return postId;
    }

    public void setPostId(Long postId) {
        this.postId = postId;
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostTagId postTagId = (PostTagId) o;

        if (postId != null ? !postId.equals(postTagId.postId) : postTagId.postId != null)
            return false;
        return tagId != null ? tagId.equals(postTagId.tagId) : postTagId.tagId == null;
    }

    @Override
    public int hashCode() {
        int result = postId != null ? postId.hashCode() : 0;
        result = 31 * result + (tagId != null ? tagId.hashCode() : 0);
        return result;
    }
}
