import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;


class OoooSmartClock {
	private TimeZone timeZone;
	private IOoooSetTimeZoneDialog  stubDialog;
	boolean dataExist=true;
	private DateFactory datefactory;
	
	
	OoooSmartClock( ){
		this.stubDialog=new OoooSetTimeZoneDialog();
		datefactory=new DateFactory();
		this.timeZone=TimeZone.getDefault();
	}
	OoooSmartClock( IOoooSetTimeZoneDialog stub,DateFactory datefactory){
		this.stubDialog=stub;
		this.datefactory=datefactory;
		//default time zone
		this.timeZone=TimeZone.getDefault();
		
	}
	public void setStub(IOoooSetTimeZoneDialog  stubDialog,DateFactory factory){
		this.stubDialog=stubDialog;
		this.datefactory=factory;
		
		
	}
	public void setTimeZone()  {
	
		
		try {
			int zoneindex=stubDialog.getindex();
		//	int zoneindex = new OoooSetTimeZoneDialog();
			this.timeZone=TimeZone.getTimeZone("GMT+"+zoneindex);
		} catch (NODATA e) {
			// TODO Auto-generated catch block
			if (e.getMessage().equals("NODATA"))
					dataExist=false;
		}
		
	}

	public void setTimeZone(int index) {
		// nothing to explain, a simple setter
		this.timeZone = TimeZone.getTimeZone("GMT+"+index);
	}
	public String getCurrentTimeStamp() {
		// YYYY-MM-DD HH:MI:Sec 形式的 format
		// 例如 ”2009-09-22 16:47:08”
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// 當這個 method 被呼叫的時候請回傳
		sdf.setTimeZone(timeZone);
		Date date_now = datefactory.create();
		return sdf.format(date_now);
	}

	public String getLocalCurrentTimeStamp() {
		Date date=datefactory.create();
		 //date = "yyyy-MM-dd";
		SimpleDateFormat sdfYMD=new SimpleDateFormat("yyyy-MM-dd");
		sdfYMD.setTimeZone(timeZone);
		String date_ans=sdfYMD.format(date);
		//  add day to date
		
		
		SimpleDateFormat sdf=new SimpleDateFormat("MM-dd");
		
		//set timeZone
		sdf.setTimeZone(timeZone);
		
		if( sdf.format(date).equals("10-10"))
			date_ans=date_ans+" "+"DOUBLE-TEN";
		else if (sdf.format(date).equals("08-08"))
			date_ans=date_ans+" "+"FATHER'S DAY";
		else if (sdf.format(date).equals("12-25"))
			date_ans=date_ans+" "+"X'MAS";
		
		//adding hour:minute:seonds
		SimpleDateFormat sdfHMS=new SimpleDateFormat("HH:mm:ss");
		//set timeZone
		sdfHMS.setTimeZone(timeZone);
		String time=sdfHMS.format(date);
		
		if (time.matches("12:00:[0-5]{1}[0-9]{1}") || time.equals("12:01:00"))
			date_ans=date_ans+" "+"NOON";
		else if (time.matches("00:00:[0-5]{1}[0-9]{1}") || time.equals("00:01:00"))
			date_ans=date_ans+" "+"MIDNIGHT";
		else 
			date_ans=date_ans+" "+time;
	
		return date_ans;
	}
}




