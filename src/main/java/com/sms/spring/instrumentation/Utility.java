package com.sms.spring.instrumentation;

import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.sms.spring.model.User;

/*package com.binaryvibes.ifg.Instrumentation;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import com.binaryvibes.ifg.entities.Make;
import com.binaryvibes.ifg.entities.Store;
import com.binaryvibes.ifg.entities.StoreItem;
import com.binaryvibes.ifg.entities.StoreUser;
import com.binaryvibes.ifg.entities.User;
import com.binaryvibes.ifg.entities.UserDetail;
import com.binaryvibes.ifg.entities.WeaponType;
import com.binaryvibes.ifg.services.SetupService;
import com.binaryvibes.ifg.services.StoreService;
import com.binaryvibes.ifg.services.UserService;

public class Utility {
	private static final Logger logger = Logger.getLogger(Utility.class);

public static User getLoggedInUser(){
Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			return = (User) authentication.getPrincipal();;
}

	public static Date convertStringToDate(String str_date) {

		DateFormat formatter;
		Date date = null;
		formatter = new SimpleDateFormat("dd.mm.yyyy");
		try {
			if (str_date != null && !str_date.toString().isEmpty()) {
				logger.info("Date before formate:" + str_date);
				date = formatter.parse(str_date);
				logger.info("Date formated:" + date);
			} else {
				logger.info("BinaryUtils.convertStringToDate:Date is empty or null.");
			}

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			logger.info("Exception:BinaryUtils.convertStringToDate:Date is empty or null.");
			e.printStackTrace();
		}
		return date;
	}

	
	 * public static Date toDateConvertor(String date){ Date dtReturn = null;
	 * SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy"); try { if
	 * (date != null && !date.toString().isEmpty()) { logger.info(
	 * "Date before formate:" + date); dtReturn = formatter.parse(date);
	 * logger.info("Date formated:" + dtReturn); } else { logger.info(
	 * "BinaryUtils.toDateConvertor:Date is empty or null."); } } catch
	 * (ParseException e) { // TODO Auto-generated catch block logger.info(
	 * "Exception:BinaryUtils.toDateConvertor:Date is empty or null.");
	 * e.printStackTrace(); } return dtReturn; }
	 

	public static String dateFormater_ddMMyyyy(Date date) {
		String reportDate = "";
		if (date != null && !date.toString().isEmpty()) {
			logger.info("Date before formate:" + date);
			DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
			reportDate = df.format(date);
			logger.info("Date formated:" + reportDate);
			return reportDate;
		} else {
			logger.info("BinaryUtils.dateFormater_ddMMyyyy:Date is empty or null.");
		}
		return reportDate;
	}

	
	 * public static String fomateStringDate_ddMMyyyy(String oldDateString) {
	 * String newDateString = ""; final String OLD_FORMAT = "yyy-MM-ddd"; final
	 * String NEW_FORMAT = "dd.MM.yyyy";
	 * 
	 * try { // August 12, 2010 // String oldDateString = "2016-01-26 10:12:25";
	 * SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT); Date d =
	 * sdf.parse(oldDateString); sdf.applyPattern(NEW_FORMAT);
	 * System.out.println(newDateString = sdf.format(d));
	 * 
	 * } catch (Exception e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } return newDateString; }
	 * 
	 * public static Date fomateDate_ddMMyyyy(String oldDateString) { String
	 * newDateString = ""; final String OLD_FORMAT = "yyy-MM-ddd"; final String
	 * NEW_FORMAT = "dd.MM.yyyy"; Date d = null; try { // August 12, 2010 //
	 * String oldDateString = "2016-01-26 10:12:25"; SimpleDateFormat sdf = new
	 * SimpleDateFormat(NEW_FORMAT); d = sdf.parse(oldDateString);
	 * sdf.applyPattern(NEW_FORMAT); System.out.println(newDateString =
	 * sdf.format(d));
	 * 
	 * } catch (Exception e) { // TODO Auto-generated catch block
	 * e.printStackTrace(); } return d; }
	 
	public static long isLoggedInUserStoreAdmin(StoreService storeService) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String loggedInUser_name = auth.getName();
		StoreUser isStoreUser = storeService.findStoreUserByUserName(loggedInUser_name);

		long id = 0l;
		if (isStoreUser != null && isStoreUser.getIsStoreAdmin()) {
			// Only store Admin can view the list.
			logger.info("UserName : " + isStoreUser.getUser().getUserName());
			logger.info("StoreId : " + isStoreUser.getStore().getStoreId());
			logger.info("Title : " + isStoreUser.getStore().getTitle());
			logger.info("isAdmin : " + isStoreUser.getIsStoreAdmin());
			id = isStoreUser.getStore().getStoreId();
		} else {
			id = 0l;
		}

		return id;
	}

	public static long findLoggedInUserRegionId(UserService userService) {
		
		 * Authentication auth =
		 * SecurityContextHolder.getContext().getAuthentication(); String
		 * loggedInUser_name = auth.getName();
		 

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		long id = 0l;
		boolean sysUser = false;
		for (GrantedAuthority a : authorities) {
			logger.info("User logged in hase Role: " + a.getAuthority().toString());
			if (a.getAuthority().toString().equals("ROLE_DATA_ENTRY")) {
				logger.info("System User set true.");
				sysUser = true;
			}
		}

		int userId = user.getUserId();
		logger.info("LoggedIn User Id:" + userId);
		if (sysUser) {
			UserDetail isUserFound = userService.findUserDetailByID(userId);
			logger.info("System User set true. set region ID for system user.");

			if (isUserFound != null) {
				if (isUserFound.getCity() != null)
					if (isUserFound.getCity().getRegion() != null)
						logger.info("Region Id:" + isUserFound.getCity().getRegion().getRegionId());
				id = isUserFound.getCity().getRegion().getRegionId();
			} else {
				id = 0l;
			}
		}

		return id;
	}

	public static boolean isUserNameExist(UserService userService, String userName) {

		User userNameFound = userService.findUserByUserName(userName);
		// Validate Unique Store Name...
		if (userNameFound != null) {
			if (userNameFound.getUserName() != null) {
				if (userNameFound.getUserName().equalsIgnoreCase(userName)) {
					return true;
				}
				return false;
			}
			return false;
		}
		return false;
	}// isUserNameExist

	public static boolean isStoreUserNameExist(StoreService storeService, String userName) {

		StoreUser storeUserNameFound = storeService.findStoreUserByUserName(userName);
		// Validate Unique Store Name...
		if (storeUserNameFound != null) {
			if (storeUserNameFound.getUser() != null) {
				if (storeUserNameFound.getUser().getUserName() != null) {
					if (storeUserNameFound.getUser().getUserName().equalsIgnoreCase(userName)) {
						return true;
					}
					return false;
				}
				return false;
			}
			return false;
		}
		return false;

	}// isStoreUserNameExist

	public static boolean isStoreNameExist(StoreService storeService, String userName) {

		Store storeNameFound = storeService.findStoreByStoreTitle(userName);
		// Validate Unique Store Name...
		if (storeNameFound != null) {
			if (storeNameFound.getTitle() != null) {
				if (storeNameFound.getTitle().equalsIgnoreCase(userName)) {
					// will enter duplicate name
					return false;
				}
				return false;
			}
			return false;
		}
		return false;

	}// isStoreUserNameExist

	public static boolean isMakeNameExist(SetupService setupService, String name) {

		Make makeNameFound = setupService.findMakeByName(name);
		// Validate Unique Make Name...
		if (makeNameFound != null) {
			if (makeNameFound.getName() != null) {
				if (makeNameFound.getName().equalsIgnoreCase(name)) {
					return true;
				}
				return false;
			}
			return false;
		}
		return false;

	}// isMakeNameExist

	public static boolean isWeaponTypeExist(SetupService setupService, String name) {

		WeaponType weaponTypeFound = setupService.findWeaponTypeByName(name);
		// Validate Unique WaeponType...
		if (weaponTypeFound != null) {
			if (weaponTypeFound.getName() != null) {
				if (weaponTypeFound.getName().equalsIgnoreCase(name)) {
					return true;
				}
				return false;
			}
			return false;
		}
		return false;

	}// isWeapontypeExist

	public static boolean isWeaponNoExist(StoreService storeService, String weaponNo) {

		StoreItem weaponNoFound = storeService.findStoreItemWeaponNo(weaponNo);
		// Validate Unique weaponNoFound...
		if (weaponNoFound != null) {
			if (weaponNoFound.getWeaponNo() != null) {
				if (weaponNoFound.getWeaponNo().equalsIgnoreCase(weaponNo)) {
					return true;
				}
				return false;
			}
			return false;
		}
		return false;

	}// isWeapontypeExist

}
*/

