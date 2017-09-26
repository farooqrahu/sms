package com.sms.spring.instrumentation;

import java.util.Locale;

import com.sms.spring.ApplicationContextProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.context.i18n.LocaleContextHolder;

/*
 * 
 * @author Farooq.Rahu
 * */

public class GlobalConstants {

	static ApplicationContext appContext = new ApplicationContextProvider().getApplicationContext();

	static Locale locale = LocaleContextHolder.getLocale();

	public static String getMessage(String msg) {
		return appContext.getMessage(msg, null, locale);
	}

	public static String getParametarizedMessage(String msg, Object object[]) {
		return appContext.getMessage(msg, object, locale);
	}

	public final static String USER_LIST = "usersList";
	/***USER-ALL-ROLES ****/
	public final static String SMS_ROLE_SALEREP = "ROLE_SALE_REP";
	public final static String ROLE_WAREHOUSE_MANAGER = "ROLE_WAREHOUSE_MANAGER";
	public final static String ROLE_DATA_ENTRY = "ROLE_DATA_ENTRY";

	public static final String SMS_USER_DELETE_SUCCESS = "SMS.USER.DELETE.SUCCESS";
	public static final String SMS_USER_DELETE_FAIL = "SMS.USER.DELETE.FAIL";
	public static final String SMS_USER_DELETE_FAIL_MSG = "SMS.USER.DELETE.FAIL.MSG";

	public static final String SMS_USER_CREATED_SUCCESS = "SMS.USER.CREATED.SUCCESS";
	public static final String SMS_USER_CREATED_FAIL = "SMS.USER.CREATED.FAIL";
	public static final String SMS_USER_UPDATED_SUCCESS = "SMS.USER.UPDATED.SUCCESS";
	public static final String SMS_USER_UPDATED_FAIL = "SMS.USER.CREATED.FAIL";
	public static final String SMS_USER_CREATE_UPDATE_FAIL = "SMS.USER.CREATE.UPDATE.FAIL";

	public static final String SAVE = "true";
	public static final String UPDATE = "true";
	public static final String SUCCESS = "s_msg";
	public static final String FAIL = "f_msg";

	/***HTML Pages **/
	public final static String MANAGEUSER_HTML_PAGE = "manageuser";
	public static final String USER_MODALS_HTML_PAGE = "usermodals";
	public static final String CHANGE_USER_PASSWORD_HTML_PAGE = "settings";

	public static final String DUPLICATE_EMPLOYEE_ID = "DUPLICATE.EMPLOYEE.ID";
	public static final String DUPLICATE_USER_NAME = "DUPLICATE.USER.NAME";
	public static final String SMS_USER_PASSWORD_MATCH_FAIL = "SMS.USER.PASSWORD.MATCH.FAIL";
	public static final String SMS_USER_PASSWORD_CHANGE_SUCCESS = "SMS.USER.PASSWORD.CHANGE.SUCCESS";
	public static final String SMS_USER_PASSWORD_CHANGE_FAIL = "SMS.USER.PASSWORD.CHANGE.FAIL";
	public static final String SMS_USER_PASSWORD_RESET_SUCCESS = "SMS.USER.PASSWORD.RESET.SUCCESS";
	public static final String SMS_USER_PASSWORD_RESET_FAIL = "SMS.USER.PASSWORD.RESET.FAIL";
	public static final String INDEX_HTML_PAGE = "index";

	public static final String ORDER_ALREADY_RETURNED = "ORDER.ALREADY.RETURNED";
	public static final String ORDER_RETURNED = "ORDER.RETURNED";
	public static final String GENERATE_INVOICE = "GENERATE.INVOICE";
	public static final String INVOICE_GENERATE_SUCCESS = "INVOICE.GENERATE.SUCCESS";
	public static final String INVOICE_GENERATE_FAIL = "INVOICE.GENERATE.FAIL";
	public static final String TEX_SAVE_SUCCESS = "TEX.SAVE.SUCCESS";
	public static final String TEX_SAVE_FAILED ="TEX.SAVE.FAILED";
	
	public static final String SMS_CUSTOMER_APPROVE_SUCCESS = "SMS.CUSTOMER.APPROVE.SUCCESS";
	public static final String SMS_CUSTOMER_APPROVE_FAIL = "SMS.CUSTOMER.APPROVE.FAIL";
	public static final String SMS_CUSTOMER_SAVE_SUCCESS = "SMS.CUSTOMER.SAVE.SUCCESS";
	public static final String SMS_CUSTOMER_CODE_DUPLICATE = "SMS.CUSTOMER.CODE.DUPLICATE";
	public static final String SMS_CUSTOMER_CNIC_DUPLICATE = "SMS.CUSTOMER.CNIC.DUPLICATE";
	public static final String SMS_CUSTOMER_NTN_DUPLICATE = "SMS.CUSTOMER.NTN.DUPLICATE";
	public static final String SMS_CUSTOMER_UPDATE_SUCCESS = "SMS.CUSTOMER.UPDATE.SUCCESS";
	public static final String SMS_CUSTOMER_SAVE_FAIL = "SMS.CUSTOMER.SAVE.FAIL";
	public static final String SMS_ORDER_DELIVERED_SUCCESS = "SMS.ORDER.DELIVERED.SUCCESS";
	public static final String SMS_PRODUCT_TOTAL_PRICE_MARKET_PRICE = "SMS.PRODUCT.TOTAL.PRICE.MARKET.PRICE";
	public static final String SMS_PRODUCT_UPDATE_SUCCESS = "SMS.PRODUCT.UPDATE.SUCCESS";
	public static final String SMS_PRODUCT_SAVE_SUCCESS = "SMS.PRODUCT.SAVE.SUCCESS";
	public static final String SMS_PRODUCT_UPDATE_FAIL = "SMS.PRODUCT.UPDATE.FAIL";
	public static final String SMS_PRODUCT_SAVE_FAIL = "SMS.PRODUCT.SAVE.FAIL";
	public static final String SMS_PRODUCT_DELETE_SUCCESS = "SMS.PRODUCT.DELETE.SUCCESS";
	public static final String SMS_PRODUCT_DELETE_FAIL = "SMS.PRODUCT.DELETE.FAIL";
	public static final String SMS_PRODUCT_NOT_FOUND_DELETE_FAIL = "SMS.PRODUCT.NOT.FOUND.DELETE.FAIL";
	public static final String SMS_PRODUCT_SIZE_NOT_AVAILABLE = "SMS.PRODUCT.SIZE.NOT.AVAILABLE";
	public static final String SMS_PRODUCT_SIZE_AVAILABLE_QUANTITY = "SMS.PRODUCT.SIZE.AVAILABLE.QUANTITY";
	public static final String SMS_PRODUCT_SIZE_OUT_OF_STOCK = "SMS.PRODUCT.SIZE.OUT.OF.STOCK";

	public static ApplicationContext getAppContext() {
		return appContext;
	}

	public static void setAppContext(ApplicationContext appContext) {
		GlobalConstants.appContext = appContext;
	};

}
