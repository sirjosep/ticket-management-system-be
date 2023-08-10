package com.lawencon.ticketjosep.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_pic_assignment")
public class PicAssignment extends Base {
	@ManyToOne
	@JoinColumn(name = "pic_id", nullable = false)
	private User pic;
	
	@ManyToOne
	@JoinColumn(name = "costumer_id", nullable = false)
	private User costumer;

	public User getPic() {
		return pic;
	}

	public void setPic(User pic) {
		this.pic = pic;
	}

	public User getCostumer() {
		return costumer;
	}

	public void setCostumer(User costumer) {
		this.costumer = costumer;
	}

}
