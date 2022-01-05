package com.robsonmrsp.netflics.core.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import com.robsonmrsp.netflics.core.audit.CustomEnversListener;
/* generated by JSetup v0.95 :  at 4 de jan de 2022 23:12:56 */
@Entity
@Table(name = "revinfo")
@RevisionEntity(CustomEnversListener.class)
public class CustomRevisionEntity {

	@Id
	@GeneratedValue
	@RevisionNumber
	@Column
	private int rev;

	@RevisionTimestamp
	private long revtstmp;

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getRevtstmp() {
		return revtstmp;
	}

	public void setRevtstmp(long revtstmp) {
		this.revtstmp = revtstmp;
	}

	public int getRev() {
		return rev;
	}

	public void setRev(int rev) {
		this.rev = rev;
	}
}