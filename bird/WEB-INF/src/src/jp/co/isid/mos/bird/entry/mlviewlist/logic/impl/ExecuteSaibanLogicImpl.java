package jp.co.isid.mos.bird.entry.mlviewlist.logic.impl;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.entry.mlviewlist.dao.UIExamNoCounterDao;
import jp.co.isid.mos.bird.entry.mlviewlist.dao.UIMlEntryListDao;
import jp.co.isid.mos.bird.entry.mlviewlist.dao.UIMlResultStatusDao;
import jp.co.isid.mos.bird.entry.mlviewlist.dto.MlViewListDto;
import jp.co.isid.mos.bird.entry.mlviewlist.entity.UIExamNoCounter;
import jp.co.isid.mos.bird.entry.mlviewlist.entity.UIMlEntryInfo;
import jp.co.isid.mos.bird.entry.mlviewlist.entity.UIMlResultStatus;
import jp.co.isid.mos.bird.entry.mlviewlist.logic.ExecuteSaibanLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;

/**
 * 受験番号採番ロジック
 * @author kusama
 */
public class ExecuteSaibanLogicImpl implements ExecuteSaibanLogic {

    /* ロジックID */
    public static final String LOGIC_ID = "BEN009L04";

    private UIExamNoCounterDao uiExamNoCounterDao;
    private UIMlEntryListDao uIMlEntryListDao;
    private UIMlResultStatusDao uiMlResultStatusDao;
    
	/**
	 * 受験番号採番
     * @param MlViewListDto mlViewListCommondto
	 */
	public void execute(MlViewListDto dto) {
        String entryCd   = dto.getEntryCd();
        String entryYear = dto.getEntryYear();
        String entryKai  = dto.getEntryKai();
        
        String userId = dto.getUserId();
        Timestamp lastTmsp = DateManager.getCurrentTimestamp();
        
        Map mapStaffId = new HashMap();
        //受験番号フォーマット用
        CodeFormatter codeFormatter = new CodeFormatter(5, "");
        codeFormatter.setFormatPattern("00000");
        
        // マスターライセンス結果状況履歴の取得
        List listResultStatus = getUiMlResultStatusDao().getMlResultStatus(entryYear, entryKai);
        // マスターライセンスエントリー状況の取得
        List listEntryStatus = getUIMlEntryListDao().getMlEntryInfoSaiban(entryCd, entryYear, entryKai);
        
        // 受験番号が採番されていないレコードを探す
        if (listResultStatus != null && !listResultStatus.isEmpty()) {
            for (Iterator ite = listResultStatus.iterator(); ite.hasNext();) {
                UIMlResultStatus uiMlResultStatus = (UIMlResultStatus) ite.next();
                if (isNull(uiMlResultStatus.getExamNo())) {
                    mapStaffId.put(uiMlResultStatus.getStaffId(), "");
                }
            }
        }
        if (listEntryStatus != null && !listEntryStatus.isEmpty()) {
            for (Iterator ite = listEntryStatus.iterator(); ite.hasNext();) {
                UIMlEntryInfo uiMlEntryInfo = (UIMlEntryInfo) ite.next();
                if (isNull(uiMlEntryInfo.getExamNo())) {
                    String staffId = uiMlEntryInfo.getStaffId();
                    if (!mapStaffId.containsKey(staffId)) {
                        mapStaffId.put(staffId, "");
                    }
                }
            }
        }
        // 採番する件数
        int saibanCnt = mapStaffId.size();
        // 採番件数が０件の場合は、処理終了
        if (saibanCnt == 0) {
            return;
        }
        
        
        // 採番テーブル取得
        List listSaibanCounter = getUiExamNoCounterDao().getCounter("01");
        UIExamNoCounter uiExamNoCounter = (UIExamNoCounter) listSaibanCounter.get(0);
        
        
        //DBに書き込むカウンター値を算出
        BigDecimal bigNowDb = new BigDecimal(uiExamNoCounter.getNoCounter());
        BigDecimal bigUpdateValue = bigNowDb.add(new BigDecimal(new Integer(saibanCnt).toString()));
        
        //採番対象のスタッフIDに受験番号を割り振る
        int loopCnt = 1;
        for (Iterator ite = mapStaffId.keySet().iterator(); ite.hasNext();) {
            String staffId = (String) ite.next();
            String examNo = bigNowDb.add(new BigDecimal(new Integer(loopCnt).toString())).toString();
            mapStaffId.put(staffId, codeFormatter.format(examNo, true));
            loopCnt++;
        }
        //マスターライセンス結果状況履歴更新
        for (Iterator ite = listResultStatus.iterator(); ite.hasNext();) {
            UIMlResultStatus uiMlResultStatus = (UIMlResultStatus) ite.next();
            String staffId = uiMlResultStatus.getStaffId();
            if (mapStaffId.containsKey(staffId)) {
                uiMlResultStatus.setExamNo(codeFormatter.format((String) mapStaffId.get(staffId), true));
                uiMlResultStatus.setLastUser(userId);
                uiMlResultStatus.setLastPgm(LOGIC_ID.substring(0, 6));
                uiMlResultStatus.setLastTmsp(lastTmsp);
                getUiMlResultStatusDao().updateEntity(uiMlResultStatus);
            }
        }

        //マスターライセンスエントリー状況の更新
        for (Iterator ite = listEntryStatus.iterator(); ite.hasNext();) {
            UIMlEntryInfo uiMlEntryInfo = (UIMlEntryInfo) ite.next();
            String staffId = uiMlEntryInfo.getStaffId();
            if (mapStaffId.containsKey(staffId)) {
                uiMlEntryInfo.setExamNo(codeFormatter.format((String) mapStaffId.get(staffId), true));
                uiMlEntryInfo.setLastUser(userId);
                uiMlEntryInfo.setLastPgm(LOGIC_ID.substring(0, 6));
                uiMlEntryInfo.setLastTmsp(lastTmsp);
                getUIMlEntryListDao().updateEntity(uiMlEntryInfo);
            }
        }

        
        // マスターライセンス受験番号採番カウンター更新
        uiExamNoCounter.setNoCounter(bigUpdateValue.toString());
        uiExamNoCounter.setLastUser(userId);
        uiExamNoCounter.setLastPgm(LOGIC_ID.substring(0, 6));
        uiExamNoCounter.setLastTmsp(lastTmsp);
        getUiExamNoCounterDao().updateCounter(uiExamNoCounter);
    }
    
    public UIExamNoCounterDao getUiExamNoCounterDao() {
        return uiExamNoCounterDao;
    }
    public void setUiExamNoCounterDao(UIExamNoCounterDao uiExamNoCounterDao) {
        this.uiExamNoCounterDao = uiExamNoCounterDao;
    }

    public UIMlEntryListDao getUIMlEntryListDao() {
        return uIMlEntryListDao;
    }

    public void setUIMlEntryListDao(UIMlEntryListDao mlEntryListDao) {
        uIMlEntryListDao = mlEntryListDao;
    }

    public UIMlResultStatusDao getUiMlResultStatusDao() {
        return uiMlResultStatusDao;
    }

    public void setUiMlResultStatusDao(UIMlResultStatusDao uiMlResultStatusDao) {
        this.uiMlResultStatusDao = uiMlResultStatusDao;
    }
    
    private boolean isNull(String val) {
        return (val == null || "".equals(val.trim())) ? true : false;
    }

}
