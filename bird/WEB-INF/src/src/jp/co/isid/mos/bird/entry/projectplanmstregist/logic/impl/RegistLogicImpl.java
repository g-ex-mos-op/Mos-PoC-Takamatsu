package jp.co.isid.mos.bird.entry.projectplanmstregist.logic.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.entry.projectplanmstregist.dao.MstKanriDao;
import jp.co.isid.mos.bird.entry.projectplanmstregist.dao.MstKanriDateDao;
import jp.co.isid.mos.bird.entry.projectplanmstregist.dao.MstKanriNoticeDao;
import jp.co.isid.mos.bird.entry.projectplanmstregist.entity.MstKanri;
import jp.co.isid.mos.bird.entry.projectplanmstregist.entity.MstKanriDate;
import jp.co.isid.mos.bird.entry.projectplanmstregist.entity.MstKanriNotice;
import jp.co.isid.mos.bird.entry.projectplanmstregist.entity.UICourseInfo;
import jp.co.isid.mos.bird.entry.projectplanmstregist.logic.RegistLogic;
import jp.co.isid.mos.bird.entry.projectplanmstregist.util.ProjectPlanMstRegistUtil;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteWithReasonException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NoTargetException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 
 * 事業方針説明会マスタ登録
 * 【事業方針説明会マスタ系情報の登録】ロジック
 * 
 * @author xkinu
 *
 */
public class RegistLogicImpl implements RegistLogic {
    /** ロジックID */
    public static final String LOGIC_ID = ProjectPlanMstRegistUtil.SCREEN_ID+"L05";
    /**
     * パラメーターKey：ユーザー情報
     */
    public static final String PK_USERINFO = "userInfo";
    /**
     * パラメーターKey： 対象事業方針説明会情報エンティティー<br/>
     * <br/>
     * UICourseInfoエンティティーです。<br/>
     * 画面上で編集されたデータを保持しているエンティティーです。<br/>
     *
     */
    public static final String PK_ENTITY_COURSE = "entityCourse";
    /**
     * パラメーターKey： 対象事業方針説明会マスタ情報リスト
     */
    public static final String PK_LIST_MST = "listMst";
    /**
     * パラメーターKey： 対象事業方針説明会日付マスタ情報リスト
     */
    public static final String PK_LIST_MST_DATE = "listMstDate";
    /**
     * パラメーターKey： 対象事業方針説明会文言マスタ情報リスト
     */
    public static final String PK_LIST_MST_NOTICE = "listMstNotice";
    /**
     * パラメーターKey：処理タイプ
     */
    public static final String PK_MODE_TYPE= "modeType";
    
    /* DAO【事業方針説明会マスタ】(BR17ENTL)*/
    private MstKanriDao mstKanriDao;
    /* DAO【事業方針説明会日付マスタ】(BR18ENTD) */
    private MstKanriDateDao mstKanriDateDao;
    /* DAO【事業方針説明会文言マスタ】(BR51ENCI) */
    private MstKanriNoticeDao mstKanriNoticeDao;
 
