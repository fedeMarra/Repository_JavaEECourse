package ardea.events;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Match extends Event {
	@ManyToOne
	@JoinColumn(name="team1")
	private Team team1;
	
	@ManyToOne
	@JoinColumn(name="team2")
	private Team team2;
	
	Match(){}
	
	public Match(Team t1, Team t2, String price, String date) {
		super (t1 + "-" + t2, price, date);
		this.team1 = t1;
		this.team2 = t2;
	}

	@Override
	public String toString() {
		return String.format("Match [team1=%s, team2=%s]", team1, team2);
	}
	
	

}
