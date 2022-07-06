import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.Stack;



public class Strips {


	public ArrayList<Operador> strips(Estado eInicial,ConjuncionMeta m,ArrayList<Operador>operadoresPosibles)
	{
		ArrayList<Operador>pl=new ArrayList<Operador>();
		Stack<ElementosPila>p=new Stack<ElementosPila>();
		
		
		p.push(m);
		
		pl=strips(eInicial,p,operadoresPosibles);
		
		if(pl==null)return null;
		else {
			Estado estadoSussman=eInicial;
			for(Operador o : pl)
			{
				estadoSussman=estadoSussman.aplicarOperador(o);
			}
			
			if(estadoSussman.getElementos().containsAll(m.getElementos()))
			{
				System.out.println("Estado final: "+eInicial.getElementos());
				return pl;
				
			}
			else
				{
				System.out.println("Ha ocurrido la Anomalia de Sussman");
				return null;
				}
			
		}
		
		
	}
	
	public ArrayList<Operador> strips(Estado actual,Stack<ElementosPila>p,ArrayList<Operador>operadoresPosibles)
	{
		if(p.isEmpty())
		{
			return new ArrayList<Operador>();
		}
		
		else
		{
			ElementosPila cima=p.peek();
	
			
			System.out.println("Estado actual: "+actual.getElementos());
			
			switch(cima.getTipo())
			{
			case 1:
				System.out.println("Cima: "+((Operador)cima).getNombre());
				System.out.println("-----------------------------------");
				if(hayCiclo(p,actual,(Operador)cima))
					{
					System.out.println("Hay ciclo");
					return null;
					}
				else if(((Operador)cima).sePuedeEjecutar(actual.getElementos()))
				{
					Operador o=(Operador)p.pop();
					Estado Eact=actual.aplicarOperador(o);
					System.out.println("Se aplica el operador "+o.getNombre());
					ArrayList<Operador>siguiente=strips(Eact,p,operadoresPosibles);
					if(siguiente!=null)
					{
						ArrayList<Operador>Plan=new ArrayList<Operador>();
						Plan.add(o);
						Plan.addAll(siguiente);
						return Plan;
					}
					return null;
					
				}
				else
				{
					Iterator<String>it=((Operador)cima).getPrecondiciones().iterator();
					ArrayList<String>conjuncion=new ArrayList<String>();
					while(it.hasNext())
					{
					String s=it.next();
					conjuncion.add(s);
					}
					ConjuncionMeta c=new ConjuncionMeta(conjuncion);
					Stack<ElementosPila>pilaCopia=new Stack<ElementosPila>();
					pilaCopia=(Stack<ElementosPila>)p.clone();
					pilaCopia.push(c);
					ArrayList<Operador>siguiente=strips(actual,pilaCopia,operadoresPosibles);
					if(siguiente!=null)
					{
						return siguiente;
					}
				}
				break;
				
			case 2:
				System.out.println("Cima: "+((Meta)cima).getElemento());
				System.out.println("-----------------------------------");
				if(((Meta)cima).seCumple(actual.getElementos()))
				{
					System.out.println("Se cumple la meta, "+((Meta)p.peek()).getElemento()+" se elimina de la pila");
					p.pop();
					return strips(actual,p,operadoresPosibles);
				}
				else 
				{
					
					ArrayList<Operador>operadores=buscarAcciones(operadoresPosibles,(Meta)cima);
					if(!operadores.isEmpty())
					{
						for(Operador op:operadores)
						{
							Stack<ElementosPila>copia=(Stack<ElementosPila>)p.clone();
							copia.push(op);
							Estado eCopia=(Estado)actual.clone();
							ArrayList<Operador>siguiente=strips(eCopia,copia,operadoresPosibles);
							if(siguiente!=null)
							{
								return siguiente;
								
							}
							
						}
					}
					else
					{
						System.out.println("No se han encontrado operadores para cumplir esta meta");
					}
				}
				break;
			case 3:
				if(((ConjuncionMeta)cima).seCumple(actual.getElementos()))
				{
					p.pop();
					return strips(actual,p,operadoresPosibles);
				}
				else
				{
					ArrayList<String>combinaciones=((ConjuncionMeta)cima).combinaciones();
						Stack<ElementosPila>pilaCopia=new Stack<ElementosPila>();
						pilaCopia=(Stack<ElementosPila>)p.clone();
						pilaCopia.pop();
						for(int i=0;i<combinaciones.size();i++)
						{
						String[]partes=combinaciones.get(i).split(" ");
						Estado eCopia=(Estado)actual.clone();
						for(int j=0;j<partes.length;j++)
						{
							if(!partes[j].equals(""))
							{
							Meta m=new Meta(partes[j]);
							pilaCopia.push(m);
							}
						}
						ArrayList<Operador>siguiente=strips(eCopia, pilaCopia, operadoresPosibles);
						if(siguiente!=null)
						{
							return siguiente;
						}
						}
						
				}
				break;
			}
		}
		return null;
	}
	
	private boolean hayCiclo(Stack<ElementosPila>pila,Estado actual,Operador o)
	{
	Stack<ElementosPila>aux=(Stack<ElementosPila>)pila.clone();
	ElementosPila cima=aux.peek();
	
	while(cima.getTipo()!=2)
	{
		cima=aux.pop();
		if(cima.getTipo()==2)
		{
				if(o.getPrecondiciones().contains(((Meta)cima).getElemento()))
				{
					return true;
				}
			}
		}
	return false;
	}
	
	private ArrayList<Operador>buscarAcciones(ArrayList<Operador>operadoresPosibles,Meta m)
	{
		ArrayList<Operador>ac=new ArrayList<Operador>();
		for(int i=0;i<operadoresPosibles.size();i++)
		{
			if(operadoresPosibles.get(i).getAdicion().contains(m.getElemento()))
				ac.add(operadoresPosibles.get(i));
			
		}
		return ac;
	}
	
}
	
	
	

