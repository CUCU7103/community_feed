package org.fastcampus.community_feed.user.domain;

import java.util.Objects;

public class User {

	private final Long id;
	private final UserInfo info;
	private final UserRelationCounter followingcount;
	private final UserRelationCounter followerCounter;


	public User(Long id, UserInfo info) {
		this.id = id;
		this.info = info;
		this.followingcount = new UserRelationCounter();
		this.followerCounter = new UserRelationCounter();
	}

	// 팔로우 기능
	public void follow(User targetUser){
		if(targetUser.equals(this)){
			throw new IllegalArgumentException();
		}

		followerCounter.increase();
		targetUser.increaseFollowerCount();

	}


	public void unfollow(User targetUser){
		if(targetUser.equals(this)){
			throw new IllegalArgumentException();
		}

		followerCounter.decrease();
		targetUser.decreaseFollowerCount();

	}


	private void increaseFollowerCount(){
		followerCounter.increase();
	}

	private void decreaseFollowerCount(){
		followerCounter.increase();
	}


	@Override
	public boolean equals(Object o) {
		if (o == null || getClass() != o.getClass())
			return false;
		User user = (User)o;
		return Objects.equals(id, user.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}
}
