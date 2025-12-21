package jp.co.isid.mos.bird.bizreport.common.urimaintenance.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizreport.common.dao.MstDiscountKbnInfoDao;
import jp.co.isid.mos.bird.bizreport.common.dao.MstTicketInfoDao;
import jp.co.isid.mos.bird.bizreport.common.entity.MstDiscountKbnInfo;
import jp.co.isid.mos.bird.bizreport.common.entity.MstTicketInfo;
import jp.co.isid.mos.bird.bizreport.common.urimaintenance.entity.UriMainteHeader;
import jp.co.isid.mos.bird.bizreport.common.urimaintenance.logic.UriMainteHeaderLogic;
import jp.co.isid.mos.bird.common.kaikei.dao.CtlSyukeiKbnDao;
import jp.co.isid.mos.bird.common.kaikei.entity.CtlSyukeiKbn;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 売上修正　共通ヘッダー取得ロジック
 * @author mwatanabe
 */
public class UriMainteHeaderLogicImpl implements UriMainteHeaderLogic {
    /** ロジックID */
    public static final String LOGIC_ID = "BBR000L15";

    /* DAO【会計集計区分情報】 */
    private CtlSyukeiKbnDao ctlSyukeiKbnDao;
    /* チケットDAO */
    private MstTicketInfoDao mstTicketInfoDao;
    /* 値引区分DAO */
    private MstDiscountKbnInfoDao mstDiscountKbnInfoDao;
    
