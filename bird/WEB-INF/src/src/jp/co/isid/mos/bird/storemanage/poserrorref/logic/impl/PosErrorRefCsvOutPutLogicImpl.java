package jp.co.isid.mos.bird.storemanage.poserrorref.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.framework.dto.CsvOutputDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.logic.CsvOutputLogic;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.storemanage.poserrorref.dao.TrnMiseHolidayInfoDao;
import jp.co.isid.mos.bird.storemanage.poserrorref.dao.TrnSalesAdjustInfoDao;
import jp.co.isid.mos.bird.storemanage.poserrorref.dto.PosErrorRefReqDto;
import jp.co.isid.mos.bird.storemanage.poserrorref.entity.TrnMiseHolidayInfo;
import jp.co.isid.mos.bird.storemanage.poserrorref.entity.TrnSalesAdjustInfo;

/**
 * POS集信エラー店舗一覧 CSVダウンロードロジック
 * @author xkonishi
 *
 * 変更日：2009/02/09 
 * 　・店コード、店名称は全行表示するよう変更
 * 　・備考列の右に「●△×合計」列を追加
 */
public class PosErrorRefCsvOutPutLogicImpl implements CsvOutputLogic {

    /**
     * ロジックID定義
     */
    public static final String LOGIC_ID = "BSM015L03";
        
    /**
     * 売上精算情報Dao
     */
    private TrnSalesAdjustInfoDao trnSalesAdjustInfoDao;

    /**
     * 店舗休日予定情報Dao
     */
    private TrnMiseHolidayInfoDao trnMiseHolidayInfoDao;
    /**
     * ファイル名取得
     */
    public String getFileName(CsvOutputDto csvOutputDto) {
        // CSVファイル名
        return "SEISAN.csv";
    }

