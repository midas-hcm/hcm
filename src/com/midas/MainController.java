package com.midas;

import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import com.midas.db.Employee;
import com.midas.db.HolidayRequest;
import com.midas.db.service.DBService;
import com.midas.db.service.DBServiceImpl;
import com.midas.mainpage.service.Loginservice;
import com.midas.mainpage.service.LoginserviceImp;
import com.midas.service.CommonService;
import com.midas.service.CommonServiceImpl;
import com.midas.taa.service.CalendarService;
import com.midas.taa.service.CalendarServiceImpl;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class MainController extends Controller implements Initializable{

	private Parent root;
	private CommonService comServ;
	private DBService dbServ;
	private Loginservice loginServ;
	private CalendarService calServ;


	// HomePage.fxml
	@FXML private Label IDLbl;
	@FXML private Label helloLbl;
	@FXML private Button logoutBtn;
	@FXML private Button commuteBtn;


	private Parent getScene(ActionEvent e) {
		Parent btnObj = (Parent)e.getSource();
		return btnObj.getScene().getRoot();
	}

	private Parent getScene(Event e) {
		Parent btnObj = (Parent)e.getSource();
		return btnObj.getScene().getRoot();
	}

	@Override
	public void setRoot(Parent root) {
		this.root = root;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		comServ = new CommonServiceImpl();
		dbServ = new DBServiceImpl();
		loginServ = new LoginserviceImp();
		calServ = new CalendarServiceImpl();
	}



	// Homepage
	public void HomepageView(ActionEvent e) {
		BorderPane borderPane = (BorderPane)getScene(e);
		Parent scene = comServ.AddScene("/com/midas/Homepage.fxml");
		borderPane.setCenter(scene);
	}

	public void EmployeeMenuView(ActionEvent e) {
		BorderPane borderPane = (BorderPane)getScene(e);
		Parent scene = comServ.AddScene("/com/midas/Employee.fxml");
		borderPane.setLeft(scene);
	}

	public void ManagerMenuView(ActionEvent e) {
		BorderPane borderPane = (BorderPane)getScene(e);
		Parent scene = comServ.AddScene("/com/midas/Manager.fxml");
		borderPane.setLeft(scene);
	}





	public void logoutBtnProc(ActionEvent e) {
		comServ.ErrorMsg("로그아웃", "로그아웃 됩니다.", "로그아웃이 실행됩니다.");
		comServ.WindowClose(e);
	}

	public void commuteBtnProc(ActionEvent e) {
		System.out.println("출근");
	}

	public void Lbl(Parent root, String id) {
		comServ.setUserLabel(root, id);
	}




	public void MyPageView(Event e) {
		BorderPane borderPane = (BorderPane)getScene(e);
		Parent scene = comServ.AddScene("/com/midas/mypage/infoPwCheck.fxml");
		borderPane.setCenter(scene);
	}










	// Salary
	public void SalaryMgmtView(Event e) {
		BorderPane borderPane = (BorderPane)getScene(e);
		Parent scene = comServ.AddScene("/com/midas/salary/SalaryMgmt.fxml");
		borderPane.setCenter(scene);
	}

	public void SalaryReportView(Event e) {

		BorderPane borderPane = (BorderPane)getScene(e);
		Parent scene = comServ.AddScene("/com/midas/salary/SalaryReport.fxml");
		borderPane.setCenter(scene);
	}

	public void SalaryStmtView(Event e) {
		BorderPane borderPane = (BorderPane)getScene(e);
		Parent scene = comServ.AddScene("/com/midas/salary/SalaryStmt.fxml");
		borderPane.setCenter(scene);
	}



	
	
	
	


	//TAA Manager
	public void SetCalendarView(Event e) {
		BorderPane borderPane = (BorderPane)getScene(e);
		root = comServ.AddSceneWithController("/com/midas/taa/SetCalendar.fxml");
		borderPane.setCenter(root);

		BorderPane pane = (BorderPane)borderPane.getCenter();
		pane.setCenter(calServ.getView());
		DatePicker dp = (DatePicker)root.lookup("#setCalendarDatePicker");
		dp.setValue(LocalDate.now());
	}

	public void TAAReportView(Event e) {
		BorderPane borderPane = (BorderPane)getScene(e);
		Parent root = comServ.AddScene("/com/midas/taa/TAAReport.fxml");
		borderPane.setCenter(root);
	}

	public void MonthTAAView(Event e) {
		BorderPane borderPane = (BorderPane)getScene(e);
		root = comServ.AddScene("/com/midas/taa/MonthTAA.fxml");

		Parent form = root;
		String [] departItems= {"부서 전체", "부서1", "부서2", "부서3", "부서4", "부서5"};
		String [] sortItems= {"전체", "사원번호", "이름"};
		comServ.AddComboBox(form, Arrays.asList(departItems), "#cmbDepart");
		comServ.AddComboBox(form, Arrays.asList(sortItems), "#cmbSort");

		borderPane.setCenter(root);
	}

	public void PersonalTAAView(Event e) {
		BorderPane borderPane = (BorderPane)getScene(e);
		root = comServ.AddScene("/com/midas/taa/PersonalTAA.fxml");

		Parent form = root;
		String [] departItems= {"부서 전체", "회계", "마케팅", "인사", "영업"};
		comServ.AddComboBox(form, Arrays.asList(departItems), "#cmbDepart");

		borderPane.setCenter(root);
	}

	public void TAAListView(Event e) {
		BorderPane borderPane = (BorderPane)getScene(e);
		root = comServ.AddScene("/com/midas/taa/TAAList.fxml");
		Parent form = root;
		borderPane.setCenter(root);
	}

	public void HolidayApprovalView(Event e) {
		BorderPane borderPane = (BorderPane)getScene(e);
		root = comServ.AddScene("/com/midas/taa/HolidayApproval.fxml");
		Parent form = root;
		borderPane.setCenter(root);

		Scene scene = ((Parent)e.getSource()).getScene();
		List<HolidayRequest> requestList = dbServ.SelectTable("HolidayRequest", "");
		comServ.ShowTableViewByList(scene, "#HoliAppTableView", requestList);
	}

	public void HolidayModifyView(Event e) {
		BorderPane borderPane = (BorderPane)getScene(e);
		root = comServ.AddScene("/com/midas/taa/HolidayModify.fxml");
		borderPane.setCenter(root);
	}

	
	
	
	
	//TAA Employee
	public void OwnTAAView(Event e) {
		BorderPane borderPane = (BorderPane)getScene(e);
		root = comServ.AddScene("/com/midas/taa/own/OwnTAA.fxml");
		borderPane.setCenter(root);
	}

	public void OwnAskHolidayView(Event e) {
		BorderPane borderPane = (BorderPane)getScene(e);
		root = comServ.AddScene("/com/midas/taa/own/OwnAskHoliday.fxml");

		Parent form = root;
		String [] FullHalfItems= {"전일", "반일"};
		comServ.AddComboBox(form, Arrays.asList(FullHalfItems), "#cmbFullHalf");

		borderPane.setCenter(root);

		Scene scene = ((Parent)e.getSource()).getScene();
		List<Employee> OwnHolidayList = dbServ.SelectTableHoliday("WHERE 사원번호 = \"200401\""); //로그인 한 사람의 아이디
		comServ.ShowTableViewByList(scene, "#OwnRemainTable", OwnHolidayList);
	}

	public void OwnModifyHolidayView(Event e) {
		BorderPane borderPane = (BorderPane)getScene(e);
		root = comServ.AddScene("/com/midas/taa/own/OwnModifyHoliday.fxml");

		String [] TypeItems= {"연차", "출장", "조퇴", "결근", "지각", "출근"};

		borderPane.setCenter(root);

		Scene scene = ((Parent)e.getSource()).getScene();
		List<Employee> OwnHolidayList = dbServ.SelectTableHoliday("WHERE 사원번호 = \"200401\""); //로그인 한 사람의 아이디
		comServ.ShowTableViewByList(scene, "#OwnRemainTable", OwnHolidayList);
	}







	//HR
	public void HRPageView(Event e) {
		BorderPane borderPane = (BorderPane)getScene(e);
		root = comServ.AddSceneWithController("/com/midas/hr/HRMpage.fxml");
		borderPane.setCenter(root);
	}





}
