package com.api_lcms.dto;

public class AuthorityDTO {
	private Integer authorityId;
    private String description;
    private boolean allowView;
    private boolean allowCreate;
    private boolean allowUpdate;
	public Integer getAuthorityId() {
		return authorityId;
	}
	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public boolean isAllowView() {
		return allowView;
	}
	public void setAllowView(boolean allowView) {
		this.allowView = allowView;
	}
	public boolean isAllowCreate() {
		return allowCreate;
	}
	public void setAllowCreate(boolean allowCreate) {
		this.allowCreate = allowCreate;
	}
	public boolean isAllowUpdate() {
		return allowUpdate;
	}
	public void setAllowUpdate(boolean allowUpdate) {
		this.allowUpdate = allowUpdate;
	}
    
    
}
