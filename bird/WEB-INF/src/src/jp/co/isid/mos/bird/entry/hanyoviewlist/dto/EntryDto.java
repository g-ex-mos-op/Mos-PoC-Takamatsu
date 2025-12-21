package jp.co.isid.mos.bird.entry.hanyoviewlist.dto;

import java.io.Serializable;

public class EntryDto implements Serializable {

	private String key;
	
	private String name;
    
    private String nameRyak;
	
	public EntryDto() {
	}
	
	public String getKey() {
		return key;
	}
	
	public void setKey(String key) {
		this.key = key;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

    public String getNameRyak() {
        return nameRyak;
    }

    public void setNameRyak(String nameRyak) {
        this.nameRyak = nameRyak;
    }
}
