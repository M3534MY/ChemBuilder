import java.awt.*;

public class ChemCanvas2d extends Canvas {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int height = 0 ;
	int width = 0;
	
   static public void DrawRadikal2D(Graphics dr,int x,int y,int Radical_Type, int direction){
	   //dr.drawString("R",x,y);
	   Font LowerIndexFont = new Font("Dialog", 0, 9);
	   Font NormalFont = new Font("Dialog", 0, 12);
	   //System.out.println("x:"+x);
	   //System.out.println("y:"+y);
	   //System.out.println(Math.sin(Math.toRadians(90)));
	   int H_Count;
	   int delta = 30;
	   int range = 0;
	   int x1, x2, y1, y2;
	   switch(Radical_Type){
	   case 1:
	   case 2:
	   case 3:
	   case 4:
	   case 5:
		   for(int i=0;i<Radical_Type;i++){
			   H_Count=2;
			   if(i==Radical_Type-1){
				   H_Count++;
			   }
			   range = delta*(i+1);
			   //System.out.println(range);
			   x1 = x+(int)(range*Math.cos(Math.toRadians(direction)));
			   y1 = y-(int)(range*Math.sin(Math.toRadians(direction)));
			   if(x1<Global.min_x){
				   Global.min_x = x1;
			   }
			   if(x1>Global.max_x){
				   Global.max_x = x1+15;
			   }
			   if(y1<Global.min_y){
				   Global.min_y = y1;
			   }
			   if(y1>Global.max_y){
				   Global.max_y = y1;
			   }
			   
			   //System.out.println("x"+i+": "+Integer.toString(x1));
			   //System.out.println("y"+i+": "+Integer.toString(y1));
			   //System.out.println("x2:"+Integer.toString(x+18+range*(int)(Math.cos(Math.toRadians(direction)))));
			   //System.out.println("y2:"+Integer.toString(y+14-range*(int)(Math.sin(Math.toRadians(direction)))));
			   dr.drawString("CH",x1,y1);
			   dr.setFont(LowerIndexFont);
			   if(H_Count>1){
				   dr.drawString(Integer.toString(H_Count),x1+18,y1+4);
			   }
			   dr.setFont(NormalFont);
	
			   dr.drawLine(x+8+(int)(8*Math.cos(Math.toRadians(direction)))+(int)((range-delta)*Math.cos(Math.toRadians(direction))), y-4-(int)(8*Math.sin(Math.toRadians(direction)))-(int)((range-delta)*Math.sin(Math.toRadians(direction))), x+8-(int)(8*Math.cos(Math.toRadians(direction)))+(int)((range)*Math.cos(Math.toRadians(direction))), y-4+(int)(8*Math.sin(Math.toRadians(direction)))-(int)((range)*Math.sin(Math.toRadians(direction))));
			   //dr.drawLine(x+(range)*(int)(Math.cos(Math.toRadians(direction))), y+(range)*(int)(Math.sin(Math.toRadians(direction))), x+37+(range)*(int)(Math.cos(Math.toRadians(direction))), y+(-37-range)*(int)(Math.sin(Math.toRadians(direction))));
		   }
		   break;
	   case 6:
		   for(int i=0;i<2;i++){
			   H_Count=2*i+1;
			   range = delta*(i+1);
			   //System.out.println(range);
			   //System.out.println("x"+i+": "+Integer.toString(x+(int)(range*Math.cos(Math.toRadians(direction)))));
			   //System.out.println("y"+i+": "+Integer.toString(y+(int)(range*Math.sin(Math.toRadians(direction)))));
			   //System.out.println("x2:"+Integer.toString(x+18+range*(int)(Math.cos(Math.toRadians(direction)))));
			   //System.out.println("y2:"+Integer.toString(y+14-range*(int)(Math.sin(Math.toRadians(direction)))));
			   dr.drawString("CH",x+(int)(range*Math.cos(Math.toRadians(direction))),y-(int)(range*Math.sin(Math.toRadians(direction))));
			   dr.setFont(LowerIndexFont);
			   if(H_Count>1){
				   dr.drawString(Integer.toString(H_Count),x+18+(int)(range*Math.cos(Math.toRadians(direction))),y+4-(int)(range*Math.sin(Math.toRadians(direction))));
			   }
			   dr.setFont(NormalFont);
	
			   dr.drawLine(x+8+(int)(8*Math.cos(Math.toRadians(direction)))+(int)((range-delta)*Math.cos(Math.toRadians(direction))), y-4-(int)(8*Math.sin(Math.toRadians(direction)))-(int)((range-delta)*Math.sin(Math.toRadians(direction))), x+8-(int)(8*Math.cos(Math.toRadians(direction)))+(int)((range)*Math.cos(Math.toRadians(direction))), y-4+(int)(8*Math.sin(Math.toRadians(direction)))-(int)((range)*Math.sin(Math.toRadians(direction))));
			   //dr.drawLine(x+(range)*(int)(Math.cos(Math.toRadians(direction))), y+(range)*(int)(Math.sin(Math.toRadians(direction))), x+37+(range)*(int)(Math.cos(Math.toRadians(direction))), y+(-37-range)*(int)(Math.sin(Math.toRadians(direction))));
		   }
		   x1 = x+(int)((range-2*delta)*Math.cos(Math.toRadians(direction+90)))+(int)((range-delta)*Math.cos(Math.toRadians(direction)));
		   y1 = y-(int)((range-2*delta)*Math.sin(Math.toRadians(direction+90)))-(int)((range-delta)*Math.sin(Math.toRadians(direction)));
		   x2 = x+(int)((range-delta)*Math.cos(Math.toRadians(direction+90)))+(int)((range-delta)*Math.cos(Math.toRadians(direction)));
		   y2 = y-(int)((range-delta)*Math.sin(Math.toRadians(direction+90)))-(int)((range-delta)*Math.sin(Math.toRadians(direction)));
		   dr.drawLine(x1+8+(int)(8*Math.cos(Math.toRadians(direction+90))), y1-4-(int)(8*Math.sin(Math.toRadians(direction+90))), x2+8-(int)(8*Math.cos(Math.toRadians(direction+90))), y2-4+(int)(8*Math.sin(Math.toRadians(direction+90))));
		   dr.drawString("CH",x2,y2);
		   dr.setFont(LowerIndexFont);
		   dr.drawString("3",x2+18,y2+4);
		   dr.setFont(NormalFont);
		   break;
	   }
	   
   }
	
