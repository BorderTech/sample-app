package com.sample.app.model.client;

import java.io.Serializable;
import java.util.Objects;

/**
 * Client details.
 */
public class ClientDetail implements Serializable {

	private String clientId;
	private String name;
	private String abn;
	private String email;
	private AddressDetail address;

	/**
	 * @return the client id
	 */
	public String getClientId() {
		return clientId;
	}

	/**
	 * @param clientId the client id
	 */
	public void setClientId(final String clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return the client name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the client name
	 */
	public void setName(final String name) {
		this.name = name;
	}

	/**
	 * @return the client ABN
	 */
	public String getAbn() {
		return abn;
	}

	/**
	 * @param abn the client ABN
	 */
	public void setAbn(final String abn) {
		this.abn = abn;
	}

	/**
	 * @return the client email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the client email
	 */
	public void setEmail(final String email) {
		this.email = email;
	}

	/**
	 * @return the address details
	 */
	public AddressDetail getAddress() {
		return address;
	}

	/**
	 * @param address the address details
	 */
	public void setAddress(final AddressDetail address) {
		this.address = address;
	}

	@Override
	public int hashCode() {
		int hash = 5;
		hash = 59 * hash + Objects.hashCode(this.clientId);
		return hash;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final ClientDetail other = (ClientDetail) obj;
		if (!Objects.equals(this.clientId, other.clientId)) {
			return false;
		}
		return true;
	}

}