    /**
     * 共通ヘッダーを作成
     * @param  String companyCd
     * @param  String syuseiDate
     * @return UriMainteHeaderDto
     * @exception ApplicationException
     */
    public UriMainteHeader execute(String companyCd) {

        //エンティティ新規作成
        UriMainteHeader entity = new UriMainteHeader();

        //作業用
        String tmp;

        //----------------------------
        // 共通部分
        //----------------------------
        //対象店舗
        entity.setTargetTenpo("対象店舗");
        //営業日
        entity.setEigyoDate("営業日");
        
        //----------------------------
        // 売上金タブ
        //----------------------------
        //売上高
        entity.setUriage("売上高");
        //消費税
        entity.setTax("消費税");
        //現金金額
        entity.setGenkinKin("現金金額");
        //実現金在高
        entity.setAridakaJitu("実現金在高");
        //現金過不足
        entity.setGenkinKabusoku("現金過不足");
        //客数
        entity.setKyakusu("客数");
        //取消件数
        entity.setCanKen("取消件数");
        //取消金額
        entity.setCanKin("取消金額");
        //更新者
        entity.setUpdateUser("更新者");

        //----------------------------
        // 前受・売掛タブ
        //----------------------------
        //DAO【会計集計区分情報】実行
        List acntList = ctlSyukeiKbnDao.selectAll();

        //集計区分2
        tmp = getSkbnName(acntList, "02");
        if(tmp == null || tmp.length() == 0){
            entity.setKkbnName2("集計区分2");
        }else{
            entity.setKkbnName2(tmp);
//            entity.setOnSkbn2Link(true);
        }

        //集計区分3
        tmp = getSkbnName(acntList, "03");
        if(tmp == null || tmp.length() == 0){
            entity.setKkbnName3("集計区分3");
        }else{
            entity.setKkbnName3(tmp);
//            entity.setOnSkbn3Link(true);
        }

        //集計区分4
        tmp = getSkbnName(acntList, "04");
        if(tmp == null || tmp.length() == 0){
            entity.setKkbnName4("集計区分4");
        }else{
            entity.setKkbnName4(tmp);
//            entity.setOnSkbn4Link(true);
        }

        //集計区分5
        tmp = getSkbnName(acntList, "05");
        if(tmp == null || tmp.length() == 0){
            entity.setKkbnName5("集計区分5");
        }else{
            entity.setKkbnName5(tmp);
//            entity.setOnSkbn5Link(true);
        }

        //集計区分6
        tmp = getSkbnName(acntList, "06");
        if(tmp == null || tmp.length() == 0){
            entity.setKkbnName6("集計区分6");
        }else{
            entity.setKkbnName6(tmp);
//            entity.setOnSkbn6Link(true);
        }

        //集計区分7
        tmp = getSkbnName(acntList, "07");
        if(tmp == null || tmp.length() == 0){
            entity.setKkbnName7("集計区分7");
        }else{
            entity.setKkbnName7(tmp);
//            entity.setOnSkbn7Link(true);
        }

        //集計区分8
        tmp = getSkbnName(acntList, "08");
        if(tmp == null || tmp.length() == 0){
            entity.setKkbnName8("集計区分8");
        }else{
            entity.setKkbnName8(tmp);
//            entity.setOnSkbn8Link(true);
        }

        //集計区分9
        tmp = getSkbnName(acntList, "09");
        if(tmp == null || tmp.length() == 0){
            entity.setKkbnName9("集計区分9");
        }else{
            entity.setKkbnName9(tmp);
//            entity.setOnSkbn9Link(true);
        }

        //集計区分10
        tmp = getSkbnName(acntList, "10");
        if(tmp == null || tmp.length() == 0){
            entity.setKkbnName10("集計区分10");
        }else{
            entity.setKkbnName10(tmp);
//            entity.setOnSkbn10Link(true);
        }

        //集計区分11
        tmp = getSkbnName(acntList, "11");
        if(tmp == null || tmp.length() == 0){
            entity.setKkbnName11("集計区分11");
        }else{
            entity.setKkbnName11(tmp);
//            entity.setOnSkbn11Link(true);
        }

        //----------------------------
        // 販売タブ
        //----------------------------
        //【チケット情報DAO】実行
        List tcktList = mstTicketInfoDao.select(companyCd, "99999");
        
        //チケット販売1
        tmp = getTcktName(tcktList, "000001");
        if(tmp == null || tmp.length() == 0){
            entity.setTcktName1("チケット販売1");
        }else{
            entity.setTcktName1(tmp);
        }

        //チケット販売1
        tmp = getTcktName(tcktList, "000001");
        if(tmp == null || tmp.length() == 0){
            entity.setTcktName1("チケット販売1");
        }else{
            entity.setTcktName1(tmp);
        }

        //チケット販売2
        tmp = getTcktName(tcktList, "000002");
        if(tmp == null || tmp.length() == 0){
            entity.setTcktName2("チケット販売2");
        }else{
            entity.setTcktName2(tmp);
        }

        //チケット販売3
        tmp = getTcktName(tcktList, "000003");
        if(tmp == null || tmp.length() == 0){
            entity.setTcktName3("チケット販売3");
        }else{
            entity.setTcktName3(tmp);
        }

        //チケット販売4
        tmp = getTcktName(tcktList, "000004");
        if(tmp == null || tmp.length() == 0){
            entity.setTcktName4("チケット販売4");
        }else{
            entity.setTcktName4(tmp);
        }

        //チケット販売5
        tmp = getTcktName(tcktList, "000005");
        if(tmp == null || tmp.length() == 0){
            entity.setTcktName5("チケット販売5");
        }else{
            entity.setTcktName5(tmp);
        }

        //チケット販売6
        tmp = getTcktName(tcktList, "000006");
        if(tmp == null || tmp.length() == 0){
            entity.setTcktName6("チケット販売6");
        }else{
            entity.setTcktName6(tmp);
        }

        //チケット販売7
        tmp = getTcktName(tcktList, "000007");
        if(tmp == null || tmp.length() == 0){
            entity.setTcktName7("チケット販売7");
        }else{
            entity.setTcktName7(tmp);
        }

        //チケット販売8
        tmp = getTcktName(tcktList, "000008");
        if(tmp == null || tmp.length() == 0){
            entity.setTcktName8("チケット販売8");
        }else{
            entity.setTcktName8(tmp);
        }

        //チケット販売9
        tmp = getTcktName(tcktList, "000009");
        if(tmp == null || tmp.length() == 0){
            entity.setTcktName9("チケット販売9");
        }else{
            entity.setTcktName9(tmp);
        }

        //チケット販売10
        tmp = getTcktName(tcktList, "000010");
        if(tmp == null || tmp.length() == 0){
            entity.setTcktName10("チケット販売10");
        }else{
            entity.setTcktName10(tmp);
        }

        //チケット販売11
        tmp = getTcktName(tcktList, "000011");
        if(tmp == null || tmp.length() == 0){
            entity.setTcktName11("チケット販売11");
        }else{
            entity.setTcktName11(tmp);
        }

        //チケット販売12
        tmp = getTcktName(tcktList, "000012");
        if(tmp == null || tmp.length() == 0){
            entity.setTcktName12("チケット販売12");
        }else{
            entity.setTcktName12(tmp);
        }

        //チケット販売13
        tmp = getTcktName(tcktList, "000013");
        if(tmp == null || tmp.length() == 0){
            entity.setTcktName13("チケット販売13");
        }else{
            entity.setTcktName13(tmp);
        }

        //チケット販売14
        tmp = getTcktName(tcktList, "000014");
        if(tmp == null || tmp.length() == 0){
            entity.setTcktName14("チケット販売14");
        }else{
            entity.setTcktName14(tmp);
        }

        //チケット販売15
        tmp = getTcktName(tcktList, "000015");
        if(tmp == null || tmp.length() == 0){
            entity.setTcktName15("チケット販売15");
        }else{
            entity.setTcktName15(tmp);
        }

        //----------------------------
        // 値引きタブ
        //----------------------------
        //【値引区分情報DAO】実行
        List dictList = mstDiscountKbnInfoDao.selectAll();

        //値引区分1
        tmp = getNkbnName(dictList, "01");
        if(tmp == null || tmp.length() == 0){
            entity.setNkbnName1("値引区分1");
        }else{
            entity.setNkbnName1(tmp);
        }

        //値引区分2
        tmp = getNkbnName(dictList, "02");
        if(tmp == null || tmp.length() == 0){
            entity.setNkbnName2("値引区分2");
        }else{
            entity.setNkbnName2(tmp);
        }

        //値引区分3
        tmp = getNkbnName(dictList, "03");
        if(tmp == null || tmp.length() == 0){
            entity.setNkbnName3("値引区分3");
        }else{
            entity.setNkbnName3(tmp);
        }

        //値引区分4
        tmp = getNkbnName(dictList, "04");
        if(tmp == null || tmp.length() == 0){
            entity.setNkbnName4("値引区分4");
        }else{
            entity.setNkbnName4(tmp);
        }

        //値引区分5
        tmp = getNkbnName(dictList, "05");
        if(tmp == null || tmp.length() == 0){
            entity.setNkbnName5("値引区分5");
        }else{
            entity.setNkbnName5(tmp);
        }

        //値引区分6
        tmp = getNkbnName(dictList, "06");
        if(tmp == null || tmp.length() == 0){
            entity.setNkbnName6("値引区分6");
        }else{
            entity.setNkbnName6(tmp);
        }

        //値引区分7
        tmp = getNkbnName(dictList, "07");
        if(tmp == null || tmp.length() == 0){
            entity.setNkbnName7("値引区分7");
        }else{
            entity.setNkbnName7(tmp);
        }

        //値引区分8
        tmp = getNkbnName(dictList, "08");
        if(tmp == null || tmp.length() == 0){
            entity.setNkbnName8("値引区分8");
        }else{
            entity.setNkbnName8(tmp);
        }

        //値引区分9
        tmp = getNkbnName(dictList, "09");
        if(tmp == null || tmp.length() == 0){
            entity.setNkbnName9("値引区分9");
        }else{
            entity.setNkbnName9(tmp);
        }

        return entity;
    }

