package run;

import br.com.appbackend.Model.Model;
import br.com.appbackend.Object.Student;

public class Teste {

	public static void main(String[] args) {
		
		Model buscador = new Model();
		
		Student student1 = buscador.searchByCode(16);
		if(student1 != null)
		{
			System.out.println("=======================UM ALUNO=======================");
			System.out.println(student1.toString());
		}
		
		Student student2 = buscador.searchByCode(14);
		if(student2 != null)
		{
			System.out.println("=======================UM ALUNO=======================");
			System.out.println(student2.toString());
		}
		
		Student student3 = buscador.searchByCode(15);
		if(student3 != null)
		{
			System.out.println("=======================UM ALUNO=======================");
			System.out.println(student3.toString());
		}
		
	}
}
