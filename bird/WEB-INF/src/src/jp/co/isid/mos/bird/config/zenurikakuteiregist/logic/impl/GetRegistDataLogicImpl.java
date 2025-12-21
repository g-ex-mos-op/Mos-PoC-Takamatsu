/**
 * 
 */
package jp.co.isid.mos.bird.config.zenurikakuteiregist.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.config.zenurikakuteiregist.dao.TrnUsrStatusDao;
import jp.co.isid.mos.bird.config.zenurikakuteiregist.entity.TrnUsrStatus;
import jp.co.isid.mos.bird.config.zenurikakuteiregist.logic.GetRegistDataLogic;
import jp.co.isid.mos.bird.config.zenurikakuteiregist.util.ZenurikakuteiRegistUtil;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * [機能設定]【売上速報前月売上確定登録】
 * 編集情報の取得
 * 
 * 作成日:2008/08/15
 * @author xkinu
 *
 */
public class GetRegistDataLogicImpl implements GetRegistDataLogic {
    /** ロジックID */
    public static final String LOGIC_ID = ZenurikakuteiRegistUtil.SCREEN_ID+"L01";
	/**
	 * DAO【売上速報レポート確定状況】
	 */
	private TrnUsrStatusDao zenurikakuteiRegistTrnUsrStatusDao;
	/**
	 * 実行処理
	 * 
	 * @param sysDate システム日付
	 * @see jp.co.isid.mos.bird.config.zenurikakuteiregist.logic.GetRegistDataLogic#execute(java.lang.String)
	 */
	public List execute(String sysDate) {
        //１．事前条件判断処理
        validate(sysDate);
        String sysNendo = DateManager.getCurrentYear(sysDate);
        String sysLastYm = "";
        String nextNendo = "";   	
        try {
        	sysLastYm = DateManager.getPrevMonth(sysDate.substring(0,6), 1);
        	nextNendo = DateManager.getNextYear(sysNendo, 1);
    	}
    	catch (Exception ex) {
    		throw new FtlSystemException("ロジック【編集情報の取得】","実行処理メソッド内日付取得ロジック",ex);
    	}
        //２．変数.表示年月開始月を生成し、当年度の4月の値を設定します。
        String taishoYmFrom = sysNendo+"04";
        //３．パラメータ.システム日付が4月の場合は
        //    変数.表示年月開始月へ前年度の3月(システム日付の前月)の年月値を設定します。
        if(sysDate.substring(4, 6).equals("04")) {
        	taishoYmFrom = sysDate.substring(0, 4)+"03";
        }
        //４．変数.表示年月終了月を生成し、システム年度の2月の値を設定します。
        String taishoYmTo = nextNendo+"02";
        //５．Dao【売上速報レポート確定状況】検索を実行し、戻り値List[[編集情報]]を取得します。
        List listRegistData = getZenurikakuteiRegistTrnUsrStatusDao().select(taishoYmFrom, taishoYmTo);
        //６．変数.売上速報年月を生成し、変数.表示年月開始月の値を設定します。
        String uriSokuYm = taishoYmFrom;
        //７．変数.whileインデックスを生成し、0を設定します。
        int i = 0;
        //８．while文で変数.売上速報年月 != 変数.表示年月終了月の条件で、下記の処理を行います。
        while(!uriSokuYm.equals(taishoYmTo)) {
        	try {
        		//while-1.変数.売上速報年月へ変数.表示年月開始月から
        		//        while現行回数(0ゼロスタート)分を足した年月の値を設定します。
        		uriSokuYm = DateManager.getNextMonth(taishoYmFrom, i);
        	}
        	catch (Exception ex) {
        		throw new FtlSystemException("ロジック【編集情報の取得】","DateManager.getNextMonthメソッド処理",ex);
        	}
        	//while-2.while現行回数(0ゼロスタート)がList[[編集情報]]の件数以上か、
        	//        又は変数.売上速報年月とList[[編集情報]]の変数.whileインデックス値の位置にある
        	//                [編集情報].売上速報レポート年月が同じでない場合、下記の処理を行います。
        	if(i>=listRegistData.size()
        			|| !uriSokuYm.equals(((TrnUsrStatus)listRegistData.get(i)).getUriSokuYm())) {
        		//①.エンティティ[売上速報レポート確定状況]をインスタンス化します。
        		TrnUsrStatus newEntity = new TrnUsrStatus();
        		//②.エンティティ[売上速報レポート確定状況]へ下記の値を設定します。
        		//エンティティ[売上速報レポート確定状況].売上速報レポート年月 ＝ 変数.売上速報年月
        		newEntity.setUriSokuYm(uriSokuYm);
        		//エンティティ[売上速報レポート確定状況].ステータスフラグ ＝ 計算値
        		newEntity.setStatusFlg(ZenurikakuteiRegistUtil.STATUS_KEISAN);
        		//エンティティ[売上速報レポート確定状況].確定フラグ ＝ ””
        		newEntity.setKakuteiFlg("");
        		//③.List[[編集情報]]のwhileインデックス値の位置へ
        		//   エンティティ[売上速報レポート確定状況]を追加します。
        		listRegistData.add(i, newEntity);
        	}
        	//while-2.List[[編集情報]]の変数.whileインデックス値の位置にある[編集情報]を取得します。
        	TrnUsrStatus entity = (TrnUsrStatus)listRegistData.get(i);
        	//while-3.システム日付前月のエンティティの場合下記の処理を行います。
        	if(uriSokuYm.equals(sysLastYm)) {
        		//①ステータスが”計算値”の場合は編集フラグをtrueに設定します。
        		if(entity.getStatusFlg().equals(ZenurikakuteiRegistUtil.STATUS_KEISAN)) {
        			entity.setRegistFlg(true);
        		}
         	}
        	//while-4.変数.whileインデックスへ+1します。
        	i++;
        }
        
        //９．List[[編集情報]]をリターンします。
		return listRegistData;
	}
    /**
     * 事前条件処理
     * @param sysDate
     * @throws MissingDataException
     */
    private void validate(final String sysDate) {
        if(CommonUtil.isNull(sysDate)){
            //MSG【E0202】(がありません。)
            throw new MissingDataException("システム日付");
        }
    }
	/**
	 * DAO【売上速報レポート確定状況】取得処理
	 * 
	 * @return zenurikakuteiRegistTrnUsrStatusDao を戻します。
	 */
	public TrnUsrStatusDao getZenurikakuteiRegistTrnUsrStatusDao() {
		return zenurikakuteiRegistTrnUsrStatusDao;
	}
	/**
	 * DAO【売上速報レポート確定状況】設定処理
	 * 
	 * @param dao zenurikakuteiRegistTrnUsrStatusDaoへ設定します。
	 */
	public void setZenurikakuteiRegistTrnUsrStatusDao(
			TrnUsrStatusDao dao) {
		this.zenurikakuteiRegistTrnUsrStatusDao = dao;
	}


}
