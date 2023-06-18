package com.pickerbell.api.group.repository;

import com.pickerbell.api.group.domain.GroupInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class GroupRepository {
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 * 그룹 정보 저장
	 * @param groupInfo
	 * @return
	 */
	public long save(GroupInfo groupInfo) {
		em.persist(groupInfo);
		return groupInfo.getId();
	}
	
	/**
	 * 그룹 정보 조회
	 * @param id
	 * @return
	 */
	public GroupInfo find(long id) {
		return em.find(GroupInfo.class, id);
	}
	
}
