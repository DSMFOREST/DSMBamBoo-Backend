package kim.jaehoon.dsmforest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

import java.time.LocalDateTime;

@ToString
@Getter @Setter
@AllArgsConstructor
public class Posts {
    @Id
    private int id;

    private String title;
    private LocalDateTime createdTime;
    private LocalDateTime updatedTime;
    private String content;

    private boolean approved;
    private String approvedDateTime;
    private String approvedAdminId;

    private boolean uploadedToFacebook;
    private String uploadedLink;

    public void setCreatedDateNow() {
        this.createdTime = LocalDateTime.now();
    }

    public void update(Posts post) {
        this.title = post.title;
        this.content = post.content;
        this.approved = post.approved;
        this.updatedTime = post.updatedTime;
    }

    public Posts(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.approved = false;
        this.uploadedToFacebook = false;
    }
}