    private static final String entryKaiDefault = "001";
    /**
     * 実行処理
     * 
     * １．事前条件処理を実行する。
     * 
     * ２．処理タイプごとに登録処理を実行します。
     *     A．新規登録処理を実行する。
     *     B．更新登録処理を実行する。
     *     C．削除登録処理を実行する。
     * 
     * ３．nullをリターンする。
     */
    public List execute(Map params) {
        //１．事前条件処理を実行する。
        validate(params);
        //パラメーター.処理タイプ
        String modeType = (String)params.get(PK_MODE_TYPE);
        //２．処理タイプごとに登録処理を実行します。
        if(ProjectPlanMstRegistUtil.MODETYPE_INSERT.equals(modeType)) {
            //A．新規登録処理を実行する。
            executeInsert(params);
        }
        else if(ProjectPlanMstRegistUtil.MODETYPE_UPDATE.equals(modeType)) {
            //B．更新登録処理を実行する。
            executeUpdate(params);
        }
        else if(ProjectPlanMstRegistUtil.MODETYPE_DELETE.equals(modeType)){
            //C．削除登録処理を実行する。
            executeDelete(params);
        }
        //３．nullをリターンする。
        return null;
    }
    /**
     * 事前条件処理
     * @param map
     * @throws ApplicationException
     */
    private void validate(final Map params) {
        BirdUserInfo userInfo = (BirdUserInfo)params.get(PK_USERINFO);
        if(userInfo == null){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("ユーザー情報");
        }
        //処理対象　対象事業方針説明会編集情報
        UICourseInfo entityInputData = (UICourseInfo)params.get(PK_ENTITY_COURSE);
        if (entityInputData == null) {
            //MSG【E0203】ありません。
            //処理対象が存在しない。
            throw new NoTargetException("対象事業方針説明会編集情報");
        }
        String modeType = (String)params.get(PK_MODE_TYPE);
        if(ProjectPlanMstRegistUtil.isNull(modeType)){
            throw new MissingDataException("処理タイプ");
        }
        if(modeType.equals(ProjectPlanMstRegistUtil.MODETYPE_UPDATE)){
            //処理対象　事業方針説明会マスタ情報
            List listMst = (List)params.get(PK_LIST_MST);
            if (listMst == null || listMst.size() < 1) {
                //MSG【E0203】ありません。
                //処理対象が存在しない。
                throw new NoTargetException("事業方針説明会マスタ情報");
            }
            
            //処理対象　事業方針説明会日付マスタ情報
            List listMstDate = (List)params.get(PK_LIST_MST_DATE);
            if (listMstDate == null || listMstDate.size() < 1) {
                //MSG【E0203】ありません。
                //処理対象が存在しない。
                throw new NoTargetException("事業方針説明会日付マスタ情報");
            }
            //処理対象　事業方針説明会文言マスタ情報
            List listMstNotice = (List)params.get(PK_LIST_MST_NOTICE);
            if (listMstNotice == null || listMstNotice.size() < 1) {
                //MSG【E0203】ありません。
                //処理対象が存在しない。
                throw new NoTargetException("事業方針説明会文言マスタ情報");
            }
        }
        if(modeType.equals(ProjectPlanMstRegistUtil.MODETYPE_DELETE)){
            //処理対象　事業方針説明会マスタ情報
            List listMst = (List)params.get(PK_LIST_MST);
            if (listMst == null || listMst.size() < 1) {
                //MSG【E0203】ありません。
                //処理対象が存在しない。
                throw new NoTargetException("事業方針説明会マスタ情報");
            }
            
        }
    }
    /**
     * 新規登録
     * 
     * ■登録対象テーブル
     * ・BR17ENTL：エントリーマスタ管理
     * ・BR18ENTD：エントリー日付管理
     * ・BR51ENCI：エントリー文言情報
     * 
     * @param params
     */
    private void executeInsert(Map params) {
        //パラメーター.BIRDユーザー情報
        BirdUserInfo userInfo = (BirdUserInfo)params.get(PK_USERINFO);
        //パラメーター.対象事業方針説明会情報
        UICourseInfo entityInputData = (UICourseInfo)params.get(PK_ENTITY_COURSE);
        
        //１．処理対象の変数を生成する。
        //１－１．タイムスタンプを生成する。
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
        //１－２．パラメーター.BIRDユーザー情報からユーザーIDを取得する。
        String userId = userInfo.getUserID();
        //１－３．ロジックIDの先頭7バイトの文字をPGMとして生成する。
        String userPgm = LOGIC_ID.substring(0, 7);
        
        //２．DAO【事業方針説明会マスタ】(BR17ENTL)．新規用の管理対象期間情報を取得 を実行し、登録対象[[事業方針説明会マスタ]]を取得する。
        //パラメーター.対象事業方針説明会情報.エントリーコード
        entityInputData.setEntryCd(ProjectPlanMstRegistUtil.ENTRY_CD);
        String entryCd = entityInputData.getEntryCd();
        try {
            //パラメーター.対象事業方針説明会情報.エントリーコード
            String execDt = entityInputData.getExecDt();
            //開催日からエントリー年の値を設定します。
            entityInputData.setEntryYear(DateManager.getCurrentYear(execDt));
        }
        catch(Exception ex){
            throw new FtlSystemException("事業方針説明会マスタ登録 登録ロジック新規登録", "", ex);
        }
        String entryYear = entityInputData.getEntryYear();
        //デフォルトエントリー回値
        String entryKai = entryKaiDefault;
        List newList = getProjectPlanMstRegistMstKanriDao().selectNewEntry(entryCd, entryYear, entryKaiDefault);
               
        //３．処理２の実行結果へパラメーター.対象事業方針説明会情報のデータを設定します。
        //３－１．処理２で取得した登録対象[[事業方針説明会マスタ]]の１行目のエンティティー[事業方針説明会マスタ]を取得する。
        MstKanri mstKanri = (MstKanri)newList.get(0);
        mstKanri.setEntryCd(entryCd);
        mstKanri.setEntryYear(entryYear);
        entityInputData.setEntryKai(mstKanri.getEntryKai());
        //３－２．処理３－１で取得した[事業方針説明会マスタ]へパラメーター.対象事業方針説明会情報のデータを設定します。
        //登録対象[事業方針説明会マスタ].説明会名称 = パラメーター.対象事業方針説明会情報.説明会名称
        //登録対象[事業方針説明会マスタ].委任状 = パラメーター.対象事業方針説明会情報.委任状
        settingDataMstKanri(entityInputData, mstKanri, userId, userPgm);
        //登録対象[事業方針説明会マスタ].登録ユーザー = 処理１－２.ユーザーID
        mstKanri.setFirstUser(userId);
        //登録対象[事業方針説明会マスタ].登録PGM = 処理１－３.PGM
        mstKanri.setFirstPgm(userPgm);
        //登録対象[事業方針説明会マスタ].登録タイムスタンプ = 処理１－１.タイムスタンプ
        mstKanri.setFirstTmsp(currentTimestamp);
        //登録対象[事業方針説明会マスタ].更新ユーザー = 処理１－２.ユーザーID
        //登録対象[事業方針説明会マスタ].更新PGM = 処理１－３.PGM
        //登録対象[事業方針説明会マスタ].更新タイムスタンプ = 処理１－１.タイムスタンプ
        mstKanri.setLastTmsp(currentTimestamp);

        //４．DAO【事業方針説明会マスタ】(BR17ENTL)．新規登録 を実行する。
        getProjectPlanMstRegistMstKanriDao().insert(mstKanri);
        
        //処理対象エントリー回を取得する。
        entryKai = mstKanri.getEntryKai();
        
        //５．開催日用のエンティティー[事業方針説明会日付マスタ]を生成する。
        MstKanriDate mstKanriExecDate = newCreateMstKanriDate(
                entryCd, entryYear, entryKai, ProjectPlanMstRegistUtil.USER_TYPE_CD_ETC, ProjectPlanMstRegistUtil.DAY_KBN_EXECDT
                , entityInputData, userId, userPgm, currentTimestamp);
        //６．DAO【事業方針説明会日付マスタ】(BR18ENTD)．新規登録 を実行する。
        getProjectPlanMstRegistMstKanriDateDao().insert(mstKanriExecDate);
        
        //７．本部：表示用のエンティティー[事業方針説明会日付マスタ]を生成する。
        MstKanriDate mstKanriDispDate = newCreateMstKanriDate(
                entryCd, entryYear, entryKai, ProjectPlanMstRegistUtil.USER_TYPE_CD_HONBU, ProjectPlanMstRegistUtil.DAY_KBN_DISPDT
                , entityInputData, userId, userPgm, currentTimestamp);
        //８．DAO【事業方針説明会日付マスタ】(BR18ENTD)．新規登録 を実行する。
        getProjectPlanMstRegistMstKanriDateDao().insert(mstKanriDispDate);
        
        //９．本部：申込用のエンティティー[事業方針説明会日付マスタ]を生成する。
        MstKanriDate mstKanriEntryDate = newCreateMstKanriDate(
                entryCd, entryYear, entryKai, ProjectPlanMstRegistUtil.USER_TYPE_CD_HONBU, ProjectPlanMstRegistUtil.DAY_KBN_ENTRYDT
                , entityInputData, userId, userPgm, currentTimestamp);
        //１０．DAO【事業方針説明会日付マスタ】(BR18ENTD)．新規登録 を実行する。
        getProjectPlanMstRegistMstKanriDateDao().insert(mstKanriEntryDate);
        
        //１１．オーナー：表示用のエンティティー[事業方針説明会日付マスタ]を生成する。
        MstKanriDate mstKanriOnerDispDate = newCreateMstKanriDate(
                entryCd, entryYear, entryKai, ProjectPlanMstRegistUtil.USER_TYPE_CD_ONER, ProjectPlanMstRegistUtil.DAY_KBN_DISPDT
                , entityInputData, userId, userPgm, currentTimestamp);
        //１２．DAO【事業方針説明会日付マスタ】(BR18ENTD)．新規登録 を実行する。
        getProjectPlanMstRegistMstKanriDateDao().insert(mstKanriOnerDispDate);

        //１３．オーナー：申込用のエンティティー[事業方針説明会日付マスタ]を生成する。
        MstKanriDate mstKanriOnerEntryDate = newCreateMstKanriDate(
                entryCd, entryYear, entryKai, ProjectPlanMstRegistUtil.USER_TYPE_CD_ONER, ProjectPlanMstRegistUtil.DAY_KBN_ENTRYDT
                , entityInputData, userId, userPgm, currentTimestamp);
        //１４．DAO【事業方針説明会日付マスタ】(BR18ENTD)．新規登録 を実行する。
        getProjectPlanMstRegistMstKanriDateDao().insert(mstKanriOnerEntryDate);
 
        //１５．エンティティー[事業方針説明会文言マスタ]を生成する。
        MstKanriNotice mstKanriNotice = new MstKanriNotice();
        //登録対象[事業方針説明会文言マスタ].エンティティーコード =登録対象[事業方針説明会マスタ].エンティティーコード
        mstKanriNotice.setEntryCd(entryCd);
        //登録対象[事業方針説明会文言マスタ].エンティティー年 = 登録対象[事業方針説明会マスタ].エンティティー年
        mstKanriNotice.setEntryYear(entryYear);
        //登録対象[事業方針説明会文言マスタ].エンティティー回 = 登録対象[事業方針説明会マスタ]エンティティー回
        mstKanriNotice.setEntryKai(entryKai);
        //登録対象[事業方針説明会文言マスタ].文言1 = パラメーター.対象事業方針説明会情報.注意事項
        settingDataMstKanriNotice(entityInputData, mstKanriNotice, userId, userPgm);
        //登録対象[事業方針説明会文言マスタ].登録ユーザー = 処理１－２.ユーザーID
        mstKanriNotice.setFirstUser(userId);
        //登録対象[事業方針説明会文言マスタ].登録PGM = 処理１－３.PGM
        mstKanriNotice.setFirstPgm(userPgm);
        //登録対象[事業方針説明会文言マスタ].登録タイムスタンプ = 処理１－１.タイムスタンプ
        mstKanriNotice.setFirstTmsp(currentTimestamp);
        //登録対象[事業方針説明会文言マスタ].更新ユーザー = 処理１－２.ユーザーID
        //登録対象[事業方針説明会文言マスタ].更新PGM = 処理１－３.PGM
        //登録対象[事業方針説明会文言マスタ].更新タイムスタンプ = 処理１－１.タイムスタンプ
        mstKanriNotice.setLastTmsp(currentTimestamp);
        
        //１６．DAO【事業方針説明会文言マスタ】(BR51ENCI)．新規登録 を実行する。
        getProjectPlanMstRegistMstKanriNoticeDao().insert(mstKanriNotice);
   }
    /**
     * 更新登録処理
     *     
     * ■登録対象テーブル
     * ・BR17ENTL：エントリーマスタ管理
     * ・BR18ENTD：エントリー日付管理
     * ・BR51ENCI：エントリー文言情報
     * 
     * @param param
     */
    private void executeUpdate(Map params) {
        //パラメーター.BIRDユーザー情報
        BirdUserInfo userInfo = (BirdUserInfo)params.get(PK_USERINFO);
        //パラメーター.対象事業方針説明会情報
        UICourseInfo entityInputData = (UICourseInfo)params.get(PK_ENTITY_COURSE);
        //パラメーター.事業方針説明会マスタ情報
        List listMst = (List)params.get(PK_LIST_MST);
        //パラメーター.事業方針説明会日付マスタ情報
        List listMstDate = (List)params.get(PK_LIST_MST_DATE);
        //パラメーター.事業方針説明会文言マスタ情報
        List listMstNotice = (List)params.get(PK_LIST_MST_NOTICE);
        
        //１．処理対象のデータを生成する。
        //１－１．パラメーター.BIRDユーザー情報からユーザーIDを取得する。
        String userId = userInfo.getUserID();
        //１－２．ロジックIDの先頭7バイトの文字をPGMとして生成する。
        String userPgm = LOGIC_ID.substring(0, 7);
        //１－３．タイムスタンプを生成する。
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
        //パラメーター.対象事業方針説明会情報.エントリーコード
        String entryCd = entityInputData.getEntryCd();
        //パラメーター.対象事業方針説明会情報.エントリー年
        String entryYear = entityInputData.getEntryYear();
        //パラメーター.対象事業方針説明会情報.エントリー回
        String entryKai = entityInputData.getEntryKai();
        
        //２．パラメーター.事業方針説明会マスタ情報の１行目のエンティティー[事業方針説明会マスタ]を取得する。
        MstKanri mstKanri = (MstKanri)listMst.get(0);
        //３．開催日の年度を生成し、下記の処理を行う。
        String execDtNendo = entryYear;
        String execDtKai = entryKai;
        try {
            //パラメーター.対象事業方針説明会情報.エントリーコード
            String execDt = entityInputData.getExecDt();
            //開催日からエントリー年の値を設定します。
            execDtNendo = DateManager.getCurrentYear(execDt);
        }
        catch(Exception ex){
            throw new FtlSystemException("事業方針説明会マスタ登録 登録ロジック更新", "", ex);
        }
        //４．処理３で生成した開催日年度 ＝ パラメーター.対象事業方針説明会情報.エントリー年の場合、下記の処理を行う。
        if(entryYear.equals(execDtNendo)){
            //４－１.パラメーター.事業方針説明会マスタ情報へパラメーター.対象事業方針説明会情報のデータを設定します。
            settingDataMstKanri(entityInputData, mstKanri, userId, userPgm);
            
            //４－２.DAO【事業方針説明会マスタ】(BR17ENTL)．更新登録 を実行する。
            getProjectPlanMstRegistMstKanriDao().update(mstKanri);
        }
        //５．処理３で生成した開催日年度 <> パラメーター.対象事業方針説明会情報.エントリー年の場合、下記の処理を行う。
        else{
            //５－１.DAO【事業方針説明会マスタ】(BR17ENTL)．新規用の管理対象期間情報を取得 を実行し、
            //       登録対象[[事業方針説明会マスタ]]を取得する。        
            List listMstChangeYear = getProjectPlanMstRegistMstKanriDao().selectNewEntry(entryCd, execDtNendo, entryKaiDefault);
            MstKanri mstKanriChangeYear = (MstKanri)listMstChangeYear.get(0);
            execDtKai = mstKanriChangeYear.getEntryKai();
            List listNoChangeData = getProjectPlanMstRegistMstKanriDao().selectCheckChanged(mstKanri);
            if(listNoChangeData == null || listNoChangeData.size() < 1){
                //排他制御
                throw new CannotExecuteWithReasonException("このデータは既に更新されている", "更新");
            }
            //５－２.パラメーター.事業方針説明会マスタ情報へパラメーター.対象事業方針説明会情報のデータを設定します。
            settingDataMstKanri(entityInputData, mstKanri, userId, userPgm);
            mstKanri.setEntryYear(execDtNendo);
            mstKanri.setEntryKai(execDtKai);
            mstKanri.setLastTmsp(currentTimestamp);
            //５－３．DAO【事業方針説明会マスタ】(BR17ENTL)．更新登録 を実行する。
            getProjectPlanMstRegistMstKanriDao().updateChangeYear(mstKanri, entryYear, entryKai);
        }
        //６．パラメーター.事業方針説明会日付マスタ情報へパラメーター.対象事業方針説明会情報のデータを設定します。
        for(int i=0; i<listMstDate.size(); i++){
            //７．パラメーター.事業方針説明会日付マスタ情報の１行目のエンティティー[事業方針説明会日付マスタ]を取得する。
            MstKanriDate mstKanriDate = (MstKanriDate)listMstDate.get(i);
            //８．
            if(entryYear.equals(execDtNendo)){
                //８－２．処理８－１で取得した[事業方針説明会日付マスタ]へパラメーター.対象事業方針説明会情報のデータを設定します。
                settingDataMstKanriDate(entityInputData, mstKanriDate, userId, userPgm);
                //８－３．DAO【事業方針説明会日付マスタ】(BR18ENTD)．更新登録 を実行する。
                getProjectPlanMstRegistMstKanriDateDao().update(mstKanriDate);
            }
            else{
                List listNoChangeData = getProjectPlanMstRegistMstKanriDateDao().selectCheckChanged(mstKanriDate);
                if(listNoChangeData == null || listNoChangeData.size() < 1){
                    //排他制御 エラーを発生させる。             
                    throw new CannotExecuteWithReasonException("このデータは既に更新されている", "更新");
                }
                //処理３－１[事業方針説明会マスタ]へパラメーター.対象事業方針説明会情報のデータを設定します。
                settingDataMstKanriDate(entityInputData, mstKanriDate, userId, userPgm);
                mstKanriDate.setEntryYear(execDtNendo);
                mstKanriDate.setEntryKai(execDtKai);
                mstKanriDate.setLastTmsp(currentTimestamp);
                getProjectPlanMstRegistMstKanriDateDao().updateChangeYear(mstKanriDate, entryYear, entryKai);
            }
            
        }
        
        //９．パラメーター.事業方針説明会文言マスタ情報の１行目のエンティティー[事業方針説明会文言マスタ]を取得する。
        MstKanriNotice mstKanriNotice = (MstKanriNotice)listMstNotice.get(0);
        if(entryYear.equals(execDtNendo)){
            //１０－２．処理１０－１で取得した[事業方針説明会文言マスタ]へパラメーター.対象事業方針説明会情報のデータを設定します。
            settingDataMstKanriNotice(entityInputData, mstKanriNotice, userId, userPgm);
            //１１．DAO【事業方針説明会文言マスタ】(BR51ENCI)．更新登録 を実行する。
            getProjectPlanMstRegistMstKanriNoticeDao().update(mstKanriNotice);
        }
        else{
            List listNoChangeData = getProjectPlanMstRegistMstKanriNoticeDao().selectCheckChanged(mstKanriNotice);
            if(listNoChangeData == null || listNoChangeData.size() < 1){
                //排他制御 エラーを発生させる。             
                throw new CannotExecuteWithReasonException("このデータは既に更新されている", "更新");
            }
            //処理３－１[事業方針説明会マスタ]へパラメーター.対象事業方針説明会情報のデータを設定します。
            settingDataMstKanriNotice(entityInputData, mstKanriNotice, userId, userPgm);
            mstKanriNotice.setEntryYear(execDtNendo);
            mstKanriNotice.setEntryKai(execDtKai);
            mstKanriNotice.setLastTmsp(currentTimestamp);
            getProjectPlanMstRegistMstKanriNoticeDao().updateChangeYear(mstKanriNotice, entryYear, entryKai);
        }
        
    }
    /**
     * 更新登録処理
     *     
     * ■登録対象テーブル
     * ・BR17ENTL：エントリーマスタ管理
     * 
     * @param param
     */
    private void executeDelete(Map params) {
        //パラメーター.BIRDユーザー情報
        BirdUserInfo userInfo = (BirdUserInfo)params.get(PK_USERINFO);        
        //パラメーター.事業方針説明会マスタ情報
        List listMst = (List)params.get(PK_LIST_MST);
        //１．処理対象のデータを生成する。
        //１－１．パラメーター.BIRDユーザー情報からユーザーIDを取得する。
        String userId = userInfo.getUserID();
        //１－２．ロジックIDの先頭7バイトの文字をPGMとして生成する。
        String userPgm = LOGIC_ID.substring(0, 7);
        
        //３．パラメーター.事業方針説明会マスタ情報へパラメーター.対象事業方針説明会情報のデータを設定します。
        //３－１．パラメーター.事業方針説明会マスタ情報の１行目のエンティティー[事業方針説明会マスタ]を取得する。
        MstKanri mstKanri = (MstKanri)listMst.get(0);
        //３－２．登録対象[事業方針説明会日付マスタ]へパラメーター.対象事業方針説明会情報のデータを設定します。
        //登録対象[事業方針説明会マスタ].削除フラグ   = '1’
        mstKanri.setSakujoFlg("1");
        //登録対象[事業方針説明会マスタ].更新ユーザー = ユーザーID
        mstKanri.setLastUser(userId);
        //登録対象[事業方針説明会マスタ].更新PGM = PGM
        mstKanri.setLastPgm(userPgm);
        
        //５．DAO【事業方針説明会マスタ】(BR17ENTL)．更新登録 を実行する。
        getProjectPlanMstRegistMstKanriDao().update(mstKanri);
    }

