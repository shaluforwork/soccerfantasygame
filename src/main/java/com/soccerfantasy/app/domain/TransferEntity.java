package com.soccerfantasy.app.domain;

import java.math.BigInteger;
import java.sql.Timestamp;

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
@Table(name = "TRANSFER", schema = "FANTASYSOCCER")
public class TransferEntity {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PLAYER_ID")
	private PlayerEntity player;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "FROM_TEAM_ID")
	private TeamEntity fromTeam;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TO_TEAM_ID")
	private TeamEntity toTeam;

	@Column(name = "BUYING_PRICE")
	private BigInteger buyingPrice;
	
	@Column(name = "DATE_TIME")
	private Timestamp dateTime;
}
