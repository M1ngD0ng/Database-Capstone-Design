package examples.dto;

public class ActorDTO {

	private String Assn;
	private String Aname;
	private String Director_Dssn;
	private String Producer_Pssn;
	
	public ActorDTO(String assn, String aname, String director_Dssn, String producer_Pssn) {
		super();
		Assn = assn;
		Aname = aname;
		Director_Dssn = director_Dssn;
		Producer_Pssn = producer_Pssn;
	}
	
	public String getAssn() {
		return Assn;
	}
	public void setAssn(String assn) {
		Assn = assn;
	}
	public String getAname() {
		return Aname;
	}
	public void setAname(String aname) {
		Aname = aname;
	}
	public String getDirector_Dssn() {
		return Director_Dssn;
	}
	public void setDirector_Dssn(String director_Dssn) {
		Director_Dssn = director_Dssn;
	}
	public String getProducer_Pssn() {
		return Producer_Pssn;
	}
	public void setProducer_Pssn(String producer_Pssn) {
		Producer_Pssn = producer_Pssn;
	}
	
	@Override
	public String toString() {
		return "ActorDTO [Assn=" + Assn + ", Aname=" + Aname + ", Director_Dssn=" + Director_Dssn + ", Producer_Pssn="
				+ Producer_Pssn + "]";
	}
	
}