    /**
     * 出力データ取得処理
     */
    public List getOutputData(CsvOutputDto csvOutputDto) {
        // 事前条件チェック処理
        validate(csvOutputDto);
        
        PosErrorRefReqDto posErrorRefReqDto = (PosErrorRefReqDto) csvOutputDto;
        
        List outputDataList = new ArrayList();
        List headerList1 = new ArrayList();
        List headerList2 = new ArrayList();
        List headerList3 = new ArrayList();
        List headerList4 = new ArrayList();
        List subHeaderList = new ArrayList();

        // ヘッダ部情報生成
        headerList1.add("会社：");
        headerList1.add(posErrorRefReqDto.getCompanyName());
        headerList1.add("");
        headerList1.add("○：");
        headerList1.add( "精算済み");
        headerList1.add("");
        headerList1.add("●：");
        headerList1.add("エラーデータ有り");
        
        headerList2.add("");
        headerList2.add("");
        headerList2.add("");
        headerList2.add("△：");
        headerList2.add("本部修正済み");
        headerList2.add("");        
        headerList2.add("×：");
        headerList2.add("集信不可");

        headerList3.add("");
        headerList3.add("");
        headerList3.add("");
        headerList3.add("-：");
        headerList3.add("休日");
        headerList3.add("");
        headerList3.add("空白：");
        headerList3.add("未処理");
        
        // サブヘッダ部生成
        subHeaderList.add("店舗コード");
        subHeaderList.add("店舗名称");
        subHeaderList.add("月");
        subHeaderList.add("1");
        subHeaderList.add("2");
        subHeaderList.add("3");
        subHeaderList.add("4");
        subHeaderList.add("5");
        subHeaderList.add("6");
        subHeaderList.add("7");
        subHeaderList.add("8");
        subHeaderList.add("9");
        subHeaderList.add("10");
        subHeaderList.add("11");
        subHeaderList.add("12");
        subHeaderList.add("13");
        subHeaderList.add("14");
        subHeaderList.add("15");
        subHeaderList.add("16");
        subHeaderList.add("17");
        subHeaderList.add("18");
        subHeaderList.add("19");
        subHeaderList.add("20");
        subHeaderList.add("21");
        subHeaderList.add("22");
        subHeaderList.add("23");
        subHeaderList.add("24");
        subHeaderList.add("25");
        subHeaderList.add("26");
        subHeaderList.add("27");
        subHeaderList.add("28");
        subHeaderList.add("29");
        subHeaderList.add("30");
        subHeaderList.add("31");
        subHeaderList.add("●△×合計");
        subHeaderList.add("備考");
        
        outputDataList.add(headerList1);
        outputDataList.add(headerList2);
        outputDataList.add(headerList3);
        outputDataList.add(headerList4);
        outputDataList.add(subHeaderList);
                
        // 当月(YYYYMM)を取得
        String to = posErrorRefReqDto.getAppDate().substring(0, 6);
        
        // 前月(YYYYMM)を取得
        String from = null;
        try {
            from = DateManager.getPrevMonth(to, 1);
        } catch (Exception e) {
            throw new FtlSystemException("POS集信エラー店舗一覧 CSVダウンロードロジック･前月取得処理");
        }
        
        String appDate = posErrorRefReqDto.getAppDate();
        // 売上精算情報取得
        List miseInfo = trnSalesAdjustInfoDao.select(posErrorRefReqDto.getSelectedCompanyCd(),
                posErrorRefReqDto.getMiseCd(), posErrorRefReqDto.getOnerCd(),
                posErrorRefReqDto.getUserTypeCd(), from, to);
        
        // Dao【店舗休日予定情報】実行
        List holidayInfo = trnMiseHolidayInfoDao.select(posErrorRefReqDto.getSelectedCompanyCd(), from, to);
        
        // データ部作成
        for (int i = 0; i < miseInfo.size(); i++) {
            List rowItemList = new ArrayList();
            TrnSalesAdjustInfo trnSalesAdjustInfo = (TrnSalesAdjustInfo) miseInfo.get(i);
            //---2009/02/06 updaet start 全レコード店コード、店名称を表示するよう変更
//            TrnSalesAdjustInfo lastTime = null;
//            
//            // 前回取得した要素を取得
//            if (i != 0) {
//                lastTime = (TrnSalesAdjustInfo) miseInfo.get(i - 1);
//            }
//            // 前回と店コードが違う場合に、店コードと店名称を挿入
//            if(i == 0 || !trnSalesAdjustInfo.getMiseCd().equals(lastTime.getMiseCd())) {
                // 店コード
                rowItemList.add(trnSalesAdjustInfo.getMiseCd());                
                // 店名称取得
                String miseName = trnSalesAdjustInfo.getMiseNameKj().trim();
                // クローズ店の場合
                if (trnSalesAdjustInfo.getCloseDt().compareTo(posErrorRefReqDto.getSysDate()) < 0) {
                    // 店名称 + (close);
                    miseName += "(CLOSE)";
                }
                rowItemList.add(miseName);
//            } else {
//                rowItemList.add("");
//                rowItemList.add("");
//            }
            //---2009/02/06 update end
            
            // 月挿入
            rowItemList.add(getMonth(trnSalesAdjustInfo.getSeisanYm()));
            // 月末日取得
            String lastDayOfMonth = trnSalesAdjustInfo.getSeisanYm() + DateManager.getLastDayOfMonth(trnSalesAdjustInfo.getSeisanYm());
            // 精算区分リスト取得
            List seisanKbnList = trnSalesAdjustInfo.getSeisanKbnList();
            // オープン日･クローズ日取得
            String openDt = trnSalesAdjustInfo.getOpenDt();
            String closeDt = trnSalesAdjustInfo.getCloseDt();
            
            String appDateSeisanKbn = null;
            int errCnt = 0;
            // 精算区分挿入
            for (int j = 0; j < seisanKbnList.size(); j++ ) {
                // 集信日取得
                String shuDt = getShuDt(trnSalesAdjustInfo.getSeisanYm(), j + 1);
                // アプリ日付と集信日が同じ場合、精算区分を取得する。
                if (shuDt.equals(posErrorRefReqDto.getAppDate())) {
                    appDateSeisanKbn = (String)seisanKbnList.get(j);
                }
                // 未来日チェック
                boolean isFuture = checkFuture(appDate, shuDt);
                // 休日チェック
                boolean isHoliday = checkHoliday(holidayInfo, trnSalesAdjustInfo.getMiseCd(), shuDt);
                // 精算区分取得
                String seisanKbn= (String) seisanKbnList.get(j);
                seisanKbn = seisanKbn.trim();
                
                // 存在しない日付の場合
                if (shuDt.compareTo(lastDayOfMonth) > 0 ) {
                    rowItemList.add(getMark(seisanKbn));
                // 精算区分を集信不可（休日）に変更する場合
                } else if ((openDt.compareTo(shuDt) <= 0 && closeDt.compareTo(shuDt) >= 0 && appDate.compareTo(shuDt) >= 0) &&
                        isHoliday && (seisanKbn.equals("3") || seisanKbn.equals("4") || (seisanKbn.equals("") && !isFuture))) {
                    seisanKbn = "5";
                    rowItemList.add(getMark(seisanKbn));
                // 未処理を集信不可に変更する場合
                } else if ((openDt.compareTo(shuDt) <= 0 && closeDt.compareTo(shuDt) >= 0 && appDate.compareTo(shuDt) >= 0) &&
                        !isHoliday && seisanKbn.equals("") && !isFuture && shuDt.compareTo(trnSalesAdjustInfo.getP4OpenDt()) >= 0) {
                    seisanKbn = "4";
                    rowItemList.add(getMark(seisanKbn));
                } else {
                    rowItemList.add(getMark(seisanKbn));
                }
                if ("2".equals(seisanKbn) || "3".equals(seisanKbn) || "4".equals(seisanKbn) || "6".equals(seisanKbn)) {
                    errCnt++;
                }
            }
            // ●×△合計
            rowItemList.add(String.valueOf(errCnt));
            // 当月の場合、備考項目に挿入
            if (trnSalesAdjustInfo.getSeisanYm().equals(to)) {
                rowItemList.add(getComment(appDateSeisanKbn));
            }
            else {
                rowItemList.add("");
            }
            
            outputDataList.add(rowItemList);
        }
        return outputDataList;
    }

