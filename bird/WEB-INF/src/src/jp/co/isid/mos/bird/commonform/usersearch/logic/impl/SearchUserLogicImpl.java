/*
 * 作成日: 2005/12/22
 */
package jp.co.isid.mos.bird.commonform.usersearch.logic.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.commonform.usersearch.dao.UIGroupTogoUserDao;
import jp.co.isid.mos.bird.commonform.usersearch.dto.UserSearchConditionDto;
import jp.co.isid.mos.bird.commonform.usersearch.entity.UIGroupTogoUser;
import jp.co.isid.mos.bird.commonform.usersearch.logic.SearchUserLogic;
import jp.co.isid.mos.bird.framework.exception.NotNullException;

/**
 * ユーザー検索処理ロジック
 * @author m.onodera
 */
public class SearchUserLogicImpl implements SearchUserLogic {
    public static final String LOGIC_ID = "BCO003L05";

    /* UIGroupTogoUserDao */
    private UIGroupTogoUserDao uiGroupTogoUserDao;

    //ソート順：ユーザＩＤ
    private class SortComparator implements Comparator {
        public boolean equals(Object obj) {
            return (super.equals(obj));
        }
        public int compare(Object obj1, Object obj2) {
            String val1 = ((UIGroupTogoUser) obj1).getUserId();
            String val2 = ((UIGroupTogoUser) obj2).getUserId();
            return val1.compareTo(val2);
        }
    }

    
    /**
     * ユーザー検索Daoを取得します。
     * @return uiGroupTogoOnerDao
     */
    public UIGroupTogoUserDao getUiGroupTogoUserDao() {
        return uiGroupTogoUserDao;
    }
    /**
     * ユーザー検索Daoを設定します。
     * @param uiGroupTogoOnerDao
     */
    public void setUiGroupTogoUserDao(UIGroupTogoUserDao uiGroupTogoUserDao) {
        this.uiGroupTogoUserDao = uiGroupTogoUserDao;
    }

