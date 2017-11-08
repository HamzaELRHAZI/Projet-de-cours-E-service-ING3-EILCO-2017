package fr.eilco.ing.bean;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema="database01", name="hellotable")
public class HelloBean implements Serializable{
	private  static final long seriaVersionUID= 1L;
	private int id;
	
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Id
	@GeneratedValue
	@Column(name="pk_hello")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
		}

	
}
