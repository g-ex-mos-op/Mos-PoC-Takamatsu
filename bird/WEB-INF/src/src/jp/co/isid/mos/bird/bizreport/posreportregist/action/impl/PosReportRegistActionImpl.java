package jp.co.isid.mos.bird.bizreport.posreportregist.action.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.posreportregist.action.PosReportRegistAction;
import jp.co.isid.mos.bird.bizreport.posreportregist.common.PosReportRegistCommon;
import jp.co.isid.mos.bird.bizreport.posreportregist.common.PosReportRegistConstants;
import jp.co.isid.mos.bird.bizreport.posreportregist.dto.PosReportRegistDto;
import jp.co.isid.mos.bird.bizreport.posreportregist.entity.UIPosReportMiseInfo;
import jp.co.isid.mos.bird.bizreport.posreportregist.logic.CheckDblPosReportMiseListLogic;
import jp.co.isid.mos.bird.bizreport.posreportregist.logic.CheckInputParamLogic;
import jp.co.isid.mos.bird.bizreport.posreportregist.logic.SetUpInitDataLogic;
import jp.co.isid.mos.bird.common.entity.MstMise;
import jp.co.isid.mos.bird.common.logic.GetMiseLogic;
import jp.co.isid.mos.bird.commonform.misesearch.dto.MiseSearchDto;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.util.MakeSessionKey;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * POS速報設定登録
 * @author Aspac
 *
 * 更新日:2010/11/09 ASPAC Ｐ６対応仕様変更対応
 *      ・新規テーブルBR82RTSM(店舗別リアルタイム集信期間マスタ)を使用し、
 *        全店対象期間設定機能を設けます。
 *      ・最大設定件数(40件)の機能を削除します。
 */
public class PosReportRegistActionImpl implements PosReportRegistAction {
    
    /*アクションID:初期化処理*/
    public static final String initialize_ACTION_ID  = "BBR007A01";
    /*アクションID:店舗検索処理*/
    public static final String searchStore_ACTION_ID = "BBR007A02";
    /*アクションID:行追加処理*/
    public static final String addRow_ACTION_ID      = "BBR007A03";
    /*アクションID:確認ボタン処理*/
    public static final String confirm_ACTION_ID     = "BBR007A04";

    /** BIRD日付情報 */
    private BirdDateInfo birdDateInfo;
    /** BIRDユーザー情報 */
    private BirdUserInfo birdUserInfo;
    /** DTO【POS速報設定】*/
    private PosReportRegistDto posReportRegistDto;
    
    /** BIRD共通DTO【店舗選択情報】*/
    private MiseSearchDto miseSearchDto;
    
    /** BIRD共通LOGIC【店舗情報取得ロジック】*/
    private GetMiseLogic getMiseLogic;
    /** LOGIC【初期表示情報取得設定】*/
    private SetUpInitDataLogic posReportRegistSetUpInitDataLogic;
    /** LOGIC【POS速報設定登録チェックロジック】 */
    private CheckInputParamLogic posReportRegistCheckCheckInputParamLogic;
    /** LOGIC【POS速報設定リスト重複チェックロジック】 */
    private CheckDblPosReportMiseListLogic checkDblPosReportMiseListLogic;
    
    /**
     * セッションキー作成クラス
     */
    private MakeSessionKey makeSessionKey = new MakeSessionKey(); 
    /**
     * selectIndex
     */
    private int selectIndex;
    
    /**
     * selectIndexを取得します。
     * @return selectIndex
     */
    public int getSelectIndex() {
        return selectIndex;
    }
    /**
     * selectIndexを設定します。
     * @param selectIndex
     */
    public void setSelectIndex(int selectIndex) {
        this.selectIndex = selectIndex;
    }


