package com.se2.ebankingproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table
@PrimaryKeyJoinColumn(name = "giftId")
public class Gift extends com.se2.ebankingproject.Model.Transaction {

	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private int giftId;
	
//	@ManyToOne(fetch = FetchType.EAGER)
//	@JoinColumn(name = "fromAccountId", foreignKey = @ForeignKey(name = "fk_fromAccount_gift"))
//	@JsonIgnoreProperties(value = {"fromGifts", "toGifts"})
//	private Account fromAccount;
	
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
