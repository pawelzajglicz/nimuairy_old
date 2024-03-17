package com.nimuairy.battle.web;

import com.nimuairy.battle.core.Battle;
import com.nimuairy.battle.core.BattleCreator;
import com.nimuairy.battle.core.HumanPlayer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/battles")
@RestController
@Slf4j
public class BattleController {

	@GetMapping("/basic")
	public ResponseEntity<Battle> getBasicBattle() {

		return ResponseEntity.ok(BattleCreator.createBattle(new HumanPlayer("Left"), new HumanPlayer("Right")));
	}
}
