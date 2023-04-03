package com.example.demo.Binding;

import java.sql.Date;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name="Bet")
public class BettingData {
	
    @Id
	private int id;
	private String numbets;
	private List<String> Game;
	private List<String> stak;
	private String returns;
	private int clientId;
	private Date date;
}
