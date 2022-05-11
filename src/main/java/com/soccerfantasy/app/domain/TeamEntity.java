package com.soccerfantasy.app.domain;

import java.math.BigInteger;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "TEAM", schema = "FANTASYSOCCER")
public class TeamEntity {


	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "TEAM_NAME")
	private String teamName;

	@Column(name = "COUNTRY")
	private String country;

	@Column(name = "TOTAL_VALUE")
	private BigInteger totalValue;

	@Column(name = "BUDGET")
	private BigInteger budget;

	public TeamEntity(String teamName, String country, BigInteger totalValue, BigInteger budget) {
		super();
		this.teamName = teamName;
		this.country = country;
		this.totalValue = totalValue;
		this.budget = budget;
	}

	public TeamEntity() {
	}

}
