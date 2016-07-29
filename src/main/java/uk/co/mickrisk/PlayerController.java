package uk.co.mickrisk;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class PlayerController {

    @RequestMapping("/player/{name}")
    public List<Player> player(@PathVariable(value="name") String name) {
        
        Squad squad = getSquad();
        if(squad != null){
            List<Player> players = squad.getPlayers();
            if(players != null){
                if(!players.isEmpty()){
                    return players;
                }
            }
        }
        return new ArrayList<Player>();
    }
    
    private Squad getSquad(){
        RestTemplate restTemplate = new RestTemplate();
        Squad squad = restTemplate.getForObject("http://api.football-data.org/v1/teams/340/players", Squad.class);
        return squad;
    }
}
