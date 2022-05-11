package com.soccerfantasy.app.domain;

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
public class UserEntity {

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

}