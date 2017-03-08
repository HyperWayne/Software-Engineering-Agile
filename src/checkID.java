/**  
 * method  checkID
 * 用來確認欲查詢的ID是否存在於GradeSystems系統內
 *
 * @param ID     用來查詢的ID
 * @return 一個布林值，若為true則代表GradeSystems有這筆資料，若false則否
 *
 * @throws NoSuchIDExceptions – 
 *			若使用者輸入的ID不在GradeSystems內則拋出NoSuchIDExceptions
 * Pseudo code:
 * 1.要aGradeSystem 做containsID(ID) 看 ID 是否含在 aGradeSystem內 
 * 2.if not, throw an object of NoSuchIDExceptions
 * 3.回傳 true
 *
 * Time estimate : O (n)
 * Example: UI物件.checkID(962001044) ; 傳回結果為 true
 */

public boolean checkID(int ID) throws NoSuchIDExceptions{
		boolean flag=false;
		for (Iterator<Grades> i = gs.gradeList.iterator(); i.hasNext();) {
			current=((Grades)i.next());
		      if(current.ID==ID){
		    	  this.currentID=ID;
		    	  flag=true;
		    	  break;
		      }
		}
		if(flag==false)throw new NoSuchIDExceptions(ID);
		return flag;
	}
