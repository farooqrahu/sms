package com.sms.spring.instrumentation;

import com.sms.spring.model.User;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

;

public class Utility {
    private static final Logger logger = Logger.getLogger(Utility.class);
    public static final int PASSWORD_LENGTH = 8;
    private static final Random RANDOM = new SecureRandom();

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
                logger.info("convertStringToDate:Date is empty or null.");
            }

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            logger.info("Exception:convertStringToDate:Date is empty or null.");
            e.printStackTrace();
        }
        return date;
    }


    public static Date toDateConvertor(String date) {
        Date dtReturn = null;
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        try {
            if
                    (date != null && !date.toString().isEmpty()) {
                logger.info(
                        "Date before formate:" + date);
                dtReturn = formatter.parse(date);
                logger.info("Date formated:" + dtReturn);
            } else {
                logger.info(
                        "toDateConvertor:Date is empty or null.");
            }
        } catch (ParseException e) { // TODO Auto-generated catch block logger.info(

            e.printStackTrace();
        }
        return dtReturn;
    }


    public static String dateFormater_ddMMyyyy(Date date) {
        String reportDate = "";
        if (date != null && !date.toString().isEmpty()) {
            logger.info("Date before formate:" + date);
            DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
            reportDate = df.format(date);
            logger.info("Date formated:" + reportDate);
            return reportDate;
        } else {
            logger.info("dateFormater_ddMMyyyy:Date is empty or null.");
        }
        return reportDate;
    }


    public static String fomateStringDate_ddMMyyyy(String oldDateString) {
        String newDateString = "";
        final String OLD_FORMAT = "yyy-MM-ddd";
        final String NEW_FORMAT = "dd.MM.yyyy";

        try { // August 12, 2010 // String oldDateString = "2016-01-26 10:12:25";
            SimpleDateFormat sdf = new SimpleDateFormat(OLD_FORMAT);
            Date d =
                    sdf.parse(oldDateString);
            sdf.applyPattern(NEW_FORMAT);
            System.out.println(newDateString = sdf.format(d));

        } catch (Exception e) { // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return newDateString;
    }

    public static Date fomateDate_ddMMyyyy(String oldDateString) {
        String
                newDateString = "";
        final String OLD_FORMAT = "yyy-MM-ddd";
        final String
                NEW_FORMAT = "dd.MM.yyyy";
        Date d = null;
        try { // August 12, 2010 //
            oldDateString = "2016-01-26 10:12:25";
            SimpleDateFormat sdf = new
                    SimpleDateFormat(NEW_FORMAT);
            d = sdf.parse(oldDateString);
            sdf.applyPattern(NEW_FORMAT);
            System.out.println(newDateString =
                    sdf.format(d));

        } catch (Exception e) { // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return d;
    }

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