package examples.dto;

public class PerformsInDTO {

	private String Actor_Assn;
	private Integer Movie_Mid;
	
	public PerformsInDTO(String actor_Assn, Integer movie_Mid) {
		super();
		Actor_Assn = actor_Assn;
		Movie_Mid = movie_Mid;
	}
	public String getActor_Assn() {
		return Actor_Assn;
	}
	public void setActor_Assn(String actor_Assn) {
		Actor_Assn = actor_Assn;
	}
	public Integer getMovie_Mid() {
		return Movie_Mid;
	}
	public void setMovie_Mid(Integer movie_Mid) {
		Movie_Mid = movie_Mid;
	}
	
	@Override
	public String toString() {
		return "PerformsInDTO [Actor_Assn=" + Actor_Assn + ", Movie_Mid=" + Movie_Mid + "]";
	}
	
}
