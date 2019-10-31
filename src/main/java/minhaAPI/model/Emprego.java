package minhaAPI.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Emprego {
	

	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private Integer id;

	    @Column(nullable = false)
	    private String descricao;
	    
	    @Column(nullable = false)
	    private String requisitos;
	    
	    @Column(nullable = false)
	    private float salario;
	    
	    @Column(nullable = false)
	    private String contato;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public String getRequisitos() {
			return requisitos;
		}

		public void setRequisitos(String requisitos) {
			this.requisitos = requisitos;
		}

		public float getSalario() {
			return salario;
		}

		public void setSalario(float salario) {
			this.salario = salario;
		}

		public String getContato() {
			return contato;
		}

		public void setContato(String contato) {
			this.contato = contato;
		}
	    
}
