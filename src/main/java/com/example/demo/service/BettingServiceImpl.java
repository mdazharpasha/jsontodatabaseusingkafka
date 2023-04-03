package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.example.demo.Binding.BettingData;
import com.example.demo.Binding.SearchRequest;
import com.example.demo.Repo.BettingDataRepository;
@Service
@ComponentScan
public class BettingServiceImpl implements BettingService{

	@Autowired
	private BettingDataRepository betDataRepo;

	@Override
	public List<String> getGame() {
		List<String> gameName = betDataRepo.getGame();
		return gameName;
	}

	@Override
	public List<String> getStake() {
		List<String> stake = betDataRepo.getStake();
		return stake;
	}

	@Override
	public List<BettingData> getBettingData(SearchRequest request) {
		BettingData data = new BettingData();
		if(betDataRepo.getGame()!=null && ! betDataRepo.getGame().equals("")) {
			data.setGame(betDataRepo.getGame());
		}
		if( betDataRepo.getStake()!=null && ! betDataRepo.getStake().equals("")) {
			data.setStak(betDataRepo.getStake());
		}
		Example<BettingData> example = Example.of(data);
		List<BettingData> betData = betDataRepo.findAll(example);

		return betData;
	}

}
