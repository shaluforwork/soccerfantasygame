package com.soccerfantasy.app.domain;

import java.math.BigInteger;

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
@Table(name = "TRANSFER_LIST", schema = "FANTASYSOCCER")
public class TransferListEntity {

	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "PLAYER_ID")
	private PlayerEntity player;

	@Column(name = "ASKING_PRICE")
	private BigInteger askingPrice;

	@Column(name = "TRANSFERRED")
	private Boolean transferred;

}
