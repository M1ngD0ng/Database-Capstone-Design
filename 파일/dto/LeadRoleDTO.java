package examples.dto;

public class LeadRoleDTO {
	
	private String actor_Assn1;
	private Integer movie_Mid;
	private String actor_assn2;
	
	public LeadRoleDTO(String actor_Assn1, Integer movie_Mid, String actor_assn2) {
		super();
		this.actor_Assn1 = actor_Assn1;
		this.movie_Mid = movie_Mid;
		this.actor_assn2 = actor_assn2;
	}
	
	public String getActor_Assn1() {
		return actor_Assn1;
	}
	public void setActor_Assn1(String actor_Assn1) {
		this.actor_Assn1 = actor_Assn1;
	}
	public Integer getMovie_Mid() {
		return movie_Mid;
	}
	public void setMovie_Mid(Integer movie_Mid) {
		this.movie_Mid = movie_Mid;
	}
	public String getActor_assn2() {
		return actor_assn2;
	}
	public void setActor_assn2(String actor_assn2) {
		this.actor_assn2 = actor_assn2;
	}
	
	@Override
	public String toString() {
		return "LeadRoleDTO [actor_Assn1=" + actor_Assn1 + ", movie_Mid=" + movie_Mid + ", actor_assn2=" + actor_assn2
				+ "]";
	}
}
