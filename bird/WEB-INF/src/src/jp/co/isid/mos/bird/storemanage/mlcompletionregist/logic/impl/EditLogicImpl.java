package jp.co.isid.mos.bird.storemanage.mlcompletionregist.logic.impl;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.dao.CodCourseDao;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.dao.UIMLStaffDao;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.dao.UIRenewalStaffDao;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.dto.MlCompletionregistDto;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.entity.CodCourse;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.entity.UIMLStaff;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.entity.UIRenewalStaff;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.logic.MlCompletionregistLogic;

/**
 * マスタライセンス研修修了登録
 * 受験スタッフ検索ロジック
 * 
 * @author xkinu
 */
public class EditLogicImpl implements MlCompletionregistLogic {
    /* ロジックID */    
    public static final String LOGIC_ID = "BSM006L04";
    
    /*【DAO】*/
    private UIMLStaffDao uiMLStaffDao;
    private UIRenewalStaffDao uiRenewalStaffDao;
    private CodCourseDao codCourseDao;
    /**
     * 事前条件処理
     */
    public void validate(MlCompletionregistDto dto) {
        // 画面DTO
        if (dto == null) {
            throw new NotNullException("マスタライセンス研修修了登録 画面DTO");
        }
        if (isNull(dto.getEntryCd())) {
            throw new NotNullException("エントリーコード");
        }

        if (isNull(dto.getEntryYear())) {
            throw new NotNullException("エントリー年");
        }

        if (isNull(dto.getEntryKai())) {
            throw new NotNullException("エントリー回");
        }
        if (isNull(dto.getCompanyCd())) {
            throw new NotNullException("会社コード");
        }
        if (isNull(dto.getSibuCd())) {
            throw new NotNullException("支部コード");
        }
        //システム日付
        String toDay = dto.getToday();
        if (isNull(toDay)) {
            throw new NotNullException("システム日付");
        }
        //ユーザータイプコード
        String usertypeCd = dto.getUsertypeCd();
        if (isNull(usertypeCd)) {
            throw new NotNullException("ユーザータイプコード");
        }
    }
    /**
     * 条件画面出力データ検索を行う
     * 
     * @param MlCompletionregistDto dto 画面データ保持クラス
     * @exception ApplicationException
     */
    public Map execute(MlCompletionregistDto dto) throws ApplicationException {
        
    	// コース情報取得
    	CodCourse codCourse = executeGetCourseData(dto);
    	
        //返却用マップ
    	Map returnMap = new HashMap();
        
        //--------------------------
        // 「更新研修」の時
        //--------------------------
    	if(dto.isRenewalEntry()) {
	        // コースデータ取得&設定
            returnMap.put("listCourse",	executeListCourse(dto));
            // 編集用データ取得&設定
            List listStaff = executeListStaffRenewal(dto);
            if (codCourse != null) {
                for (Iterator i = listStaff.iterator(); i.hasNext();) {
                	UIRenewalStaff entity = (UIRenewalStaff) i.next();
                	entity.setCompleCourseCd(codCourse.getCourseCd());
                	entity.setCompleCourseName(codCourse.getCourseName());
                }
            }
            returnMap.put("listStaff", listStaff);
//            returnMap.put("listStaff", executeListStaffRenewal(dto));
	    }
        //--------------------------
        // 「更新研修」以外の時
        //--------------------------
        else{

            //対象スタッフ情報取得(編集用データ取得&設定)
            List listStaff = executeListStaff(dto);
            if (codCourse != null) {
				for (Iterator i = listStaff.iterator(); i.hasNext();) {
					UIMLStaff entity = (UIMLStaff) i.next();
					entity.setCompleCourseCd(codCourse.getCourseCd());
					entity.setCompleCourseName(codCourse.getCourseName());
				}
			}
            returnMap.put("listStaff", listStaff);
//            returnMap.put("listStaff", executeListStaff(dto));
        }
        
        //検索データを戻す。
        return returnMap;
    }
    /**
     * コースデータ取得処理
     * 
     * @param MlCompletionregistDto dto 画面データ保持クラス
     * @return　List コースデータ情報検索結果
     * @throws ApplicationException
     */
    private List executeListCourse(MlCompletionregistDto dto) 
    throws ApplicationException {
    	String entryCd = dto.getEntryCd();
    	String entryYear = dto.getEntryYear();
        String entryKai = dto.getEntryKai();
        String sysDate = dto.getToday();
        String usertypeCd = dto.getUsertypeCd();
        List listCourse = getCodCourseDao().select(entryCd, entryYear, entryKai, sysDate, usertypeCd);
        if (listCourse == null || listCourse.size() == 0) {
            throw new NotExistException("更新研修対象のコース情報");
        }
        return listCourse;
    }

