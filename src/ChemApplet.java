import java.applet.Applet;


public class ChemApplet extends Applet{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void init () {
		
		this.setSize(500, 550);

		//Прием входных параметров 
		String param = new String();
		// Прием входной строки 
		try{
			Global.input_string = getParameter("InputString");
		}catch(NullPointerException e){
			Global.input_string = null;
		}
		if (Global.input_string == null) {
			Global.input_string = Global.test_strings[0];
			Global.input_type = 2;
		}else{
			param = getParameter("InputType");
			try {
				if (param != null)
					Global.input_type = Integer.parseInt(param);
				else
					Global.input_type = 0;
			} catch (NumberFormatException e)
			{ Global.input_type = -1; }
		}
		
		Global.work_string = Global.input_string;
		ChemPanel chempanel = new ChemPanel();
		chempanel.init();
		chempanel.setLocation(1, 1);
		setLayout(null);
		add(chempanel);
	}

}
