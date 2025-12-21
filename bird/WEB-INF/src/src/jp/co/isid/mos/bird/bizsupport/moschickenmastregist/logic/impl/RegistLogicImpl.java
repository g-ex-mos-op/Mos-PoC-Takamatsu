package jp.co.isid.mos.bird.bizsupport.moschickenmastregist.logic.impl;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.bizsupport.common.entity.MstKanriKikan;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.dao.MstKanriKikanDao;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.dao.MstKanriMenuGroupDao;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.dao.UIKanriMenuDao;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.entity.MstKanriMenuGroup;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.entity.UIKanriMenu;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.logic.RegistLogic;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.util.MosChickenMstRegistUtil;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NoTargetException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

/**
 * 
 * 予約販売管理マスタ登録
 * 【予約販売管理マスタ系情報の登録】ロジックインターフェース
 * 
 * @author xkinu
 *
 */
public class RegistLogicImpl implements RegistLogic {
    /** ロジックID */
    public static final String LOGIC_ID = MosChickenMstRegistUtil.SCREEN_ID+"L06";
    /**
     * パラメーターキー：管理対象期間リスト
     */
    public static final String PK_USER_ID= "userId";
    /**
     * パラメーターキー：管理対象期間リスト
     */
    public static final String PK_LIST_KIKAN= "listKikan";
    /**
     * パラメーターキー：管理対象メニューグループリスト
     */
    public static final String PK_LIST_MENU_GROUP= "listMenuGroup";
    /**
     * パラメーターキー：管理対象メニューリスト
     */
    public static final String PK_LIST_MENU= "listMenu";
    /**
     * パラメーターキー：処理タイプ
     */
    public static final String PK_MODE_TYPE= "modeType";
    /**
     * パラメーターキー：当年度（システム年度）
     * 新規登録時のみ必要になります。
     */
    public static final String PK_NENDO= "nendo";
    
    /*【DAO】管理対象期間 */
    private MstKanriKikanDao mosChickenMstRegMstKanriKikanDao;
    /*【DAO】管理対象メニューグループ */
    private MstKanriMenuGroupDao mosChickenMstRegMstKanriMenuGroupDao;
    /*【DAO】管理対象メニュー */
    private UIKanriMenuDao mosChickenMstRegUIKanriMenuDao;
 
