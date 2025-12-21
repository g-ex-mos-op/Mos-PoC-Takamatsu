package jp.co.isid.mos.bird.bizsupport.moschickenreservation.action.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizsupport.moschickenreservation.action.MosChiCkenInfoEditAction;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.dto.MosChickenRevInfoDto;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.entity.CodMiseList;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.entity.MstCmenuInfo;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.entity.MstMosChikenFromTo;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.entity.TrnMosChikenDet;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.entity.TrnMosChikenInfo;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.logic.CheckMosChickenLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.logic.MosChickenRevCancelLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.logic.MosChickenRevDetailInfoLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.logic.MosChickenRevNewInfoLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.logic.UpdateMosChickenRevLogic;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;

/**
 * モスチキン予約
 * 編集画面用アクション実行クラス
 * 
 * @author inazawa
 *
 */
public class MosChickenInfoEditActionImpl implements MosChiCkenInfoEditAction {
    /* VIEW_ID */
    public static final String VIEW_ID            = "BBS016";
    public static final String condition_VIEW_ID  = "BBS016V01";
    public static final String result_VIEW_ID     = "BBS016V02";
    public static final String menuAdd_VIEW_ID    = "BBS016V03";
    public static final String onerSearch_VIEW_ID      = "BCO006V01";
    /*キャンセルフラグ0：未、1：済、2:新規登録*/
    public static final String cancelZumi = "1";
    public static final String cancelNot  = "0";
    public static final String NewRev         = "0";
    /*新規／修正0：新規、2：修正*/
    public static final String sinki = "1";
    public static final String shusei  = "0";
    
    public static final String editBack = "1";
    /* アクションID */
    public static final String newReserved_ACTION_ID = "BBS016A03";
    public static final String revExec_ACTION_ID     = "BBS016A04";
    public static final String updateExit_ACTION_ID  = "BBS016A06";
    public static final String updateNext_ACTION_ID  = "BBS016A07";
    public static final String notReserve_ACTION_ID  = "BBS016A08";
    public static final String BACK_ACTION_ID        = "BBS016A10";
    public static final String initialize_ACTION_ID  = "BBS016A17";

    /** 指定改行文字 */
    private static final String _ENTER_WORD = "`";

    /**ロジック[[モスチキン予約明細]]*/
    public MosChickenRevDetailInfoLogic mosChickenRevDetailInfoLogic;
    /** ロジック【モスチキン新規予約】*/
    private MosChickenRevNewInfoLogic mosChickenRevNewInfoLogic;
    /** ロジック【エラーチェック】*/
    private CheckMosChickenLogic checkMosChickenLogic;
    /** ロジック【登録・更新】*/
    private UpdateMosChickenRevLogic updateMosChickenRevLogic;
    /** ロジック【キャンセル】*/
    private MosChickenRevCancelLogic mosChickenRevCancelLogic;
    /**Dto*/
    public MosChickenRevInfoDto mosChickenRevInfoDto;
    
