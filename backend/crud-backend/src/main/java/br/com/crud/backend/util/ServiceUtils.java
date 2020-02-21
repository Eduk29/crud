package br.com.crud.backend.util;

public class ServiceUtils {
	

	public static String getModeSearch(String filterQuery) {
		if (filterQuery != null) {
			return filterQuery.substring(0, filterQuery.indexOf("="));
		}
		return "default";
	}

	public static String getParamSearch(String filterQuery) {
		if (filterQuery != null) {
			return filterQuery.substring(filterQuery.indexOf("=") + 1, filterQuery.length());
		}
		return "";
	}

	public static void removeDoubleQuotes(String stringWithDoubleQuotes) {
		stringWithDoubleQuotes = stringWithDoubleQuotes.replace("\"", "");
	}
	
	
}