   public void Draw2D(Graphics dr){
		height = 0 ;
		width = 0;
	   Font LowerIndexFont = new Font("Dialog", 0, 9);
	   Font NormalFont = new Font("Dialog", 0, 12);
	   dr.setFont(NormalFont);
	   int x = 30;
	   int y = 140;
	   int x1 = 0;
	   int y1 = 0;
	   int delta = 40;
	   boolean DrawFirstRadikal;
	   boolean DrawSecondradikal;
	   int H_Count;
	   switch(Global.work_substance.type){
	   case 0:
		   //dr.drawString("H",x-30,y);
		   //dr.drawLine(x-20, y-5, x-3, y-5);
		   for(int i=0;i<Global.work_substance.c_count;i++){
			   H_Count=2;
			   x1 = x+delta*i;
			   y1 = y;
			   if(x1<Global.min_x){
				   Global.min_x = x1;
			   }
			   if(x1>Global.max_x){
				   Global.max_x = x1;
			   }
			   if(y1<Global.min_y){
				   Global.min_y = y1;
			   }
			   if(y1>Global.max_y){
				   Global.max_y = y1;
			   }
			   if(i==0||i==Global.work_substance.c_count-1){
				   H_Count++;
			   }
			   DrawFirstRadikal = false;
			   DrawSecondradikal = false;
			   for(int j=0;j<Global.work_substance.radikals.size();j++){
				   if(Global.work_substance.radikals.get(j).place==i+1){
					   if(DrawSecondradikal){
						   Global.error_in_name = true;
					   }else{
						   if(DrawFirstRadikal){
							   DrawRadikal2D(dr,x1,y1,Global.work_substance.radikals.get(j).type,90);
							   DrawSecondradikal = true;
							   H_Count--;
							   //dr.drawLine(x+delta*i+4, y-12, x+delta*i+4, y-28);
						   }else{
							   DrawRadikal2D(dr,x1,y1,Global.work_substance.radikals.get(j).type,270);
							   DrawFirstRadikal = true;
							   H_Count--;
							   //dr.drawLine(x+delta*i+4, y+2, x+delta*i+4, y+18);
						   }
					   }
				   }
			   }
			   if(H_Count<0){
				   Global.error_in_name = true;
			   }
			   if(H_Count>0){
				   dr.drawString("CH",x1,y1);
				   dr.setFont(LowerIndexFont);
				   if(H_Count>1){
					   dr.drawString(Integer.toString(H_Count),x1+18,y1+4);
				   }
				   dr.setFont(NormalFont);
				   if(i<Global.work_substance.c_count-1){
					   dr.drawLine(x1+20, y1-5, x1+37, y1-5);
				   }
			   }else{
				   dr.drawString("C",x1,y1);
				   dr.drawLine(x1+10, y1-5, x1+37, y1-5);
			   }
			   /*dr.drawString("C",x+30*i,y);
			   dr.drawLine(x+30*i+10, y-5, x+30*i+27, y-5);//линия вправо
			   dr.drawLine(x+30*i+4, y-12, x+30*i+4, y-29);//линия вверх
			   dr.drawLine(x+30*i+4, y+2, x+30*i+4, y+19);//линия вниз
			   dr.drawString("H",x+30*i,y-30);
			   if(DrawH_Down){
				   dr.drawString("H",x+30*i,y+30);
			   }*/
		   }
		   break;
	   case 1:
		   x = 90;
		   int x2,y2;
		   int direction,angle;
		   int current_angle;
		   angle = 360/Global.work_substance.c_count;
		   System.out.println("а"+angle);
		   current_angle = 90+180/Global.work_substance.c_count;
		   for(int i=0;i<Global.work_substance.c_count;i++){
			   System.out.println(i);
			   H_Count=2;
			   
			   
			   direction = i*(360/Global.work_substance.c_count);
			   x1 = x+(int)(delta*Math.cos(Math.toRadians(direction)));
			   y1 = y+(int)(delta*Math.sin(Math.toRadians(direction)));
			   x2 = x+(int)(delta*Math.cos(Math.toRadians(direction-360/Global.work_substance.c_count)));
			   y2 = y+(int)(delta*Math.sin(Math.toRadians(direction-360/Global.work_substance.c_count)));
			   System.out.println(x1);
			   System.out.println(y1);
			   if(x1<Global.min_x){
				   Global.min_x = x1;
			   }
			   if(x1>Global.max_x){
				   Global.max_x = x1+20;
			   }
			   if(y1<Global.min_y){
				   Global.min_y = y1;
			   }
			   if(y1>Global.max_y){
				   Global.max_y = y1;
			   }
			   DrawFirstRadikal = false;
			   DrawSecondradikal = false;
			   for(int j=0;j<Global.work_substance.radikals.size();j++){
				   if(Global.work_substance.radikals.get(j).place==i+1){
					   if(DrawSecondradikal){
						   Global.error_in_name = true;
					   }else{
						   if(DrawFirstRadikal){
							   DrawRadikal2D(dr,x1,y1,Global.work_substance.radikals.get(j).type,direction-45);
							   DrawSecondradikal = true;
							   H_Count--;
							   //dr.drawLine(x+delta*i+4, y-12, x+delta*i+4, y-28);
						   }else{
							   DrawRadikal2D(dr,x1,y1,Global.work_substance.radikals.get(j).type,direction+45);
							   DrawFirstRadikal = true;
							   H_Count--;
							   //dr.drawLine(x+delta*i+4, y+2, x+delta*i+4, y+18);
						   }
					   }
				   }
			   }
			   if(H_Count<0){
				   Global.error_in_name = true;
			   }
			   if(H_Count>0){
				   dr.drawString("CH",x1,y1);
				   dr.setFont(LowerIndexFont);
				   if(H_Count>1){
					   dr.drawString(Integer.toString(H_Count),x1+18,y1+4);
				   }
				   dr.setFont(NormalFont);
				   System.out.println("cа"+current_angle);
				   System.out.println(8*Math.cos(Math.toRadians(current_angle)));
				   //dr.drawLine(x1+8+(int)(8*Math.cos(Math.toRadians(current_angle))), y1-4, x1+8+(int)(8*Math.cos(Math.toRadians(current_angle))), y2-4);
				   dr.drawLine(x1+8+(int)(8*Math.cos(Math.toRadians(current_angle))), y1-4-(int)(8*Math.sin(Math.toRadians(current_angle))), x2+8-(int)(8*Math.cos(Math.toRadians(current_angle))), y2-4+(int)(8*Math.sin(Math.toRadians(current_angle))));
				   //dr.drawLine(x1, y1, x2, y2);
			   }else{
				   dr.drawString("C",x1,y1);
				   dr.drawLine(x1+8+(int)(8*Math.cos(Math.toRadians(current_angle))), y1-4-(int)(8*Math.sin(Math.toRadians(current_angle))), x2+8-(int)(8*Math.cos(Math.toRadians(current_angle))), y2-4+(int)(8*Math.sin(Math.toRadians(current_angle))));
				   //dr.drawLine(x1, y1, x2, y2);
			   }
			   current_angle = current_angle - angle;
		   }
		   break;
	   case 2://алкадиен
	   case 3://алкен
		   for(int i=0;i<Global.work_substance.c_count;i++){
			   boolean special_connect = false; // специальная связь
			   H_Count=2;
			   x1 = x+delta*i;
			   y1 = y;
			   if(x1<Global.min_x){
				   Global.min_x = x1;
			   }
			   if(x1>Global.max_x){
				   Global.max_x = x1;
			   }
			   if(y1<Global.min_y){
				   Global.min_y = y1;
			   }
			   if(y1>Global.max_y){
				   Global.max_y = y1;
			   }
			   if(i==0||i==Global.work_substance.c_count-1){
				   H_Count++;
			   }
			   for(int j=0;j<Global.work_substance.special_info.size();j++){
				   if(i+1==Global.work_substance.special_info.get(j)){
					   special_connect = true;
					   H_Count--;
				   }
				   if(i==Global.work_substance.special_info.get(j)){
					   H_Count--;
				   }
			   }
			   DrawFirstRadikal = false;
			   DrawSecondradikal = false;
			   for(int j=0;j<Global.work_substance.radikals.size();j++){
				   if(Global.work_substance.radikals.get(j).place==i+1){
					   if(DrawSecondradikal){
						   Global.error_in_name = true;
					   }else{
						   if(DrawFirstRadikal){
							   DrawRadikal2D(dr,x1,y1,Global.work_substance.radikals.get(j).type,90);
							   DrawSecondradikal = true;
							   H_Count--;
							   //dr.drawLine(x+delta*i+4, y-12, x+delta*i+4, y-28);
						   }else{
							   DrawRadikal2D(dr,x1,y1,Global.work_substance.radikals.get(j).type,270);
							   DrawFirstRadikal = true;
							   H_Count--;
							   //dr.drawLine(x+delta*i+4, y+2, x+delta*i+4, y+18);
						   }
					   }
				   }
			   }
			   if(H_Count<0){
				   Global.error_in_name = true;
			   }
			   if(H_Count>0){
				   dr.drawString("CH",x1,y1);
				   dr.setFont(LowerIndexFont);
				   if(H_Count>1){
					   dr.drawString(Integer.toString(H_Count),x1+18,y1+4);
				   }
				   dr.setFont(NormalFont);
				   if(i<Global.work_substance.c_count-1){
					   //рисуем связь
					   if(special_connect){//двойная связь
						   dr.drawLine(x1+20, y1-3, x1+37, y1-3);
						   dr.drawLine(x1+20, y1-7, x1+37, y1-7);
					   }else{//одинарная связь
						   dr.drawLine(x1+20, y1-5, x1+37, y1-5);
					   }
				   }
			   }else{
				   dr.drawString("C",x1,y1);
				   //рисуем связь
				   if(special_connect){//двойная связь
					   dr.drawLine(x1+10, y1-3, x1+37, y1-3);
					   dr.drawLine(x1+10, y1-7, x1+37, y1-7);
				   }else{//одинарная связь
					   dr.drawLine(x1+10, y1-5, x1+37, y1-5);
				   }
			   }
		   }
		   break;
	   case 4://алкин
		   for(int i=0;i<Global.work_substance.c_count;i++){
			   boolean special_connect = false; // специальная связь
			   H_Count=2;
			   x1 = x+delta*i;
			   y1 = y;
			   if(x1<Global.min_x){
				   Global.min_x = x1;
			   }
			   if(x1>Global.max_x){
				   Global.max_x = x1;
			   }
			   if(y1<Global.min_y){
				   Global.min_y = y1;
			   }
			   if(y1>Global.max_y){
				   Global.max_y = y1;
			   }
			   if(i==0||i==Global.work_substance.c_count-1){
				   H_Count++;
			   }
			   for(int j=0;j<Global.work_substance.special_info.size();j++){
				   if(i+1==Global.work_substance.special_info.get(j)){
					   special_connect = true;
					   H_Count=H_Count-2;
				   }
				   if(i==Global.work_substance.special_info.get(j)){
					   H_Count=H_Count-2;
				   }
			   }
			   DrawFirstRadikal = false;
			   DrawSecondradikal = false;
			   for(int j=0;j<Global.work_substance.radikals.size();j++){
				   if(Global.work_substance.radikals.get(j).place==i+1){
					   if(DrawSecondradikal){
						   Global.error_in_name = true;
					   }else{
						   if(DrawFirstRadikal){
							   DrawRadikal2D(dr,x1,y1,Global.work_substance.radikals.get(j).type,90);
							   DrawSecondradikal = true;
							   H_Count--;
							   //dr.drawLine(x+delta*i+4, y-12, x+delta*i+4, y-28);
						   }else{
							   DrawRadikal2D(dr,x1,y1,Global.work_substance.radikals.get(j).type,270);
							   DrawFirstRadikal = true;
							   H_Count--;
							   //dr.drawLine(x+delta*i+4, y+2, x+delta*i+4, y+18);
						   }
					   }
				   }
			   }
			   if(H_Count<0){
				   Global.error_in_name = true;
			   }
			   if(H_Count>0){
				   dr.drawString("CH",x1,y1);
				   dr.setFont(LowerIndexFont);
				   if(H_Count>1){
					   dr.drawString(Integer.toString(H_Count),x1+18,y1+4);
				   }
				   dr.setFont(NormalFont);
				   if(i<Global.work_substance.c_count-1){
					   //рисуем связь
					   if(special_connect){//тройная связь
						   dr.drawLine(x1+20, y1-9, x1+37, y1-9);
						   dr.drawLine(x1+20, y1-1, x1+37, y1-1);
						   dr.drawLine(x1+20, y1-5, x1+37, y1-5);
					   }else{//одинарная связь
						   dr.drawLine(x1+20, y1-5, x1+37, y1-5);
					   }
				   }
			   }else{
				   dr.drawString("C",x1,y1);
				   //рисуем связь
				   if(special_connect){//тройная связь
					   dr.drawLine(x1+10, y1-9, x1+37, y1-9);
					   dr.drawLine(x1+10, y1-1, x1+37, y1-1);
					   dr.drawLine(x1+10, y1-5, x1+37, y1-5);
				   }else{//одинарная связь
					   dr.drawLine(x1+10, y1-5, x1+37, y1-5);
				   }
			   }
		   }
		   break;	
	   case 5://спирты(-ол)
		   for(int i=0;i<Global.work_substance.c_count;i++){
			   boolean special_connect = false; // специальная связь
			   H_Count=2;
			   x1 = x+delta*i;
			   y1 = y;
			   if(x1<Global.min_x){
				   Global.min_x = x1;
			   }
			   if(x1>Global.max_x){
				   Global.max_x = x1;
			   }
			   if(y1<Global.min_y){
				   Global.min_y = y1;
			   }
			   if(y1>Global.max_y){
				   Global.max_y = y1;
			   }
			   if(i==0||i==Global.work_substance.c_count-1){
				   H_Count++;
			   }
			   DrawFirstRadikal = false;
			   DrawSecondradikal = false;
			   for(int j=0;j<Global.work_substance.special_info.size();j++){
				   if(i+1==Global.work_substance.special_info.get(j)){
					   DrawFirstRadikal = true;
					   H_Count=H_Count-1;
					   dr.drawString("OH",x1,y1+30);
					   dr.drawLine(x1+4, y1+2, x1+4, y1+16);
					   Global.max_y = y1+30;
				   }

			   }
			   for(int j=0;j<Global.work_substance.sub_special_info.size();j++){
				   if(i+1==Global.work_substance.sub_special_info.get(j)){
					   special_connect = true;
					   switch(Global.work_substance.sub_type){
					   case 2:
						   H_Count--;
						   break;
					   case 3:
						   H_Count--;
						   break;
					   case 4:
						   H_Count=H_Count-2;
						   break;
					   }
					   
				   }
				   if(i==Global.work_substance.sub_special_info.get(j)){
					   H_Count--;
				   }
			   }

			   for(int j=0;j<Global.work_substance.radikals.size();j++){
				   if(Global.work_substance.radikals.get(j).place==i+1){
					   if(DrawSecondradikal){
						   Global.error_in_name = true;
					   }else{
						   if(DrawFirstRadikal){
							   DrawRadikal2D(dr,x1,y1,Global.work_substance.radikals.get(j).type,90);
							   DrawSecondradikal = true;
							   H_Count--;
							   //dr.drawLine(x+delta*i+4, y-12, x+delta*i+4, y-28);
						   }else{
							   DrawRadikal2D(dr,x1,y1,Global.work_substance.radikals.get(j).type,270);
							   DrawFirstRadikal = true;
							   H_Count--;
							   //dr.drawLine(x+delta*i+4, y+2, x+delta*i+4, y+18);
						   }
					   }
				   }
			   }
			   if(H_Count<0){
				   Global.error_in_name = true;
			   }
			   if(H_Count>0){
				   dr.drawString("CH",x1,y1);
				   dr.setFont(LowerIndexFont);
				   if(H_Count>1){
					   dr.drawString(Integer.toString(H_Count),x1+18,y1+4);
				   }
				   dr.setFont(NormalFont);
				   if(i<Global.work_substance.c_count-1){
					   if(special_connect){//Nная связь
						   switch(Global.work_substance.sub_type){
						   case 2:
							   dr.drawLine(x1+20, y1-3, x1+37, y1-3);
							   dr.drawLine(x1+20, y1-7, x1+37, y1-7);
							   break;
						   case 3:
							   dr.drawLine(x1+20, y1-3, x1+37, y1-3);
							   dr.drawLine(x1+20, y1-7, x1+37, y1-7);
							   break;
						   case 4:
							   dr.drawLine(x1+20, y1-9, x1+37, y1-9);
							   dr.drawLine(x1+20, y1-1, x1+37, y1-1);
							   dr.drawLine(x1+20, y1-5, x1+37, y1-5);
							   break;
						   }
					   }else{//одинарная связь
						   dr.drawLine(x1+20, y1-5, x1+37, y1-5);
					   }
				   }
			   }else{
				   dr.drawString("C",x1,y1);
				   dr.drawLine(x1+10, y1-5, x1+37, y1-5);
			   }
		   }
		   break;	
	   case 6://альдегид(-аль)
		   for(int i=0;i<Global.work_substance.c_count;i++){
			   boolean special_connect = false; // специальная связь
			   H_Count=2;
			   x1 = x+delta*i;
			   y1 = y;
			   if(x1<Global.min_x){
				   Global.min_x = x1;
			   }
			   if(x1>Global.max_x){
				   Global.max_x = x1;
			   }
			   if(y1<Global.min_y){
				   Global.min_y = y1;
			   }
			   if(y1>Global.max_y){
				   Global.max_y = y1;
			   }
			   if(i==0){
				   H_Count=0;
				   dr.drawString("O",x1-20,y1-12);
				   dr.drawLine(x1-10, y1-14, x1-2, y1-6);
				   dr.drawLine(x1-10, y1-18, x1-2, y1-10);
				   dr.drawString("H",x1-20,y1+12);
				   dr.drawLine(x1-10, y1+6, x1-2, y1-2);
				   Global.min_x = x1-20;
				   Global.min_y = y1-12;
				   Global.max_y = y1+12;
			   }
			   if(i==Global.work_substance.c_count-1){
				   H_Count++;
			   }
			   for(int j=0;j<Global.work_substance.sub_special_info.size();j++){
				   if(i+1==Global.work_substance.sub_special_info.get(j)){
					   special_connect = true;
					   switch(Global.work_substance.sub_type){
					   case 2:
						   H_Count--;
						   break;
					   case 3:
						   H_Count--;
						   break;
					   case 4:
						   H_Count=H_Count-2;
						   break;
					   }
					   
				   }
				   if(i==Global.work_substance.sub_special_info.get(j)){
					   H_Count--;
				   }
			   }
			   
			   DrawFirstRadikal = false;
			   DrawSecondradikal = false;
			   for(int j=0;j<Global.work_substance.radikals.size();j++){
				   if(Global.work_substance.radikals.get(j).place==i+1){
					   if(DrawSecondradikal){
						   Global.error_in_name = true;
					   }else{
						   if(DrawFirstRadikal){
							   DrawRadikal2D(dr,x1,y1,Global.work_substance.radikals.get(j).type,90);
							   DrawSecondradikal = true;
							   H_Count--;
							   //dr.drawLine(x+delta*i+4, y-12, x+delta*i+4, y-28);
						   }else{
							   DrawRadikal2D(dr,x1,y1,Global.work_substance.radikals.get(j).type,270);
							   DrawFirstRadikal = true;
							   H_Count--;
							   //dr.drawLine(x+delta*i+4, y+2, x+delta*i+4, y+18);
						   }
					   }
				   }
			   }
			   if(H_Count<0){
				   Global.error_in_name = true;
			   }
			   if(H_Count>0){
				   dr.drawString("CH",x1,y1);
				   dr.setFont(LowerIndexFont);
				   if(H_Count>1){
					   dr.drawString(Integer.toString(H_Count),x1+18,y1+4);
				   }
				   dr.setFont(NormalFont);
				   if(i<Global.work_substance.c_count-1){
					   if(special_connect){//Nная связь
						   switch(Global.work_substance.sub_type){
						   case 2:
							   dr.drawLine(x1+20, y1-3, x1+37, y1-3);
							   dr.drawLine(x1+20, y1-7, x1+37, y1-7);
							   break;
						   case 3:
							   dr.drawLine(x1+20, y1-3, x1+37, y1-3);
							   dr.drawLine(x1+20, y1-7, x1+37, y1-7);
							   break;
						   case 4:
							   dr.drawLine(x1+20, y1-9, x1+37, y1-9);
							   dr.drawLine(x1+20, y1-1, x1+37, y1-1);
							   dr.drawLine(x1+20, y1-5, x1+37, y1-5);
							   break;
						   }
					   }else{//одинарная связь
						   dr.drawLine(x1+20, y1-5, x1+37, y1-5);
					   }
				   }
			   }else{
				   dr.drawString("C",x1,y1);
				   if(special_connect){//Nная связь
					   switch(Global.work_substance.sub_type){
					   case 2:
						   dr.drawLine(x1+10, y1-3, x1+37, y1-3);
						   dr.drawLine(x1+10, y1-7, x1+37, y1-7);
						   break;
					   case 3:
						   dr.drawLine(x1+10, y1-3, x1+37, y1-3);
						   dr.drawLine(x1+10, y1-7, x1+37, y1-7);
						   break;
					   case 4:
						   dr.drawLine(x1+10, y1-9, x1+37, y1-9);
						   dr.drawLine(x1+10, y1-1, x1+37, y1-1);
						   dr.drawLine(x1+10, y1-5, x1+37, y1-5);
						   break;
					   }
				   }else{//одинарная связь
					   dr.drawLine(x1+10, y1-5, x1+37, y1-5);
				   }
			   }
		   }
		   break;
	   case 7://кетон(-он)
		   for(int i=0;i<Global.work_substance.c_count;i++){
			   boolean special_connect = false; // специальная связь
			   H_Count=2;
			   x1 = x+delta*i;
			   y1 = y;
			   if(x1<Global.min_x){
				   Global.min_x = x1;
			   }
			   if(x1>Global.max_x){
				   Global.max_x = x1;
			   }
			   if(y1<Global.min_y){
				   Global.min_y = y1;
			   }
			   if(y1>Global.max_y){
				   Global.max_y = y1;
			   }

			   if(i==0||i==Global.work_substance.c_count-1){
				   H_Count++;
			   }
			   for(int j=0;j<Global.work_substance.sub_special_info.size();j++){
				   if(i+1==Global.work_substance.sub_special_info.get(j)){
					   special_connect = true;
					   switch(Global.work_substance.sub_type){
					   case 2:
						   H_Count--;
						   break;
					   case 3:
						   H_Count--;
						   break;
					   case 4:
						   H_Count=H_Count-2;
						   break;
					   }
					   
				   }
				   if(i==Global.work_substance.sub_special_info.get(j)){
					   H_Count--;
				   }
			   }
			   
			   DrawFirstRadikal = false;
			   DrawSecondradikal = false;
			   for(int j=0;j<Global.work_substance.special_info.size();j++){
				   if(i+1==Global.work_substance.special_info.get(j)){
					   DrawFirstRadikal = true;
					   H_Count=H_Count-2;
					   dr.drawString("O",x1,y1+30);
					   dr.drawLine(x1+2, y1+2, x1+2, y1+16);
					   dr.drawLine(x1+6, y1+2, x1+6, y1+16);
					   Global.max_y = y1+30;
				   }

			   }
			   for(int j=0;j<Global.work_substance.radikals.size();j++){
				   if(Global.work_substance.radikals.get(j).place==i+1){
					   if(DrawSecondradikal){
						   Global.error_in_name = true;
					   }else{
						   if(DrawFirstRadikal){
							   DrawRadikal2D(dr,x1,y1,Global.work_substance.radikals.get(j).type,90);
							   DrawSecondradikal = true;
							   H_Count--;
							   //dr.drawLine(x+delta*i+4, y-12, x+delta*i+4, y-28);
						   }else{
							   DrawRadikal2D(dr,x1,y1,Global.work_substance.radikals.get(j).type,270);
							   DrawFirstRadikal = true;
							   H_Count--;
							   //dr.drawLine(x+delta*i+4, y+2, x+delta*i+4, y+18);
						   }
					   }
				   }
			   }
			   if(H_Count<0){
				   Global.error_in_name = true;
			   }
			   if(H_Count>0){
				   dr.drawString("CH",x1,y1);
				   dr.setFont(LowerIndexFont);
				   if(H_Count>1){
					   dr.drawString(Integer.toString(H_Count),x1+18,y1+4);
				   }
				   dr.setFont(NormalFont);
				   if(i<Global.work_substance.c_count-1){
					   if(special_connect){//Nная связь
						   switch(Global.work_substance.sub_type){
						   case 2:
							   dr.drawLine(x1+20, y1-3, x1+37, y1-3);
							   dr.drawLine(x1+20, y1-7, x1+37, y1-7);
							   break;
						   case 3:
							   dr.drawLine(x1+20, y1-3, x1+37, y1-3);
							   dr.drawLine(x1+20, y1-7, x1+37, y1-7);
							   break;
						   case 4:
							   dr.drawLine(x1+20, y1-9, x1+37, y1-9);
							   dr.drawLine(x1+20, y1-1, x1+37, y1-1);
							   dr.drawLine(x1+20, y1-5, x1+37, y1-5);
							   break;
						   }
					   }else{//одинарная связь
						   dr.drawLine(x1+20, y1-5, x1+37, y1-5);
					   }
				   }
			   }else{
				   dr.drawString("C",x1,y1);
				   if(special_connect){//Nная связь
					   switch(Global.work_substance.sub_type){
					   case 2:
						   dr.drawLine(x1+10, y1-3, x1+37, y1-3);
						   dr.drawLine(x1+10, y1-7, x1+37, y1-7);
						   break;
					   case 3:
						   dr.drawLine(x1+10, y1-3, x1+37, y1-3);
						   dr.drawLine(x1+10, y1-7, x1+37, y1-7);
						   break;
					   case 4:
						   dr.drawLine(x1+10, y1-9, x1+37, y1-9);
						   dr.drawLine(x1+10, y1-1, x1+37, y1-1);
						   dr.drawLine(x1+10, y1-5, x1+37, y1-5);
						   break;
					   }
				   }else{//одинарная связь
					   dr.drawLine(x1+10, y1-5, x1+37, y1-5);
				   }
			   }
		   }
		   break;
	   case 8://карбоновые кислоты
		   for(int i=0;i<Global.work_substance.c_count;i++){
			   boolean special_connect = false; // специальная связь
			   H_Count=2;
			   x1 = x+delta*i;
			   y1 = y;
			   if(x1<Global.min_x){
				   Global.min_x = x1;
			   }
			   if(x1>Global.max_x){
				   Global.max_x = x1;
			   }
			   if(y1<Global.min_y){
				   Global.min_y = y1;
			   }
			   if(y1>Global.max_y){
				   Global.max_y = y1;
			   }
			   if(i==0){
				   H_Count=0;
				   dr.drawString("O",x1-20,y1-12);
				   dr.drawLine(x1-10, y1-14, x1-2, y1-6);
				   dr.drawLine(x1-10, y1-18, x1-2, y1-10);
				   dr.drawString("HO",x1-29,y1+12);
				   dr.drawLine(x1-10, y1+6, x1-2, y1-2);
				   Global.min_x = x1-29;
				   Global.min_y = y1-12;
				   Global.max_y = y1+12;
			   }
			   if(i==Global.work_substance.c_count-1){
				   H_Count++;
			   }
			   for(int j=0;j<Global.work_substance.sub_special_info.size();j++){
				   if(i+1==Global.work_substance.sub_special_info.get(j)){
					   special_connect = true;
					   switch(Global.work_substance.sub_type){
					   case 2:
						   H_Count--;
						   break;
					   case 3:
						   H_Count--;
						   break;
					   case 4:
						   H_Count=H_Count-2;
						   break;
					   }
					   
				   }
				   if(i==Global.work_substance.sub_special_info.get(j)){
					   H_Count--;
				   }
			   }
			   
			   DrawFirstRadikal = false;
			   DrawSecondradikal = false;
			   for(int j=0;j<Global.work_substance.radikals.size();j++){
				   if(Global.work_substance.radikals.get(j).place==i+1){
					   if(DrawSecondradikal){
						   Global.error_in_name = true;
					   }else{
						   if(DrawFirstRadikal){
							   DrawRadikal2D(dr,x1,y1,Global.work_substance.radikals.get(j).type,90);
							   DrawSecondradikal = true;
							   H_Count--;
							   //dr.drawLine(x+delta*i+4, y-12, x+delta*i+4, y-28);
						   }else{
							   DrawRadikal2D(dr,x1,y1,Global.work_substance.radikals.get(j).type,270);
							   DrawFirstRadikal = true;
							   H_Count--;
							   //dr.drawLine(x+delta*i+4, y+2, x+delta*i+4, y+18);
						   }
					   }
				   }
			   }
			   if(H_Count<0){
				   Global.error_in_name = true;
			   }
			   if(H_Count>0){
				   dr.drawString("CH",x1,y1);
				   dr.setFont(LowerIndexFont);
				   if(H_Count>1){
					   dr.drawString(Integer.toString(H_Count),x1+18,y1+4);
				   }
				   dr.setFont(NormalFont);
				   if(i<Global.work_substance.c_count-1){
					   if(special_connect){//Nная связь
						   switch(Global.work_substance.sub_type){
						   case 2:
							   dr.drawLine(x1+20, y1-3, x1+37, y1-3);
							   dr.drawLine(x1+20, y1-7, x1+37, y1-7);
							   break;
						   case 3:
							   dr.drawLine(x1+20, y1-3, x1+37, y1-3);
							   dr.drawLine(x1+20, y1-7, x1+37, y1-7);
							   break;
						   case 4:
							   dr.drawLine(x1+20, y1-9, x1+37, y1-9);
							   dr.drawLine(x1+20, y1-1, x1+37, y1-1);
							   dr.drawLine(x1+20, y1-5, x1+37, y1-5);
							   break;
						   }
					   }else{//одинарная связь
						   dr.drawLine(x1+20, y1-5, x1+37, y1-5);
					   }
				   }
			   }else{
				   dr.drawString("C",x1,y1);
				   if(special_connect){//Nная связь
					   switch(Global.work_substance.sub_type){
					   case 2:
						   dr.drawLine(x1+10, y1-3, x1+37, y1-3);
						   dr.drawLine(x1+10, y1-7, x1+37, y1-7);
						   break;
					   case 3:
						   dr.drawLine(x1+10, y1-3, x1+37, y1-3);
						   dr.drawLine(x1+10, y1-7, x1+37, y1-7);
						   break;
					   case 4:
						   dr.drawLine(x1+10, y1-9, x1+37, y1-9);
						   dr.drawLine(x1+10, y1-1, x1+37, y1-1);
						   dr.drawLine(x1+10, y1-5, x1+37, y1-5);
						   break;
					   }
				   }else{//одинарная связь
					   dr.drawLine(x1+10, y1-5, x1+37, y1-5);
				   }
			   }
		   }
		   break;
	   }
	   width=Global.max_x-Global.min_x+65;
	   System.out.println("ololo width "+width);
	   height=Global.max_y-Global.min_y+150;
	   System.out.println("ololo height "+height);
	   
   }

