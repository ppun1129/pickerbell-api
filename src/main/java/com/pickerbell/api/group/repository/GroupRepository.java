package com.pickerbell.api.group.repository;

import com.pickerbell.api.group.domain.GroupInfo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
