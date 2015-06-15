package eReleveEJB;


import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="Note")
public class Note implements Serializable {

	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private long id;
	

	private String note;
	
	
	private Eleve eleve;
	
	
	private Module module;
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	public long getId() {
		return id;
	}

    
	public void setId(long id) {
		this.id = id;
	}
	
	
	@Column(name="Note")
	public String getNote() {
		return note;
	}


	public void setNote(String note) {
		this.note = note;
	}

	
	@ManyToOne
	@JoinColumn(name = "idEleve")
	public Eleve getEleve() {
		return eleve;
	}

	public void setEleve(Eleve eleve) {
		this.eleve = eleve;
	}
	
	
	@ManyToOne
	@JoinColumn(name = "idModule")
	public Module getModule() {
		return module;
	}

	public void setModule(Module module) {
		this.module = module;
	}

}
