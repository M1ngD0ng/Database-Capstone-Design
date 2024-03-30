package examples.dto;

public class MovieDTO {
	
	private Integer Mid;
	private String Mtitle;
	private String Director_Dssn;
	
	public MovieDTO(Integer mid, String mtitle, String director_Dssn) {
		super();
		Mid = mid;
		Mtitle = mtitle;
		Director_Dssn = director_Dssn;
	}

	public Integer getMid() {
		return Mid;
	}

	public void setMid(Integer mid) {
		Mid = mid;
	}

	public String getMtitle() {
		return Mtitle;
	}

	public void setMtitle(String mtitle) {
		Mtitle = mtitle;
	}

	public String getDirector_Dssn() {
		return Director_Dssn;
	}

	public void setDirector_Dssn(String director_Dssn) {
		Director_Dssn = director_Dssn;
	}

	@Override
	public String toString() {
		return "MovieDTO [Mid=" + Mid + ", Mtitle=" + Mtitle + ", Director_Dssn=" + Director_Dssn + "]";
	}
}
