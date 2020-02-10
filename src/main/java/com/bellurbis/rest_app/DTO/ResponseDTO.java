package com.bellurbis.rest_app.DTO;

import java.util.List;

import com.bellurbis.rest_app.models.UserModel;

public class ResponseDTO {
	private String timestamp;
	private int status;
	private List<UserModel> data;
	private String message;
	
	
	
	public ResponseDTO() {
		super();
	}
	public ResponseDTO( int status, List<UserModel> data, String message) {
		this.status = status;
		this.data = data;
		this.message = message;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}


	public String getTimestamp() {
		return timestamp;
	}
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<UserModel> getData() {
		return data;
	}

	public void setData(List<UserModel> data) {
		this.data = data;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "ResponseDTO [timestamp=" + timestamp + ", status=" + status + ", data=" + data + ", message=" + message
				+ "]";
	}
	
}
