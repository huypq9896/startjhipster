package com.mycompany.myapp.repository;

import java.util.List;

import com.mycompany.myapp.domain.DmDonVi;

public interface DmDonViRepositoryCustom {
	List<DmDonVi> getAllByMa(String ma);
}
