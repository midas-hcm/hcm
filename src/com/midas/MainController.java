package com.midas;

import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.ResourceBundle;

import com.midas.db.Commute;
import com.midas.db.Employee;
import com.midas.db.service.DBService;
import com.midas.db.service.DBServiceImpl;
import com.midas.mainpage.service.HompageService;
import com.midas.mainpage.service.HompageServiceImp;
import com.midas.mainpage.service.Loginservice;
import com.midas.mainpage.service.LoginserviceImp;
import com.midas.service.CommonService;
import com.midas.service.CommonServiceImpl;


import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;

public class MainController extends Controller implements Initializable{

	private Parent root;
	private CommonService comServ;
	private DBService dbServ;

	private Loginservice loginServ;
	private HompageService homeServ;


//	@FXML private Button loginBtn;
//	@FXML private TextField loginIdTxt;
//	@FXML private TextField loginPwTxt;


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
		homeServ = new HompageServiceImp();



		//////////////////////////////////////////////////////////
		/* 출근에 대한 DB 저장 기본 내용
		Commute com = new Commute();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String timeStr = sdf.format(cal.getTime());
		com.setNum("200401"); //로그인 한 사람
		com.setSortation("출근"); //출근 버튼이면 출근
		com.setDate(LocalDate.now().toString()); //출퇴근일 : 오늘
		com.setTime(timeStr);
		com 테스트로 get 출력해보기..
		new DBServiceImpl().SaveCommute(com);
		 */



		/*  응용 1.. 위에꺼 지워야 함
		   버튼에 setOnAction 만들어서 테스트해본 후 DB에 잘 들어가는지 확인.
		  데이터 베이스에 commute 테이블 중 '시간' 칼럼은 널값이 들어갈 수도 있기 때문에(응용할 수도 있어서 냅둠.)
		  com.setTime 하기 전에 timeStr이 들어가는지 확인해본 후 작성할 것.
		  */
		
		/*
		DBService dbServ = new DBServiceImpl();  
		Commute com = new Commute();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String timeStr = sdf.format(cal.getTime());  

		public void GoToWorkProc(ActionEvent e){ //출근버튼 누르면
			com.setNum("200401"); //로그인 한 사람 사원번호
			com.setSortation("출근"); //출근 버튼이 눌렸으므로 출근
			com.setDate(LocalDate.now().toString()); //출퇴근일 : 오늘
			com.setTime(timeStr); //현재 시간
			if(dbServ.SaveCommute(com)) comServ.ErrorMsg("출근완료");
			else comServ.ErrorMsg("출근error 관리자 문의");
		}
		public void GetOffWorkProc(ActionEvent e){ //퇴근버튼 누르면
			com.setNum("200401"); //로그인 한 사람 사원번호
			com.setSortation("퇴근"); //퇴근 버튼이 눌렸으므로 퇴근
			com.setDate(LocalDate.now().toString()); //출퇴근일 : 오늘
			com.setTime(timeStr); //현재 시간
			if(dbServ.SaveCommute(com)) comServ.ErrorMsg("출근완료");
			else comServ.ErrorMsg("출근error 관리자 문의");
		}
		 */
		
		/*
		 * //응용 2.. 위에꺼 지워야 함. DBService에 옮겨놓은 메소드로 간결하게 함. DBService dbServ = new
		 * DBServiceImpl(); Commute commute = new Commute(); Calendar cal =
		 * Calendar.getInstance(); SimpleDateFormat sdf = new
		 * SimpleDateFormat("HH:mm:ss"); String timeStr = sdf.format(cal.getTime());
		 * 
		 * //if //public void GoToWorkProc(ActionEvent e){ //출근버튼 누르면
		 * dbServ.SaveCommute(commute, "200401", "출근", timeStr);
		 * comServ.ErrorMsg("출근완료", "명언"); } //public void GetOffWorkProc(ActionEvent
		 * e){ //퇴근버튼 누르면 dbServ.SaveCommute(commute, "200401", "퇴근", timeStr);
		 * comServ.ErrorMsg("퇴근완료", "명언"); //}
		 */		 
		//////////////////////////////////////////////////////////




