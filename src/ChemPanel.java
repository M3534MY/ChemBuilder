import java.awt.Button;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.TextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class ChemPanel extends Panel{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	Button btn = new Button("send");
	Button cbtn = new Button("close");
	TextField inpt = new TextField("введите название элемента",1);
	ChemCanvas2d chemcanvas2d = new ChemCanvas2d();
    ChemCanvas3d bb = new ChemCanvas3d();

	
	public void init () {
		System.out.println("initialization");
		this.setSize(500, 550);
		//this.setBackground(Color.blue);

		Global.work_string = Global.input_string;
		
		//инициализация интерфейсов
		setLayout(null);

		inpt.setText("введите название элемента");
		inpt.setColumns(25);
		inpt.setSize(170, 20);
		inpt.setLocation(20, 89);
		btn.setSize(80, 20);
		btn.setLocation(20, 120);
		btn.addMouseListener(new MouseAdapter() {

			public void mouseClicked(MouseEvent e) {
			if (e.getButton() == MouseEvent.NOBUTTON)
			{
			inpt.setText("No button clicked...");
			} else if (e.getButton() == MouseEvent.BUTTON1) {
				Global.input_type=4;
				Global.input_string=inpt.getText();
				if(Global.input_string.contentEquals("введите название элемента")){
					Global.input_string=Global.test_strings[14];
				}else{
					Global.input_string=inpt.getText();
				}
				Global.work_string = Global.input_string;
				Global.DetectSubstance();
				
				init();
				/*System.out.println("init 4");
				inpt.setVisible(false);
				btn.setVisible(false);
				cbtn.setVisible(true);
				bb.setVisible(true);
				chemcanvas2d.setVisible(true);
				setLayout(null);
				System.out.println(Global.work_string);
				System.out.println("init 4. stage 2");
				bb = new ChemCanvas3d();
				bb.setSize(Global.work_substance.c_count*70,Global.work_substance.c_count*70);
				System.out.println(bb.getWidth());
				chemcanvas2d.setSize(200, 200);
				chemcanvas2d.setLocation(30, 30);
				add(chemcanvas2d);
				bb.setLocation(30, 50);
				
				bb.repaint();
				add(bb);
				bb.init();*/

				setSize(500, 551);
				repaint();
			} else if (e.getButton() == MouseEvent.BUTTON2) {
				inpt.setText("Button 2 clicked...");
			} else if (e.getButton() == MouseEvent.BUTTON3) {
				inpt.setText("Button 3 clicked...");
			}
			 
			}
		});
		
		cbtn.setSize(70,20);
		cbtn.setLocation(400, 10);
		cbtn.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			if (e.getButton() == MouseEvent.NOBUTTON)
			{
			inpt.setText("No button clicked...");
			} else 
			if (e.getButton() == MouseEvent.BUTTON1) {
				inpt.setText("Button 1 clicked...");
				bb.setVisible(false);
				//remove(bb);
				System.out.println(bb);
				//remove(chemcanvas2d);
				repaint();
				Global.input_type=1;
				Global.work_string="";
				Global.work_string=Global.input_string;
			} else if (e.getButton() == MouseEvent.BUTTON2) {
				inpt.setText("Button 2 clicked...");
			} else if (e.getButton() == MouseEvent.BUTTON3) {
				inpt.setText("Button 3 clicked...");
			}
			 
			}
		});

		add(btn);
		add(cbtn);
		add(inpt);	
		
		switch(Global.input_type){
		case 1:
			System.out.println("init 1");
			break;
		case 2:
			System.out.println(Global.work_string);
			Global.DetectSubstance();
			inpt.setVisible(false);
			btn.setVisible(false);
			cbtn.setVisible(false);
			chemcanvas2d = new ChemCanvas2d();;
			
			setLayout(null);
			chemcanvas2d.setSize(1, 1);			
			
			add(chemcanvas2d);
			break;
		case 3:
			System.out.println(Global.work_string);
			Global.DetectSubstance();
			inpt.setVisible(false);
			btn.setVisible(false);
			cbtn.setVisible(false);
			setLayout(null);
			bb = new ChemCanvas3d();
			this.setSize(600, 450);
			bb.setSize(600,450);
			System.out.println(bb.getWidth());
			bb.setLocation(0, 0);
			add(bb);
			bb.init();
			System.out.println("init 3");
			break;
		case 4:
			System.out.println("init 4");
			inpt.setVisible(false);
			btn.setVisible(false);
			cbtn.setVisible(true);
			chemcanvas2d.setVisible(true);
			bb.setVisible(true);
			setLayout(null);

			chemcanvas2d.setSize(1, 1);
			chemcanvas2d.setLocation(30, 30);
			add(chemcanvas2d);
			System.out.println("init 4. stage 2");
			bb.setSize(500,500);
			System.out.println(bb.getWidth());
			bb.setLocation(1, 1);
			bb.init();
			add(bb);
			chemcanvas2d.repaint();
			System.out.println(chemcanvas2d.getSize());
			this.setSize(500, 550);
			break;
		}
		System.out.println("stop initialization");
   }
   

   // Вывод в апплет значений полученных
   // и преобразованных параметров
	public void paint (Graphics dr) {
		System.out.println("__________________________________");

		switch(Global.input_type){
		case 1:
			System.out.println("paint 1");
			setLayout(null);
			inpt.setVisible(true);
			btn.setVisible(true);
			cbtn.setVisible(false);
			chemcanvas2d.setVisible(false);
			break;
		case 2:

			break;
		case 3:
			inpt.setVisible(false);
			btn.setVisible(false);
			cbtn.setVisible(false);
			bb.setSize(600,450);
			bb.repaint();
			
			System.out.println("paint 3");
			break;
		case 4:	
			System.out.println("paint 4");
			inpt.setVisible(false);
			btn.setVisible(false);
			cbtn.setVisible(true);
			cbtn.setLocation(400,10);
			chemcanvas2d.setVisible(true);
			setLayout(null);
			//System.out.println(Global.work_string);
			//chemcanvas2d.setSize(200, 200);
			//chemcanvas2d.setLocation(30, 30);
			//add(chemcanvas2d);
			
			//bb.setSize(500,500);
			//bb.setLocation(0, 0);
			bb.repaint();
			System.out.println(Global.max_y);
			//chemcanvas2d.setLocation(1,500-Global.max_y+Global.min_y);
			//chemcanvas2d.repaint();
			setComponentZOrder(chemcanvas2d, 3);
			setComponentZOrder(bb, 4);
			System.out.println(getComponentZOrder(bb));
			System.out.println(getComponentZOrder(chemcanvas2d));
			System.out.println(chemcanvas2d.getSize());
			System.out.println(chemcanvas2d.Get_Height());
			bb.setLocation(1, chemcanvas2d.Get_Height()-120);
			this.setSize(500, 550+chemcanvas2d.Get_Height()-120);
			System.out.println("this.size "+this.getSize());
			//bb.repaint();
			//chemcanvas2d.repaint();
			//this.setSize(500, 550);
			//System.out.println((int)((2-2.5)*2)%2));
			//System.out.println(bb.getWidth());*/
			
		}
      //dr.drawString ("work_string   : " + work_string   , 20, 40);
      //dr.drawString ("C_Count   : " + C_Count   , 20, 60);
      //dr.drawString ("H_Count   : " + H_Count   , 20, 80);
      //if (error_in_name){
	  //  	dr.drawString ("Error   : " + error_string   , 20, 100);
      //}else{
    	//  dr.drawString ("Error   : " + "Ошибки в названии отсутствуют"   , 20, 100);
      //}
      //dr.drawString ("WidthBox  : " + WidthBox1  , 20, 120);

      //add(chamcanvas2d);
      //dr.drawString ("AreaBox   : " + AreaBox1   , 20, 140);
      //dr.drawString ("floatBox  : " + floatBox1  , 20, 170);
      //dr.drawString ("activeBox : " + activeBox1 , 20, 220);

	}
}
