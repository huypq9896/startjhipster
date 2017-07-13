package com.mycompany.myapp.service.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;

public class DmDonViDTO {
	private Long id;
    private String ma;
    private String name;
    private String diaChi;
    private String ghiChu;
    private String login;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMa() {
		return ma;
	}
	public void setMa(String ma) {
		this.ma = ma;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDiaChi() {
		return diaChi;
	}
	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}
	public String getGhiChu() {
		return ghiChu;
	}
	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public DmDonViDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DmDonViDTO(String ma, String name, String diaChi, String ghiChu, String login) {
		super();
		this.ma = ma;
		this.name = name;
		this.diaChi = diaChi;
		this.ghiChu = ghiChu;
		this.login = login;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DmDonViDTO other = (DmDonViDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "DmDonViDTO [id=" + id + ", ma=" + ma + ", name=" + name + ", diaChi=" + diaChi + ", ghiChu=" + ghiChu
				+ ", login=" + login + "]";
	}
	
}