    /**
     * 実行処理
     */
    public List execute(Map params) {
        //１．事前条件処理を実行する。
        validate(params);
        //処理対象　処理タイプ
        String modeType = (String)params.get(PK_MODE_TYPE);
        if(MosChickenMstRegistUtil.MODETYPE_INSERT.equals(modeType)) {
            //２．新規登録時
            executeInsert(params);
        }
        else if(MosChickenMstRegistUtil.MODETYPE_UPDATE.equals(modeType)) {
            executeUpdate(params);
        }
        return null;
    }
    /**
     * 事前条件処理
     * @param map
     * @throws ApplicationException
     */
    private void validate(final Map params) {
        //処理対象　ユーザーID
        String userId = (String)params.get(PK_USER_ID);
        if(MosChickenMstRegistUtil.isNull(userId)){
            throw new MissingDataException("登録ユーザーID");
        }
        //処理対象　管理対象期間
        List listKikan = (List)params.get(PK_LIST_KIKAN);
        if (listKikan == null || listKikan.size() == 0) {
            //MSG【E0203】ありません。
            //処理対象が存在しない。
            throw new NoTargetException("管理対象期間");
        }
        String modeType = (String)params.get(PK_MODE_TYPE);
        if(MosChickenMstRegistUtil.isNull(modeType)){
            throw new MissingDataException("処理タイプ");
        }
        if(MosChickenMstRegistUtil.MODETYPE_INSERT.equals(modeType)) {
            //２．新規登録時
            String nendo = (String)params.get(PK_NENDO);
            if(MosChickenMstRegistUtil.isNull(nendo)){
                throw new MissingDataException("当年度");
            }
        }
    }
    /**
     * 新規登録処理
     * １．タイムスタンプを生成する。
     * 
     * ２．Dao【管理対象期間】．新規用の管理対象期間情報を取得 を実行し、新規管理番号を取得する。
     * 　　パラメーター：パラメーター.当年度の年度＋"01" (デフォルト管理番号)
     *           デフォルト管理番号:パラメーター.当年度の年度と文字列"01"を結合した文字列。
     * 　　　例）システム日付 20060330 ---> '200501'
     *           システム日付 20060930 ---> '200601'
     * 
     * ３．パラメーター.処理対象 [[管理対象期間]]へ新規管理番号と更新系カラムへ値を設定する。
     * 　　[管理対象期間].管理番号           ＝　新規管理番号
     * 　　[管理対象期間].登録ユーザー       ＝　パラメーター.ユーザーID
     * 　　[管理対象期間].登録PGM            ＝　画面ID＋"L"
     * 　　[管理対象期間].登録タイムスタンプ ＝　処理１．タイムスタンプ
     * 　　[管理対象期間].更新ユーザー       ＝　パラメーター.ユーザーID
     * 　　[管理対象期間].更新PGM            ＝　画面ID＋"L"
     * 　　[管理対象期間].更新タイムスタンプ ＝　処理１．タイムスタンプ
     * 
     * ４．Dao【管理対象期間】．新規登録 を実行する。
     *     パラメーター：パラメーター.処理対象 [[管理対象期間]].[管理対象期間]エンティティ
     * 
     * ５．パラメーター.処理対象 [[管理対象メニューグループ]]がNullでなければ下記の処理を行う。
     *     ５－１．パラメーター.処理対象 [[管理対象メニューグループ]]件数分、以下の処理を行う。
     *        1).パラメーター.処理対象[管理対象メニューグループ].メニューグループコードを取得する。
     *        2).行番号で新規メニューグループコードを生成する。
     *        　 　　　前ゼロ付加の2バイト
     *              例）１件目の場合は ---> "01"
     *   
     *        3).処理対象 [[管理対象メニュー]]の件数分、下記の処理を行う。
     *         3-1).処理1)のメニューグループコード＝＝処理対象[管理対象メニュー].メニューグループコードの場合下記の処理を行う。
     *           a.パラメーター.処理対象[管理対象メニュー]エンティティへ下記の値を設定する。
     *     　　　        処理対象[管理対象メニュー].管理番号               ＝　パラメーター.処理対象 [[管理対象期間]].[管理対象期間].管理番号
     *     　　　        処理対象[管理対象メニュー].メニューグループコード ＝　新規メニューグループコード
     *     　　　        処理対象[管理対象メニュー].登録ユーザー           ＝　パラメーター.ユーザーID
     * 　　　　　        処理対象[管理対象メニュー].登録PGM                ＝　画面ID＋"L"
     * 　　　　　        処理対象[管理対象メニュー].登録タイムスタンプ     ＝　処理１．タイムスタンプ
     *     　　　        処理対象[管理対象メニュー].更新ユーザー           ＝　パラメーター.ユーザーID
     * 　　　　　        処理対象[管理対象メニュー].更新PGM                ＝　画面ID＋"L"
     * 　　　　　        処理対象[管理対象メニュー].更新タイムスタンプ     ＝　処理１．タイムスタンプ
     * 
     *           b.Dao【管理対象メニュー】新規登録処理を実行する。
     *           パラメーター：パラメーター.処理対象 [[管理対象期間]].[管理対象期間].管理番号
     *                         パラメーター.処理対象 [[管理対象メニューグループ]].[管理対象メニューグループ].メニューグループコード
     *                         処理 1) の新規メニューグループコード
     *                         パラメーター.処理対象 [[管理対象メニュー]]
     *                         パラメーター.ユーザーID
     *                         処理１．タイムスタンプ
     *           
     *           c.処理3-1)へ。
     *                         
     *     　 4).パラメーター.処理対象[管理対象メニューグループ]エンティティへ下記の値を設定する。
     *     　　　処理対象[管理対象メニューグループ].管理番号               ＝　パラメーター.処理対象 [[管理対象期間]].[管理対象期間].管理番号
     *     　　　処理対象[管理対象メニューグループ].メニューグループコード ＝　新規メニューグループコード
     *     　　　処理対象[管理対象メニューグループ].登録ユーザー           ＝　パラメーター.ユーザーID
     * 　　　　　処理対象[管理対象メニューグループ].登録PGM                ＝　画面ID＋"L"
     * 　　　　　処理対象[管理対象メニューグループ].登録タイムスタンプ     ＝　処理１．タイムスタンプ
     *     　　　処理対象[管理対象メニューグループ].更新ユーザー           ＝　パラメーター.ユーザーID
     * 　　　　　処理対象[管理対象メニューグループ].更新PGM                ＝　画面ID＋"L"
     * 　　　　　処理対象[管理対象メニューグループ].更新タイムスタンプ     ＝　処理１．タイムスタンプ
     * 
     *        5).Dao【管理対象メニューグループ】新規登録処理を実行する。
     *           パラメーター：パラメーター.処理対象[管理対象メニューグループ]エンティティ
     *        
     *        6).処理５－１へ。
     * 
     * @param param
     */
    private void executeInsert(Map params) {
        String nendo = (String)params.get(PK_NENDO);
        //処理対象　ユーザーID
        String userId = (String)params.get(PK_USER_ID);
        //１．タイムスタンプを生成する。
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
        //２．Dao【管理対象期間】．新規用の管理対象期間情報を取得 を実行する。
        List newCkanriNoList = getMosChickenMstRegMstKanriKikanDao().selectNewCKanriNo(nendo);
        MstKanriKikan entityNewCkanriNo = (MstKanriKikan)newCkanriNoList.get(0);
        String newCkanriNo = entityNewCkanriNo.getCkanriNo();
        //３．パラメーター.処理対象 [[管理対象期間]]へ新規管理番号と更新系カラムへ値を設定する。
        List listKikan = (List)params.get(PK_LIST_KIKAN);
        //日付変換
        DateFormatter dateFormat = new DateFormatter(DateFormatter.DATE_TYPE_YMD, DateFormatter.PATTERN_NORMAL);
        MstKanriKikan entityKikan = (MstKanriKikan)listKikan.get(0);
        //対象期間FROM
        String targetFrom = dateFormat.format(entityKikan.getTargetFrom(), false);
        //対象期間TO
        String targetTo = dateFormat.format(entityKikan.getTargetTo(), false);
        //デフォルト表示期間FROM
        String defaultFrom = dateFormat.format(entityKikan.getDefaultFrom(), false);
        //デフォルト表示期間TO
        String defaultTo = dateFormat.format(entityKikan.getDefaultTo(), false);
        entityKikan.setCkanriNo(newCkanriNo);
        entityKikan.setTargetFrom(targetFrom);
        entityKikan.setTargetTo(targetTo);
        entityKikan.setDefaultFrom(defaultFrom);
        entityKikan.setDefaultTo(defaultTo);
        entityKikan.setFirstUser(userId);
        entityKikan.setFirstPgm(LOGIC_ID.substring(0, 7));
        entityKikan.setFirstTmsp(currentTimestamp);
        entityKikan.setLastUser(userId);
        entityKikan.setLastPgm(LOGIC_ID.substring(0, 7));
        entityKikan.setLastTmsp(currentTimestamp);

        //４．Dao【管理対象期間】．新規登録 を実行する。
        getMosChickenMstRegMstKanriKikanDao().insert(entityKikan);
        
        //処理対象 [[管理対象メニューグループ]]
        List listMenuGroup = (List)params.get(PK_LIST_MENU_GROUP);
        //処理対象 [[管理対象メニュー]]
        List listMenu = (List)params.get(PK_LIST_MENU);
        //５．this.管理対象メニューグループ新規登録処理を実行する。
        executeInsertMenuGroup(newCkanriNo, listMenuGroup, listMenu, userId, currentTimestamp);
    }
    /**
     * 更新登録処理
     * 
     * １．タイムスタンプを生成する。
     * 
     * ２．パラメーター.処理対象 [[管理対象期間]]の更新系カラムへ値を設定する。
     * 　　[管理対象期間].更新ユーザー       ＝　パラメーター.ユーザーID
     * 　　[管理対象期間].更新PGM            ＝　画面ID＋"L"
     * 　　[管理対象期間].更新タイムスタンプ ＝　処理１．タイムスタンプ
     * 
     * ３．Dao【管理対象期間】．更新登録 を実行する。
     *     パラメーター：パラメーター.処理対象 [[管理対象期間]].[管理対象期間]エンティティ
     * 
     * ４．Dao【管理対象メニューグループ】.削除 処理を実行する。
     *     パラメーター：パラメーター.処理対象 [[管理対象期間]].[管理対象期間].管理番号
     *     
     * ５．Dao【管理対象メニュー】.削除 処理を実行する。
     *     パラメーター：パラメーター.処理対象 [[管理対象期間]].[管理対象期間].管理番号
     * 
     * ６．パラメーター.処理対象 [[管理対象メニューグループ]]がNullでなければ下記の処理を行う。
     *     ６－１．パラメーター.処理対象 [[管理対象メニューグループ]]件数分、以下の処理を行う。
     *        1).パラメーター.処理対象[管理対象メニューグループ].メニューグループコードを取得する。
     *        2).行番号で新規メニューグループコードを生成する。
     *        　 　　　前ゼロ付加の2バイト
     *              例）１件目の場合は ---> "01"
     *   
     *        3).処理対象 [[管理対象メニュー]]の件数分、下記の処理を行う。
     *         3-1).処理1)のメニューグループコード＝＝処理対象[管理対象メニュー].メニューグループコードの場合下記の処理を行う。
     *           a.パラメーター.処理対象[管理対象メニュー]エンティティへ下記の値を設定する。
     *     　　　        処理対象[管理対象メニュー].管理番号               ＝　パラメーター.処理対象 [[管理対象期間]].[管理対象期間].管理番号
     *     　　　        処理対象[管理対象メニュー].メニューグループコード ＝　新規メニューグループコード
     *     　　　        処理対象[管理対象メニュー].登録ユーザー           ＝　パラメーター.ユーザーID
     * 　　　　　        処理対象[管理対象メニュー].登録PGM                ＝　画面ID＋"L"
     * 　　　　　        処理対象[管理対象メニュー].登録タイムスタンプ     ＝　処理１．タイムスタンプ
     *     　　　        処理対象[管理対象メニュー].更新ユーザー           ＝　パラメーター.ユーザーID
     * 　　　　　        処理対象[管理対象メニュー].更新PGM                ＝　画面ID＋"L"
     * 　　　　　        処理対象[管理対象メニュー].更新タイムスタンプ     ＝　処理１．タイムスタンプ
     * 
     *           b.Dao【管理対象メニュー】新規登録処理を実行する。
     *           パラメーター：パラメーター.処理対象 [[管理対象期間]].[管理対象期間].管理番号
     *                         パラメーター.処理対象 [[管理対象メニューグループ]].[管理対象メニューグループ].メニューグループコード
     *                         処理 1) の新規メニューグループコード
     *                         パラメーター.処理対象 [[管理対象メニュー]]
     *                         パラメーター.ユーザーID
     *                         処理１．タイムスタンプ
     *           
     *           c.処理3-1)へ。
     *                         
     *     　 4).パラメーター.処理対象[管理対象メニューグループ]エンティティへ下記の値を設定する。
     *     　　　処理対象[管理対象メニューグループ].管理番号               ＝　パラメーター.処理対象 [[管理対象期間]].[管理対象期間].管理番号
     *     　　　処理対象[管理対象メニューグループ].メニューグループコード ＝　新規メニューグループコード
     *     　　　処理対象[管理対象メニューグループ].登録ユーザー           ＝　パラメーター.ユーザーID
     * 　　　　　処理対象[管理対象メニューグループ].登録PGM                ＝　画面ID＋"L"
     * 　　　　　処理対象[管理対象メニューグループ].登録タイムスタンプ     ＝　処理１．タイムスタンプ
     *     　　　処理対象[管理対象メニューグループ].更新ユーザー           ＝　パラメーター.ユーザーID
     * 　　　　　処理対象[管理対象メニューグループ].更新PGM                ＝　画面ID＋"L"
     * 　　　　　処理対象[管理対象メニューグループ].更新タイムスタンプ     ＝　処理１．タイムスタンプ
     * 
     *        5).Dao【管理対象メニューグループ】新規登録処理を実行する。
     *           パラメーター：パラメーター.処理対象[管理対象メニューグループ]エンティティ
     *        
     *        6).処理６－１へ。
     *     
     * @param param
     */
    private void executeUpdate(Map params) {
        //処理対象　ユーザーID
        String userId = (String)params.get(PK_USER_ID);
        //１．タイムスタンプを生成する。
        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
        //２．パラメーター.処理対象 [[管理対象期間]]の更新系カラムへ値を設定する。
        List listKikan = (List)params.get(PK_LIST_KIKAN);
        //日付変換
        DateFormatter dateFormat = new DateFormatter(DateFormatter.DATE_TYPE_YMD, DateFormatter.PATTERN_NORMAL);
        MstKanriKikan entityKikan = (MstKanriKikan)listKikan.get(0);
        //対象期間FROM
        String targetFrom = dateFormat.format(entityKikan.getTargetFrom(), false);
        //対象期間TO
        String targetTo = dateFormat.format(entityKikan.getTargetTo(), false);
        //デフォルト表示期間FROM
        String defaultFrom = dateFormat.format(entityKikan.getDefaultFrom(), false);
        //デフォルト表示期間TO
        String defaultTo = dateFormat.format(entityKikan.getDefaultTo(), false);
        entityKikan.setTargetFrom(targetFrom);
        entityKikan.setTargetTo(targetTo);
        entityKikan.setDefaultFrom(defaultFrom);
        entityKikan.setDefaultTo(defaultTo);
        //更新ユーザー ＝　パラメーター.ユーザーID
        entityKikan.setLastUser(userId);
        //更新PGM ＝　画面ID＋"L"
        entityKikan.setLastPgm(LOGIC_ID.substring(0, 7));
        //更新タイムスタンプ ＝　処理１．タイムスタンプ
//        entityKikan.setLastTmsp(currentTimestamp);

        //３．Dao【管理対象期間】．更新登録 を実行する。
        getMosChickenMstRegMstKanriKikanDao().update(entityKikan);
        //対象管理番号
        String cKanriNo = entityKikan.getCkanriNo();
        
        //４．Dao【管理対象メニューグループ】.削除 処理を実行する。
        getMosChickenMstRegMstKanriMenuGroupDao().delete(cKanriNo);
        //５．Dao【管理対象メニュー】.削除 処理を実行する。
        getMosChickenMstRegUIKanriMenuDao().delete(cKanriNo);
        
        //処理対象 [[管理対象メニュー]]
        List listMenu = (List)params.get(PK_LIST_MENU);
        //処理対象 [[管理対象メニューグループ]]
        List listMenuGroup = (List)params.get(PK_LIST_MENU_GROUP);
        //６．this.管理対象メニューグループ新規登録処理を実行する。
        executeInsertMenuGroup(cKanriNo, listMenuGroup, listMenu, userId, currentTimestamp);

    }
    /**
     * 管理対象メニューグループ新規登録処理
     * 
     * １．パラメーター.処理対象 [[管理対象メニューグループ]]がNullでなければ下記の処理を行う。
     *     １－１．引数.処理対象 [[管理対象メニューグループ]]件数分、以下の処理を行う。
     *        1).パラメーター.処理対象[管理対象メニューグループ].メニューグループコードを取得する。
     *        2).行番号で新規メニューグループコードを生成する。
     *        　 　　　前ゼロ付加の2バイト
     *              例）１件目の場合は ---> "01"
     *   
     *        3).this.管理対象メニュー新規登録処理の処理を行う。
     *        　　引数：引数.管理番号
     *                  引数.処理対象[管理対象メニューグループ].メニューグループコード
     *                  新規メニューグループコード
     *                  引数.処理対象[[管理対象メニュー]]
     *                  引数.ユーザーID
     *                  引数.登録タイムスタンプ
     *                         
     *     　 4).パラメーター.処理対象[管理対象メニューグループ]エンティティへ下記の値を設定する。
     *     　　　処理対象[管理対象メニューグループ].管理番号               ＝　パラメーター.処理対象 [[管理対象期間]].[管理対象期間].管理番号
     *     　　　処理対象[管理対象メニューグループ].メニューグループコード ＝　新規メニューグループコード
     *     　　　処理対象[管理対象メニューグループ].登録ユーザー           ＝　パラメーター.ユーザーID
     * 　　　　　処理対象[管理対象メニューグループ].登録PGM                ＝　画面ID＋"L"
     * 　　　　　処理対象[管理対象メニューグループ].登録タイムスタンプ     ＝　処理１．タイムスタンプ
     *     　　　処理対象[管理対象メニューグループ].更新ユーザー           ＝　パラメーター.ユーザーID
     * 　　　　　処理対象[管理対象メニューグループ].更新PGM                ＝　画面ID＋"L"
     * 　　　　　処理対象[管理対象メニューグループ].更新タイムスタンプ     ＝　処理１．タイムスタンプ
     * 
     *        5).Dao【管理対象メニューグループ】新規登録処理を実行する。
     *           パラメーター：パラメーター.処理対象[管理対象メニューグループ]エンティティ
     *        
     *        6).処理１－１へ。
     * 
     * @param ckanriNo
     * @param listMenuGroup
     * @param listMenu
     * @param userId
     * @param currentTimestamp
     */
    private void executeInsertMenuGroup(String ckanriNo, List listMenuGroup, List listMenu, String userId, Timestamp currentTimestamp) {
        //１．パラメーター.処理対象 [[管理対象メニューグループ]]がNullでなければ下記の処理を行う。
        if (listMenuGroup != null || listMenuGroup.size() > 0) {
            //１－１．処理対象 [[管理対象メニューグループ]]件数分、
            //    Dao【管理対象メニューグループ】新規登録処理を実行する。
            CodeFormatter cdf = new CodeFormatter(2, "00");
            cdf.setFormatPattern("00");
            for(int i=0; i<listMenuGroup.size(); i++) {
                MstKanriMenuGroup entity = (MstKanriMenuGroup)listMenuGroup.get(i);
                //1).パラメーター.処理対象[管理対象メニューグループ].メニューグループコードを取得する。
                String oldMenuGrop = entity.getMenuGroup();
                //2).行番号で新規メニューグループコードを生成する。
                String newMenuGroup =  cdf.format(String.valueOf(i+1), true);
                //3).this.管理対象メニュー新規登録処理の処理を行う。
                executeInsertMenu(ckanriNo, oldMenuGrop, newMenuGroup, listMenu, userId, currentTimestamp);
                //4).パラメーター.処理対象[管理対象メニューグループ]エンティティへ下記の値を設定する。
                entity.setCkanriNo(ckanriNo);
                entity.setMenuGroup(newMenuGroup);
                entity.setFirstUser(userId);
                entity.setFirstPgm(LOGIC_ID.substring(0, 7));
                entity.setFirstTmsp(currentTimestamp);
                entity.setLastUser(userId);
                entity.setLastPgm(LOGIC_ID.substring(0, 7));
                entity.setLastTmsp(currentTimestamp);
                //5).Dao【管理対象メニューグループ】新規登録処理を実行する。
                getMosChickenMstRegMstKanriMenuGroupDao().insert(entity);
                //6).処理１－１へ。
            }
        }
    }
    /**
     * 管理対象メニュー新規登録処理
     * 
     * １．処理対象 [[管理対象メニュー]]の中から、引数.メニューグループコードと同じデータを、
     * 　　Dao【管理対象メニュー】新規登録処理を実行する。
     *           パラメーター：引数.管理番号
     *                         引数.メニューグループコード
     *                         引数.新規メニューグループコード
     *                         引数.[[管理対象メニュー]]
     *                         引数.ユーザーID
     *                         引数.タイムスタンプ
     * @param newCkanriNo
     * @param targetMenuGroup
     * @param newMenuGroup
     * @param listMenu
     * @param userId
     * @param currentTimestamp
     */
    private void executeInsertMenu(String newCkanriNo, String targetMenuGroup, String newMenuGroup, List listMenu, String userId, Timestamp currentTimestamp) {
        //処理対象 [[管理対象メニュー]]
        if (targetMenuGroup != null && newMenuGroup != null && listMenu != null) {
            //１．処理対象 [[管理対象メニュー]]件数分、
            //    Dao【管理対象メニュー】新規登録処理を実行する。
            for(int i=0; i<listMenu.size(); i++) {
                UIKanriMenu entity = (UIKanriMenu)listMenu.get(i);
                if(targetMenuGroup.equals(entity.getMenuGroup())){
                    entity.setCkanriNo(newCkanriNo);
                    entity.setMenuGroup(newMenuGroup);
                    entity.setFirstUser(userId);
                    entity.setFirstPgm(LOGIC_ID.substring(0, 7));
                    entity.setFirstTmsp(currentTimestamp);
                    entity.setLastUser(userId);
                    entity.setLastPgm(LOGIC_ID.substring(0, 7));
                    entity.setLastTmsp(currentTimestamp);
                    getMosChickenMstRegUIKanriMenuDao().insert(entity);
                }
            }
        }
    }
    /**
     * @return mosChickenMstRegMstKanriKikanDao を戻します。
     */
    public MstKanriKikanDao getMosChickenMstRegMstKanriKikanDao() {
        return mosChickenMstRegMstKanriKikanDao;
    }
    /**
     * @param mosChickenMstRegMstKanriKikanDao 設定する mosChickenMstRegMstKanriKikanDao。
     */
    public void setMosChickenMstRegMstKanriKikanDao(
            MstKanriKikanDao mosChickenMstRegMstKanriKikanDao) {
        this.mosChickenMstRegMstKanriKikanDao = mosChickenMstRegMstKanriKikanDao;
    }
    /**
     * @return mosChickenMstRegMstKanriMenuGroupDao を戻します。
     */
    public MstKanriMenuGroupDao getMosChickenMstRegMstKanriMenuGroupDao() {
        return mosChickenMstRegMstKanriMenuGroupDao;
    }
    /**
     * @param mosChickenMstRegMstKanriMenuGroupDao 設定する mosChickenMstRegMstKanriMenuGroupDao。
     */
    public void setMosChickenMstRegMstKanriMenuGroupDao(
            MstKanriMenuGroupDao mosChickenMstRegMstKanriMenuGroupDao) {
        this.mosChickenMstRegMstKanriMenuGroupDao = mosChickenMstRegMstKanriMenuGroupDao;
    }
    /**
     * @return mosChickenMstRegUIKanriMenuDao を戻します。
     */
    public UIKanriMenuDao getMosChickenMstRegUIKanriMenuDao() {
        return mosChickenMstRegUIKanriMenuDao;
    }
    /**
     * @param mosChickenMstRegUIKanriMenuDao 設定する mosChickenMstRegUIKanriMenuDao。
     */
    public void setMosChickenMstRegUIKanriMenuDao(
            UIKanriMenuDao mosChickenMstRegUIKanriMenuDao) {
        this.mosChickenMstRegUIKanriMenuDao = mosChickenMstRegUIKanriMenuDao;
    }

}
