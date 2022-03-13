package com.se2.ebankingproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Entity
@Table
@Inheritance(strategy = InheritanceType.JOINED)
public class Transaction {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transactionId;
	
	@Column(nullable = false)
	@Min(value = 0)
	private long amount;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "accountId", foreignKey = @ForeignKey(name = "fk_account_transaction"))
	@JsonIgnoreProperties(value = "transactions")
	private Account account;

	@Column(updatable = false)
	@CreationTimestamp
	private LocalDateTime timeCreated;
	
	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}


	public LocalDateTime getTimeCreated() {
		return timeCreated;
	}

	public void setTimeCreated(LocalDateTime timeCreated) {
		this.timeCreated = timeCreated;
	}
	
	
	
}