    /**
     * モスチキン予約明細情報取得
     */
    public String revExec(){
//      add start xkhata 
        MakeSessionKey make = new MakeSessionKey();
        
        if ( !make.isValidSessionKey( mosChickenRevInfoDto.getViewSessionKey(),mosChickenRevInfoDto.getSessionKey() ) ) {
            return make.operationErr_VIEW_ID;
        }        
// add end 
        
        List resultList = getMosChickenRevDetailInfoLogic().execute(getMosChickenRevInfoDto());
        if(resultList == null || resultList.size() ==0){
            throw new NotExistException("明細情報");
        }
        getTitle();
        getMiseNameKj();
        getRemark();
        getMosChickenRevInfoDto().setNewFlg(shusei);
        
        if(null == getMosChickenRevInfoDto().getReservMm()){
            reservTime();
        }
		TrnMosChikenDet trnMosChiken = (TrnMosChikenDet)resultList.get(0);
        
        //お渡し日が空白のまま条件画面から遷移した場合
        if( getMosChickenRevInfoDto().getReservDate() == null ||
        		getMosChickenRevInfoDto().getReservDate().equals(""))  {
            getMosChickenRevInfoDto().setReservDate(trnMosChiken.getReserveDt());
        } else if(!trnMosChiken.getReserveDt().equals(getMosChickenRevInfoDto().getReservDate())) {
        	//DBから取得した値とパラメータの値が異なる場合、DBから取得した値が正しい
        	getMosChickenRevInfoDto().setReservDate(trnMosChiken.getReserveDt());
        }
        //結果画面取得したお渡し日を格納-----------------------------------
        getMosChickenRevInfoDto().setParamReservDate(getMosChickenRevInfoDto().getReservDate());
        //-------------------------------------------------------------
        getMosChickenRevInfoDto().setListMosChiCkenDatailInfo(resultList);
        for(int i=0;resultList.size()>i;i++){
            TrnMosChikenDet menuList = (TrnMosChikenDet)resultList.get(i);
            menuList.setStrReserveAmt(String.valueOf(menuList.getReserveAmt()));
        } 
        return result_VIEW_ID;
        
    }
    /**
     * 備考メモなど取得
     */
    private void getRemark() {
        for(int i=0;getMosChickenRevInfoDto().getListMosChiCkenInfo().size()>i;i++){
            TrnMosChikenInfo mosDevList = (TrnMosChikenInfo)getMosChickenRevInfoDto().getListMosChiCkenInfo().get(i);
            if(i == getMosChickenRevInfoDto().getRevIndex()){
                getMosChickenRevInfoDto().setBikou(mosDevList.getRemark());
                getMosChickenRevInfoDto().setMemo(createMemo(mosDevList.getMemo()));
                getMosChickenRevInfoDto().setPaymentFlg(mosDevList.getPaymentFlg());
                getMosChickenRevInfoDto().setPremiumFlg(mosDevList.getPremiumFlg());
                getMosChickenRevInfoDto().setReservHour(mosDevList.getReserveHh());
                getMosChickenRevInfoDto().setReservMin(mosDevList.getReserveMm());
            }
        }
    }
    /**
     * メモを改行にする
     * @param memo
     * @return
     */
    private String createMemo(String memo) {
        String addBrMemo = "";

        int index = 0;
        boolean endFlg = true;
        
        if(memo != null && !memo.equals("")){
            while(endFlg){
                index = memo.indexOf(_ENTER_WORD);
                if(index == -1) {
                    endFlg = false;
                }else{
                    String topMemo = memo.substring(0,index);
                    String bottomMemo = memo.substring(index+1);
                        memo = topMemo + "\n"+ bottomMemo;
                }
            }
            addBrMemo = memo;
        }

        return addBrMemo;
    }
    /**
     * 新規登録押下
     * @return
     */
    public String newReserved(){
//      add start xkhata 
        MakeSessionKey make = new MakeSessionKey();
        
        if ( !make.isValidSessionKey( mosChickenRevInfoDto.getViewSessionKey(),mosChickenRevInfoDto.getSessionKey() ) ) {
            return make.operationErr_VIEW_ID;
        }
// add end
       getMosChickenRevInfoDto().newRegClear();
       getTitle();
       getMiseNameKj();
       List newRevList = null;
       if(getMosChickenRevInfoDto().getListMstMenu() == null || getMosChickenRevInfoDto().getListMstMenu().size() == 0){
           newRevList = getMosChickenRevNewInfoLogic().execute(getMosChickenRevInfoDto());
           if(newRevList == null || newRevList.size() ==0){
               throw new NotExistException("登録商品");
           }
           getMosChickenRevInfoDto().setListMosChiCkenDatailInfo(newRevList);
           for(int i=0;newRevList.size()>i;i++){
               MstCmenuInfo menuList = (MstCmenuInfo)newRevList.get(i);
               menuList.setStrReserveAmt("0");
           }
           getMosChickenRevInfoDto().setListMstMenu(newRevList);
       }else{
           newRevList = getMosChickenRevInfoDto().getListMstMenu();
       }
       getMosChickenRevInfoDto().setNewFlg(sinki);
       if(null == getMosChickenRevInfoDto().getReservMm()){
           reservTime();
       }
       //お渡し日と格納したパラメータ渡し日が異なる場合
       getMosChickenRevInfoDto().setParamReservDate(getMosChickenRevInfoDto().getReservDate());
       //---------------------
       getMosChickenRevInfoDto().setCancelFlg(NewRev);
       return result_VIEW_ID;
    }
    /**
     * 選択された店名称を取得
     *
     */
    private void getMiseNameKj() {
        //店名称取得
        if(getMosChickenRevInfoDto().getListOnerMise() != null && getMosChickenRevInfoDto().getListOnerMise().size() > 0){
            for(int i=0;getMosChickenRevInfoDto().getListOnerMise().size()>i;i++){
                CodMiseList miseList = (CodMiseList)getMosChickenRevInfoDto().getListOnerMise().get(i);
                if(miseList.getMiseCd().equals(getMosChickenRevInfoDto().getMiseCd())){
                    getMosChickenRevInfoDto().setMiseNmKj(miseList.getMiseNameKj());
                }
            }
        }
   }
    /**
     * 時間取得
     */
    private void reservTime(){
        List listHh = new ArrayList();
        CodeFormatter dateFormatter = new CodeFormatter(2);
        dateFormatter.setFormatPattern("00");
        for(int j=0;j<24;j++){
            String strHh = String.valueOf(dateFormatter.format(String.valueOf(j), true));
            // add start xlee お渡し時間　初期値空白
            if(j == 0) {
                SelectItem hour = new SelectItem("");
                listHh.add(hour);            	
            }
            // add end
            SelectItem hour = new SelectItem(strHh);
            listHh.add(hour);
        }
        getMosChickenRevInfoDto().setReservHh(listHh);
        List listMm = new ArrayList();
        for(int k=0;k<46;k = k+15){
            String strMm = String.valueOf(dateFormatter.format(String.valueOf(k), true));
            // add start xlee お渡し時間　初期値空白
            if(k == 0) {
                SelectItem min = new SelectItem("");
                listMm.add(min);
            }
            //add end
            SelectItem min = new SelectItem(strMm);
            listMm.add(min);
        }
        getMosChickenRevInfoDto().setReservMm(listMm);
        if(getMosChickenRevInfoDto().getNewFlg().equals(shusei)){
            TrnMosChikenInfo trnMosChikenInfo = (TrnMosChikenInfo)getMosChickenRevInfoDto().getListMosChiCkenInfo().get(getMosChickenRevInfoDto().getRevIndex());
            getMosChickenRevInfoDto().setReservHour(dateFormatter.format(trnMosChikenInfo.getReserveHh(),true));
            getMosChickenRevInfoDto().setReservMin(dateFormatter.format(trnMosChikenInfo.getReserveMm(),true));
            getMosChickenRevInfoDto().setReservDate(trnMosChikenInfo.getReserveDt());
        }else if(getMosChickenRevInfoDto().getNewFlg().equals(sinki)){
            getMosChickenRevInfoDto().setReservHour(""); //初期値
            getMosChickenRevInfoDto().setReservMin(""); //初期値
        }
    }
   /**
     * 選択されたタイトルを取得
     *
     */
    private void getTitle() {
        //選択されたキャンペーン名取得
        if(getMosChickenRevInfoDto().getListCamp() != null && getMosChickenRevInfoDto().getListCamp().size() > 0){
            for(int i=0;getMosChickenRevInfoDto().getListCamp().size()>i;i++){
                MstMosChikenFromTo titleList = (MstMosChikenFromTo)getMosChickenRevInfoDto().getListCamp().get(i);
                if(titleList.getCkanriNo().equals(getMosChickenRevInfoDto().getCkanriNo())){
                    getMosChickenRevInfoDto().setTitle(titleList.getTitle());
                }
            }
        }
   }
    /**
     * 登録して終了
     */
    public String updateExit() {
// add start xkhata 20061027 セッションキーによる複数ウィンドウチェック(後勝ち)
        MakeSessionKey make = new MakeSessionKey();
        if (!make.isValidSessionKey(mosChickenRevInfoDto.getSessionKey(), mosChickenRevInfoDto.getViewSessionKey())) {
            return make.operationErr_VIEW_ID;
        }
// add end
        getCheckMosChickenLogic().validater(getMosChickenRevInfoDto());
        if(getMosChickenRevInfoDto().getNewFlg().equals(sinki)){
            getUpdateMosChickenRevLogic().insert(getMosChickenRevInfoDto());
        }else if(getMosChickenRevInfoDto().getNewFlg().equals(shusei)){
            getUpdateMosChickenRevLogic().update(getMosChickenRevInfoDto());
        }
        getMosChickenRevInfoDto().setListMosChiCkenInfo(null);
        return condition_VIEW_ID;
    }
    /**
     * 登録して次の新規予約
     */
    public String updateNext() {
//      add start xkhata 20061027 セッションキーによる複数ウィンドウチェック(後勝ち)
        MakeSessionKey make = new MakeSessionKey();
        if (!make.isValidSessionKey(mosChickenRevInfoDto.getSessionKey(), mosChickenRevInfoDto.getViewSessionKey())) {
            return make.operationErr_VIEW_ID;
        }
// add end
        getCheckMosChickenLogic().validater(getMosChickenRevInfoDto());
        if(getMosChickenRevInfoDto().getNewFlg().equals(sinki)){
            getUpdateMosChickenRevLogic().insert(getMosChickenRevInfoDto());
        }else if(getMosChickenRevInfoDto().getNewFlg().equals(shusei)){
            getUpdateMosChickenRevLogic().update(getMosChickenRevInfoDto());
        }
        getMosChickenRevInfoDto().setNewFlg(sinki);
        getMosChickenRevInfoDto().newRegClear();
        newReserved();
        return null;
    }
    