    /**
     * チケット名称を取得します。
     * @param tcktList
     * @param tcktCd
     */
    private String getTcktName(List tcktList, String tcktCd) {
        
        String ret = "";
        
        if(tcktCd == null || tcktCd.length() == 0){
            return ret;
        }

        if(tcktList != null && tcktList.size() > 0){
            
            for (int i = 0; i < tcktList.size(); i++) {
                MstTicketInfo info = (MstTicketInfo)tcktList.get(i);
                if(tcktCd.equals(info.getTcktCd())){
                    ret = info.getTcktName();
                    break;
                }
            }
        }
        return ret;
    }
    
    /**
     * 値引区分名称を取得します。
     * @param dictList
     * @param nkbnCd
     */
    private String getNkbnName(List dictList, String nkbnCd) {
        
        String ret = "";
        
        if(nkbnCd == null || nkbnCd.length() == 0){
            return ret;
        }

        if(dictList != null && dictList.size() > 0){
            
            for (int i = 0; i < dictList.size(); i++) {
                MstDiscountKbnInfo info = (MstDiscountKbnInfo)dictList.get(i);
                if(nkbnCd.equals(info.getNkbnCd())){
                    ret = info.getNkbnName();
                    break;
                }
            }
        }
        return ret;
    }

    /**
     * 集計区分名称を取得します。
     * @param acntList
     * @param skbnCd
     */
    private String getSkbnName(List acntList, String skbnCd) {
        
        String ret = "";
        
        if(skbnCd == null || skbnCd.length() == 0){
            return ret;
        }

        if(acntList != null && acntList.size() > 0){
            
            for (int i = 0; i < acntList.size(); i++) {
            	CtlSyukeiKbn info = (CtlSyukeiKbn)acntList.get(i);
                if(skbnCd.equals(info.getSyukeiCd())){
                    ret = info.getSyukeiName();
                    break;
                }
            }
        }
        return ret;
    }
    /**
     * @return mstDiscountKbnInfoDao を戻します。
     */
    public MstDiscountKbnInfoDao getMstDiscountKbnInfoDao() {
        return mstDiscountKbnInfoDao;
    }

    /**
     * @param mstDiscountKbnInfoDao 設定する mstDiscountKbnInfoDao。
     */
    public void setMstDiscountKbnInfoDao(MstDiscountKbnInfoDao mstDiscountKbnInfoDao) {
        this.mstDiscountKbnInfoDao = mstDiscountKbnInfoDao;
    }

    /**
     * @return mstTicketInfoDao を戻します。
     */
    public MstTicketInfoDao getMstTicketInfoDao() {
        return mstTicketInfoDao;
    }

    /**
     * @param mstTicketInfoDao 設定する mstTicketInfoDao。
     */
    public void setMstTicketInfoDao(MstTicketInfoDao mstTicketInfoDao) {
        this.mstTicketInfoDao = mstTicketInfoDao;
    }

	/**
	 * DAO【会計集計区分情報】
	 * @return クラス変数ctlSyukeiKbnDao を戻します。
	 */
	public CtlSyukeiKbnDao getCtlSyukeiKbnDao() {
		return ctlSyukeiKbnDao;
	}

	/**
	 * DAO【会計集計区分情報】
	 * @param ctlSyukeiKbnDao を クラス変数ctlSyukeiKbnDaoへ設定します。
	 */
	public void setCtlSyukeiKbnDao(CtlSyukeiKbnDao ctlSyukeiKbnDao) {
		this.ctlSyukeiKbnDao = ctlSyukeiKbnDao;
	}
}
