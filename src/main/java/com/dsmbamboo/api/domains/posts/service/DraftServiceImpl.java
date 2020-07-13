package com.dsmbamboo.api.domains.posts.service;

import com.dsmbamboo.api.domains.posts.dto.CreateArticleRequest;
import com.dsmbamboo.api.domains.posts.exception.InvalidCategoryException;
import com.dsmbamboo.api.domains.posts.model.Article;
import com.dsmbamboo.api.domains.posts.model.ArticleType;
import com.dsmbamboo.api.domains.users.exception.UserNotFoundException;
import com.dsmbamboo.api.domains.users.security.AuthenticationFacade;
import com.dsmbamboo.api.domains.users.security.JwtTokenProvider;
import com.dsmbamboo.api.domains.users.security.UserPrincipal;
import com.dsmbamboo.api.domains.users.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DraftServiceImpl implements DraftService {

    private final UserService userService;
    private final ArticleService articleService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationFacade authenticationFacade;
    private final PublishedIdGenerator publishedIdGenerator;
    private final PushNotificationService pushNotificationService;

    @Override
    public Page<Article> findAll(Pageable pageable) {
        return articleService.findAllArticlesByType(ArticleType.DRAFT, pageable);
    }

    @Override
    public Article create(String documentKey, CreateArticleRequest request) {
        if (NoticeService.isContainsNoticeCategory(request.getCategories()))
            throw new InvalidCategoryException();
        jwtTokenProvider.validateDocumentKey(documentKey);
        return notifyDraftCreated(articleService.create(request, ArticleType.DRAFT, null, null));
    }

    private Article notifyDraftCreated(Article article) {
        String title = String.format("익명의 제보 요청(#%d)이 들어왔습니다. 승인 여부를 결정해 주십시오.", article.getId());
        String content = article.getTitle();
        pushNotificationService.sendToAdministrators(title, content);

        return article;
    }

    @Override
    public Optional<Article> findByArticleId(Long articleId) {
        return articleService.findArticleByTypeAndId(ArticleType.DRAFT, articleId);
    }

    @Override
    public Optional<Article> approve(Long draftId) {
        return articleService.findArticleByTypeAndId(ArticleType.DRAFT, draftId)
                .map(article -> {
                    Long approverId = ((UserPrincipal) authenticationFacade.getAuthentication().getPrincipal()).getId();
                    return userService.findById(approverId)
                            .map(approver -> {
                                long publishedId = publishedIdGenerator.getNextNoticePublishedId();
                                return articleService.save(article.approve(approver, publishedId));
                            })
                            .orElseThrow(UserNotFoundException::new);
                })
                .map(this::notifyDraftApproved);
    }

    private Article notifyDraftApproved(Article article) {
        String titleForAdmin = String.format("%s님께서 #%d 제보 요청을 승인하셨습니다.", article.getApprover().getUsername(), article.getId());
        String commonContent = "#" + article.getPublishedId() + "번째_대마: " + article.getTitle();
        pushNotificationService.sendToAdministrators(titleForAdmin, commonContent);

        Long applicantId = Long.parseLong(article.getCreatedBy());
        String titleForApplicant = "귀하의 사연 제보가 관리자에 의해 승인되었습니다.";
        pushNotificationService.sendToSingleDeviceByUserId(applicantId, titleForApplicant, commonContent);

        String titleForAnonymousUsers = "대마고 대나무숲에 새로운 익명 제보가 들어왔습니다.";
        pushNotificationService.sendToAnonymousUsers(titleForAnonymousUsers, commonContent);

        return article;
    }

    @Override
    public Optional<Article> disapprove(Long draftId) {
        return articleService.findArticleByTypeAndId(ArticleType.DRAFT, draftId)
                .map(article -> {
                    Long approverId = ((UserPrincipal) authenticationFacade.getAuthentication().getPrincipal()).getId();
                    return userService.findById(approverId)
                            .map(approver -> articleService.save(article.disapprove()))
                            .orElseThrow(UserNotFoundException::new);
                })
                .map(this::notifyDraftDisapproved);
    }

    private Article notifyDraftDisapproved(Article article) {
        String disapproverUsername = ((UserPrincipal) authenticationFacade.getAuthentication().getPrincipal()).getUsername();
        String titleForAdmin = String.format("%s님께서 #%d 제보를 거절하셨습니다.", disapproverUsername, article.getId());
        String contentForAdmin = "거절된 제보 요청은 삭제되며 제보 요청 목록에서 더 이상 볼 수 없습니다.";
        pushNotificationService.sendToAdministrators(titleForAdmin, contentForAdmin);

        Long applicantId = Long.parseLong(article.getCreatedBy());
        String titleForApplicant = "귀하의 사연 제보가 관리자에 의해 거절되었습니다.";
        String contentForApplicant = "지나친 욕설이나 비방 등으로 인해 커뮤니티 규칙을 위반한 것으로 확인되었습니다.";
        pushNotificationService.sendToSingleDeviceByUserId(applicantId, titleForApplicant, contentForApplicant);

        return article;
    }

}