    /**
     * キャンセル取り消し／予約キャンセル
     */
    public String notReserve() {
        // add start xkhata 20061027 セッションキーによる複数ウィンドウチェック(後勝ち)
        MakeSessionKey make = new MakeSessionKey();
        if (!make.isValidSessionKey(mosChickenRevInfoDto.getSessionKey(), mosChickenRevInfoDto.getViewSessionKey())) {
            return make.operationErr_VIEW_ID;
        }
        // add end
        
        getMosChickenRevCancelLogic().cancel(getMosChickenRevInfoDto());
        //検索結果一覧
        List listMosChiCkenInfo = getMosChickenRevInfoDto().getListMosChiCkenInfo();
        if(listMosChiCkenInfo != null){
            //検索一覧がある場合
            TrnMosChikenInfo entity = (TrnMosChikenInfo)listMosChiCkenInfo.get(0);
            //検索結果一覧の引渡し日を条件項目.引渡し日に設定する。
            getMosChickenRevInfoDto().setReservDate(entity.getReserveDt());
        }
        getMosChickenRevInfoDto().setActionKbn(editBack);
        
        return condition_VIEW_ID;
    }
    /**
     * 初期処理
     */
    public String initialize() {
//      add start xkhata 200601031
        MakeSessionKey make = new MakeSessionKey();
        
        String sessionKey = make._makeSessionKey();
        
        mosChickenRevInfoDto.setViewSessionKey( sessionKey );
        mosChickenRevInfoDto.setSessioniKey( sessionKey );
// add end

        return null;
    }
    /**
     * 戻る処理
     */
    public String back(){
        // add start xkhata 20061027 セッションキーによる複数ウィンドウチェック(後勝ち)
        MakeSessionKey make = new MakeSessionKey();
        if (!make.isValidSessionKey(mosChickenRevInfoDto.getSessionKey(), mosChickenRevInfoDto.getViewSessionKey())) {
            return make.operationErr_VIEW_ID;
        }
        //検索結果一覧
        List listMosChiCkenInfo = getMosChickenRevInfoDto().getListMosChiCkenInfo();
        if(listMosChiCkenInfo != null){
            //検索一覧がある場合
            TrnMosChikenInfo entity = (TrnMosChikenInfo)listMosChiCkenInfo.get(0);
            //検索結果一覧の引渡し日を条件項目.引渡し日に設定する。
            getMosChickenRevInfoDto().setReservDate(entity.getReserveDt());
        }
        getMosChickenRevInfoDto().setActionKbn(editBack);
        return condition_VIEW_ID;
    }
    
