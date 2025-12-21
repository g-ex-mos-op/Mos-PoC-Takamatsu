/*
 * 作成日: 2006/04/10
 */
package jp.co.isid.mos.bird.storemanage.mlcompletionregist.action.impl;

import java.util.Iterator;

import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.action.MlCompletionregistEditAction;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.dto.MlCompletionregistDto;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.entity.UIMLStaff;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.entity.UIRenewalStaff;
import jp.co.isid.mos.bird.storemanage.mlcompletionregist.logic.impl.CheckContentsLogicImpl;

/**
 * マスタライセンス研修修了登録
 * 編集画面アクション
 * 
 * @author xkinu
 */
public class MlCompletionregistEditActionImpl 
    implements MlCompletionregistEditAction {
   
    /*【ロジック】*/
    private CheckContentsLogicImpl checkContentsLogic;
    /*【DTO】*/
    private MlCompletionregistDto mlCompletionregistDto;
    
    /**
     * 初期処理
     * @return
     */
    public String initialize() {
        // 初期値 [-1]の遷移区分を立てる
        getMlCompletionregistDto().setScenechangedKbn(
        		MlCompletionregistDto.SCENECHANGE_KBN_INIT);
        return null;
    }

    /**
     * 実行処理
     * @return  
     */
    public String execute() {
        // ロジック【登録内容のチェック】を実行する。
        getCheckContentsLogic().execute(getMlCompletionregistDto());
        // 編集画面-->確認画面の遷移区分を立てる
        getMlCompletionregistDto().setScenechangedKbn(
        		MlCompletionregistDto.SCENECHANGE_KBN_EDIT_TO_CONF);
        return VIEWID_CONFIRM;
    }
    
    /**
     * 戻る
     * @return 
     */
    public String back() {
        // 編集画面-->条件画面の遷移区分を立てる
        getMlCompletionregistDto().setScenechangedKbn(
        		MlCompletionregistDto.SCENECHANGE_KBN_EDIT_TO_COND);
        return VIEWID_CONDITION;
    }

    /**
     * 一括設定
     * @return 
     */
    public String paramSetUp() {
    	int regListCnt = mlCompletionregistDto.getRegistDataListSize();
    	String setUpCourseCdIndex = "0";
    	if(mlCompletionregistDto.getCourseList() != null) {
    		//出張研修の場合のみコースコード取得
    		setUpCourseCdIndex = mlCompletionregistDto.getCourseListIndex();
    	}
    	//設定状況取得
    	String setUpStatusIndex = "0";
    	setUpStatusIndex = mlCompletionregistDto.getParamCourseStatus();
    	
    	//設定年月取得
    	String setUpYm = mlCompletionregistDto.getSetUpYm();
        DateVerifier dateVerifier = new DateVerifier(DateVerifier.DATE_TYPE_YM);
    	if (setUpYm == null || setUpYm.trim().equals("")
				|| formatDate(setUpYm.trim()) == ""
				|| !dateVerifier.validate(setUpYm.trim())) {
			InvalidInputException invEx = new InvalidInputException("一括設定 年月");
			invEx.setHtmlElementName("setUpYm");
			throw invEx;
		}

        for (Iterator i = mlCompletionregistDto.getRegistDataList().iterator(); i.hasNext();) {
        	//ベーシック研修の場合
        	if (mlCompletionregistDto.isBasicEntry() && setUpStatusIndex != null) {
            	UIMLStaff entity = (UIMLStaff) i.next();
    			//状況一括設定
        		entity.setCourseStatus(setUpStatusIndex);
    			//年月一括設定
            	entity.setCompleCourseDt(formatDate(setUpYm.trim()));
        	}
        	
        	//出張研修の場合
        	if (mlCompletionregistDto.isTripEntry()) {
            	UIMLStaff entity = (UIMLStaff) i.next();
    			//年月一括設定
            	entity.setCompleCourseDt(formatDate(setUpYm.trim()));
        	}

        	//更新研修の場合
        	if (mlCompletionregistDto.isRenewalEntry()) {
            	UIRenewalStaff entity = (UIRenewalStaff) i.next();
    			//コース一括設定
//        		entity.setCompleCourseCd(setUpCourseCdIndex);
//                for (Iterator j = mlCompletionregistDto.getCourseList()
//						.iterator(); j.hasNext();) {
//                	CodCourse codCourse = (CodCourse) j.next();
//                	if (codCourse.getCourseCd().equals(setUpCourseCdIndex)) {
//                		entity.setCompleCourseName(codCourse.getCourseName());
//                		break;
//                	}
//				}
				//年月一括設定
            	entity.setCompleCourseDt(formatDate(setUpYm.trim()));
        	}
        }
		return null;
    }

    /**
     * 日付フォーマット(YYYY/MM）処理
     * 許可するフォーマットは、以下の10種類
     * YYYYMM , YYYY/MM
     * @param date
     * @return
     */
    private String formatDate(String date) {
		String fDate = "";

		//「/」なし入力
		if (date.indexOf("/") == -1) {
			//「YYYYMM」以外はエラー
			if (!(date.length() == 6)) {
				return "";
			}

			//YYMMDD入力
			else if (date.length() == 6) {
				fDate = date.substring(0, 4) + date.substring(4, 6);
//				fDate = date.substring(0, 4) + "/" + date.substring(4, 6);
			}
		}
		//「/」あり入力
		else {
			String[] tmpDate = date.split("/");
			if (tmpDate.length != 2) {
				return "";
			}

			//年
			if (!(tmpDate[0].length() == 4)) {
				return "";
			}
			//「YYYY」入力
			if (tmpDate[0].length() == 4) {
				//処理なし
			}

			//月
			if (!(tmpDate[1].length() == 1 || tmpDate[1].length() == 2)) {
				return "";
			}
			//「M」入力
			if (tmpDate[1].length() == 1) {
				tmpDate[1] = "0" + tmpDate[1];
			}
			//「MM」入力
			else if (tmpDate[1].length() == 2) {
				//処理なし
			}
			fDate = tmpDate[0] + tmpDate[1];
//			fDate = tmpDate[0] + "/" + tmpDate[1];
		}
		return fDate;
	}

    
    /**
	 * @return MlCompletionregistDto を戻します。
	 */
    public MlCompletionregistDto getMlCompletionregistDto() {
        return mlCompletionregistDto;
    }
    /**
     * @param MlCompletionregistDto mlCompletionregistDto を設定。
     */
    public void setMlCompletionregistDto(MlCompletionregistDto mlCompletionregistDto) {
        this.mlCompletionregistDto = mlCompletionregistDto;
    }
    /**
     * チェックロジックオブジェクト取得処理
     * 
     * @return checkContentsLogic を戻します。
     */
    public CheckContentsLogicImpl getCheckContentsLogic() {
        return checkContentsLogic;
    }
    /**
     * チェックロジックオブジェクト設定処理
     * @param logic
     */
    public void setCheckContentsLogic(CheckContentsLogicImpl logic) {
        checkContentsLogic = logic;
    }
}