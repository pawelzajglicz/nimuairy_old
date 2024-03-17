package com.nimuairy.battle.core;

import com.nimuairy.battle.core.exceptions.PlayerSide;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@Builder
@Getter
@Entity
@NoArgsConstructor
@Setter
@Table(name = "heroes", schema = "nim_battle")
public class Hero extends Character {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Transient
	private int attack;
	@Transient
	private boolean fieldable;
	@Transient
	private int initiative;
	@Transient
	private String name;
	@Transient
	private boolean wallable;

	public Hero(int healthPoints) {
		super(healthPoints);
	}

	public Hero(int healthPoints, PlayerSide side) {
		super(healthPoints, side);
	}
}
