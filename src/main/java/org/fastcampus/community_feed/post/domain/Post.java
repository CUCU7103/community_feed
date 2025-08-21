package org.fastcampus.community_feed.post.domain;

import org.fastcampus.community_feed.common.domain.PositiveIntegerCounter;
import org.fastcampus.community_feed.post.domain.content.PostContent;
import org.fastcampus.community_feed.post.domain.content.PostPublicationState;
import org.fastcampus.community_feed.user.domain.User;

public class Post {


	private final Long id;
	private final User author;
	private final PostContent content;
	private final PositiveIntegerCounter likeCount;
	private PostPublicationState state;

	public Post(Long id, User author, PostContent content) {
		if(author == null){
			throw new IllegalArgumentException();
		}

		this.id = id;
		this.author = author;
		this.content = content;
		this.likeCount = new PositiveIntegerCounter();
		this.state = PostPublicationState.PUBILC;
	}

	public void like(User user){
		if (this.author.equals(user)) {
			throw new IllegalArgumentException();
		}
		likeCount.increase();
	}

	public void unlike(User user){
		this.likeCount.decrease();
	}

	public void updatePost(User user, String updateContent , PostPublicationState state){
		if (!this.author.equals(user)) {
			throw new IllegalArgumentException();
		}
		this.state = state;
		this.content.updateContent(updateContent);
	}

	// 객체 전체를 받는것과 아이디만 받는것
	// 객체를 직접 주입받는 형식이 좀더 객체지향에 가깝다

}
