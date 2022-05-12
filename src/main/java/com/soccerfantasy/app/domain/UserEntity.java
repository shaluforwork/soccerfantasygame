package com.soccerfantasy.app.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "USER", schema = "FANTASYSOCCER")
public class UserEntity implements Serializable {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "USER_ID")
	private String userId;

	@Column(name = "EMAIL")
	private String email;

	@Column(name = "ENCRYPTED_PASSWORD")
	private String encryptedPassword;

	@OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TEAM_ID")
	private TeamEntity team;

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UserEntity other = (UserEntity) obj;
		return Objects.equals(id, other.id);
	}
}