    /**
     * 事業方針説明会日付マスタエンティティ新規データ作成処理
     * 
     * @param entryCd
     * @param entryYear
     * @param entryKay
     * @param userTypeCd
     * @param dayKbn
     * @param entityInputData
     * @return
     */
    private MstKanriDate newCreateMstKanriDate(
            String entryCd, String entryYear, String entryKay
            , String userTypeCd, String dayKbn, UICourseInfo entityInputData
            , String userId, String userPgm, Timestamp currentTimestamp)
    {
        MstKanriDate mstKanriDate = new MstKanriDate();
        //登録対象[事業方針説明会日付マスタ].エンティティーコード =登録対象[事業方針説明会マスタ].エンティティーコード
        mstKanriDate.setEntryCd(entryCd);
        //登録対象[事業方針説明会日付マスタ].エンティティー年 = 登録対象[事業方針説明会マスタ].エンティティー年
        mstKanriDate.setEntryYear(entryYear);
        //登録対象[事業方針説明会日付マスタ].エンティティー回 = 登録対象[事業方針説明会マスタ]エンティティー回
        mstKanriDate.setEntryKai(entryKay);
        //登録対象[事業方針説明会日付マスタ].ユーザータイプコード = '99'（その他)
        mstKanriDate.setUsertypeCd(userTypeCd);
        //登録対象[事業方針説明会日付マスタ].日付区分 = '01'（開催日)
        mstKanriDate.setDayKbn(dayKbn);
        //登録対象[事業方針説明会日付マスタ].開始日 = パラメーター.対象事業方針説明会情報.開催日
        //登録対象[事業方針説明会日付マスタ].終了日 = パラメーター.対象事業方針説明会情報.開催日
        settingDataMstKanriDate(entityInputData, mstKanriDate, userId, userPgm);
        //登録対象[事業方針説明会日付マスタ].登録ユーザー = 処理１－２.ユーザーID
        mstKanriDate.setFirstUser(userId);
        //登録対象[事業方針説明会日付マスタ].登録PGM = 処理１－３.PGM
        mstKanriDate.setFirstPgm(userPgm);
        //登録対象[事業方針説明会日付マスタ].登録タイムスタンプ = 処理１－１.タイムスタンプ
        mstKanriDate.setFirstTmsp(currentTimestamp);
        //登録対象[事業方針説明会日付マスタ].更新ユーザー = 処理１－２.ユーザーID
        //登録対象[事業方針説明会日付マスタ].更新PGM = 処理１－３.PGM
        //登録対象[事業方針説明会日付マスタ].更新タイムスタンプ = 処理１－１.タイムスタンプ
        mstKanriDate.setLastTmsp(currentTimestamp);
        return mstKanriDate;
        
    }
    /**
     * 事業方針説明会マスタへ入力値の設定処理
     * 
     * 設定されるデータは下記のとおりです。
     * ・登録対象[事業方針説明会マスタ].説明会名称   = 対象事業方針説明会情報.説明会名称
     * ・登録対象[事業方針説明会マスタ].委任状       = 対象事業方針説明会情報.委任状
     * ・登録対象[事業方針説明会マスタ].削除フラグ   = '0'
     * ・登録対象[事業方針説明会マスタ].更新ユーザー = ユーザーID
     * ・登録対象[事業方針説明会マスタ].更新PGM      = PGM
     * 
     * @param entityInputData　[対象事業方針説明会情報]
     * @param mstKanri　　登録対象[事業方針説明会マスタ]
     * @param userId　　 ユーザーID
     * @param userPgm　　PGM
     * @return
     */
    private void settingDataMstKanri(UICourseInfo entityInputData, MstKanri mstKanri, String userId, String userPgm){
        //登録対象[事業方針説明会マスタ].説明会名称 = [対象事業方針説明会情報].説明会名称
        mstKanri.setEntryTitle(entityInputData.getEntryTitle());
        //登録対象[事業方針説明会マスタ].委任状 = [対象事業方針説明会情報].委任状
        String note = entityInputData.getNote();
        if(ProjectPlanMstRegistUtil.isNull(note)){
            //nullの場合は空を設定する。
            note = "";
        }
        mstKanri.setNote(note);
        //登録対象[事業方針説明会マスタ].削除フラグ= '0'
        mstKanri.setSakujoFlg("0");
        //登録対象[事業方針説明会マスタ].更新ユーザー = ユーザーID
        mstKanri.setLastUser(userId);
        //登録対象[事業方針説明会マスタ].更新PGM = PGM
        mstKanri.setLastPgm(userPgm);
    }
    /**
     * 事業方針説明会日付マスタへ入力値の設定処理
     * 
     * 設定されるデータは下記のとおりです。
     * ・登録対象[事業方針説明会日付マスタ].開始日   = 処理１.開始日
     * ・登録対象[事業方針説明会日付マスタ].終了日       = 処理１.終了日
     * ・登録対象[事業方針説明会日付マスタ].更新ユーザー = ユーザーID
     * ・登録対象[事業方針説明会日付マスタ].更新PGM      = PGM
     * 
     * @param entityInputData　   [対象事業方針説明会情報]
     * @param mstKanriDate　登録対象[事業方針説明会日付マスタ]
     * @param userId　　    ユーザーID
     * @param userPgm　　   PGM
     * @return
     */
    private void settingDataMstKanriDate(
            UICourseInfo entityInputData, MstKanriDate mstKanriDate
            , String userId, String userPgm)
   {
        //登録対象[事業方針説明会日付マスタ].ユーザータイプコード
        String userTypeCd = mstKanriDate.getUsertypeCd();
        //登録対象[事業方針説明会日付マスタ].日付区分
        String dayKbn = mstKanriDate.getDayKbn();
        String fromDt = "";
        String toDt = "";
        //１．ユーザータイプコードと日付区分のパターンにより設定対象値を判別し、取得する。
        //１－１．ユーザータイプコードが「その他」の場合
        if(ProjectPlanMstRegistUtil.USER_TYPE_CD_ETC.equals(userTypeCd)){
            if(ProjectPlanMstRegistUtil.DAY_KBN_EXECDT.equals(dayKbn)){
                fromDt = entityInputData.getExecDt();
                toDt = "";
            }
        }
        //１－２．ユーザータイプコードが「本部」の場合
        else if(ProjectPlanMstRegistUtil.USER_TYPE_CD_HONBU.equals(userTypeCd)){
            if(ProjectPlanMstRegistUtil.DAY_KBN_DISPDT.equals(dayKbn)){
                //日付区分が「表示」の場合
                fromDt = entityInputData.getDispFrom();
                toDt = entityInputData.getDispTo();
            }
            else if(ProjectPlanMstRegistUtil.DAY_KBN_ENTRYDT.equals(dayKbn)){
                //日付区分が「登録」の場合
                fromDt = entityInputData.getEntryFrom();
                toDt = entityInputData.getEntryTo();
            }
        }
        //１－３．ユーザータイプコードが「オーナー」の場合
        else if(ProjectPlanMstRegistUtil.USER_TYPE_CD_ONER.equals(userTypeCd)){
            if(ProjectPlanMstRegistUtil.DAY_KBN_DISPDT.equals(dayKbn)){
                //日付区分が「表示」の場合
                fromDt = entityInputData.getOnerDispFrom();
                toDt = entityInputData.getOnerDispTo();
            }
            else if(ProjectPlanMstRegistUtil.DAY_KBN_ENTRYDT.equals(dayKbn)){
                //日付区分が「登録」の場合
                fromDt = entityInputData.getOnerEntryFrom();
                toDt = entityInputData.getOnerEntryTo();
            }
        }
        //処理
        //登録対象[事業方針説明会日付マスタ].開始日 = 処理１.開始日
        mstKanriDate.setFromDt(fromDt);
        //登録対象[事業方針説明会日付マスタ].終了日 = 処理１.終了日
        mstKanriDate.setToDt(toDt);
        //登録対象[事業方針説明会日付マスタ].更新ユーザー = ユーザーID
        mstKanriDate.setLastUser(userId);
        //登録対象[事業方針説明会日付マスタ].更新PGM = PGM
        mstKanriDate.setLastPgm(userPgm);
    }
    /**
     * 事業方針説明会文言マスタへ入力値の設定処理
     * 
     * 設定されるデータは下記のとおりです。
     * ・登録対象[事業方針説明会文言マスタ].文言1 = [対象事業方針説明会情報].注意事項
     * ・登録対象[事業方針説明会文言マスタ].更新ユーザー = ユーザーID
     * ・登録対象[事業方針説明会文言マスタ].更新PGM      = PGM
     * 
     * @param entityInputData　[対象事業方針説明会情報]
     * @param mstKanri　　登録対象[事業方針説明会文言マスタ]
     * @param userId　　 ユーザーID
     * @param userPgm　　PGM
     * @return
     */
    private void settingDataMstKanriNotice(
            UICourseInfo entityInputData, MstKanriNotice mstKanriNotice
            , String userId, String userPgm)
    {
        //登録対象[事業方針説明会マスタ].文言1 = [対象事業方針説明会情報].注意事項
        String notice1 = entityInputData.getNotice1();
        if(ProjectPlanMstRegistUtil.isNull(notice1)){
            //nullの場合は空を設定する。
            notice1 = "";
        }else{
            //改行文字を「`」へ変換する。
            notice1 = ProjectPlanMstRegistUtil.changeEnterWordJSFtoDB(notice1);
        }
        mstKanriNotice.setNotice1(notice1);
        //登録対象[事業方針説明会マスタ].更新ユーザー = ユーザーID
        mstKanriNotice.setLastUser(userId);
        //登録対象[事業方針説明会マスタ].更新PGM = PGM
        mstKanriNotice.setLastPgm(userPgm);
    }
    /**
     * @return mstKanriDao を戻します。
     */
    public MstKanriDao getProjectPlanMstRegistMstKanriDao() {
        return mstKanriDao;
    }
    /**
     * @param mstKanriDao 設定する。
     */
    public void setProjectPlanMstRegistMstKanriDao(
            MstKanriDao dao) {
        this.mstKanriDao = dao;
    }
    /**
     * @return mstKanrDateDao を戻します。
     */
    public MstKanriDateDao getProjectPlanMstRegistMstKanriDateDao() {
        return mstKanriDateDao;
    }
    /**
     * @param mstKanrDateDao 設定する 。
     */
    public void setProjectPlanMstRegistMstKanriDateDao(
            MstKanriDateDao dao) {
        this.mstKanriDateDao = dao;
    }
    /**
     * @return mstKanriNoticeDao を戻します。
     */
    public MstKanriNoticeDao getProjectPlanMstRegistMstKanriNoticeDao() {
        return mstKanriNoticeDao;
    }
    /**
     * @param mstKanriNoticeDao 設定する。
     */
    public void setProjectPlanMstRegistMstKanriNoticeDao(
            MstKanriNoticeDao dao) {
        this.mstKanriNoticeDao = dao;
    }

}
