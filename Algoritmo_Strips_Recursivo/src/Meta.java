import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class Meta extends ElementosPila{
	
	private String elemento;
	
   public Meta(String e)
   {
	   elemento=e;
	   tipo=2;
   }
   
   
   public boolean seCumple(LinkedHashSet<String>s)
   {
	  Iterator<String>it=s.iterator();
      
	  while(it.hasNext())
	  {
		  String aux=it.next();
		  if(elemento.equals(aux))
			  return true;
	  }
	  return false;
   }
   
   public String getElemento()
   {
	   return elemento;
   }
   
   @Override
	public int getTipo() {
		return tipo;
	}
   
}
