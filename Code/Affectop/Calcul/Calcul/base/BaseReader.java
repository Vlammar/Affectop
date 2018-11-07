package Calcul.base;

public class BaseReader implements BaseAdapter {
	public BaseReader() {

	}

	public String tagRequest(String tag, String token) {
		if (tag.equals("name")) {
			return nameRequest(token);
		}
		if (tag.equals("firstName")) {
			return firstNameRequest(token);
		}
		if (tag.equals("affectList")) {
			return affectListRequest(token);
		}
		return "error";
	}

	public String nameRequest(String token) {
		return "nameRequest";
	}

	public String firstNameRequest(String token) {

		return "firstNameRequest";
	}

	private String affectListRequest(String token) {

		return "affectListRequest";
	}
}
