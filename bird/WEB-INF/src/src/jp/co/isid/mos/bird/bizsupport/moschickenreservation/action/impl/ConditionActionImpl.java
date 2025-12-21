package jp.co.isid.mos.bird.bizsupport.moschickenreservation.action.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizsupport.moschickenreservation.action.ConditionAction;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.dto.MosChickenRevInfoDto;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.entity.CodMiseList;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.entity.MstMosChikenFromTo;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.entity.TrnMosChikenInfo;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.logic.CondMiseListLogicChickenRevLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.logic.CondMosChickenTitleLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.logic.MosChickenRevInfoLogic;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.exception.NoTargetException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * モスチキン予約
 * 初期画面用アクション実行クラス
 * @author inazawa
 * 2006/09/19
 */
public class ConditionActionImpl implements ConditionAction {
    /* DTO【モスチキン予約】*/
    private MosChickenRevInfoDto mosChickenRevInfoDto;
    /* DTO【オーナー選択】*/
    private OwnerSearchDto ownerSearchDto;

    /*ユーザータイプ*/
    private static final String HONBU_USER = "01";
    /* モス会社コード */
    private static final String COMPANY_CD_MOS = "00";
    /* アクションID */
    public static final String initialize_ACTION_ID     = "BBS016A01";
    public static final String exec_ACTION_ID           = "BBS016A02";
    public static final String honbuUserBack_ACTION_ID  = "BBS016A05";
    public static final String onerSearch_ACTION_ID     = "BBS016A14";
    public static final String changeTitle_ACTION_ID    = "BBS016A15";
    public static final String honbuExec_ACTION_ID      = "BBS016A16";
    /* VIEW_ID */
    public static final String VIEW_ID            = "BBS016";
    public static final String condition_VIEW_ID  = "BBS016V01";
    public static final String result_VIEW_ID     = "BBS016V02";
    public static final String menuAdd_VIEW_ID    = "BBS016V03";
    public static final String onerSearch_VIEW_ID      = "BCO006V01";
    /* ロジック【オーナー保有店舗】*/
    private CondMiseListLogicChickenRevLogic mosChickenRevCondMiseListLogic;
    /* ロジック【モスチキン予約入力条件設定】*/
    private CondMosChickenTitleLogic mosChickenRevCondMosChickenTitleLogic;
    /* ロジック【モスチキン予約情報取得】*/
    private MosChickenRevInfoLogic mosChickenRevInfoLogic;
    /** コードバリデータ */
    private CodeVerifier mosChickenRevCodeVerifier;
    /*キャンセルフラグ0：未、1：済、2:新規登録*/
    public static final String cancelZumi = "1";
    public static final String cancelNot  = "0";
    public static final String NewRev         = "0";
    /*新規／修正0：新規、2：修正*/
    public static final String sinki = "1";
    public static final String shusei  = "0";
    
    /*新規／修正0：新規、2：修正*/
    public static final String onerExec = "2";
    
