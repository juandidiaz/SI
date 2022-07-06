import java.util.ArrayList;
import java.util.LinkedHashSet;

public class Operador extends ElementosPila{

	private String nombre;
	private LinkedHashSet<String>precondiciones=new LinkedHashSet<String>();
	private LinkedHashSet<String>adicion=new LinkedHashSet<String>();
	private LinkedHashSet<String>supresion=new LinkedHashSet<String>();
	
	public Operador(String n,LinkedHashSet<String>p,LinkedHashSet<String>a,LinkedHashSet<String>s)
	{
		nombre=n;
		precondiciones=p;
		adicion=a;
		supresion=s;
		tipo=1;
	}
	
	public String getNombre()
	{
		return nombre;
	}
	
	public LinkedHashSet<String>getPrecondiciones()
	{
		return precondiciones;
	}
	
	public LinkedHashSet<String>getAdicion()
	{
		return adicion;
	}
	
	public LinkedHashSet<String>getSupresion()
	{
		return supresion;
	}
	
	public boolean sePuedeEjecutar(LinkedHashSet<String>Estado)
	{
		if(!this.precondiciones.isEmpty())
		{
			if(Estado.containsAll(this.precondiciones))
		
			return true;
		else
			return false;
		}
		else
		{
			return true;
		}
	  
	}

	@Override
	public int getTipo() {
		return tipo;
	}
}
