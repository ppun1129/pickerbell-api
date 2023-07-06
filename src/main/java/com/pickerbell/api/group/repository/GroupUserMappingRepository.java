package com.pickerbell.api.group.repository;

import com.pickerbell.api.group.domain.GroupInfo;
import com.pickerbell.api.group.domain.GroupUserMappingInfo;
import com.pickerbell.api.user.domain.UserInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class GroupUserMappingRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	// 그룹 정보와 사용자 정보를 매핑하여 테이블에 저장(사용자의 그룹 가입)
	public long save(GroupUserMappingInfo groupUserMappingInfo) {
		em.persist(groupUserMappingInfo);
		return groupUserMappingInfo.getId();
	}
	
	// 사용자 고유번호를 기준으로 가입된 그룹 목록을 조회
	public List<GroupInfo> groupsByUserNumber(long userNumber) {
		return em.createQuery(
				"SELECT " +
						"g " +
						"FROM " +
						"GroupUserMappingInfo m INNER JOIN m.groupInfo g " +
						"WHERE " +
						"m.userInfo.id = :userNumber",
				GroupInfo.class)
				.setParameter("userNumber", userNumber)
				.getResultList();
	}
	
	// 그룹 고유번호를 기준으로 가입된 사용자 목록을 조회
	public List<UserInfo> usersByGroupNumber(long groupNumber) {
		return em.createQuery(
				"SELECT " +
						"u " +
						"FROM " +
						"GroupUserMappingInfo m INNER JOIN m.userInfo u " +
						"WHERE " +
						"m.groupInfo.id = :groupNumber",
				UserInfo.class)
				.setParameter("groupNumber", groupNumber)
				.getResultList();
	}
	
}