    /**
     * コースデータ取得処理
     * 
     * @param MlCompletionregistDto dto 画面データ保持クラス
     * @return CodCourse コースデータ情報検索結果
     * @throws ApplicationException
     */
    private CodCourse executeGetCourseData(MlCompletionregistDto dto) 
    throws ApplicationException {
    	String entryCd = dto.getEntryCd();
    	String entryYear = dto.getEntryYear();
        String entryKai = dto.getEntryKai();
        //String sysDate = dto.getToday();
        //String usertypeCd = dto.getUsertypeCd();
        CodCourse codCourse = getCodCourseDao().selectCourse(entryCd, entryYear, entryKai);
        if (codCourse == null) {
//            throw new NotExistException("コース情報");
        }
        return codCourse;
    }

    /**
     * 編集「更新研修」対象スタッフ情報取得処理
     * 
     * @param MlCompletionregistDto dto 画面データ保持クラス
     * @return
     * @throws ApplicationException
     */
    private List executeListStaffRenewal(MlCompletionregistDto dto) throws ApplicationException {

        //DTOより情報の取得
        String entryCd        = dto.getEntryCd();
    	String entryYear      = dto.getEntryYear();
    	String entryKai       = dto.getEntryKai();
    	String companyCd      = dto.getCompanyCd();
    	String sibuCd         = dto.getSibuCd();
    	boolean entryOnlyflg = dto.getOptionFlg();
        List listStaff = null;
            
    	if(entryOnlyflg) {
    	    // エントリー者のみ
            listStaff = getUIRenewalStaffDao().selectEntryStaff(
                    entryCd, entryYear, entryKai, companyCd, sibuCd);
    	} else {           
            listStaff = getUIRenewalStaffDao().selectStaff(
                    entryCd, entryYear, entryKai, companyCd, sibuCd);
    	}

        if (listStaff == null || listStaff.size() == 0) {
            throw new NotExistException("受験スタッフ情報");
        }
            
        return listStaff;
    	
    }
    /**
     * 編集「マスターライセンス」対象スタッフ情報取得処理
     * 
     * @param MlCompletionregistDto dto 画面データ保持クラス
     * @return
     * @throws ApplicationException
     */
    private List executeListStaff(MlCompletionregistDto dto) throws ApplicationException {

        //DTOより情報の取得
        String entryCd        = dto.getEntryCd();
        String entryYear      = dto.getEntryYear();
        String entryKai       = dto.getEntryKai();
        String companyCd      = dto.getCompanyCd();
        String sibuCd         = dto.getSibuCd();
        boolean entryOnlyflg = dto.getOptionFlg();
        List listStaff = null;
            
        if(entryOnlyflg) {
            // エントリー者のみ
            listStaff = getUIMLStaffDao().selectEntryStaff(
                    entryCd, entryYear, entryKai, companyCd, sibuCd);
        } else {
            listStaff = getUIMLStaffDao().selectStaff(
                    entryCd, entryYear, entryKai, companyCd, sibuCd);
        }

        //0件の時
        if (listStaff == null || listStaff.size() == 0) {
            throw new NotExistException("スタッフ情報");
        }
            
        return listStaff;
        
    }    
    /**
     * マスターライセンス受験者スタッフ情報Dao取得処理
     * 
     * @return uiMLStaffDao を戻します。
     */
    public UIMLStaffDao getUIMLStaffDao() {
        return uiMLStaffDao;
    }
    /**
     * マスターライセンス受験者スタッフ情報Dao設定処理
     * 
     * @param uiMLStaffDao を設定。
     */
    public void setUIMLStaffDao(UIMLStaffDao dao) {
        this.uiMLStaffDao = dao;
    }

    /**
     * 更新研修受験者スタッフ情報Dao取得処理
     * 
     * @return uiRenewalStaffDao を戻します。
     */
    public UIRenewalStaffDao getUIRenewalStaffDao() {
        return uiRenewalStaffDao;
    }
    /**
     * 更新研修受験者スタッフ情報Dao設定処理
     * 
     * @param uiRenewalStaffDao を設定。
     */
    public void setUIRenewalStaffDao(UIRenewalStaffDao dao) {
        this.uiRenewalStaffDao = dao;
    }

    /**
     * @return codCourseDao を戻します。
     */
    public CodCourseDao getCodCourseDao() {
        return codCourseDao;
    }
    /**
     * @param codCourseDao codCourseDao を設定。
     */
    public void setCodCourseDao(CodCourseDao codCourseDao) {
        this.codCourseDao = codCourseDao;
    }
    /**
     * Null判断処理
     * @param str
     * @return boolean true：空かNullの場合
     */
    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }

}
