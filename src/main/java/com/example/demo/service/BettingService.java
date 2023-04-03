package com.example.demo.service;

import java.util.List;

import com.example.demo.Binding.BettingData;
import com.example.demo.Binding.SearchRequest;

public interface BettingService {
	public List<String> getGame();
	public List<String> getStake();
	public List<BettingData> getBettingData(SearchRequest request);
}
