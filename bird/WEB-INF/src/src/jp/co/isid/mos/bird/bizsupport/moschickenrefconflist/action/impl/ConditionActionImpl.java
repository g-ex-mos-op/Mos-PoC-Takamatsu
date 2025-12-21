package jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.action.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.action.ConditionAction;
import jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.dto.MosChickenRefConfListCondDto;
import jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.dto.MosChickenRefConfListDto;
import jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.entity.CodMiseList;
import jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.entity.MstMosChikenFromTo;
import jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.logic.CondMiseListLogicChickenRevLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenrefconflist.logic.CondMosChickenTitleLogic;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * 予約販売確認表
 * 初期画面用アクション実行クラス
 * @author inazawa
 * 2006/10/16
 */



public class ConditionActionImpl implements ConditionAction {
    /* DTO【モスチキン予約】*/
    private MosChickenRefConfListDto mosChickenRefConfListDto;
    /* DTO【モスチキン条件予約】*/
    private MosChickenRefConfListCondDto mosChickenRefConfListCondDto;
    /* DTO【オーナー選択】*/
    private OwnerSearchDto ownerSearchDto;

    /*ユーザータイプ*/
    private static final String HONBU_USER = "01";
    private static final String ONER_USER = "02";
    private static final String TENPO_USER = "03";
    /* モス会社コード */
    private static final String COMPANY_CD_MOS = "00";
    /* アクションID */
    public static final String initialize_ACTION_ID     = "BBS020A01";
    public static final String honbuUserBack_ACTION_ID  = "BBS020A04";
    public static final String onerSearch_ACTION_ID     = "BBS020A05";
    public static final String changeTitle_ACTION_ID    = "BBS020A06";
    public static final String honbuExec_ACTION_ID      = "BBS020A07";
    /* VIEW_ID */
    public static final String VIEW_ID            = "BBS020";
    public static final String condition_VIEW_ID  = "BBS020V01";
    public static final String onerSearch_VIEW_ID      = "BCO006V01";
    /* ロジック【オーナー保有店舗】*/
    private CondMiseListLogicChickenRevLogic mosChickenRefConfCondMiseListLogicImpl;
    /* ロジック【モスチキン予約入力条件設定】*/
    private CondMosChickenTitleLogic mosChickenRefConfCondMosChickenTitleLogicImpl;
    /* ロジック【モスチキン予約情報取得】*/
    /** コードバリデータ */
    private CodeVerifier mosChickenrefconflistCodeVerifier;
                          
    /**
     * 初期処理 アクション
     * 
     * @return null 画面ID
     * @throws Exception 
     */
    public String initialize() throws Exception {
        
        if (getPullDownMenuDto().isClearFlg()) {
            //newWindowIDを設定
            getMosChickenRefConfListCondDto().updateWindowid();

            getMosChickenRefConfListDto().clear();
            getMosChickenRefConfListCondDto().clear();
            //ユーザーIDの設定
            getMosChickenRefConfListCondDto().setUserId(getBirdUserInfo().getMstUser().getUser_id());
            //ユーザータイプの設定
            getMosChickenRefConfListCondDto().setUserTypeCd(getBirdUserInfo().getMstUser().getUserTypeCd());
            //会社コードの設定
            getMosChickenRefConfListDto().setRCompanyCd(COMPANY_CD_MOS);
            //システム日付
            getMosChickenRefConfListCondDto().setSysDate(getBirdDateInfo().getSysDate());
            if(!getMosChickenRefConfListCondDto().getUserTypeCd().equals(HONBU_USER)){
                initShori();
                getMosChickenRefConfListCondDto().setInitShoriFlg("1");
                //条件画面設定メソッド
            }else{
                if(getMosChickenRefConfListCondDto().getInitShoriFlg() == null){
                    getMosChickenRefConfListCondDto().setInitShoriFlg("0");
                }
            }
            getPullDownMenuDto().setClearFlg(false);    
            getOwnerSearchDto().setOnerCd(null);
            getMosChickenRefConfListDto().setBirdUserInfo(getBirdUserInfo());

        }
        if(ownerSearchDto.isActionFlag()){
            if(getOwnerSearchDto().getOnerCd() != null){
                getMosChickenRefConfListCondDto().setOnerCd(getOwnerSearchDto().getOnerCd());
                initShori();
                getMosChickenRefConfListCondDto().setInitShoriFlg("1");
                getMosChickenRefConfListCondDto().setOnerCd(getOwnerSearchDto().getOnerCd());
                ownerSearchDto.setActionFlag(false);
            }
        }
        return null;
    }
    /**
     * 条件画面設定処理
     * @throws Exception
     */
    
