package examples.dto;

public class ProducerDTO {

	private String Pssn;
	private String Pname;
	
	public ProducerDTO(String pssn, String pname) {
		super();
		Pssn = pssn;
		Pname = pname;
	}
	
	public String getPssn() {
		return Pssn;
	}
	public void setPssn(String pssn) {
		Pssn = pssn;
	}
	public String getPname() {
		return Pname;
	}
	public void setPname(String pname) {
		Pname = pname;
	}
	
	@Override
	public String toString() {
		return "ProducerDTO [Pssn=" + Pssn + ", Pname=" + Pname + "]";
	}
	
}