    /**
     * 初期化処理
     */
    public String initialize() {
        

        // コンポーネント取得
        S2Container container = SingletonS2ContainerFactory.getContainer();
        
        // メニューDTO取得
        PullDownMenuDto pullDownMenuDto = (PullDownMenuDto) container.getComponent(PullDownMenuDto.class);
        
        // メニューから遷移
        if (pullDownMenuDto.isClearFlg()) {
            
            // クリアフラグ設定
            pullDownMenuDto.setClearFlg(false);
            
            // ウインドウID生成
            posReportRegistDto.updateWindowid();
            
            // セッションキーを生成し、DTOにセット。
            String sessionKey = makeSessionKey._makeSessionKey();
            posReportRegistDto.setSessionKey(sessionKey);
            posReportRegistDto.setNowSessionKey(sessionKey);
            
            //LOGIC【初期表示情報取得設定】を実行します。
            getPosReportRegistSetUpInitDataLogic().execute(
            		getBirdDateInfo(), getBirdUserInfo(), getPosReportRegistDto());
        }
        
        // 確認画面から登録完了後、遷移
        else if( getPosReportRegistDto().isRegistFlg()) {
            //LOGIC【初期表示情報取得設定】を実行します。
            getPosReportRegistSetUpInitDataLogic().execute(
            		getBirdDateInfo(), getBirdUserInfo(), getPosReportRegistDto());
            
        }
        
        // 店舗検索
        else if (getMiseSearchDto().getReturnKind() != MiseSearchDto.RETURNKIND_INIT){
            // ウインドウID反映
        	getPosReportRegistDto().setWindowId(miseSearchDto.getWindowId());
            
            // セッションキーチェック
            if(!isValidSessionKey()) {
                miseSearchDto.setActionFlg(false);
                return PosReportRegistConstants.VIEW_ID_OPERATION_ERR;
            }
            
            if (getMiseSearchDto().isActionFlg()) {
                // アクションフラグ設定
                getMiseSearchDto().setActionFlg(false);
                
                //店舗情報を取得
                String miseCd = getMiseSearchDto().getMiseCd();
                MstMise mstMise = getGetMiseLogic().execute(
                		getPosReportRegistDto().getPosCompanyCd() , miseCd);
                
                // 取得した店舗情報をDTOに設定
                List list = getPosReportRegistDto().getListPosReport();
                UIPosReportMiseInfo posinfo = (UIPosReportMiseInfo)list.get(getPosReportRegistDto().getSelectIndex());
                posinfo.setMiseCd(miseCd);
                posinfo.setMiseNameKj(mstMise.getMiseNameKj());
                posinfo.setCloseDt(mstMise.getCloseDt());
                posinfo.setCompanyCd(mstMise.getCompanyCd());
            }
        }
        
		// 自画面へ遷移
		return null;
	}

    
    
