package com.midas.taa;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import com.midas.Controller;
import com.midas.db.HolidayRequest;
import com.midas.db.service.DBService;
import com.midas.db.service.DBServiceImpl;
import com.midas.service.CommonService;
import com.midas.service.CommonServiceImpl;
import com.midas.taa.service.TAAService;
import com.midas.taa.service.TAAServiceImpl;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class HolidayApprovalController extends Controller implements Initializable{
	private Parent root;
	private DBService dbServ;
	private CommonService comServ;
	private TAAService TAAServ;
	@FXML DatePicker ApprovalDatePicker;
	@FXML ComboBox<String> cmbSort;
	@FXML TextField searchTf;
	private final static String[] ATTRIBUTE = {"사원번호", "이름", "부서", "잔여 연차", "요청일", "시작일", "종료일", "기간", "사유", "승인여부"};
	@FXML TableView<String> HoliAppTableView;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ApprovalDatePicker.setValue(LocalDate.now());
		dbServ = new DBServiceImpl();
		comServ = new CommonServiceImpl();
		TAAServ = new TAAServiceImpl();
		cmbSort.getItems().addAll(ATTRIBUTE);
		cmbSort.setValue("사원번호");
	}
	@Override
	public void setRoot(Parent root) {
		this.root = root;
	}
	
	public void HoliAppTableShow(ActionEvent e) {
		Scene scene = ((Parent)e.getSource()).getScene();
		String attribute = cmbSort.getValue();
		String txt = searchTf.getText();
		List<HolidayRequest> requestList = dbServ.SelectHolidayApprovalSearch(attribute, txt);
		comServ.ShowTableViewByList(scene, "#HoliAppTableView", requestList);
	}
	public void approvalBtn(ActionEvent e) {
		String attribute = cmbSort.getValue();
		String txt = searchTf.getText();
		List<HolidayRequest> requestList = dbServ.SelectHolidayApprovalSearch(attribute, txt);
		
		int row = HoliAppTableView.getSelectionModel().getSelectedIndex()+1;
		String num = requestList.get(row-1).getId();
		String periodDay = requestList.get(row-1).getPeriodDay();
		System.out.println(row+" "+num+" "+periodDay);
		System.out.println(requestList.get(row-1).getApproval());
		if("미승인".contentEquals(requestList.get(row-1).getApproval())) {
			dbServ.updateApprovalHoliday("WHERE ROWID="+row);
			dbServ.updateEmployeeHoliday("WHERE "+"\"사원번호\""+" = "+num, periodDay);
			dbServ.updateEmployeeHoliday2("WHERE "+"\"사원번호\""+" = "+num, periodDay);
			comServ.ErrorMsg("휴가가 승인되었습니다.");
		}
		else if("승인".contentEquals(requestList.get(row-1).getApproval())) {
			comServ.ErrorMsg("이미 승인되었습니다.");
		}
		else { //반려
			dbServ.updateApprovalHoliday("WHERE ROWID="+row);
			dbServ.updateEmployeeHoliday("WHERE "+"\"사원번호\""+" = "+num, periodDay);
			dbServ.updateEmployeeHoliday2("WHERE "+"\"사원번호\""+" = "+num, periodDay);
			comServ.ErrorMsg("반려 휴가가 재승인되었습니다.");
		}
	}
	public void declineBtn(ActionEvent e) {
		String attribute = cmbSort.getValue();
		String txt = searchTf.getText();
		List<HolidayRequest> requestList = dbServ.SelectHolidayApprovalSearch(attribute, txt);
		
		int row = HoliAppTableView.getSelectionModel().getSelectedIndex()+1;
		String num = requestList.get(row-1).getId();
		String periodDay = requestList.get(row-1).getPeriodDay();
		System.out.println(row+" "+num+" "+periodDay);
		if("미승인".contentEquals(requestList.get(row-1).getApproval())) {			
			dbServ.updateDeclineHoliday("WHERE ROWID="+row);
			comServ.ErrorMsg("반려되었습니다.");
		}
		else if("승인".contentEquals(requestList.get(row-1).getApproval())){
			dbServ.updateDeclineHoliday("WHERE ROWID="+row);
			dbServ.updateEmployeeHolidayDe("WHERE "+"\"사원번호\""+" = "+num, periodDay);
			dbServ.updateEmployeeHolidayDe2("WHERE "+"\"사원번호\""+" = "+num, periodDay);
			comServ.ErrorMsg("승인이 반려되었습니다.");
		}
		else { //반려
			comServ.ErrorMsg("이미 반려되었습니다.");
		}
	}
}
