package com.soccerfantasy.app.service.builder;

import java.math.BigInteger;

import com.github.javafaker.Faker;
import com.soccerfantasy.app.domain.TeamEntity;
import com.soccerfantasy.app.util.Utils;

public class TeamBuilder {

	private String teamName;
	private String country;
	private BigInteger totalValue;
	private BigInteger budget;

	public TeamBuilder() {
		Faker faker = new Faker();
		this.teamName = faker.name().fullName().concat(" Team");
		this.country = Utils.getRandomCountry();
		this.totalValue = new BigInteger("20000000");
		this.budget = new BigInteger("5000000");
	}
	public TeamEntity build() {
		return new TeamEntity(teamName, country, totalValue, budget);
	}
}