    /**
     * 確認ボタン
     * @return
     */
    public String confirm() {
        
        // セッションキーチェック
        if(!isValidSessionKey()) {
            return PosReportRegistConstants.VIEW_ID_OPERATION_ERR;
        }
        //----------------------------------
        // POS速報設定登録チェックロジック
        //----------------------------------
        //２．ロジック【入力チェック】を実行します。
        getPosReportRegistCheckCheckInputParamLogic().execute(
        		getBirdDateInfo(), getPosReportRegistDto());
        List listPos = getPosReportRegistDto().getListPosReport();
        //３．新しく変数．List[[登録対象データ]]としてＬｉｓｔを生成します。
        List listRegistData = new ArrayList(0);
        
        //---------------------------------------------------------------
        // 空レコード削除 & POS速報設定リストの処理ステータスを初期化
        //---------------------------------------------------------------
        //４．DTO【POS速報設定情報保持】．List[[POS速報店舗]]の件数分、for分で下記の処理を行います。
        for (Iterator ite = listPos.iterator(); ite.hasNext();) {
            //for-1.変数．[POS速報店舗]へite.next()のエンティティを設定します。
        	UIPosReportMiseInfo posinfo = (UIPosReportMiseInfo) ite.next();
            if(PosReportRegistCommon.isNull(posinfo.getMiseCd())) {
            	ite.remove();
                continue;
            }
            //for-2.変数．[POS速報店舗]．店コードに値が設定されている場合は、下記の処理を行います。
            // Ⅰ変数．[POS速報店舗]．処理ステータスへ””（ブランク）を設定します。
            posinfo.setProcState("");
            // Ⅱ変数．List[[登録対象データ]]へ変数．[POS速報店舗]を格納します。
            listRegistData.add(posinfo);
        }
        //５．DTO【POS速報設定情報保持】．List[[登録対象データ]]へ変数．List[[登録対象データ]]を設定します。
        getPosReportRegistDto().setListRegistData(listRegistData);
        
        //６．DTO【POS速報設定情報保持】．List[[登録対象データ]]の
        //    全てのエンティティ．確認画面用ＳＥＱＮｏへ番号を設定します。
        PosReportRegistCommon.setSeqNoConfirm(listRegistData);
        
        
        //７．DTO【POS速報設定情報保持】．List[[登録対象データ]]の
        //    全ての[新規用エンティティ]の店舗名称へ名称を設定します。
        MstMise mstMise = new MstMise();
        for (Iterator ite = listRegistData.iterator(); ite.hasNext();) {
            UIPosReportMiseInfo posinfo = (UIPosReportMiseInfo) ite.next();
            if(posinfo.isInsertFlg()) {
            	//７－１．BIRD共通LOGIC【店舗情報】を実行し、[店舗マスタ情報]を取得します。
                mstMise = getGetMiseLogic().execute(getPosReportRegistDto().getPosCompanyCd(),posinfo.getMiseCd());
                //７－２．処理７－１で取得した[店舗マスタ情報]から下記の値を設定します。
                posinfo.setMiseNameKj(mstMise.getMiseNameKj().trim());
                posinfo.setCompanyCd(mstMise.getCompanyCd());
            }
        }
        
        
        //----------------------------------------
        // POS速報設定リスト重複チェックロジック
        //----------------------------------------
        boolean chkDbl;
        //８．ロジック【POS速報店舗リスト重複チェック】を実行し、重複存在チェックフラグを取得します。
        chkDbl = getCheckDblPosReportMiseListLogic().execute(
        		listRegistData, getBirdDateInfo().getSysDate());
        //９．DTO【POS速報設定情報保持】．重複存在チェックフラグへ
        //    ロジックの戻り値重複存在チェックフラグを設定します。
        getPosReportRegistDto().setChkDblFlg(chkDbl);
        
        //10．確認画面ViewIDを返す
        return PosReportRegistConstants.VIEWID_CONFIRM;
    }
    
    
    /**
     * 行追加
     * @return
     */
    public String addRow() {
        
        // セッションキーチェック
        if(!isValidSessionKey()) {
            return PosReportRegistConstants.VIEW_ID_OPERATION_ERR;            
        }
        PosReportRegistCommon.addRowEntity(
        		getPosReportRegistDto().getListPosReport()
            , getBirdDateInfo().getSysDate());
	        getPosReportRegistDto().setAddRow(true);
	        getPosReportRegistDto().setAddRowIndex(
	        		getPosReportRegistDto().getListPosReport().size()-1);
        return null;
    }
    
    
    /**
     * 店舗検索ボタン
     * 
     * 更新日：2010/11/10 ASPAC xkinu 会社コードリスト設定ロジックを追加。
     * @return 店舗検索VIEWID
     */
    public String searchStore() {
        
        // セッションキーチェック
        if(!isValidSessionKey()) {
            return PosReportRegistConstants.VIEW_ID_OPERATION_ERR;            
        }
        miseSearchDto.setWindowId(posReportRegistDto.getWindowId());
        // 会社コードリストをセット
        List listCompany = new ArrayList();
        listCompany.add(getPosReportRegistDto().getPosCompanyCd());
        getMiseSearchDto().setRCompanyCdList(listCompany);
        getMiseSearchDto().setActionFlg(true);
        getMiseSearchDto().setInitialFlag(true);
        getMiseSearchDto().setNeedReturnKind(true);
        getMiseSearchDto().setNavigationCase(PosReportRegistConstants.VIEWID_REGIST);
        getPosReportRegistDto().setSelectIndex(getSelectIndex());
        
        return PosReportRegistConstants.VIEWID_MISESEARCH;
    }
    
