package com.se2.ebankingproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table
@PrimaryKeyJoinColumn(name = "giftId")
public class Gift extends com.se2.ebankingproject.Model.Transaction {

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn( name = "toAccountId" , foreignKey = @ForeignKey(name = "fk_toAccount_gift"))
	@JsonIgnoreProperties(value = {"fromGifts", "toGifts"})
	private Account toAccount;

	public Account getToAccount() {
		return toAccount;
	}

	public void setToAccount(Account toAccount) {
		this.toAccount = toAccount;
	}
	
	
}
