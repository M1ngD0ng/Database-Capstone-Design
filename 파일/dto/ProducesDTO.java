package examples.dto;

public class ProducesDTO {
	
	private String Producer_Pssn;
	private Integer Movie_Mid;
	
	public ProducesDTO(String producer_Pssn, Integer movie_Mid) {
		super();
		Producer_Pssn = producer_Pssn;
		Movie_Mid = movie_Mid;
	}
	public String getProducer_Pssn() {
		return Producer_Pssn;
	}
	public void setProducer_Pssn(String producer_Pssn) {
		Producer_Pssn = producer_Pssn;
	}
	public Integer getMovie_Mid() {
		return Movie_Mid;
	}
	public void setMovie_Mid(Integer movie_Mid) {
		Movie_Mid = movie_Mid;
	}
	
	@Override
	public String toString() {
		return "ProducesDTO [Producer_Pssn=" + Producer_Pssn + ", Movie_Mid=" + Movie_Mid + "]";
	}
	
	
}
