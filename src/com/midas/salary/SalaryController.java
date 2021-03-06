package com.midas.salary;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;

import com.midas.Controller;
import com.midas.db.SalaryResult;
import com.midas.db.service.DB2ExcelExporter;
import com.midas.db.service.DBServiceImpl;
import com.midas.salary.service.SalaryService;
import com.midas.salary.service.SalaryServiceImpl;
import com.midas.service.CommonService;
import com.midas.service.CommonServiceImpl;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SalaryController extends Controller implements Initializable{

	private Parent root;
	private CommonService comServ;
	//private DBService dbServ;
	private SalaryService salServ;
	
	//salaryMgmt.fxml
	@FXML private TextField salaryMgmtNameTextField;
	@FXML private ComboBox salaryMgmtComboBox;
	@FXML private ChoiceBox salaryMgmtChoiceBox;
	@FXML private DatePicker salaryMgmtDatePicker;
	
	//salaryReport.fxml
	@FXML private TextField salaryReportNameTextField;
	@FXML private ComboBox salaryReportComboBox;
	@FXML private ChoiceBox salaryReportChoiceBox;
	@FXML private DatePicker salaryReportDatePicker;
	
	//salaryStmt.fxml
	@FXML private DatePicker salaryStmtDatePicker;
	@FXML private TextArea salaryStmtTextArea;
	
	private List<SalaryResult> salaryResultList;
	private Map<String, String> m;
	
	@Override
	public void setRoot(Parent root) {
		this.root = root;
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		comServ = new CommonServiceImpl();
//		dbServ = new DBServiceImpl();
		salServ = new SalaryServiceImpl();
		
		m = new HashMap<String, String>(){{
			put("사원번호", "num");
			put("아이디", "id");
			put("이름", "name");
			put("부서", "department");
		}};
		
//		salaryMgmtComboBox.getItems().addAll(Arrays.asList("사원번호", "아이디", "부서"));
//		salaryMgmtChoiceBox.getItems().addAll(Arrays.asList("사원번호", "아이디", "부서"));
//		
//		salaryReportComboBox.getItems().addAll(Arrays.asList("사원번호", "아이디", "부서"));
//		salaryReportChoiceBox.getItems().addAll(Arrays.asList("사원번호", "아이디", "부서"));
	}


	public void ShowTableProc(ActionEvent e) {
		Scene scene = ((Parent)e.getSource()).getScene();
		String option = "";
		
		salaryResultList = null;
		
		if(salaryMgmtNameTextField.getText().length() > 0) {
			option += "WHERE " + m.get(salaryMgmtComboBox.getValue()) + " like '%" + salaryMgmtNameTextField.getText() + "%'";
		}
		salaryResultList = new DBServiceImpl().SelectTable("SalaryResult", option);
		
		salServ.ShowTableViewByList(scene, "#salaryMgmtTableView", salaryResultList);
	}

	public void SalaryMgmtExportToExcel(ActionEvent e) {
		new DB2ExcelExporter().export(salaryResultList);
		comServ.ErrorMsg("엑셀로 내보내기 완료 !");
	}
	
	public void ShowReportProc(ActionEvent e) {
		Scene scene = ((Parent)e.getSource()).getScene();
		String option = "";
		
		salaryResultList = null;
		
		if(salaryReportNameTextField.getText().length() > 0) {
			option += "WHERE " + m.get(salaryReportComboBox.getValue()) + " like '%" + salaryReportNameTextField.getText() + "%'";
		}
		salaryResultList = new DBServiceImpl().SelectTable("SalaryResult", option);
		
		salServ.ShowLineChartByList(scene, "#salaryReportLineChart", salaryResultList);
	}

	public void SalaryReportExportToExcel() {
		new DB2ExcelExporter().export(salaryResultList);
	}
	
	public void ShowStmtProc(ActionEvent e) {
		Scene scene = ((Parent)e.getSource()).getScene();
		String option = "WHERE num = '" + comServ.getUserLabel(root) + "' " + 
						"AND month = " + String.valueOf(salaryStmtDatePicker.getValue().getMonthValue());
		
		List<SalaryResult> salaryResultList = new DBServiceImpl().SelectTable("SalaryResult", option);
		
		if(salaryResultList.isEmpty()) return;

		salServ.ShowSalaryStmt(scene, "#salaryStmtTextArea", salaryResultList.get(0));
	}
	
	public void SalaryStmtExportToExcel(ActionEvent e) {
		new DB2ExcelExporter().export(salaryStmtTextArea.getText());
	}
}