	public int Get_Height(){
		System.out.println("ololo3"+height);
		return height;
	}
	
	public void paint(Graphics g) {
		//g.setColor(Color.white);
		//g.fillRect(0,0, size.width, size.height);
		g.setColor(Color.black);
		//g.drawRect(0, 0, size.width-1, size.height-1);
		//g.drawLine(0, 0, size.width, size.height);//линия
		Draw2D(g);
		//g.copyArea(10, 10, 25, 25, 30, 0);
		setSize(width,height);
		//g.drawRect(Global.min_x, Global.min_y, Global.max_x-Global.min_x, Global.max_y-Global.min_y);
		//g.copyArea(Global.min_x, Global.min_y-10, Global.max_x-Global.min_x+30, Global.max_y-Global.min_y+15, -Global.min_x+10, -Global.min_y+20);
		//g.setColor(Color.white);
		//g.fillRect(1, Global.max_y-Global.min_y+25, size.width-2, size.height-2-(Global.max_y-Global.min_y+25));
		//g.fillRect(1, 1, 9, Global.max_y-Global.min_y+24);
		//g.fillRect(size.width-25, 1, 24, Global.max_y-Global.min_y+25);
		//g.clearRect(1, Global.max_y-Global.min_y+25, size.width-2, size.height-2-(Global.max_y-Global.min_y+25));
		//g.clearRect(1, 1, 9, Global.max_y-Global.min_y+24);
		//g.clearRect(size.width-25, 1, 24, Global.max_y-Global.min_y+25);
		//System.out.println(Global.min_x);
		//System.out.println(Global.max_x);
		//System.out.println(Global.min_y);
		//System.out.println(Global.max_y);
		System.out.println("chemcanvas2d.paint()");
		setLocation(-Global.min_x+5, -Global.min_y+20);
		getParent().setSize(500, 430+height);
	}
} 
