package kim.jaehoon.dsmforest.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Notice {
    @Id
    private int id;

    private String title;
    private String localDateTime;
    private String content;

    private boolean approved;
    private String approvedDateTime;
    private String approvedAdminId;

    private boolean uploadedToFacebook;
    private String uploadedLink;
}
