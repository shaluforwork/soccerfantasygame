package com.soccerfantasy.app.service;

import java.math.BigInteger;
import java.sql.Timestamp;

import javax.transaction.Transactional;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.soccerfantasy.app.domain.PlayerEntity;
import com.soccerfantasy.app.domain.TeamEntity;
import com.soccerfantasy.app.domain.TransferEntity;
import com.soccerfantasy.app.domain.TransferListEntity;
import com.soccerfantasy.app.domain.UserEntity;
import com.soccerfantasy.app.model.request.TransferRequestModel;
import com.soccerfantasy.app.repository.TransferRepository;

@Service
public class TransferService {

	@Autowired
	private TransferRepository transferRepository;

	@Autowired
	private PlayerTransferValidationService transferValidationService;

	@Autowired
	private PlayerService playerService;

	@Autowired
	private UserService userService;

	@Autowired
	private TeamService teamService;

	@Autowired
	private TransferListService transferListService;

	@Transactional
	public Boolean buyPlayer(String userId, TransferRequestModel transferRequest) {
		UserEntity userEntity = userService.findUserByUserId(userId);
		TransferListEntity transferListEntity = transferListService.getTransferListById(transferRequest.getTransferListId());
		TeamEntity toTeamEntity = teamService.findTeamEntityByTeamById(transferRequest.getToTeam());
		PlayerEntity playerEntity = transferListEntity.getPlayer();
		TeamEntity fromTeamEntity = playerEntity.getTeam();
		BigInteger buyingPrice = transferListEntity.getAskingPrice();

		/** Check that User is authorized and owns the team team buying the players **/
		transferValidationService.validateBuyingTeamUser(userEntity, transferRequest);

		/** Check that the Player is on Transfer List and does not belong to the same team **/
		transferValidationService.validateNotSameTeam(fromTeamEntity, toTeamEntity);
		/** Check that there is enough balance to buy the player **/
		transferValidationService.validateBudget(buyingPrice, toTeamEntity);

		/** make new entry in the transfer table **/
		TransferEntity transfer = new TransferEntity();
		transfer.setPlayer(playerEntity);
		transfer.setFromTeam(fromTeamEntity);
		transfer.setToTeam(toTeamEntity);
		transfer.setBuyingPrice(buyingPrice);
		transfer.setDateTime(new Timestamp(DateTime.now(DateTimeZone.UTC).getMillis()));

		Double newMarketValue = playerEntity.getMarketValue().doubleValue() * ((double) 1.1 + (Math.random() * (double) 0.9));
		BigInteger updatedMarketValue = new BigInteger(newMarketValue.toString().substring(0, newMarketValue.toString().indexOf(".")));

		/** update player has been transferred, update player's team and new market price **/
		playerEntity.setTeam(toTeamEntity);
		playerEntity.setMarketValue(updatedMarketValue);

		/** increase fromTeam budget and decrease total value **/
		fromTeamEntity.setBudget(toTeamEntity.getBudget().add(buyingPrice));
		fromTeamEntity.setTotalValue(toTeamEntity.getTotalValue().subtract(buyingPrice));

		/** reduce toTeam budget and increment total value **/
		toTeamEntity.setBudget(toTeamEntity.getBudget().subtract(buyingPrice));
		toTeamEntity.setTotalValue(toTeamEntity.getTotalValue().add(updatedMarketValue));
		transferListEntity.setTransferred(true);

		transferRepository.save(transfer);
		teamService.saveUpdatedRecord(toTeamEntity);
		teamService.saveUpdatedRecord(toTeamEntity);
		playerService.saveUpdatedRecord(playerEntity);
		return true;
	}
}
