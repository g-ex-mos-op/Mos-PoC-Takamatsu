package jp.co.isid.mos.bird.portal.login.logic.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.DuplicateDataException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.portal.login.dao.CtlMatrixRirekiDao;
import jp.co.isid.mos.bird.portal.login.dao.UIUserMatrixInfoDao;
import jp.co.isid.mos.bird.portal.login.dto.MatrixRegistDto;
import jp.co.isid.mos.bird.portal.login.entity.CtlMatrixRireki;
import jp.co.isid.mos.bird.portal.login.entity.UIUserMatrixInfo;
import jp.co.isid.mos.bird.portal.login.logic.UpdateMatrixInfoLogic;


/**
 * @author xnkusama
 */
public class UpdateMatrixInfoLogicImpl implements UpdateMatrixInfoLogic {

    /* ロジックＩＤ*/ 
    public static final String LOGIC_ID = "BPO000L06";
    
    /** マトリクス認証数 */
    public static final int MATRIX_NUM = 3;
    /** マトリクス履歴チェック数 */
    //private static final int MATRIX_RIREKI_CHECK_NUM = 2;
    
    private UIUserMatrixInfoDao uiUserMatrixInfoDao;
    private CtlMatrixRirekiDao loginCtlMatrixRirekiDao;
    
    /* マトリックス情報取得処理 */
    public void updateMatrixInfo(final MatrixRegistDto dto) throws ApplicationException {
        // 事前条件チェック
        Hashtable hashValues = validate(dto);
        UIUserMatrixInfo entity = new UIUserMatrixInfo();
        //登録済み情報取得
        List listMatrixInfo = getUiUserMatrixInfoDao().getUserMatrixInfo(dto.getUserId());
        UIUserMatrixInfo entityDbMatrix = null;
        if (listMatrixInfo != null && !listMatrixInfo.isEmpty()) {
            entity = (UIUserMatrixInfo) listMatrixInfo.get(0);
            entityDbMatrix = (UIUserMatrixInfo) listMatrixInfo.get(0);
        }

        entity.setUserId(dto.getUserId());
        entity.setMatrixKey1((String) hashValues.get("1"));
        entity.setMatrixKey2((String) hashValues.get("2"));
        entity.setMatrixKey3((String) hashValues.get("3"));
//        entity.setMatrixKey4((String) hashValues.get("4"));
//        entity.setMatrixKey5((String) hashValues.get("5"));
        entity.setMatrixKey4("");
        entity.setMatrixKey5("");
        entity.setLastUpdtDt(getNowDay());
        entity.setLastUser(dto.getUserId());
        entity.setLastPgm("BPO000");

        // 登録・更新
        if (listMatrixInfo == null || listMatrixInfo.isEmpty()) {
            //新規登録
            entity.setFirstUser(dto.getUserId());
            entity.setFirstPgm("BPO000");
            entity.setFirstTmsp(DateManager.getCurrentTimestamp());
            getUiUserMatrixInfoDao().insertUserMatrixInfo(entity);
        }
        else {
            //更新登録
            //過去登録分とのチェック
            List listMatrixRireki = getLoginCtlMatrixRirekiDao().getUserMatrixInfoUserAll(dto.getUserId());
            if (listMatrixRireki != null && !listMatrixRireki.isEmpty()) {
                for (Iterator ite = listMatrixRireki.iterator(); ite.hasNext();) {
                    CtlMatrixRireki ctlMatrixRireki = (CtlMatrixRireki) ite.next();
                    if (entityDbMatrix.getMatrixKey1().trim().equals(ctlMatrixRireki.getMatrixKey1().trim()) 
                            && entityDbMatrix.getMatrixKey2().trim().equals(ctlMatrixRireki.getMatrixKey2().trim())
                            && entityDbMatrix.getMatrixKey3().trim().equals(ctlMatrixRireki.getMatrixKey3().trim())
                            && entityDbMatrix.getMatrixKey4().trim().equals(ctlMatrixRireki.getMatrixKey4().trim())
                            && entityDbMatrix.getMatrixKey5().trim().equals(ctlMatrixRireki.getMatrixKey5().trim()))
                    {
                        throw new GenericMessageException("過去2回と同一の認証キーは設定できません。");
                    }
                }
            }

            getUiUserMatrixInfoDao().updateUserMatrixInfo(entity);
        }

        //履歴テーブルへ登録
        //更新の場合、履歴テーブルへ登録
        CtlMatrixRireki ctlMatrixRireki = new CtlMatrixRireki();
        ctlMatrixRireki.setUserId(dto.getUserId());
        ctlMatrixRireki.setMatrixKey1(entity.getMatrixKey1());
        ctlMatrixRireki.setMatrixKey2(entity.getMatrixKey2());
        ctlMatrixRireki.setMatrixKey3(entity.getMatrixKey3());
        ctlMatrixRireki.setMatrixKey4(entity.getMatrixKey4());
        ctlMatrixRireki.setMatrixKey5(entity.getMatrixKey5());
        ctlMatrixRireki.setLastUpdtDt(getNowDay());
        ctlMatrixRireki.setFirstUser(entity.getFirstUser());
        ctlMatrixRireki.setFirstPgm(entity.getFirstPgm());
        ctlMatrixRireki.setFirstTmsp(entity.getFirstTmsp());
        ctlMatrixRireki.setLastUser(dto.getUserId());
        ctlMatrixRireki.setLastPgm("BPO000");
        getLoginCtlMatrixRirekiDao().insertUserMatrixInfo(ctlMatrixRireki);
    }

