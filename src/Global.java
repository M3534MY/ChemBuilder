public class Global {
	// Обьявляем переменные для приема параметров
	static String  input_string = new String();
	static int     input_type = 0;
	static String  work_string = new String();
	static String  suffiks[] = {"мет","эт","проп","бут","пент","гекс","гепт","окт","нон","дек"};
	static String  ends[] = {"ан","цикло*ан","адиен","ен","ин","ол","аль","он","овая_кислота"};
	static String  radicals[] = {"метил","этил","пропил","бутил","пентил","изопропил","изобутил","втор-бутил","трет-бутил"};
	static String  multipliers[] = {"ди","три","тетра","пента"};
	static String  ciklo_word = "цикло";
	static String  error_strings[] = {"Такого вещества не существует","Данное вещество невозможно распознать. "};
	static String  error_string;
	static boolean error_in_name = false;
	static Substance work_substance = new Substance();
	static String  test_strings[] = {"этанол-1",//00
							"гексен-3",//1
							"2,3-диметил-4-бутилпентан",//2
							"5-изопропил-2,8-диметил-3-этилнонан",//3
							"3,3-диметилбутан",//4
							"1,1-диметилциклогексан",//5
							"2,2,3-триметилгексен-3",//6
							"2,4-диметилпентадиен-2,4",//7
							"5-метилдекин-8",//8
							"3-метил-1-бутенол-2",//9
							"пропаналь",//10
							"5,5-диметилгексанон-3",//11
							"2-метилбутановая_кислота",//12
							"3-бутенол-2",//13
							"5,5-диметил-4-бутил-2-декиновая_кислота"};//14
	static int     min_x = 200;
	static int     max_x = 0;
	static int     min_y = 200;
	static int     max_y = 0;
	
	//Распознавание радикала
	static public void DetectRadikal(){
		int     radikal_multiplier;
		for (int i = 1; i < 10; i++) {
			if (work_string.startsWith(Integer.toString(i))) {
				//System.out.println("Start to detect radikal");
				//System.out.println(work_string);
				if (i > work_substance.c_count) {
					error_in_name = true;
				} else {
					Radikal help_radikal = new Radikal(i, -1);
					work_substance.radikals.add(help_radikal);
					for(int l=0;l<work_substance.radikals.size();l++){
						//System.out.print("["+work_substance.radikals.get(l).place+","+work_substance.radikals.get(l).type+"]; ");
					}
				}
				radikal_multiplier = 1;
				work_string = work_string.substring(1);
				//System.out.println(work_string);
				while (!work_string.startsWith("-")) {
					if (work_string.startsWith(",")) {
						work_string = work_string.substring(1);
						//System.out.println(work_string);
					}
					for (int j = 1; j < 10; j++) {
						if (work_string.startsWith(Integer.toString(j))) {
							Radikal helpradikal = new Radikal( j, -1);
							work_substance.radikals.add(helpradikal);
							radikal_multiplier = radikal_multiplier + 1;
							work_string = work_string.substring(1);
							//System.out.println(work_string);
							for(int l=0;l<work_substance.radikals.size();l++){
								//System.out.print("["+work_substance.radikals.get(l).place+","+work_substance.radikals.get(l).type+"]; ");
							}
						}
					}
				}
				work_string = work_string.substring(1);
				//System.out.println(work_string);
				// радикал
				if(radikal_multiplier>1){
					if (work_string.startsWith(multipliers[radikal_multiplier-2])) {
						work_string = work_string.substring(multipliers[radikal_multiplier-2].length());
					} else {
						error_in_name = true;
					}
				}
				for(int l=0;l<work_substance.radikals.size();l++){
					//System.out.print("["+work_substance.radikals.get(l).place+","+work_substance.radikals.get(l).type+"]; ");
				}
				for (int k = 0; k < radicals.length; k++) {
					if (work_string.startsWith(radicals[k])) {
						for (int j = 0; j < work_substance.radikals.size(); j++) {
							if (work_substance.radikals.get(j).type == -1) {
								work_substance.radikals.get(j).type = k+1;
							}
						}
						work_string = work_string.substring(radicals[k].length());
					}
				}
			}
		}
		for(int i=0;i<work_substance.radikals.size();i++){
			//System.out.print("["+work_substance.radikals.get(i).place+","+work_substance.radikals.get(i).type+"]; ");
		}
		if(work_string.startsWith("-")){
			work_string = work_string.substring(1);
			//System.out.println(work_string);
			DetectRadikal();
		}
		
	}
	
	//определение специальной информации о веществе
	static public void DetectSpecialInfo(){
		//System.out.println("DetectSpecialInfo");
		int EndWithNumber = 0;
		for (int i = 1; i < 10; i++) {
			//System.out.println(i);
			if (work_string.endsWith(Integer.toString(i))) {
				EndWithNumber = i;
				break;
			}
		}
		if(EndWithNumber>0){
			work_substance.special_info.add(EndWithNumber);
			work_string = work_string.substring(0,work_string.length() - 1);
			while (!work_string.endsWith("-")||error_in_name){
				if (work_string.endsWith(",")) {
					work_string = work_string.substring(0,work_string.length() - 1);
					EndWithNumber = 0;
					for (int i = 1; i < 10; i++) {
						//System.out.println(i);
						if (work_string.endsWith(Integer.toString(i))) {
							EndWithNumber = i;
							break;
						}
					}
					if(EndWithNumber>0){
						work_substance.special_info.add(EndWithNumber);
						work_string = work_string.substring(0,work_string.length() - 1);
					}else{
						error_in_name = true;
						error_string = error_strings[1]+"11";
					}
				}else{
					error_in_name = true;
					error_string = error_strings[1]+"12";
				}
			}
			work_string = work_string.substring(0,work_string.length() - 1);
		}
	}
	
	//определение типа вещества
	static public void DetectType(){
		//System.out.println("DetectType");
		int i=0;
		boolean EndSelected = false;
		while(!EndSelected&&!error_in_name){
			if(i<ends.length){
				if(work_string.endsWith(ends[i])) {
					EndSelected = true;
					work_substance.type = i;
					//System.out.println("Substance Type: алк" + Ends[work_substance.Type]);
					work_string = work_string.substring(0,work_string.length() - ends[i].length());
				}
			}else{
				error_in_name = true;
				error_string = error_strings[1]+"21";
			}
			i++;
		}
		if(EndSelected){
			//проверка на соответствие типа и специальной информациии
			switch(work_substance.type){
			case 0: break;
			case 1: 
				if(work_substance.special_info.size()!=1){
					error_in_name = true;
					error_string = error_strings[1]+"22";
				}
			}
			//определение дополнительного типа вещества
			if(work_substance.type>4){
				EndSelected = false;
				i=-1;
				while(!EndSelected&&!error_in_name){
					i++;
					if(i<5){
						if(work_string.endsWith(ends[i])) {
							EndSelected = true;
							work_substance.sub_type = i;
							work_string = work_string.substring(0,work_string.length() - ends[i].length());
						}
					}else{
						error_in_name = true;
						error_string = error_strings[1]+"23";
					}
				}
			}
		}else{
			error_in_name = true;
			error_string = error_strings[1]+"24";
		}
	}
	
	//Определяем главную цепь вещества
	static public void DetectMainBranch(){
		//System.out.println("DetectMainBranch");
		boolean SuffiksSelected = false;
		int i=-1;
		while(!SuffiksSelected&&!error_in_name){
			i++;
			if(i<suffiks.length){
				if (work_string.endsWith(suffiks[i])){
					SuffiksSelected = true;
					work_substance.c_count = i + 1;
				};
			}else{
				error_in_name = true;
				error_string = error_strings[1];
			}
		}
		//System.out.println("error_in_name: " + error_in_name);
		if(SuffiksSelected){
			switch (work_substance.type) {
			case 0:
				//H_Count = C_Count * 2 + 2;
				break;
			case 2:
				if (work_substance.c_count < 2) {
					error_in_name = true;
					error_string = error_strings[1]+"31";
				}
				//H_Count = C_Count * 2;
				break;
			case 3:
				if (work_substance.c_count < 3) {
					error_in_name = true;
					error_string = error_strings[1]+"31";
				}
				//H_Count = C_Count * 2 - 2;
				break;
			case 4:
				if (work_substance.c_count < 2) {
					error_in_name = true;
					error_string = error_strings[1]+"31";
				}
				//H_Count = C_Count * 2 - 2;
				break;
			case 5: 
				break;
			case 6:
				break;
			case 7:
				break;
			case 8:
				break;
			default:
				error_in_name = true;
				break;
			}
			work_string = work_string.substring(0,work_string.length() - suffiks[i].length());
			if(work_string.endsWith(ciklo_word)&&work_substance.type==0){
				work_substance.type=1;
				if (work_substance.c_count < 3) {
					error_in_name = true;
					error_string = error_strings[1]+"32";
				}
				//H_Count = C_Count * 2;
				work_string = work_string.substring(0,work_string.length() - ciklo_word.length());
			}
			// Вещество не является углеводородом
			if((work_substance.type>4)&&(work_substance.sub_type>1)){
				if(work_string.endsWith("-")){
					work_string = work_string.substring(0,work_string.length() - 1);
					int EndWithNumber = 0;
					for (i = 1; i < 10; i++) {
						System.out.println(i);
						if (work_string.endsWith(Integer.toString(i))) {
							EndWithNumber = i;
							System.out.println(i);
							break;
						}
					}
					if(EndWithNumber>0){
						work_substance.sub_special_info.add(EndWithNumber);
						work_string = work_string.substring(0,work_string.length() - 1);
					}else{
						error_in_name = true;
						error_string = error_strings[1]+"34";
					}
				}else{
					error_in_name = true;
					error_string = error_strings[1]+"33";
				}
			}
		}
		//System.out.println("error_in_name: " + error_in_name);
		//System.out.println("error_string: " + error_string);
	}
	
	//Распознавание структуры
	static public void DetectSubstance(){
		work_substance.special_info.clear();
		work_substance.sub_special_info.clear();
		work_substance.radikals.clear();
		work_substance.c_count = 0;
		work_substance.type = -1;
		work_substance.sub_type = -1;
		error_in_name = false;
		int i=0;
		//System.out.println("in start: "+work_string);
		DetectSpecialInfo();
		//System.out.println("SpecialInfo: "+work_substance.special_info);
		
		DetectType();
		//System.out.println("Substance Type: " + ends[work_substance.type]);
		if(work_substance.sub_type>=0){
			//System.out.println("Substance SubType: " + ends[work_substance.sub_type]);
		}
		//System.out.println("after end delete: " + work_string);
		
		DetectMainBranch();

		//System.out.println("after suffiks delete: " + work_string);
		//System.out.println("error_in_name: " + error_in_name);
		//Определяем радикалы вещества
		while(work_string.length()!=0&&!error_in_name){
			DetectRadikal();
					


			if (work_string.length() != 0) {// дошли до конца
															// названия, но
															// строка не
															// закончилась
				error_in_name = true;
			}
		}
		for(i=0;i<work_substance.radikals.size();i++){
			//System.out.print("["+work_substance.radikals.get(i).place+","+work_substance.radikals.get(i).type+"]; ");
		}
		System.out.println();
		work_substance.Print();
	}

	static public int Get_Max_X(){
		System.out.println("ololo3"+max_x);
		return max_x;
	}
	
	static public void Set_Max_X(int m_x){
		max_x=m_x;
		System.out.println("ololo5"+max_x);
	}
}