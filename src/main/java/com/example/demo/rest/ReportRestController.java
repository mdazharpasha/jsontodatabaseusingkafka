package com.example.demo.rest;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.Binding.BettingData;
import com.example.demo.Binding.SearchRequest;
import com.example.demo.service.BettingService;


@RestController
public class ReportRestController {

	@Autowired
	private BettingService bettingService;

	@GetMapping("/gameName")
	public ResponseEntity<List<String>> getGame() {
		List<String> gameNames = bettingService.getGame();
		return new ResponseEntity<>(gameNames,HttpStatus.OK);
	}
	@GetMapping("/stack")
	public ResponseEntity<List<String>> getStak(){
		List<String> stakes = bettingService.getStake();
		return new ResponseEntity<>(stakes,HttpStatus.OK);
	}

	@GetMapping("/getBettingData")
	public ResponseEntity<List<BettingData>> search(@RequestBody SearchRequest request){
		List<BettingData> bettingData = bettingService.getBettingData(request);
	return new ResponseEntity<>	(bettingData,HttpStatus.OK);
			}
}
