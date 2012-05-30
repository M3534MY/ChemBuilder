import java.util.ArrayList;
import java.util.List;


public class Substance {
	int type;
	List <Radikal> radikals = new ArrayList<Radikal>();
	int c_count;
	List <Integer> special_info = new ArrayList<Integer>();
	int sub_type;
	List <Integer> sub_special_info = new ArrayList<Integer>();
	
	public void Print(){
		System.out.println("substance.type: "+this.type);
		System.out.println("substance.c_count: "+this.c_count);
		System.out.println("substance.sub_type: "+this.sub_type);
		System.out.println("substance.special_info: "+this.special_info);
		System.out.println("substance.sub_special_info: "+this.sub_special_info);
		System.out.print("substance.radikals: ");
		for(int i=0;i<this.radikals.size();i++){
			System.out.print("["+this.radikals.get(i).place+","+this.radikals.get(i).type+"]; ");
		}
		System.out.println();
	}
}
