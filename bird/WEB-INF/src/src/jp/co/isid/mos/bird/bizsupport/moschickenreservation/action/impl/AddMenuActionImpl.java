package jp.co.isid.mos.bird.bizsupport.moschickenreservation.action.impl;




import java.util.List;

import jp.co.isid.mos.bird.bizsupport.moschickenreservation.action.AddMenuAction;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.dto.MosChickenRevInfoDto;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.entity.CodSMneuInfo;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.entity.MstCmenuInfo;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.entity.TrnMosChikenDet;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.entity.TrnMosChikenInfo;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.logic.StoreMenuAddLogic;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;
/**
 * モスチキン予約
 * 商品追加画面用アクション実行クラス
 * @author inazawa
 * 2006/09/19
 */
public class AddMenuActionImpl implements AddMenuAction {
    /** コードバリデータ */

    /**
     * updateMosChickenInfo
     * 
     * @return null 画面ID
     */
    /* VIEW_ID */
    public static final String VIEW_ID            = "BBS016";
    public static final String result_VIEW_ID     = "BBS016V02";
    public static final String menuAdd_VIEW_ID    = "BBS016V03";
    /* ACTION_ID*/
    public static final String addMenuInit_ACTION_ID = "BBS016A09";
    public static final String exec_ACTION_ID        = "BBS016A11";
    public static final String select_ACTION_ID      = "BBS016A12";
    public static final String back_ACTION_ID        = "BBS016A13";
    public static final String initialize_ACTION_ID  = "BBS016A18";
    /*ロジック[[商品追加]]*/
    public StoreMenuAddLogic mosChickenRevStoreMenuAddLogic;
    /*モスチキン予約Dto*/
    MosChickenRevInfoDto mosChickenRevInfoDto;
    /*新規／修正0：新規、2：修正*/
    public static final String sinki = "1";
    public static final String shusei  = "0";
    /**
     * 決定ボタン押下
     */
    public String select(){
        
//      add start xkhata 
        MakeSessionKey make = new MakeSessionKey();
        
        if ( !make.isValidSessionKey( mosChickenRevInfoDto.getViewSessionKey(),mosChickenRevInfoDto.getSessionKey() ) ) {
            return make.operationErr_VIEW_ID;
        }
        
// add end 

        errCheck();
        getMosChickenRevInfoDto().setAddMenuText("");
        getMosChickenRevInfoDto().setListAddMenu(null);
        return result_VIEW_ID;
    }
    /**
     * 商品追加初期処理
     */
    public String addMenuInit() {
        // add start xkhata 20061027 セッションキーによる複数ウィンドウチェック(後勝ち)
        
        MakeSessionKey make  = new MakeSessionKey();
        
        if (!make.isValidSessionKey(mosChickenRevInfoDto.getSessionKey(), mosChickenRevInfoDto.getViewSessionKey())) {
            return make.operationErr_VIEW_ID;
        }
        // add end
        getMosChickenRevInfoDto().setListAddMenu(null);
        return menuAdd_VIEW_ID;
        
    }
    private void errCheck() {
        if(getMosChickenRevInfoDto().getListAddMenu() == null || getMosChickenRevInfoDto().getListAddMenu().size() ==0){
            throw new GenericMessageException("追加する商品を選択して下さい。");
        }
        boolean errCheck = false;
        for(int i=0;getMosChickenRevInfoDto().getListAddMenu().size()>i;i++){
            CodSMneuInfo codSMneuInfo= (CodSMneuInfo)getMosChickenRevInfoDto().getListAddMenu().get(i);
            if(codSMneuInfo.isCheckedMenu()){
                errCheck = true;
            }
        }
        if (!errCheck) {
            throw new GenericMessageException("追加する商品を選択して下さい。");
        }
        List list = getMosChickenRevInfoDto().getListAddMenu();
        List kizonList = getMosChickenRevInfoDto().getListMosChiCkenDatailInfo();
        
        int kizonListSize = kizonList.size();

        for (int i = 0; i < list.size(); i++) {
            CodSMneuInfo codSMneuInfo = (CodSMneuInfo) list.get(i);
            if(codSMneuInfo.isCheckedMenu()){ 
                boolean choufukuCheck = true;
                if(getMosChickenRevInfoDto().getNewFlg().equals(shusei)){
                    for (int j = 0; j < kizonListSize; j++) {
        
                        TrnMosChikenDet entity = (TrnMosChikenDet) kizonList.get(j);
                        if(codSMneuInfo.getMenuCd().equals(entity.getMenuCd())){
                            choufukuCheck = false;
                            break;
                        }
                    }
                    if(choufukuCheck){
                            TrnMosChikenDet trnMosChikenDetty = new TrnMosChikenDet();
                            TrnMosChikenInfo trnMosChikenDet = (TrnMosChikenInfo)getMosChickenRevInfoDto().getListMosChiCkenInfo().get(getMosChickenRevInfoDto().getRevIndex());
                            trnMosChikenDetty.setCkanriNo(getMosChickenRevInfoDto().getCkanriNo());
                            trnMosChikenDetty.setCompanyCd(codSMneuInfo.getCompanyCd());
                            trnMosChikenDetty.setMenuCd(codSMneuInfo.getMenuCd());
                            trnMosChikenDetty.setMenuNameKj(codSMneuInfo.getMenuNameKj());
                            trnMosChikenDetty.setMiseCd(getMosChickenRevInfoDto().getMiseCd());
                            trnMosChikenDetty.setStrReserveAmt("0");
                            trnMosChikenDetty.setSeqNo(trnMosChikenDet.getSeqNo());
                            getMosChickenRevInfoDto().getListMosChiCkenDatailInfo().add(trnMosChikenDetty);
                    }
              }else{
                  for (int j = 0; j < kizonListSize; j++) {
                      MstCmenuInfo entity = (MstCmenuInfo) kizonList.get(j);
                      if(codSMneuInfo.isCheckedMenu() && codSMneuInfo.getMenuCd().equals(entity.getMenuCd())){
                          choufukuCheck = false;
                          break;
                      }
                  }
                  if(choufukuCheck){
                      MstCmenuInfo mstCmenuInfo = new MstCmenuInfo();
                      mstCmenuInfo.setCkanriNo(getMosChickenRevInfoDto().getCkanriNo());
                      mstCmenuInfo.setMenuCd(codSMneuInfo.getMenuCd());
                      mstCmenuInfo.setMenuNameKj(codSMneuInfo.getMenuNameKj());
                      mstCmenuInfo.setStrReserveAmt("0");
                      getMosChickenRevInfoDto().getListMosChiCkenDatailInfo().add(mstCmenuInfo);
                  }
              }
            }
        }
    }
    /**
     * 初期処理
     */
    public String initialize() {
        if(getMosChickenRevInfoDto().getListAddMenu() == null || getMosChickenRevInfoDto().getListAddMenu().size() ==0){
            getMosChickenRevInfoDto().setAddMenuText("");
        }
        return null;
    }
    /**
     * 検索ボタン押下
     */
    public String exec(){
        
//      add start xkhata 
        MakeSessionKey make = new MakeSessionKey();
        
        if ( !make.isValidSessionKey( mosChickenRevInfoDto.getViewSessionKey(),mosChickenRevInfoDto.getSessionKey() ) ) {
            return make.operationErr_VIEW_ID;
        }
        
// add end 

        validate(getMosChickenRevInfoDto());
        List resultList = getMosChickenRevStoreMenuAddLogic().exec(getMosChickenRevInfoDto());
        if(resultList == null || resultList.size()==0){
            mosChickenRevInfoDto.setListAddMenu(null);
            throw new NotExistException("該当データ","miseText",null);
        }
        getMosChickenRevInfoDto().setListAddMenu(resultList);
        return null;
    }
    /**
     * 戻るボタン押下
     */
    public String back(){
        
//      add start xkhata 
        MakeSessionKey make = new MakeSessionKey();
        
        if ( !make.isValidSessionKey( mosChickenRevInfoDto.getViewSessionKey(),mosChickenRevInfoDto.getSessionKey() ) ) {
            return make.operationErr_VIEW_ID;
        }
        
// add end 

        getMosChickenRevInfoDto().setListAddMenu(null);
        getMosChickenRevInfoDto().setAddMenuText(null);
        return result_VIEW_ID;
    }
    /**
     * エラーチェック
     * @param dto
     */
    private void validate(MosChickenRevInfoDto dto) {
        
        if(isNull(dto.getAddMenuText())){
            mosChickenRevInfoDto.setListAddMenu(null);
            throw new GenericMessageException("商品名を指定してください。","miseNm",null);
        }
            
         
     }
     /**
      * nullチェック
      */ 
     private boolean isNull(String val) {
         if (val == null) {
             return true;
         }
         if ("".equals(val.trim())) {
             return true;
         }
         return false;
     }

    /**
     * mosChickenRevStoreMenuAddLogicを戻す
     * @return mosChickenRevStoreMenuAddLogic
     */
    public StoreMenuAddLogic getMosChickenRevStoreMenuAddLogic() {
        return mosChickenRevStoreMenuAddLogic;
    }
    /**
     * mosChickenRevStoreMenuAddLogicを設定
     * @param mosChickenRevStoreMenuAddLogic
     */
    public void setMosChickenRevStoreMenuAddLogic(
            StoreMenuAddLogic mosChickenRevStoreMenuAddLogic) {
        this.mosChickenRevStoreMenuAddLogic = mosChickenRevStoreMenuAddLogic;
    }
    /**
     * mosChickenRevInfoDtoを設定
     * @return mosChickenRevInfoDto
     */
    public MosChickenRevInfoDto getMosChickenRevInfoDto() {
        return mosChickenRevInfoDto;
    }
    /**
     * mosChickenRevInfoDtoを戻す
     * @param mosChickenRevInfoDto
     */
    public void setMosChickenRevInfoDto(MosChickenRevInfoDto mosChickenRevInfoDto) {
        this.mosChickenRevInfoDto = mosChickenRevInfoDto;
    }

}
