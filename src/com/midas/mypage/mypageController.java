package com.midas.mypage;

import java.net.URL;
import java.util.ResourceBundle;

import com.midas.Controller;
import com.midas.db.Employee;
import com.midas.db.service.DBService;
import com.midas.db.service.DBServiceImpl;
import com.midas.mypage.serivce.MypageService;
import com.midas.mypage.serivce.MypageServiceImpl;
import com.midas.service.CommonService;
import com.midas.service.CommonServiceImpl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;


public class mypageController extends Controller implements Initializable{

	Parent root;
	@FXML private TextField nameTxt;
	@FXML private TextField phoneNumTxt;
	@FXML private TextField addressTxt;
	@FXML private TextField emailTxt;
	@FXML private Label departmemtLbl;
	@FXML private Label finalEduLbl;
	@FXML private Label positionLbl;
	@FXML private Label joinDateLbl;
	@FXML private Label birthLbl;
	@FXML private Label employeeLbl;
	@FXML private Button modifyBtn;		// 수정버튼
	
	@FXML private TextField newPwTxt;
	@FXML private TextField newPwOKTxt;

	private CommonService comServ;
	private MypageService myServ;
	
	@Override
	public void setRoot(Parent root) {
		// TODO Auto-generated method stub
		this.root = root;
//		System.out.println(root);
//		setInfo();
	}
	
	private Parent getScene(ActionEvent e) {
		Parent btnObj = (Parent)e.getSource();
		return btnObj.getScene().getRoot();
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		comServ = new CommonServiceImpl();
		myServ = new MypageServiceImpl();
		
		newPwTxt.textProperty().addListener((obs, oldTxt, newTxt)->{
		});
		
		newPwOKTxt.textProperty().addListener((obs, oldTxt, newTxt)->{
		});
		
		nameTxt.setOnAction(e->newPwTxt.requestFocus());
		newPwTxt.setOnAction(e->newPwOKTxt.requestFocus());
		newPwOKTxt.setOnAction(e->emailTxt.requestFocus());
		emailTxt.setOnAction(e->phoneNumTxt.requestFocus());
		phoneNumTxt.setOnAction(e->addressTxt.requestFocus());
		addressTxt.setOnAction(e->modifyBtn.requestFocus());
		//System.out.println(root);
//		setInfo();
	}

	public void modifyBtnProc(ActionEvent e) {
		DBService dbServ = new DBServiceImpl();
		Employee employee = new Employee();
		
		employee.setName(nameTxt.getText());
		employee.setPhone(phoneNumTxt.getText());
		employee.setEmail(emailTxt.getText());
		employee.setAddress(addressTxt.getText());
		
		
		if(newPwTxt.getText().length()>0 && newPwOKTxt.getText().length()==0) {
			
			comServ.ErrorMsg("내 정보 수정","새로운 비밀번호 확인","새로운 비밀번호 확인을 입력해주세요.");
			newPwOKTxt.requestFocus();
		}
		else if(newPwTxt.getText().length()>0 && newPwOKTxt.getText().length()>0
				&& newPwTxt.getText().equals(newPwOKTxt.getText())) {	
			dbServ = new DBServiceImpl();
			employee.setPw(newPwTxt.getText());
			dbServ.mypage(employeeLbl.getText(), employee, false);
			comServ.ErrorMsg("내 정보 수정","새로운 비밀번호 확인","새로운 비밀번호로 변경되었습니다.");
			setHelloLbl(e);
			//comServ.WindowClose(e);
		}
		else if(newPwTxt.getText().length()>0 && newPwOKTxt.getText().length()>0
				&& !newPwTxt.getText().equals(newPwOKTxt.getText())) {		
			
			comServ.ErrorMsg("내 정보 수정","새로운 비밀번호 확인","새로운 비밀번호와 확인 비밀번호가 같지않습니다. \n다시입렵해주세요.");
			newPwOKTxt.requestFocus();
			newPwOKTxt.clear();		
		}
		else if(newPwTxt.getText().length()==0 && newPwOKTxt.getText().length()==0) {
			dbServ = new DBServiceImpl();
			dbServ.mypage(employeeLbl.getText(), employee, true);
			comServ.ErrorMsg("내 정보 수정","정보수정","새로운 정보로 정보로 저장되었습니다.");
			setHelloLbl(e);
			//comServ.WindowClose(e);
			
		}
	}
	
	private void setHelloLbl(ActionEvent e) {
		Label helloLbl = (Label)getScene(e).lookup("#helloLbl");
		helloLbl.setText(nameTxt.getText()+"님 안녕하세요");
	}
	
//	public void setInfo() {
//		dbServ = new DBServiceImpl();
//		System.out.println("SetINFO");
//		//String id = comServ.getUserLabel(root);
//		//System.out.println("USER ID : " + id);
//		//Employee emp = myServ.getEmployee(id);
//		Employee emp = myServ.getEmployee("cat");
//
//		phoneNumTxt.setText(emp.getPhone());
//		nameTxt.setText(emp.getName());
//		birthLbl.setText(emp.getBirth());
//		departmemtLbl.setText(emp.getDepartment());
//		emailTxt.setText(emp.getEmail());
//		addressTxt.setText(emp.getAddress());
//		positionLbl.setText(emp.getPosition());
//		finalEduLbl.setText(emp.getEducation());
//		employeeLbl.setText(emp.getNum());
//		joinDateLbl.setText(emp.getJoin());
//
//		/*
//		 * if(member.getimage() == null || member.getimage().length() == 0) {
//		 * employee_picture.setImage(new Image("/MIDAS/image/기본사진.png")); } else {
//		 * employee_picture.setImage(new
//		 * Image(getClass().getResource(member.getimage()).toString())); }
//		 */
//		//if 등록한 사진이 없을 땐 기본사진  else 등록한 사진이 있을 때는 등록된 사진 출력
//	}


}
