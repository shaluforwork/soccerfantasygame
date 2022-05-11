package com.soccerfantasy.app.service.builder;

import java.math.BigInteger;
import java.sql.Date;

import com.github.javafaker.Faker;
import com.soccerfantasy.app.domain.PlayerEntity;
import com.soccerfantasy.app.domain.PlayerType;
import com.soccerfantasy.app.domain.TeamEntity;
import com.soccerfantasy.app.util.Utils;

public class PlayerBuilder {
	private String firstName;
	private String lastName;
	private String country;
	private Date birthDate;
	private BigInteger marketValue;
	private String position;
	private TeamEntity team;

	public PlayerBuilder(PlayerType playerType, TeamEntity teamEntity) {
		Faker faker = new Faker();
		this.firstName = faker.name().firstName();
		this.lastName = faker.name().lastName();
		this.country = teamEntity.getCountry();
		this.birthDate = Utils.getRandomDateOfBirth();
		this.marketValue = new BigInteger("1000000");
		this.position = playerType.name();
		this.team = teamEntity;
	}
	public PlayerBuilder withFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}
	public PlayerBuilder withLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}
	public PlayerBuilder withCountry(String country) {
		this.country = country;
		return this;
	}
	public PlayerBuilder withBirthDate(Date birthDate) {
		this.birthDate = birthDate;
		return this;
	}
	public PlayerBuilder withMarketValue(BigInteger marketValue) {
		this.marketValue = marketValue;
		return this;
	}
	public PlayerBuilder withPosition(PlayerType playerType) {
		this.position = playerType.name();
		return this;
	}
	public PlayerBuilder withTeamEntity(TeamEntity team) {
		this.team = team;
		return this;
	}
	public PlayerEntity build() {
		return new PlayerEntity(firstName, lastName, country, birthDate, marketValue, position, team);
	}
}
