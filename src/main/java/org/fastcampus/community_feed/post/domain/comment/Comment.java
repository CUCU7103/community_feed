package org.fastcampus.community_feed.post.domain.comment;

import org.fastcampus.community_feed.common.domain.PositiveIntegerCounter;
import org.fastcampus.community_feed.post.domain.Post;
import org.fastcampus.community_feed.post.domain.content.CommentContent;
import org.fastcampus.community_feed.user.domain.User;

public class Comment {

	private final Long id;
	private final Post post;
	private final User author;
	private final CommentContent content;
	private final PositiveIntegerCounter likeCount;



	public Comment(Long id, Post post, User author, CommentContent content) {

		if(author == null){
			throw new IllegalArgumentException();
		}

		if(post == null){
			throw new IllegalArgumentException();
		}

		if(content == null){
			throw new IllegalArgumentException();
		}

		this.id = id;
		this.post = post;
		this.author = author;
		this.content = content;
		this.likeCount = new PositiveIntegerCounter();
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

	public void updateComment(User user, String updateContent){
		if (!this.author.equals(user)) {
			throw new IllegalArgumentException();
		}
		this.content.updateContent(updateContent);
	}


}
