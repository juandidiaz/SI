import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class ConjuncionMeta extends ElementosPila{
	
	public ArrayList<String>elementos;
	
	
	public ConjuncionMeta(ArrayList<String>e)
	{
		elementos=e;
		tipo=3;
	}
	
	public ArrayList<String>getElementos()
	{
		return elementos;
	}
	
	public boolean seCumple(LinkedHashSet<String>el)
	{
		Iterator<String> it=el.iterator();
		ArrayList<String>aux=new ArrayList<String>();
		while(it.hasNext())
		{
			String s=it.next();
			aux.add(s);
		}
		
		if(aux.containsAll(elementos))
			return true;
		else
			return false;
	}
	
	
	public  ArrayList<String> combinaciones(){
        ArrayList<String> combinaciones = new ArrayList<>();
        combinacionesRec(elementos,combinaciones,"");
        return combinaciones;
    }

    public  void combinacionesRec(ArrayList<String> element,ArrayList<String>combinaciones,String palabra){
        ArrayList<String> copiatokens;
        String auxiliarpalabra;
        if(element.size() == 1){
            copiatokens = new ArrayList<String>(element);
            auxiliarpalabra = palabra + " " + copiatokens.remove(0);
            combinaciones.add(auxiliarpalabra);
            return;
        }
        for(int i=0;i<element.size();i++){
            copiatokens = new ArrayList<String>(element);
            auxiliarpalabra = palabra + " " + copiatokens.remove(i);
            combinacionesRec(copiatokens,combinaciones,auxiliarpalabra);
        }
    


    }

	@Override
	public int getTipo() {
		return tipo;
	}

}