    /**
     * 事前条件チェック処理
     */
    public void validate(CsvOutputDto csvOutputDto) {
        PosErrorRefReqDto posErrorRefReqDto = (PosErrorRefReqDto) csvOutputDto; 
        // 当月(YYYYMM)を取得
        String to = posErrorRefReqDto.getAppDate().substring(0, 6);
        // 前月(YYYYMM)を取得
        String from = null;
        
        try {
            from = DateManager.getPrevMonth(to, 1);
             
        } catch (Exception e) {
            throw new FtlSystemException("POS集信エラー店舗一覧 CSVダウンロードロジック･前月取得処理");
        }
        int count = trnSalesAdjustInfoDao.getCount(posErrorRefReqDto.getSelectedCompanyCd(),
                posErrorRefReqDto.getMiseCd(), posErrorRefReqDto.getOnerCd(),
                posErrorRefReqDto.getUserTypeCd(), from, to);
 
        if (count == 0) {
             throw new NotExistException("該当データ"); 
        }
    }


    /**
     * 集信日取得
     * @param seisanYm 精算年月
     * @param index    インデックス
     * @return 集信日
     */
    private String getShuDt(String seisanYm, int index) {
        Integer dd = new Integer(index);
        String day = dd.toString();
        if (day.length() == 1) {
            day = "0" + day;
        }     
        return seisanYm + day;
    }
    
    /**
     * 休日チェック
     * @param holidayInfo 休日情報
     * @param miseCd      店コード
     * @param shuDt       集信日
     * @return 休日判定
     */
    private boolean checkHoliday(List holidayInfo, String miseCd, String shuDt) {
        boolean isHoliday = false;
        
        for ( Iterator it = holidayInfo.iterator(); it.hasNext();) {
            TrnMiseHolidayInfo trnMiseHolidayInfo = (TrnMiseHolidayInfo) it.next();
            
            if (miseCd.equals(trnMiseHolidayInfo.getMiseCd()) &&
                    shuDt.equals(trnMiseHolidayInfo.getMiseHolDt())) {
                isHoliday = true;
            }
        }
        return isHoliday;
    }
    
    /**
     * 未来日チェック
     * @param appDate
     * @param shuDt
     * @return 未来日判定
     */
    private boolean checkFuture(String appDate, String shuDt) {
        boolean isFuture = false;
        
        if (appDate.compareTo(shuDt) < 0) {
            isFuture = true;
        }
        return isFuture;
    }
    /**
     * 月取得
     * @param   yyyyMm 年月
     * @return　month  月
     */
    private String getMonth(String yyyyMm) {
        String month = yyyyMm.substring(4) + "月";
        
        if (month.startsWith("0")) {
             month = month.substring(1);
        }      
        return month;
    }

    /**
     * 精算区分記号取得
     * @param seisanKbn 精算区分
     * @return mark     精算区分記号
     */
    private String getMark (String seisanKbn) {
        String mark = null;
          
        String kbn = seisanKbn.trim();
        // 未処理
        if (kbn.equals("")) {
            mark = "";
        // 正常（精算済）
        } else if (kbn.equals("1")) {
            mark = "○";
        // エラーデータ有り
        } else if (kbn.equals("2")) {
            mark = "●";
        // 集信不可（未清算）･集信不可（通信異常）
        } else if (kbn.equals("3") || seisanKbn.equals("4")) {
            mark = "×";
        // 集信不可（休日）
        } else if (kbn.equals("5")) {
            mark = "-";
        // 本部修正
        } else if (kbn.equals("6")) {
            mark = "△";     
        }
        return mark;
    }
    
    /**
     * 備考欄コメント取得
     * @param seisanKbn 精算区分
     * @return comment  備考欄コメント
     */    
    private String getComment(String seisanKbn) {
        String comment = null;
        
        String kbn = seisanKbn.trim();
        // 未処理･正常（精算済）･本部修正
        if (kbn.equals("") || kbn.equals("1") || kbn.equals("6")) {
            comment = "";
        // エラーデータ有り
        } else if (kbn.equals("2")) {
            comment = "エラーデータ有り";
        // 集信不可（未清算）
        } else if (kbn.equals("3")) {
            comment = "集信不可（未清算）";
        // 集信不可（通信異常）
        } else if (kbn.equals("4")) {
            comment = "集信不可（通信異常）";
        // 集信不可（休日）
        } else if (kbn.equals("5")) {
            comment = "集信不可（休日）";            
        }
        return comment;
    }
    
    
    public TrnSalesAdjustInfoDao getTrnSalesAdjustInfoDao() {
        return trnSalesAdjustInfoDao;
    }

    public void setTrnSalesAdjustInfoDao(TrnSalesAdjustInfoDao trnSalesAdjustInfoDao) {
        this.trnSalesAdjustInfoDao = trnSalesAdjustInfoDao;
    }

    public TrnMiseHolidayInfoDao getTrnMiseHolidayInfoDao() {
        return trnMiseHolidayInfoDao;
    }

    public void setTrnMiseHolidayInfoDao(TrnMiseHolidayInfoDao trnMiseHolidayInfoDao) {
        this.trnMiseHolidayInfoDao = trnMiseHolidayInfoDao;
    }
}