    private void initShori() throws Exception {
        List titleList = getMosChickenRefConfCondMosChickenTitleLogicImpl().execute(getMosChickenRefConfListCondDto());
        DateFormatter formatter = new DateFormatter();
       if (titleList == null || titleList.size() == 0) {
           getMosChickenRefConfListCondDto().setOnerCd(null);
           getMosChickenRefConfListCondDto().setTitleCnt(0);
       }else{
            getMosChickenRefConfListCondDto().setListCamp(titleList);
            getMosChickenRefConfListCondDto().setTitleCnt(titleList.size());
            List miseList  = getMosChickenRefConfCondMiseListLogicImpl().execute(getMosChickenRefConfListCondDto());
            
            if (miseList == null || miseList.size() == 0) {
               throw new NotExistException("オーナー");
            }
            getMosChickenRefConfListCondDto().setListOnerMise(miseList);
            getMosChickenRefConfListCondDto().setMiseCnt(miseList.size());
            CodMiseList cdMiseList = (CodMiseList)miseList.get(0);
            getMosChickenRefConfListDto().setMiseCd(cdMiseList.getMiseCd());
            if(miseList.size() == 1){
                getMosChickenRefConfListDto().setMiseNmKj(cdMiseList.getMiseNameKj());
            }
            int titleIndex = 0;
            if(titleList.size() == 1){
                MstMosChikenFromTo titleListIndex = (MstMosChikenFromTo)titleList.get(0);
                getMosChickenRefConfListDto().setTitle(titleListIndex.getTitle());
            }else{
                if (!getPullDownMenuDto().isClearFlg()) {
                    for(int i=0;titleList.size()>i;i++){
                        MstMosChikenFromTo titleListIndex = (MstMosChikenFromTo)titleList.get(i);
                        if(titleListIndex.getCkanriNo().equals(getMosChickenRefConfListDto().getCkanriNo())){
                            titleIndex = i;
                            break;
                        }
                    }
                }
            }
            MstMosChikenFromTo resultTitleList = (MstMosChikenFromTo)titleList.get(titleIndex);
            getMosChickenRefConfListDto().setCkanriNo(resultTitleList.getCkanriNo());
            int days = 0;
            List listDate = new ArrayList();
            if(Integer.parseInt(resultTitleList.getTargetTo()) == Integer.parseInt(resultTitleList.getTargetFrom())){
                days++;
                getMosChickenRefConfListDto().setReserveDate(resultTitleList.getTargetTo());
            }else{
                for (int date = Integer.parseInt(resultTitleList.getTargetTo());
                            date>=Integer.parseInt(resultTitleList.getTargetFrom());date--) {
                    days++;
                    String strNendo = String.valueOf(formatter.format(String.valueOf(date),DateFormatter.PATTERN_SLASH,DateFormatter.DATE_TYPE_YMD));
                    if(strNendo.length() == 10){
                        SelectItem taishoDate = new SelectItem(strNendo);
                        listDate.add(taishoDate);
                    }
                    if(date == Integer.parseInt(resultTitleList.getTargetFrom())){
                        getMosChickenRefConfListDto().setReserveDate(resultTitleList.getTargetFrom());
                    }
                }
                getMosChickenRefConfListDto().setReserveDate(String.valueOf(formatter.format(String.valueOf(getBirdDateInfo().getSysDate()),DateFormatter.PATTERN_SLASH,DateFormatter.DATE_TYPE_YMD)));
                getMosChickenRefConfListCondDto().setListDate(listDate);
            }
            getMosChickenRefConfListCondDto().setCnpDays(days);
            List listHh = new ArrayList();
            CodeFormatter dateFormatter = new CodeFormatter(2);
            dateFormatter.setFormatPattern("00");
            for(int j=0;j<25;j++){
                String strHh = String.valueOf(dateFormatter.format(String.valueOf(j), true));
                strHh = strHh + ":00";
                SelectItem hour = new SelectItem(strHh);
                listHh.add(hour);
            }
            getMosChickenRefConfListCondDto().setListReserveFrom(listHh);
            List listMm = new ArrayList();
            for(int k=0;k<25;k++){
                String strMm = String.valueOf(dateFormatter.format(String.valueOf(k), true));
                strMm = strMm + ":00";
                SelectItem min = new SelectItem(strMm);
                listMm.add(min);
                if(k==24){
                    getMosChickenRefConfListDto().setReserveHourTo(strMm);
                }
            }
            getMosChickenRefConfListCondDto().setListReserveTo(listMm);
            getMosChickenRefConfListCondDto().setInitShoriFlg("1");
       }
    }
    /**
     * 本部ユーザー実行ボタン押下
     * @throws Exception 
     */
     public String honbuExec() throws Exception{
        if(isNull(getMosChickenRefConfListCondDto().getOnerCd())){
            throw new NoInputException("オーナーコード","onerCd",null);
        }else{
            if(!getMosChickenrefconflistCodeVerifier().validate(getMosChickenRefConfListCondDto().getOnerCd().trim())){
                throw new InvalidInputException("オーナーコード","onerCd",null);
            }
        }   
            
        initShori();
        getMosChickenRefConfListCondDto().setInitShoriFlg("1");
        return null;
         
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
      * 本部ユーザーの初期画面に戻る
      * @return
      */
     public String honbuUserBack(){
        getMosChickenRefConfListDto().clear();
        getMosChickenRefConfListCondDto().setInitShoriFlg("0");
        getPullDownMenuDto().setClearFlg(true);    
        return null;
     }

    /**
      *検索画面押下時
      */
     public String exec(){
         
         return null;
     }
     /**
      * キャンペーンプルダウン変更処理
     * @throws Exception 
      */
     public String changeTitle() throws Exception {
         initShori();
         getMosChickenRefConfListDto().setListMosChiCkenInfo(null);
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
        getOwnerSearchDto().setWindowId(getMosChickenRefConfListCondDto().getWindowId());
        // 選択された会社コードセット
        List listCompany = new ArrayList();
        listCompany.add(COMPANY_CD_MOS);
        getOwnerSearchDto().setRCompanyCdList(listCompany);
        //オーナーコードを保持
        //getOwnerSearchDto().setOnerCd(getMosChickenRevInfoDto().getOnerCd());
        getMosChickenRefConfListCondDto().setInitShoriFlg("0");
        return onerSearch_VIEW_ID;
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
     * mosChickenRefConfListDtoを取得
     * @return mosChickenRefConfListDto
     */
    public MosChickenRefConfListDto getMosChickenRefConfListDto() {
        return mosChickenRefConfListDto;
    }
    /**
     * mosChickenRefConfListDtoを設定
     * @param mosChickenRefConfListDto
     */
    public void setMosChickenRefConfListDto(
            MosChickenRefConfListDto mosChickenRefConfListDto) {
        this.mosChickenRefConfListDto = mosChickenRefConfListDto;
    }
    /**
     * mosChickenRefConfCondMiseListLogicImplを取得
     * @return mosChickenRefConfCondMiseListLogicImpl
     */
    public CondMiseListLogicChickenRevLogic getMosChickenRefConfCondMiseListLogicImpl() {
        return mosChickenRefConfCondMiseListLogicImpl;
    }
    /**
     * mosChickenRefConfCondMiseListLogicImplを設定
     * @param mosChickenRefConfCondMiseListLogicImpl
     */
    public void setMosChickenRefConfCondMiseListLogicImpl(
            CondMiseListLogicChickenRevLogic mosChickenRefConfCondMiseListLogicImpl) {
        this.mosChickenRefConfCondMiseListLogicImpl = mosChickenRefConfCondMiseListLogicImpl;
    }
    /**
     * mosChickenRefConfCondMosChickenTitleLogicImpを取得l
     * @return mosChickenRefConfCondMosChickenTitleLogicImpl
     */
    public CondMosChickenTitleLogic getMosChickenRefConfCondMosChickenTitleLogicImpl() {
        return mosChickenRefConfCondMosChickenTitleLogicImpl;
    }
    /**
     * mosChickenRefConfCondMosChickenTitleLogicImplを設定
     * @param mosChickenRefConfCondMosChickenTitleLogicImpl
     */
    public void setMosChickenRefConfCondMosChickenTitleLogicImpl(
            CondMosChickenTitleLogic mosChickenRefConfCondMosChickenTitleLogicImpl) {
        this.mosChickenRefConfCondMosChickenTitleLogicImpl = mosChickenRefConfCondMosChickenTitleLogicImpl;
    }
    /**
     * mosChickenrefconflistCodeVerifierを戻す
     * @return mosChickenrefconflistCodeVerifier
     */
    public CodeVerifier getMosChickenrefconflistCodeVerifier() {
        return mosChickenrefconflistCodeVerifier;
    }
    /**
     * mosChickenrefconflistCodeVerifierを設定
     * @param mosChickenrefconflistCodeVerifier
     */
    public void setMosChickenrefconflistCodeVerifier(
            CodeVerifier mosChickenrefconflistCodeVerifier) {
        this.mosChickenrefconflistCodeVerifier = mosChickenrefconflistCodeVerifier;
    }
    /**
     * mosChickenRefConfListCondDtoを取得
     * @return mosChickenRefConfListCondDto
     */
    public MosChickenRefConfListCondDto getMosChickenRefConfListCondDto() {
        return mosChickenRefConfListCondDto;
    }
    /**
     * mosChickenRefConfListCondDtoを設定
     * @param mosChickenRefConfListCondDto
     */
    public void setMosChickenRefConfListCondDto(
            MosChickenRefConfListCondDto mosChickenRefConfListCondDto) {
        this.mosChickenRefConfListCondDto = mosChickenRefConfListCondDto;
    }


}
