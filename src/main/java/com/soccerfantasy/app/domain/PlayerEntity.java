package com.soccerfantasy.app.domain;

import java.math.BigInteger;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "PLAYER", schema = "FANTASYSOCCER")
public class PlayerEntity {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "FIRST_NAME")
	private String firstName;

	@Column(name = "LAST_NAME")
	private String lastName;

	@Column(name = "COUNTRY")
	private String country;

	@Column(name = "BIRTH_DATE")
	private Date birthDate;

	@Column(name = "MARKET_VALUE")
	private BigInteger marketValue;

	@Column(name = "POSITION")
	private String position;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TEAM_ID")
	private TeamEntity team;

	public PlayerEntity(String firstName, String lastName, String country, Date birthDate, BigInteger marketValue,
			String position, TeamEntity team) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.country = country;
		this.birthDate = birthDate;
		this.marketValue = marketValue;
		this.position = position;
		this.team = team;
	}

	public PlayerEntity() {
	}

	
}
