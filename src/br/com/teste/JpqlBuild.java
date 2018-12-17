package br.com.teste;

public class JpqlBuild {

	StringBuilder jpql = new StringBuilder();
	
	public JpqlBuild from(Class<Entidade> class1) {
		jpql.append(" FROM ");
		jpql.append(class1.getCanonicalName());
		return this;
	}
	
	public JpqlBuild as(String string) {
		jpql.append(" ");
		jpql.append(string);
		jpql.append(" ");
		return this;
	}

	public JpqlBuild where(String string) {
		jpql.append(" WHERE ");
		jpql.append(string);
		return this;
		
	}

	public JpqlBuild and(String string) {
		jpql.append(" and ");
		jpql.append(string);
		return this;
		
	}

	public JpqlBuild join(Class<Entidade> class1) {
		jpql.append(" JOIN ");
		jpql.append(class1.getCanonicalName());
		return this;
		
	}

	public StringBuilder getJpql() {
		return jpql;
	}

	public JpqlBuild on(Class<Entidade> class1) {
		return this;
		
	}

}