    /**
     * セッションキーチェック
     * @return セッションキーチェック結果
     */
    private boolean isValidSessionKey(){
         boolean result = makeSessionKey.isValidSessionKey(
                 posReportRegistDto.getNowSessionKey(), posReportRegistDto.getSessionKey());
         
         return result;
    }
    
    
    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }
    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }
    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }
    /**
     * 店舗情報ロジックを取得します。
     * @return 店舗情報ロジック
     */
    public GetMiseLogic getGetMiseLogic() {
        return getMiseLogic;
    }
    /**
     * 店舗情報ロジックを設定します。
     * @param 店舗情報ロジック
     */
    public void setGetMiseLogic(GetMiseLogic getMiseLogic) {
        this.getMiseLogic = getMiseLogic;
    }
    /**
     * POS速報設定リスト重複チェックロジックを取得します。
     * @return POS速報設定リスト重複チェックロジック
     */
    public CheckDblPosReportMiseListLogic getCheckDblPosReportMiseListLogic() {
        return checkDblPosReportMiseListLogic;
    }
    /**
     * POS速報設定リスト重複チェックロジックを設定します。
     * @param POS速報設定リスト重複チェックロジック
     */
    public void setCheckDblPosReportMiseListLogic(
            CheckDblPosReportMiseListLogic checkDblPosReportMiseListLogic) {
        this.checkDblPosReportMiseListLogic = checkDblPosReportMiseListLogic;
    }
    /**
     * POS速報設定DTOを取得します。
     * @return POS速報設定DTO
     */
    public PosReportRegistDto getPosReportRegistDto() {
        return posReportRegistDto;
    }
    /**
     * POS速報設定DTOを設定します。
     * @param POS速報設定DTO
     */
    public void setPosReportRegistDto(PosReportRegistDto posReportRegistDto) {
        this.posReportRegistDto = posReportRegistDto;
    }
    /**
     * 店舗情報DTOを取得します。
     * @return 店舗情報DTO
     */
    public MiseSearchDto getMiseSearchDto() {
        return miseSearchDto;
    }
    /**
     * 店舗情報DTOを設定します。
     * @return 店舗情報DTO
     */
    public void setMiseSearchDto(MiseSearchDto miseSearchDto) {
        this.miseSearchDto = miseSearchDto;
    }
    /**
     * LOGIC【初期表示情報取得設定】取得処理
     * @return
     */
	public SetUpInitDataLogic getPosReportRegistSetUpInitDataLogic() {
		return posReportRegistSetUpInitDataLogic;
	}
	/**
	 * LOGIC【初期表示情報取得設定】設定処理
	 * @param posReportRegistSetUpInitDataLogic
	 */
	public void setPosReportRegistSetUpInitDataLogic(
			SetUpInitDataLogic posReportRegistSetUpInitDataLogic) {
		this.posReportRegistSetUpInitDataLogic = posReportRegistSetUpInitDataLogic;
	}
	public CheckInputParamLogic getPosReportRegistCheckCheckInputParamLogic() {
		return posReportRegistCheckCheckInputParamLogic;
	}
	public void setPosReportRegistCheckCheckInputParamLogic(
			CheckInputParamLogic posReportRegistCheckCheckInputParamLogic) {
		this.posReportRegistCheckCheckInputParamLogic = posReportRegistCheckCheckInputParamLogic;
	}


    
    
}
