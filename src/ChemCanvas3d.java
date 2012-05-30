import java.awt.*;
import java.awt.event.*;
import java.util.List;

import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;

import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;

public class ChemCanvas3d extends Panel implements MouseMotionListener {
/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private TransformGroup objTrans;
private Transform3D trans = new Transform3D();
private TransformGroup VpTG;
private float xx=0.0f;
private float yy=0.0f;
private int mouse_prev_x=0;
private int mouse_prev_y=0;
float x =0;

Color3f gray = new Color3f(0.0f, 0.0f, 1.0f);
Color3f black = new Color3f(0.0f, 0.0f, 0.0f);
Color3f white = new Color3f(1.0f, 1.0f, 1.0f);
Color3f red = new Color3f(0.7f, .15f, .15f);
Color3f yellow = new Color3f(0.15f, 1.0f, 0.5f);

Material element_h_material = new Material();
Material element_c_material = new Material();
Material element_o_material = new Material();

public TransformGroup DrawUsualElement(int element_place, int second_radikal_type, int first_radikal_type){
	
	Sphere element_c = new Sphere(0.15f);
	Appearance ap = new Appearance();
	ap.setMaterial(element_c_material);
	element_c.setAppearance(ap);
	TransformGroup element_trans = new TransformGroup();
	TransformGroup radikal_trans = new TransformGroup();
	for(int j=0;j<3;j++){
		int rot = 120*j;
		if((j==(((element_place%4)+2)/2))&&(first_radikal_type>0)){
			radikal_trans = DrawRadical3d(first_radikal_type);
			Transform3D radikal_pos = new Transform3D();
			radikal_pos.setTranslation(new Vector3f(-0.6f*(float)Math.cos(Math.toRadians(60))*(float)Math.sin(Math.toRadians(60)),-0.6f*(float)Math.sin(Math.toRadians(30)),((int)(((element_place%4)-1.5)*2)%2)*0.6f*(float)Math.sin(Math.toRadians(60))*(float)Math.sin(Math.toRadians(60))));
			Matrix3d branch_rot = new Matrix3d();
			branch_rot.rotZ(Math.toRadians(180));
			radikal_pos.setRotation(branch_rot);
			radikal_trans.setTransform(radikal_pos);
			element_trans.addChild(radikal_trans);
		}else{
			//рисуем шар
			TransformGroup sphere_trans = new TransformGroup();
			sphere_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			Transform3D sphere_pos = new Transform3D();
			sphere_pos.setTranslation(new Vector3f(0.6f,0.0f,0.0f));
			Sphere element_h =new Sphere(0.1f);
			ap = new Appearance();
			ap.setMaterial(element_h_material);
			element_h.setAppearance(ap);
			sphere_trans.setTransform(sphere_pos);
			sphere_trans.addChild(element_h);
			
			//рисуем цилиндр
			TransformGroup cylinder_trans = new TransformGroup();
			cylinder_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			Transform3D cylinder_pos = new Transform3D();
			cylinder_pos.setTranslation(new Vector3f(0.3f,0.0f,0.0f));
			Cylinder cylinder = new Cylinder(0.05f,0.6f);
			Matrix3d cylinder_rot = new Matrix3d();
			cylinder_rot.rotZ(Math.toRadians(90));
			cylinder_pos.setRotation(cylinder_rot);
			cylinder_trans.setTransform(cylinder_pos);
			cylinder_trans.addChild(cylinder);
			
			
			TransformGroup branch_trans = new TransformGroup();
			branch_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			Transform3D branch_pos = new Transform3D();
			Matrix3d branch_rot_z = new Matrix3d();
			branch_rot_z.rotZ(Math.toRadians(-30));
			Matrix3d branch_rot_y = new Matrix3d();
			branch_rot_y.rotY(Math.toRadians(rot));
			Matrix3d branch_rot = new Matrix3d();
			branch_rot.mul(branch_rot_y, branch_rot_z);
			branch_pos.setRotation(branch_rot);
			branch_trans.setTransform(branch_pos);
			
			branch_trans.addChild(cylinder_trans);
			branch_trans.addChild(sphere_trans);
			element_trans.addChild(branch_trans);
		}
	}
	if(second_radikal_type>0){
		radikal_trans = DrawRadical3d(second_radikal_type);
		Transform3D radikal_pos = new Transform3D();
		radikal_pos.setTranslation(new Vector3f(0.0f,0.6f,0.0f));
		Matrix3d branch_rot = new Matrix3d();
		branch_rot.rotZ(Math.toRadians(180));
		radikal_pos.setRotation(branch_rot);
		radikal_trans.setTransform(radikal_pos);
		element_trans.addChild(radikal_trans);
	}else{
		//рисуем 4 шар 
		TransformGroup objTrans2 = new TransformGroup();
		objTrans2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D pos2 = new Transform3D();
		pos2.setTranslation(new Vector3f(0.0f,0.6f,0.0f));
		Sphere h =new Sphere(0.1f);
		ap = new Appearance();
		ap.setMaterial(element_h_material);
		h.setAppearance(ap);
		objTrans2.setTransform(pos2);
		objTrans2.addChild(h);
		//рисуем цилиндр
		TransformGroup objTrans3 = new TransformGroup();
		objTrans3.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		Transform3D pos3 = new Transform3D();
		pos3.setTranslation(new Vector3f(0.0f,0.3f,0.0f));
		Cylinder c = new Cylinder(0.05f,0.6f);
		objTrans3.setTransform(pos3);
		objTrans3.addChild(c);
		element_trans.addChild(objTrans3);
		element_trans.addChild(objTrans2);
	}
	element_trans.addChild(element_c);
	return element_trans;
}

public TransformGroup DrawRadical3d(int radikal_type){
	int rot = 0;
	System.out.println("drawradical"+rot);
	TransformGroup radikal_trans = new TransformGroup();
	float x = 0;
	switch(radikal_type){
		case 1:
		case 2:
		case 3:
		case 4:
		case 5:
			for(int i=0;i<radikal_type;i++){
				TransformGroup element_trans = new TransformGroup();
				for(int j=0;j<3;j++){
					rot = 120*j;
					//рисуем шар
					TransformGroup sphere_trans = new TransformGroup();
					sphere_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
					Transform3D sphere_pos = new Transform3D();
					sphere_pos.setTranslation(new Vector3f(0.6f,0.0f,0.0f));
					Sphere element_h =new Sphere(0.1f);
					Appearance ap = new Appearance();
					ap.setMaterial(element_h_material);
					element_h.setAppearance(ap);
					sphere_trans.setTransform(sphere_pos);
					sphere_trans.addChild(element_h);
					
					//рисуем цилиндр
					TransformGroup cylinder_trans = new TransformGroup();
					cylinder_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
					Transform3D cylinder_pos = new Transform3D();
					cylinder_pos.setTranslation(new Vector3f(0.3f,0.0f,0.0f));
					Cylinder cylinder = new Cylinder(0.05f,0.6f);
					Matrix3d cylinder_rot = new Matrix3d();
					cylinder_rot.rotZ(Math.toRadians(90));
					cylinder_pos.setRotation(cylinder_rot);
					cylinder_trans.setTransform(cylinder_pos);
					cylinder_trans.addChild(cylinder);
					
					
					TransformGroup branch_trans = new TransformGroup();
					branch_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
					Transform3D branch_pos = new Transform3D();
					Matrix3d branch_rot_z = new Matrix3d();
					branch_rot_z.rotZ(Math.toRadians(-30));
					Matrix3d branch_rot_y = new Matrix3d();
					branch_rot_y.rotY(Math.toRadians(rot));
					Matrix3d branch_rot = new Matrix3d();
					branch_rot.mul(branch_rot_y, branch_rot_z);
					branch_pos.setRotation(branch_rot);
					branch_trans.setTransform(branch_pos);
					
					branch_trans.addChild(cylinder_trans);
					branch_trans.addChild(sphere_trans);
					element_trans.addChild(branch_trans);
				}
				//рисуем 4 шар 
				TransformGroup objTrans2 = new TransformGroup();
				objTrans2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
				Transform3D pos2 = new Transform3D();
				pos2.setTranslation(new Vector3f(0.0f,0.6f,0.0f));
				Sphere element_h =new Sphere(0.1f);
				Appearance ap = new Appearance();
				ap.setMaterial(element_h_material);
				element_h.setAppearance(ap);
				objTrans2.setTransform(pos2);
				objTrans2.addChild(element_h);
				//рисуем цилиндр
				TransformGroup objTrans3 = new TransformGroup();
				objTrans3.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
				Transform3D pos3 = new Transform3D();
				pos3.setTranslation(new Vector3f(0.0f,0.3f,0.0f));
				Cylinder c = new Cylinder(0.05f,0.6f);
				objTrans3.setTransform(pos3);
				objTrans3.addChild(c);
				
				element_trans.addChild(objTrans3);
				element_trans.addChild(objTrans2);
				Sphere element_c = new Sphere(0.15f);
				ap = new Appearance();
				ap.setMaterial(element_c_material);
				element_c.setAppearance(ap);
				element_trans.addChild(element_c);
				
				Transform3D pos1 = new Transform3D();
				
				switch (i%4){
				case 0:
					pos1 = new Transform3D();
					pos1.setTranslation(new Vector3f(x,0.0f,0.0f));
					x = x+ 0.6f*(float)Math.cos(Math.toRadians(30));
					break;
				case 1:
					pos1 = new Transform3D();
					pos1.setTranslation(new Vector3f(x,-0.6f*(float)Math.sin(Math.toRadians(30)),0.0f));
					Matrix3d rotz = new Matrix3d();
					rotz.rotZ(Math.toRadians(180));
					pos1.setRotation(rotz);
					x = x+0.6f*(float)Math.cos(Math.toRadians(60))*(float)Math.sin(Math.toRadians(60));
					break;
				case 2:
					pos1 = new Transform3D();
					pos1.setTranslation(new Vector3f(x,0.0f,0.6f*(float)Math.sin(Math.toRadians(60))*(float)Math.sin(Math.toRadians(60))));
					x = x+ 0.6f*(float)Math.cos(Math.toRadians(30));
					break;
				case 3:
					pos1 = new Transform3D();
					pos1.setTranslation(new Vector3f(x,-0.6f*(float)Math.sin(Math.toRadians(30)),0.6f*(float)Math.sin(Math.toRadians(60))*(float)Math.sin(Math.toRadians(60))));
					rotz = new Matrix3d();
					rotz.rotZ(Math.toRadians(180));
					pos1.setRotation(rotz);
					x = x+0.6f*(float)Math.cos(Math.toRadians(60))*(float)Math.sin(Math.toRadians(60));
					break;
				}
			
				element_trans.setTransform(pos1);
				
				radikal_trans.addChild(element_trans);
			}
			break;
	}
	return radikal_trans;
}

public TransformGroup DrawAlkan3d(int start_position){
	System.out.println("DrawAlkan3d");
	TransformGroup substance_trans = new TransformGroup();
	TransformGroup element_trans = new TransformGroup();

	for(int i=start_position;i<Global.work_substance.c_count;i++){
		
		boolean DrawFirstRadikal = false;
		boolean DrawSecondRadikal = false;
		int second_radikal_type = -1;
		int first_radikal_type = -1;
		
		for(int k=0;k<Global.work_substance.radikals.size();k++){
			   if(Global.work_substance.radikals.get(k).place==i+1){
				   if(DrawSecondRadikal){
					   Global.error_in_name = true;
				   }else{
					   if(DrawFirstRadikal){
						   DrawSecondRadikal = true;
						   second_radikal_type = Global.work_substance.radikals.get(k).type;
					   }else{
						   DrawFirstRadikal = true;
						   first_radikal_type = Global.work_substance.radikals.get(k).type;
					   }
				   }
			   }
		   }
		
		element_trans = DrawUsualElement(i,first_radikal_type,second_radikal_type);
		
		Transform3D pos1 = new Transform3D();
		
		switch (i%4){
		case 0:
			pos1 = new Transform3D();
			pos1.setTranslation(new Vector3f(x,0.0f,0.0f));
			x = x+ 0.6f*(float)Math.cos(Math.toRadians(30));
			break;
		case 1:
			pos1 = new Transform3D();
			pos1.setTranslation(new Vector3f(x,-0.6f*(float)Math.sin(Math.toRadians(30)),0.0f));
			Matrix3d rotz = new Matrix3d();
			rotz.rotZ(Math.toRadians(180));
			pos1.setRotation(rotz);
			x = x+0.6f*(float)Math.cos(Math.toRadians(60))*(float)Math.sin(Math.toRadians(60));
			break;
		case 2:
			pos1 = new Transform3D();
			pos1.setTranslation(new Vector3f(x,0.0f,0.6f*(float)Math.sin(Math.toRadians(60))*(float)Math.sin(Math.toRadians(60))));
			x = x+ 0.6f*(float)Math.cos(Math.toRadians(30));
			break;
		case 3:
			pos1 = new Transform3D();
			pos1.setTranslation(new Vector3f(x,-0.6f*(float)Math.sin(Math.toRadians(30)),0.6f*(float)Math.sin(Math.toRadians(60))*(float)Math.sin(Math.toRadians(60))));
			rotz = new Matrix3d();
			rotz.rotZ(Math.toRadians(180));
			pos1.setRotation(rotz);
			x = x+0.6f*(float)Math.cos(Math.toRadians(60))*(float)Math.sin(Math.toRadians(60));
			break;
		}
	
		element_trans.setTransform(pos1);
		substance_trans.addChild(element_trans);
	}


	return substance_trans;
}

public TransformGroup DrawAlken3d(int start_position, List<Integer> sub_special_info){
	System.out.println("DrawAlken3d");
	System.out.println(sub_special_info);
	TransformGroup substance_trans = new TransformGroup();
	TransformGroup radikal_trans = new TransformGroup();

	for(int i=start_position;i<Global.work_substance.c_count;i++){
		
		boolean DrawFirstRadikal = false;
		boolean DrawSecondRadikal = false;
		int second_radikal_type = -1;
		int first_radikal_type = -1;
		
		for(int k=0;k<Global.work_substance.radikals.size();k++){
		   if(Global.work_substance.radikals.get(k).place==i+1){
			   if(DrawSecondRadikal){
				   Global.error_in_name = true;
			   }else{
				   if(DrawFirstRadikal){
					   DrawSecondRadikal = true;
					   second_radikal_type = Global.work_substance.radikals.get(k).type;
				   }else{
					   DrawFirstRadikal = true;
					   first_radikal_type = Global.work_substance.radikals.get(k).type;
				   }
			   }
		   }
	   }
		
		boolean special_connect_forward = false;
		boolean special_connect_backward = false;
		Sphere element_c = new Sphere(0.15f);
		Appearance ap = new Appearance();
		ap.setMaterial(element_c_material);
		element_c.setAppearance(ap);
		TransformGroup element_trans = new TransformGroup();
		for(int k=0;k<sub_special_info.size();k++){
			if(i+1==sub_special_info.get(k)){//двойная связь вперед
				special_connect_forward = true;
			}
			if(i==sub_special_info.get(k)){//двойная связь назад
				special_connect_backward = true;
			}
		}
		
		
		
		if(special_connect_forward){
			if(special_connect_backward){
				for(int j=0;j<2;j++){
					int rot = 120*(j+2*(i%2)+(int)(i/3));
					System.out.println("j"+j);
		
					//рисуем шар
					TransformGroup sphere_trans = new TransformGroup();
					sphere_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
					Transform3D sphere_pos = new Transform3D();
					sphere_pos.setTranslation(new Vector3f(0.6f,0.0f,0.0f));
					Sphere element_h =new Sphere(0.1f);
					ap = new Appearance();
					ap.setMaterial(element_h_material);
					element_h.setAppearance(ap);
					sphere_trans.setTransform(sphere_pos);
					sphere_trans.addChild(element_h);
					
					//рисуем цилиндр
					TransformGroup cylinder_trans1 = new TransformGroup();
					cylinder_trans1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
					Transform3D cylinder_pos = new Transform3D();
					cylinder_pos.setTranslation(new Vector3f(0.3f,-0.05f,0.0f));
					Cylinder cylinder = new Cylinder(0.05f,0.6f);
					Matrix3d cylinder_rot = new Matrix3d();
					cylinder_rot.rotZ(Math.toRadians(90));
					cylinder_pos.setRotation(cylinder_rot);
					cylinder_trans1.setTransform(cylinder_pos);
					cylinder_trans1.addChild(cylinder);
					TransformGroup cylinder_trans2 = new TransformGroup();
					cylinder_trans2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
					cylinder_pos = new Transform3D();
					cylinder_pos.setTranslation(new Vector3f(0.3f,0.05f,0.0f));
					cylinder = new Cylinder(0.05f,0.6f);
					cylinder_rot = new Matrix3d();
					cylinder_rot.rotZ(Math.toRadians(90));
					cylinder_pos.setRotation(cylinder_rot);
					cylinder_trans2.setTransform(cylinder_pos);
					cylinder_trans2.addChild(cylinder);
					
					
					TransformGroup branch_trans = new TransformGroup();
					branch_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
					Transform3D branch_pos = new Transform3D();
					Matrix3d branch_rot_z = new Matrix3d();
					branch_rot_z.rotZ(Math.toRadians(-30));
					Matrix3d branch_rot_y = new Matrix3d();
					branch_rot_y.rotY(Math.toRadians(rot));
					Matrix3d branch_rot = new Matrix3d();
					branch_rot.mul(branch_rot_y, branch_rot_z);
					branch_pos.setRotation(branch_rot);
					branch_trans.setTransform(branch_pos);
					
					branch_trans.addChild(cylinder_trans1);
					branch_trans.addChild(cylinder_trans2);
					branch_trans.addChild(sphere_trans);
					element_trans.addChild(branch_trans);
				}
				element_trans.addChild(element_c);
			}else{
				for(int j=0;j<3;j++){
					if(j==(((i+1)%2)*(5-((i+1)%4))/2)&&(DrawFirstRadikal)){
						radikal_trans = DrawRadical3d(first_radikal_type);
						Transform3D radikal_pos = new Transform3D();
						radikal_pos.setTranslation(new Vector3f(-0.6f*(float)Math.cos(Math.toRadians(60))*(float)Math.sin(Math.toRadians(60)),-0.6f*(float)Math.sin(Math.toRadians(30)),((int)(((i%4)-1.5)*2)%2)*0.6f*(float)Math.sin(Math.toRadians(60))*(float)Math.sin(Math.toRadians(60))));
						Matrix3d branch_rot = new Matrix3d();
						branch_rot.rotZ(Math.toRadians(180));
						radikal_pos.setRotation(branch_rot);
						radikal_trans.setTransform(radikal_pos);
						element_trans.addChild(radikal_trans);
					}
					if(j==(i%2)*(5-(i%4))/2){
						int rot = 120*j;
						System.out.println("j"+j);
			
						//рисуем шар
						TransformGroup sphere_trans = new TransformGroup();
						sphere_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
						Transform3D sphere_pos = new Transform3D();
						sphere_pos.setTranslation(new Vector3f(0.6f,0.0f,0.0f));
						Sphere element_h =new Sphere(0.1f);
						ap = new Appearance();
						ap.setMaterial(element_h_material);
						element_h.setAppearance(ap);
						sphere_trans.setTransform(sphere_pos);
						sphere_trans.addChild(element_h);
						
						//рисуем цилиндр
						TransformGroup cylinder_trans1 = new TransformGroup();
						cylinder_trans1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
						Transform3D cylinder_pos = new Transform3D();
						cylinder_pos.setTranslation(new Vector3f(0.3f,-0.05f,0.0f));
						Cylinder cylinder = new Cylinder(0.05f,0.6f);
						Matrix3d cylinder_rot = new Matrix3d();
						cylinder_rot.rotZ(Math.toRadians(90));
						cylinder_pos.setRotation(cylinder_rot);
						cylinder_trans1.setTransform(cylinder_pos);
						cylinder_trans1.addChild(cylinder);
						TransformGroup cylinder_trans2 = new TransformGroup();
						cylinder_trans2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
						cylinder_pos = new Transform3D();
						cylinder_pos.setTranslation(new Vector3f(0.3f,0.05f,0.0f));
						cylinder = new Cylinder(0.05f,0.6f);
						cylinder_rot = new Matrix3d();
						cylinder_rot.rotZ(Math.toRadians(90));
						cylinder_pos.setRotation(cylinder_rot);
						cylinder_trans2.setTransform(cylinder_pos);
						cylinder_trans2.addChild(cylinder);
						
						
						TransformGroup branch_trans = new TransformGroup();
						branch_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
						Transform3D branch_pos = new Transform3D();
						Matrix3d branch_rot_z = new Matrix3d();
						branch_rot_z.rotZ(Math.toRadians(-30));
						Matrix3d branch_rot_y = new Matrix3d();
						branch_rot_y.rotY(Math.toRadians(rot));
						Matrix3d branch_rot = new Matrix3d();
						branch_rot.mul(branch_rot_y, branch_rot_z);
						branch_pos.setRotation(branch_rot);
						branch_trans.setTransform(branch_pos);
						
						branch_trans.addChild(cylinder_trans1);
						branch_trans.addChild(cylinder_trans2);
						branch_trans.addChild(sphere_trans);
						element_trans.addChild(branch_trans);
					}else{
						int rot = 120*j;
			
						//рисуем шар
						TransformGroup sphere_trans = new TransformGroup();
						sphere_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
						Transform3D sphere_pos = new Transform3D();
						sphere_pos.setTranslation(new Vector3f(0.6f,0.0f,0.0f));
						Sphere element_h =new Sphere(0.1f);
						ap = new Appearance();
						ap.setMaterial(element_h_material);
						element_h.setAppearance(ap);
						sphere_trans.setTransform(sphere_pos);
						sphere_trans.addChild(element_h);
						
						//рисуем цилиндр
						TransformGroup cylinder_trans = new TransformGroup();
						cylinder_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
						Transform3D cylinder_pos = new Transform3D();
						cylinder_pos.setTranslation(new Vector3f(0.3f,0.0f,0.0f));
						Cylinder cylinder = new Cylinder(0.05f,0.6f);
						Matrix3d cylinder_rot = new Matrix3d();
						cylinder_rot.rotZ(Math.toRadians(90));
						cylinder_pos.setRotation(cylinder_rot);
						cylinder_trans.setTransform(cylinder_pos);
						cylinder_trans.addChild(cylinder);
						
						
						TransformGroup branch_trans = new TransformGroup();
						branch_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
						Transform3D branch_pos = new Transform3D();
						Matrix3d branch_rot_z = new Matrix3d();
						branch_rot_z.rotZ(Math.toRadians(-30));
						Matrix3d branch_rot_y = new Matrix3d();
						branch_rot_y.rotY(Math.toRadians(rot));
						Matrix3d branch_rot = new Matrix3d();
						branch_rot.mul(branch_rot_y, branch_rot_z);
						branch_pos.setRotation(branch_rot);
						branch_trans.setTransform(branch_pos);
						
						branch_trans.addChild(cylinder_trans);
						branch_trans.addChild(sphere_trans);
						element_trans.addChild(branch_trans);
					}
				}
				element_trans.addChild(element_c);
			}
		}else{
			if(special_connect_backward){
				for(int j=0;j<3;j++){
					if(j==(((i)%2)*(5-((i)%4))/2)&&(DrawFirstRadikal)){
						radikal_trans = DrawRadical3d(first_radikal_type);
						Transform3D radikal_pos = new Transform3D();
						radikal_pos.setTranslation(new Vector3f(-0.6f*(float)Math.cos(Math.toRadians(60))*(float)Math.sin(Math.toRadians(60)),-0.6f*(float)Math.sin(Math.toRadians(30)),((int)(((i%4)-1.5)*2)%2)*0.6f*(float)Math.sin(Math.toRadians(60))*(float)Math.sin(Math.toRadians(60))));
						Matrix3d branch_rot = new Matrix3d();
						branch_rot.rotZ(Math.toRadians(180));
						radikal_pos.setRotation(branch_rot);
						radikal_trans.setTransform(radikal_pos);
						element_trans.addChild(radikal_trans);
					}
					if(j==((i+1)%2)*(5-((i+1)%4))/2){
						int rot = 120*j;
		
						//рисуем шар
						TransformGroup sphere_trans = new TransformGroup();
						sphere_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
						Transform3D sphere_pos = new Transform3D();
						sphere_pos.setTranslation(new Vector3f(0.6f,0.0f,0.0f));
						Sphere element_h =new Sphere(0.1f);
						ap = new Appearance();
						ap.setMaterial(element_h_material);
						element_h.setAppearance(ap);
						sphere_trans.setTransform(sphere_pos);
						sphere_trans.addChild(element_h);
						
						//рисуем цилиндр
						TransformGroup cylinder_trans1 = new TransformGroup();
						cylinder_trans1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
						Transform3D cylinder_pos = new Transform3D();
						cylinder_pos.setTranslation(new Vector3f(0.3f,-0.05f,0.0f));
						Cylinder cylinder = new Cylinder(0.05f,0.6f);
						Matrix3d cylinder_rot = new Matrix3d();
						cylinder_rot.rotZ(Math.toRadians(90));
						cylinder_pos.setRotation(cylinder_rot);
						cylinder_trans1.setTransform(cylinder_pos);
						cylinder_trans1.addChild(cylinder);
						TransformGroup cylinder_trans2 = new TransformGroup();
						cylinder_trans2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
						cylinder_pos = new Transform3D();
						cylinder_pos.setTranslation(new Vector3f(0.3f,0.05f,0.0f));
						cylinder = new Cylinder(0.05f,0.6f);
						cylinder_rot = new Matrix3d();
						cylinder_rot.rotZ(Math.toRadians(90));
						cylinder_pos.setRotation(cylinder_rot);
						cylinder_trans2.setTransform(cylinder_pos);
						cylinder_trans2.addChild(cylinder);
						
						
						TransformGroup branch_trans = new TransformGroup();
						branch_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
						Transform3D branch_pos = new Transform3D();
						Matrix3d branch_rot_z = new Matrix3d();
						branch_rot_z.rotZ(Math.toRadians(-30));
						Matrix3d branch_rot_y = new Matrix3d();
						branch_rot_y.rotY(Math.toRadians(rot));
						Matrix3d branch_rot = new Matrix3d();
						branch_rot.mul(branch_rot_y, branch_rot_z);
						branch_pos.setRotation(branch_rot);
						branch_trans.setTransform(branch_pos);
						
						branch_trans.addChild(cylinder_trans1);
						branch_trans.addChild(cylinder_trans2);
						branch_trans.addChild(sphere_trans);
						element_trans.addChild(branch_trans);
					}else{
						int rot = 120*j;
			
						//рисуем шар
						TransformGroup sphere_trans = new TransformGroup();
						sphere_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
						Transform3D sphere_pos = new Transform3D();
						sphere_pos.setTranslation(new Vector3f(0.6f,0.0f,0.0f));
						Sphere element_h =new Sphere(0.1f);
						ap = new Appearance();
						ap.setMaterial(element_h_material);
						element_h.setAppearance(ap);
						sphere_trans.setTransform(sphere_pos);
						sphere_trans.addChild(element_h);
						
						//рисуем цилиндр
						TransformGroup cylinder_trans = new TransformGroup();
						cylinder_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
						Transform3D cylinder_pos = new Transform3D();
						cylinder_pos.setTranslation(new Vector3f(0.3f,0.0f,0.0f));
						Cylinder cylinder = new Cylinder(0.05f,0.6f);
						Matrix3d cylinder_rot = new Matrix3d();
						cylinder_rot.rotZ(Math.toRadians(90));
						cylinder_pos.setRotation(cylinder_rot);
						cylinder_trans.setTransform(cylinder_pos);
						cylinder_trans.addChild(cylinder);
						
						
						TransformGroup branch_trans = new TransformGroup();
						branch_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
						Transform3D branch_pos = new Transform3D();
						Matrix3d branch_rot_z = new Matrix3d();
						branch_rot_z.rotZ(Math.toRadians(-30));
						Matrix3d branch_rot_y = new Matrix3d();
						branch_rot_y.rotY(Math.toRadians(rot));
						Matrix3d branch_rot = new Matrix3d();
						branch_rot.mul(branch_rot_y, branch_rot_z);
						branch_pos.setRotation(branch_rot);
						branch_trans.setTransform(branch_pos);
						
						branch_trans.addChild(cylinder_trans);
						branch_trans.addChild(sphere_trans);
						element_trans.addChild(branch_trans);
					}
				}
				element_trans.addChild(element_c);
			}else{
				element_trans = DrawUsualElement(i,first_radikal_type,second_radikal_type);
			}
		}
		
		Transform3D pos1 = new Transform3D();
		
		switch (i%4){
		case 0:
			pos1 = new Transform3D();
			pos1.setTranslation(new Vector3f(x,0.0f,0.0f));
			x = x+ 0.6f*(float)Math.cos(Math.toRadians(30));
			break;
		case 1:
			pos1 = new Transform3D();
			pos1.setTranslation(new Vector3f(x,-0.6f*(float)Math.sin(Math.toRadians(30)),0.0f));
			Matrix3d rotz = new Matrix3d();
			rotz.rotZ(Math.toRadians(180));
			pos1.setRotation(rotz);
			x = x+0.6f*(float)Math.cos(Math.toRadians(60))*(float)Math.sin(Math.toRadians(60));
			break;
		case 2:
			pos1 = new Transform3D();
			pos1.setTranslation(new Vector3f(x,0.0f,0.6f*(float)Math.sin(Math.toRadians(60))*(float)Math.sin(Math.toRadians(60))));
			x = x+ 0.6f*(float)Math.cos(Math.toRadians(30));
			break;
		case 3:
			pos1 = new Transform3D();
			pos1.setTranslation(new Vector3f(x,-0.6f*(float)Math.sin(Math.toRadians(30)),0.6f*(float)Math.sin(Math.toRadians(60))*(float)Math.sin(Math.toRadians(60))));
			rotz = new Matrix3d();
			rotz.rotZ(Math.toRadians(180));
			pos1.setRotation(rotz);
			x = x+0.6f*(float)Math.cos(Math.toRadians(60))*(float)Math.sin(Math.toRadians(60));
			break;
		}
	
		element_trans.setTransform(pos1);
		substance_trans.addChild(element_trans);
	}


	return substance_trans;
}

public TransformGroup DrawAlkin3d(int start_position, List<Integer> sub_special_info){
	System.out.println("DrawAlkin3d");
	TransformGroup substance_trans = new TransformGroup();

	for(int i=start_position;i<Global.work_substance.c_count;i++){
		
		boolean DrawFirstRadikal = false;
		boolean DrawSecondRadikal = false;
		int second_radikal_type = -1;
		int first_radikal_type = -1;
		
		for(int k=0;k<Global.work_substance.radikals.size();k++){
		   if(Global.work_substance.radikals.get(k).place==i+1){
			   if(DrawSecondRadikal){
				   Global.error_in_name = true;
			   }else{
				   if(DrawFirstRadikal){
					   DrawSecondRadikal = true;
					   second_radikal_type = Global.work_substance.radikals.get(k).type;
				   }else{
					   DrawFirstRadikal = true;
					   first_radikal_type = Global.work_substance.radikals.get(k).type;
				   }
			   }
		   }
	   }
		
		boolean special_connect_forward = false;
		boolean special_connect_backward = false;
		
		Sphere element_c = new Sphere(0.15f);
		Appearance ap = new Appearance();
		ap.setMaterial(element_c_material);
		element_c.setAppearance(ap);
		TransformGroup element_trans = new TransformGroup();

		for(int k=0;k<sub_special_info.size();k++){
			if(i+1==sub_special_info.get(k)){//тройная связь вперед
				special_connect_forward = true;
			}
			if(i==sub_special_info.get(k)){//тройная связь назад
				special_connect_backward = true;
			}
		}
		for(int k=0;k<Global.work_substance.radikals.size();k++){
		   if(Global.work_substance.radikals.get(k).place==i+1){
			   if(DrawSecondRadikal){
				   Global.error_in_name = true;
			   }else{
				   if(DrawFirstRadikal){
					   DrawSecondRadikal = true;
				   }else{
					   DrawFirstRadikal = true;
				   }
			   }
		   }
	   }
		
		if(special_connect_forward){
			for(int j=0;j<2;j++){
				if(j==0){
					int rot = 120*((i%2)*(5-(i%4))/2);

					//рисуем шар
					TransformGroup sphere_trans = new TransformGroup();
					sphere_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
					Transform3D sphere_pos = new Transform3D();
					sphere_pos.setTranslation(new Vector3f(0.6f,0.0f,0.0f));
					Sphere element_h =new Sphere(0.1f);
					ap = new Appearance();
					ap.setMaterial(element_h_material);
					element_h.setAppearance(ap);
					sphere_trans.setTransform(sphere_pos);
					sphere_trans.addChild(element_h);
					
					//рисуем цилиндр
					TransformGroup cylinder_trans1 = new TransformGroup();
					cylinder_trans1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
					Transform3D cylinder_pos = new Transform3D();
					cylinder_pos.setTranslation(new Vector3f(0.3f,-0.075f,0.0f));
					Cylinder cylinder = new Cylinder(0.05f,0.6f);
					Matrix3d cylinder_rot = new Matrix3d();
					cylinder_rot.rotZ(Math.toRadians(90));
					cylinder_pos.setRotation(cylinder_rot);
					cylinder_trans1.setTransform(cylinder_pos);
					cylinder_trans1.addChild(cylinder);
					TransformGroup cylinder_trans2 = new TransformGroup();
					cylinder_trans2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
					cylinder_pos = new Transform3D();
					cylinder_pos.setTranslation(new Vector3f(0.3f,0.00f,0.0f));
					cylinder = new Cylinder(0.05f,0.6f);
					cylinder_rot = new Matrix3d();
					cylinder_rot.rotZ(Math.toRadians(90));
					cylinder_pos.setRotation(cylinder_rot);
					cylinder_trans2.setTransform(cylinder_pos);
					cylinder_trans2.addChild(cylinder);
					TransformGroup cylinder_trans3 = new TransformGroup();
					cylinder_trans3.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
					cylinder_pos = new Transform3D();
					cylinder_pos.setTranslation(new Vector3f(0.3f,0.075f,0.0f));
					cylinder = new Cylinder(0.05f,0.6f);
					cylinder_rot = new Matrix3d();
					cylinder_rot.rotZ(Math.toRadians(90));
					cylinder_pos.setRotation(cylinder_rot);
					cylinder_trans3.setTransform(cylinder_pos);
					cylinder_trans3.addChild(cylinder);
					
					
					TransformGroup branch_trans = new TransformGroup();
					branch_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
					Transform3D branch_pos = new Transform3D();
					Matrix3d branch_rot_z = new Matrix3d();
					branch_rot_z.rotZ(Math.toRadians(-30));
					Matrix3d branch_rot_y = new Matrix3d();
					branch_rot_y.rotY(Math.toRadians(rot));
					Matrix3d branch_rot = new Matrix3d();
					branch_rot.mul(branch_rot_y, branch_rot_z);
					branch_pos.setRotation(branch_rot);
					branch_trans.setTransform(branch_pos);
					
					branch_trans.addChild(cylinder_trans1);
					branch_trans.addChild(cylinder_trans2);
					branch_trans.addChild(cylinder_trans3);
					branch_trans.addChild(sphere_trans);
					element_trans.addChild(branch_trans);
				}else{
					int rot = 120*(((i+1)%2)*(5-(i%4))/2);
		
					//рисуем шар
					TransformGroup sphere_trans = new TransformGroup();
					sphere_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
					Transform3D sphere_pos = new Transform3D();
					sphere_pos.setTranslation(new Vector3f(0.6f,0.0f,0.0f));
					Sphere element_h =new Sphere(0.1f);
					ap = new Appearance();
					ap.setMaterial(element_h_material);
					element_h.setAppearance(ap);
					sphere_trans.setTransform(sphere_pos);
					sphere_trans.addChild(element_h);
					
					//рисуем цилиндр
					TransformGroup cylinder_trans = new TransformGroup();
					cylinder_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
					Transform3D cylinder_pos = new Transform3D();
					cylinder_pos.setTranslation(new Vector3f(0.3f,0.0f,0.0f));
					Cylinder cylinder = new Cylinder(0.05f,0.6f);
					Matrix3d cylinder_rot = new Matrix3d();
					cylinder_rot.rotZ(Math.toRadians(90));
					cylinder_pos.setRotation(cylinder_rot);
					cylinder_trans.setTransform(cylinder_pos);
					cylinder_trans.addChild(cylinder);
					
					
					TransformGroup branch_trans = new TransformGroup();
					branch_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
					Transform3D branch_pos = new Transform3D();
					Matrix3d branch_rot_z = new Matrix3d();
					branch_rot_z.rotZ(Math.toRadians(-30));
					Matrix3d branch_rot_y = new Matrix3d();
					branch_rot_y.rotY(Math.toRadians(rot));
					Matrix3d branch_rot = new Matrix3d();
					branch_rot.mul(branch_rot_y, branch_rot_z);
					branch_pos.setRotation(branch_rot);
					branch_trans.setTransform(branch_pos);
					
					branch_trans.addChild(cylinder_trans);
					branch_trans.addChild(sphere_trans);
					element_trans.addChild(branch_trans);
				}
			}
			element_trans.addChild(element_c);
		}else{
			if(special_connect_backward){
				for(int j=0;j<2;j++){
					if(j==1){
						int rot = 120*(((i+1)%2)*(5-(i%4))/2);
			
						//рисуем шар
						TransformGroup sphere_trans = new TransformGroup();
						sphere_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
						Transform3D sphere_pos = new Transform3D();
						sphere_pos.setTranslation(new Vector3f(0.6f,0.0f,0.0f));
						Sphere element_h =new Sphere(0.1f);
						ap = new Appearance();
						ap.setMaterial(element_h_material);
						element_h.setAppearance(ap);
						sphere_trans.setTransform(sphere_pos);
						sphere_trans.addChild(element_h);
						
						//рисуем цилиндр
						TransformGroup cylinder_trans1 = new TransformGroup();
						cylinder_trans1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
						Transform3D cylinder_pos = new Transform3D();
						cylinder_pos.setTranslation(new Vector3f(0.3f,-0.075f,0.0f));
						Cylinder cylinder = new Cylinder(0.05f,0.6f);
						Matrix3d cylinder_rot = new Matrix3d();
						cylinder_rot.rotZ(Math.toRadians(90));
						cylinder_pos.setRotation(cylinder_rot);
						cylinder_trans1.setTransform(cylinder_pos);
						cylinder_trans1.addChild(cylinder);
						TransformGroup cylinder_trans2 = new TransformGroup();
						cylinder_trans2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
						cylinder_pos = new Transform3D();
						cylinder_pos.setTranslation(new Vector3f(0.3f,0.00f,0.0f));
						cylinder = new Cylinder(0.05f,0.6f);
						cylinder_rot = new Matrix3d();
						cylinder_rot.rotZ(Math.toRadians(90));
						cylinder_pos.setRotation(cylinder_rot);
						cylinder_trans2.setTransform(cylinder_pos);
						cylinder_trans2.addChild(cylinder);
						TransformGroup cylinder_trans3 = new TransformGroup();
						cylinder_trans3.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
						cylinder_pos = new Transform3D();
						cylinder_pos.setTranslation(new Vector3f(0.3f,0.075f,0.0f));
						cylinder = new Cylinder(0.05f,0.6f);
						cylinder_rot = new Matrix3d();
						cylinder_rot.rotZ(Math.toRadians(90));
						cylinder_pos.setRotation(cylinder_rot);
						cylinder_trans3.setTransform(cylinder_pos);
						cylinder_trans3.addChild(cylinder);
						
						
						TransformGroup branch_trans = new TransformGroup();
						branch_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
						Transform3D branch_pos = new Transform3D();
						Matrix3d branch_rot_z = new Matrix3d();
						branch_rot_z.rotZ(Math.toRadians(-30));
						Matrix3d branch_rot_y = new Matrix3d();
						branch_rot_y.rotY(Math.toRadians(rot));
						Matrix3d branch_rot = new Matrix3d();
						branch_rot.mul(branch_rot_y, branch_rot_z);
						branch_pos.setRotation(branch_rot);
						branch_trans.setTransform(branch_pos);
						
						branch_trans.addChild(cylinder_trans1);
						branch_trans.addChild(cylinder_trans2);
						branch_trans.addChild(cylinder_trans3);
						branch_trans.addChild(sphere_trans);
						element_trans.addChild(branch_trans);
					}else{
						int rot = 120*(((i)%2)*(5-(i%4))/2);
			
						//рисуем шар
						TransformGroup sphere_trans = new TransformGroup();
						sphere_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
						Transform3D sphere_pos = new Transform3D();
						sphere_pos.setTranslation(new Vector3f(0.6f,0.0f,0.0f));
						Sphere element_h =new Sphere(0.1f);
						ap = new Appearance();
						ap.setMaterial(element_h_material);
						element_h.setAppearance(ap);
						sphere_trans.setTransform(sphere_pos);
						sphere_trans.addChild(element_h);
						
						//рисуем цилиндр
						TransformGroup cylinder_trans = new TransformGroup();
						cylinder_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
						Transform3D cylinder_pos = new Transform3D();
						cylinder_pos.setTranslation(new Vector3f(0.3f,0.0f,0.0f));
						Cylinder cylinder = new Cylinder(0.05f,0.6f);
						Matrix3d cylinder_rot = new Matrix3d();
						cylinder_rot.rotZ(Math.toRadians(90));
						cylinder_pos.setRotation(cylinder_rot);
						cylinder_trans.setTransform(cylinder_pos);
						cylinder_trans.addChild(cylinder);
						
						
						TransformGroup branch_trans = new TransformGroup();
						branch_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
						Transform3D branch_pos = new Transform3D();
						Matrix3d branch_rot_z = new Matrix3d();
						branch_rot_z.rotZ(Math.toRadians(-30));
						Matrix3d branch_rot_y = new Matrix3d();
						branch_rot_y.rotY(Math.toRadians(rot));
						Matrix3d branch_rot = new Matrix3d();
						branch_rot.mul(branch_rot_y, branch_rot_z);
						branch_pos.setRotation(branch_rot);
						branch_trans.setTransform(branch_pos);
						
						branch_trans.addChild(cylinder_trans);
						branch_trans.addChild(sphere_trans);
						element_trans.addChild(branch_trans);
					}
				}
				element_trans.addChild(element_c);
			}else{
				element_trans = DrawUsualElement(i,first_radikal_type,second_radikal_type);
			}
		}
		
		
		Transform3D pos1 = new Transform3D();
		
		switch (i%4){
		case 0:
			pos1 = new Transform3D();
			pos1.setTranslation(new Vector3f(x,0.0f,0.0f));
			x = x+ 0.6f*(float)Math.cos(Math.toRadians(30));
			break;
		case 1:
			pos1 = new Transform3D();
			pos1.setTranslation(new Vector3f(x,-0.6f*(float)Math.sin(Math.toRadians(30)),0.0f));
			Matrix3d rotz = new Matrix3d();
			rotz.rotZ(Math.toRadians(180));
			pos1.setRotation(rotz);
			x = x+0.6f*(float)Math.cos(Math.toRadians(60))*(float)Math.sin(Math.toRadians(60));
			break;
		case 2:
			pos1 = new Transform3D();
			pos1.setTranslation(new Vector3f(x,0.0f,0.6f*(float)Math.sin(Math.toRadians(60))*(float)Math.sin(Math.toRadians(60))));
			x = x+ 0.6f*(float)Math.cos(Math.toRadians(30));
			break;
		case 3:
			pos1 = new Transform3D();
			pos1.setTranslation(new Vector3f(x,-0.6f*(float)Math.sin(Math.toRadians(30)),0.6f*(float)Math.sin(Math.toRadians(60))*(float)Math.sin(Math.toRadians(60))));
			rotz = new Matrix3d();
			rotz.rotZ(Math.toRadians(180));
			pos1.setRotation(rotz);
			x = x+0.6f*(float)Math.cos(Math.toRadians(60))*(float)Math.sin(Math.toRadians(60));
			break;
		}
	
		element_trans.setTransform(pos1);
		substance_trans.addChild(element_trans);
	}


	return substance_trans;
}

public TransformGroup DrawPhenol3d(){
	System.out.println("DrawPhenol3d");
	TransformGroup substance_trans = new TransformGroup();
	TransformGroup element_trans = new TransformGroup();

	boolean special_connect_forward = false;
	Sphere element_c = new Sphere(0.15f);
	Appearance ap = new Appearance();
	ap.setMaterial(element_c_material);
	element_c.setAppearance(ap);
	
	for(int k=0;k<Global.work_substance.sub_special_info.size();k++){
		if(1==Global.work_substance.sub_special_info.get(k)){//особая связь вперед
			special_connect_forward = true;
		}
	}

	for(int j=0;j<3;j++){
		int rot = 120*j;
		if(j==2){
			TransformGroup branch_trans = new TransformGroup();
			//рисуем шар
			TransformGroup oxid_trans = new TransformGroup();
			oxid_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			Transform3D oxid_pos = new Transform3D();
			oxid_pos.setTranslation(new Vector3f(0.6f,0.0f,0.0f));
			Sphere element_o =new Sphere(0.135f);
			ap = new Appearance();
			ap.setMaterial(element_o_material);
			element_o.setAppearance(ap);
			oxid_trans.setTransform(oxid_pos);
			oxid_trans.addChild(element_o);
			
			//рисуем цилиндр
			TransformGroup cylinder_trans = new TransformGroup();
			cylinder_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			Transform3D cylinder_pos = new Transform3D();
			cylinder_pos.setTranslation(new Vector3f(0.3f,0.0f,0.0f));
			Cylinder cylinder = new Cylinder(0.05f,0.6f);
			Matrix3d cylinder_rot = new Matrix3d();
			cylinder_rot.rotZ(Math.toRadians(90));
			cylinder_pos.setRotation(cylinder_rot);
			cylinder_trans.setTransform(cylinder_pos);
			cylinder_trans.addChild(cylinder);
			
			branch_trans.addChild(cylinder_trans);
			
			//рисуем шар
			TransformGroup sphere_trans = new TransformGroup();
			sphere_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			Transform3D sphere_pos = new Transform3D();
			sphere_pos.setTranslation(new Vector3f(1.2f,0.0f,0.0f));
			Sphere element_h =new Sphere(0.1f);
			ap = new Appearance();
			ap.setMaterial(element_h_material);
			element_h.setAppearance(ap);
			sphere_trans.setTransform(sphere_pos);
			sphere_trans.addChild(element_h);
			
			//рисуем цилиндр
			cylinder_trans = new TransformGroup();
			cylinder_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			cylinder_pos = new Transform3D();
			cylinder_pos.setTranslation(new Vector3f(0.9f,0.0f,0.0f));
			cylinder = new Cylinder(0.05f,0.6f);
			cylinder_rot = new Matrix3d();
			cylinder_rot.rotZ(Math.toRadians(90));
			cylinder_pos.setRotation(cylinder_rot);
			cylinder_trans.setTransform(cylinder_pos);
			cylinder_trans.addChild(cylinder);
			
			
			
			branch_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			Transform3D branch_pos = new Transform3D();
			Matrix3d branch_rot_z = new Matrix3d();
			branch_rot_z.rotZ(Math.toRadians(-30));
			Matrix3d branch_rot_y = new Matrix3d();
			branch_rot_y.rotY(Math.toRadians(rot));
			Matrix3d branch_rot = new Matrix3d();
			branch_rot.mul(branch_rot_y, branch_rot_z);
			branch_pos.setRotation(branch_rot);
			branch_trans.setTransform(branch_pos);
			
			branch_trans.addChild(cylinder_trans);
			branch_trans.addChild(sphere_trans);
			branch_trans.addChild(oxid_trans);
			element_trans.addChild(branch_trans);
		}else{
			if((j==0)&&(special_connect_forward)){
				
			}else{
				//рисуем шар
				TransformGroup sphere_trans = new TransformGroup();
				sphere_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
				Transform3D sphere_pos = new Transform3D();
				sphere_pos.setTranslation(new Vector3f(0.6f,0.0f,0.0f));
				Sphere element_h =new Sphere(0.1f);
				ap = new Appearance();
				ap.setMaterial(element_h_material);
				element_h.setAppearance(ap);
				sphere_trans.setTransform(sphere_pos);
				sphere_trans.addChild(element_h);
				
				//рисуем цилиндр
				TransformGroup cylinder_trans = new TransformGroup();
				cylinder_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
				Transform3D cylinder_pos = new Transform3D();
				cylinder_pos.setTranslation(new Vector3f(0.3f,0.0f,0.0f));
				Cylinder cylinder = new Cylinder(0.05f,0.6f);
				Matrix3d cylinder_rot = new Matrix3d();
				cylinder_rot.rotZ(Math.toRadians(90));
				cylinder_pos.setRotation(cylinder_rot);
				cylinder_trans.setTransform(cylinder_pos);
				cylinder_trans.addChild(cylinder);
				
				
				TransformGroup branch_trans = new TransformGroup();
				branch_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
				Transform3D branch_pos = new Transform3D();
				Matrix3d branch_rot_z = new Matrix3d();
				branch_rot_z.rotZ(Math.toRadians(-30));
				Matrix3d branch_rot_y = new Matrix3d();
				branch_rot_y.rotY(Math.toRadians(rot));
				Matrix3d branch_rot = new Matrix3d();
				branch_rot.mul(branch_rot_y, branch_rot_z);
				branch_pos.setRotation(branch_rot);
				branch_trans.setTransform(branch_pos);
				
				branch_trans.addChild(cylinder_trans);
				branch_trans.addChild(sphere_trans);
				element_trans.addChild(branch_trans);
			}
		}
	}
	//рисуем 4 шар 
	TransformGroup objTrans2 = new TransformGroup();
	objTrans2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	Transform3D pos2 = new Transform3D();
	pos2.setTranslation(new Vector3f(0.0f,0.6f,0.0f));
	Sphere h =new Sphere(0.1f);
	ap = new Appearance();
	ap.setMaterial(element_h_material);
	h.setAppearance(ap);
	objTrans2.setTransform(pos2);
	objTrans2.addChild(h);
	//рисуем цилиндр
	TransformGroup objTrans3 = new TransformGroup();
	objTrans3.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
	Transform3D pos3 = new Transform3D();
	pos3.setTranslation(new Vector3f(0.0f,0.3f,0.0f));
	Cylinder c = new Cylinder(0.05f,0.6f);
	objTrans3.setTransform(pos3);
	objTrans3.addChild(c);
	element_trans.addChild(objTrans3);
	element_trans.addChild(objTrans2);

	element_trans.addChild(element_c);
	substance_trans.addChild(element_trans);
	
	x = x+ 0.6f*(float)Math.cos(Math.toRadians(30));
	
	element_trans = new TransformGroup();
	switch(Global.work_substance.sub_type){
	case 0:
		element_trans  = DrawAlkan3d(1);
		break;
	case 2:
	case 3:
		element_trans  = DrawAlken3d(1, Global.work_substance.sub_special_info);
		break;
	case 4:
		element_trans  = DrawAlkin3d(1, Global.work_substance.sub_special_info);
		break;
	}
	
	
	substance_trans.addChild(element_trans);


	return substance_trans;
}

public TransformGroup DrawAldegid3d(){
	System.out.println("DrawAldegid3d");
	TransformGroup substance_trans = new TransformGroup();
	TransformGroup element_trans = new TransformGroup();

	boolean special_connect_forward = false;
	Sphere element_c = new Sphere(0.15f);
	Appearance ap = new Appearance();
	ap.setMaterial(element_c_material);
	element_c.setAppearance(ap);
	
	for(int k=0;k<Global.work_substance.sub_special_info.size();k++){
		if(1==Global.work_substance.sub_special_info.get(k)){//особая связь вперед
			special_connect_forward = true;
		}
	}

	for(int j=0;j<3;j++){
		int rot = 120*j;
		switch (j){
		case 2:
			TransformGroup branch_trans = new TransformGroup();
			//рисуем шар
			TransformGroup oxid_trans = new TransformGroup();
			oxid_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			Transform3D oxid_pos = new Transform3D();
			oxid_pos.setTranslation(new Vector3f(0.6f,0.0f,0.0f));
			Sphere element_o =new Sphere(0.135f);
			ap = new Appearance();
			ap.setMaterial(element_o_material);
			element_o.setAppearance(ap);
			oxid_trans.setTransform(oxid_pos);
			oxid_trans.addChild(element_o);
			
			//рисуем цилиндр
			TransformGroup cylinder_trans1 = new TransformGroup();
			cylinder_trans1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			Transform3D cylinder_pos = new Transform3D();
			cylinder_pos.setTranslation(new Vector3f(0.3f,-0.075f,0.0f));
			Cylinder cylinder = new Cylinder(0.05f,0.6f);
			Matrix3d cylinder_rot = new Matrix3d();
			cylinder_rot.rotZ(Math.toRadians(90));
			cylinder_pos.setRotation(cylinder_rot);
			cylinder_trans1.setTransform(cylinder_pos);
			cylinder_trans1.addChild(cylinder);
			TransformGroup cylinder_trans2 = new TransformGroup();
			cylinder_trans2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			cylinder_pos = new Transform3D();
			cylinder_pos.setTranslation(new Vector3f(0.3f,0.00f,0.0f));
			cylinder = new Cylinder(0.05f,0.6f);
			cylinder_rot = new Matrix3d();
			cylinder_rot.rotZ(Math.toRadians(90));
			cylinder_pos.setRotation(cylinder_rot);
			cylinder_trans2.setTransform(cylinder_pos);
			cylinder_trans2.addChild(cylinder);
			
			
			
			branch_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			Transform3D branch_pos = new Transform3D();
			Matrix3d branch_rot_z = new Matrix3d();
			branch_rot_z.rotZ(Math.toRadians(-30));
			Matrix3d branch_rot_y = new Matrix3d();
			branch_rot_y.rotY(Math.toRadians(rot));
			Matrix3d branch_rot = new Matrix3d();
			branch_rot.mul(branch_rot_y, branch_rot_z);
			branch_pos.setRotation(branch_rot);
			branch_trans.setTransform(branch_pos);
			
			branch_trans.addChild(cylinder_trans1);
			branch_trans.addChild(cylinder_trans2);
			branch_trans.addChild(oxid_trans);
			element_trans.addChild(branch_trans);
			break;
		case 1:
			TransformGroup sphere_trans = new TransformGroup();
			sphere_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			Transform3D sphere_pos = new Transform3D();
			sphere_pos.setTranslation(new Vector3f(0.6f,0.0f,0.0f));
			Sphere element_h =new Sphere(0.1f);
			ap = new Appearance();
			ap.setMaterial(element_h_material);
			element_h.setAppearance(ap);
			sphere_trans.setTransform(sphere_pos);
			sphere_trans.addChild(element_h);
			
			//рисуем цилиндр
			TransformGroup cylinder_trans = new TransformGroup();
			cylinder_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			cylinder_pos = new Transform3D();
			cylinder_pos.setTranslation(new Vector3f(0.3f,0.0f,0.0f));
			cylinder = new Cylinder(0.05f,0.6f);
			cylinder_rot = new Matrix3d();
			cylinder_rot.rotZ(Math.toRadians(90));
			cylinder_pos.setRotation(cylinder_rot);
			cylinder_trans.setTransform(cylinder_pos);
			cylinder_trans.addChild(cylinder);
			
			
			branch_trans = new TransformGroup();
			branch_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			branch_pos = new Transform3D();
			branch_rot_z = new Matrix3d();
			branch_rot_z.rotZ(Math.toRadians(-30));
			branch_rot_y = new Matrix3d();
			branch_rot_y.rotY(Math.toRadians(rot));
			branch_rot = new Matrix3d();
			branch_rot.mul(branch_rot_y, branch_rot_z);
			branch_pos.setRotation(branch_rot);
			branch_trans.setTransform(branch_pos);
			
			branch_trans.addChild(cylinder_trans);
			branch_trans.addChild(sphere_trans);
			element_trans.addChild(branch_trans);
			break;
		case 0:
			if(special_connect_forward){
				
			}else{
				//рисуем шар
				sphere_trans = new TransformGroup();
				sphere_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
				sphere_pos = new Transform3D();
				sphere_pos.setTranslation(new Vector3f(0.6f,0.0f,0.0f));
				element_h =new Sphere(0.1f);
				ap = new Appearance();
				ap.setMaterial(element_h_material);
				element_h.setAppearance(ap);
				sphere_trans.setTransform(sphere_pos);
				sphere_trans.addChild(element_h);
				
				//рисуем цилиндр
				cylinder_trans = new TransformGroup();
				cylinder_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
				cylinder_pos = new Transform3D();
				cylinder_pos.setTranslation(new Vector3f(0.3f,0.0f,0.0f));
				cylinder = new Cylinder(0.05f,0.6f);
				cylinder_rot = new Matrix3d();
				cylinder_rot.rotZ(Math.toRadians(90));
				cylinder_pos.setRotation(cylinder_rot);
				cylinder_trans.setTransform(cylinder_pos);
				cylinder_trans.addChild(cylinder);
				
				
				branch_trans = new TransformGroup();
				branch_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
				branch_pos = new Transform3D();
				branch_rot_z = new Matrix3d();
				branch_rot_z.rotZ(Math.toRadians(-30));
				branch_rot_y = new Matrix3d();
				branch_rot_y.rotY(Math.toRadians(rot));
				branch_rot = new Matrix3d();
				branch_rot.mul(branch_rot_y, branch_rot_z);
				branch_pos.setRotation(branch_rot);
				branch_trans.setTransform(branch_pos);
				
				branch_trans.addChild(cylinder_trans);
				branch_trans.addChild(sphere_trans);
				element_trans.addChild(branch_trans);
			}
		}
	}
	
	substance_trans.addChild(element_trans);
	
	x = x+ 0.6f*(float)Math.cos(Math.toRadians(30));
	
	element_trans = new TransformGroup();
	switch(Global.work_substance.sub_type){
	case 0:
		element_trans  = DrawAlkan3d(1);
		break;
	case 2:
	case 3:
		element_trans  = DrawAlken3d(1, Global.work_substance.sub_special_info);
		break;
	case 4:
		element_trans  = DrawAlkin3d(1, Global.work_substance.sub_special_info);
		break;
	}
	
	
	substance_trans.addChild(element_trans);


	return substance_trans;
}

public TransformGroup DrawKeton3d(){
	System.out.println("DrawPhenol3d");
	TransformGroup substance_trans = new TransformGroup();
	TransformGroup element_trans = new TransformGroup();

	boolean special_connect_forward = false;
	Sphere element_c = new Sphere(0.15f);
	Appearance ap = new Appearance();
	ap.setMaterial(element_c_material);
	element_c.setAppearance(ap);
	
	for(int k=0;k<Global.work_substance.sub_special_info.size();k++){
		if(1==Global.work_substance.sub_special_info.get(k)){//особая связь вперед
			special_connect_forward = true;
		}
	}

	for(int j=0;j<3;j++){
		int rot = 120*j;
		if(j==2){
			TransformGroup branch_trans = new TransformGroup();
			//рисуем шар
			TransformGroup oxid_trans = new TransformGroup();
			oxid_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			Transform3D oxid_pos = new Transform3D();
			oxid_pos.setTranslation(new Vector3f(0.6f,0.0f,0.0f));
			Sphere element_o =new Sphere(0.135f);
			ap = new Appearance();
			ap.setMaterial(element_o_material);
			element_o.setAppearance(ap);
			oxid_trans.setTransform(oxid_pos);
			oxid_trans.addChild(element_o);
			
			//рисуем цилиндр
			TransformGroup cylinder_trans1 = new TransformGroup();
			cylinder_trans1.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			Transform3D cylinder_pos = new Transform3D();
			cylinder_pos.setTranslation(new Vector3f(0.3f,-0.075f,0.0f));
			Cylinder cylinder = new Cylinder(0.05f,0.6f);
			Matrix3d cylinder_rot = new Matrix3d();
			cylinder_rot.rotZ(Math.toRadians(90));
			cylinder_pos.setRotation(cylinder_rot);
			cylinder_trans1.setTransform(cylinder_pos);
			cylinder_trans1.addChild(cylinder);
			TransformGroup cylinder_trans2 = new TransformGroup();
			cylinder_trans2.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			cylinder_pos = new Transform3D();
			cylinder_pos.setTranslation(new Vector3f(0.3f,0.00f,0.0f));
			cylinder = new Cylinder(0.05f,0.6f);
			cylinder_rot = new Matrix3d();
			cylinder_rot.rotZ(Math.toRadians(90));
			cylinder_pos.setRotation(cylinder_rot);
			cylinder_trans2.setTransform(cylinder_pos);
			cylinder_trans2.addChild(cylinder);
			
		
			branch_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			Transform3D branch_pos = new Transform3D();
			Matrix3d branch_rot_z = new Matrix3d();
			branch_rot_z.rotZ(Math.toRadians(-30));
			Matrix3d branch_rot_y = new Matrix3d();
			branch_rot_y.rotY(Math.toRadians(rot));
			Matrix3d branch_rot = new Matrix3d();
			branch_rot.mul(branch_rot_y, branch_rot_z);
			branch_pos.setRotation(branch_rot);
			branch_trans.setTransform(branch_pos);
			
			branch_trans.addChild(cylinder_trans1);
			branch_trans.addChild(cylinder_trans2);
			branch_trans.addChild(oxid_trans);
			element_trans.addChild(branch_trans);
		}else{
			if((j==0)&&(special_connect_forward)){
				
			}else{
				//рисуем шар
				TransformGroup sphere_trans = new TransformGroup();
				sphere_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
				Transform3D sphere_pos = new Transform3D();
				sphere_pos.setTranslation(new Vector3f(0.6f,0.0f,0.0f));
				Sphere element_h =new Sphere(0.1f);
				ap = new Appearance();
				ap.setMaterial(element_h_material);
				element_h.setAppearance(ap);
				sphere_trans.setTransform(sphere_pos);
				sphere_trans.addChild(element_h);
				
				//рисуем цилиндр
				TransformGroup cylinder_trans = new TransformGroup();
				cylinder_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
				Transform3D cylinder_pos = new Transform3D();
				cylinder_pos.setTranslation(new Vector3f(0.3f,0.0f,0.0f));
				Cylinder cylinder = new Cylinder(0.05f,0.6f);
				Matrix3d cylinder_rot = new Matrix3d();
				cylinder_rot.rotZ(Math.toRadians(90));
				cylinder_pos.setRotation(cylinder_rot);
				cylinder_trans.setTransform(cylinder_pos);
				cylinder_trans.addChild(cylinder);
				
				
				TransformGroup branch_trans = new TransformGroup();
				branch_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
				Transform3D branch_pos = new Transform3D();
				Matrix3d branch_rot_z = new Matrix3d();
				branch_rot_z.rotZ(Math.toRadians(-30));
				Matrix3d branch_rot_y = new Matrix3d();
				branch_rot_y.rotY(Math.toRadians(rot));
				Matrix3d branch_rot = new Matrix3d();
				branch_rot.mul(branch_rot_y, branch_rot_z);
				branch_pos.setRotation(branch_rot);
				branch_trans.setTransform(branch_pos);
				
				branch_trans.addChild(cylinder_trans);
				branch_trans.addChild(sphere_trans);
				element_trans.addChild(branch_trans);
			}
		}
	}
	
	element_trans.addChild(element_c);
	substance_trans.addChild(element_trans);
	
	x = x+ 0.6f*(float)Math.cos(Math.toRadians(30));
	
	
	element_trans = new TransformGroup();
	switch(Global.work_substance.sub_type){
	case 0:
		element_trans  = DrawAlkan3d(1);
		break;
	case 2:
	case 3:
		element_trans  = DrawAlken3d(1, Global.work_substance.sub_special_info);
		break;
	case 4:
		element_trans  = DrawAlkin3d(1, Global.work_substance.sub_special_info);
		break;
	}
	
	substance_trans.addChild(element_trans);


	return substance_trans;
}

public TransformGroup DrawKislota3d(){
	System.out.println("DrawPhenol3d");
	TransformGroup substance_trans = new TransformGroup();
	TransformGroup element_trans = new TransformGroup();

	boolean special_connect_forward = false;
	Sphere element_c = new Sphere(0.15f);
	Appearance ap = new Appearance();
	ap.setMaterial(element_c_material);
	element_c.setAppearance(ap);
	
	for(int k=0;k<Global.work_substance.sub_special_info.size();k++){
		if(1==Global.work_substance.sub_special_info.get(k)){//особая связь вперед
			special_connect_forward = true;
		}
	}

	for(int j=0;j<3;j++){
		int rot = 120*j;
		switch (j){
		case 2:
			TransformGroup branch_trans = new TransformGroup();
			//рисуем шар
			TransformGroup oxid_trans = new TransformGroup();
			oxid_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			Transform3D oxid_pos = new Transform3D();
			oxid_pos.setTranslation(new Vector3f(0.6f,0.0f,0.0f));
			Sphere element_o =new Sphere(0.135f);
			ap = new Appearance();
			ap.setMaterial(element_o_material);
			element_o.setAppearance(ap);
			oxid_trans.setTransform(oxid_pos);
			oxid_trans.addChild(element_o);
			
			//рисуем цилиндр
			TransformGroup cylinder_trans = new TransformGroup();
			cylinder_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			Transform3D cylinder_pos = new Transform3D();
			cylinder_pos.setTranslation(new Vector3f(0.3f,0.0f,0.0f));
			Cylinder cylinder = new Cylinder(0.05f,0.6f);
			Matrix3d cylinder_rot = new Matrix3d();
			cylinder_rot.rotZ(Math.toRadians(90));
			cylinder_pos.setRotation(cylinder_rot);
			cylinder_trans.setTransform(cylinder_pos);
			cylinder_trans.addChild(cylinder);
			
			branch_trans.addChild(cylinder_trans);
			
			//рисуем шар
			TransformGroup sphere_trans = new TransformGroup();
			sphere_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			Transform3D sphere_pos = new Transform3D();
			sphere_pos.setTranslation(new Vector3f(1.2f,0.0f,0.0f));
			Sphere element_h =new Sphere(0.1f);
			ap = new Appearance();
			ap.setMaterial(element_h_material);
			element_h.setAppearance(ap);
			sphere_trans.setTransform(sphere_pos);
			sphere_trans.addChild(element_h);
			
			//рисуем цилиндр
			cylinder_trans = new TransformGroup();
			cylinder_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			cylinder_pos = new Transform3D();
			cylinder_pos.setTranslation(new Vector3f(0.9f,0.0f,0.0f));
			cylinder = new Cylinder(0.05f,0.6f);
			cylinder_rot = new Matrix3d();
			cylinder_rot.rotZ(Math.toRadians(90));
			cylinder_pos.setRotation(cylinder_rot);
			cylinder_trans.setTransform(cylinder_pos);
			cylinder_trans.addChild(cylinder);
			
			
			
			branch_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			Transform3D branch_pos = new Transform3D();
			Matrix3d branch_rot_z = new Matrix3d();
			branch_rot_z.rotZ(Math.toRadians(-30));
			Matrix3d branch_rot_y = new Matrix3d();
			branch_rot_y.rotY(Math.toRadians(rot));
			Matrix3d branch_rot = new Matrix3d();
			branch_rot.mul(branch_rot_y, branch_rot_z);
			branch_pos.setRotation(branch_rot);
			branch_trans.setTransform(branch_pos);
			
			branch_trans.addChild(cylinder_trans);
			branch_trans.addChild(sphere_trans);
			branch_trans.addChild(oxid_trans);
			element_trans.addChild(branch_trans);
			break;
		case 1:
			sphere_trans = new TransformGroup();
			sphere_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			sphere_pos = new Transform3D();
			sphere_pos.setTranslation(new Vector3f(0.6f,0.0f,0.0f));
			element_h =new Sphere(0.1f);
			ap = new Appearance();
			ap.setMaterial(element_h_material);
			element_h.setAppearance(ap);
			sphere_trans.setTransform(sphere_pos);
			sphere_trans.addChild(element_h);
			
			//рисуем цилиндр
			cylinder_trans = new TransformGroup();
			cylinder_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			cylinder_pos = new Transform3D();
			cylinder_pos.setTranslation(new Vector3f(0.3f,0.0f,0.0f));
			cylinder = new Cylinder(0.05f,0.6f);
			cylinder_rot = new Matrix3d();
			cylinder_rot.rotZ(Math.toRadians(90));
			cylinder_pos.setRotation(cylinder_rot);
			cylinder_trans.setTransform(cylinder_pos);
			cylinder_trans.addChild(cylinder);
			
			
			branch_trans = new TransformGroup();
			branch_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
			branch_pos = new Transform3D();
			branch_rot_z = new Matrix3d();
			branch_rot_z.rotZ(Math.toRadians(-30));
			branch_rot_y = new Matrix3d();
			branch_rot_y.rotY(Math.toRadians(rot));
			branch_rot = new Matrix3d();
			branch_rot.mul(branch_rot_y, branch_rot_z);
			branch_pos.setRotation(branch_rot);
			branch_trans.setTransform(branch_pos);
			
			branch_trans.addChild(cylinder_trans);
			branch_trans.addChild(sphere_trans);
			element_trans.addChild(branch_trans);
			break;
		case 0:
			if(special_connect_forward){
				
			}else{
				//рисуем шар
				sphere_trans = new TransformGroup();
				sphere_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
				sphere_pos = new Transform3D();
				sphere_pos.setTranslation(new Vector3f(0.6f,0.0f,0.0f));
				element_h =new Sphere(0.1f);
				ap = new Appearance();
				ap.setMaterial(element_h_material);
				element_h.setAppearance(ap);
				sphere_trans.setTransform(sphere_pos);
				sphere_trans.addChild(element_h);
				
				//рисуем цилиндр
				cylinder_trans = new TransformGroup();
				cylinder_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
				cylinder_pos = new Transform3D();
				cylinder_pos.setTranslation(new Vector3f(0.3f,0.0f,0.0f));
				cylinder = new Cylinder(0.05f,0.6f);
				cylinder_rot = new Matrix3d();
				cylinder_rot.rotZ(Math.toRadians(90));
				cylinder_pos.setRotation(cylinder_rot);
				cylinder_trans.setTransform(cylinder_pos);
				cylinder_trans.addChild(cylinder);
				
				
				branch_trans = new TransformGroup();
				branch_trans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
				branch_pos = new Transform3D();
				branch_rot_z = new Matrix3d();
				branch_rot_z.rotZ(Math.toRadians(-30));
				branch_rot_y = new Matrix3d();
				branch_rot_y.rotY(Math.toRadians(rot));
				branch_rot = new Matrix3d();
				branch_rot.mul(branch_rot_y, branch_rot_z);
				branch_pos.setRotation(branch_rot);
				branch_trans.setTransform(branch_pos);
				
				branch_trans.addChild(cylinder_trans);
				branch_trans.addChild(sphere_trans);
				element_trans.addChild(branch_trans);
			}
		}
	}
	element_trans.addChild(element_c);
	
	substance_trans.addChild(element_trans);
	
	x = x+ 0.6f*(float)Math.cos(Math.toRadians(30));
	
	element_trans = new TransformGroup();
	switch(Global.work_substance.sub_type){
	case 0:
		element_trans  = DrawAlkan3d(1);
		break;
	case 2:
	case 3:
		element_trans  = DrawAlken3d(1, Global.work_substance.sub_special_info);
		break;
	case 4:
		element_trans  = DrawAlkin3d(1, Global.work_substance.sub_special_info);
		break;
	}
	
	System.out.println("x: "+x);
	
	
	substance_trans.addChild(element_trans);

	return substance_trans;
}

public BranchGroup createSceneGraph() {
	System.out.println("CreateSceneGraph");
	x = 0;
	
	element_h_material.setAmbientColor(gray);
	element_h_material.setDiffuseColor(gray);
	element_h_material.setSpecularColor(white);
	element_h_material.setEmissiveColor(black);
	element_h_material.setShininess(64.0f);
	element_h_material.setColorTarget(Material.DIFFUSE);
	
	element_c_material.setAmbientColor(red);
	element_c_material.setDiffuseColor(red);
	element_c_material.setSpecularColor(white);
	element_c_material.setEmissiveColor(black);
	element_c_material.setShininess(64.0f);
	element_c_material.setColorTarget(Material.DIFFUSE);
	
	element_o_material.setAmbientColor(yellow);
	element_o_material.setDiffuseColor(yellow);
	element_o_material.setSpecularColor(white);
	element_o_material.setEmissiveColor(black);
	element_o_material.setShininess(64.0f);
	element_o_material.setColorTarget(Material.DIFFUSE);
	
    // Create the root of the branch graph
	BranchGroup objRoot = new BranchGroup();
	
	objTrans = new TransformGroup();
	objTrans.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

	TransformGroup substance_trans = new TransformGroup();
	
	switch(Global.work_substance.type){
	case 0:
		substance_trans=DrawAlkan3d(0);
		break;
	case 1:
		break;
	case 2://алкадиен
	case 3://алкен
		substance_trans=DrawAlken3d(0,Global.work_substance.special_info);
		break;
	case 4://алкин
		substance_trans=DrawAlkin3d(0,Global.work_substance.special_info);
		break;
	case 5://спирты(-ол)
		substance_trans=DrawPhenol3d();
		break;
	case 6://альдегид(-аль)
		substance_trans=DrawAldegid3d();
		break;
	case 7://кетон(-он)
		substance_trans=DrawKeton3d();
		break;
	case 8://карбоновые кислоты
		substance_trans=DrawKislota3d();
		break;
	}
	

	Transform3D substance_pos = new Transform3D();
	substance_pos.setTranslation(new Vector3f(-x/2,0.0f,0.0f));
	substance_trans.setTransform(substance_pos);
	
	objTrans.addChild(substance_trans);
	objRoot.addChild(objTrans);
   
   BoundingSphere bounds =
      new BoundingSphere(new Point3d(0.0,0.0,0.0), 100.0);
   Color3f light1Color = new Color3f(1.0f, 1.0f, 1.0f);
   Vector3f light1Direction = new Vector3f(4.0f, -7.0f, -12.0f);
   DirectionalLight light1
      = new DirectionalLight(light1Color, light1Direction);
   light1.setInfluencingBounds(bounds);
   objRoot.addChild(light1);

   // Set up the ambient light
   Color3f ambientColor = new Color3f(1.0f, 1.0f, 1.0f);
   AmbientLight ambientLightNode = new AmbientLight(ambientColor);
   ambientLightNode.setInfluencingBounds(bounds);
   objRoot.addChild(ambientLightNode);

   return objRoot;
}
public void init(){
	System.out.println("ChemCanvas3d.init()");
	setLayout(new BorderLayout());
	//GraphicsConfigTemplate3D graphicsTemplate = new GraphicsConfigTemplate3D();
	
	
    //GraphicsConfiguration gc1 = 
      //GraphicsEnvironment.getLocalGraphicsEnvironment()
        //.getDefaultScreenDevice().getBestConfiguration(graphicsTemplate);
	//GraphicsConfiguration config = new GraphicsConfiguration();
     GraphicsConfiguration config =
	      SimpleUniverse.getPreferredConfiguration();
	   Canvas3D c = new Canvas3D(config);
	   add("Center", c);

	   c.addMouseWheelListener(new MouseAdapter(){

		  
		   public void mouseWheelMoved(MouseWheelEvent e){
			   Transform3D pos1 = new Transform3D();
				Vector3d v = new Vector3d();

				VpTG.getTransform(pos1);
				pos1.get(v);
			   float Zcamera = (float)v.z+e.getWheelRotation()*0.1f; //put the camera 12 meters back
			   Transform3D Trfcamera = new Transform3D();
			   Trfcamera.setTranslation(new Vector3f(0.0f, 0.0f, Zcamera));  
			   
			   VpTG.setTransform( Trfcamera );
		   }
	   });
	   
	   c.addMouseMotionListener(this);
	   
	   //timer.start();

	   // Create a simple scene and attach it to the virtual universe

	   BranchGroup scene = createSceneGraph();
	   
	   System.out.println("Global.work_substance.c_count"+Global.work_substance.c_count);

	   SimpleUniverse u = new SimpleUniverse(c);
	   
	   u.getViewingPlatform().setNominalViewingTransform();

	   VpTG = u.getViewingPlatform().getViewPlatformTransform();

	   
	   float Zcamera = 2.5f+(float)Global.work_substance.c_count/2; 
	   Transform3D Trfcamera = new Transform3D();
	   Trfcamera.setTranslation(new Vector3f(0.0f, 0.0f, Zcamera));  
	   VpTG.setTransform( Trfcamera );

	   u.addBranchGraph(scene);
}
public ChemCanvas3d() {
	
   this.repaint();

}

public void paint (Graphics dr) {
	System.out.println("chemcanvas3d.paint()");
	
}

public void mouseMoved(MouseEvent e){
	mouse_prev_x=e.getX();
	mouse_prev_y=e.getY();
}

public void mouseDragged(MouseEvent e){
	Transform3D pos1 = new Transform3D();
	Vector3d v = new Vector3d();
	Matrix3d m = new Matrix3d();

	objTrans.getTransform(pos1);
	pos1.get(v);
	pos1.get(m);
	Matrix3d mx = new Matrix3d(m);
	Matrix3d my = new Matrix3d(m);
	//m.rotX(1.1f);
	xx=xx+(e.getX()-mouse_prev_x)/100f;
	yy=yy+(e.getY()-mouse_prev_y)/100f;
	//v.z=12;
	//m.rotZ(xx);
	mouse_prev_x=e.getX();
	mouse_prev_y=e.getY();
	mx.rotY(xx);
	my.rotX(yy);
	m.mul(mx,my);
	trans.setRotation(m);
	trans.setTranslation(v);
	objTrans.setTransform(trans);
}


}