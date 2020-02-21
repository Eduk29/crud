package br.com.crud.backend.interfaces;

public interface ServiceUtilsInterface {
	
	public String getModeSearch(String filterQuery);
	public String getParamSearch(String filterQuery);
	public void removeDoubleQuotes (String stringWithDoubleQuotes);
}