public class Utility {
	private static final Logger logger = Logger.getLogger(Utility.class);
	 private static final Random RANDOM = new SecureRandom();
	  /** Length of password. @see #generateRandomPassword() */
	  public static final int PASSWORD_LENGTH = 8;
	  /**
	   * Generate a random String suitable for use as a temporary password.
	   *
	   * @return String suitable for use as a temporary password
	   * @since 2.4
	   */

	public static User getLoggedInUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) authentication.getPrincipal();
		if (user != null) {
			logger.info("User Name LoggednIn:" + user.getUsername());
		} else {
			logger.info("loggedIn UserName Not Found.");
		}
		return user;
	}
	
	
	
	  public static String generateRandomPassword()
	  {
	      // Pick from some letters that won't be easily mistaken for each
	      // other. So, for example, omit o O and 0, 1 l and L.
	      String letters = "abcdefghjkmnpqrstuvwxyzABCDEFGHJKMNPQRSTUVWXYZ23456789+@";

	      String pw = "";
	      for (int i=0; i<PASSWORD_LENGTH; i++)
	      {
	          int index = (int)(RANDOM.nextDouble()*letters.length());
	          pw += letters.substring(index, index+1);
	      }
	      return pw;
	  }
	
	  public static Long getCurrentLongDate() {
		 // String creationDate = new Date().toString();
			//SimpleDateFormat f = new SimpleDateFormat("dd-MMM-yyyy");
			Date d = new Date();
			/*try {
				//d = f.parse(creationDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				logger.info("Date Exception:",e);
			}*/
			Long milliseconds = d.getTime();
			return milliseconds;	
		  
	  }
	
	  public static String getFormatedStringDateFromLong(Long date){
			String format3 = new SimpleDateFormat("MM/dd/yyyy'T'HH:mm:ss.SSS", Locale.ENGLISH).format(date);
			return format3.substring(0, 10);
	  }
	  
}