    /*
     * 事前条件チェック
     * @return Hashtable
     */
    private Hashtable validate(final MatrixRegistDto dto) throws ApplicationException {
        // ユーザーID
        String userId = dto.getUserId();
        
        /* １．必須チェック */
        if (userId == null) {
            throw new NotNullException("ユーザーID", "");
        }
        
        /* ２．キー１～３のチェック */
        Hashtable hashValues = new Hashtable();
        String keyRow[] = new String[]{"1", "2", "3", "4", "5"};
        String keyCol[] = new String[]{"A", "B", "C", "D", "E"};
        int keyCnt = 0;
//        int x = 0;
//        int y = 0;
        for (int i = 0; i < dto.getInputList().size(); i++) {
            List row = (List) dto.getInputList().get(i);
            for (int j = 0; j < row.size(); j++) {
                String val = (String) row.get(j);
                if (val != null && !val.trim().equals("")) {
                    if (hashValues.containsKey(val)) {
                        throw new DuplicateDataException("「" + val + "」");                    	
                    }
                    keyCnt++;
                    hashValues.put(val, keyCol[i] + keyRow[j]);
                }
            }
        }
        // ３つ以外の入力はエラー
        if (keyCnt != MATRIX_NUM) {
            throw new NoInputException("１～３の３ヶ所");
        }
        // 入力内容チェック（１～３のみ許可）
        String allowMoji = "123";
        for (Enumeration e = hashValues.keys(); e.hasMoreElements();) {
            String key = (String) e.nextElement();
            if (allowMoji.indexOf(key) < 0) {
                throw new NoInputException("１～３");
            }
        }
        
        return hashValues;
    }

    // 「yyyyMMdd」型の現在日付文字列を取得
    private String getNowDay() {
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat();

        synchronized ( formatter ) {
            formatter.setLenient(false);
            formatter.applyPattern("yyyyMMdd");
            return formatter.format(cal.getTime());
        }
    }

    public UIUserMatrixInfoDao getUiUserMatrixInfoDao() {
        return uiUserMatrixInfoDao;
    }

    public void setUiUserMatrixInfoDao(UIUserMatrixInfoDao uiUserMatrixInfoDao) {
        this.uiUserMatrixInfoDao = uiUserMatrixInfoDao;
    }

    public CtlMatrixRirekiDao getLoginCtlMatrixRirekiDao() {
        return loginCtlMatrixRirekiDao;
    }

    public void setLoginCtlMatrixRirekiDao(
            CtlMatrixRirekiDao loginCtlMatrixRirekiDao) {
        this.loginCtlMatrixRirekiDao = loginCtlMatrixRirekiDao;
    }
}