    /**
     * ユーザー検索処理
     * @param onerSearchConditionDto
     * @param indexSearchList
	 */
	public List execute(UserSearchConditionDto userSearchConditionDto) {
		List honbudata = null;
		List onerdata = null;
		List misedata = null;
        List miseFromOner = null;
        validate(userSearchConditionDto);

		String kaisyaCd = userSearchConditionDto.getKaisyaCd();
		String userName = (userSearchConditionDto.getUserName() == null) ? null : "%" + userSearchConditionDto.getUserName() + "%";
		String bumonCd = userSearchConditionDto.getBumonCd();
		String onerCd = userSearchConditionDto.getOnerCd();
		String onerName = (userSearchConditionDto.getOnerNameKj() == null) ? null : "%" + userSearchConditionDto.getOnerNameKj() + "%";
		String miseCd = userSearchConditionDto.getMiseCd();
		String miseName = (userSearchConditionDto.getMiseNameKj() == null) ? null : "%" + userSearchConditionDto.getMiseNameKj() + "%";

        //20060628 ADD START ユーザー選択画面 条件追加対応 INAZAWA
        String roleCd = userSearchConditionDto.getRoleCd();
        boolean stopFlg = userSearchConditionDto.isStopFlg();
        String strStopFlg = null;
        if(!stopFlg){
            strStopFlg = "0";
        }
        //20060628 ADD END ユーザー選択画面 条件追加対応 INAZAWA

        String userIdCond = userSearchConditionDto.getUserIdCond();
        if(userIdCond != null){
            userIdCond = "%" + userIdCond + "%";
        }
        
        //--------------------------------------------------------------
        // (1) kaisyaCdとuserName以外の入力がない場合は全てのSQLを実行する
        //--------------------------------------------------------------
		if(bumonCd == null && onerCd == null && onerName == null && miseCd == null && miseName == null){

            //Honbu
//			honbudata = getUiGroupTogoUserDao().selectHonbu(kaisyaCd, bumonCd, userName,roleCd,strStopFlg);
            honbudata = getUiGroupTogoUserDao().selectHonbu(kaisyaCd, bumonCd, userName,roleCd,strStopFlg, userIdCond);

			userSearchConditionDto.setUserSearchHonbuList(honbudata);

            //Oner
			onerdata = getUiGroupTogoUserDao().selectOner(kaisyaCd, userName, onerCd, onerName,roleCd,strStopFlg, userIdCond);
			userSearchConditionDto.setUserSearchOnerList(onerdata);            

            //Mise
			misedata = getUiGroupTogoUserDao().selectMise(kaisyaCd, userName, miseCd, miseName, onerCd, onerName,roleCd,strStopFlg, userIdCond);
			userSearchConditionDto.setUserSearchMiseList(misedata);
		}
        //--------------------------------------------------------------
        // (2) 部門が選択された場合はHonbu用SQLのみ実行
        //--------------------------------------------------------------
		else if(bumonCd != null){

			honbudata = getUiGroupTogoUserDao().selectHonbu(kaisyaCd, bumonCd, userName,roleCd,strStopFlg, userIdCond);
			userSearchConditionDto.setUserSearchHonbuList(honbudata);
		}
        //--------------------------------------------------------------
        // (3) 部門、店コード、店名称が選択されていない場合のみOner用SQL実行
        //--------------------------------------------------------------
        else if(miseCd == null && miseName == null && (onerCd != null || onerName != null)){	

            onerdata = getUiGroupTogoUserDao().selectOner(kaisyaCd, userName, onerCd, onerName,roleCd,strStopFlg, userIdCond);
			userSearchConditionDto.setUserSearchOnerList(onerdata);

            //オーナーコードが入力された場合、オーナーの保有する店舗のユーザ取得
            miseFromOner = getMiseFormOwner(onerdata, strStopFlg);
            
		}
        //--------------------------------------------------------------
        // (4) 店コード、店名称が選択されている場合はMise用SQL実行
        //--------------------------------------------------------------
        else if(miseCd != null || miseName != null){	

			misedata = getUiGroupTogoUserDao().selectMise(kaisyaCd, userName, miseCd, miseName, onerCd, onerName,roleCd,strStopFlg, userIdCond);

            userSearchConditionDto.setUserSearchMiseList(misedata);
		}else{
		}

		//各SQLの結果を一つに纏める
		List alldata = new ArrayList();
		if(userSearchConditionDto.getUserSearchHonbuList() != null){
			alldata.addAll(userSearchConditionDto.getUserSearchHonbuList());
			//結果Listにset後、本部Listのクリア
			userSearchConditionDto.setUserSearchHonbuList(null);
		}
		if(userSearchConditionDto.getUserSearchOnerList() != null){
			alldata.addAll(userSearchConditionDto.getUserSearchOnerList());
			//結果Listにset後、オーナーListのクリア
			userSearchConditionDto.setUserSearchOnerList(null);
		}
		if(userSearchConditionDto.getUserSearchMiseList() != null){
			alldata.addAll(userSearchConditionDto.getUserSearchMiseList());
			//結果Listにset後、店Listのクリア
			userSearchConditionDto.setUserSearchMiseList(null);
		}
        if(miseFromOner != null){
            alldata.addAll(miseFromOner);
        }
        //重複ユーザＩＤ削除
        alldata = deleteDuplicateUserId(alldata);
        
        // 全件数設定
		userSearchConditionDto.setCount(alldata.size());
        
        //ソート
        Collections.sort(alldata, new SortComparator());

        
        //----表示件数設定--------------------------
        int fromIndex = userSearchConditionDto.getPageFirstRecordNumber();
        int toIndex = fromIndex + userSearchConditionDto.getMaxPageCount();

        // インデックス補正
        fromIndex = (fromIndex > alldata.size()) ? alldata.size() : fromIndex;
        toIndex = (toIndex > alldata.size()) ? alldata.size() : toIndex;

        // ページ範囲リストを返却
        return alldata.subList(fromIndex, toIndex);
        //----表示件数設定--------------------------
	}

    /**
     * オーナーの保有する店舗のユーザーを取得する。
     * @param onerdata オーナー 
     * @param strStopFlg 使用停止フラグ
     * @return
     */
    private List getMiseFormOwner(List onerdata,  String strStopFlg){
        List misedata = new ArrayList();
        
        for(int i = 0; i < onerdata.size(); i++){
            UIGroupTogoUser e = (UIGroupTogoUser)onerdata.get(i);
            List ower = getUiGroupTogoUserDao().selectMiseFromOwner(e.getCompanyCd(), e.getOnerCd(), strStopFlg);
            misedata.addAll(ower);
        }
        return misedata;
    }

    /**
     * 重複したユーザＩＤを削除します。
     * @param alldata 検索結果
     * @return 重複したユーザＩＤなし
     */
    private List deleteDuplicateUserId(List alldata){
        List result = new ArrayList();
        Map check = new HashMap();
        for(int i = 0; i < alldata.size(); i++){
            UIGroupTogoUser e = (UIGroupTogoUser)alldata.get(i);
            if(!check.containsKey(e.getUserId())){
                result.add(e);
                check.put(e.getUserId(), "");
            }
        }
        return result;
    }
    
    /**
     * 妥当性チェック
     * @param userSearchConditionDto
     */
    private void validate(UserSearchConditionDto userSearchConditionDto){
        String kaisyaCd = userSearchConditionDto.getKaisyaCd();
        if(kaisyaCd == null || kaisyaCd.trim().length() == 0){
            throw new NotNullException("会社");
        }
    }
    
}