		//		
		//		loginBtn.setOnAction(e->{			
		//			loginBtnProc();
		//		});
		//
		//		loginBtn.setDisable(true);
		//
		//		loginIdTxt.textProperty().addListener((obs, oldTxt, newTxt)->{
		//			disableButton();
		//
		//		});
		//		loginPwTxt.textProperty().addListener((obs, oldTxt, newTxt)->{
		//			disableButton();
		//		});
		//
		//		loginIdTxt.setOnAction(e->loginPwTxt.requestFocus());
		//		loginPwTxt.setOnAction(e->loginBtn.requestFocus());
		//		
		
		

	}



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
		DBService dbServ = new DBServiceImpl();
		String num = IDLbl.getText();
		Commute commute = new Commute();
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		String timeStr = sdf.format(cal.getTime());
		
		if(commuteBtn.getText().contentEquals("출근")) {
			dbServ.SaveCommute(commute, num, "출근", timeStr);
			//dbServ.SaveCommute(commute);
			System.out.println(timeStr);
			comServ.ErrorMsg("출/퇴근", "출근입니다.", "출근하였습니다.\n오늘하루도 화이팅입니다!");
			commuteBtn.setText("퇴근");
		}

		else if(commuteBtn.getText().contentEquals("퇴근")) {		
			if(comServ.ConfirmMsg("출/퇴근", "퇴근입니다.", "퇴근하시겠습니까?")) {
				dbServ.SaveCommute(commute, num, "퇴근", timeStr);
				comServ.ErrorMsg("출/퇴근", "퇴근입니다.", "퇴근하였습니다.\n오늘하루도 고생하셨습니다!");
				commuteBtn.setText("출근");
			}
			else {}
		}				
	}

	public void Lbl(Parent root, String id) {
		homeServ.setUserLabel(root, id);
	}
	
	
	
	
	public void MyPageView(Event e) {
		BorderPane borderPane = (BorderPane)getScene(e);
		root = comServ.AddSceneWithController("/com/midas/mypage/infoPwCheck.fxml");
		borderPane.setCenter(root);
		System.out.println("RRRRRRRRRRRRRRRRRR " + root);
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
		Parent root = comServ.AddScene("/com/midas/taa/SetCalendar.fxml");
		borderPane.setCenter(root);
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
		String [] departItems= {"부서 전체", "회계", "마케팅", "인사", "영업"};
		String [] sortItems= {"전체", "사원번호", "이름"};
		comServ.AddComboBox(form, Arrays.asList(departItems), "#cmbDepart");
		comServ.AddComboBox(form, Arrays.asList(sortItems), "#cmbSort");

		borderPane.setCenter(root);
	}

	public void HolidayApprovalView(Event e) {
		BorderPane borderPane = (BorderPane)getScene(e);
		root = comServ.AddScene("/com/midas/taa/HolidayApproval.fxml");

		Parent form = root;
		String [] departItems= {"부서 전체", "회계", "마케팅", "인사", "영업"};
		String [] sortItems= {"사원번호", "이름"};
		String [] approvalItems = {"승인", "미승인", "반려"};
		comServ.AddComboBox(form, Arrays.asList(departItems), "#cmbDepart");
		comServ.AddComboBox(form, Arrays.asList(sortItems), "#cmbSort");
		comServ.AddComboBox(form, Arrays.asList(approvalItems), "#cmbApproval");

		borderPane.setCenter(root);
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
		List<Employee> OwnHolidayList = dbServ.SelectTable("Employee", "WHERE id = \"200401\""); //로그인 한 사람의 아이디
		comServ.ShowTableViewByList(scene, "#OwnRemainTable", OwnHolidayList);
	}

	public void OwnModifyHolidayView(Event e) {
		BorderPane borderPane = (BorderPane)getScene(e);
		root = comServ.AddScene("/com/midas/taa/own/OwnModifyHoliday.fxml");

		String [] TypeItems= {"연차", "출장", "조퇴", "결근", "지각", "출근"};

		borderPane.setCenter(root);
	}
	
	

	//HR
	public void HRPageView(Event e) {
		BorderPane borderPane = (BorderPane)getScene(e);
		root = comServ.AddScene("/com/midas/hr/HRMpage.fxml");
		borderPane.setCenter(root);
	}
}
