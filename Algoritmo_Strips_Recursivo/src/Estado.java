import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Stack;



public class Estado implements Cloneable{
	
	private LinkedHashSet<String>elementos=new LinkedHashSet<String>();
	
	public Estado(LinkedHashSet<String>e)
	{
		elementos=e;
	}
	

	
	public LinkedHashSet<String>getElementos()
	{
		return elementos;
	}
	
	
	public Estado aplicarOperador(Operador o)
	{
		Estado aux=new Estado(this.elementos);
		
		LinkedHashSet<String>adi=o.getAdicion();
		LinkedHashSet<String>sup=o.getSupresion();
		
		Iterator<String>it=adi.iterator();
		
		while(it.hasNext())
		{
			String s=it.next();
			aux.elementos.add(s);
		}
		it=sup.iterator();
		while(it.hasNext())
		{
			String s=it.next();
			if(aux.elementos.contains(s))
			{
				aux.elementos.remove(s);
			}
		}
		return aux;
		
	}
	
	@Override
	
	public Object clone()
	{

		Estado obj=null;
		try {
	     obj=(Estado)super.clone();
		 obj.elementos=(LinkedHashSet<String>)this.elementos;
		}catch(CloneNotSupportedException ex)
		{
			System.out.println("No se ha podido duplicar");
		}
		return obj;
	}
	
	

	
}
