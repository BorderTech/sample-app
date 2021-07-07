package com.sample.app.model.client;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * Client details.
 */
public class ClientDetail implements Serializable {

	private String clientId;
	@NotBlank
	private String name;
	@NotBlank
	private String abn;
	@Email
	private String email;
	private AddressDetail address;

	private Integer sampleInt;
	private Date sampleDate;
	private Date sampleOnlyDate;
	private LocalDate sampleLocalDate;
	private LocalDateTime sampleLocalDateTime;

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

	/**
	 * @return the sample integer
	 */
	public Integer getSampleInt() {
		return sampleInt;
	}

	/**
	 * @param sampleInt the sample integer
	 */
	public void setSampleInt(final Integer sampleInt) {
		this.sampleInt = sampleInt;
	}

	/**
	 * @return the sample date
	 */
	public Date getSampleDate() {
		return sampleDate;
	}

	/**
	 * @param sampleDate the sample date
	 */
	public void setSampleDate(final Date sampleDate) {
		this.sampleDate = sampleDate;
	}

	/**
	 * @return the sample only date
	 */
	public Date getSampleOnlyDate() {
		return sampleOnlyDate;
	}

	/**
	 * @param sampleOnlyDate the sample only date
	 */
	public void setSampleOnlyDate(final Date sampleOnlyDate) {
		this.sampleOnlyDate = sampleOnlyDate;
	}

	/**
	 * @return the sample local date
	 */
	public LocalDate getSampleLocalDate() {
		return sampleLocalDate;
	}

	/**
	 * @param sampleLocalDate the sample local date
	 */
	public void setSampleLocalDate(final LocalDate sampleLocalDate) {
		this.sampleLocalDate = sampleLocalDate;
	}

	/**
	 * @return the sample local date time
	 */
	public LocalDateTime getSampleLocalDateTime() {
		return sampleLocalDateTime;
	}

	/**
	 * @param sampleLocalDateTime the sample local date time
	 */
	public void setSampleLocalDateTime(final LocalDateTime sampleLocalDateTime) {
		this.sampleLocalDateTime = sampleLocalDateTime;
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
