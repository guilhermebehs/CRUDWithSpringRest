package br.com.guilhermebehs.data.vos;

import java.io.Serializable;
import java.util.Objects;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.github.dozermapper.core.Mapping;


@JsonPropertyOrder({"id", "last_name", "name", "birth", "address"})
public class ClientVO extends RepresentationModel<ClientVO> implements Serializable, Cloneable {


	private static final long serialVersionUID = 4735342484086033668L;

	@Mapping("id")
	@JsonProperty("id")
	private Long key;
	
	private String name;
	
	private String lastName;
	
	private String birth;
	
	private String address;
	
	private Boolean enabled;
		

	public Long getKey() {
		return key;
	}
	public void setKey(Long key) {
		this.key = key;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getBirth() {
		return birth;
	}
	public void setBirth(String birth) {
		this.birth = birth;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Boolean getEnabled() {
		return enabled;
	}
	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + Objects.hash(address, birth, enabled, key, lastName, name);
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ClientVO other = (ClientVO) obj;
		return Objects.equals(address, other.address) && Objects.equals(birth, other.birth)
				&& Objects.equals(enabled, other.enabled) && Objects.equals(key, other.key)
				&& Objects.equals(lastName, other.lastName) && Objects.equals(name, other.name);
	}
	
	@Override
	 public Object clone() {
	        try {
	            return super.clone();
	        } catch (CloneNotSupportedException e) {
	            System.out.println("Cloning not allowed.");
	            return this;
	        }
	    }
	
}