    /**
     * 初期処理 アクション
     * 
     * @return null 画面ID
     * @throws Exception 
     */
    public String initialize() throws Exception {

        if (getPullDownMenuDto().isClearFlg()) {
        	//WINDOWID追加
        	getMosChickenRevInfoDto().updateWindowid();
        	//検索条件クリア
        	getMosChickenRevInfoDto().subClear();
            //ユーザーIDの設定
            getMosChickenRevInfoDto().setUserId(getBirdUserInfo().getMstUser().getUser_id());
            //ユーザータイプの設定
            getMosChickenRevInfoDto().setUserTypeCd(getBirdUserInfo().getMstUser().getUserTypeCd());
            //会社コードの設定
            getMosChickenRevInfoDto().setRCompanyCd(COMPANY_CD_MOS);
            
            //システム日付
            getMosChickenRevInfoDto().setSysDate(getBirdDateInfo().getSysDate());
            if(!getMosChickenRevInfoDto().getUserTypeCd().equals(HONBU_USER)){
                initShori();
                getMiseList();
                getMosChickenRevInfoDto().setInitShoriFlg("1");
                //条件画面設定メソッド
            }else{
                if(getMosChickenRevInfoDto().getInitShoriFlg() == null){
                    getMosChickenRevInfoDto().setInitShoriFlg("0");
                }
            }
            getPullDownMenuDto().setClearFlg(false);    
            getOwnerSearchDto().setOnerCd(null);
            getMosChickenRevInfoDto().setBirdUserInfo(getBirdUserInfo());
            
            // add start xkhata 複数ウィンドウ            
            MakeSessionKey make = new MakeSessionKey();
            
            String sessionKey = make._makeSessionKey();
            
            mosChickenRevInfoDto.setViewSessionKey( sessionKey );
            mosChickenRevInfoDto.setSessioniKey( sessionKey ); // add end

        }
        //２．オーナー選択画面から遷移した場合、下記の処理を行う。
        else if(getOwnerSearchDto().getReturnKind() != OwnerSearchDto.RETURNKIND_INIT){
            //２－１．DTO【オーナー選択】.遷移区分を初期値に設定する。
            getOwnerSearchDto().setReturnKind(OwnerSearchDto.RETURNKIND_INIT);
            //２－２．DTO.ウィンドウIDにDTO【オーナー選択】.ウィンドウIDを設定する。
            getMosChickenRevInfoDto().setWindowId(getOwnerSearchDto().getWindowId());
            //２－３．【sessionKey有効チェック】 有効でない場合は操作エラー画面(※)に遷移
	        MakeSessionKey make = new MakeSessionKey();
	        if ( !make.isValidSessionKey( mosChickenRevInfoDto.getViewSessionKey(),mosChickenRevInfoDto.getSessionKey() ) ) {
	            return make.operationErr_VIEW_ID;
	        }
            //２－４．オーナーを選択後遷移した場合は下記の処理を行う。
            if(getOwnerSearchDto().isActionFlag()){        
    			getMosChickenRevInfoDto().setOnerDefCd(getOwnerSearchDto().getOnerCd());
    			//オーナー検索画面から戻ったらすぐ予約入力条件画面が表示されるように
                initShori();
                getMiseList();
                getMosChickenRevInfoDto().setInitShoriFlg("1");
            }
            //２－５．DTO【オーナー選択】のクリア処理を実行する。
            getOwnerSearchDto().clear();
        }
        //３．『戻る』or『キャンセル取り消し』or『予約キャンセル』された場合、下記の処理を行う。
        else if( MosChickenInfoEditActionImpl.editBack.equals(getMosChickenRevInfoDto().getActionKbn()) ){
            //３－１．DTO【予約入力】.検索結果を取得する。
            List listSearchData = getMosChickenRevInfoDto().getListMosChiCkenInfo();
        	if(listSearchData != null) {               
        		//a.処理３－１の結果が存在する場合は再検索を行う。
        		exec();
        	}
            //３－２．アクション区分にnullを設定する。
        	getMosChickenRevInfoDto().setActionKbn(null);
        } else {
	        MakeSessionKey make = new MakeSessionKey();
	        if ( !make.isValidSessionKey( mosChickenRevInfoDto.getViewSessionKey(),mosChickenRevInfoDto.getSessionKey() ) ) {
	            return make.operationErr_VIEW_ID;
	        }
        }
        
        return null;    
    }
    /**
     * 本部ユーザー実行ボタン押下
     * @throws Exception 
     */
     public String honbuExec() throws Exception{
//       add start xkhata 
//         MakeSessionKey make = new MakeSessionKey();
//         
//         if ( !make.isValidSessionKey( mosChickenRevInfoDto.getViewSessionKey(),mosChickenRevInfoDto.getSessionKey() ) ) {
//             return make.operationErr_VIEW_ID;
//         }
         
         mosChickenRevInfoDto.setOnerCd(  mosChickenRevInfoDto.getOnerDefCd() ) ;
//  add end 

        if(isNull(getMosChickenRevInfoDto().getOnerCd())){
            throw new NotNullException("オーナーコード","onerCd",null);
        }else{
            if(!getMosChickenRevCodeVerifier().validate(getMosChickenRevInfoDto().getOnerCd().trim())){
                throw new InvalidInputException("オーナーコード","onerCd",null);
            }
        }   
        List onerList = getMosChickenRevCondMiseListLogic().execHonbu(getMosChickenRevInfoDto());    
        if(onerList == null || onerList.size() == 0){
           throw new NotExistException("オーナーコード"); 
        }
        initShori();
        getMiseList();
        getMosChickenRevInfoDto().setInitShoriFlg("1");
        getMosChickenRevInfoDto().setActionKbn(onerExec);
        return null;
         
     }

