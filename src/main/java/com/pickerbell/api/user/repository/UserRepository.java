package com.pickerbell.api.user.repository;

import com.pickerbell.api.user.domain.UserInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

/*
ID 중복여부 조회
닉네임 중복여부 조회
회원정보 등록
로그인 처리(ID 기준 회원정보 조회)
 */
@Repository
public class UserRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * 사용자 정보 저장(가입)
	 * @param userInfo 회원가입 페이지에서 기입한 사용자 정보
	 * @return UserInfo.id
	 */
	public long save(UserInfo userInfo) {
		em.persist(userInfo);
		
		return userInfo.getId();
	}
	
	/**
	 * 사용자 고유 순번 기준으로 사용자 정보 조회
	 * @param id 사용자 순번(자동 발번된 번호)
	 * @return UserInfo - 사용자 정보
	 */
	public UserInfo find(long id) {
		return em.find(UserInfo.class, id);
	}
	
	/**
	 * 로그인 ID 기준으로 사용자 정보 조회
	 * @param loginId 사용자 ID(로그인 시 사용하는 ID)
	 * @return UserInfo - 사용자 정보
	 */
	public UserInfo findByLoginId(String loginId) {
		try {
			TypedQuery<UserInfo> query = em.createQuery("select u from UserInfo u where u.userId = :userId", UserInfo.class)
					.setParameter("userId", loginId);
			return query.getSingleResult();
		} catch (NoResultException exception) {
			exception.printStackTrace();
			return null;
		}
		
	}
	
	/**
	 * 사용자 ID 중복 여부 조회
	 * @param userId 사용자 ID
	 * @return true or false
	 */
	public boolean isExistUserId(String userId) {
		TypedQuery<Integer> query = em.createQuery("select count(*) from UserInfo where userId = :userId", Integer.class)
				.setParameter("userId", userId);
		return query.getSingleResult() > 0;
	}
	
	/**
	 * <pre>닉네임 중복 여부 조회</pre>
	 * @param nickname 닉네임
	 * @return true or false
	 */
	public boolean isExistNickname(String nickname) {
		TypedQuery<Integer> query = em.createQuery("select count(*) from UserInfo where userId = :nickname", Integer.class)
				.setParameter("nickname", nickname);
		return query.getSingleResult() > 0;
	}
	
}
