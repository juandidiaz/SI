import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Main {
	
	public static void main(String[] args)
	{
	
		Strips st=new Strips();
		
		//Elementos Operador1
		LinkedHashSet<String>pOp1=new LinkedHashSet<String>();
		LinkedHashSet<String>aOp1=new LinkedHashSet<String>();
		LinkedHashSet<String>sOp1=new LinkedHashSet<String>();
		
		//Elementos Operador2
		LinkedHashSet<String>pOp2=new LinkedHashSet<String>();
		LinkedHashSet<String>aOp2=new LinkedHashSet<String>();
		LinkedHashSet<String>sOp2=new LinkedHashSet<String>();
		
		//Elementos operador3
		LinkedHashSet<String>pOp3=new LinkedHashSet<String>();
		LinkedHashSet<String>aOp3=new LinkedHashSet<String>();
		LinkedHashSet<String>sOp3=new LinkedHashSet<String>();
		
		LinkedHashSet<String>meta=new LinkedHashSet<String>();
		
		LinkedHashSet<String>elementosInicial=new LinkedHashSet<String>();
		
		
		//Definicion de Meta que queremos cumplir
		ArrayList<String>objetivos=new ArrayList<String>();
		objetivos.add("d");
		objetivos.add("a");

		ConjuncionMeta m=new ConjuncionMeta(objetivos);
		
		//Definicion Operador 1
		//Precondiciones
		pOp1.add("b");
		pOp1.add("d");
		//Adicion
		aOp1.add("c");
		aOp1.add("e");
		//Supresion
		sOp1.add("d");
		
		//Definicion Operador 2
		//Precondiciones
		pOp2.add("b");
		//Adicion
		aOp2.add("a");
		//Supresion
		sOp2.add("c");
		sOp2.add("d");
		
		//Definicion Operador 3
		//Precondiciones
		pOp3.add("a");
		//Adicion
		aOp3.add("d");
		
		Operador op1=new Operador("OP1",pOp1,aOp1,sOp1);

		Operador op2=new Operador("OP2",pOp2,aOp2,sOp2);
		
		Operador op3=new Operador("OP3",pOp3,aOp3,sOp3);
		
		//Definicion Estado Inicial
		
		elementosInicial.add("b");
		elementosInicial.add("d");
		elementosInicial.add("e");
		Estado inicial=new Estado(elementosInicial);
		
		//Definicion de operadores posibles
		ArrayList<Operador>operadoresPosibles=new ArrayList<Operador>();
		
		operadoresPosibles.add(op1);
		operadoresPosibles.add(op2);
		operadoresPosibles.add(op3);
		
		//STRIPS
		ArrayList<Operador>plan=st.strips(inicial, m, operadoresPosibles);
		if(plan!=null)
		{
			System.out.println("---------------------");
			System.out.println("PLAN: ");
			for(int i=0;i<plan.size();i++)
		{
			if(i==plan.size()-1)
			{
				System.out.print(plan.get(i).getNombre());
			}
			else
			{
				System.out.print(plan.get(i).getNombre()+"-->");
			}
			
		}
		}
		else
		{
			System.out.print("Plan nulo");
		}
	}
	
		
}
	
	