     /**
      * 本部ユーザーの初期画面に戻る
      * @return
      */
     public String honbuUserBack(){
//       add start xkhata 
         MakeSessionKey make = new MakeSessionKey();
         
         if ( !make.isValidSessionKey( mosChickenRevInfoDto.getViewSessionKey(),mosChickenRevInfoDto.getSessionKey() ) ) {
             return make.operationErr_VIEW_ID;
         }
         
//  add end 
        
        getMosChickenRevInfoDto().clear();
        getMosChickenRevInfoDto().setInitShoriFlg("0");
        getPullDownMenuDto().setClearFlg(true);    
        return null;
     }

    /**
      *検索画面押下時
      */
     public String exec(){
//       add start xkhata 
         MakeSessionKey make = new MakeSessionKey();
         
         if ( !make.isValidSessionKey( mosChickenRevInfoDto.getViewSessionKey(),mosChickenRevInfoDto.getSessionKey() ) ) {
             return make.operationErr_VIEW_ID;
         }
//  add end 
    	 //お渡し日の必須チェック
    	 validate();
         List mosChickenInfroList = new ArrayList();
         DateFormatter formatter = new DateFormatter();
         mosChickenInfroList = getMosChickenRevInfoLogic().execute(getMosChickenRevInfoDto());
         if(mosChickenInfroList == null || mosChickenInfroList.size() == 0){
             getMosChickenRevInfoDto().setListMosChiCkenInfo(null);
             throw new NotExistException("該当データ");
         }
         getMosChickenRevInfoDto().setListMosChiCkenInfo(mosChickenInfroList);
         ArrayList countList = new ArrayList();
         ArrayList hourList = new ArrayList();
         for(int i=0;mosChickenInfroList.size()>i;i++){
             TrnMosChikenInfo revList = (TrnMosChikenInfo)getMosChickenRevInfoDto().getListMosChiCkenInfo().get(i);
             hourList.add(revList.getReserveHh()+revList.getReserveMm());
         }
         int count=1;
         String revHourMae = null;
         String revHour = null;
         for(int i=0;mosChickenInfroList.size()>i;i++){
             TrnMosChikenInfo revList = (TrnMosChikenInfo)getMosChickenRevInfoDto().getListMosChiCkenInfo().get(i);
             if(mosChickenInfroList.size() == 1){
                 countList.add(String.valueOf(count));
             }else{
                 if(mosChickenInfroList.size() == i+1){
                     countList.add(String.valueOf(count));
                     if(!revHour.equals(revHourMae)){
                         countList.add(String.valueOf(1));
                     }
                 }else{
                     revHour =(String) hourList.get(i+1);
                     revHourMae = revList.getReserveHh()+revList.getReserveMm();
                     if(revHour.equals(revHourMae)){
                         count++;
                     }else{
                         countList.add(String.valueOf(count));
                         count = 1;
                     }
                 }
             }
         }
         int cntListSize = 0;
         int rowspan = 0;
         for(int i=0;mosChickenInfroList.size()>i;i++){
             TrnMosChikenInfo revList = (TrnMosChikenInfo)getMosChickenRevInfoDto().getListMosChiCkenInfo().get(i);
             if(rowspan == 0){
                 rowspan = Integer.parseInt((String) countList.get(cntListSize));
                 cntListSize++;
                 revList.setRowspan(rowspan);
             }else{
                 revList.setRowspan(0);
             }
             rowspan--;
             if(!"".equals(revList.getCancelDt())){
                 revList.setCancelDt(String.valueOf(formatter.format(String.valueOf(revList.getCancelDt()),DateFormatter.PATTERN_SLASH,DateFormatter.DATE_TYPE_YMD)));
             }
         }
         //----------------------------------
         //検索結果のお渡し日をParamReserveDateに格納
         getMosChickenRevInfoDto().setParamReservDate(getMosChickenRevInfoDto().getReservDate());
         return null;
     }
     /**
      * キャンペーンプルダウン変更処理
     * @throws Exception 
      */
     public String changeTitle() throws Exception {
//       add start xkhata 
         MakeSessionKey make = new MakeSessionKey();
         
         if ( !make.isValidSessionKey( mosChickenRevInfoDto.getViewSessionKey(),mosChickenRevInfoDto.getSessionKey() ) ) {
             return make.operationErr_VIEW_ID;
         }
//  add end 
         initShori();
         getMiseList();
         getMosChickenRevInfoDto().setListMosChiCkenInfo(null);
         return null;
     }
    /**
     * 
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }
    /**
     * オーナー選択処理
     * @return
     */
    public String onerSearch(){
        // オーナ情報Dto初期化 & 遷移元情報セット & 初期処理起動フラグON
        getOwnerSearchDto().clear();
        getOwnerSearchDto().setNavigationCase(condition_VIEW_ID);
        getOwnerSearchDto().setInitFlag(true);
        getOwnerSearchDto().setWindowId(getMosChickenRevInfoDto().getWindowId());
        //オーナー選択.遷移情報を有効に設定。
        getOwnerSearchDto().setNeedReturnKind(true);
        // 選択された会社コードセット
        List listCompany = new ArrayList();
        listCompany.add(getMosChickenRevInfoDto().getCompanyCd());
        getOwnerSearchDto().setRCompanyCdList(listCompany);

        getMosChickenRevInfoDto().setInitShoriFlg("0");
        return onerSearch_VIEW_ID;
    }
    
    
    /**
     * 条件画面設定処理
     * @throws Exception
     */
    private void initShori() throws Exception {
        List titleList = getMosChickenRevCondMosChickenTitleLogic().execute(getMosChickenRevInfoDto());
        if (titleList == null || titleList.size() == 0) {
        	getMosChickenRevInfoDto().setInitShoriFlg("1");
            throw new NoTargetException("現在予約入力期間中の対象キャンペーン");
        }
        getMosChickenRevInfoDto().setListCamp(titleList);
        int titleIndex = 0;
        if (!getPullDownMenuDto().isClearFlg()) {
            for(int i=0;titleList.size()>i;i++){
                MstMosChikenFromTo titleListIndex = (MstMosChikenFromTo)titleList.get(i);
                if(titleListIndex.getCkanriNo().equals(getMosChickenRevInfoDto().getCkanriNo())){
                    titleIndex = i;
                    break;
                }
            }
        }
        MstMosChikenFromTo resultTitleList = (MstMosChikenFromTo)titleList.get(titleIndex);
        List listDate = new ArrayList();
        //２．日付コンバーターをインスタンス化する。
        DateFormatter dateCnvt = new DateFormatter();
        String targetFrom = resultTitleList.getTargetFrom();
        String targetTo = resultTitleList.getTargetTo();
        String setDate = targetTo;
        //日付をリストへ格納していきます。
        for(int d=0; setDate.compareTo(targetFrom) >= 0; d++){
            SelectItem entityCode = new SelectItem();
            //Valueフィールド値設定
            entityCode.setValue(setDate);
            //ラベルフィールド値設定
            entityCode.setLabel((String)dateCnvt.format(setDate, true));
            listDate.add(d, entityCode);
            try{
                setDate = DateManager.getPrevDate(setDate, 1);
            }catch(Exception e){
                throw new FtlSystemException("予約入力初期画面用アクション実行クラス条件画面設定処理のお渡し日生成");
            }
        }
        //お渡し日の初期値は空白
        getMosChickenRevInfoDto().setReservDate("");
        getMosChickenRevInfoDto().setListDate(listDate);
    }
    /**
     * 店リストを取得する
     * @throws Exception
     */
    private void getMiseList() throws Exception {
        List miseList  = getMosChickenRevCondMiseListLogic().execute(getMosChickenRevInfoDto());
        
        if (miseList == null || miseList.size() == 0) {
           throw new NotExistException("対象店舗");
        }
        getMosChickenRevInfoDto().setListOnerMise(miseList);
        CodMiseList cdMiseList = (CodMiseList)miseList.get(0);
        getMosChickenRevInfoDto().setMiseCd(cdMiseList.getMiseCd());
        if(miseList.size() == 1){
            getMosChickenRevInfoDto().setMiseNmKj(cdMiseList.getMiseNameKj());
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
     * 入力チェック
     */
    private void validate() {
        // オーナーコード必須チェック
    	String reserveDate = getMosChickenRevInfoDto().getReservDate();
    	
    	//オーナーコード未入力
		if (reserveDate == null || reserveDate.equals("")) {
			throw new NoInputException("お渡し日");
		}
    }

    /**
     * BIRD日付情報取得処理
     * @return birdDateInfo を戻します。
     */
    private BirdDateInfo getBirdDateInfo() {
        return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }

    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }

    /**
     * mosChicenRevInfoDtoを返す
     * @return mosChicenRevInfoDto
     */ 
    public MosChickenRevInfoDto getMosChickenRevInfoDto() {
        return mosChickenRevInfoDto;
    }

    /**
     * mosChicenRevInfoDtoを設定
     * @param mosChicenRevInfoDto
     */
    public void setMosChickenRevInfoDto(MosChickenRevInfoDto mosChickenRevInfoDto) {
        this.mosChickenRevInfoDto = mosChickenRevInfoDto;
    }


    /**
     * mosChickenRevCondMiseListLogicを戻す
     * @return mosChickenRevCondMiseListLogic
     */
    public CondMiseListLogicChickenRevLogic getMosChickenRevCondMiseListLogic() {
        return mosChickenRevCondMiseListLogic;
    }

    /**
     * mosChickenRevCondMiseListLogicを設定
     * @param mosChickenRevCondMiseListLogic
     */
    public void setMosChickenRevCondMiseListLogic(
            CondMiseListLogicChickenRevLogic mosChickenRevCondMiseListLogic) {
        this.mosChickenRevCondMiseListLogic = mosChickenRevCondMiseListLogic;
    }

    /**
     * mosChickenRevCondMosChickenTitleLogicを戻す
     * @return mosChickenRevCondMosChickenTitleLogic
     */
    public CondMosChickenTitleLogic getMosChickenRevCondMosChickenTitleLogic() {
        return mosChickenRevCondMosChickenTitleLogic;
    }

    /**
     * mosChickenRevCondMosChickenTitleLogicを設定
     * @param mosChickenRevCondMosChickenTitleLogic
     */
    public void setMosChickenRevCondMosChickenTitleLogic(
            CondMosChickenTitleLogic mosChickenRevCondMosChickenTitleLogic) {
        this.mosChickenRevCondMosChickenTitleLogic = mosChickenRevCondMosChickenTitleLogic;
    }
    /**
     * mosChickenOwnerSearchDtoを戻す
     * @return mosChickenOwnerSearchDto
     */
    public OwnerSearchDto getOwnerSearchDto() {
        return ownerSearchDto;
    }
    /**
     * mosChickenOwnerSearchDtoを設定
     * @param mosChickenOwnerSearchDto
     */
    public void setOwnerSearchDto(OwnerSearchDto ownerSearchDto) {
        this.ownerSearchDto = ownerSearchDto;
    }
    /**
     * pullDownMenuDtoの設定
     * @return pullDownMenuDto を戻します。
     */
    private PullDownMenuDto getPullDownMenuDto() {
        return (PullDownMenuDto) getS2Container().getComponent(PullDownMenuDto.class);
    }
    /**
     * mosChickenRevCodeVerifierを戻す
     * @return mosChickenRevCodeVerifier
     */
    public CodeVerifier getMosChickenRevCodeVerifier() {
        return mosChickenRevCodeVerifier;
    }
    /**
     * mosChickenRevCodeVerifierを設定
     * @param mosChickenRevCodeVerifier
     */
    public void setMosChickenRevCodeVerifier(CodeVerifier mosChickenRevCodeVerifier) {
        this.mosChickenRevCodeVerifier = mosChickenRevCodeVerifier;
    }
    /**
     * mosChickenRevInfoLogicを戻す
     * @return mosChickenRevInfoLogic
     */
    public MosChickenRevInfoLogic getMosChickenRevInfoLogic() {
        return mosChickenRevInfoLogic;
    }
    /**
     * mosChickenRevInfoLogicを設定
     * @param mosChickenRevInfoLogic
     */
    public void setMosChickenRevInfoLogic(
            MosChickenRevInfoLogic mosChickenRevInfoLogic) {
        this.mosChickenRevInfoLogic = mosChickenRevInfoLogic;
    }
}