    /**
     * mosChickenRevDetailInfoLogicを戻す
     * @return mosChickenRevDetailInfoLogic
     */
    public MosChickenRevDetailInfoLogic getMosChickenRevDetailInfoLogic() {
        return mosChickenRevDetailInfoLogic;
    }
    /**
     * mosChickenRevDetailInfoLogicを設定
     * @param mosChickenRevDetailInfoLogic
     */
    public void setMosChickenRevDetailInfoLogic(
            MosChickenRevDetailInfoLogic mosChickenRevDetailInfoLogic) {
        this.mosChickenRevDetailInfoLogic = mosChickenRevDetailInfoLogic;
    }
    /**
     * mosChickenRevInfoDtoを戻す
     * @return mosChickenRevInfoDto
     */
    public MosChickenRevInfoDto getMosChickenRevInfoDto() {
        return mosChickenRevInfoDto;
    }
    /**
     * mosChickenRevInfoDtoを設定
     * @param mosChickenRevInfoDto
     */
    public void setMosChickenRevInfoDto(MosChickenRevInfoDto mosChickenRevInfoDto) {
        this.mosChickenRevInfoDto = mosChickenRevInfoDto;
    }
    /**
     * mosChickenRevNewInfoLogicを戻す
     * @return mosChickenRevNewInfoLogic
     */
    public MosChickenRevNewInfoLogic getMosChickenRevNewInfoLogic() {
        return mosChickenRevNewInfoLogic;
    }
    /**
     * mosChickenRevNewInfoLogicを設定
     * @param mosChickenRevNewInfoLogic
     */
    public void setMosChickenRevNewInfoLogic(
            MosChickenRevNewInfoLogic mosChickenRevNewInfoLogic) {
        this.mosChickenRevNewInfoLogic = mosChickenRevNewInfoLogic;
    }
    /**
     * checkMosChickenLogicを返す
     * @return checkMosChickenLogic
     */
    public CheckMosChickenLogic getCheckMosChickenLogic() {
        return checkMosChickenLogic;
    }
    /**
     * checkMosChickenLogicを戻す
     * @param checkMosChickenLogic
     */
    public void setCheckMosChickenLogic(CheckMosChickenLogic checkMosChickenLogic) {
        this.checkMosChickenLogic = checkMosChickenLogic;
    }
    /**
     * updateMosChickenRevLogicを返す
     * @return updateMosChickenRevLogic
     */
    public UpdateMosChickenRevLogic getUpdateMosChickenRevLogic() {
        return updateMosChickenRevLogic;
    }
    /**
     * updateMosChickenRevLogicを設定
     * @param updateMosChickenRevLogic
     */
    public void setUpdateMosChickenRevLogic(
            UpdateMosChickenRevLogic updateMosChickenRevLogic) {
        this.updateMosChickenRevLogic = updateMosChickenRevLogic;
    }
    /**
     * mosChickenRevCancelLogicを返す
     * @return mosChickenRevCancelLogic
     */
    public MosChickenRevCancelLogic getMosChickenRevCancelLogic() {
        return mosChickenRevCancelLogic;
    }
    /**
     * mosChickenRevCancelLogicを設定
     * @param mosChickenRevCancelLogic
     */
    public void setMosChickenRevCancelLogic(
            MosChickenRevCancelLogic mosChickenRevCancelLogic) {
        this.mosChickenRevCancelLogic = mosChickenRevCancelLogic;
    }
    
//    //2006/11/09
//    private void setParamReserveDt(
//            String paramReserveDt) {
//        this.paramReserveDt = paramReserveDt;
//    }
//    
//    private String getParamReserveDt() {
//        return paramReserveDt;
//    }

}
