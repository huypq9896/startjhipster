package com.mycompany.myapp.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.elasticsearch.common.inject.Inject;
import org.springframework.stereotype.Repository;

import com.mycompany.myapp.domain.DmDonVi;
import com.mycompany.myapp.repository.DmDonViRepositoryCustom;

@Repository
public class DmDonViRepository implements DmDonViRepositoryCustom{

	@Inject
	private EntityManager em;
	
	@Override
	public List<DmDonVi> getAllByMa(String ma) {
		List<DmDonVi> list = new ArrayList<>();
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT * FROM dm_don_vi WHERE ma=:ma");
		Query query = em.createNativeQuery(sql.toString(), DmDonVi.class);
		query.setParameter("ma", ma);
		list = query.getResultList();
		return list;
	}
	
}
